
package ci;

import static ci.CrInstrument.isNumeric;
import static java.lang.Thread.sleep;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;
import wsn.WSN;
import y.ylib.ylib;

/**
 *
 * @author peter
 */
public class CIActionThread extends Thread {
CrInstrument instrument;
  Vector waitV=new Vector();
  boolean isSleep=false;
  long waitTime=1000L*60L*60L*24L,waitTime2=1L;
    public CIActionThread(CrInstrument instrument){
    this.instrument=instrument;
  }
  public void run(){
      while(true){
        while(waitV.size()>0){

            CIActionDataClass actionDataClass=(CIActionDataClass)waitV.get(0);
            long time=actionDataClass.dataClass.time;
             String dataHex=null,dataStr=null;
             byte hexData[]=null;
            if(time>1000000){
               dataHex=actionDataClass.dataClass.data;
               dataStr=instrument.wn.getStringData(dataHex,-1,-1,-1);
               hexData=instrument.wn.getByteData(dataHex);
            } else {

            }
            Iterator it=actionDataClass.actionCodeTM.keySet().iterator();
            String SN="01",key="";
            boolean sendEmail=false,sendSms=false;
            TreeMap newActionTM=new TreeMap();
            for(;it.hasNext();){
              String actionCode=(String)it.next();
             String act[]=ylib.csvlinetoarray((String)instrument.actionTM.get(actionCode));
            if(act[2].trim().equalsIgnoreCase("Set device SN")){

            }
            else if(act[2].trim().equalsIgnoreCase("Set data value")){
               String dataX="";
               double dataValue=0.0;
               byte b2[];
                key=act[1]+","+act[39]+","+act[16]+","+SN+","+act[17];
               if(instrument.wn.w.chkValue(act[28])){}
               else SN="01";
                       if(act[3].trim().equalsIgnoreCase("Byte data")){
                         if(instrument.wn.w.chkValue(act[7])){
                           int from=Integer.parseInt(act[8]);
                           int to2=Integer.parseInt(act[9]);
                           if(from<1) from=1;
                           if(from>hexData.length) from=hexData.length;
                           if(to2<1) to2=1;
                           if(to2>hexData.length) to2=hexData.length;
                           b2=new byte[to2 - from + 1];
                           for(int k=0,j=from-1;j<to2;k++,j++) b2[k]=hexData[j];
                         } else {
                           b2=instrument.wn.getByteData(dataHex);
                           dataX=dataHex;
                         }
                         if(instrument.wn.w.chkValue(act[34])) dataValue=(double)instrument.wn.getIEEE754Float(b2);
                           else{
                             dataX=instrument.wn.byteToStr(b2);
                             dataX=ylib.replace(dataX," ","");
                             if(wsn.WSN.isHexNumber(dataX)) dataValue=(double)Long.parseLong(dataX,16); else dataValue=0.0;
                           }
                       } else if(act[3].trim().equalsIgnoreCase("String data")){
                          if(act[4].trim().equalsIgnoreCase("Whole line")){
                            dataX=dataStr;
                          } else {
                            String str[]={};
                            if(act[4].trim().equalsIgnoreCase("Separated by space")){
                              str=dataStr.split(" ");
                            } else if(act[4].trim().equalsIgnoreCase("Separated by ','")){
                              str=ylib.csvlinetoarray(dataStr);
                            } else if(act[4].trim().equalsIgnoreCase("Fixed column length")){
                               int len=Integer.parseInt(act[5]);
                               int cnt=dataStr.length()/len + (dataStr.length()%len >0 ? 1:0);
                               str=new String[cnt];
                               for(int j=0;j<cnt;j++){
                                 str[j]=dataStr.substring(j*len, (j*len+len >dataStr.length() ? dataStr.length():j*len+len));
                               }
                            }
                               int columnN=Integer.parseInt(act[6]);
                               if(columnN<1) columnN=1;
                               if(columnN<str.length+1) dataX=str[columnN-1];
                          }
                           if(instrument.wn.w.chkValue(act[7])){
                           int from=Integer.parseInt(act[8]);
                           int to2=Integer.parseInt(act[9]);
                           if(from<1) from=1;
                           if(from>dataX.length()) from=dataX.length();
                           if(to2<1) to2=1;
                           if(to2>dataX.length()) to2=dataX.length();
                           dataX=dataX.substring(from-1, to2);
                           }
                           if(WSN.isNumeric(dataX)) dataValue=Double.parseDouble(dataX);
                       } 
                double rawData=dataValue;
                if(act.length>38 && act[38].equalsIgnoreCase("Y")){
                  if(((CITransferClass)instrument.jClasses.get(act[33]))==null){
                    if(!instrument.loadClass(act[33],1)) {instrument.sysLog("java transfer class "+act[33]+" not exist or not implements CITransferClass interface."); return;}
                  }
                  if(((CITransferClass)instrument.jClasses.get(act[33]))!=null){
                    dataValue=((CITransferClass)instrument.jClasses.get(act[33])).getValue(dataValue);
                  }
                }
                if(dataValue<0 && !act[26].equalsIgnoreCase("Y")){
                   dataValue=Math.abs(dataValue);
                }
                TreeMap dataTM=null;
               if(instrument.allDatum.get(key)==null){
                 dataTM=new TreeMap();
               } else dataTM=(TreeMap) instrument.allDatum.get(key);
               dataTM.put(actionDataClass.dataClass.time, dataValue);
               instrument.allDatum.put(key, dataTM);
               if(instrument.sensors.get(key)!=null){
                 String info[]=ylib.csvlinetoarray((String)instrument.sensors.get(key));

                 double d5 = 1000000.0, d6 = -1000000.0, d7 = 1000000.0, d8 = -1000000.0;
                 if (isNumeric(info[5])) d5 = Double.parseDouble(info[5]);
                 else d5=dataValue+1.0;
                 if (isNumeric(info[6])) d6 = Double.parseDouble(info[6]);
                 else d6=dataValue-1.0;
                 if (isNumeric(info[7])) d7 = Double.parseDouble(info[7]);
                 else d7=dataValue+1.0;
                 if (isNumeric(info[8])) d8 = Double.parseDouble(info[8]);
                 else d8=dataValue-1.0;
                 boolean sendMail=false,sendSMS=false;
                 if (dataValue > d7){
                   if(instrument.chkProps("up-action-email")) {
                     newActionTM.put(getActionCode(key,"Send email message"),"");
                     sendMail=true;
                   } 
                   if(instrument.chkProps("up-action-sms")) {
                     newActionTM.put(getActionCode(key,"Send SMS message to cell phone"),"");
                     sendSMS=true;
                   } 
                   String actions[]=getActions(key,"Over upper take-action level");
                       if(actions.length>0) {
                         for(int i=0;i<actions.length;i++) {
                           newActionTM.put(actions[i],"");
                         if(instrument.actionTM.get(actions[i])!=null){
                           String actInfo[]=ylib.csvlinetoarray((String)instrument.actionTM.get(actions[i]));
                           if(actInfo.length>2 && actInfo[2].equalsIgnoreCase("Send email message")) sendMail=true;
                           if(actInfo.length>2 && actInfo[2].equalsIgnoreCase("Send SMS message to cell phone")) sendSMS=true;
                         }
                         }
                       }
                   if(sendMail){
                     instrument.emailMsg.append((instrument.firstEmailMsg ? "" : "\r\n") + ylib.replace(key, ",", "-") + " value "+dataValue+" exceeds the take-action level : " + dataValue + (dataValue > d7 ? " > " + d7 : " < " + d8));
                     instrument.firstEmailMsg = false;
                   }
                   if(sendSMS){
                     instrument.smsMsg.append((instrument.firstEmailMsg ? "" : "\r\n") + ylib.replace(key, ",", "-") + " value "+dataValue+" exceeds the take-action level : " + dataValue + (dataValue > d7 ? " > " + d7 : " < " + d8));
                     instrument.firstSmsMsg = false;
                   }
                 }
                 else if(dataValue < d8) {
                   if(instrument.chkProps("down-action-email")) {
                     newActionTM.put(getActionCode(key,"Send email message"),"");
                     sendMail=true;
                   }
                   if(instrument.chkProps("down-action-sms")) {
                     newActionTM.put(getActionCode(key,"Send SMS message to cell phone"),"");
                     sendSMS=true;
                   } 
                   String actions[]=getActions(key,"Under lower take-action level");
                       if(actions.length>0) {
                         for(int i=0;i<actions.length;i++) {
                           newActionTM.put(actions[i],"");
                         if(instrument.actionTM.get(actions[i])!=null){
                           String actInfo[]=ylib.csvlinetoarray((String)instrument.actionTM.get(actions[i]));
                           if(actInfo.length>2 && actInfo[2].equalsIgnoreCase("Send email message")) sendMail=true;
                           if(actInfo.length>2 && actInfo[2].equalsIgnoreCase("Send SMS message to cell phone")) sendSMS=true;
                         }
                         }
                       }
                   if(sendMail){
                     instrument.emailMsg.append((instrument.firstEmailMsg ? "" : "\r\n") + ylib.replace(key, ",", "-")  + " value "+dataValue+" exceeds the take-action level : " + dataValue + (dataValue > d7 ? " > " + d7 : " < " + d8));
                     instrument.firstEmailMsg = false;
                   }
                   if(sendSMS){
                     instrument.smsMsg.append((instrument.firstEmailMsg ? "" : "\r\n") + ylib.replace(key, ",", "-")  + " value "+dataValue+" exceeds the take-action level : " +  dataValue + (dataValue > d7 ? " > " + d7 : " < " + d8));
                     instrument.firstSmsMsg = false;
                   }
                 }
                 else if (dataValue > d5){
                   if(instrument.chkProps("up-alarm-email")) {
                     newActionTM.put(getActionCode(key,"Send email message"),"");
                     sendMail=true;
                   } 
                   if(instrument.chkProps("up-alarm-sms")) {
                     newActionTM.put(getActionCode(key,"Send SMS message to cell phone"),"");
                     sendSMS=true;
                   } 
                   String actions[]=getActions(key,"Over upper alert level");
                       if(actions.length>0) {
                         for(int i=0;i<actions.length;i++) {
                           newActionTM.put(actions[i],"");
                         if(instrument.actionTM.get(actions[i])!=null){
                           String actInfo[]=ylib.csvlinetoarray((String)instrument.actionTM.get(actions[i]));
                           if(actInfo.length>2 && actInfo[2].equalsIgnoreCase("Send email message")) sendMail=true;
                           if(actInfo.length>2 && actInfo[2].equalsIgnoreCase("Send SMS message to cell phone")) sendSMS=true;
                         }
                         }
                       }
                   if(sendMail){
                     instrument.emailMsg.append((instrument.firstEmailMsg ? "" : "\r\n") + ylib.replace(key, ",", "-")  + " value "+dataValue+" exceeds the alert level : " +  dataValue + (dataValue > d5 ? " > " + d5 : " < " + d6));
                     instrument.firstEmailMsg = false;
                   }
                   if(sendSMS){
                     instrument.smsMsg.append((instrument.firstEmailMsg ? "" : "\r\n") + ylib.replace(key, ",", "-") + " value "+dataValue+" exceeds the alert level : " + dataValue + (dataValue > d5 ? " > " + d5 : " < " + d6));
                     instrument.firstSmsMsg = false;
                   }
                 } 
                 else if(dataValue < d6){
                   if(instrument.chkProps("down-alarm-email")) {
                     newActionTM.put(getActionCode(key,"Send email message"),"");
                     sendMail=true;
                   } 
                   if(instrument.chkProps("down-alarm-sms")) {
                     newActionTM.put(getActionCode(key,"Send SMS message to cell phone"),"");
                     sendSMS=true;
                   }                    
                    String actions[]=getActions(key,"Under lower alert level");
                       if(actions.length>0) {
                         for(int i=0;i<actions.length;i++) {
                           newActionTM.put(actions[i],"");
                         if(instrument.actionTM.get(actions[i])!=null){
                           String actInfo[]=ylib.csvlinetoarray((String)instrument.actionTM.get(actions[i]));
                           if(actInfo.length>2 && actInfo[2].equalsIgnoreCase("Send email message")) sendMail=true;
                           if(actInfo.length>2 && actInfo[2].equalsIgnoreCase("Send SMS message to cell phone")) sendSMS=true;
                         }
                         }
                       }
                   if(sendMail){
                     instrument.emailMsg.append((instrument.firstEmailMsg ? "" : "\r\n") + ylib.replace(key, ",", "-") + " value "+dataValue+" exceeds the alert level : " + dataValue + (dataValue > d5 ? " > " + d5 : " < " + d6));
                     instrument.firstEmailMsg = false;
                   }
                   if(sendSMS){
                     instrument.smsMsg.append((instrument.firstEmailMsg ? "" : "\r\n") + ylib.replace(key, ",", "-") + " value "+dataValue+" exceeds the alert level : " + dataValue + (dataValue > d5 ? " > " + d5 : " < " + d6));
                     instrument.firstSmsMsg = false;
                   }
                 }
                 int d14=0;
                 if (isNumeric(info[14])) {
                     d14 = Integer.parseInt(info[14]);
                 }
                 info[17]=""+rawData;
                 info[20]=instrument.getRound2(dataValue,d14);
                 info[22]=String.valueOf(time);
                 info[24]=String.valueOf(time);
                 instrument.sensors.put(key,ylib.arrayToCsvLine(info));
                 instrument.lastDataTime=time;
               }
               instrument.allConfigTM.put(key, instrument.getConfig(key));
               instrument.allStatusTM.put(key, instrument.getStatus(key));
               instrument.dataUpdated=true;
            }

            else if(act[2].trim().equalsIgnoreCase("Send email message")){
              if(instrument.chkEmailTime()){
              instrument.waitThread.setWork(1, instrument.emailMsg.toString());
              } else {
                  instrument.sysLog("email inform (not sent, for time interval reason): " + instrument.emailMsg.toString());
              }
              instrument.emailMsg.delete(0,instrument.emailMsg.length());
              instrument.firstEmailMsg = true;
            }
            else if(act[2].trim().equalsIgnoreCase("Send SMS message to cell phone")){
              if(instrument.chkSmsTime()){
              instrument.waitThread.setWork(2, instrument.smsMsg.toString());
              } else {
                  instrument.sysLog("sms inform (not sent, for time interval reason): " + instrument.smsMsg.toString());
              }
              instrument.smsMsg.delete(0,instrument.smsMsg.length());
              instrument.firstSmsMsg = true;
            }
            else if(act[2].trim().equalsIgnoreCase("Send command")){
              instrument.miscThread.setData(5,act[0],actionDataClass.dataClass.data);
            }
            else if(act[2].trim().equalsIgnoreCase("Connect port")){
              instrument.miscThread.setData(10,act[0],actionDataClass.dataClass.data);
            }
            else if(act[2].trim().equalsIgnoreCase("Disconnect port")){
              instrument.miscThread.setData(11,act[0],actionDataClass.dataClass.data);
            }
            else if(act[2].trim().equalsIgnoreCase("Start monitor")){
              instrument.miscThread.setData(6,act[0],actionDataClass.dataClass.data);
            }
            else if(act[2].trim().equalsIgnoreCase("Stop monitor")){
              instrument.miscThread.setData(7,act[0],actionDataClass.dataClass.data);
            }
            else if(act[2].trim().equalsIgnoreCase("Exit application")){
              instrument.setVisible(false);
            }
            else if(act[2].trim().equalsIgnoreCase("Restart application")){
              instrument.restart();
            }
            else if(act[2].trim().equalsIgnoreCase("Java class")){
              instrument.miscThread.setData(4,act[0],actionDataClass.dataClass.data);
            }
            else if(act[2].trim().equalsIgnoreCase("Open URL")){
              instrument.miscThread.setData(3,"dataSrc",act[70]);
            }
            }
            if(newActionTM.size()>0){
              CIActionDataClass dataClass=new CIActionDataClass(newActionTM,actionDataClass.dataClass);
              waitV.add(dataClass);
            }
            waitV.remove(0);
           }
           if(instrument.chkProps("recordwhenreceive") && instrument.lastDataTime>instrument.lastRecordTime){
              instrument.saveData3(instrument.lastDataTime,instrument.lastDataTime,5);
           }
          try{
              isSleep=true;
              sleep(waitTime);
          }catch(InterruptedException e){
             isSleep=false;
          }
      }
  }
  String [] getActions(String key,String cond){
    String rtn[]={};
    Iterator it=instrument.eventTM.values().iterator();
    boolean found=false;
    for(;it.hasNext();){
      String evn[]=ylib.csvlinetoarray((String)it.next());
      if(evn[1].trim().equals("1") && evn[3].equalsIgnoreCase(cond)){
          int an=Integer.parseInt(evn[2]);
          if(an>0 && evn.length> (3+an)){
            rtn=new String[an];
            for(int i=0;i<an;i++){
              rtn[i]=evn[4+i];
            }
        }
      }
    }
    return rtn;
  }

  String getActionCode(String key,String action){
    String rtn="";
    String keyInfo[]=ylib.csvlinetoarray(key);
    Iterator it=instrument.actionTM.values().iterator();
    for(;it.hasNext();){
      String act[]=ylib.csvlinetoarray((String)it.next());
      if(act[1].equalsIgnoreCase(keyInfo[0]) && act[39].equalsIgnoreCase(keyInfo[1])&& act[16].equalsIgnoreCase(keyInfo[2]) && act[17].equalsIgnoreCase(keyInfo[4]) && act[2].equalsIgnoreCase(action)) 
        return act[0];
    }
    rtn=instrument.getNewAction(key,action);
    return rtn;
  }

   public void setAction(TreeMap tm,CIDataClass dc){

    CIActionDataClass dataClass=new CIActionDataClass(tm,dc);
    waitV.add(dataClass);
    if(isSleep) this.interrupt();
}
}