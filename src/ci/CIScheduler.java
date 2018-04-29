package ci;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Vector;
import java.util.regex.Pattern;
import y.base64.YB642D;
import y.ylib.OneVar;
import y.ylib.ylib;

public class CIScheduler {

    CrInstrument instrument;
    TreeMap scheduleTM=new TreeMap();
    public SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
    public Vector insertVector=new Vector();
    int scheduleArrSize=50;
    boolean previousResult=true;
    long processStartTime=System.currentTimeMillis(),ttlProcessTime=0,nextWakeupTime=0;
    String common[][]={{}}; 
    TimeThread timeThread;

    long sysStartTime=System.currentTimeMillis();
    String nextAName="",nextATitle="";

    public CIScheduler(CrInstrument instrument){
        this.instrument=instrument;
        init();
    }
    void init(){

        timeThread=new TimeThread();
        timeThread.start();

    }

  

  public void checkNextTime(String x,int mode){
    ScheduleItem item=(ScheduleItem)scheduleTM.get(x);
    if(item==null) return;
    long chkNext=100L*366L*24L*60L*60L*1000L,timeNow=System.currentTimeMillis();
    int y1,mon1,w1,d1,h1,min1,s1,y2,mon2,w2,d2,h2,min2,s2,
      y3,mon3,w3,d3,h3,min3,s3,y4,mon4,weekOne,d4,h4,min4,s4,
      d5,h5,min5,s5;
    Calendar canr=GregorianCalendar.getInstance(),canrNext=GregorianCalendar.getInstance();
    long wdhmsSpan[]={24*60*60*1000,24*60*60*1000,24*60*60*1000,24*60*60*1000,60*60*1000,60*1000,1000};

    if(item.itemArr[21].equals("2")){
      item.nextTime=timeNow+chkNext;
    } else {
        if(mode==1 && item.errorMode==1 && OneVar.check(item.itemArr[28],0)){
          item.nextTime+=item.errTimeSpan;
        } else {
            canr.setTime(new Date());
            y1=canr.get(Calendar.YEAR);
            mon1=canr.get(Calendar.MONTH);
            w1=canr.get(Calendar.DAY_OF_WEEK);
            d1=canr.get(Calendar.DATE);
            h1=canr.get(Calendar.HOUR_OF_DAY);
            min1=canr.get(Calendar.MINUTE);
            s1=canr.get(Calendar.SECOND);

            if(item.itemArr[7].length()>0) y2=Integer.parseInt(item.itemArr[7]); else y2=0;
            if(item.itemArr[8].length()>0) mon2=Integer.parseInt(item.itemArr[8])-1; else mon2=-1;
            if(item.itemArr[9].length()>0) {
              w2=Integer.parseInt(item.itemArr[9]);
              if(w2==2) w2=Calendar.MONDAY;
                else if(w2==3) w2=Calendar.TUESDAY;
                else if(w2==4) w2=Calendar.WEDNESDAY;
                else if(w2==5) w2=Calendar.THURSDAY;
                else if(w2==6) w2=Calendar.FRIDAY;
                else if(w2==7) w2=Calendar.SATURDAY;
                else if(w2==1) w2=Calendar.SUNDAY;
            } else w2=0;
            if(item.itemArr[10].length()>0) d2=Integer.parseInt(item.itemArr[10]); else d2=0;
            if(item.itemArr[11].length()>0) h2=Integer.parseInt(item.itemArr[11]); else h2=0;
            if(item.itemArr[12].length()>0) min2=Integer.parseInt(item.itemArr[12]); else min2=0;
            if(item.itemArr[13].length()>0) s2=Integer.parseInt(item.itemArr[13]); else s2=0;
            if(item.itemArr[7].length()>0 && y2>0) item.start_FirstCode=0;
              else if(item.itemArr[8].length()>0 && mon2>-1) item.start_FirstCode=1;
              else if(item.itemArr[9].length()>0 && w2>0) item.start_FirstCode=2; 
              else if(item.itemArr[10].length()>0 && d2>0) item.start_FirstCode=3;
              else if(item.itemArr[11].length()>0) item.start_FirstCode=4;
              else if(item.itemArr[12].length()>0) item.start_FirstCode=5;
              else if(item.itemArr[13].length()>0) item.start_FirstCode=6;
              else  item.start_FirstCode=7;

            if(item.itemArr[14].length()>0) y3=Integer.parseInt(item.itemArr[14]); else y3=0;
            if(item.itemArr[15].length()>0) mon3=Integer.parseInt(item.itemArr[15])-1; else mon3=-1;
            if(item.itemArr[16].length()>0) w3=Integer.parseInt(item.itemArr[16]); else w3=0;
            if(item.itemArr[17].length()>0) d3=Integer.parseInt(item.itemArr[17]); else d3=0;
            if(item.itemArr[18].length()>0) h3=Integer.parseInt(item.itemArr[18]); else h3=0;
            if(item.itemArr[19].length()>0) min3=Integer.parseInt(item.itemArr[19]); else min3=0;
            if(item.itemArr[20].length()>0) s3=Integer.parseInt(item.itemArr[20]); else s3=0;
            if(item.itemArr[14].length()>0 && y3>0) item.stop_FirstCode=0;
              else if(item.itemArr[15].length()>0 && mon3>-1) item.stop_FirstCode=1;
              else if(item.itemArr[16].length()>0 && w3>0) item.stop_FirstCode=2;
              else if(item.itemArr[17].length()>0 && d3>0) item.stop_FirstCode=3;
              else if(item.itemArr[18].length()>0) item.stop_FirstCode=4;
              else if(item.itemArr[19].length()>0) item.stop_FirstCode=5;
              else if(item.itemArr[20].length()>0) item.stop_FirstCode=6;
              else  item.stop_FirstCode=7;
            if(y3==0) y3=y1;
            if(mon3==-1) mon3=mon1; 
            if(d3==0) d3=d1;        

            y4=(item.itemArr[37].length()>3? Integer.parseInt(item.itemArr[37]):y1);
            if(item.itemArr[22].length()>0) mon4=Integer.parseInt(item.itemArr[22])-1; else mon4=-1;

            if(item.itemArr[23].length()>0) weekOne=Integer.parseInt(item.itemArr[23]); else weekOne=0;
            

            if(item.itemArr[24].length()>0) d4=Integer.parseInt(item.itemArr[24]); else d4=0;
            if(item.itemArr[25].length()>0) h4=Integer.parseInt(item.itemArr[25]); else h4=0;
            if(item.itemArr[26].length()>0) min4=Integer.parseInt(item.itemArr[26]); else min4=0;
            if(item.itemArr[27].length()>0) s4=Integer.parseInt(item.itemArr[27]); else s4=0;
            if(item.itemArr[22].length()>0 && mon4>-1) item.performcycle_FirstCode=1;
              else if(item.itemArr[23].length()>0 && weekOne>0) item.performcycle_FirstCode=2;
              else if(item.itemArr[24].length()>0 && d4>0) item.performcycle_FirstCode=3;
              else if(item.itemArr[25].length()>0) item.performcycle_FirstCode=4;
              else if(item.itemArr[26].length()>0) item.performcycle_FirstCode=5;
              else if(item.itemArr[27].length()>0) item.performcycle_FirstCode=6;
              else  item.performcycle_FirstCode=7;

            if(item.itemArr[29].length()>0) d5=Integer.parseInt(item.itemArr[29]); else d5=0;
            if(item.itemArr[30].length()>0) h5=Integer.parseInt(item.itemArr[30]); else h5=0;
            if(item.itemArr[31].length()>0) min5=Integer.parseInt(item.itemArr[31]); else min5=0;
            if(item.itemArr[32].length()>0) s5=Integer.parseInt(item.itemArr[32]); else s5=0;
            item.errTimeSpan=(long)d5*wdhmsSpan[3]+(long)h5*wdhmsSpan[4]+(long)min5*wdhmsSpan[5]+(long)s5*wdhmsSpan[6];
            if(item.itemArr[29].length()>0 && d5>0) item.errCycle_FirstCode=3;
              else if(item.itemArr[30].length()>0) item.errCycle_FirstCode=4;
              else if(item.itemArr[31].length()>0) item.errCycle_FirstCode=5;
              else if(item.itemArr[32].length()>0) item.errCycle_FirstCode=6;
              else  item.errCycle_FirstCode=7;
            if(mode==0 || mode==2){
              if((item.itemArr[7].length()<1 || item.itemArr[7].equals("0")) && (item.itemArr[8].length()<1 || item.itemArr[8].equals("0")) && (item.itemArr[9].length()<1 || item.itemArr[9].equals("0")) && (item.itemArr[10].length()<1 || item.itemArr[10].equals("0")) && (item.itemArr[11].length()<1) && (item.itemArr[12].length()<1) && (item.itemArr[13].length()<1))
                item.startTime=timeNow;
                else if(item.start_FirstCode==0) {canr.set(y2,mon2,d2,h2,min2,s2); item.startTime=canr.getTime().getTime();}
                else item.startTime=timeNow;
              item.startTime=item.startTime-item.startTime%1000L;
              if((item.itemArr[14].length()<1 || item.itemArr[14].equals("0")) && (item.itemArr[15].length()<1 || item.itemArr[15].equals("0")) && (item.itemArr[16].length()<1 || item.itemArr[16].equals("0")) && (item.itemArr[17].length()<1 || item.itemArr[17].equals("0")) && (item.itemArr[18].length()<1) && (item.itemArr[19].length()<1) && (item.itemArr[20].length()<1))
                item.stopTime=(timeNow>item.startTime? timeNow:item.startTime)+chkNext;
                else if(item.stop_FirstCode==0) {canr.set(y3,mon3,d3,h3,min3,s3); item.stopTime=canr.getTime().getTime();}
                else item.stopTime=(timeNow>item.startTime? timeNow:item.startTime)+chkNext;
            }
            if(item.itemArr[21].equals("0")){
              item.timeSpan=(long)d4*wdhmsSpan[3]+(long)h4*wdhmsSpan[4]+(long)min4*wdhmsSpan[5]+(long)s4*wdhmsSpan[6];
              if((item.itemArr[7].length()<1 || item.itemArr[7].equals("0")) && (item.itemArr[8].length()<1 || item.itemArr[8].equals("0")) && (item.itemArr[9].length()<1 || item.itemArr[9].equals("0")) && (item.itemArr[10].length()<1 || item.itemArr[10].equals("0")) && (item.itemArr[11].length()<1) && (item.itemArr[12].length()<1) && (item.itemArr[13].length()<1)){

                switch(mode){
                  case 0:
                  case 2:
                    if(OneVar.check(item.itemArr[28],3)) item.nextTime=timeNow;
                      else item.nextTime=timeNow+item.timeSpan;
                    break;
                  case 1:
                    if(item.errorMode==1 && OneVar.check(item.itemArr[28],0)) {
                        item.nextTime+=item.errTimeSpan;
                        while(item.nextTime<timeNow)  item.nextTime+=item.errTimeSpan;
                    }
                    else {
                        if(OneVar.check(item.itemArr[28],3)) item.nextTime= item.startTime;

                        while(item.nextTime<timeNow)  item.nextTime+=item.timeSpan;
                    }
                    break;
                }
              } else {
                  switch(mode){
                    case 0:
                    case 2:
                      if(OneVar.check(item.itemArr[28],3) && mode!=2) item.nextTime=timeNow;
                        else {
                          item.nextTime=item.startTime;

                          while(item.nextTime<timeNow){
                            item.nextTime+=item.timeSpan;
                          }
                        }
                      break;
                    case 1:
                      if(item.errorMode==1 && OneVar.check(item.itemArr[28],0)) {
                         while(item.nextTime<=timeNow)  item.nextTime+=item.errTimeSpan;
                      }
                        else{

                          if(OneVar.check(item.itemArr[28],3)) item.nextTime= item.startTime;
                          while(item.nextTime<=timeNow) item.nextTime+=item.timeSpan;
                        }
                      break;

                  }
                }
              item.nextTime=item.nextTime-item.nextTime%1000L;
              if((item.stop_FirstCode!=7 && item.nextTime>item.stopTime) || item.nextTime-timeNow>chkNext) item.currOnOff=0;
            } else if(item.itemArr[21].equals("1")){
                switch(mode){
                  case 0:
                  case 2:
                    if(OneVar.check(item.itemArr[28],3)) item.nextTime=timeNow;
                    else item.nextTime=findTime(timeNow,y4,mon4,weekOne,d4,h4,min4,s4,item.performcycle_FirstCode,true);     
                    while(item.nextTime<item.startTime){
                      item.nextTime=findTime(item.nextTime,y4,mon4,weekOne,d4,h4,min4,s4,item.performcycle_FirstCode,true);
                    }
                    break;
                  case 1:
                    item.nextTime=findTime(item.nextTime,y4,mon4,weekOne,d4,h4,min4,s4,item.performcycle_FirstCode,true);
                    break;
                }
                item.nextTime=item.nextTime-item.nextTime%1000L;
                if((item.stop_FirstCode!=7 && item.nextTime>item.stopTime) || item.nextTime-timeNow>chkNext) item.currOnOff=0;
              }
              else {

                canrNext.set(y4,mon4,d4,h4,min4,s4);
                item.nextTime=canrNext.getTime().getTime();
                if(item.nextTime<System.currentTimeMillis()) item.currOnOff=0;
              }
          }
      }
        

    scheduleTM.put(x, item);
  }  
      

