package ci;

import golib.Config;
import golib.MultiPanel2;
import golib.Status;
import javax.swing.JInternalFrame;

import java.awt.event.*;
import java.awt.*;
import java.text.ParseException;
import java.util.*;
import javax.swing.JOptionPane;
import y.ylib.OneVar;
import y.ylib.ylib;

public class GraphIFrame extends JInternalFrame {
    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;
    public boolean currentSizeIsMax=false;
    CrInstrument instrument;
    DataViewFrame uFrame;
    Config cfg0;
    Status sta0;
    HistoryGraphSetup gSetup;
    long originalStatLong0=0,originalStatLong6=0,originalCfgLong1=0,originalCfgLong2=0,originalCfgLong3=0;
    double originalStatDouble0=0,originalStatDouble1=0;
    public MultiPanel2 multiPanel;
    public GraphIFrame(DataViewFrame uFrame) {
        initComponents();
        this.uFrame=uFrame;
        instrument=uFrame.instrument;
        this.setTitle("Graph");
        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(false);
        init();

    }
void init(){
       double panels[][]={{0.02,0.02,0.96,0.96}};
        multiPanel=new MultiPanel2(Color.white,panels);
        jPanel1.add(multiPanel,BorderLayout.CENTER);

}

public void doLayout(){
    super.doLayout();
    int w=this.getContentPane().getWidth();
    int h=this.getContentPane().getHeight();
    jPanel1.setBounds(0, 0, (int)(((double)w)*1.0), (int)(((double)h)*0.9));
    jPanel2.setBounds(0, (int)(((double)h)*0.9), (int)(((double)w)*1.0), (int)(((double)h)*0.1));
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel51 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameDeactivated(evt);
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameDeiconified(evt);
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameIconified(evt);
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanel1);
        jPanel1.setBounds(20, 10, 350, 120);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setLayout(new java.awt.GridLayout(1, 1));

        jPanel51.setBackground(new java.awt.Color(255, 255, 255));
        jPanel51.setOpaque(false);

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel51.add(jButton1);

        jButton2.setText("Setup");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel51.add(jButton2);

        jPanel2.add(jPanel51);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(30, 250, 360, 40);

        pack();
    }

    private void formInternalFrameIconified(javax.swing.event.InternalFrameEvent evt) {

        uFrame.dMax=true;
        uFrame.gMax=false;
        System.out.println("A, uFrame.dMax="+uFrame.dMax);
        uFrame.revalidate();
        this.revalidate();
    }

    private void formInternalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {

    }

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {

    }

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {

    }

    private void formInternalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {

    }

    private void formPropertyChange(java.beans.PropertyChangeEvent evt) {
        if(!currentSizeIsMax && this.isMaximum) {

            uFrame.dMax=false;
            uFrame.gMax=true;
            currentSizeIsMax=true;

            uFrame.revalidate();
            this.revalidate();
        }
        else if(currentSizeIsMax && !this.isMaximum) {

            uFrame.gMax=false;
            currentSizeIsMax=false;

            uFrame.revalidate();
            this.revalidate();
        }
         else if(currentSizeIsMax && this.isMaximum) {

            uFrame.gMax=false;
            currentSizeIsMax=false;

            uFrame.validate();
        }
    }

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
        uFrame.dMax=true;
        uFrame.gMax=false;
        uFrame.revalidate();
        this.revalidate();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        int inx=0; 
        TreeMap config2s=multiPanel.configArr[inx];
        TreeMap stat2s=multiPanel.statusArr[inx];
        Config cfg=(Config)config2s.get("0");
        Status stat=(Status)stat2s.get("0");
         stat.longValue[0]=originalStatLong0;
              stat.longValue[6]=originalStatLong6;
              cfg.longValue[1]=originalCfgLong1;
              cfg.longValue[2]=originalCfgLong2;
              cfg.longValue[3]=originalCfgLong3;

              stat.doubleValue[6]=multiPanel.getOriginStatAllDouble6();
              stat.doubleValue[7]=multiPanel.getOriginStatAllDouble7();
              stat.doubleValue[8]=multiPanel.originalStatAllDouble8;
              stat.doubleValue[9]=multiPanel.originalStatAllDouble9;

      uFrame.instrument.props.setProperty("history_linetype","1");
    uFrame.instrument.props.setProperty("history_linesize","1");
    uFrame.instrument.props.setProperty("history_pointsize","6");
    uFrame.instrument.props.setProperty("history_bgtype","2");
    uFrame.instrument.props.setProperty("history_gcolor1","-5192482");
    uFrame.instrument.props.setProperty("history_gcolor2","-1");

          config2s.put("0", cfg);
          if(multiPanel.panels.length>inx) multiPanel.setConfig(inx, config2s);
          stat2s.put("0", stat);
          if(multiPanel.panels.length>inx) multiPanel.setStatus(inx, stat2s);

          showCurve();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
       if(gSetup==null) gSetup=new HistoryGraphSetup(instrument,true);
       else gSetup.init();
       gSetup.setVisible(true);
    }
