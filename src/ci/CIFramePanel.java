
package ci;

import static ci.CrInstrument.isNumeric;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.Toolkit;

import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TreeMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import y.ylib.ylib;
import javax.swing.border.Border;

/**
 *
 * @author peter
 */
public class CIFramePanel extends javax.swing.JPanel {

  CrInstrument instrument;

    public ResourceBundle bundle2 = java.util.ResourceBundle.getBundle("ci/Bundle");
    double headerRatio=0.0;
    String frameColor="-1";
    ImageIcon status_label=null,status_light=null,headerImg=null;

  public CIFramePanel(CrInstrument instrument) {
    initComponents();
    this.instrument=instrument;
    for(int i=0;i<stroke.length;i++) stroke[i]=new BasicStroke((float)i);

    }

  public void doLayout(){
      super.doLayout();

   String info[]=null;
 if(instrument.editUI!=null){

  int frameHeight = instrument.getHeight();
  int panelWidth = this.getWidth();
  int panelHeight = this.getHeight();
  int headerHeightInPoint=74;
  headerRatio=0.0;
  boolean needUpdateImg=false;

  if(instrument.editUI.get("frame")!=null){
           info=ylib.csvlinetoarray((String)instrument.editUI.get("frame"));
           if(instrument.isNumeric(info[7])) {this.setBackground(new Color(Integer.parseInt(info[7]))); frameColor=info[7];}
                 else {this.setBackground(Color.white); frameColor="-1";}

           if(instrument.isNumeric(info[6])){
           if(info[2].trim().equals("%")){
               if(frameHeight==0) frameHeight=712;
               else frameHeight=(int)(Double.parseDouble(info[6])* ((double) (Toolkit.getDefaultToolkit().getScreenSize().height - 20)));
           } else {
               frameHeight=(int)(Double.parseDouble(info[6]));
           }
           } else frameHeight=712;
           headerRatio=((double)headerHeightInPoint)/((double)frameHeight);
           if(info[2].trim().equals("%")){
               instrument.uiPanel2.jLabel107.setText("%");
               instrument.uiPanel2.jLabel139.setText("%");
               instrument.uiPanel2.jLabel156.setText("%");
               instrument.uiPanel2.jLabel154.setText("%");
           } else {
               instrument.uiPanel2.jLabel107.setText("");
               instrument.uiPanel2.jLabel139.setText("");
               instrument.uiPanel2.jLabel156.setText("");
               instrument.uiPanel2.jLabel154.setText("");

           }

    } else System.out.println("instrument.editUI.get(\"frame\") is null");

     headerLabel.setBounds(0, 0,panelWidth, (int)Math.round(((double)panelHeight)*headerRatio));
     if(headerImg==null || headerLabel.getWidth()!=headerImg.getIconWidth() || headerLabel.getHeight()!=headerImg.getIconHeight()){
      needUpdateImg=true;

      Image image2=new ImageIcon(getClass().getClassLoader().getResource("ui_header2.jpg")).getImage();

              if(headerLabel.getWidth()>0 && headerLabel.getHeight()>0 ){
              image2 = image2.getScaledInstance(headerLabel.getWidth(), headerLabel.getHeight(),Image.SCALE_SMOOTH);
              }
             headerImg = new ImageIcon(image2);

     }
      headerLabel.setIcon(headerImg);
     if(status_label==null || needUpdateImg){
         if(instrument.editUI.get("light label area")!=null){
         info=ylib.csvlinetoarray((String)instrument.editUI.get("light label area"));
           int x2=0,y2=0,width2=0,height2=0;
           if(info.length > 3 && info[3].length()>0) x2=(int)(Double.parseDouble(info[3]) * ((double)panelWidth));
           if(info.length > 4 && info[4].length()>0) y2=(int)(Double.parseDouble(info[4]) * ((double)panelHeight))+(int)(((double)frameHeight)*headerRatio);
           if(info.length > 5 && info[5].length()>0) width2=(int)(Double.parseDouble(info[5]) * ((double)panelWidth));
           if(info.length > 6 && info[6].length()>0) height2=(int)(Double.parseDouble(info[6]) * ((double)panelHeight));
              Image image2=new ImageIcon(getClass().getClassLoader().getResource("ui_statuslabel.jpg")).getImage();

              if(width2>0 && height2>0 ){
              image2 = image2.getScaledInstance(width2, height2,Image.SCALE_SMOOTH);
              }
             status_label = new ImageIcon(image2);
          }
     }
     if(status_light==null || needUpdateImg){
         if(instrument.editUI.get("light area")!=null){
         info=ylib.csvlinetoarray((String)instrument.editUI.get("light area"));
           int x2=0,y2=0,width2=0,height2=0;
           if(info.length > 3 && info[3].length()>0) x2=(int)(Double.parseDouble(info[3]) * ((double)panelWidth));
           if(info.length > 4 && info[4].length()>0) y2=(int)(Double.parseDouble(info[4]) * ((double)panelHeight))+(int)(((double)frameHeight)*headerRatio);
           if(info.length > 5 && info[5].length()>0) width2=(int)(Double.parseDouble(info[5]) * ((double)panelWidth));
           if(info.length > 6 && info[6].length()>0) height2=(int)(Double.parseDouble(info[6]) * ((double)panelHeight));
              Image image2=new ImageIcon(getClass().getClassLoader().getResource("ui_statuslight.jpg")).getImage();

              if(width2>0 && height2>0 ){
              image2 = image2.getScaledInstance(width2, height2,Image.SCALE_SMOOTH);
              }
             status_light = new ImageIcon(image2);
          }
     }

    setButton("button 02",button02,panelWidth,panelHeight);
  setButton("button 03",button03,panelWidth,panelHeight);
  setButton("button 04",button04,panelWidth,panelHeight);
  setButton("button 05",button05,panelWidth,panelHeight);
  setButton("button 06",button06,panelWidth,panelHeight);
  setButton("button 07",button07,panelWidth,panelHeight);
  setButton("button 08",button08,panelWidth,panelHeight);
  setButton("button 09",button09,panelWidth,panelHeight);
  setButton("button 10",button10,panelWidth,panelHeight);
  setButton("connect button",btnConnect,panelWidth,panelHeight);
  setButton("start button",btnStart,panelWidth,panelHeight);
  setPanel("data area",dataPanel,panelWidth,panelHeight,bundle2.getString("CrInstrument.dataPanel.border.title"));
  setPanel("chart area",chartPanel,panelWidth,panelHeight,bundle2.getString("CrInstrument.chartPanel.border.title"));
  setPanel("device table area",deviceTable,panelWidth,panelHeight,bundle2.getString("CrInstrument.jScrollPane3.border.title"));
  setPanel("station list area",stationList,panelWidth,panelHeight,bundle2.getString("CrInstrument.jScrollPane2.border.title"));
  setLabel("light label area",statusLabel,panelWidth,panelHeight,status_label);
  setLabel("light area",lightLabel,panelWidth,panelHeight,status_light);
  setLabel("time area",timeLabel,panelWidth,panelHeight,null);

  setPanel("chart option area",chartOptionPanel,panelWidth,panelHeight,bundle2.getString("CrInstrument.chartOptionPanel.border.title"));

 }

  }
     void setLabel(String key,JLabel label,int frameWidth,int frameHeight,ImageIcon icon){
     String info[];
     if(instrument.editUI.get(key)!=null){
         info=ylib.csvlinetoarray((String)instrument.editUI.get(key));
         if(info.length>2 && info[2].equalsIgnoreCase("s")){
           int x2=0,y2=0,width2=0,height2=0;
           label.setVisible(true);
           if(info.length > 3 && info[3].length()>0) x2=(int)(Double.parseDouble(info[3]) * ((double)frameWidth));
           if(info.length > 4 && info[4].length()>0) y2=(int)(Double.parseDouble(info[4]) * ((double)frameHeight))+(int)(((double)frameHeight)*headerRatio);
           if(info.length > 5 && info[5].length()>0) width2=(int)(Double.parseDouble(info[5]) * ((double)frameWidth));
           if(info.length > 6 && info[6].length()>0) height2=(int)(Double.parseDouble(info[6]) * ((double)frameHeight));
           label.setBounds(x2, y2,width2,height2);
           if(icon!=null){
             label.setIcon(icon);
           }
           Font font=label.getFont();
           if(info[1].trim().length()>0) label.setText(info[1]);
           label.setFont(instrument.getFont(font,font.getSize(),info[8],info[9],info[11].equalsIgnoreCase("b"),info[12].equalsIgnoreCase("i")));
           label.setBackground((info.length>7 && info[7].length()>0 && instrument.isNumeric(info[7]))? new Color(Integer.parseInt(info[7])):label.getBackground());
           label.setForeground((info.length>10 && info[10].length()>0 && instrument.isNumeric(info[10]))? new Color(Integer.parseInt(info[10])):label.getForeground());
            if(instrument.selectedUIAreaItem.length()>0 && instrument.selectedUIAreaItem.equalsIgnoreCase(key)){
             Border compound;
             Border redline = BorderFactory.createLineBorder(Color.red);
            label.setBorder(redline); 
         } else {
                Border emptyBorder = BorderFactory.createEmptyBorder();
               label.setBorder(emptyBorder);
           }

         } else label.setVisible(false);
       } else if(instrument.editUI.size()>0) instrument.sysLog("Warning: label key '"+key+"' not found in editUI.");
   }
    void setPanel(String key,JPanel panel,int frameWidth,int frameHeight,String title){
      String info[];

     if(instrument.editUI.get(key)!=null){
    info=ylib.csvlinetoarray((String)instrument.editUI.get(key));

    if(info.length>2 && info[2].equalsIgnoreCase("s")){
        int x=0,y=0,width=0,height=0;
        panel.setVisible(true);
        if(info.length > 3 && info[3].length()>0) x=(int)(Double.parseDouble(info[3]) * ((double)frameWidth));
        if(info.length > 4 && info[4].length()>0) y=(int)(Double.parseDouble(info[4]) * ((double)frameHeight))+(int)(((double)frameHeight)*headerRatio);
        if(info.length > 5 && info[5].length()>0) width=(int)(Double.parseDouble(info[5]) * ((double)frameWidth));
        if(info.length > 6 && info[6].length()>0) height=(int)(Double.parseDouble(info[6]) * ((double)frameHeight));
        panel.setBounds(x, y,width,height);
        panel.setBackground((info.length>7 && info[7].length()>0 && instrument.isNumeric(info[7]))? new Color(Integer.parseInt(info[7])):panel.getBackground());
        panel.setForeground((info.length>10 && info[10].length()>0 && instrument.isNumeric(info[10]))? new Color(Integer.parseInt(info[10])):panel.getForeground());
        if(title.length()>0){
        Color fontColor=((info.length>10 && info[10].length()>0 && isNumeric(info[10]))? new Color(Integer.parseInt(info[10])):panel.getForeground());
        Color borderColor=((info.length>13 && info[13].length()>0 && isNumeric(info[13]))? new Color(Integer.parseInt(info[13])):new java.awt.Color(102, 0, 255));

         Font titleFont=panel.getFont();
         int fontSize=(int)Math.round(((double)(instrument.isNumeric(info[9])? Integer.parseInt(info[9]):panel.getFont().getSize()))/2.0);
         titleFont=instrument.getFont(panel.getFont(), fontSize, panel.getFont().getFontName(), ""+fontSize, info[11].equalsIgnoreCase("b"), info[12].equalsIgnoreCase("i"));
         Border titleBorder=javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(borderColor), title, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, titleFont, fontColor);
         if(instrument.selectedUIAreaItem.length()>0 && instrument.selectedUIAreaItem.equalsIgnoreCase(key)){
             Border compound;
             Border redline = BorderFactory.createLineBorder(Color.red);
            compound = BorderFactory.createCompoundBorder(redline, titleBorder);
            panel.setBorder(compound); 
         } else {
              panel.setBorder(titleBorder); 
           }
        } else {
            if(instrument.selectedUIAreaItem.length()>0 && instrument.selectedUIAreaItem.equalsIgnoreCase(key)){
             Border compound;
             Border redline = BorderFactory.createLineBorder(Color.red);
            panel.setBorder(redline); 
         } else {
            if(frameColor.trim().equals(info[7].trim())) {
              Border lineBorder=BorderFactory.createLineBorder(Color.darkGray);
              panel.setBorder(lineBorder); 
            }
           }
        }
    } else panel.setVisible(false);
  } else if(instrument.editUI.size()>0) instrument.sysLog("Warning: panel key '"+key+"' not found in editUI.");
  }
   void setButton(String key,JButton button,int frameWidth,int frameHeight){
     String info[];
       if(instrument.editUI.get(key)!=null){
       info=ylib.csvlinetoarray((String)instrument.editUI.get(key));
       if(info.length>2 && info[2].equalsIgnoreCase("s")){
        int x=0,y=0,width=0,height=0;
        button.setVisible(true);
        if(info.length>1 && info[1].length() >0 && !(info[0].equalsIgnoreCase("connect button") || info[0].equalsIgnoreCase("start button"))) button.setText(info[1]);
        if(info.length > 3 && info[3].length()>0) x=(int)(Double.parseDouble(info[3]) * ((double)frameWidth));
        if(info.length > 4 && info[4].length()>0) y=(int)(Double.parseDouble(info[4]) * ((double)frameHeight))+(int)(((double)frameHeight)*headerRatio);
        if(info.length > 5 && info[5].length()>0) width=(int)(Double.parseDouble(info[5]) * ((double)frameWidth));
        if(info.length > 6 && info[6].length()>0) height=(int)(Double.parseDouble(info[6]) * ((double)frameHeight));
        button.setBounds(x, y,width,height);
        Font font=button.getFont();
        int fontSize=(int)Math.round(((double)(instrument.isNumeric(info[9])? Integer.parseInt(info[9]):font.getSize()))/2.0);
        button.setFont(instrument.getFont(font,fontSize,info[8],""+fontSize,info[11].equalsIgnoreCase("b"),info[12].equalsIgnoreCase("i")));

        button.setForeground((info.length>10 && info[10].length()>0 && instrument.isNumeric(info[10]))? new Color(Integer.parseInt(info[10])):button.getForeground());
        if(instrument.selectedUIAreaItem.length()>0 && instrument.selectedUIAreaItem.equalsIgnoreCase(key)){
             Border compound;
             Border redline = BorderFactory.createLineBorder(Color.red);
            button.setBorder(redline); 
         } else {
            Border emptyBorder = BorderFactory.createEmptyBorder();
           button.setBorder(emptyBorder);
        }
    } else  button.setVisible(false);
  }   else if(instrument.editUI.size()>0) instrument.sysLog("Warning: button key '"+key+"' not found in editUI.");
   }
   void updateAreaItem(){
     if(instrument.uiPanel2.jComboBox36.getSelectedIndex()!=-1){
         String sel=(String)instrument.uiPanel2.jComboBox36.getSelectedItem();
    if(instrument.editUI.get(sel)==null) return;
    String info[]=ylib.csvlinetoarray((String)instrument.editUI.get(sel));
    if(instrument.uiPanel2.jCheckBox37.isSelected()) info[2]="s"; else info[2]="e";
    info[1]= instrument.uiPanel2.jTextField3.getText().trim();
    info[3]= (instrument.isNumeric(instrument.uiPanel2.jTextField5.getText())? String.valueOf(Double.parseDouble(instrument.uiPanel2.jTextField5.getText())/100.0):"0.0");
    info[4]= (instrument.isNumeric(instrument.uiPanel2.jTextField9.getText())? String.valueOf(Double.parseDouble(instrument.uiPanel2.jTextField9.getText())/100.0):"0.0");
    info[5]= (instrument.isNumeric(instrument.uiPanel2.jTextField10.getText())? String.valueOf(Double.parseDouble(instrument.uiPanel2.jTextField10.getText())/100.0):"0.0");
    info[6]= (instrument.isNumeric(instrument.uiPanel2.jTextField61.getText())? String.valueOf(Double.parseDouble(instrument.uiPanel2.jTextField61.getText())/100.0):"0.0");
    info[9]= instrument.uiPanel2.jTextField73.getText().trim();
    if(instrument.uiPanel2.jCheckBox3.isSelected()) info[11]="b"; else info[11]="e";
    if(instrument.uiPanel2.jCheckBox46.isSelected()) info[12]="i"; else info[12]="e";
    info[7]=""+instrument.uiPanel2.jLabel63.getBackground().getRGB();
    info[10]=""+instrument.uiPanel2.jLabel62.getBackground().getRGB();
    info[13]=""+instrument.uiPanel2.jLabel2.getBackground().getRGB();
    instrument.editUI.put(sel,ylib.arrayToCsvLine(info));
    invalidate();
    if(sel.equalsIgnoreCase("data area")) instrument.dataPanel2.invalidate();
     }
   }
  

  