  public long findTime(long time1,int y7, int mon7,int weekOnevar, int d7, int h7, int min7,int s7, int perform_StartCode, boolean forward){

    long wdhmsSpan[]={24*60*60*1000,24*60*60*1000,24*60*60*1000,24*60*60*1000,60*60*1000,60*1000,1000};
    int y8,mon8,w8Name,w8Value=0,d8,h8,min8,s8;
    long nextTime=0;
    Calendar canr=GregorianCalendar.getInstance(),canrNext=GregorianCalendar.getInstance();
    canrNext.setTime(new Date(time1));
    y8=canr.get(Calendar.YEAR);
    mon8=canr.get(Calendar.MONTH);

    d8=canr.get(Calendar.DATE);
    h8=canr.get(Calendar.HOUR_OF_DAY);
    min8=canr.get(Calendar.MINUTE);
    s8=canr.get(Calendar.SECOND);
    if(perform_StartCode==0) {
        y8=y7;  mon8=mon7; d8=d7; h8=h7; min8=min7; s8=s7;
    } else if(perform_StartCode==1) {
          mon8=mon7; d8=d7; h8=h7; min8=min7; s8=s7;
        } else if(perform_StartCode==2) {
            

            h8=h7; min8=min7; s8=s7;
          } else if(perform_StartCode==3) {
              d8=d7; h8=h7; min8=min7; s8=s7;
            } else if(perform_StartCode==4) {
                h8=h7; min8=min7; s8=s7;
              } else if(perform_StartCode==5) {
                  min8=min7; s8=s7;
                } else if(perform_StartCode==6) {
                    s8=s7;
                  }
    canrNext.set(y8,mon8,d8,h8,min8,s8);
    nextTime=canrNext.getTime().getTime();
    if(perform_StartCode==0) return  nextTime;
    if(perform_StartCode==7) return  (forward? (nextTime>time1? nextTime:time1):(nextTime>time1? time1:nextTime));
    if(perform_StartCode==2){
      while(!chkWeek(canrNext,weekOnevar)){
        if(forward==true) canrNext.setTime(new Date(canrNext.getTime().getTime()+1000*60*60*24));
        if(forward==false)canrNext.setTime(new Date(canrNext.getTime().getTime()-1000*60*60*24));
      }
    }
    nextTime=canrNext.getTime().getTime();
    switch(perform_StartCode){
      case 1:
         if(forward==true){
           while(nextTime<=time1){ 
               canrNext.add(Calendar.YEAR,1);
               nextTime=canrNext.getTime().getTime();
           }
         }
          else if(forward==false) {
            while(nextTime>=time1) {
               canrNext.add(Calendar.YEAR,-1);
               nextTime=canrNext.getTime().getTime();
            }
          }
        break;
      case 2:
        if(forward==true){
          while(nextTime<=time1 || !chkWeek(nextTime,weekOnevar)) {
              nextTime+=wdhmsSpan[perform_StartCode];
          }
        }
        else if(forward==false){ 
          while(nextTime>=time1 || chkWeek(nextTime,weekOnevar)) {nextTime-=wdhmsSpan[perform_StartCode];}
        }
        break;
      case 3:
         if(forward==true){
           while(nextTime<=time1){ 
               canrNext.add(Calendar.MONTH,1);
               nextTime=canrNext.getTime().getTime();
           }
         }
          else if(forward==false) {
            while(nextTime>=time1) {
               canrNext.add(Calendar.MONTH,-1);
               nextTime=canrNext.getTime().getTime();
            }
          }
        break;
      case 4:
      case 5:
      case 6:
         if(forward==true){
           while(nextTime<=time1){ nextTime+=wdhmsSpan[perform_StartCode-1];}

         }
          else if(forward==false) {
            while(nextTime>=time1) {nextTime-=wdhmsSpan[perform_StartCode-1];}

          }
        break;
     }
    return nextTime;
  }
  public boolean chkWeek(long time,int weekOnevar){
      Calendar cd=Calendar.getInstance();
      cd.setTime(new Date(time));
      return chkWeek(cd,weekOnevar);
  }
  public boolean chkWeek(Calendar canr2,int weekOnevar){
    int w=canr2.get(Calendar.DAY_OF_WEEK),wValue=0;
     if(w==Calendar.MONDAY) wValue=2;
                else if(w==Calendar.TUESDAY) wValue=3;
                else if(w==Calendar.WEDNESDAY) wValue=4;
                else if(w==Calendar.THURSDAY) wValue=5;
                else if(w==Calendar.FRIDAY) wValue=6;
                else if(w==Calendar.SATURDAY) wValue=7;
                else if(w==Calendar.SUNDAY) wValue=1;
       return OneVar.check(weekOnevar,wValue);
  }
  public TreeMap getSchedule(){
      return scheduleTM;
  }
  public void setScheduleItem(String evKey,String items){
      scheduleTM.put(evKey, new ScheduleItem(items));
      spanAndFirstNextTime();
      if(timeThread.isSleep) timeThread.interrupt();
  }
    public void setSchedule(TreeMap tm){
      this.scheduleTM=tm;
      spanAndFirstNextTime();
      if(timeThread.isSleep) timeThread.interrupt();
  }

  