public void showCurve(){

      int selectedCount=0;
      long start=System.currentTimeMillis();
       TreeMap curveConfigs=new TreeMap(),curveStatuses=new TreeMap(),stationData=new TreeMap();

       curveConfigs.put("0", getConfig(0));
       curveStatuses.put("0", getStatus(0));

      for(int i=0;i<uFrame.columnColor.length;i++) {
          if(uFrame.columnColor[i]!=null) {
              selectedCount++;
              curveConfigs.put(uFrame.dataTitle[i], getConfig(i));
              TreeMap tmpDataDTM=new TreeMap();
              int inx=0;
              Iterator it=uFrame.dataStrTM[0].keySet().iterator();
              for(;it.hasNext();){

                  long longKey=((Long)it.next()).longValue();
                  if(uFrame.cellColor[i][inx]>0 && uFrame.dataDTM[i].get(longKey)!=null) {
                      double value=((Double)uFrame.dataDTM[i].get(longKey)).doubleValue();
                      tmpDataDTM.put(longKey, value);
                  }
                  inx++;
              }
              stationData.put(uFrame.dataTitle[i],tmpDataDTM);
              curveStatuses.put(uFrame.dataTitle[i], getStatus(i));
          }
      }
       if(uFrame.dataTitle != null && uFrame.dataStrTM != null && uFrame.dataStrTM.length>0 && selectedCount>0){

       uFrame.gFrame.multiPanel.setTMData(0,stationData,curveConfigs,curveStatuses,"0");

       uFrame.gFrame.multiPanel.repaint();

      } 
      else if(uFrame.gFrame.multiPanel!=null){

       multiPanel.setTMData(0,null,curveConfigs,curveStatuses,"0");
       multiPanel.repaint();

      }
    }