BasicStroke stroke[]=new BasicStroke[10];

public BasicStroke getStroke(int lineWidth,int lineStyle){
  float multiply=(float)(lineStyle/10+1);
  lineStyle=lineStyle%10;
    BasicStroke stroke2=null;
   switch(lineStyle){
     case 0:
       if(lineWidth>9) stroke2=new BasicStroke((float)lineWidth);
        else stroke2=stroke[lineWidth];
       break;
     case 1:
       float[] DW_REL_LINE2 = {3f*multiply,1f*multiply};
       stroke2=new BasicStroke((float)lineWidth,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,1f,DW_REL_LINE2,0f);
       break;
     case 2:
       float[] DW_REL_LINE3 = {1f*multiply,3f*multiply};
       stroke2=new BasicStroke((float)lineWidth,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,1f,DW_REL_LINE3,0f);
       break;
     case 3:
       float[] DW_REL_LINE8 = {9f*multiply,3f*multiply,2f*multiply,3f*multiply};
       stroke2=new BasicStroke((float)lineWidth,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,1f,DW_REL_LINE8,0f);
       break;
     case 4:
       break;
     case 5:
       break;
     case 6:
       break;
     case 7:
       break;
     case 8:
       break;
     case 9:
       break;
   }
 return stroke2;
}
  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")

    private void initComponents() {

        chartPanel = new javax.swing.JPanel();
        chartOptionPanel = new javax.swing.JPanel();
        deviceTable = new javax.swing.JPanel();
        dataPanel = new javax.swing.JPanel();
        stationList = new javax.swing.JPanel();
        timeLabel = new javax.swing.JLabel();
        btnConnect = new javax.swing.JButton();
        button02 = new javax.swing.JButton();
        btnStart = new javax.swing.JButton();
        button03 = new javax.swing.JButton();
        statusLabel = new javax.swing.JLabel();
        button04 = new javax.swing.JButton();
        button05 = new javax.swing.JButton();
        button06 = new javax.swing.JButton();
        button07 = new javax.swing.JButton();
        button08 = new javax.swing.JButton();
        button09 = new javax.swing.JButton();
        button10 = new javax.swing.JButton();
        lightLabel = new javax.swing.JLabel();
        headerLabel = new javax.swing.JLabel();

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        setLayout(null);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ci/Bundle"); 
        chartPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), bundle.getString("CIFramePanel.chartPanel.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("新細明體", 0, 6))); 
        chartPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chartPanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout chartPanelLayout = new javax.swing.GroupLayout(chartPanel);
        chartPanel.setLayout(chartPanelLayout);
        chartPanelLayout.setHorizontalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        chartPanelLayout.setVerticalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 145, Short.MAX_VALUE)
        );

        add(chartPanel);
        chartPanel.setBounds(350, 20, 110, 160);

        chartOptionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), bundle.getString("CIFramePanel.chartOptionPanel.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("新細明體", 0, 6))); 
        chartOptionPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chartOptionPanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout chartOptionPanelLayout = new javax.swing.GroupLayout(chartOptionPanel);
        chartOptionPanel.setLayout(chartOptionPanelLayout);
        chartOptionPanelLayout.setHorizontalGroup(
            chartOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        chartOptionPanelLayout.setVerticalGroup(
            chartOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 85, Short.MAX_VALUE)
        );

        add(chartOptionPanel);
        chartOptionPanel.setBounds(350, 210, 100, 100);

        deviceTable.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), bundle.getString("CIFramePanel.deviceTable.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("新細明體", 0, 6))); 
        deviceTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deviceTableMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout deviceTableLayout = new javax.swing.GroupLayout(deviceTable);
        deviceTable.setLayout(deviceTableLayout);
        deviceTableLayout.setHorizontalGroup(
            deviceTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        deviceTableLayout.setVerticalGroup(
            deviceTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 135, Short.MAX_VALUE)
        );

        add(deviceTable);
        deviceTable.setBounds(170, 30, 130, 150);

        dataPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), bundle.getString("CIFramePanel.dataPanel.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("新細明體", 0, 6))); 
        dataPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataPanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout dataPanelLayout = new javax.swing.GroupLayout(dataPanel);
        dataPanel.setLayout(dataPanelLayout);
        dataPanelLayout.setHorizontalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        dataPanelLayout.setVerticalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 85, Short.MAX_VALUE)
        );

        add(dataPanel);
        dataPanel.setBounds(190, 200, 100, 100);

        stationList.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), bundle.getString("CIFramePanel.stationList.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("新細明體", 0, 6))); 
        stationList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stationListMouseClicked(evt);
            }
        });
        stationList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                stationListKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout stationListLayout = new javax.swing.GroupLayout(stationList);
        stationList.setLayout(stationListLayout);
        stationListLayout.setHorizontalGroup(
            stationListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        stationListLayout.setVerticalGroup(
            stationListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 85, Short.MAX_VALUE)
        );

        add(stationList);
        stationList.setBounds(20, 40, 100, 100);

        timeLabel.setFont(timeLabel.getFont().deriveFont(timeLabel.getFont().getSize()-6f));
        timeLabel.setText(bundle.getString("CIFramePanel.timeLabel.text")); 
        timeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timeLabelMouseClicked(evt);
            }
        });
        add(timeLabel);
        timeLabel.setBounds(30, 190, 60, 8);

        btnConnect.setFont(btnConnect.getFont().deriveFont(btnConnect.getFont().getSize()-6f));
        btnConnect.setText(bundle.getString("CIFramePanel.btnConnect.text")); 
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });
        add(btnConnect);
        btnConnect.setBounds(30, 210, 53, 17);

        button02.setFont(button02.getFont().deriveFont(button02.getFont().getSize()-6f));
        button02.setText(bundle.getString("CIFramePanel.button02.text")); 
        button02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button02ActionPerformed(evt);
            }
        });
        add(button02);
        button02.setBounds(30, 230, 55, 17);

        btnStart.setFont(btnStart.getFont().deriveFont(btnStart.getFont().getSize()-6f));
        btnStart.setText(bundle.getString("CIFramePanel.btnStart.text")); 
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        add(btnStart);
        btnStart.setBounds(30, 250, 45, 17);

        button03.setFont(button03.getFont().deriveFont(button03.getFont().getSize()-6f));
        button03.setText(bundle.getString("CIFramePanel.button03.text")); 
        button03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button03ActionPerformed(evt);
            }
        });
        add(button03);
        button03.setBounds(30, 150, 57, 17);

        statusLabel.setBackground(new java.awt.Color(51, 255, 255));
        statusLabel.setText(bundle.getString("CIFramePanel.statusLabel.text")); 
        statusLabel.setOpaque(true);
        statusLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                statusLabelMouseClicked(evt);
            }
        });
        add(statusLabel);
        statusLabel.setBounds(40, 270, 80, 20);

        button04.setFont(button04.getFont().deriveFont(button04.getFont().getSize()-6f));
        button04.setText(bundle.getString("CIFramePanel.button04.text")); 
        button04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button04ActionPerformed(evt);
            }
        });
        add(button04);
        button04.setBounds(30, 150, 57, 17);

        button05.setFont(button05.getFont().deriveFont(button05.getFont().getSize()-6f));
        button05.setText(bundle.getString("CIFramePanel.button05.text")); 
        button05.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button05ActionPerformed(evt);
            }
        });
        add(button05);
        button05.setBounds(30, 150, 57, 17);

        button06.setFont(button06.getFont().deriveFont(button06.getFont().getSize()-6f));
        button06.setText(bundle.getString("CIFramePanel.button06.text")); 
        button06.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button06ActionPerformed(evt);
            }
        });
        add(button06);
        button06.setBounds(30, 150, 57, 17);

        button07.setFont(button07.getFont().deriveFont(button07.getFont().getSize()-6f));
        button07.setText(bundle.getString("CIFramePanel.button07.text")); 
        button07.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button07ActionPerformed(evt);
            }
        });
        add(button07);
        button07.setBounds(30, 150, 57, 17);

        button08.setFont(button08.getFont().deriveFont(button08.getFont().getSize()-6f));
        button08.setText(bundle.getString("CIFramePanel.button08.text")); 
        button08.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button08ActionPerformed(evt);
            }
        });
        add(button08);
        button08.setBounds(30, 150, 57, 17);

        button09.setFont(button09.getFont().deriveFont(button09.getFont().getSize()-6f));
        button09.setText(bundle.getString("CIFramePanel.button09.text")); 
        button09.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button09ActionPerformed(evt);
            }
        });
        add(button09);
        button09.setBounds(30, 150, 57, 17);

        button10.setFont(button10.getFont().deriveFont(button10.getFont().getSize()-6f));
        button10.setText(bundle.getString("CIFramePanel.button10.text")); 
        button10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button10ActionPerformed(evt);
            }
        });
        add(button10);
        button10.setBounds(30, 150, 57, 17);

        lightLabel.setBackground(new java.awt.Color(255, 51, 51));
        lightLabel.setFont(lightLabel.getFont());
        lightLabel.setText(bundle.getString("CIFramePanel.lightLabel.text")); 
        lightLabel.setOpaque(true);
        lightLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lightLabelMouseClicked(evt);
            }
        });
        add(lightLabel);
        lightLabel.setBounds(40, 300, 70, 10);

        headerLabel.setBackground(new java.awt.Color(0, 0, 255));
        headerLabel.setFont(headerLabel.getFont());
        headerLabel.setText(bundle.getString("CIFramePanel.headerLabel.text")); 
        headerLabel.setOpaque(true);
        add(headerLabel);
        headerLabel.setBounds(40, 10, 200, 0);
    }

  private void formKeyPressed(java.awt.event.KeyEvent evt) {
    System.out.println("key pressed in framepanel2, keycode="+evt.getKeyCode()+",char="+evt.getKeyChar()+",id="+evt.getID()+",modifiers="+evt.getModifiers());
  }

  private void formMouseClicked(java.awt.event.MouseEvent evt) {
   instrument.uiPanel2.jComboBox36.setSelectedItem("");
       instrument.selectedUIAreaItem="";
  }

  private void formMouseDragged(java.awt.event.MouseEvent evt) {

  }

    private void stationListMouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox36.setSelectedItem("station list area");

    }

    private void deviceTableMouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox36.setSelectedItem("device table area");

    }

    private void chartPanelMouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox36.setSelectedItem("chart area");

    }

    private void dataPanelMouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox36.setSelectedItem("data area");

    }

    private void chartOptionPanelMouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox36.setSelectedItem("chart option area");

    }

    private void timeLabelMouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox36.setSelectedItem("time area");

    }

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {
       instrument.uiPanel2.jComboBox36.setSelectedItem("connect button");

    }

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {
       instrument.uiPanel2.jComboBox36.setSelectedItem("start button");

    }

    private void statusLabelMouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox36.setSelectedItem("light label area");

    }

    private void lightLabelMouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox36.setSelectedItem("light area");

    }

    private void button02ActionPerformed(java.awt.event.ActionEvent evt) {
       instrument.uiPanel2.jComboBox36.setSelectedItem("button 02");

    }

    private void button03ActionPerformed(java.awt.event.ActionEvent evt) {
       instrument.uiPanel2.jComboBox36.setSelectedItem("button 03");

    }

    private void button04ActionPerformed(java.awt.event.ActionEvent evt) {
       instrument.uiPanel2.jComboBox36.setSelectedItem("button 04");

    }

    private void button05ActionPerformed(java.awt.event.ActionEvent evt) {
       instrument.uiPanel2.jComboBox36.setSelectedItem("button 05");

    }

    private void button06ActionPerformed(java.awt.event.ActionEvent evt) {
       instrument.uiPanel2.jComboBox36.setSelectedItem("button 06");

    }

    private void button07ActionPerformed(java.awt.event.ActionEvent evt) {
       instrument.uiPanel2.jComboBox36.setSelectedItem("button 07");

    }

    private void button08ActionPerformed(java.awt.event.ActionEvent evt) {
       instrument.uiPanel2.jComboBox36.setSelectedItem("button 08");

    }

    private void button09ActionPerformed(java.awt.event.ActionEvent evt) {
       instrument.uiPanel2.jComboBox36.setSelectedItem("button 09");

    }

    private void button10ActionPerformed(java.awt.event.ActionEvent evt) {
       instrument.uiPanel2.jComboBox36.setSelectedItem("button 10");

    }

    private void stationListKeyPressed(java.awt.event.KeyEvent evt) {
      System.out.println("key pressed in station list, keycode="+evt.getKeyCode()+",char="+evt.getKeyChar()+",id="+evt.getID()+",modifiers="+evt.getModifiers());
    }

    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton button02;
    private javax.swing.JButton button03;
    private javax.swing.JButton button04;
    private javax.swing.JButton button05;
    private javax.swing.JButton button06;
    private javax.swing.JButton button07;
    private javax.swing.JButton button08;
    private javax.swing.JButton button09;
    private javax.swing.JButton button10;
    private javax.swing.JPanel chartOptionPanel;
    private javax.swing.JPanel chartPanel;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JPanel deviceTable;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JLabel lightLabel;
    private javax.swing.JPanel stationList;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel timeLabel;

}