    public void updateFromConditionItem(String condiKey){
            Iterator it=instrument.eventTM.values().iterator();
            boolean found=false;
            for(;it.hasNext();){
              String evt[]=ylib.csvlinetoarray((String)it.next());
              int cCnt=Integer.parseInt(evt[1]);
              int aCnt=Integer.parseInt(evt[2]);
              TreeMap actionCodeTM=new TreeMap();
              for(int i=0;i<cCnt;i++){
                String cId=evt[3+i];
                if(condiKey.equals(cId) && instrument.conditionTM.get(cId)!=null){
                  String cond[]=ylib.csvlinetoarray((String)instrument.conditionTM.get(cId));
                  found=true;

                  cond[18]=cond[38];
                  cond[19]=cond[39];
                  cond[22]=cond[42];

                  String title=cond[2];
                  cond[0]=evt[0];
                  cond[1]=title;
                  cond[2]="1";
                  scheduleTM.put(evt[0], new ScheduleItem(cond));
                }
              }
            }
            if(found){
              spanAndFirstNextTime();
              if(timeThread.isSleep) timeThread.interrupt();
            }
    }

        public void updateFromEvent(){
            scheduleTM.clear();
            Iterator it=instrument.eventTM.values().iterator();
            boolean found=false;
            for(;it.hasNext();){
              String evt[]=ylib.csvlinetoarray((String)it.next());
              int cCnt=Integer.parseInt(evt[1]);
              int aCnt=Integer.parseInt(evt[2]);
              TreeMap actionCodeTM=new TreeMap();
              for(int i=0;i<cCnt;i++){
                String cId=evt[3+i];
                if(instrument.conditionTM.get(cId)!=null){
                  String cond[]=ylib.csvlinetoarray((String)instrument.conditionTM.get(cId));
                  if(cond[2].equalsIgnoreCase("Schedule time")){
                  found=true;

                  cond[18]=cond[38];
                  cond[19]=cond[39];
                  cond[22]=cond[42];

                  String title=cond[2];
                  cond[0]=evt[0];
                  cond[1]=title;
                  cond[2]="1";
                  scheduleTM.put(evt[0], new ScheduleItem(cond));
                  }
                }
              }
            }
            if(found){
              spanAndFirstNextTime();
              if(timeThread.isSleep) timeThread.interrupt();
            }
    }

