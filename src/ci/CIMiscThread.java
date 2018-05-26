
package ci;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.apache.commons.net.ftp.*;
import y.base64.YB16D;
import y.base64.YB642D;
import y.ende2003.d2003;
import y.ylib.OneVar;
import y.ylib.windowscommand2;
import y.ylib.ylib;

public class CIMiscThread extends Thread{
CrInstrument instrument;
  Vector waitV=new Vector();
  boolean isSleep=false;
  public ResourceBundle bundle2 = java.util.ResourceBundle.getBundle("ci/Bundle");
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
                       ((CIAction)instrument.jClasses.get(act[35])).startAction(instrument, dataClass.dataSrc,dataClass);
                     }
                   }
                   else if(miscCode==5){
                     String act[]=ylib.csvlinetoarray((String)instrument.actionTM.get(dataClass.dataSrc));
                     sendCmd(act,1);
                   }
                   else if(miscCode==6){
                     if (instrument.framePanel2.btnStart.getText().trim().equals(bundle2.getString("CrInstrument.xy.msg3")))  instrument.pressStartBtn(2);
                   }
                   else if(miscCode==7){
                     if (instrument.framePanel2.btnStart.getText().trim().equals(bundle2.getString("CrInstrument.xy.msg4")))instrument.pressStartBtn(2);
                   }
                   else if(miscCode==8){
                     String act[]=ylib.csvlinetoarray((String)instrument.actionTM.get(dataClass.dataSrc));
                     sendCmd(act,2);
                   }
                   else if(miscCode==9){
                     String act[]=ylib.csvlinetoarray((String)instrument.actionTM.get(dataClass.dataSrc));
                     sendCmd(act,3);
                   }
                   else if(miscCode==10){
                     instrument.connect();
                   }
                   else if(miscCode==11){
                   }
                   else if(miscCode==12){
                     if(instrument.connected){
                       String act[]=ylib.csvlinetoarray((String)instrument.actionTM.get(dataClass.dataSrc));
                       String id=instrument.getItemId(instrument.getDataSrcFromStation(act[1]));
                       String cmd="performcommand wsn.WSN stopcontinue "+instrument.getDataSrcFromStation(act[1])+" all null"; 
                       instrument.wn.w.sendToOne(cmd,id);
                     } else {
                          instrument.sysLog("Yet not connected! can't send command.");
                       }
                   }
                   else if(miscCode==13){
                       String act[]=ylib.csvlinetoarray((String)instrument.actionTM.get(dataClass.dataSrc));
                       String temp=act[4];
                       act[0]=act[18];
                       if(act[2].equalsIgnoreCase("FTP upload")) act[4]="put";
                       else if(act[2].equalsIgnoreCase("FTP download")) act[4]="get";
                       else if(act[2].equalsIgnoreCase("FTP delete")) act[4]="delete";
                       act[2]=temp;
                       if(act[7].trim().length()<1) act[7]="*";
                       if(act[9].trim().length()<1) act[9]="*";
                       if(act[17].equalsIgnoreCase("default") || act[17].trim().length()<1) act[17]="UNIX";
                       doFTP(ylib.arrayToCsvLine(act));
                   }else if(miscCode==14){
                       String act[]=ylib.csvlinetoarray((String)instrument.actionTM.get(dataClass.dataSrc));
                       String temp=act[4];
                       act[0]=act[18];
                       if(act[2].equalsIgnoreCase("FTP upload")) act[4]="put";
                       else if(act[2].equalsIgnoreCase("FTP download")) act[4]="get";
                       else if(act[2].equalsIgnoreCase("FTP delete")) act[4]="delete";
                       act[2]=temp;
                       if(act[7].trim().length()<1) act[7]="*";
                       if(act[9].trim().length()<1) act[9]="*";
                       if(act[17].equalsIgnoreCase("default") || act[17].trim().length()<1) act[17]="UNIX";
                       doFTP(ylib.arrayToCsvLine(act));
                   }else if(miscCode==15){
                       String act[]=ylib.csvlinetoarray((String)instrument.actionTM.get(dataClass.dataSrc));
                       String temp=act[4];
                       act[0]=act[18];
                       if(act[2].equalsIgnoreCase("FTP upload")) act[4]="put";
                       else if(act[2].equalsIgnoreCase("FTP download")) act[4]="get";
                       else if(act[2].equalsIgnoreCase("FTP delete")) act[4]="delete";
                       act[2]=temp;
                       if(act[17].equalsIgnoreCase("default")) act[17]="UNIX";
                       doFTP(ylib.arrayToCsvLine(act));
                   }else if(miscCode==16){
                       String act[]=ylib.csvlinetoarray((String)instrument.actionTM.get(dataClass.dataSrc));
                      if(act[63].trim().length()>0){
                         String cmd=instrument.actionThread.getTextValue(act[63],act[72],act[76]);
                        windowscommand2 wc2=new windowscommand2(cmd);
                        wc2.start();
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

  void sendCmd(String actInfo[],int type){
    if(instrument.getDataSrcFromStation(actInfo[1])==null) {
        instrument.log("Warning: cannot send command, because cannot get data source from station \""+actInfo[1]+"\".", true);
        return;
    }
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

  if(type==2){
     String nextInfo[]=instrument.getNextDevice(actInfo[1],actInfo[39],actInfo[16],actInfo[30]); 
     if(nextInfo==null) {instrument.sysLog(actInfo[1]+"-"+actInfo[39]+"-"+actInfo[16]+"-"+actInfo[30]+" has no next device."); return;}
     String key=nextInfo[0]+","+nextInfo[1]+","+nextInfo[2]+","+nextInfo[3]+","+nextInfo[4];
     String info[]=ylib.csvlinetoarray((String)instrument.sensors.get(key));  
     actInfo[1]=nextInfo[0];
     actInfo[27]=info[11];
     actInfo[28]=info[23];
  } else if(type==3){
     String nextInfo[]=instrument.getNextStation(actInfo[1]); 
     if(nextInfo==null) {instrument.sysLog(actInfo[1]+" has no next station."); return;}
     actInfo[1]=nextInfo[0];
     actInfo[27]= (String)instrument.portToCoors.get(instrument.wn.getPort(nextInfo[1]));
  }
  cmd=instrument.actionThread.getTextValue(cmd, ""+instrument.dataValue,instrument.textValue);
  cmd=ylib.replace(cmd, "[#masterid#]", actInfo[27]);
  cmd=ylib.replace(cmd, "[#deviceid#]", actInfo[28]);
  cmd=ylib.replace(cmd, "[#Masterid#]", actInfo[27]);
  cmd=ylib.replace(cmd, "[#Deviceid#]", actInfo[28]);
  cmd=ylib.replace(cmd, "[#MASTERID#]", actInfo[27]);
  cmd=ylib.replace(cmd, "[#DEVICEID#]", actInfo[28]);
  cmd=instrument.wn.w.e642(cmd);
  if(cmd.trim().length()<1) {
      instrument.sysLog("No commad or data to be send");
      return;
  }

      byte [] b=null;

      if(actInfo[14].equalsIgnoreCase("Y")) {

          if(instrument.wn.isNumeric(actInfo[15]) && Double.parseDouble(actInfo[15])>0) {
              if(Double.parseDouble(actInfo[15])<0.5) actInfo[15]="0.5";
          }
          else {
            instrument.sysLog("Warning: No time interval for \"continue send\".");
              return;
          }

      }
       String id=instrument.getItemId(instrument.getDataSrcFromStation(actInfo[1]));

          cmd="performcommand wsn.WSN cmd "+instrument.getDataSrcFromStation(actInfo[1])+" all "+actInfo[10].equalsIgnoreCase("Byte data")+" "+
                  (actInfo.length>12 && actInfo[12]!=null && actInfo[12].equalsIgnoreCase("Y"))+" "+actInfo[14].equalsIgnoreCase("Y")+" "+(actInfo[15].length()>0 && instrument.isNumeric(actInfo[15])? actInfo[15]:"10000.0")+" "+cmd+" "+instrument.wn.w.e642((actInfo.length>13? actInfo[13]:""))+" 0 0 0 0 0"; 
          instrument.wn.w.sendToOne(cmd,id);

}
 Hashtable ftpmodeMap=new Hashtable();
 FTPClient ftpClient=new FTPClient();
 boolean overtime=false;
 int downloadn=0,ftpdeln=0,ftpdelchkfailedaftermatch=0,ftpdelfilematchn=0,downfilematchn=0,movefromftpservern=0,
           downchkfailedaftermatch=0,uploadn=0,upfilematchn=0,upchkfailedaftermatch=0,movetoftpservern=0;
 long defaultsysstoptime=System.currentTimeMillis()+(1000L*60L*60L*24L*365L),ftpdelsize=0,
         movefromftpserversize=0,downloadsize=0,uploadsize=0,movetoftpserversize=0;
 String fullDir="",ftpMode="";
    FTPClientConfig conf;

 public void doFTP(String para){

   String item[]=ylib.csvlinetoarray(para);
   String current_ftphost="",current_ftpid="",
    current_ftppw="",ftpmodefilename=System.getProperty("user.dir")+"\\apps\\cr-wsn\\ftpmode.txt",
    ftpmodeArr[][]={{"210.65.11.29","UNIX"},{"140.96.19.7",""},{"60.251.194.192","UNIX"},{"172.16.11.15","UNIX"}};
   boolean ftpLoginOK=false,db1LoginOK=false,db2LoginOK=false,autoExit=true,
   logSysOnly=false,doingwork=false,ftpPerform=false,only1file=false;
   long fromtime=0,totime=0;
   if(isFTPCmd(item[4])){

	if(ftpmodeArr!=null && ftpmodeArr.length>0){
	  for(int i=0;i<ftpmodeArr.length;i++){
	    ftpmodeMap.put(ftpmodeArr[i][0],ftpmodeArr[i][1]);
	  }
	}

    File f;
    FileInputStream in;
    BufferedReader d;
    String str1,str3[]=new String[2];
    try{
      f=new File(ftpmodefilename);
      if(f.exists()){
        in=new FileInputStream(ftpmodefilename);
        d= new BufferedReader(new InputStreamReader(in));
        while(true){
          str1=d.readLine();
          if(str1==null) {in.close(); d.close(); break; }
          if(str1.length()>2) {
            str3=ylib.csvlinetoarray(str1);
            if(str3.length>1){
              ftpmodeMap.put(str3[0],str3[1]);
            } else {
                instrument.log("error CommonsNet().5.3 in parsing ftpmode file: properties "+str3[0]+" not in csv form.");
              }
          }
        }
        in.close();
        d.close();
      } else {
          instrument.log(ftpmodefilename+" not found. Use default values.");
      	}
    } catch (IOException e) {
        instrument.log("error CommonsNet().5.3 in reading ftpmode:\r\n"+instrument.getStackTrace(e));
        e.printStackTrace();
      }
      catch (Exception e) {
        instrument.log("error CommonsNet().5.4 in reading ftpmode:\r\n"+instrument.getStackTrace(e));
        e.printStackTrace();
      }

   if(!item[0].equalsIgnoreCase("db") && !item[0].equalsIgnoreCase("mail") && !item[0].equalsIgnoreCase("email") && !item[0].equalsIgnoreCase("itrimail") && !item[0].equalsIgnoreCase("gmail") && !item[0].equalsIgnoreCase("hmail") && !item[0].equalsIgnoreCase("sys") && !item[0].equalsIgnoreCase("system")){
            if(item[10].equals("0") || item[10].length()==0) item[10]="0000-00-00";
            if(item[11].equals("0") || item[11].length()==0) item[11]="00:00:00";
            if(item[12].equals("0") || item[12].length()==0) item[12]="9999-12-31";
            if(item[13].equals("0") || item[13].length()==0) item[13]="23:59:59";

            if(item[4].equalsIgnoreCase("rd") || item[4].equalsIgnoreCase("rmd")){
              item[10]="0000-00-00";
              item[11]="00:00:00";
              item[12]="9999-12-31";
              item[13]="23:59:59";
            }

            if(item[6].length()>0 && item[6].lastIndexOf("\\")==item[6].length()-1) item[6]=item[6].substring(0,item[6].length());
            if(item[8].length()>0 && item[8].lastIndexOf("\\")==item[8].length()-1) item[8]=item[8].substring(0,item[8].length());
            try{
              fromtime=instrument.format9.parse(item[10]+" "+item[11]).getTime();
            } catch(java.text.ParseException e){
                instrument.log("error CommonsNet().7 in parsing \""+item[10]+" "+item[11]+"\":\r\n"+instrument.getStackTrace(e));
        	fromtime=0;
              }
            try{
              totime=instrument.format9.parse(item[12]+" "+item[13]).getTime();
            } catch(java.text.ParseException e){
                instrument.log("error CommonsNet().8 in parsing \""+item[12]+" "+item[13]+"\":\r\n"+instrument.getStackTrace(e));
        	totime=System.currentTimeMillis();
              }
          }
  Calendar start=Calendar.getInstance();
  Calendar end=Calendar.getInstance();
  start.setTimeInMillis(fromtime); 
  end.setTimeInMillis(totime);
     int result=0;
     try{

     if(current_ftphost.length()<1){
        instrument.log("current_ftphost.length()<1.");
              ftpLoginOK=connectFTP(item[0],item[1],item[2],(item[16].length()<1? YB642D.decode(item[3]):(item[16].equalsIgnoreCase("noencode")? item[3]:d2003.de(item[16],item[3]))),item[5],item[17]);

	     if(ftpLoginOK) {
                current_ftphost=item[0]; current_ftpid=item[2]; 

              } else if(item[4].equalsIgnoreCase("testftp")){
                  JOptionPane.showMessageDialog(instrument, "Warning 1: Cannot connect to FTP server, please check server ip, port, or id/password.");
                  return;
              }
            } else if(!(current_ftphost.equalsIgnoreCase(item[0]) && current_ftpid.equalsIgnoreCase(item[2]))){

                  instrument.log("host & id not the same, current_ftphost="+current_ftphost+",current_ftpid="+current_ftpid);
        	  ftpClient.logout();
   	          current_ftphost=""; current_ftpid="";
        	  ftpLoginOK=connectFTP(item[0],item[1],item[2],(item[16].length()<1? YB642D.decode(item[3]):(item[16].equalsIgnoreCase("noencode")? item[3]:d2003.de(item[16],item[3]))),item[5],item[17]);
                if(ftpLoginOK) {
                  current_ftphost=item[0]; current_ftpid=item[2]; 

                } else if(item[4].equalsIgnoreCase("testftp")){
                  JOptionPane.showMessageDialog(instrument, "Warning 2: Cannot connect to FTP server, please check server ip, port, or id/password.");
                  return;
              }
      	      }

     if(ftpLoginOK){
      	      if(item[4].equalsIgnoreCase("ls") || item[4].equalsIgnoreCase("list") || item[4].equalsIgnoreCase("dir")){
                  if(ftpListDir(item[6],true,item)) result=1; else result=0;
                  ftpPerform=true;
              } 
              else if(item[4].equalsIgnoreCase("testftp")){
                  if(ftpTest(true,item)) result=1; else result=0;
                  ftpPerform=true;
                  if(result==0){
                    JOptionPane.showMessageDialog(instrument, "Warning : ftp test failed. (but not a connection or login error)");

                  } else JOptionPane.showMessageDialog(instrument, "FTP test successfully.");
              } 
      	      else if(item[4].equalsIgnoreCase("checkpath")){
	      	      item[14]="excludesubdir";
                  if(ftpListDir(item[6],true,item)) result=1; else result=0;
                  ftpPerform=true;
              } 
              else if((item[4].equalsIgnoreCase("put") || item[4].equalsIgnoreCase("upload")) && !only1file){
				uploadn=0;
                if(item[9].indexOf("*")!=-1 || item[9].equals(".") || item[9].indexOf("?")!=-1) only1file=false; 
                 else if(item[14].equalsIgnoreCase("includesubdir")) only1file=false; 
                  else only1file=true;
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                DateFormat df = DateFormat.getDateInstance( DateFormat.SHORT );

                if(!only1file){
              	  if(new File(item[8]).exists()){
                    result=(ftpUpDir(item[6],item[8],start,end,true,false,item)? 1:0);
                  } else {
                      instrument.log("error: local dir "+item[8]+" not found, stop to upload.");
                      result=0;
                    }
                } else{
                    boolean yesOK=false,changeOK=true;
                    File f1=new File(item[8] + (item[8].trim().length()>0? File.separator:"") + item[9]);
					if(item[7].equals("*") || item[7].equals(".")) item[7]=item[9];

                    if(f1.exists()){

                      if(item[6]!=null && item[6].length()>0) changeOK=ftpClient.changeWorkingDirectory(ftpMode.equalsIgnoreCase("UNIX")? "/"+ylib.replace((new String(item[6].getBytes("Big5"),"ISO-8859-1")),"\\","/"):(new String(item[6].getBytes("Big5"),"ISO-8859-1")));
                      if(!changeOK) {
              	        ftpMkDirs("",item[6]);
				        ftpClient.changeWorkingDirectory(ftpMode.equalsIgnoreCase("UNIX")? "/"+ylib.replace((new String(item[6].getBytes("Big5"),"ISO-8859-1")),"\\","/"):(new String(item[6].getBytes("Big5"),"ISO-8859-1")));
                      } 
                      java.util.Date fileDate = new java.util.Date(f1.lastModified());

                      if(fileDate.compareTo( start.getTime() ) >= 0 && fileDate.compareTo( end.getTime() ) <= 0 ){
                         upfilematchn++;

                         if(item[15].equalsIgnoreCase("overwrite")) yesOK=true;
                        else {

                           FTPFile[] files = ftpClient.listFiles();
                           if(item[15].equalsIgnoreCase("checkdate")){
                              yesOK=true;
                              for(int k=0;k<files.length;k++) 
                  	          if(new String(files[k].getName().getBytes("ISO-8859-1"), "Big5").equalsIgnoreCase(item[7])){
                  	            	if(files[ k ].getTimestamp().getTime().compareTo( fileDate ) >= 0) yesOK=false; 
                  	            	break;
                  	          }
                           } else if(item[15].equalsIgnoreCase("keeporiginal")){
                               yesOK=true;
                               for(int k=0;k<files.length;k++)
                  	          if(new String(files[k].getName().getBytes("ISO-8859-1"), "Big5").equalsIgnoreCase(item[7])){
                                    yesOK=false;
                                    break;
                                  }
                           } else {
                               instrument.log("error: file chk type: item[15]="+item[15]);
                             }
                         }

                         if(yesOK){

                           instrument.log("up: "+df.format( new java.util.Date(f1.lastModified()) )+"\t" + item[7]+"\t"+f1.length());
                           FileInputStream fInputStream = new FileInputStream(f1);

	    	           ftpClient.storeFile(new String(item[7].getBytes("Big5"),"ISO-8859-1"), fInputStream);
	    	           fInputStream.close();
	    	           uploadn++;
	      	           uploadsize+=f1.length();
		         } else upchkfailedaftermatch++;
		      }
		    }else {
			instrument.log("Warning: file "+item[8] + (item[8].trim().length()>0? File.separator:"") + item[9]+" not exists.");
			   }
                  }
                  ftpPerform=true;
		  if(uploadn==0) result=0;
              }
              else if(item[4].equalsIgnoreCase("movetoftpserver")){
			    movetoftpservern=0;
                if(item[9].indexOf("*")!=-1 || item[9].equals(".") || item[9].indexOf("?")!=-1) only1file=false; 
                  else if(item[14].equalsIgnoreCase("includesubdir")) only1file=false; 
                  else only1file=true;
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                DateFormat df = DateFormat.getDateInstance( DateFormat.SHORT );
                if(!only1file){
              	  if(new File(item[8]).exists()){
                    result=(ftpUpDir(item[6],item[8],start,end,true,true,item)? 1:0);
                  } else {
                      instrument.log("error: local dir "+item[8]+" not found, stop to upload.");
                      result=0;
                    }
                } else{
                    boolean yesOK=false;
		    if(item[7].equals("*") || item[7].equals(".")) item[7]=item[9];
                    File f1=new File(item[8] + (item[8].trim().length()>0? File.separator:"") + item[9]);
                    if(f1.exists()){
                      if(item[6]!=null && item[6].length()>0) ftpClient.changeWorkingDirectory((ftpMode.equalsIgnoreCase("UNIX")? "/"+ylib.replace((new String(item[6].getBytes("Big5"),"ISO-8859-1")),"\\","/"):(new String(item[6].getBytes("Big5"),"ISO-8859-1"))));
                      java.util.Date fileDate = new java.util.Date(f1.lastModified());
                      if(fileDate.compareTo( start.getTime() ) >= 0 &&
                         fileDate.compareTo( end.getTime() ) <= 0 ){
                         upfilematchn++;

                         if(item[15].equalsIgnoreCase("overwrite")) yesOK=true;
                        else {
                           FTPFile[] files = ftpClient.listFiles();
                           if(item[15].equalsIgnoreCase("checkdate")){
                              yesOK=true;
                              for(int k=0;k<files.length;k++) 
                  	          if(new String(files[k].getName().getBytes("ISO-8859-1"), "Big5").equalsIgnoreCase(item[7])){
                  	            	if(files[ k ].getTimestamp().getTime().compareTo( fileDate ) >= 0) yesOK=false; 
                  	            	break;
                  	          }
                           } else if(item[15].equalsIgnoreCase("keeporiginal")){
                               yesOK=true;
                               for(int k=0;k<files.length;k++)
                  	          if(new String(files[k].getName().getBytes("ISO-8859-1"), "Big5").equalsIgnoreCase(item[7])){
                                    yesOK=false;
                                    break;
                                  }
                           } else {
                               instrument.log("error: file chk type: item[15]="+item[15]);
                             }
                         }
                         if(yesOK){

                           instrument.log("moveup: "+df.format( new java.util.Date(f1.lastModified()) )+"\t" + item[7]+"\t"+f1.length());
                           FileInputStream fInputStream = new FileInputStream(f1);
	    	           ftpClient.storeFile(new String(item[7].getBytes("Big5"),"ISO-8859-1"), fInputStream);
	    	           fInputStream.close();
	    	           movetoftpservern++;
	      	           movetoftpserversize+=f1.length();
	    	           f1.delete();
		         } else upchkfailedaftermatch++;
		      }
		    } else {
			 instrument.log("Warning: file "+item[8] + (item[8].trim().length()>0? File.separator:"") + item[9]+" not exists.");
			   }
                  }
                  ftpPerform=true;
				  if(movetoftpservern==0) result=0;
              }
              else if((item[4].equalsIgnoreCase("get") || item[4].equalsIgnoreCase("download"))){

                if(item[7].indexOf("*")!=-1 || item[7].equals(".") || item[7].indexOf("?")!=-1) only1file=false; 
                  else if(item[14].equalsIgnoreCase("includesubdir")) only1file=false; 
                  else only1file=true;
                if(!only1file){
                  ftpDownDir(item[6],item[8],start,end,true,false,item);
                } else {

                    try {

                      if(item[6]!=null && item[6].length()>0) ftpClient.changeWorkingDirectory(ftpMode.equalsIgnoreCase("UNIX")? "/"+ylib.replace((new String(item[6].getBytes("Big5"),"ISO-8859-1")),"\\","/"):(new String(item[6].getBytes("Big5"),"ISO-8859-1")));
                        FTPFile[] files = ftpClient.listFiles();
                      DateFormat df = DateFormat.getDateInstance( DateFormat.SHORT );
                      boolean match=false;
                      for( int k=0; k<files.length; k++ ) {
                        switch(files[ k ].getType()){
                          case FTPFile.DIRECTORY_TYPE:
                            break;
                          default:
                            if(ylib.chkfn(item[7],new String(files[k].getName().getBytes("ISO-8859-1"), "Big5"))){
                              java.util.Date fileDate = files[ k ].getTimestamp().getTime();
                              boolean yesOK=false;
                              if(fileDate.compareTo( start.getTime() ) >= 0 &&
                                fileDate.compareTo( end.getTime() ) <= 0 ){
                                downfilematchn++;
                                if(item[9].equals(".") || item[9].equals("*")) item[9]=new String(files[k].getName().getBytes("ISO-8859-1"), "Big5");
                                if(item[15].equalsIgnoreCase("overwrite")) yesOK=true;
                                else if(item[15].equalsIgnoreCase("checkdate")){
                                	File f1=new File(item[8] + (item[8].trim().length()>0? File.separator:"") + item[9]);
                                	yesOK=true;
                                	if(f1.exists() && fileDate.compareTo(new java.util.Date(f1.lastModified())) <= 0) yesOK=false;
                                }
                                else if(item[15].equalsIgnoreCase("keeporiginal")){
                                	if(!(new File(item[8] + (item[8].trim().length()>0? File.separator:"") + item[9]).exists())) yesOK=true;
                              	  else yesOK=false;
                                } else {
                                   instrument.log("error: file chk type: item[15]="+item[15]);
                              	}
                                if(yesOK){

                                  instrument.log("down: "+df.format( files[ k ].getTimestamp().getTime() )+"\t" + item[9]+"\t"+files[ k ].getSize());
                                  File file1 =new File( item[8]);
                                  if(!file1.exists()){file1.mkdirs();}
                                  file1 = new File( item[8] + (item[8].trim().length()>0? File.separator:"") + item[9] );
                                  FileOutputStream fos = new FileOutputStream( file1 ); 
                                  ftpClient.retrieveFile( files[ k ].getName(), fos );
                                  fos.close();
                                  file1.setLastModified( fileDate.getTime() );
                                  match=true;
                                  downloadn++;
                                  downloadsize+=files[ k ].getSize();
                                  if(System.currentTimeMillis()-defaultsysstoptime>0){
                                    instrument.log("Warning: over the time limit, following workitems stopped.");
                                    overtime=true;
                                    result=0;
                                  } 
                                } else downchkfailedaftermatch++;
                              }
                            }
                            break;
                         }
                         if(match) break;
                       }
                    } catch( Exception e ) {
                        instrument.log("error 2-Exception in getting only1file:\r\n"+instrument.getStackTrace(e));
                        result=0;
                  	    e.printStackTrace(); 
                  	}
                    if(System.currentTimeMillis()-defaultsysstoptime>0){
                       instrument.log("Warning: over the time limit, following workitems stopped.");
                       overtime=true;
                    }
                  }
                  ftpPerform=true;
              }
              else if(item[4].equalsIgnoreCase("movefromftpserver")){
                if(item[7].indexOf("*")!=-1 || item[7].equals(".") || item[7].indexOf("?")!=-1) only1file=false; 
                  else if(item[14].equalsIgnoreCase("includesubdir")) only1file=false; 
                  else only1file=true;
                if(!only1file){
                  ftpDownDir(item[6],item[8],start,end,true,true,item);
                } else {

                    try {

					  if(item[6]!=null && item[6].length()>0) ftpClient.changeWorkingDirectory(ftpMode.equalsIgnoreCase("UNIX")? "/"+ylib.replace((new String(item[6].getBytes("Big5"),"ISO-8859-1")),"\\","/"):(new String(item[6].getBytes("Big5"),"ISO-8859-1")));
                      FTPFile[] files = ftpClient.listFiles();
                      DateFormat df = DateFormat.getDateInstance( DateFormat.SHORT );
                      boolean match=false;
                      for( int k=0; k<files.length; k++ ) {
                        switch(files[ k ].getType()){
                          case FTPFile.DIRECTORY_TYPE:
                            break;
                          default:
                            if(ylib.chkfn(item[7],new String(files[k].getName().getBytes("ISO-8859-1"), "Big5"))){
                              java.util.Date fileDate = files[ k ].getTimestamp().getTime();
                              boolean yesOK=false;
                              if(fileDate.compareTo( start.getTime() ) >= 0 &&
                                fileDate.compareTo( end.getTime() ) <= 0 ){
                                downfilematchn++;
                                if(item[9].equals(".") || item[9].equals("*")) item[9]=new String(files[k].getName().getBytes("ISO-8859-1"), "Big5");
                                if(item[15].equalsIgnoreCase("overwrite")) yesOK=true;
                                else if(item[15].equalsIgnoreCase("checkdate")){
                                	File f1=new File(item[8] + (item[8].trim().length()>0? File.separator:"") + item[9]);
                                	yesOK=true;
                                	if(f1.exists() && fileDate.compareTo(new java.util.Date(f1.lastModified())) <= 0) yesOK=false;
                                }
                                else if(item[15].equalsIgnoreCase("keeporiginal")){
                                	if(!(new File(item[8] + (item[8].trim().length()>0? File.separator:"") + item[9]).exists())) yesOK=true;
                              	  else yesOK=false;
                                } else {
                                   instrument.log("error: file chk type: item[15]="+item[15]);
                                  result=0;
                              	}
                                if(yesOK){

                                  instrument.log("movedown: "+df.format( files[ k ].getTimestamp().getTime() )+"\t" + item[9]+"\t"+files[ k ].getSize());
                                  File file1 =new File( item[8]);
                                  if(!file1.exists()){file1.mkdirs();}
                                  file1 = new File( item[8] + (item[8].trim().length()>0? File.separator:"") + item[9] );
                                  FileOutputStream fos = new FileOutputStream( file1 ); 
                                  ftpClient.retrieveFile( files[ k ].getName(), fos );
                                  fos.close();
                                  file1.setLastModified( fileDate.getTime() );
                                  match=true;
                                  long len=files[ k ].getSize();
                                  if(ftpClient.deleteFile(files[k].getName())){
                                    movefromftpservern++;
                                    movefromftpserversize+=len;
                                  } else {
                                     instrument.log("error in deleting ftpserver file: "+files[k].getName());
                                      result=0;
                                    }
                                  if(System.currentTimeMillis()-defaultsysstoptime>0){
                                    instrument.log("Warning: over the time limit, following workitems stopped.");
                                    overtime=true;
                                    result=0;
                                  } 
                                } else downchkfailedaftermatch++;
                              }
                            }
                            break;
                         }
                         if(match) break;
                       }
                    } catch( Exception e ) {
                        instrument.log("error 2-Exception in getting only1file:\r\n"+instrument.getStackTrace(e));
                        result=0;
                  	e.printStackTrace(); 
                  	}
                    if(System.currentTimeMillis()-defaultsysstoptime>0){
                       instrument.log("Warning: over the time limit, following workitems stopped.");
                       overtime=true;
                       result=0;
                    }
                  }
                  ftpPerform=true;
              }
              else if(item[4].equalsIgnoreCase("del") || item[4].equalsIgnoreCase("delete")){
              	

                if(item[7].indexOf("*")!=-1 || item[7].equals(".") || item[7].indexOf("?")!=-1) only1file=false; 
                  else if(item[14].equalsIgnoreCase("includesubdir")) only1file=false; 
                  else only1file=true;
                if(!only1file){
                  ftpDelDir(item[6],start,end,true,item);
                } else {
                    try {

					  if(item[6]!=null && item[6].length()>0) ftpClient.changeWorkingDirectory(ftpMode.equalsIgnoreCase("UNIX")? "/"+ylib.replace((new String(item[6].getBytes("Big5"),"ISO-8859-1")),"\\","/"):(new String(item[6].getBytes("Big5"),"ISO-8859-1")));
                      FTPFile[] files = ftpClient.listFiles();
                      DateFormat df = DateFormat.getDateInstance( DateFormat.SHORT );
                      boolean match=false;
                      for( int k=0; k<files.length; k++ ) {
                        switch(files[ k ].getType()){
                          case FTPFile.DIRECTORY_TYPE:
                            break;
                          default:
                            if(ylib.chkfn(item[7],new String(files[k].getName().getBytes("ISO-8859-1"), "Big5"))){
                              java.util.Date fileDate = files[ k ].getTimestamp().getTime();
                              boolean yesOK=false;
                              if(fileDate.compareTo( start.getTime() ) >= 0 &&
                                fileDate.compareTo( end.getTime() ) <= 0 ){
                                ftpdelfilematchn++;
                                if(item[7].equals(".") || item[7].equals("*")) item[9]=new String(files[k].getName().getBytes("ISO-8859-1"), "Big5");
                                yesOK=true;
                                if(yesOK){
                                  instrument.log("ftpdel: "+df.format( files[ k ].getTimestamp().getTime() )+"\t" + item[9]+"\t"+files[ k ].getSize());
                                  match=true;
                                  long len=files[ k ].getSize();

                                  if(ftpClient.deleteFile(files[k].getName())){
                                    ftpdeln++;
                                    ftpdelsize+=len;
                                  } else {
                                     instrument.log("error in deleting ftpserver file: "+files[k].getName());
                                      result=0;
                                    }
                                  if(System.currentTimeMillis()-defaultsysstoptime>0){
                                    instrument.log("Warning: over the time limit, following workitems stopped.");
                                    overtime=true;
                                    result=0;
                                  }
                                } else ftpdelchkfailedaftermatch++;
                              }
                            }
                            break;
                         }
                         if(match) break;
                       }
                    } catch( Exception e ) {
                          instrument.log("error 2-Exception in getting only1file:\r\n"+instrument.getStackTrace(e));
                          result=0;
                    	  e.printStackTrace(); 
                    	}
                    if(System.currentTimeMillis()-defaultsysstoptime>0){
                       instrument.log("Warning: over the time limit, following workitems stopped.");
                       result=0;
                       overtime=true;
                    }
                  }
                ftpPerform=true;
              }
              else if(item[4].equalsIgnoreCase("ren") || item[4].equalsIgnoreCase("rename")){

                if(item[6]!=null && item[6].length()>0) ftpClient.changeWorkingDirectory(ftpMode.equalsIgnoreCase("UNIX")? "/"+ylib.replace((new String(item[6].getBytes("Big5"),"ISO-8859-1")),"\\","/"):(new String(item[6].getBytes("Big5"),"ISO-8859-1")));
                result=(ftpClient.rename(new String(item[7].getBytes("Big5"),"ISO-8859-1"),new String(item[9].getBytes("Big5"),"ISO-8859-1"))? 1:0);
                ftpPerform=true;
              }
              else if(item[4].equalsIgnoreCase("md") || item[4].equalsIgnoreCase("mkd") || item[4].equalsIgnoreCase("mkdir")){
                result=(ftpMkDirs(item[6],item[7])? 1:0);
                ftpPerform=true;
              }
              else if(item[4].equalsIgnoreCase("rd") || item[4].equalsIgnoreCase("rmd")){
                item[14]="includesubdir";
                item[7]=".";
                ftpDelDir(item[6],start,end,true,item);
				ftpClient.changeToParentDirectory();
                if(!ftpClient.removeDirectory((ftpMode.equalsIgnoreCase("UNIX")? "/"+ylib.replace((new String(item[6].getBytes("Big5"),"ISO-8859-1")),"\\","/"):(new String(item[6].getBytes("Big5"),"ISO-8859-1"))))){
                  instrument.log("error: failed to removeDirectory(\""+item[6]+"\"). working dir="+(new String(ftpClient.printWorkingDirectory().getBytes("ISO-8859-1"),"Big5"))+",Msg: "+ftpClient.getReplyString());
				  result=0;
				} else result=1;
                ftpPerform=true;
              }
              else if(item[4].equalsIgnoreCase("checktime")){
              }
              else {
                instrument.log("error: unknown ftp worktype:"+item[4]);
                ftpPerform=false;
              }
              if(ftpPerform){

                ftpClient.logout();
                current_ftphost=""; current_ftpid="";
                ftpLoginOK=false;
              }
            }
     } catch(IOException e){
          instrument.log("error in doWorkItem():\r\n"+instrument.getStackTrace(e));
    	  e.printStackTrace();
    	  current_ftphost=""; current_ftpid="";
    	  result=0;
      } catch(Exception e){
          instrument.log("error in doWorkItem():\r\n"+instrument.getStackTrace(e));
    	  e.printStackTrace();
    	  current_ftphost=""; current_ftpid="";
    	  result=0;
        }
     }
   else instrument.log("Warning: "+item[4]+" is not FTP command.");
 }
 boolean isFTPCmd(String cmd){
    String ftpcmd[]={"testftp","ls","list","dir","del","delete","ren","rename","get","download","put",
      "upload","md","mkd","mkdir","rd","rmd","checktime","movefromftpserver",
      "movetoftpserver","checkpath"};
    boolean rtn=false;
    for(int i=0;i<ftpcmd.length;i++){
      if(cmd.equalsIgnoreCase(ftpcmd[i])){ rtn=true; break;}
    }
    return rtn;
  }
   

   boolean connectFTP(String host,String port,String id,String pw,String onevar,String mode){

    boolean error = false;
    if(ftpmodeMap.get(host)!=null) mode=(String)ftpmodeMap.get(host);
      else if(ftpmodeMap.get("default")!=null) mode=(String)ftpmodeMap.get("default");
    try {
      int reply;
      ftpClient.connect(host,(port.length()>0 ? Integer.parseInt(port):21));
       instrument.log("Connected to " + host + ".");
       instrument.log("ftpreply="+new String(ftpClient.getReplyString().getBytes("ISO-8859-1"), "Big5"));

      reply = ftpClient.getReplyCode();

      if(!FTPReply.isPositiveCompletion(reply)) {
        ftpClient.disconnect();
         instrument.log("FTP server "+host+" refused connection.");

      } else {
	      ftpMode=mode;
          if(!mode.equalsIgnoreCase("null")) {
		    if(mode.length()==0) conf=new FTPClientConfig(FTPClientConfig.SYST_NT);
			  else conf=new FTPClientConfig(mode);
		    ftpClient.configure(conf); 
		  } 
	      if(!OneVar.check(onevar,0)) ftpClient.enterLocalPassiveMode();
      	  error=!ftpClient.login(id, pw);
      	  if(error) {
            instrument.log("error: login failed. id="+id);
      	  }   else  {}
      	}

    } catch(IOException e) {
         instrument.log("error connect():\r\n"+instrument.getStackTrace(e));
        error = true;
        e.printStackTrace();
    } finally {
        if(ftpClient.isConnected()) {
      }
      }
    return !error;
  }
     

  void ftpDelDir(String serverDir,Calendar start,Calendar end,boolean firstDir,String item[]){
              try {
              	boolean changeOK=true;

				String chTo=(firstDir? (ftpMode.equalsIgnoreCase("UNIX")? "/"+ylib.replace((new String(serverDir.getBytes("Big5"),"ISO-8859-1")),"\\","/"):(new String(serverDir.getBytes("Big5"),"ISO-8859-1"))):(ftpMode.equalsIgnoreCase("UNIX")? ylib.replace(serverDir,"\\","/"):serverDir));
				if(serverDir!=null && serverDir.length()>0) changeOK=ftpClient.changeWorkingDirectory(chTo);
                if(!changeOK) {
                  instrument.log("error: cann't chang server Dir to "+chTo+", stop ftpdel.(firstDir="+firstDir+")");
               	  return;
                } 

                fullDir=new String(ftpClient.printWorkingDirectory().getBytes("ISO-8859-1"),"Big5");
                FTPFile[] files = ftpClient.listFiles();
                instrument.log("N of files in dir "+fullDir+": " + files.length+",firstDir="+firstDir);
                DateFormat df = DateFormat.getDateInstance( DateFormat.SHORT );
                for( int k=0; k<files.length; k++ ) {
                  try{
                    java.util.Date fileDate = files[ k ].getTimestamp().getTime();
                    switch(files[ k ].getType()){
                      case FTPFile.DIRECTORY_TYPE:
                        if(item[14].equalsIgnoreCase("includesubdir") && !overtime){
                          ftpDelDir(files[ k ].getName(),start,end,false,item);

                          if((item[7].equals(".") || item[7].equals("*")) && 
                           (fileDate.compareTo( start.getTime() ) >= 0 &&
                              fileDate.compareTo( end.getTime() ) <= 0 )) {

							  ftpClient.removeDirectory(files[k].getName());

						   }
                        }
                        break;
                      default:

                        item[9]=new String(files[k].getName().getBytes("ISO-8859-1"), "Big5");
                        if(ylib.chkfn(item[7],item[9])){
                          boolean yesOK=false;
                          if(fileDate.compareTo( start.getTime() ) >= 0 &&
                              fileDate.compareTo( end.getTime() ) <= 0 ){
                            ftpdelfilematchn++;
                            yesOK=true;
                            if(yesOK){

                              instrument.log("ftpdel: "+df.format( files[ k ].getTimestamp().getTime() )+"\t" + item[9]+"\t"+files[ k ].getSize());
                              long len=files[k].getSize();
                              if(ftpClient.deleteFile(files[k].getName())){
                                ftpdeln++;
                                ftpdelsize+=len;
                              } else {
                                     instrument.log("error in deleting ftpserver file: "+files[k].getName());
                                    }
                            } else ftpdelchkfailedaftermatch++;
                          }
                        }
                        break;
                    }
                    if(System.currentTimeMillis()-defaultsysstoptime>0) {
                              instrument.log("Warning: over the time limit, following workitems stopped.");
                        overtime=true;
                        break;
                    }
                  }catch(IOException e){
                     instrument.log("error ftpDelDir(\""+serverDir+"\"):\r\n"+instrument.getStackTrace(e));
               	     e.printStackTrace(); 
                   }
                }
                if(!firstDir) {
                  ftpClient.changeToParentDirectory();

                }
              } catch( Exception e ) {
                    instrument.log("error ftpDelDir(\""+serverDir+"\"):\r\n"+instrument.getStackTrace(e));
              	    e.printStackTrace(); 
              	  }
  }

  boolean ftpDownDir(String serverDir,String myDir,Calendar start,Calendar end,boolean firstDir,boolean move,String item[]){

	  boolean rtn=true;
              try {
              	boolean changeOK=true;

				if(serverDir!=null && serverDir.length()>0) changeOK=ftpClient.changeWorkingDirectory((firstDir? (ftpMode.equalsIgnoreCase("UNIX")? "/"+ylib.replace((new String(serverDir.getBytes("Big5"),"ISO-8859-1")),"\\","/"):(new String(serverDir.getBytes("Big5"),"ISO-8859-1"))):(ftpMode.equalsIgnoreCase("UNIX")? ylib.replace(serverDir,"\\","/"):serverDir)));
                if(!changeOK) {
                  instrument.log("error: cann't chang server Dir to "+serverDir+", stop downloading.");
               	  return false;
                } 

                fullDir=new String(ftpClient.printWorkingDirectory().getBytes("ISO-8859-1"),"Big5");

                FTPFile[] files = ftpClient.listFiles();

                instrument.log("myDir="+myDir+",N of files in dir "+fullDir+": " + files.length+",firstDir="+firstDir);
                DateFormat df = DateFormat.getDateInstance( DateFormat.SHORT );
                for( int k=0; k<files.length; k++ ) {
                  try{
                    switch(files[ k ].getType()){
                      case FTPFile.DIRECTORY_TYPE:
                        if(item[14].equalsIgnoreCase("includesubdir") && !overtime){

                          ftpDownDir(files[ k ].getName(),myDir+ (myDir.trim().length()>0? File.separator:"") +new String(files[k].getName().getBytes("ISO-8859-1"), "Big5"),start,end,false,move,item);
                        }
                        break;
                      default:

                        item[9]=new String(files[k].getName().getBytes("ISO-8859-1"), "Big5");
                        if(ylib.chkfn(item[7],item[9])){
                          java.util.Date fileDate = files[ k ].getTimestamp().getTime();
                          boolean yesOK=false;
                          if(fileDate.compareTo( start.getTime() ) >= 0 &&
                              fileDate.compareTo( end.getTime() ) <= 0 ){
                            downfilematchn++;
                            if(item[15].equalsIgnoreCase("overwrite")) yesOK=true;
                            else if(item[15].equalsIgnoreCase("checkdate")){
                              	yesOK=true;
                              	File f1=new File(myDir + (myDir.trim().length()>0? File.separator:"") + item[9]);

                              	if(f1.exists() && fileDate.compareTo(new java.util.Date(f1.lastModified())) <= 0) yesOK=false;
                            }
                            else if(item[15].equalsIgnoreCase("keeporiginal")){
                              if(!(new File(myDir + (myDir.trim().length()>0? File.separator:"") + item[9]).exists())) yesOK=true;
                                else yesOK=false;
                            } else {
                                instrument.log("error: file chk type: item[15]="+item[15]);
                                rtn=false;
                              }
                            if(yesOK){

                              instrument.log((move? "movedown: ":"down: ")+df.format( files[ k ].getTimestamp().getTime() )+"\t" + item[9]+"\t"+files[ k ].getSize());
                              File file1 =new File (myDir+((myDir.length()==2 && myDir.indexOf(":")==1)? "\\":""));
                              if(!file1.exists()){file1.mkdirs();}
                              file1 = new File( myDir + (myDir.trim().length()>0? File.separator:"") + item[9] );
				try{
                                FileOutputStream fos = new FileOutputStream( file1 ); 
                                ftpClient.retrieveFile( files[ k ].getName(), fos );
                                fos.close();
				} catch(FileNotFoundException e) {
				   instrument.log(instrument.getStackTrace(e));
					break;
				    }
                              file1.setLastModified( fileDate.getTime() );
                              long len=files[k].getSize();
                              if(move){
                                if(ftpClient.deleteFile(files[k].getName())){
                                  movefromftpservern++;
                                  movefromftpserversize+=len;
                               } else {
                                    instrument.log("error in deleting ftpserver file: "+files[k].getName());
                                      rtn=false;
                                 }

                              } else {
                                  downloadn++;
                                  downloadsize+=len;
                                }
                            } else downchkfailedaftermatch++;
                          }
                        }
                        break;
                    }
                    if(System.currentTimeMillis()-defaultsysstoptime>0) {
                        instrument.log("Warning: over the time limit, following workitems stopped.");
                        overtime=true;
                        break;
                    }
                  }catch(IOException e){
                     instrument.log("error ftpDownDir(\""+serverDir+"\",\""+myDir+"\"):\r\n"+instrument.getStackTrace(e));
                     rtn=false;
               	     e.printStackTrace(); 
                   }
                }
                if(!firstDir) {
                  ftpClient.changeToParentDirectory();

                }
              } catch( Exception e ) {
		try{
                    instrument.log("error ftpDownDir(\""+serverDir+"\",\""+myDir+"\",xxx,xxx,"+firstDir+","+move+"),workingdir="+(new String(ftpClient.printWorkingDirectory().getBytes("ISO-8859-1"),"Big5"))+":\r\n"+instrument.getStackTrace(e));
		}catch(IOException e2){e2.printStackTrace();}
                    rtn=false;
              	    e.printStackTrace(); 
              	  }
       return rtn;
  }
      boolean ftpListDir(String serverDir,boolean firstDir,String item[]){
  	 boolean resu=true;
	   try{
  	      boolean changeOK=true;
              int dirn=0,filen=0;

              fullDir=new String(ftpClient.printWorkingDirectory().getBytes("ISO-8859-1"),"Big5");
              if(firstDir) serverDir=(ftpMode.equalsIgnoreCase("UNIX")? "/"+ylib.replace((new String(serverDir.getBytes("Big5"),"ISO-8859-1")),"\\","/"):(new String(serverDir.getBytes("Big5"),"ISO-8859-1")));
              if(serverDir!=null && serverDir.length()>0) changeOK=ftpClient.changeWorkingDirectory(serverDir);

			  if(!changeOK) {
                instrument.log("error: cann't chang server Dir to "+serverDir+", stop listing.");
              	return false;
              } else fullDir=(firstDir? "":fullDir)+(firstDir? "":"/")+serverDir;

              FTPFile[] files = ftpClient.listFiles();

              DateFormat df = DateFormat.getDateInstance( DateFormat.SHORT );
              instrument.log("目錄: "+fullDir);
              for( int k=0; k<files.length; k++ ) {
                if(files[k].getType()==FTPFile.DIRECTORY_TYPE){
                  if(ylib.chkfn(item[7],new String(files[k].getName().getBytes("ISO-8859-1"), "Big5"))){
                      instrument.log(""+new String(files[k].getName().getBytes("ISO-8859-1"), "Big5")+"\t"+(files[k].getType()==FTPFile.DIRECTORY_TYPE? "<DIR>":String.valueOf(files[k].getSize()))+"\t"+instrument.format9.format(files[k].getTimestamp().getTime()));
                    dirn++;
                  }
                }
              }
              for( int k=0; k<files.length; k++ ) {
                if(files[k].getType()!=FTPFile.DIRECTORY_TYPE){
                  if(ylib.chkfn(item[7],new String(files[k].getName().getBytes("ISO-8859-1"), "Big5"))){
                    instrument.log(""+new String(files[k].getName().getBytes("ISO-8859-1"), "Big5")+"\t"+(files[k].getType()==FTPFile.DIRECTORY_TYPE? "<DIR>":String.valueOf(files[k].getSize()))+"\t"+instrument.format9.format(files[k].getTimestamp().getTime()));
                    filen++;
                  }
                }
              }
              instrument.log("\t"+dirn+" 個目錄, "+filen+" 個檔案。");
              if(System.currentTimeMillis()-defaultsysstoptime>0) overtime=true;
              if(item[14].equalsIgnoreCase("includesubdir") && !overtime){
                for( int k=0; k<files.length; k++ ) {
                  if(files[k].getType()==FTPFile.DIRECTORY_TYPE){
                    resu=ftpListDir(files[k].getName(),false,item);
                  }
                  if(System.currentTimeMillis()-defaultsysstoptime>0) {overtime=true; break;}
                }
              }
              if(!firstDir) {
              	ftpClient.changeToParentDirectory();

              }
              if(System.currentTimeMillis()-defaultsysstoptime>0) overtime=true;
              if(overtime) {
                instrument.log("Warning: over the time limit, following workitems stopped.");
              	resu=false;
              }
        }catch(Exception e){
            instrument.log("error ftpListDir(\""+serverDir+"\"):\r\n"+instrument.getStackTrace(e));
            e.printStackTrace();
            resu=false;
           }
      return resu;
  }

  boolean ftpTest(boolean firstDir,String item[]){
      String serverDir=item[6];
  	 boolean resu=true;
	   try{
  	      boolean changeOK=true;
              int dirn=0,filen=0;

              fullDir=new String(ftpClient.printWorkingDirectory().getBytes("ISO-8859-1"),"Big5");
              if(firstDir) serverDir=(ftpMode.equalsIgnoreCase("UNIX")? "/"+ylib.replace((new String(serverDir.getBytes("Big5"),"ISO-8859-1")),"\\","/"):(new String(serverDir.getBytes("Big5"),"ISO-8859-1")));
              if(serverDir!=null && serverDir.length()>0) changeOK=ftpClient.changeWorkingDirectory(serverDir);

	      if(!changeOK) {
                instrument.log("error: cann't chang server Dir to "+serverDir+", stop listing.");
              	return false;
              } else fullDir=(firstDir? "":fullDir)+(firstDir? "":"/")+serverDir;

              FTPFile[] files = ftpClient.listFiles();

              DateFormat df = DateFormat.getDateInstance( DateFormat.SHORT );
              instrument.log("目錄: "+fullDir);
              for( int k=0; k<files.length; k++ ) {
                if(files[k].getType()==FTPFile.DIRECTORY_TYPE){
                  if(ylib.chkfn(item[7],new String(files[k].getName().getBytes("ISO-8859-1"), "Big5"))){
                      instrument.log(""+new String(files[k].getName().getBytes("ISO-8859-1"), "Big5")+"\t"+(files[k].getType()==FTPFile.DIRECTORY_TYPE? "<DIR>":String.valueOf(files[k].getSize()))+"\t"+instrument.format9.format(files[k].getTimestamp().getTime()));
                    dirn++;
                  }
                }
              }
              for( int k=0; k<files.length; k++ ) {
                if(files[k].getType()!=FTPFile.DIRECTORY_TYPE){
                  if(ylib.chkfn(item[7],new String(files[k].getName().getBytes("ISO-8859-1"), "Big5"))){
                    instrument.log(""+new String(files[k].getName().getBytes("ISO-8859-1"), "Big5")+"\t"+(files[k].getType()==FTPFile.DIRECTORY_TYPE? "<DIR>":String.valueOf(files[k].getSize()))+"\t"+instrument.format9.format(files[k].getTimestamp().getTime()));
                    filen++;
                  }
                }
              }
              instrument.log("\t"+dirn+" 個目錄, "+filen+" 個檔案。");
              if(System.currentTimeMillis()-defaultsysstoptime>0) overtime=true;
              if(item[14].equalsIgnoreCase("includesubdir") && !overtime){
                for( int k=0; k<files.length; k++ ) {
                  if(files[k].getType()==FTPFile.DIRECTORY_TYPE){
                    resu=ftpListDir(files[k].getName(),false,item);
                  }
                  if(System.currentTimeMillis()-defaultsysstoptime>0) {overtime=true; break;}
                }
              }
              if(!firstDir) {
              	ftpClient.changeToParentDirectory();

              }
              if(System.currentTimeMillis()-defaultsysstoptime>0) overtime=true;
              if(overtime) {
                instrument.log("Warning: over the time limit, following workitems stopped.");
              	resu=false;
              }
        }catch(Exception e){
            instrument.log("error ftpListDir(\""+serverDir+"\"):\r\n"+instrument.getStackTrace(e));
            e.printStackTrace();
            resu=false;
           }
      return resu;
  }

  boolean ftpUpDir(String serverDir,String myDir,Calendar start,Calendar end,boolean firstDir,boolean move,String item[]){
	boolean rtn=true;
    try {
	  instrument.log("ftpUpDir("+serverDir+","+myDir+",start,end,"+firstDir+","+move+"), workingdir="+(new String(ftpClient.printWorkingDirectory().getBytes("ISO-8859-1"),"Big5")));
              boolean makeOK1=true,makeOK2=true;
              boolean changeOK=true;

              if(serverDir!=null && serverDir.length()>0) changeOK=ftpClient.changeWorkingDirectory((ftpMode.equalsIgnoreCase("UNIX")? (firstDir? "/":"")+ylib.replace((new String(serverDir.getBytes("Big5"),"ISO-8859-1")),"\\","/"):(new String(serverDir.getBytes("Big5"),"ISO-8859-1"))));
              if(!changeOK) {
              	rtn=(firstDir? ftpMkDirs("",serverDir):ftpMkDirs((new String(ftpClient.printWorkingDirectory().getBytes("ISO-8859-1"),"Big5")).substring(1),serverDir));

				ftpClient.changeWorkingDirectory((ftpMode.equalsIgnoreCase("UNIX")? (new String(ftpClient.printWorkingDirectory().getBytes("ISO-8859-1"),"Big5"))+"/"+ylib.replace((new String(serverDir.getBytes("Big5"),"ISO-8859-1")),"\\","/"):(new String(ftpClient.printWorkingDirectory().getBytes("ISO-8859-1"),"Big5"))+"\\"+(new String(serverDir.getBytes("Big5"),"ISO-8859-1"))));
              	

              } 
              FTPFile[] files = ftpClient.listFiles();

              fullDir=new String(ftpClient.printWorkingDirectory().getBytes("ISO-8859-1"),"Big5");
              if(firstDir) serverDir=new String(serverDir.getBytes("Big5"),"ISO-8859-1");
              File myFiles[]=new File(myDir+((myDir.length()==2 && myDir.indexOf(":")==1)? "\\":"")).listFiles();
              instrument.log("N of files in myDir "+myDir+((myDir.length()==2 && myDir.indexOf(":")==1)? "\\":"")+": "+myFiles.length+",N of files in serverDir="+fullDir+"="+files.length+", firstDir="+firstDir);
              DateFormat df = DateFormat.getDateInstance( DateFormat.SHORT );
              if(myFiles!=null){
              	p2:
                for( int k=0; k<myFiles.length; k++ ) {
                  if(myFiles[ k ].isDirectory()){
                      if(item[14].equalsIgnoreCase("includesubdir") && !overtime){
                      	boolean found=false;
                      	for(int m=0;m<files.length;m++){
                      	  if(myFiles[k].getName().equalsIgnoreCase(new String(files[m].getName().getBytes("ISO-8859-1"), "Big5")) && files[m].getType()==FTPFile.DIRECTORY_TYPE){
                      	    found=true; break;
                      	  }
                      	}
                      	makeOK2=true;
                      	if(!found) makeOK2=ftpClient.makeDirectory(new String(myFiles[k].getName().getBytes("Big5"),"ISO-8859-1"));

                        if(makeOK2) rtn=ftpUpDir(myFiles[k].getName(),myFiles[k].getAbsolutePath(),start,end,false,move,item);
                          else {
                            instrument.log("error : cann't makeDirectory() on server, dir = "+myFiles[k].getName()+", and skip upload to this dir. (server dir="+(new String(ftpClient.printWorkingDirectory().getBytes("ISO-8859-1"),"Big5"))+")");
                            rtn=false;
                          }
                      }
                      if(System.currentTimeMillis()-defaultsysstoptime>0) overtime=true;
                  } else {
                      boolean yesOK=false;
                      if(ylib.chkfn(item[9],myFiles[k].getName())){
                      	java.util.Date fileDate = new java.util.Date(myFiles[ k ].lastModified());
                        if(fileDate.compareTo( start.getTime() ) >= 0 &&
                          fileDate.compareTo( end.getTime() ) <= 0 ){
                          upfilematchn++;
                          item[7]=myFiles[k].getName();
                          if(item[15].equalsIgnoreCase("overwrite")) yesOK=true;
                          else {
                            if(item[15].equalsIgnoreCase("checkdate")){
                              yesOK=true;
                              for(int m=0;m<files.length;m++) 
                  	          if(myFiles[k].getName().equalsIgnoreCase(new String(files[m].getName().getBytes("ISO-8859-1"), "Big5"))){
                  	            	if(files[m].getTimestamp().getTime().compareTo( fileDate ) >= 0) yesOK=false; 
                  	            	break;
                  	          }
                            } else if(item[15].equalsIgnoreCase("keeporiginal")){
                                yesOK=true;
                                for(int m=0;m<files.length;m++)
                  	          if(new String(files[m].getName().getBytes("ISO-8859-1"), "Big5").equalsIgnoreCase(myFiles[k].getName())){
                                    yesOK=false;
                                    break;
                                  }
                              }
                              else {
                                instrument.log("error: file chk type: item[15]="+item[15]);
                              }
                          }
                          if(yesOK){

                            item[7]=myFiles[k].getName();
                            instrument.log((move? "moveup: ":"up: ")+df.format( new java.util.Date(myFiles[ k ].lastModified()) )+"\t" + item[7]+"\t"+myFiles[k].length());
                            FileInputStream fInputStream = new FileInputStream(myFiles[k]);
   	      	            ftpClient.storeFile(new String(item[7].getBytes("Big5"),"ISO-8859-1"), fInputStream);
	    	            fInputStream.close();
	    	            if(move){
	    	              movetoftpservern++;
	    	              movetoftpserversize+=myFiles[k].length();
	    	              myFiles[k].delete();
	    	            } else{
	    	                uploadn++;
	    	                uploadsize+=myFiles[k].length();
	    	              }

                          }
                           else upchkfailedaftermatch++;
                        }
                      }
                      if(System.currentTimeMillis()-defaultsysstoptime>0) overtime=true;
                    }
                  if(overtime){
                        instrument.log("Warning: over the time limit, following workitems stopped.");
                            rtn=false;
                      break;
                  }   
                }
              } else {
                 instrument.log("error: 目錄: "+myDir+((myDir.length()==2 && myDir.indexOf(":")==1)? "\\":"")+",contents1=null, Pathname maby be wrong.");
                  rtn=false;
                }
              if(!firstDir) {
		instrument.log("before changeToParentDirectory(), workingdir="+(new String(ftpClient.printWorkingDirectory().getBytes("ISO-8859-1"),"Big5")));
              	ftpClient.changeToParentDirectory();
		instrument.log("after changeToParentDirectory(), workingdir="+(new String(ftpClient.printWorkingDirectory().getBytes("ISO-8859-1"),"Big5")));

              }
            } catch( Exception e ) {  
		try{
                 instrument.log("error ftpUpDir(\""+serverDir+"\",\""+myDir+"\",xxx,xxx,"+firstDir+","+move+"),workingdir="+(new String(ftpClient.printWorkingDirectory().getBytes("ISO-8859-1"),"Big5"))+",\r\n"+instrument.getStackTrace(e));
          	  } catch(Exception e2){
                     instrument.log("Exception: "+instrument.getStackTrace(e2));
                      e2.printStackTrace();
                  };
                  rtn=false;
                  e.printStackTrace(); 
              }
		try{
		 instrument.log("before ftpUpDir() return, workingdir="+(new String(ftpClient.printWorkingDirectory().getBytes("ISO-8859-1"),"Big5")));
		} catch(Exception e){
                              instrument.log("Exception: "+instrument.getStackTrace(e));
                              e.printStackTrace();
                 }
      return rtn;
  }
  

  public boolean ftpMkDirs(String serverDir,String newDir){
	boolean rtn=true;

  	try{
                if((serverDir==null || serverDir.length()<1) || (serverDir!=null && serverDir.length()>0 && ftpClient.changeWorkingDirectory((ftpMode.equalsIgnoreCase("UNIX")? "/"+ylib.replace((new String(serverDir.getBytes("Big5"),"ISO-8859-1")),"\\","/"):(new String(serverDir.getBytes("Big5"),"ISO-8859-1")))))){

                    if(!ftpClient.changeWorkingDirectory((ftpMode.equalsIgnoreCase("UNIX")? "/"+ylib.replace((new String((serverDir+(ftpMode.equalsIgnoreCase("UNIX")? "/":"\\")+newDir).getBytes("Big5"),"ISO-8859-1")),"\\","/"):(new String((serverDir+(ftpMode.equalsIgnoreCase("UNIX")? "/":"\\")+newDir).getBytes("Big5"),"ISO-8859-1"))))){
                      if(newDir.indexOf("\\")!=-1){
                        StringTokenizer st=new StringTokenizer(newDir,"\\");
                        while(st.hasMoreTokens()){
                          String tmp=st.nextToken();
                          if(!ftpClient.changeWorkingDirectory((ftpMode.equalsIgnoreCase("UNIX")? ylib.replace((new String(tmp.getBytes("Big5"),"ISO-8859-1")),"\\","/"):(new String(tmp.getBytes("Big5"),"ISO-8859-1"))))){
                            if(ftpClient.makeDirectory(new String(tmp.getBytes("Big5"),"ISO-8859-1"))==false){
                              instrument.log("Warning-1: 無法建立目錄:\""+tmp+"\" (working dir="+(new String(ftpClient.printWorkingDirectory().getBytes("ISO-8859-1"),"Big5"))+").");
                              rtn=false;
                              break;
                            } else {

                                if(!ftpClient.changeWorkingDirectory((ftpMode.equalsIgnoreCase("UNIX")? ylib.replace((new String(tmp.getBytes("Big5"),"ISO-8859-1")),"\\","/"):(new String(tmp.getBytes("Big5"),"ISO-8859-1"))))) {
                                  instrument.log("Warning-2: 無法chang 工作目錄到:\""+tmp+"\". (working dir="+(new String(ftpClient.printWorkingDirectory().getBytes("ISO-8859-1"),"Big5"))+")");
                        	  break;
                      	        }
                      	      }
                          }
                        }
                      } else if(newDir.indexOf("/")!=-1){
                          StringTokenizer st=new StringTokenizer(newDir,"/");
                          while(st.hasMoreTokens()){
                            String tmp=st.nextToken();
                            if(!ftpClient.changeWorkingDirectory((ftpMode.equalsIgnoreCase("UNIX")? ylib.replace((new String(tmp.getBytes("Big5"),"ISO-8859-1")),"\\","/"):(new String(tmp.getBytes("Big5"),"ISO-8859-1"))))){
                              if(ftpClient.makeDirectory(new String(tmp.getBytes("Big5"),"ISO-8859-1"))==false){
                                instrument.log("Warning-3: 無法建立目錄:\""+tmp+"\". (working dir="+(new String(ftpClient.printWorkingDirectory().getBytes("ISO-8859-1"),"Big5"))+")");
                                rtn=false;
                                break;
                              } else {

                          	      if(!ftpClient.changeWorkingDirectory((ftpMode.equalsIgnoreCase("UNIX")? ylib.replace((new String(tmp.getBytes("Big5"),"ISO-8859-1")),"\\","/"):(new String(tmp.getBytes("Big5"),"ISO-8859-1"))))) {
                                    instrument.log("Warning-4: 無法chang 工作目錄到:\""+tmp+"\". (working dir="+(new String(ftpClient.printWorkingDirectory().getBytes("ISO-8859-1"),"Big5"))+")");
                      	  	    break;
                      	          }
                                }
                            } 
                          }
        	      } else {
                          if(ftpClient.makeDirectory(new String(newDir.getBytes("Big5"),"ISO-8859-1"))==false){
                            instrument.log("Warning-5: 無法建立單一目錄:\""+newDir+"\". (serverDir3="+serverDir+")");
                            rtn=false;
                          }
                        }
                    } else {
                        instrument.log("Warning-6: 目錄:\""+newDir+"\"已存在,不必建立此目錄. (serverDir="+serverDir+")");
                      }
                } else {
                        instrument.log("Warning-7: 目錄:\""+serverDir+"\"不存在,無法建立其次目錄.");
                        rtn=false;
                  }

            } catch( Exception e ) {  
                  instrument.log("error ftpMkDirs(\""+serverDir+"\",\""+newDir+"\"):\r\n"+instrument.getStackTrace(e));
                  rtn=false;
                  e.printStackTrace(); 
              }
     return rtn;
  }

   public void setData(long time,String dataSrc,String data){

    CIDataClass dataClass=new CIDataClass(time,dataSrc,data);
    waitV.add(dataClass);
    if(isSleep) this.interrupt();
}
}