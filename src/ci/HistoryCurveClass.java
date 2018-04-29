
package ci;

import golib.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeMap;
import javax.swing.SwingUtilities;
import y.ylib.OneVar;

/**
 *
 * @author peter
 */
public class HistoryCurveClass implements CustomizeClass{
  

    int last_pressed_x=0,last_pressed_y=0;
    long last_pressed_stat_long0=0,last_pressed_stat_long6=0,last_pressed_cfg_long1=0,last_pressed_cfg_long2=0,last_pressed_cfg_long3=0;
    double last_pressed_stat_double6=0,last_pressed_stat_double7=0,last_pressed_stat_double8=0,last_pressed_stat_double9=0;
    NumberFormat numberFormat = new DecimalFormat("############0.0################################################");
  public void beforePlotData(MultiPanel2 panel2,Graphics2D g2,Config cfg,Status stat,String para[],double bx, double by,double width,double height,double x3,double y3,double width3,double height3){

  }

  public void afterPlotData(MultiPanel2 panel2,Graphics2D g2,Config cfg,Status stat,String para[],double bx, double by,double width,double height,double x3,double y3,double width3,double height3){

  }

  public void beforeData(MultiPanel2 panel2,Graphics2D g2,Config cfg,Status status,String para[],double x0,double y0,double width, double height,double x3,double y3,double w3,double h3){
  }

  public void afterData(MultiPanel2 panel2,Graphics2D g2,Config cfg,Status status,String para[],double x0,double y0,double width, double height,double x3,double y3,double w3,double h3){
    }
  