    public void spanAndFirstNextTime(){
    if(scheduleTM.size()>0){
      Iterator it=scheduleTM.keySet().iterator();
      for(;it.hasNext();){

        String key=(String)it.next();
        checkNextTime(key,0);
      }
    }
  }
     

  public void handleResultForTrigger(String scheduleItemId,boolean result){
    if(scheduleItemId!=null && scheduleItemId.length()>0 && !scheduleItemId.equals("0")){
      if(scheduleTM.size()>0){
        TreeMap scheduleTMClone=(TreeMap) scheduleTM.clone();
        Iterator it=scheduleTM.values().iterator();
        for(;it.hasNext();){
          ScheduleItem item=(ScheduleItem)it.next();
         if(item.itemArr[34].equals(scheduleItemId)){
           if(result){
             if(item.itemArr[33].length()>0 && !item.itemArr[33].equals("0")){
               Iterator it2=scheduleTMClone.values().iterator();
               for(;it2.hasNext();){
                 ScheduleItem item2=(ScheduleItem)it2.next();
                 if(item.itemArr[33].equals(item2.itemArr[34])){
                     insertVector.add(item2);
                     if(timeThread.isSleep)  timeThread.interrupt();
                     break;
                 }
               }
             }

          } else {
             if(item.itemArr[38].length()>0 && !item.itemArr[38].equals("0")){
               Iterator it2=scheduleTMClone.values().iterator();
               for(;it2.hasNext();){
                 ScheduleItem item2=(ScheduleItem)it2.next();
                 if(item.itemArr[38].equals(item2.itemArr[34])){
                     insertVector.add(item2);
                     if(timeThread.isSleep)  timeThread.interrupt();
                     break;
                 }
               }
             }
          }
          break;
         }
        }
      }
    }
  }
  void addScheduleItem(String data[]){
      if(data.length>= (scheduleArrSize-3)){
         ScheduleItem item=new ScheduleItem(data);
         int key=Integer.parseInt(item.itemArr[3]);
         while(true){
          if(scheduleTM.get(new Integer(key))==null) {scheduleTM.put(key,item); break;}
          item.itemArr[3]=""+(Integer.parseInt(item.itemArr[3])+1);
         }

         TreeMap tm=new TreeMap();
         Iterator it=scheduleTM.values().iterator();
         int inx=0;
         for(;it.hasNext();){
            ScheduleItem item2=(ScheduleItem)it.next();
            int key2=inx * 10;
            item2.itemArr[3]= ""+key2;
            tm.put(key2,item2);
         }
         scheduleTM=tm;
      } else System.out.println("Warning: add item failed, because data.length < "+(scheduleArrSize-3));
  }
  

