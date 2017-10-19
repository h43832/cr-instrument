
package ci;

import java.awt.Color;
import java.util.Date;
import java.util.Vector;
import wsn.WSNDataRecord;

public class CIShowDataThread extends Thread{
CrInstrument instrument;
  Vector waitV=new Vector();
  boolean isSleep=false;
  String shownData="",dataType="";
  long waitTime=1000L*60L*60L*24L,waitTime2=1L;
    public CIShowDataThread(CrInstrument instrument){
    this.instrument=instrument;
  }
  public void run(){
      while(true){
        while(waitV.size()>0){
          CIDataClass dataClass=(CIDataClass)waitV.get(0);
          String dataHex=dataClass.data,dataStr=instrument.wn.getStringData(dataHex,-1,-1,-1);
          String stringx[]=new String[7];
          int inx2=dataClass.dataSrc.indexOf("tcpclient");
          if(inx2>-1){
            int inx=dataClass.dataSrc.indexOf(":");
            stringx[1]=(inx>-1 && inx<inx2? dataClass.dataSrc.substring(0,inx):instrument.wn.w.getGNS(6));
            stringx[2]="4";
            stringx[3]=(inx>-1 && inx<inx2? dataClass.dataSrc.substring(inx+1):dataClass.dataSrc);
            stringx[6]=(inx>-1 && inx<inx2?  dataClass.dataSrc.substring(0,inx):instrument.wn.w.getGNS(6));
            inx=dataClass.dataSrc.indexOf("-");
            stringx[4]=(inx>-1? stringx[3].substring(inx+1):"1");
            stringx[3]=(inx>-1? stringx[3].substring(0,inx):stringx[3]);
          } else {
          int inx=dataClass.dataSrc.indexOf(":");
          stringx[1]=(inx>-1? dataClass.dataSrc.substring(0,inx):instrument.wn.w.getGNS(6));
          stringx[2]=(dataClass.dataSrc.indexOf("COM")>-1? "2":"1");
          stringx[3]=(inx>-1? dataClass.dataSrc.substring(inx+1):dataClass.dataSrc);
          stringx[6]=(inx>-1? dataClass.dataSrc.substring(0,inx):instrument.wn.w.getGNS(6));
          inx=dataClass.dataSrc.indexOf("-");
          stringx[4]=(inx>-1? stringx[3].substring(inx+1):"1");
          stringx[3]=(inx>-1? stringx[3].substring(0,inx):stringx[3]);
          }
          String fid=stringx[1];
          String dataType=stringx[2];
          String portName=stringx[3];
          String connectionId=stringx[4];
          long time=System.currentTimeMillis();
          String shownData="",timeStr=instrument.wn.formatter3.format(new Date(time));
      if(instrument.beginToReceive && instrument.receiveList.getSelectedValue()!=null && !dataType.equals("3")){
       if(instrument.getItemId((String)instrument.receiveList.getSelectedValue()).equals("0") || dataClass.dataSrc.equals((String)instrument.receiveList.getSelectedValue())){
          if(instrument.show16RB.isSelected()){
               shownData=dataHex;

            } else {
               shownData=dataStr;
               if(shownData.lastIndexOf("\r\n")!=-1 && shownData.lastIndexOf("\r\n")==(shownData.length()-2)) shownData=shownData.substring(0,shownData.length()-2);
               if(shownData.lastIndexOf("\n")!=-1 && shownData.lastIndexOf("\n")==(shownData.length()-1)) shownData=shownData.substring(0,shownData.length()-1);
             }
            if(!instrument.crnlCB.isSelected() && stringx[1].equals(instrument.lastDataFid) && stringx[2].equals(instrument.lastDataType) && stringx[3].equals(instrument.lastDataPortN) &&
                stringx[4].equals(instrument.lastDataConnectionId) && !instrument.beginTextPane && instrument.lastIsData){
                shownData=" "+shownData;
            } else {

                String tmp=(instrument.beginTextPane? "":"\r\n")+(instrument.showTimeCB.isSelected()? timeStr+" ":"")+(instrument.showSrcCB.isSelected()? fid+":"+portName+(portName.indexOf("COM")==-1?  "-"+stringx[6]:"")+" ":"");
                instrument.textPaneAppend(tmp,Color.BLACK,0);

            } 

           instrument.textPaneAppend(shownData,(dataType.equals("1")? instrument.wn.socketColor:instrument.wn.serialColor),0);

           instrument.lastDataFid=stringx[1];
           instrument.lastDataType=stringx[2];
          instrument. lastDataPortN=stringx[3];
           instrument.lastDataConnectionId=stringx[4];
           instrument.lastIsData=true;
           if(instrument.dSrcRecord.get(dataClass.dataSrc)!=null){
             WSNDataRecord dRecord=((WSNDataRecord) instrument.dSrcRecord.get(dataClass.dataSrc));
             if(dRecord.sb.length()+dataHex.length()> instrument.maxDSrcLogLength) dRecord.clear();
             dRecord.sb.append(timeStr).append(" ").append(dRecord.hexType? dataHex:dataStr).append("\r\n");
             instrument.dSrcRecord.put(dataClass.dataSrc,dRecord);
           } else {
             WSNDataRecord dRecord=new WSNDataRecord(instrument.show16RB.isSelected());
             dRecord.sb.append(timeStr).append(" ").append(dRecord.hexType? dataHex:dataStr).append("\r\n");
             instrument.dSrcRecord.put(dataClass.dataSrc,dRecord);
           }
           }
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
   public void setData(long time,String nodeId,String dataSrc,String data){

    CIDataClass dataClass=new CIDataClass(time,dataSrc,data);
    waitV.add(dataClass);
    if(isSleep) this.interrupt();
}
}