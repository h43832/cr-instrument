
package ci;

import java.util.*;
import java.io.*;
public class CIFileThread extends Thread {
  CrInstrument instrument;
  Vector waitV=new Vector();
  boolean isSleep=false;
  long waitTime=1000L*60L*60L*24L,waitTime2=1L;
  public CIFileThread(CrInstrument instrument){
    this.instrument=instrument;
  }
  public void run(){
      while(true){
        while(waitV.size()>0){

          CIDataClass dataClass=(CIDataClass)waitV.get(0);
            if(dataClass.time==0){
              instrument.msgLog(dataClass.data);
            } else {
              instrument.dataLog(dataClass);
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