public Config getConfig(int inx){

    boolean hasYValue=false,hasY2Value=false;
    Config cfgA;
        String confStr="lin2,1431048,-65536,-1,A,1,1,1,0,0,0.0,1.0,0.0,2,45,"+
              "-45,400,24,0,0,1.0,0.0,,,,Y,-24,,,,"+
              "0.0,10,1,4,6,0,1.0,0.1,0.18,0.3,5,-3,0.02,0.75,0.85,0.5,0.5,68719607550,3,"+
              ",,,,,,,,,,"+
              ",,,,,,,,,,"+
              ",,,,,,,,,,"+
              ",,,,,,,,,,"+
              ",,,,,,,,,,"+
              ",,,,,,,,,,"+
              ",,,,,,,,,,"+
              ",,,,,,,,,,"+
              ",,,,,,,,,,"+
              ",,,,,,,,,,"+
              ",,,,,,,,,,"+
              ",,,,,,,,,,"+
              ",,,,,,,,,,"+
              ",,,,,,,,,,"+
              ",,,,,,,,,";
        String confStr2=",,,,,,,,,,,,,"+
              ",,,,,,,,,,,,,,,,,"+
              ",,,,,,,,,,,,,,,,,,"+
              ",,,,,,,,,,"+
              ",,,,,,,,,,"+
              ",,,,,,,,,,"+
              ",,,,,,,,,,"+
              ",,,,,,,,,,"+
              ",,,,,,,,,,";

     String[] confA=ylib.csvlinetoarray(confStr);
     String[] confA_2=ylib.csvlinetoarray(confStr2);
     confA[0]=uFrame.dataTitle[inx];
     confA[1]=(uFrame.instrument.getPropsInt("history_bgtype")==2? OneVar.add(confA[1],9):OneVar.remove(confA[1],9));
     confA[1]=OneVar.remove(confA[1],16);
     confA[1]=OneVar.remove(confA[1],17);
     confA[1]=OneVar.remove(confA[1],12);
     confA[1]=OneVar.remove(confA[1],20);
     confA[1] = OneVar.add(confA[1], 14);

     confA[2]=(uFrame.columnColor[inx]==null? ""+Color.black.getRGB():""+uFrame.columnColor[inx].getRGB());
     confA[3]=""+uFrame.instrument.getPropsInt("history_bgcolor");
     confA[14]="0.15";
     confA[15]="-0.15";
     if(inx==0){
       long beginTime=0, endTime=0;
       for(int i=2;i<uFrame.dataDTM.length;i++){
        if(uFrame.dataDTM[i].size()>0){
         long time1=((Long)uFrame.dataDTM[i].firstKey()).longValue();
         long time2=((Long)uFrame.dataDTM[i].lastKey()).longValue();
         if(beginTime>time1 || beginTime==0){beginTime=time1;}
         if(endTime<time2 || endTime==0){endTime=time2;}
         if(uFrame.columnColor[i]!=null){
            if(OneVar.check(uFrame.columnOneVar[i],0)) hasY2Value=true;
            else hasYValue=true;
        }
        }
        }
       long span=endTime-beginTime;
       if(span<180000L) span=180000L;
       long hour=span/(1000L*60L*60L);
       long min=(span%(1000L*60L*60L))/(1000L*60L);
       long sec=(span%(1000L*60L))/1000L;
     confA[1]=(hasYValue? OneVar.add(confA[1], 15):OneVar.remove(confA[1], 15));
     confA[17]=""+hour;
     confA[18]=""+min;
     confA[19]=""+sec;
     originalCfgLong1=hour;
     originalCfgLong2=min;
     originalCfgLong3=sec;

     } else {
     confA[17]="0";
     confA[18]="3";
     confA[19]="0";

     }

     confA[26]="-2";
     confA[32]=(uFrame.instrument.getPropsInt("history_linetype")==1? ""+uFrame.instrument.getPropsInt("history_linesize"):"0");
     confA[33]=(uFrame.instrument.getPropsInt("history_linetype")==2? ""+uFrame.instrument.getPropsInt("history_pointsize"):"0");

     confA[34]="13";
     confA[42]="0.06";
     confA[43]="0.75";
     confA[44]="0.63";
     confA[45]=(hasY2Value? "0.5":"0.65");
     confA[46]="0.60";

     confA[47]="482294496510";

     confA[47]=OneVar.remove(confA[47],11);

     confA[47]=(hasY2Value? OneVar.add(confA[47],18):OneVar.remove(confA[47],18));
     confA[47]=OneVar.remove(confA[47],21);
     if(OneVar.check(uFrame.columnOneVar[inx],0)) confA[47]=OneVar.add(confA[47],28);
     else confA[47]=OneVar.remove(confA[47],28);
     if(hasY2Value) confA[47]=OneVar.add(confA[47],29);
     confA[47]=OneVar.remove(confA[47],30);
     confA[47]=OneVar.remove(confA[47],31);
     confA[47]=OneVar.remove(confA[47],39);
     confA[47]=OneVar.remove(confA[47],40);
     if(hasY2Value) confA[47]=OneVar.add(confA[47],38);
     confA[47]=(hasYValue? OneVar.remove(confA[47],51):OneVar.add(confA[47],51));
     confA[47]=OneVar.add(confA[47],52);

     confA[50]=""+Color.red.getRGB();
     confA[51]=""+Color.white.getRGB();
     confA[52]=""+Color.orange.getRGB();
     confA[54]=""+Color.orange.getRGB();
     confA[57]=""+Color.lightGray.getRGB();
     confA[92]="";
     confA[98]=(uFrame.instrument.getPropsInt("history_linetype")==1? "1":(uFrame.instrument.getPropsInt("history_pointsize")>3? "3":"1"));
     confA[100]=(uFrame.columnColor[inx]==null? ""+Color.black.getRGB():""+uFrame.columnColor[inx].getRGB());
     confA[101]="-16777216";
     confA[108]="3";
     confA[125]="-6";
     confA[126]="1";
     confA[127]="-4934476";
     confA[148]=""+Color.PINK.getRGB();
     confA[177]=""+Color.red.getRGB();
     confA[178]=""+Color.red.getRGB();
     confA[184]="1";
     confA[185]="1";
     confA[186]=""+Color.gray.getRGB();
     confA[187]="0";
     confA[188]="11";
     confA[189]="11";
     confA[190]="11";
     confA[192]="";
     confA_2[0]="11";
     confA_2[1]="2";
     confA_2[2]="2";
     confA_2[3]="1";
     confA_2[4]="2";
     confA_2[9]="2";
     confA_2[10]="2";
     confA_2[11]="ci.HistoryCurveClass";
     confA_2[12]=""+(new Color(200,200,255)).getRGB();
     confA_2[13]=""+Color.white.getRGB();
     confA_2[14]="0";
     confA_2[19]="1";
     confA_2[20]="2";
     confA_2[30]="1";
     confA_2[38]=""+Color.red.getRGB();
     confA_2[40]=""+Color.red.getRGB();
     confA_2[42]="2";
     confA_2[43]="11";
     confA_2[44]="2";
     confA_2[45]="11";
     confA_2[48]="3";
     confA_2[51]="0.8";
     confA_2[52]=uFrame.instrument.getPropsString("history_gcolor1");
     confA_2[53]=uFrame.instrument.getPropsString("history_gcolor2");
     confA_2[54]="90";

     cfgA=new Config(confA,confA_2);

     return cfgA;
}

