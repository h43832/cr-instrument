
package ci;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.Vector;
import y.ylib.ylib;

public class CIMiscThread extends Thread{
CrInstrument instrument;
  Vector waitV=new Vector();
  boolean isSleep=false;
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
                       ((CIAction)instrument.jClasses.get(act[35])).startAction(instrument, dataClass.dataSrc,dataClass.data);
                     }
                   }
                   else if(miscCode==5){
                     String act[]=ylib.csvlinetoarray((String)instrument.actionTM.get(dataClass.dataSrc));
                     sendCmd(act);
                   }
                   else if(miscCode==6){
                     if (instrument.btnStart.getText().trim().equals("Start Record"))  instrument.pressStartBtn(2);
                   }
                   else if(miscCode==7){
                     if (!instrument.btnStart.getText().trim().equals("Start Record"))instrument.pressStartBtn(2);
                   }
                   else if(miscCode==8){

                   }
                   else if(miscCode==9){
                   }
                   else if(miscCode==10){
                     instrument.connect();
                   }
                   else if(miscCode==11){
                   }
                   else if(miscCode==12){
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
  void sendCmd(String actInfo[]){
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

  if(cmd.trim().length()<1) {
      instrument.sysLog("No commad or data to be send");
      return;
  }
  if(instrument.connected){
      byte [] b=null;
      int sendType=1;
      long interval=10L * 1000L;
      if(actInfo[14].equalsIgnoreCase("Y")) {
          sendType=2;
          if(instrument.wn.isNumeric(actInfo[15]) && Double.parseDouble(actInfo[15])>0) interval=(long)(Double.parseDouble(actInfo[15])*1000.0);
          else {
            instrument.sysLog("No time interval for \"continue send\".");
              return;
          }
          if(interval<100L) interval=100L;
      }
       String id=instrument.getItemId(instrument.getDataSrcFromStation(actInfo[1]));

          cmd="performcommand wsn.WSN cmd "+instrument.getDataSrcFromStation(actInfo[1])+" 1 "+actInfo[10].equals("1")+" "+
                  (actInfo.length>12 && actInfo[12]!=null && actInfo[12].equalsIgnoreCase("Y"))+" "+actInfo[14].equalsIgnoreCase("Y")+" "+interval+" "+cmd+" "+instrument.wn.w.e642((actInfo.length>13? actInfo[13]:""))+" 0 0 0 0 0"; 
          instrument.wn.w.sendToOne(cmd,id);

  } else {
      instrument.sysLog("Yet not connected! can't send command.");
  }
}

   public void setData(long time,String dataSrc,String data){

    CIDataClass dataClass=new CIDataClass(time,dataSrc,data);
    waitV.add(dataClass);
    if(isSleep) this.interrupt();
}
}