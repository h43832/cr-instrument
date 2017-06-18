
package ci;

import java.util.Vector;

public class CIChartThread extends Thread{
CrInstrument instrument;
  Vector waitV=new Vector();
  boolean isSleep=false;
  long waitTime=1000L*60L*60L*24L,waitTime2=1L;
    public CIChartThread(CrInstrument instrument){
    this.instrument=instrument;
  }
  public void run(){
      while(true){
        while(waitV.size()>0){

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