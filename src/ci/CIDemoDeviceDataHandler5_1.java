
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
public class CIDemoDeviceDataHandler5_1 implements wsn.WSNSocketDataHandler{
  int deviceN=4;
  TreeMap tm=new TreeMap();

  SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
  int inx=0,idInx=0;
  String date=format.format(new Date()),id[]={"K1_235","K1_236","K1_237","K1_238"},coordinatorId="COOR01",dummyId[]={"","","DUMMY",""};
  public CIDemoDeviceDataHandler5_1(){

  }
  public void setData(byte byteData[],WSNSocketDevice device){
        String dataHexStr=byteToStr(byteData);
        String strData=new String(byteData);
        String data[]=ylib.csvlinetoarray(strData);
        if(data.length>0 && data[0].equals("getmasterid")){
            double values[]=getValues();
            String feedback="feedbackmasterid,"+coordinatorId+",,";
            device.sendCommand_notThroughUI(feedback.getBytes());
        } else if(data.length>0 && data[0].equals("getdeviceid")){
            for(int i=0;i<id.length;i++){
              String feedback="feedbackdeviceid,"+coordinatorId+","+id[i]+","+dummyId[i]+",,";
              device.sendCommand_notThroughUI(feedback.getBytes());
              try{Thread.sleep(100L);} catch(InterruptedException e){}
            }
            String feedback2="feedbackdeviceid_end,"+coordinatorId+",,,";
            device.sendCommand_notThroughUI(feedback2.getBytes());
        } else if(data.length>1 && data[0].equals("getdevicedata")){
            String sn=data[1];
            idInx=-1;
            for(int i=0;i<id.length;i++){
                if(sn.equals(id[i])) {idInx=i; break;}
            }
            if(idInx==-1) return;
            double values[]=getValues();
            String feedback="feedbackdevicedata,"+format.format(new Date())+","+sn+","+values[0]+","+values[1]+","+values[2]+","+coordinatorId+","+dummyId[idInx]+",";
            device.sendCommand_notThroughUI(feedback.getBytes());
        } else if(data.length>0 && data[0].equals("getstationdata")){
            StringBuffer sb=new StringBuffer("feedbackstationdata,"+format.format(new Date()));
            for(int i=0;i<id.length;i++){
                double values[]=getValues();
                sb.append(","+id[i]+","+values[0]+","+values[1]+","+values[2]+","+coordinatorId+","+dummyId[idInx]+",");
            }
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