    public static final Pattern Num_PATTERN= Pattern.compile("^-?[0-9]+(\\.[0-9]+)?$");
    public static boolean isNumeric(String s){
          return (s==null? false: Num_PATTERN.matcher(s).matches());
    }

   class TimeThread extends Thread{
        public boolean isSleep=false;
        public void run(){
            while(true){
              long timeNow=System.currentTimeMillis();
              int activeQuantity=0;
              try{
                if(scheduleTM.size()>0){
                  Iterator it=scheduleTM.keySet().iterator();
                  for(;it.hasNext();){
                    String key=(String)it.next();
                    ScheduleItem item=(ScheduleItem)scheduleTM.get(key);
                    item.errorMode=0;  

                    while(insertVector.size()>0){
                      ScheduleItem item2=(ScheduleItem)insertVector.get(0);
                      if(item2.itemArr[4].trim().equals("1")){

                        instrument.eventThread.setStatus(instrument.wn.w.getGNS(1),item2.itemArr[0],92);
                      }
                      insertVector.remove(0);
                    }
                    if(item.currOnOff==1 && item.itemArr[0].trim().toLowerCase().indexOf("rem ")==-1 && !OneVar.check(item.itemArr[28],6)){
                    if(item.nextTime<=timeNow || item.itemArr[21].equals("2")) {

                      instrument.eventThread.setStatus(instrument.wn.w.getGNS(1),item.itemArr[0],92);
                      item.lastTime=System.currentTimeMillis();
                      if(OneVar.check(item.itemArr[28],2)) {
            	        item.currOnOff=0; 

                      } else {

                        checkNextTime(key,1); 
                        if(item.nextTime <=System.currentTimeMillis() && item.itemArr[21].equals("3")){
                          item.currOnOff=0; 

                        } else {
                          activeQuantity++;

                        }
                       }
                     }
            if(item.currOnOff==1) activeQuantity++;
          }
          scheduleTM.put(key, item);
        }
       }
         if(activeQuantity==0) {
             isSleep=true;
             sleep(1000L * 3600L * 24L * 365L);
         }
         isSleep=false;

        timeNow=System.currentTimeMillis();
        long minLong=0;
        if(scheduleTM.size()>0){
         Iterator it=scheduleTM.values().iterator();
        for(;it.hasNext();){
          ScheduleItem item=(ScheduleItem)it.next();
          if(item.currOnOff==1) { 
            minLong=item.nextTime; 
            nextAName=item.itemArr[0]; 
            nextATitle=item.itemArr[1];
       	    break; 
          }
        }
        it=scheduleTM.values().iterator();
        for(;it.hasNext();){
          ScheduleItem item=(ScheduleItem)it.next();
          if(item.currOnOff==1 && item.nextTime<minLong) {
            minLong=item.nextTime;
            nextAName=item.itemArr[0]; 
            nextATitle=item.itemArr[1];
          }
        }
        nextWakeupTime=minLong;
        if(nextWakeupTime<timeNow){
          int x2=0;
          String valueCode[]=new String[scheduleTM.size()];
          nextWakeupTime=timeNow;
          for(int x1=0;x1<scheduleTM.size();x1++) valueCode[x1]="";
          it=scheduleTM.keySet().iterator();
          for(;it.hasNext();){
              String key=(String)it.next();
              ScheduleItem item=(ScheduleItem)scheduleTM.get(key);
          	if(item.currOnOff==1 && item.nextTime<timeNow) {
          		valueCode[x2]=key; x2++;
          	}
          }
          ScheduleItem item=(ScheduleItem)scheduleTM.get(valueCode[0]);
          minLong=item.timeSpan;
          nextAName=item.itemArr[0]; 
          nextATitle=item.itemArr[1];
          for(int x1=1;x1<x2;x1++) {
            item=(ScheduleItem)scheduleTM.get(valueCode[x1]);
            if(minLong>item.timeSpan) {
            	minLong=item.timeSpan;
            	 nextAName=item.itemArr[0]; 
            	 nextATitle=item.itemArr[1];
            }
          }
          nextWakeupTime=nextWakeupTime+minLong;
        }
        it=scheduleTM.values().iterator();
        for(;it.hasNext();){
          ScheduleItem item=(ScheduleItem)it.next();
          if(nextWakeupTime>item.nextTime && item.nextTime>timeNow && item.currOnOff==1) 
            {nextWakeupTime=item.nextTime; nextAName=item.itemArr[0]; nextATitle=item.itemArr[1];}
        }
        Date date3= new Date(nextWakeupTime);
        String dateNext = formatter.format(date3);
        timeNow=System.currentTimeMillis();
        String dateNow = formatter.format(new Date(timeNow));

        long processStopTime=System.currentTimeMillis();
        ttlProcessTime+=processStopTime-processStartTime;
        if(nextWakeupTime-timeNow>0){
            Date date2= new Date(nextWakeupTime);

            isSleep=true;
            sleep(nextWakeupTime-timeNow);
          }
          isSleep=false;

          timeNow=System.currentTimeMillis();
          if(nextWakeupTime-timeNow>0){
            isSleep=true;
            sleep(nextWakeupTime-timeNow);
          }
         }
              }catch(InterruptedException e){

              }
              isSleep=false;
            }
        }
    }
   

  class ScheduleItem{
    String[] itemArr;
    long nextTime;
    long stopTime;
    long startTime;
    long lastTime;
    long timeSpan;
    long errTimeSpan;
    int start_FirstCode;
    int performcycle_FirstCode;
    int stop_FirstCode;
    int errCycle_FirstCode;
    int lastResult;
    int errorMode;
    int currOnOff;
    String common[]=new String[3];
    public ScheduleItem(String items){
            this.itemArr=ylib.csvlinetoarray(items);
            currOnOff=Integer.parseInt(this.itemArr[4]);
            if(!isNumeric(this.itemArr[3])) this.itemArr[3]="0";
    }
    public ScheduleItem(String item[]){
            this.itemArr=item;
            currOnOff=Integer.parseInt(this.itemArr[4]);
            if(!isNumeric(this.itemArr[3])) this.itemArr[3]="0";
    }
  }
}