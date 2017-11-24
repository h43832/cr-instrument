
package ci;

import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.Vector;
import y.ylib.ylib;

public class CIMiscThread extends Thread{
CrInstrument instrument;
  Vector waitV=new Vector();
  boolean isSleep=false;
  public ResourceBundle bundle2 = java.util.ResourceBundle.getBundle("ci/Bundle");
  long waitTime=1000L*60L*60L*24L,waitTime2=1L;
    public CIMiscThread(CrInstrument instrument){
    this.instrument=instrument;
  }
  public void run(){
      while(true){
        while(waitV.size()>0){
            CIDataClass dataClass=(CIDataClass)waitV.get(0);
            int miscCode=(int)dataClass.time;
                 if(miscCode==1){
                   }
                   else if(miscCode==2){
                   }
                   else if(miscCode==3){
                     instrument.wn.openURL.open(ylib.replace(ylib.replace(dataClass.data,"[#user.country#]", System.getProperty("user.country")),"[#user.language#]",System.getProperty("user.language")));
                   }
                   else if(miscCode==4){
                     String act[]=ylib.csvlinetoarray((String)instrument.actionTM.get(dataClass.dataSrc));
                     if(((CIAction)instrument.jClasses.get(act[35]))==null){
                       if(!instrument.loadClass(act[35],2)) {instrument.sysLog("java action class \""+dataClass.data+"\" not exist or not implements CIAction interface."); return;}
                     }
                     if(((CIAction)instrument.jClasses.get(act[35]))!=null){
                       ((CIAction)instrument.jClasses.get(act[35])).startAction(instrument, dataClass.dataSrc,dataClass);
                     }
                   }
                   else if(miscCode==5){
                     String act[]=ylib.csvlinetoarray((String)instrument.actionTM.get(dataClass.dataSrc));
                     sendCmd(act,1);
                   }
                   else if(miscCode==6){
                     if (instrument.framePanel2.btnStart.getText().trim().equals(bundle2.getString("CrInstrument.xy.msg3")))  instrument.pressStartBtn(2);
                   }
                   else if(miscCode==7){
                     if (instrument.framePanel2.btnStart.getText().trim().equals(bundle2.getString("CrInstrument.xy.msg4")))instrument.pressStartBtn(2);
                   }
                   else if(miscCode==8){
                     String act[]=ylib.csvlinetoarray((String)instrument.actionTM.get(dataClass.dataSrc));
                     sendCmd(act,2);
                   }
                   else if(miscCode==9){
                     String act[]=ylib.csvlinetoarray((String)instrument.actionTM.get(dataClass.dataSrc));
                     sendCmd(act,3);
                   }
                   else if(miscCode==10){
                     instrument.connect();
                   }
                   else if(miscCode==11){
                   }
                   else if(miscCode==12){
                     if(instrument.connected){
                       String act[]=ylib.csvlinetoarray((String)instrument.actionTM.get(dataClass.dataSrc));
                       String id=instrument.getItemId(instrument.getDataSrcFromStation(act[1]));
                       String cmd="performcommand wsn.WSN stopcontinue "+instrument.getDataSrcFromStation(act[1])+" all null"; 
                       instrument.wn.w.sendToOne(cmd,id);
                     } else {
                          instrument.sysLog("Yet not connected! can't send command.");
                       }
                   }
                   else if(miscCode==13){
                   }
          waitV.remove(0);
          }
          try{
              isSleep=true;
              sleep(waitTime);
          }catch(InterruptedException e){
             isSleep=false;
          }
      }
  }

  void sendCmd(String actInfo[],int type){
  String cmd="";
  if(actInfo.length>37 && actInfo[36]!=null && actInfo[36].equalsIgnoreCase("Y") && actInfo[37].length()>0){
    if(((CIDataGenerator)instrument.jClasses.get(actInfo[37]))==null){
       if(!instrument.loadClass(actInfo[37],4)) instrument.sysLog("Failed to load cmd class "+actInfo[37]+".");
    }
    if(((CIDataGenerator)instrument.jClasses.get(actInfo[37]))!=null){
      byte b[]= ((CIDataGenerator)instrument.jClasses.get(actInfo[37])).getData();
      cmd=(actInfo[10].equals("1")? instrument.wn.byteToStr(b):new String(b)).trim(); 
    }
  } else cmd=actInfo[11];

  if(type==2){
     String nextInfo[]=instrument.getNextDevice(actInfo[1],actInfo[39],actInfo[16],actInfo[30]); 
     if(nextInfo==null) {instrument.sysLog(actInfo[1]+"-"+actInfo[39]+"-"+actInfo[16]+"-"+actInfo[30]+" has no next device."); return;}
     String key=nextInfo[0]+","+nextInfo[1]+","+nextInfo[2]+","+nextInfo[3]+","+nextInfo[4];
     String info[]=ylib.csvlinetoarray((String)instrument.sensors.get(key));  
     actInfo[1]=nextInfo[0];
     actInfo[27]=info[11];
     actInfo[28]=info[23];
  } else if(type==3){
     String nextInfo[]=instrument.getNextStation(actInfo[1]); 
     if(nextInfo==null) {instrument.sysLog(actInfo[1]+" has no next station."); return;}
     actInfo[1]=nextInfo[0];
     actInfo[27]= (String)instrument.portToCoors.get(instrument.wn.getPort(nextInfo[1]));
  }
  cmd=instrument.actionThread.getTextValue(cmd, ""+instrument.dataValue,instrument.textValue);
  cmd=ylib.replace(cmd, "[#masterid#]", actInfo[27]);
  cmd=ylib.replace(cmd, "[#deviceid#]", actInfo[28]);
  cmd=ylib.replace(cmd, "[#Masterid#]", actInfo[27]);
  cmd=ylib.replace(cmd, "[#Deviceid#]", actInfo[28]);
  cmd=ylib.replace(cmd, "[#MASTERID#]", actInfo[27]);
  cmd=ylib.replace(cmd, "[#DEVICEID#]", actInfo[28]);
  cmd=instrument.wn.w.e642(cmd);
  if(cmd.trim().length()<1) {
      instrument.sysLog("No commad or data to be send");
      return;
  }

      byte [] b=null;

      if(actInfo[14].equalsIgnoreCase("Y")) {

          if(instrument.wn.isNumeric(actInfo[15]) && Double.parseDouble(actInfo[15])>0) {
              if(Double.parseDouble(actInfo[15])<0.5) actInfo[15]="0.5";
          }
          else {
            instrument.sysLog("Warning: No time interval for \"continue send\".");
              return;
          }

      }
       String id=instrument.getItemId(instrument.getDataSrcFromStation(actInfo[1]));

          cmd="performcommand wsn.WSN cmd "+instrument.getDataSrcFromStation(actInfo[1])+" all "+actInfo[10].equalsIgnoreCase("Byte data")+" "+
                  (actInfo.length>12 && actInfo[12]!=null && actInfo[12].equalsIgnoreCase("Y"))+" "+actInfo[14].equalsIgnoreCase("Y")+" "+(actInfo[15].length()>0 && instrument.isNumeric(actInfo[15])? actInfo[15]:"10000.0")+" "+cmd+" "+instrument.wn.w.e642((actInfo.length>13? actInfo[13]:""))+" 0 0 0 0 0"; 
          instrument.wn.w.sendToOne(cmd,id);

}

   public void setData(long time,String dataSrc,String data){

    CIDataClass dataClass=new CIDataClass(time,dataSrc,data);
    waitV.add(dataClass);
    if(isSleep) this.interrupt();
}
}