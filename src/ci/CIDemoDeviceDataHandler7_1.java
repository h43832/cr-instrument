
package ci;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;
import wsn.WSNSocketDevice;
import y.ylib.ylib;

/**
 *
 * @author peter
 */
public class CIDemoDeviceDataHandler7_1 implements wsn.WSNSocketDataHandler{

  TreeMap tm=new TreeMap();

  SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
  int inx=0,idInx=0;
  String date=format.format(new Date()),id[]={"K1_235","K1_236","K1_237","K1_238"},coordinatorId="COOR01",dummyId[]={"","","DUMMY",""};
  public CIDemoDeviceDataHandler7_1(){

  }
  public void setData(byte byteData[],WSNSocketDevice device){
        String dataHexStr=byteToStr(byteData);
        String strData=new String(byteData);
        String data[]=ylib.csvlinetoarray(strData);
        if(data.length>0 && data[0].equals("getdata")){
            StringBuffer sb=new StringBuffer("feedbackdata,"+format.format(new Date()));
                double values[]=getValues();
                sb.append(","+values[0]+","+values[1]+","+values[2]);
            device.sendCommand_notThroughUI(sb.toString().getBytes());
        }
  }
  double[] getValues(){
        double temperature_value=25.0;
            double temperature_range=1.0;
            temperature_value=(temperature_value-temperature_range/2.0)+temperature_range* Math.random();
            temperature_value=((double)Math.round(temperature_value*100.0))/100.0;
            double co2_value=600.0;
            double co2_range=100.0;
            co2_value=(co2_value-co2_range/2.0)+co2_range* Math.random();
            co2_value=((double)Math.round(co2_value*10.0))/10.0;
            double humidity_value=60.0;
            double humidity_range=10.0;
            humidity_value=(humidity_value-humidity_range/2.0)+humidity_range* Math.random();
            humidity_value=((double)Math.round(humidity_value*10.0))/10.0;
            return new double[]{temperature_value,co2_value,humidity_value};
  }

 public String byteToStr(byte b[]){
    StringBuilder sb=new StringBuilder();
    boolean first=true;
    for (int i=0;i<b.length;i++) {
        if(first) first=false;
        else sb.append(" ");
	    int j=new Byte(b[i]).intValue();
	    String h=Integer.toHexString(j).toUpperCase();
	    switch(h.length()){
	      case 1:
	        h="0"+h;
	        break;
	      case 2:

	        break;
	      default:
	        h=h.substring(h.length()-2,h.length());
	        break;
	    }
	    sb.append(h);
	  }
    return sb.toString();
  }
}