public Status getStatus(int inx){
   Status staA=null;
   try{

   long startTime=((Long)uFrame.dataStrTM[0].firstKey()).longValue();

     String statusStr=startTime+",1,0,0,0,0,0,0,0,0,0.0,0.0,0.0,0.0,0.0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,";
     String stat[]=ylib.csvlinetoarray(statusStr);
     if(inx==0){
       long beginTime=0, endTime=0;
       for(int i=2;i<uFrame.dataDTM.length;i++){
        if(uFrame.dataDTM[i].size()>0){
         long time1=((Long)uFrame.dataDTM[i].firstKey()).longValue();
         long time2=((Long)uFrame.dataDTM[i].lastKey()).longValue();
         if(beginTime>time1 || beginTime==0){beginTime=time1;}
         if(endTime<time2 || endTime==0){endTime=time2;}
        }
        }
       stat[0]=""+beginTime;
       stat[21]=""+endTime;
       originalStatLong0=beginTime;
       originalStatLong6=endTime;
     }

    staA=new Status(new long[]{Long.parseLong(stat[0]),Long.parseLong(stat[1]),Long.parseLong(stat[2]),Long.parseLong(stat[3]),Long.parseLong(stat[4]),
                     Long.parseLong(stat[20]),Long.parseLong(stat[21]),Long.parseLong(stat[22]),Long.parseLong(stat[23]),Long.parseLong(stat[24])},
                           new int[]{Integer.parseInt(stat[5]),Integer.parseInt(stat[6]),Integer.parseInt(stat[7]),Integer.parseInt(stat[8]),Integer.parseInt(stat[9])},
                           new double[]{Double.parseDouble(stat[10]),Double.parseDouble(stat[11]),Double.parseDouble(stat[12]),Double.parseDouble(stat[13]),Double.parseDouble(stat[14]),Double.parseDouble(stat[25]),Double.parseDouble(stat[26]),Double.parseDouble(stat[27]),Double.parseDouble(stat[28]),Double.parseDouble(stat[29])},stat);

   } catch(Exception e2){
       e2.printStackTrace();
   }
   return staA;
}

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel51;

}