
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
            TreeMap sensorClone=(TreeMap)instrument.sensors.clone();

            Iterator it=actionDataClass.actionCodeTM.keySet().iterator();
            boolean sendEmail=false,sendSms=false;
            TreeMap newActionTM=new TreeMap();
            String SN="01";
            boolean foundSN=false;
            for(;it.hasNext();){
              String actionCode=(String)it.next();
             String act[]=ylib.csvlinetoarray((String)instrument.actionTM.get(actionCode));

            if(!foundSN){
            Iterator it2=sensorClone.keySet().iterator();
            for(;it2.hasNext();){
                String keyInfo[]=ylib.csvlinetoarray((String)it2.next());
                if(keyInfo[0].equalsIgnoreCase(act[1]) && keyInfo[1].equalsIgnoreCase(act[39]) && keyInfo[2].equalsIgnoreCase(act[16])){

                    SN=keyInfo[3];
                    break;
                }
            }
            }
            String key="";
            if(act[2].trim().equalsIgnoreCase("Set device SN")){
              String gottenMasterId="",gottenDeviceId="",gottenDummyId="",setMasterId="",setDeviceId="",setDummyId="";
              String column[]=new String[]{""};
              Iterator it2=sensorClone.keySet().iterator();
              for(;it2.hasNext();){
                String key2=(String)it2.next();
                String keyInfo[]=ylib.csvlinetoarray(key2);
                if(keyInfo[0].equalsIgnoreCase(act[1]) && keyInfo[1].equalsIgnoreCase(act[39]) && keyInfo[2].equalsIgnoreCase(act[16])){
                  String info[]=ylib.csvlinetoarray((String)sensorClone.get(key2));
                  setMasterId=info[10].trim();
                    break;
                }
              }
                if(act[43].trim().equalsIgnoreCase("Byte data")){
                    column[0]=dataHex;
                } else if(act[43].trim().equalsIgnoreCase("String data")){
                     if(act[44].trim().equalsIgnoreCase("Whole line")){
                            column[0]=dataStr;
                          } else {
                            if(act[44].trim().equalsIgnoreCase("Separated by space")){
                              column=dataStr.split(" ");
                            } else if(act[44].trim().equalsIgnoreCase("Separated by ','")){
                              column=ylib.csvlinetoarray(dataStr);
                            } else if(act[44].trim().equalsIgnoreCase("Fixed column length")){
                               int len=Integer.parseInt(act[55]);
                               int cnt=dataStr.length()/len + (dataStr.length()%len >0 ? 1:0);
                               column=new String[cnt];
                               for(int j=0;j<cnt;j++){
                                 column[j]=dataStr.substring(j*len, (j*len+len >dataStr.length() ? dataStr.length():j*len+len));
                               }
                            }
                          }
                }
              if(instrument.wn.w.chkValue(act[46])){
                  if(act[43].trim().equalsIgnoreCase("Byte data")){
                          if(instrument.wn.w.chkValue(act[48]) && instrument.isNumeric(act[49]) && instrument.isNumeric(act[50])){
                              int from=Integer.parseInt(act[49]);
                              int to=Integer.parseInt(act[50]);
                              if(from>0 && from<to && to<=column[0].length()) {
                                  from=3*(from-1);
                                  to=3*(to-1)+2;
                                  gottenMasterId=column[0].substring(from, to);
                              }
                          } else gottenMasterId=column[0];
                  } else {
                      int colN=(instrument.isNumeric(act[47])? Integer.parseInt(act[47]):1);
                      if(colN<1) colN=1;
                      if(column.length>=colN){
                          if(instrument.wn.w.chkValue(act[48]) && instrument.isNumeric(act[49]) && instrument.isNumeric(act[50])){
                              int from=Integer.parseInt(act[49]);
                              int to=Integer.parseInt(act[50]);
                              if(from>0 && from<to && to<=column[colN-1].length()) gottenMasterId=column[colN-1].substring(from-1, to);
                          } else gottenMasterId=column[colN-1];
                      }

                  }
                  if(gottenMasterId.length()>0){
                    boolean update=false;
                    if(setMasterId.length()>0){
                      if(setMasterId.equalsIgnoreCase(gottenMasterId)){
                        update=true;
                      }
                    } else {
                        update=true;
                     }
                   if(update){
                     instrument.portToCoors.put(instrument.wn.getPort(actionDataClass.dataClass.dataSrc), gottenMasterId);
                     instrument.coorToPorts.put(gottenMasterId, instrument.wn.getPort(actionDataClass.dataClass.dataSrc));
                     it2=sensorClone.keySet().iterator();
                     for(;it2.hasNext();){
                       String key2=(String)it2.next();
                       String keyInfo[]=ylib.csvlinetoarray(key2);
                       if(keyInfo[0].equalsIgnoreCase(act[1])){
                         String info[]=ylib.csvlinetoarray((String)sensorClone.get(key2));
                         info[11]=gottenMasterId;
                         String tmp=ylib.arrayToCsvLine(info);
                         sensorClone.put(key2,tmp);
                         instrument.sensors.put(key2,tmp);
                     }
                    }
                   }
                  }
              }
              if(instrument.wn.w.chkValue(act[55])){
                  if(act[43].trim().equalsIgnoreCase("Byte data")){
                          if(instrument.wn.w.chkValue(act[57]) && instrument.isNumeric(act[58]) && instrument.isNumeric(act[59])){
                              int from=Integer.parseInt(act[58]);
                              int to=Integer.parseInt(act[59]);
                              if(from>0 && from<to && to<=column[0].length()) {
                                  from=3*(from-1);
                                  to=3*(to-1)+2;
                                  gottenDeviceId=column[0].substring(from, to);
                              }
                          } else gottenDeviceId=column[0];
                  } else {
                      int colN=(instrument.isNumeric(act[56])? Integer.parseInt(act[56]):1);
                      if(colN<1) colN=1;
                      if(column.length>=colN){
                          if(instrument.wn.w.chkValue(act[57]) && instrument.isNumeric(act[58]) && instrument.isNumeric(act[59])){
                              int from=Integer.parseInt(act[58]);
                              int to=Integer.parseInt(act[59]);
                              if(from>0 && from<to && to<=column[colN-1].length()) gottenDeviceId=column[colN-1].substring(from-1, to);
                          } else gottenDeviceId=column[colN-1];
                      }
                  }
                  if(gottenDeviceId.length()>0){
                    it2=sensorClone.keySet().iterator();
                    for(;it2.hasNext();){
                      String key2=(String)it2.next();
                      String keyInfo[]=ylib.csvlinetoarray(key2);
                      if(keyInfo[0].equalsIgnoreCase(act[1]) && keyInfo[1].equalsIgnoreCase(act[39]) && keyInfo[2].equalsIgnoreCase(act[16])){
                        String info[]=ylib.csvlinetoarray((String)sensorClone.get(key2));
                        setDeviceId=info[15].trim();
                        if(setDeviceId.length()>0 && setDeviceId.equalsIgnoreCase(gottenDeviceId)){
                           foundSN=true;
                           SN=info[3];
                           if(info[11].trim().length()>0){
                              TreeMap tm=(TreeMap)instrument.deviceKeyDevices.get(keyInfo[0]+","+keyInfo[1]+","+keyInfo[2]);
                              if(tm==null) tm=new TreeMap();
                              tm.put(SN,gottenDeviceId);
                              instrument.deviceKeyDevices.put(keyInfo[0]+","+keyInfo[1]+","+keyInfo[2], tm);
                           }
                            String key3=keyInfo[0]+","+keyInfo[1]+","+keyInfo[2]+","+SN;
                            instrument.deviceToKeys.put(gottenDeviceId,key3);
                            instrument.keyToDevices.put(key3,gottenDeviceId);
                           if(!info[23].equalsIgnoreCase(gottenDeviceId)){
                             info[23]=gottenDeviceId;
                             String tmp=ylib.arrayToCsvLine(info);
                             sensorClone.put(key2,tmp);
                             instrument.sensors.put(key2,tmp);
                           }
                        }
                      }
                    }
                   if(!foundSN){
                     it2=sensorClone.keySet().iterator();
                     for(;it2.hasNext();){
                       String key2=(String)it2.next();
                       String keyInfo[]=ylib.csvlinetoarray(key2);
                       if(keyInfo[0].equalsIgnoreCase(act[1]) && keyInfo[1].equalsIgnoreCase(act[39]) && keyInfo[2].equalsIgnoreCase(act[16])){
                         String info[]=ylib.csvlinetoarray((String)sensorClone.get(key2));
                         if(info[23].trim().length()>0 && info[23].trim().equalsIgnoreCase(gottenDeviceId)) {
                             foundSN=true;
                             SN=info[3];
                             break;
                         }
                     }
                    }
                   }
                   if(!foundSN){
                     it2=sensorClone.keySet().iterator();
                     for(;it2.hasNext();){
                       String key2=(String)it2.next();
                       String keyInfo[]=ylib.csvlinetoarray(key2);
                       if(keyInfo[0].equalsIgnoreCase(act[1]) && keyInfo[1].equalsIgnoreCase(act[39]) && keyInfo[2].equalsIgnoreCase(act[16])){
                         String info[]=ylib.csvlinetoarray((String)sensorClone.get(key2));
                         if(info[23].trim().length()==0) {
                             foundSN=true;
                             SN=info[3];
                             info[23]=gottenDeviceId;
                             String tmp=ylib.arrayToCsvLine(info);
                             sensorClone.put(key2,tmp);
                             instrument.sensors.put(key2,tmp);
                            if(info[11].trim().length()>0){
                              TreeMap tm=(TreeMap)instrument.deviceKeyDevices.get(info[11]);
                              if(tm==null) tm=new TreeMap();
                              tm.put(SN,gottenDeviceId);
                              instrument.deviceKeyDevices.put(info[11], tm);
                           }
                            String key3=keyInfo[0]+","+keyInfo[1]+","+keyInfo[2]+","+SN;
                            instrument.deviceToKeys.put(gottenDeviceId,key3);
                            instrument.keyToDevices.put(key3,gottenDeviceId);
                             break;
                         }
                     }
                    }
                    if(foundSN==true){
                    it2=sensorClone.keySet().iterator();
                     for(;it2.hasNext();){
                       String key2=(String)it2.next();
                       String keyInfo[]=ylib.csvlinetoarray(key2);
                       if(keyInfo[0].equalsIgnoreCase(act[1]) && keyInfo[1].equalsIgnoreCase(act[39]) && keyInfo[2].equalsIgnoreCase(act[16])){
                         String info[]=ylib.csvlinetoarray((String)sensorClone.get(key2));
                         if(info[23].trim().length()==0 &&  info[3].equals(SN)) {
                             info[23]=gottenDeviceId;
                             String tmp=ylib.arrayToCsvLine(info);
                             sensorClone.put(key2,tmp);
                             instrument.sensors.put(key2,tmp);
                         }
                     }
                    }
                   }
                   }
                  }
                  if(!foundSN) SN="99";

              }
              if(instrument.wn.w.chkValue(act[65])){
                  if(act[43].trim().equalsIgnoreCase("Byte data")){
                          if(instrument.wn.w.chkValue(act[67]) && instrument.isNumeric(act[68]) && instrument.isNumeric(act[69])){
                              int from=Integer.parseInt(act[68]);
                              int to=Integer.parseInt(act[69]);
                              if(from>0 && from<to && to<=column[0].length()) {
                                  from=3*(from-1);
                                  to=3*(to-1)+2;
                                  gottenDeviceId=column[0].substring(from, to);
                              }
                          } else gottenDeviceId=column[0];
                  } else {
                      int colN=(instrument.isNumeric(act[66])? Integer.parseInt(act[66]):1);
                      if(colN<1) colN=1;
                      if(column.length>=colN){
                          if(instrument.wn.w.chkValue(act[67]) && instrument.isNumeric(act[68]) && instrument.isNumeric(act[69])){
                              int from=Integer.parseInt(act[68]);
                              int to=Integer.parseInt(act[69]);
                              if(from>0 && from<to && to<=column[colN-1].length()) gottenDeviceId=column[colN-1].substring(from-1, to);
                          } else gottenDeviceId=column[colN-1];
                      }
                  }

                    it2=sensorClone.keySet().iterator();
                    for(;it2.hasNext();){
                      String key2=(String)it2.next();
                      String keyInfo[]=ylib.csvlinetoarray(key2);
                      if(keyInfo[0].equalsIgnoreCase(act[1]) && keyInfo[1].equalsIgnoreCase(act[39]) && keyInfo[2].equalsIgnoreCase(act[16])){
                        String info[]=ylib.csvlinetoarray((String)sensorClone.get(key2));
                        setDummyId=info[26].trim();
                        if(SN.equals(info[3])){
                         if(gottenDummyId.length()>0){
                          if(setDummyId.length()>0){
                           if(setDummyId.equalsIgnoreCase(gottenDummyId)){
                           if(!info[27].equalsIgnoreCase(gottenDummyId) || !info[5].equals("0")){
                             info[27]=gottenDummyId;
                             info[25]="0";
                             String tmp=ylib.arrayToCsvLine(info);
                             sensorClone.put(key2,tmp);
                             instrument.sensors.put(key2,tmp);
                           }
                           } else{
                           if(!info[27].equalsIgnoreCase(gottenDummyId) || !info[5].equals("1")){
                             info[27]=gottenDummyId;
                             info[25]="1";
                             String tmp=ylib.arrayToCsvLine(info);
                             sensorClone.put(key2,tmp);
                             instrument.sensors.put(key2,tmp);
                           }
                           }
                          } else {
                           if(!info[27].equalsIgnoreCase(gottenDummyId) || !info[5].equals("1")){
                             info[27]=gottenDummyId;
                             info[25]="1";
                             String tmp=ylib.arrayToCsvLine(info);
                             sensorClone.put(key2,tmp);
                             instrument.sensors.put(key2,tmp);
                           }
                          }
                         }
                         else {
                           if(info[27].length()>0 || !info[5].equals("1")){
                             info[27]=gottenDummyId;
                             info[25]="1";
                             String tmp=ylib.arrayToCsvLine(info);
                             sensorClone.put(key2,tmp);
                             instrument.sensors.put(key2,tmp);
                           }
                         }
                        }
                      }
                    }

              }
            }
            else if(act[2].trim().equalsIgnoreCase("Set data value")){
               String dataX="";
               double dataValue=0.0;
               boolean alertSound=false,actionSound=false;
               byte b2[];
                key=act[1]+","+act[39]+","+act[16]+","+SN+","+act[17];
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

               if(sensorClone.get(key)!=null){
                 String info[]=ylib.csvlinetoarray((String)sensorClone.get(key));

                 double vA0=0.0;
                 if(instrument.isNumeric(info[12])) vA0=Double.parseDouble(info[12]);
                 if(vA0> 99999999.0 && vA0<100000001.0) {
                     vA0=dataValue;
                     info[12]=String.valueOf(dataValue);
                 }
                 if(act[31].equalsIgnoreCase("Y")) dataValue=dataValue - vA0;

                 if(act.length>38 && act[38].equalsIgnoreCase("Y")){
                  if(((CITransferClass)instrument.jClasses.get(act[33]))==null){
                    if(!instrument.loadClass(act[33],1)) {instrument.sysLog("java transfer class "+act[33]+" not exist or not implements CITransferClass interface."); return;}
                  }
                  if(((CITransferClass)instrument.jClasses.get(act[33]))!=null){
                    dataValue=((CITransferClass)instrument.jClasses.get(act[33])).getValue(dataValue);
                  }
                }
                if(act[19].equalsIgnoreCase("Y")){
                  double x2=0.0,x1=0.0,aV=0.0;
                  if(instrument.isNumeric(act[20])) x2=Double.parseDouble(info[20]);
                  if(instrument.isNumeric(act[21])) x1=Double.parseDouble(info[21]);
                  if(instrument.isNumeric(act[22])) aV=Double.parseDouble(info[22]);
                  dataValue= x2 * dataValue + x1 * dataValue + aV;
                }
                if(dataValue<0 && !act[26].equalsIgnoreCase("Y")){

                }

                TreeMap dataTM=null;
                if(instrument.allDatum.get(key)==null){
                  dataTM=new TreeMap();
                } else dataTM=(TreeMap) instrument.allDatum.get(key);
                dataTM.put(actionDataClass.dataClass.time, dataValue);
                instrument.allDatum.put(key, dataTM);

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
                   if(instrument.chkProps("alert-sound-on")) {
                     newActionTM.put(getActionCode(key,"Alert sound alarm"),"");
                     alertSound=true;
                   } 
                   if(instrument.chkProps("action-sound-on")) {
                     newActionTM.put(getActionCode(key,"Action sound alarm"),"");
                     actionSound=true;
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
                   if(instrument.chkProps("alert-sound-on")) {
                     newActionTM.put(getActionCode(key,"Alert sound alarm"),"");
                     alertSound=true;
                   } 
                   if(instrument.chkProps("action-sound-on")) {
                     newActionTM.put(getActionCode(key,"Action sound alarm"),"");
                     actionSound=true;
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
                   if(instrument.chkProps("alert-sound-on")) {
                     newActionTM.put(getActionCode(key,"Alert sound alarm"),"");
                     alertSound=true;
                   } 
                   if(instrument.chkProps("action-sound-on")) {
                     newActionTM.put(getActionCode(key,"Action sound alarm"),"");
                     actionSound=true;
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

                   if(instrument.chkProps("alert-sound-on")) {
                     newActionTM.put(getActionCode(key,"Alert sound alarm"),"");
                     alertSound=true;
                   } 
                   if(instrument.chkProps("action-sound-on")) {
                     newActionTM.put(getActionCode(key,"Action sound alarm"),"");
                     actionSound=true;
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
                 String tmp=ylib.arrayToCsvLine(info);
                 sensorClone.put(key,tmp);
                 instrument.sensors.put(key,tmp);
                 instrument.lastDataTime=time;
                 instrument.allConfigTM.put(key, instrument.getConfig(key));
                 instrument.allStatusTM.put(key, instrument.getStatus(key));
                 instrument.dataUpdated=true;
               }

            }

            else if(act[2].trim().equalsIgnoreCase("Send email message")){
              if(instrument.emailMsg.toString().length()>0){
              if(instrument.chkEmailTime()){
              instrument.waitThread.setWork(1, instrument.emailMsg.toString());
              } else {
                  instrument.sysLog("email inform (not sent, for time interval reason): " + instrument.emailMsg.toString());
              }
              instrument.emailMsg.delete(0,instrument.emailMsg.length());
              instrument.firstEmailMsg = true;
              } else {
                  instrument.waitThread.setWork(1, "Email empty data, using action function.");
              }
            }
            else if(act[2].trim().equalsIgnoreCase("Send SMS message to cell phone")){
              if(instrument.smsMsg.toString().length()>0){
              if(instrument.chkSmsTime()){
              instrument.waitThread.setWork(2, instrument.smsMsg.toString());
              } else {
                  instrument.sysLog("sms inform (not sent, for time interval reason): " + instrument.smsMsg.toString());
              }
              instrument.smsMsg.delete(0,instrument.smsMsg.length());
              instrument.firstSmsMsg = true;
              } else {
                  instrument.waitThread.setWork(2, "SMS empty data, using action function.");
              }
            }
            else if(act[2].trim().equalsIgnoreCase("Send command")){
              if(act[11].toUpperCase().indexOf("[#MASTERID#]")!=-1 || act[11].toUpperCase().indexOf("[#DEVICEID#]")!=-1){
                key=act[1]+","+act[39]+","+act[16]+","+SN+","+act[17];
                if(instrument.sensors.get(key)!=null){
                String info[]=ylib.csvlinetoarray((String)instrument.sensors.get(key));

                act[27]=info[11];
                act[28]=info[23];
                act[30]=SN;
                instrument.actionTM.put(act[0],ylib.arrayToCsvLine(act));
                }
              }
              instrument.miscThread.setData(5,act[0],actionDataClass.dataClass.data);
            }
            else if(act[2].trim().equalsIgnoreCase("Next device send command")){

              key=act[1]+","+act[39]+","+act[16]+","+SN+","+act[17];
              if(instrument.sensors.get(key)!=null){
              String info[]=ylib.csvlinetoarray((String)instrument.sensors.get(key));

              act[30]=SN;
              } else act[30]=SN;
              instrument.actionTM.put(act[0],ylib.arrayToCsvLine(act));
              instrument.miscThread.setData(8,act[0],actionDataClass.dataClass.data);

            }
            else if(act[2].trim().equalsIgnoreCase("Next Station send command")){

              key=act[1]+","+act[39]+","+act[16]+","+SN+","+act[17];
              if(instrument.sensors.get(key)!=null){
              String info[]=ylib.csvlinetoarray((String)instrument.sensors.get(key));

              act[30]=SN;
              instrument.actionTM.put(act[0],ylib.arrayToCsvLine(act));
              } else act[30]=SN;

              instrument.miscThread.setData(9,act[0],actionDataClass.dataClass.data);
            }
            else if(act[2].trim().equalsIgnoreCase("Stop continue send command")){
              instrument.miscThread.setData(12,act[0],actionDataClass.dataClass.data);
            }
            else if(act[2].trim().equalsIgnoreCase("Connect all port")){
              instrument.miscThread.setData(10,act[0],actionDataClass.dataClass.data);
            }
            else if(act[2].trim().equalsIgnoreCase("Disconnect all port")){
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
            } else if(act[2].trim().equalsIgnoreCase("Alert sound alarm")){
                instrument.soundThread.setAction("2,"+instrument.getPropsString("alert-sound-times")+","+instrument.getPropsString("alert-sound-file")+","+instrument.getPropsString("alert-sound-interval")+",1");
            } else if(act[2].trim().equalsIgnoreCase("Action sound alarm")){
                instrument.soundThread.setAction("2,"+instrument.getPropsString("action-sound-times")+","+instrument.getPropsString("action-sound-file")+","+instrument.getPropsString("action-sound-interval")+",1");
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