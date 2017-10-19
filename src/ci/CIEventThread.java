
package ci;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.Vector;
import y.ylib.ylib;

public class CIEventThread extends Thread{
CrInstrument instrument;
  Vector waitData=new Vector();
  Vector waitStatus=new Vector();
  boolean isSleep=false;
  long waitTime=1000L*60L*60L*24L,waitTime2=1L;
    public CIEventThread(CrInstrument instrument){
    this.instrument=instrument;
  }
  public void run(){
      while(true){
        while(waitData.size()>0 || waitStatus.size()>0){

          if(waitData.size()>0){
            CIDataClass dataClass=(CIDataClass)waitData.get(0);
            String dataHex=dataClass.data,dataStr=instrument.wn.getStringData(dataHex,-1,-1,-1);
            byte hexData[]=instrument.wn.getByteData(dataHex);
            Iterator it=instrument.eventTM.values().iterator();
            for(;it.hasNext();){
              String evt[]=ylib.csvlinetoarray((String)it.next());
              int cCnt=Integer.parseInt(evt[1]);
              int aCnt=Integer.parseInt(evt[2]);
              if(cCnt>0 && aCnt>0){
              boolean conditionOK=true;
              TreeMap actionCodeTM=new TreeMap();
              String dataX="";
              for(int i=0;i<cCnt;i++){
                String cId=evt[3+i];
                if(instrument.conditionTM.get(cId)!=null){
                  String cond[]=ylib.csvlinetoarray((String)instrument.conditionTM.get(cId));

                  if(cond[1].equalsIgnoreCase((String)instrument.ports.get(dataClass.dataSrc))){
                     if(cond.length>11 && cond[2].trim().equalsIgnoreCase("Data Condition") && cond[11].length()>0){

                       if(cond[3].trim().equalsIgnoreCase("Byte data")){
                         if(instrument.wn.w.chkValue(cond[7])){
                           int from=Integer.parseInt(cond[8]);
                           int to2=Integer.parseInt(cond[9]);
                           if(from<1) from=1;
                           if(from>hexData.length) from=hexData.length;
                           if(to2<1) to2=1;
                           if(to2>hexData.length) to2=hexData.length;
                           byte b2[]=new byte[to2 - from + 1];
                           for(int k=0,j=from-1;j<to2;k++,j++) b2[k]=hexData[j];

                             dataX=instrument.wn.byteToStr(b2);

                         } else dataX=dataHex;
                       } else if(cond[3].trim().equalsIgnoreCase("String data")){
                          if(cond[4].trim().equalsIgnoreCase("Whole line")){
                            dataX=dataStr;
                          } else {
                            String str[]={};
                            if(cond[4].trim().equalsIgnoreCase("Separated by space")){
                              str=dataStr.split(" ");
                            } else if(cond[4].trim().equalsIgnoreCase("Separated by ','")){
                              str=ylib.csvlinetoarray(dataStr);
                            } else if(cond[4].trim().equalsIgnoreCase("Fixed column length")){
                               int len=Integer.parseInt(cond[5]);
                               int cnt=dataStr.length()/len + (dataStr.length()%len >0 ? 1:0);
                               str=new String[cnt];
                               for(int j=0;j<cnt;j++){
                                 str[j]=dataStr.substring(j*len, (j*len+len >dataStr.length() ? dataStr.length():j*len+len));
                               }
                            }
                               int columnN=Integer.parseInt(cond[6]);
                               if(columnN<1) columnN=1;
                               if(columnN>str.length) columnN=str.length;
                               dataX=str[columnN-1];
                              if(cond[14].trim().equalsIgnoreCase("Y")){
                               if(cond[20].trim().equals(">") && str.length> instrument.wn.w.getValueInt(cond[16])) {}
                               else if(cond[20].trim().equals("=") && str.length== instrument.wn.w.getValueInt(cond[16])) {}
                               else if(cond[20].trim().equals("<") && str.length< instrument.wn.w.getValueInt(cond[16])) {}
                               else if(cond[20].trim().equals(">=") && str.length>= instrument.wn.w.getValueInt(cond[16])) {}
                               else if(cond[20].trim().equals("!=") && str.length!=instrument.wn.w.getValueInt(cond[16])) {}
                               else if(cond[20].trim().equals("<=") && str.length<= instrument.wn.w.getValueInt(cond[16])) {}
                               else conditionOK=false;
                              }
                          }
                           if(instrument.wn.w.chkValue(cond[7])){
                           int from=Integer.parseInt(cond[8]);
                           int to2=Integer.parseInt(cond[9]);
                           if(from<1) from=1;
                           if(from>dataX.length()) from=dataX.length();
                           if(to2<1) to2=1;
                           if(to2>dataX.length()) to2=dataX.length();
                           dataX=dataX.substring(from-1, to2);
                           }

                       }  else if(cond[3].trim().equalsIgnoreCase("Byte string data")){
                            dataX=ylib.replace(dataHex," ","");

                           if(instrument.wn.w.chkValue(cond[7])){
                           int from=Integer.parseInt(cond[8]);
                           int to2=Integer.parseInt(cond[9]);
                           if(from<1) from=1;
                           if(from>dataX.length()) from=dataX.length();
                           if(to2<1) to2=1;
                           if(to2>dataX.length()) to2=dataX.length();
                           dataX=dataX.substring(from-1, to2);
                           }

                       if(cond[12].trim().equalsIgnoreCase("Y")){
                         if(cond[10].trim().equals(">") && dataX.compareToIgnoreCase(cond[11])>0) {}
                         else if(cond[10].trim().equals("=") && dataX.compareToIgnoreCase(cond[11])>0) {}
                         else if(cond[10].trim().equals("<") && dataX.compareToIgnoreCase(cond[11])<0) {}
                         else if(cond[10].trim().equals(">=") && dataX.compareToIgnoreCase(cond[11])>=0) {}
                         else if(cond[10].trim().equals("!=") && dataX.compareToIgnoreCase(cond[11])!=0) {}
                         else if(cond[10].trim().equals("<=") &&  dataX.compareToIgnoreCase(cond[11])<=0) {}
                         else conditionOK=false;
                       }
                        if(cond[15].trim().equalsIgnoreCase("Y") && instrument.isNumeric(cond[17])){
                               if(cond[20].trim().equals(">") && dataX.length()> instrument.wn.w.getValueInt(cond[17])) {}
                               else if(cond[20].trim().equals("=") && dataX.length()== instrument.wn.w.getValueInt(cond[17])) {}
                               else if(cond[20].trim().equals("<") && dataX.length()< instrument.wn.w.getValueInt(cond[17])) {}
                               else if(cond[20].trim().equals(">=") && dataX.length()>= instrument.wn.w.getValueInt(cond[17])) {}
                               else if(cond[20].trim().equals("!=") && dataX.length()!=instrument.wn.w.getValueInt(cond[17])) {}
                               else if(cond[20].trim().equals("<=") && dataX.length()<= instrument.wn.w.getValueInt(cond[17])) {}
                               else conditionOK=false;
                              }
                       }
                     } else if(cond[2].trim().equalsIgnoreCase("Data checked by Java class")) {
                         if(((CIChkDataClass)instrument.jClasses.get(cond[13]))==null){
                           if(!instrument.loadClass(cond[13],3)) {instrument.sysLog("java check data class "+cond[13]+" not exist or not implements CIChkDataClass interface."); return;}
                         }
                         if(((CIChkDataClass)instrument.jClasses.get(cond[13]))!=null){
                           conditionOK=((CIChkDataClass)instrument.jClasses.get(cond[13])).chkData(dataHex);
                       }
                      }
                      else if(cond[2].trim().equalsIgnoreCase("Any data")) {
                          if(dataHex.length()<1) conditionOK=false;
                      } else conditionOK=false;

                  }
                  else conditionOK=false;
                }
                else conditionOK=false;
              }
              if(conditionOK && evt.length>= (3+cCnt+aCnt)){
                for(int i=0;i<aCnt;i++){
                  String aId=evt[3+cCnt+i];
                  if(aId.length()>0) actionCodeTM.put(aId, aId);
                }

                instrument.actionThread.setAction(actionCodeTM, dataClass);
              }
              }
            }
            waitData.remove(0);
          }
          if(waitStatus.size()>0){
            CIDataClass dataClass=(CIDataClass)waitStatus.get(0);
            int status=(int)dataClass.time;

            Iterator it=instrument.eventTM.values().iterator();
            for(;it.hasNext();){
              String evt[]=ylib.csvlinetoarray((String)it.next());
              int cCnt=Integer.parseInt(evt[1]);
              int aCnt=Integer.parseInt(evt[2]);
              boolean conditionOK=true;
              if(cCnt>0 && aCnt>0){
              TreeMap actionCodeTM=new TreeMap();
              String dataX="";
              for(int i=0;i<cCnt;i++){
                String cId=evt[3+i];
                if(instrument.conditionTM.get(cId)!=null){
                  String cond[]=ylib.csvlinetoarray((String)instrument.conditionTM.get(cId));
                   if(status==1 && cond[2].trim().equalsIgnoreCase("After connected") && cond[1].equalsIgnoreCase((String)instrument.ports.get(dataClass.dataSrc))){}
                   else if(status==2 && cond[2].trim().equalsIgnoreCase("After disconnected") && cond[1].equalsIgnoreCase((String)instrument.ports.get(dataClass.dataSrc))){}
                   else if(status==11 && cond[2].trim().equalsIgnoreCase("Click connect button")){}

                   else if(status==14 && cond[2].trim().equalsIgnoreCase("Click start button")){}

                   else if(status==16 && cond[2].trim().equalsIgnoreCase("Click stop button")){}

                   else if(status==19 && cond[2].trim().equalsIgnoreCase("Over upper take-action level")){}
                   else if(status==20 && cond[2].trim().equalsIgnoreCase("Under lower take-action level")){}
                   else if(status==21 && cond[2].trim().equalsIgnoreCase("Over upper alert level")){}
                   else if(status==22 && cond[2].trim().equalsIgnoreCase("Under lower alert level")){}
                   else if(status==25 && cond[2].trim().equalsIgnoreCase("Click button 01")){}
                   else if(status==26 && cond[2].trim().equalsIgnoreCase("Click button 02")){}
                   else if(status==27 && cond[2].trim().equalsIgnoreCase("Click button 03")){}
                   else if(status==28 && cond[2].trim().equalsIgnoreCase("Click button 04")){}
                   else if(status==29 && cond[2].trim().equalsIgnoreCase("Click button 05")){}
                   else if(status==30 && cond[2].trim().equalsIgnoreCase("Click button 06")){}
                   else if(status==31 && cond[2].trim().equalsIgnoreCase("Click button 07")){}
                   else if(status==32 && cond[2].trim().equalsIgnoreCase("Click button 08")){}
                   else if(status==33 && cond[2].trim().equalsIgnoreCase("Click button 09")){}
                   else if(status==34 && cond[2].trim().equalsIgnoreCase("Click button 10")){}
                   else if(status==35 && cond[2].trim().equalsIgnoreCase("Click file menuitem 01")){}
                   else if(status==36 && cond[2].trim().equalsIgnoreCase("Click file menuitem 02")){}
                   else if(status==37 && cond[2].trim().equalsIgnoreCase("Click file menuitem 03")){}
                   else if(status==38 && cond[2].trim().equalsIgnoreCase("Click file menuitem 04")){}
                   else if(status==39 && cond[2].trim().equalsIgnoreCase("Click file menuitem 05")){}
                   else if(status==40 && cond[2].trim().equalsIgnoreCase("Click tool menuitem 01")){}
                   else if(status==41 && cond[2].trim().equalsIgnoreCase("Click tool menuitem 02")){}
                   else if(status==42 && cond[2].trim().equalsIgnoreCase("Click tool menuitem 03")){}
                   else if(status==43 && cond[2].trim().equalsIgnoreCase("Click tool menuitem 04")){}
                   else if(status==44 && cond[2].trim().equalsIgnoreCase("Click tool menuitem 05")){}
                   else if(status==45 && cond[2].trim().equalsIgnoreCase("Click help menuitem 01")){}
                   else if(status==46 && cond[2].trim().equalsIgnoreCase("Click help menuitem 02")){}
                   else if(status==47 && cond[2].trim().equalsIgnoreCase("Click help menuitem 03")){}
                   else if(status==48 && cond[2].trim().equalsIgnoreCase("Click help menuitem 04")){}
                   else if(status==49 && cond[2].trim().equalsIgnoreCase("Click help menuitem 05")){}
                   else if(status==50 && cond[2].trim().equalsIgnoreCase("After system startup")){}
                   else if(status==51 && cond[2].trim().equalsIgnoreCase("Before system terminated")){}
                   else conditionOK=false;
                }
              }
              if(conditionOK && evt.length>= (3+cCnt+aCnt)){
                for(int i=0;i<aCnt;i++){
                  String aId=evt[3+cCnt+i];
                  if(aId.length()>0) actionCodeTM.put(aId, aId);
                }
                instrument.actionThread.setAction(actionCodeTM, dataClass);
              }
            }
            }
            waitStatus.remove(0);
          }
          }
          try{
              isSleep=true;
              sleep(waitTime);
          }catch(InterruptedException e){
             isSleep=false;
          }
      }
  }
   public void setData(long time,String nodeId,String dataSrc,String data){

    CIDataClass dataClass=new CIDataClass(time,dataSrc,data);
    waitData.add(dataClass);
    if(isSleep) this.interrupt();
}
   public void setStatus(String nodeId,String dataSrc,int statusCode){

    CIDataClass dataClass=new CIDataClass((long)statusCode,dataSrc,nodeId);
    waitStatus.add(dataClass);
    if(isSleep) this.interrupt();
}
}