  public void formMouseMoved(MultiPanel2 panel2,Graphics2D g2,int inx,Config cfg,Status stat,String para[],double bx,double by,double width, double height,double x3,double y3,double width3,double height3,java.awt.event.MouseEvent evt){
      int x=evt.getX();
      int y=evt.getY();

      if(x>= (int)x3 && x<=(int)(x3+width3) && y>=(int)y3 && y<= (int)(y3+height3)){
       if(cfg==null || stat==null) {System.out.println("HistoryCurveClss.formMouseMoved():cfg==null? "+(cfg==null)+", stat==null? "+(stat==null)); return;}
       TreeMap config2s=panel2.configArr[inx];
       Config config2=(Config)config2s.get("0");
          config2.intValue[110]=2;
          config2.intValue[111]=x;
          config2.intValue[112]=(int)y3;
          config2.intValue[113]=x;
          config2.intValue[114]=(int)(y3+height3);
          config2.intValue[115]=1;
          config2.intValue[116]=1;
          config2.c[70]=Color.ORANGE;

         long tValueN=cfg.longValue[1]* 3600000L + cfg.longValue[2]*60000L + cfg.longValue[3]*1000L;
         long time1=stat.longValue[0],time2=time1+tValueN;
         long targetTime=time1+ ((long)(((double)x-x3)/width3 * ((double)tValueN))),foundTargetTime=0;
         long upperOne=0,lowerOne=0;
         String srcKey="";
         long tolerate=Math.round(((double)tValueN)/width3) * 2;
         TreeMap datumTM=panel2.datumArr[0];
         String data[]=new String[datumTM.size()];
         boolean isRightAxis[]=new boolean[datumTM.size()];
         Color color[]=new Color[datumTM.size()];
         if(data.length>0){
           for(int i=0;i<data.length;i++) data[i]="";
         Iterator it=datumTM.keySet().iterator();
         boolean foundExect=false;

         for(;it.hasNext();){
             srcKey=(String)it.next();
             TreeMap dataTM=(TreeMap)datumTM.get(srcKey);
             Iterator it2=dataTM.keySet().iterator();
             for(;it2.hasNext();){
             long timeKey=((Long)it2.next()).longValue();
             if(targetTime==timeKey){
                 foundExect=true;
                 foundTargetTime=timeKey;
                 break;
             } else if(targetTime>timeKey){
                 lowerOne=timeKey;
             } else if(targetTime<timeKey){
                 upperOne=timeKey;
                 break;
             }
             if(foundExect) break;
             }
           }

           if(foundTargetTime==0){
             if(upperOne>0 && lowerOne>0){
               if((upperOne-targetTime)<=(targetTime-lowerOne) && (upperOne-targetTime)<=tolerate) foundTargetTime=upperOne;
               else if((targetTime-lowerOne)<=tolerate) foundTargetTime=lowerOne;
             } else if(upperOne>0 && (upperOne-targetTime)<=tolerate){
                 foundTargetTime=upperOne;
             } else if(lowerOne>0 && (targetTime-lowerOne)<=tolerate){
                 foundTargetTime=lowerOne;
             }

           }

           if(foundTargetTime>0){
             int inx2=0;
             it=datumTM.keySet().iterator();
             for(;it.hasNext();inx2++){
               srcKey=(String)it.next();
               TreeMap dataTM=(TreeMap)datumTM.get(srcKey);
               if(dataTM.get(foundTargetTime)!=null) {
                   Config cfgTMP=(Config)config2s.get(srcKey);
                   color[inx2]=cfgTMP.c[1];
                   if(OneVar.check(cfgTMP.longValue[4],28)) isRightAxis[inx2]=true; else isRightAxis[inx2]=false;
                   data[inx2]=numberFormat.format(((Double)dataTM.get(foundTargetTime)).doubleValue());
               }
             }
           }

           for(int i=0;i<data.length && i<13;i++){
               if(data[i].length()>0){
                 double v=Double.parseDouble(data[i]);
                 int x2=x+2,y2=(int)(y3+height3*((stat.doubleValue[6]-v)/(stat.doubleValue[6]-stat.doubleValue[7])))-10;
                 if(isRightAxis[i]) y2=(int)(y3+height3*((stat.doubleValue[8]-v)/(stat.doubleValue[8]-stat.doubleValue[9])))-10;
                 if((x2)<((int)x3)) x2= ((int)(x3+2.0));
                 if((x2)>((int)(x3+width3))-32) x2= ((int)(x3+width3))-32;
                 if(y2< ((int)y3)) y2=(int)(y3+15.0);
                 if(y2> ((int)(y3+height3))) y2=(int)(y3+height3-15.0);
                 config2.intValue[120+i*10]=1;
                 config2.intValue[121+i*10]=x2;
                 config2.intValue[122+i*10]=y2;
                 config2.intValue[124+i*10]=0;
                 config2.conf2[78+i*10]=data[i];
                 config2.c[71+i]=color[i];
               } else config2.intValue[120+i*10]=0;
           }
         }

          config2s.put("0", config2);
          if(panel2.panels.length>inx) panel2.setConfig(inx, config2s);
          panel2.repaint();

      } else {
          TreeMap config2s=panel2.configArr[inx];
          Config config2=(Config)config2s.get("0");
          if(config2.intValue[110]!=0){
            config2.intValue[110]=0;
            config2s.put("0", config2);
            if(panel2.panels.length>inx) panel2.setConfig(inx, config2s);
            panel2.repaint();
          }
      }
  }
  public void formMousePressed(MultiPanel2 panel2,Graphics2D g2,int inx,Config cfg,Status stat,String para[],double bx,double by,double width, double height,double x3,double y3,double width3,double height3,java.awt.event.MouseEvent evt){
      int x=evt.getX();
      int y=evt.getY();
      if(x>= (int)x3 && x<=(int)(x3+width3) && y>=(int)y3 && y<= (int)(y3+height3)){
       if(cfg==null || stat==null) {System.out.println("HistoryCurveClss.formMousePressed():cfg==null? "+(cfg==null)+", stat==null? "+(stat==null)); return;}
      last_pressed_x=evt.getX();
      last_pressed_y=evt.getY();
      last_pressed_stat_long0=stat.longValue[0];
      last_pressed_stat_long6=stat.longValue[6];
      last_pressed_cfg_long1=cfg.longValue[1];
      last_pressed_cfg_long2=cfg.longValue[2];
      last_pressed_cfg_long3=cfg.longValue[3];
      last_pressed_stat_double6=stat.doubleValue[6];
      last_pressed_stat_double7=stat.doubleValue[7];
      last_pressed_stat_double8=stat.doubleValue[8];
      last_pressed_stat_double9=stat.doubleValue[9];

      }
  }
  public void formMouseReleased(MultiPanel2 panel2,Graphics2D g2,int inx,Config cfg,Status stat,String para[],double bx,double by,double width, double height,double x3,double y3,double width3,double height3,java.awt.event.MouseEvent evt){

  }
  public void formMouseClicked(MultiPanel2 panel2,Graphics2D g2,int inx,Config cfg,Status stat,String para[],double bx,double by,double width, double height,double x3,double y3,double width3,double height3,java.awt.event.MouseEvent evt){
      if (SwingUtilities.isRightMouseButton(evt)){

          } else {

          }
  }
  public void formMouseDragged(MultiPanel2 panel2,Graphics2D g2,int inx,Config cfg,Status stat,String para[],double x0,double y0,double width, double height,double x3,double y3,double width3,double height3,MouseEvent evt){
      int x = evt.getX(), y = evt.getY();
      if(x>= (int)x3 && x<=(int)(x3+width3) && y>=(int)y3 && y<= (int)(y3+height3)){

       if(cfg==null || stat==null) {System.out.println("HistoryCurveClss.formMouseDragged():cfg==null? "+(cfg==null)+", stat==null? "+(stat==null)); return;}
      long newStatLong0=0,newStatLong6=0;
      double newStatDouble6=0,newStatDouble7=0,newStatDouble8=0,newStatDouble9=0;
      long tValueN=cfg.longValue[1]* 3600000L + cfg.longValue[2]*60000L + cfg.longValue[3]*1000L;

      newStatLong0=last_pressed_stat_long0-(long)(((double)(x-last_pressed_x))/width3*((double)tValueN));
      newStatLong6=newStatLong0+tValueN;
      newStatDouble6=last_pressed_stat_double6+((double)(y-last_pressed_y))/height3*(last_pressed_stat_double6-last_pressed_stat_double7);
      newStatDouble7=newStatDouble6-(last_pressed_stat_double6-last_pressed_stat_double7);
      newStatDouble8=last_pressed_stat_double8+((double)(y-last_pressed_y))/height3*(last_pressed_stat_double8-last_pressed_stat_double9);
      newStatDouble9=newStatDouble8-(last_pressed_stat_double8-last_pressed_stat_double9);
      stat.longValue[0]=newStatLong0;
      stat.longValue[6]=newStatLong6;
      stat.doubleValue[6]=newStatDouble6;
      stat.doubleValue[7]=newStatDouble7;
      stat.doubleValue[8]=newStatDouble8;
      stat.doubleValue[9]=newStatDouble9;

          cfg.intValue[110]=2;
          cfg.intValue[111]=x;
          cfg.intValue[112]=(int)y3;
          cfg.intValue[113]=x;
          cfg.intValue[114]=(int)(y3+height3);
          cfg.intValue[115]=1;
          cfg.intValue[116]=1;
          cfg.c[70]=Color.ORANGE;

         TreeMap config2s=panel2.configArr[inx];
         long time1=stat.longValue[0],time2=time1+tValueN;
         long targetTime=time1+ ((long)(((double)x-x3)/width3 * ((double)tValueN))),foundTargetTime=0;
         long upperOne=0,lowerOne=0;
         String srcKey="";
         long tolerate=Math.round(((double)tValueN)/width3) * 2;
         TreeMap datumTM=panel2.datumArr[0];
         String data[]=new String[datumTM.size()];
         boolean isRightAxis[]=new boolean[datumTM.size()];
         Color color[]=new Color[datumTM.size()];
         if(data.length>0){
           for(int i=0;i<data.length;i++) data[i]="";
         Iterator it=datumTM.keySet().iterator();
         boolean foundExect=false;

         for(;it.hasNext();){
             srcKey=(String)it.next();
             TreeMap dataTM=(TreeMap)datumTM.get(srcKey);
             Iterator it2=dataTM.keySet().iterator();
             for(;it2.hasNext();){
             long timeKey=((Long)it2.next()).longValue();
             if(targetTime==timeKey){
                 foundExect=true;
                 foundTargetTime=timeKey;
                 break;
             } else if(targetTime>timeKey){
                 lowerOne=timeKey;
             } else if(targetTime<timeKey){
                 upperOne=timeKey;
                 break;
             }
             if(foundExect) break;
             }
           }

           if(foundTargetTime==0){
             if(upperOne>0 && lowerOne>0){
               if((upperOne-targetTime)<=(targetTime-lowerOne) && (upperOne-targetTime)<=tolerate) foundTargetTime=upperOne;
               else if((targetTime-lowerOne)<=tolerate) foundTargetTime=lowerOne;
             } else if(upperOne>0 && (upperOne-targetTime)<=tolerate){
                 foundTargetTime=upperOne;
             } else if(lowerOne>0 && (targetTime-lowerOne)<=tolerate){
                 foundTargetTime=lowerOne;
             }

           }

           if(foundTargetTime>0){
             int inx2=0;
             it=datumTM.keySet().iterator();
             for(;it.hasNext();inx2++){
               srcKey=(String)it.next();
               TreeMap dataTM=(TreeMap)datumTM.get(srcKey);
               if(dataTM.get(foundTargetTime)!=null) {
                   Config cfgTMP=(Config)config2s.get(srcKey);
                   color[inx2]=cfgTMP.c[1];
                   if(OneVar.check(cfgTMP.longValue[4],28)) isRightAxis[inx2]=true; else isRightAxis[inx2]=false;
                   data[inx2]=numberFormat.format(((Double)dataTM.get(foundTargetTime)).doubleValue());
               }
             }
           }

           for(int i=0;i<data.length && i<13;i++){
               if(data[i].length()>0){
                 double v=Double.parseDouble(data[i]);
                 int x2=x+2,y2=(int)(y3+height3*((stat.doubleValue[6]-v)/(stat.doubleValue[6]-stat.doubleValue[7])))-10;
                 if(isRightAxis[i]) y2=(int)(y3+height3*((stat.doubleValue[8]-v)/(stat.doubleValue[8]-stat.doubleValue[9])))-10;
                 if((x2)<((int)x3)) x2= ((int)(x3+2.0));
                 if((x2)>((int)(x3+width3))-32) x2= ((int)(x3+width3))-32;
                 if(y2< ((int)y3)) y2=(int)(y3+15.0);
                 if(y2> ((int)(y3+height3))) y2=(int)(y3+height3-15.0);
                 cfg.intValue[120+i*10]=1;
                 cfg.intValue[121+i*10]=x2;
                 cfg.intValue[122+i*10]=y2;
                 cfg.intValue[124+i*10]=0;
                 cfg.conf2[78+i*10]=data[i];
                 cfg.c[71+i]=color[i];
               } else cfg.intValue[120+i*10]=0;
           }
         }

          config2s.put("0", cfg);
          if(panel2.panels.length>inx) panel2.setConfig(inx, config2s);
            TreeMap stat2s=panel2.statusArr[inx];
          stat2s.put("0", stat);
          if(panel2.panels.length>inx) panel2.setStatus(inx, stat2s);
          panel2.repaint();
      }
  }
  public void formMouseWheelMoved(MultiPanel2 panel2,Graphics2D g2,int inx,Config cfg,Status stat,String para[],double x0,double y0,double width, double height,double x3,double y3,double width3,double height3,java.awt.event.MouseWheelEvent evt) {                                     
      int notches = evt.getWheelRotation();
      int x=evt.getX();
      int y=evt.getY();
      if(x>= (int)x3 && x<=(int)(x3+width3) && y>=(int)y3 && y<= (int)(y3+height3)){

       if(notches<0){
              long newLong[]=resize(cfg,stat,true);
              stat.longValue[0]=newLong[0];
              stat.longValue[6]=newLong[1];
              cfg.longValue[1]=newLong[2];
              cfg.longValue[2]=newLong[3];
              cfg.longValue[3]=newLong[4];
            TreeMap config2s=panel2.configArr[inx];
          config2s.put("0", cfg);
          if(panel2.panels.length>inx) panel2.setConfig(inx, config2s);
            TreeMap stat2s=panel2.statusArr[inx];
          stat2s.put("0", stat);
          if(panel2.panels.length>inx) panel2.setStatus(inx, stat2s);
          panel2.repaint();
          } else {
              long newLong[]=resize(cfg,stat,false);
              stat.longValue[0]=newLong[0];
              stat.longValue[6]=newLong[1];
              cfg.longValue[1]=newLong[2];
              cfg.longValue[2]=newLong[3];
              cfg.longValue[3]=newLong[4];
             TreeMap config2s=panel2.configArr[inx];
          config2s.put("0", cfg);
          if(panel2.panels.length>inx) panel2.setConfig(inx, config2s);
            TreeMap stat2s=panel2.statusArr[inx];
          stat2s.put("0", stat);
          if(panel2.panels.length>inx) panel2.setStatus(inx, stat2s);
          panel2.repaint();
          }
      }
  }

