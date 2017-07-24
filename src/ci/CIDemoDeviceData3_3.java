
package ci;

import java.text.SimpleDateFormat;
import java.util.Date;
import wsn.WSNDataGenerator;

public class CIDemoDeviceData3_3 implements WSNDataGenerator {
  SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
  int inx=0,idInx=0;
  String date=format.format(new Date()),id[]={"Y235","Y236","Y237"},coordinatorId="COOR04",dummyId[]={"","",""};
  public byte[] getData(){
    double te1=20.0,te2=60.0,te3=500.0;
    int value=180;
    String temp1=""+(te1+Math.random()*5.0);
    String temp2=""+(te2+Math.random()*10.0);
    String temp3=""+(te3+Math.random()*100.0);
    int inx=temp1.indexOf(".");
    if(inx>-1) temp1=temp1.substring(0, inx+2);
    else temp1=temp1+".0";
    inx=temp2.indexOf(".");
    if(inx>-1) temp2=temp2.substring(0,inx);
    inx=temp3.indexOf(".");
    if(inx>-1) temp3=temp3.substring(0, inx);
    String data=date+","+id[idInx]+","+temp1+","+temp2+","+temp3+","+coordinatorId+","+dummyId[idInx]+",";
    idInx++;
    if(idInx>2) idInx=0;
    return data.getBytes();
  }
}