  long[] resize(Config cfg,Status stat,boolean zoomin){
      long rtn[]={0,0,0,0,0};
      long tValueN=cfg.longValue[1]* 3600000L + cfg.longValue[2]*60000L + cfg.longValue[3]*1000L;
      long minima=180000L,newSpan=minima,center=0;
      if(stat.longValue[6]==0) center=stat.longValue[0]+(tValueN)/2;
      else center=stat.longValue[0]+(stat.longValue[6]-stat.longValue[0])/2;

      if(zoomin){
          while(true){
             if(tValueN<newSpan){
                 break;
             }
             newSpan=newSpan * 2;
          }
          newSpan=newSpan / 2;
          if(newSpan==tValueN) newSpan=newSpan / 2;
      } else {
          while(true){
             if(tValueN<newSpan){
                 break;
             } if(tValueN==newSpan){
                 newSpan=newSpan * 2;
                 break;
             }
             newSpan=newSpan * 2;
          }
      }
      rtn[2]=newSpan/(1000L*60L*60L);
      rtn[3]=(newSpan%(1000L*60L*60L))/(1000L*60L);
      rtn[4]=(newSpan%(1000L*60L))/1000L;
      rtn[0]=center-newSpan/2;
      rtn[1]=center+newSpan/2;

      return rtn;
  }
}