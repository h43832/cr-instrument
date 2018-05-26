
package ci;

import static ci.CrInstrument.isNumeric;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import y.ylib.ylib;
import javax.swing.border.Border;

/**
 *
 * @author peter
 */
public class CIUIFramePanel extends javax.swing.JPanel {

  CrInstrument instrument;

    public ResourceBundle bundle2 = java.util.ResourceBundle.getBundle("ci/Bundle");
    double headerRatio=0.0;
    String frameColor="-1";
    ImageIcon status_label=null,status_light=null,headerImg=null;
  int localMousePressX=-1,localMousePressY=-1,globalMousePressX=-1,globalMousePressY=-1,
          mousePressType=-1;
  double originalX=-1.0,originalY=-1.0,originalWidth=-1.0,originalHeight=-1.0;
  public CIUIFramePanel(CrInstrument instrument) {
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
    setPanel("panel 01",panel01,panelWidth,panelHeight,"");
    setPanel("panel 02",panel02,panelWidth,panelHeight,"");
    setPanel("panel 03",panel03,panelWidth,panelHeight,"");
    setPanel("panel 04",panel04,panelWidth,panelHeight,"");
    setPanel("panel 05",panel05,panelWidth,panelHeight,"");
    setPanel("panel 06",panel06,panelWidth,panelHeight,"");
    setPanel("panel 07",panel07,panelWidth,panelHeight,"");
    setPanel("panel 08",panel08,panelWidth,panelHeight,"");
    setPanel("panel 09",panel09,panelWidth,panelHeight,"");
    setPanel("panel 10",panel10,panelWidth,panelHeight,"");

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
  setButton("button 11",button11,panelWidth,panelHeight);
  setButton("button 12",button12,panelWidth,panelHeight);
  setButton("button 13",button13,panelWidth,panelHeight);
  setButton("button 14",button14,panelWidth,panelHeight);
  setButton("button 15",button15,panelWidth,panelHeight);
  setButton("button 16",button16,panelWidth,panelHeight);
  setButton("button 17",button17,panelWidth,panelHeight);
  setButton("button 18",button18,panelWidth,panelHeight);
  setButton("button 19",button19,panelWidth,panelHeight);
  setButton("button 20",button20,panelWidth,panelHeight);
  setButton("button 21",button21,panelWidth,panelHeight);
  setButton("button 22",button22,panelWidth,panelHeight);
  setButton("button 23",button23,panelWidth,panelHeight);
  setButton("button 24",button24,panelWidth,panelHeight);
  setButton("button 25",button25,panelWidth,panelHeight);
  setButton("button 26",button26,panelWidth,panelHeight);
  setButton("button 27",button27,panelWidth,panelHeight);
  setButton("button 28",button28,panelWidth,panelHeight);
  setButton("button 29",button29,panelWidth,panelHeight);
  setButton("button 30",button30,panelWidth,panelHeight);
  setButton("connect button",btnConnect,panelWidth,panelHeight);
  setButton("start button",btnStart,panelWidth,panelHeight);
  setPanel("data area",dataPanel,panelWidth,panelHeight,bundle2.getString("CrInstrument.dataPanel.border.title"));
  setPanel("chart area",chartPanel,panelWidth,panelHeight,bundle2.getString("CrInstrument.chartPanel.border.title"));
  setPanel("device table area",deviceTable,panelWidth,panelHeight,bundle2.getString("CrInstrument.jScrollPane3.border.title"));
  setPanel("station list area",stationList,panelWidth,panelHeight,bundle2.getString("CrInstrument.jScrollPane2.border.title"));
  setLabel("light label area",statusLabel,panelWidth,panelHeight,status_label);
  setLabel("light area",lightLabel,panelWidth,panelHeight,status_light);
  setLabel("time area",timeLabel,panelWidth,panelHeight,null);
  setLabel("textlabel 01",textLabel01,panelWidth,panelHeight,null);
  setLabel("textlabel 02",textLabel02,panelWidth,panelHeight,null);
  setLabel("textlabel 03",textLabel03,panelWidth,panelHeight,null);
  setLabel("textlabel 04",textLabel04,panelWidth,panelHeight,null);
  setLabel("textlabel 05",textLabel05,panelWidth,panelHeight,null);
  setLabel("textlabel 06",textLabel06,panelWidth,panelHeight,null);
  setLabel("textlabel 07",textLabel07,panelWidth,panelHeight,null);
  setLabel("textlabel 08",textLabel08,panelWidth,panelHeight,null);
  setLabel("textlabel 09",textLabel09,panelWidth,panelHeight,null);
  setLabel("textlabel 10",textLabel10,panelWidth,panelHeight,null);
  setLabel("textlabel 11",textLabel11,panelWidth,panelHeight,null);
  setLabel("textlabel 12",textLabel12,panelWidth,panelHeight,null);
  setLabel("textlabel 13",textLabel13,panelWidth,panelHeight,null);
  setLabel("textlabel 14",textLabel14,panelWidth,panelHeight,null);
  setLabel("textlabel 15",textLabel15,panelWidth,panelHeight,null);
  setLabel("textlabel 16",textLabel16,panelWidth,panelHeight,null);
  setLabel("textlabel 17",textLabel17,panelWidth,panelHeight,null);
  setLabel("textlabel 18",textLabel18,panelWidth,panelHeight,null);
  setLabel("textlabel 19",textLabel19,panelWidth,panelHeight,null);
  setLabel("textlabel 20",textLabel20,panelWidth,panelHeight,null);
  setLabel("textlabel 21",textLabel21,panelWidth,panelHeight,null);
  setLabel("textlabel 22",textLabel22,panelWidth,panelHeight,null);
  setLabel("textlabel 23",textLabel23,panelWidth,panelHeight,null);
  setLabel("textlabel 24",textLabel24,panelWidth,panelHeight,null);
  setLabel("textlabel 25",textLabel25,panelWidth,panelHeight,null);
  setLabel("textlabel 26",textLabel26,panelWidth,panelHeight,null);
  setLabel("textlabel 27",textLabel27,panelWidth,panelHeight,null);
  setLabel("textlabel 28",textLabel28,panelWidth,panelHeight,null);
  setLabel("textlabel 29",textLabel29,panelWidth,panelHeight,null);
  setLabel("textlabel 30",textLabel30,panelWidth,panelHeight,null);

  setTextField("textfield 01",textField01,panelWidth,panelHeight);
  setTextField("textfield 02",textField02,panelWidth,panelHeight);
  setTextField("textfield 03",textField03,panelWidth,panelHeight);
  setTextField("textfield 04",textField04,panelWidth,panelHeight);
  setTextField("textfield 05",textField05,panelWidth,panelHeight);
  setTextField("textfield 06",textField06,panelWidth,panelHeight);
  setTextField("textfield 07",textField07,panelWidth,panelHeight);
  setTextField("textfield 08",textField08,panelWidth,panelHeight);
  setTextField("textfield 09",textField09,panelWidth,panelHeight);
  setTextField("textfield 10",textField10,panelWidth,panelHeight);
  setTextField("textfield 11",textField11,panelWidth,panelHeight);
  setTextField("textfield 12",textField12,panelWidth,panelHeight);
  setTextField("textfield 13",textField13,panelWidth,panelHeight);
  setTextField("textfield 14",textField14,panelWidth,panelHeight);
  setTextField("textfield 15",textField15,panelWidth,panelHeight);
  setTextField("textfield 16",textField16,panelWidth,panelHeight);
  setTextField("textfield 17",textField17,panelWidth,panelHeight);
  setTextField("textfield 18",textField18,panelWidth,panelHeight);
  setTextField("textfield 19",textField19,panelWidth,panelHeight);
  setTextField("textfield 20",textField20,panelWidth,panelHeight);
  setTextField("textfield 21",textField21,panelWidth,panelHeight);
  setTextField("textfield 22",textField22,panelWidth,panelHeight);
  setTextField("textfield 23",textField23,panelWidth,panelHeight);
  setTextField("textfield 24",textField24,panelWidth,panelHeight);
  setTextField("textfield 25",textField25,panelWidth,panelHeight);
  setTextField("textfield 26",textField26,panelWidth,panelHeight);
  setTextField("textfield 27",textField27,panelWidth,panelHeight);
  setTextField("textfield 28",textField28,panelWidth,panelHeight);
  setTextField("textfield 29",textField29,panelWidth,panelHeight);
  setTextField("textfield 30",textField30,panelWidth,panelHeight);
  setComboBox("combobox 01",comboBox01,panelWidth,panelHeight);
  setComboBox("combobox 02",comboBox02,panelWidth,panelHeight);
  setComboBox("combobox 03",comboBox03,panelWidth,panelHeight);
  setComboBox("combobox 04",comboBox04,panelWidth,panelHeight);
  setComboBox("combobox 05",comboBox05,panelWidth,panelHeight);
  setComboBox("combobox 06",comboBox06,panelWidth,panelHeight);
  setComboBox("combobox 07",comboBox07,panelWidth,panelHeight);
  setComboBox("combobox 08",comboBox08,panelWidth,panelHeight);
  setComboBox("combobox 09",comboBox09,panelWidth,panelHeight);
  setComboBox("combobox 10",comboBox10,panelWidth,panelHeight);
  setComboBox("combobox 11",comboBox11,panelWidth,panelHeight);
  setComboBox("combobox 12",comboBox12,panelWidth,panelHeight);
  setComboBox("combobox 13",comboBox13,panelWidth,panelHeight);
  setComboBox("combobox 14",comboBox14,panelWidth,panelHeight);
  setComboBox("combobox 15",comboBox15,panelWidth,panelHeight);
  setComboBox("combobox 16",comboBox16,panelWidth,panelHeight);
  setComboBox("combobox 17",comboBox17,panelWidth,panelHeight);
  setComboBox("combobox 18",comboBox18,panelWidth,panelHeight);
  setComboBox("combobox 19",comboBox19,panelWidth,panelHeight);
  setComboBox("combobox 20",comboBox20,panelWidth,panelHeight);
  setCheckBox("checkbox 01",checkBox01,panelWidth,panelHeight);
  setCheckBox("checkbox 02",checkBox02,panelWidth,panelHeight);
  setCheckBox("checkbox 03",checkBox03,panelWidth,panelHeight);
  setCheckBox("checkbox 04",checkBox04,panelWidth,panelHeight);
  setCheckBox("checkbox 05",checkBox05,panelWidth,panelHeight);
  setCheckBox("checkbox 06",checkBox06,panelWidth,panelHeight);
  setCheckBox("checkbox 07",checkBox07,panelWidth,panelHeight);
  setCheckBox("checkbox 08",checkBox08,panelWidth,panelHeight);
  setCheckBox("checkbox 09",checkBox09,panelWidth,panelHeight);
  setCheckBox("checkbox 10",checkBox10,panelWidth,panelHeight);
  setCheckBox("checkbox 11",checkBox11,panelWidth,panelHeight);
  setCheckBox("checkbox 12",checkBox12,panelWidth,panelHeight);
  setCheckBox("checkbox 13",checkBox13,panelWidth,panelHeight);
  setCheckBox("checkbox 14",checkBox14,panelWidth,panelHeight);
  setCheckBox("checkbox 15",checkBox15,panelWidth,panelHeight);
  setCheckBox("checkbox 16",checkBox16,panelWidth,panelHeight);
  setCheckBox("checkbox 17",checkBox17,panelWidth,panelHeight);
  setCheckBox("checkbox 18",checkBox18,panelWidth,panelHeight);
  setCheckBox("checkbox 19",checkBox19,panelWidth,panelHeight);
  setCheckBox("checkbox 20",checkBox20,panelWidth,panelHeight);
  setRadioButton("radiobutton 01",radioButton01,panelWidth,panelHeight);
  setRadioButton("radiobutton 02",radioButton02,panelWidth,panelHeight);
  setRadioButton("radiobutton 03",radioButton03,panelWidth,panelHeight);
  setRadioButton("radiobutton 04",radioButton04,panelWidth,panelHeight);
  setRadioButton("radiobutton 05",radioButton05,panelWidth,panelHeight);
  setRadioButton("radiobutton 06",radioButton06,panelWidth,panelHeight);
  setRadioButton("radiobutton 07",radioButton07,panelWidth,panelHeight);
  setRadioButton("radiobutton 08",radioButton08,panelWidth,panelHeight);
  setRadioButton("radiobutton 09",radioButton09,panelWidth,panelHeight);
  setRadioButton("radiobutton 10",radioButton10,panelWidth,panelHeight);
  setRadioButton("radiobutton 11",radioButton11,panelWidth,panelHeight);
  setRadioButton("radiobutton 12",radioButton12,panelWidth,panelHeight);
  setRadioButton("radiobutton 13",radioButton13,panelWidth,panelHeight);
  setRadioButton("radiobutton 14",radioButton14,panelWidth,panelHeight);
  setRadioButton("radiobutton 15",radioButton15,panelWidth,panelHeight);
  setRadioButton("radiobutton 16",radioButton16,panelWidth,panelHeight);
  setRadioButton("radiobutton 17",radioButton17,panelWidth,panelHeight);
  setRadioButton("radiobutton 18",radioButton18,panelWidth,panelHeight);
  setRadioButton("radiobutton 19",radioButton19,panelWidth,panelHeight);
  setRadioButton("radiobutton 20",radioButton20,panelWidth,panelHeight);

  setPanel("chart option area",chartOptionPanel,panelWidth,panelHeight,bundle2.getString("CrInstrument.chartOptionPanel.border.title"));

 }
  if(!instrument.initStage) instrument.updateUILayoutAll=false;
  }
   void setLabel(String key,JLabel label,int frameWidth,int frameHeight,ImageIcon icon){
     String info[];
     if(instrument.editUI.get(key)!=null){
         info=ylib.csvlinetoarray((String)instrument.editUI.get(key));
         if(info.length>2 && info[2].equalsIgnoreCase("s")){
           int x2=0,y2=0,width2=0,height2=0;
           if(info.length > 3 && info[3].length()>0) x2=(int)(Double.parseDouble(info[3]) * ((double)frameWidth));
           if(info.length > 4 && info[4].length()>0) y2=(int)(Double.parseDouble(info[4]) * ((double)frameHeight))+(int)(((double)frameHeight)*headerRatio);
           if(info.length > 5 && info[5].length()>0) width2=(int)(Double.parseDouble(info[5]) * ((double)frameWidth));
           if(info.length > 6 && info[6].length()>0) height2=(int)(Double.parseDouble(info[6]) * ((double)frameHeight));
           label.setBounds(x2, y2,width2,height2);
           if(instrument.updateUILayoutAll){
           if(icon!=null){
             label.setIcon(icon);
           }
           Font font=label.getFont();
           if(info[1].trim().length()>0) label.setText(info[1]);
           int fontSize=(int)Math.round(((double)(instrument.isNumeric(info[9])? Integer.parseInt(info[9]):label.getFont().getSize()))/2.0);
           label.setFont(instrument.getFont(font,font.getSize(),info[8],""+fontSize,info[11].equalsIgnoreCase("b"),info[12].equalsIgnoreCase("i")));
           label.setBackground((info.length>7 && info[7].length()>0 && instrument.isNumeric(info[7]))? new Color(Integer.parseInt(info[7])):label.getBackground());
           label.setForeground((info.length>10 && info[10].length()>0 && instrument.isNumeric(info[10]))? new Color(Integer.parseInt(info[10])):label.getForeground());
           label.setOpaque((info.length>13 && info[13].equalsIgnoreCase("o")));
            if(instrument.selectedUIAreaItem.length()>0 && instrument.selectedUIAreaItem.equalsIgnoreCase(key)){
             Border compound;
             Border redline = BorderFactory.createLineBorder(Color.red);
            label.setBorder(redline); 
         } else {
                Border emptyBorder = BorderFactory.createEmptyBorder();
               label.setBorder(emptyBorder);
           }
           }
           label.setVisible(true);
         } else label.setVisible(false);
       } else if(instrument.editUI.size()>0) instrument.sysLog("Warning: label key '"+key+"' not found in editUI.");
   }
     void setTextField(String key,JTextField textfield,int frameWidth,int frameHeight){
     String info[];
     if(instrument.editUI.get(key)!=null){
         info=ylib.csvlinetoarray((String)instrument.editUI.get(key));
         if(info.length>2 && info[2].equalsIgnoreCase("s")){
           int x2=0,y2=0,width2=0,height2=0;
           if(info.length > 3 && info[3].length()>0) x2=(int)(Double.parseDouble(info[3]) * ((double)frameWidth));
           if(info.length > 4 && info[4].length()>0) y2=(int)(Double.parseDouble(info[4]) * ((double)frameHeight))+(int)(((double)frameHeight)*headerRatio);
           if(info.length > 5 && info[5].length()>0) width2=(int)(Double.parseDouble(info[5]) * ((double)frameWidth));
           if(info.length > 6 && info[6].length()>0) height2=(int)(Double.parseDouble(info[6]) * ((double)frameHeight));
           textfield.setBounds(x2, y2,width2,height2);
           Font font=textfield.getFont();
           textfield.setText(info[1]);
           int fontSize=(int)Math.round(((double)(instrument.isNumeric(info[9])? Integer.parseInt(info[9]):textfield.getFont().getSize()))/2.0);
           textfield.setFont(instrument.getFont(font,font.getSize(),info[8],""+fontSize,info[11].equalsIgnoreCase("b"),info[12].equalsIgnoreCase("i")));
           textfield.setOpaque((info.length>13 && info[13].equalsIgnoreCase("o")));
           textfield.setBackground((info.length>7 && info[7].length()>0 && instrument.isNumeric(info[7]))? new Color(Integer.parseInt(info[7])):Color.white);
           textfield.setForeground((info.length>10 && info[10].length()>0 && instrument.isNumeric(info[10]))? new Color(Integer.parseInt(info[10])):textfield.getForeground());
            if(instrument.selectedUIAreaItem.length()>0 && instrument.selectedUIAreaItem.equalsIgnoreCase(key)){
             Border compound;
             Border redline = BorderFactory.createLineBorder(Color.red);
            textfield.setBorder(redline); 
         } else {
                Border emptyBorder = BorderFactory.createEmptyBorder();
               textfield.setBorder(emptyBorder);
           }
           textfield.setVisible(true);
         } else textfield.setVisible(false);
       } else if(instrument.editUI.size()>0) instrument.sysLog("Warning: textfield key '"+key+"' not found in editUI.");
   }
   void setComboBox(String key,JComboBox comboBox,int frameWidth,int frameHeight){
     String info[];
     if(instrument.editUI.get(key)!=null){
         info=ylib.csvlinetoarray((String)instrument.editUI.get(key));
         if(info.length>2 && info[2].equalsIgnoreCase("s")){
           int x2=0,y2=0,width2=0,height2=0;
           if(info.length > 3 && info[3].length()>0) x2=(int)(Double.parseDouble(info[3]) * ((double)frameWidth));
           if(info.length > 4 && info[4].length()>0) y2=(int)(Double.parseDouble(info[4]) * ((double)frameHeight))+(int)(((double)frameHeight)*headerRatio);
           if(info.length > 5 && info[5].length()>0) width2=(int)(Double.parseDouble(info[5]) * ((double)frameWidth));
           if(info.length > 6 && info[6].length()>0) height2=(int)(Double.parseDouble(info[6]) * ((double)frameHeight));
           comboBox.setBounds(x2, y2,width2,height2);
           if(instrument.updateUILayoutAll){
           Font font=comboBox.getFont();

           int fontSize=(int)Math.round(((double)(instrument.isNumeric(info[9])? Integer.parseInt(info[9]):comboBox.getFont().getSize()))/2.0);
           comboBox.setFont(instrument.getFont(font,font.getSize(),info[8],""+fontSize,info[11].equalsIgnoreCase("b"),info[12].equalsIgnoreCase("i")));
           comboBox.setBackground((info.length>7 && info[7].length()>0 && instrument.isNumeric(info[7]))? new Color(Integer.parseInt(info[7])):comboBox.getBackground());
           comboBox.setForeground((info.length>10 && info[10].length()>0 && instrument.isNumeric(info[10]))? new Color(Integer.parseInt(info[10])):comboBox.getForeground());
            if(instrument.selectedUIAreaItem.length()>0 && instrument.selectedUIAreaItem.equalsIgnoreCase(key)){
             Border compound;
             Border redline = BorderFactory.createLineBorder(Color.red);
            comboBox.setBorder(redline); 
         } else {
                Border emptyBorder = BorderFactory.createEmptyBorder();
               comboBox.setBorder(emptyBorder);
           }
           }
           comboBox.setVisible(true);
         } else comboBox.setVisible(false);
       } else if(instrument.editUI.size()>0) instrument.sysLog("Warning: textfield key '"+key+"' not found in editUI.");
   }
  void setCheckBox(String key,JCheckBox checkbox,int frameWidth,int frameHeight){
     String info[];
     if(instrument.editUI.get(key)!=null){
         info=ylib.csvlinetoarray((String)instrument.editUI.get(key));
         if(info.length>2 && info[2].equalsIgnoreCase("s")){
           int x2=0,y2=0,width2=0,height2=0;
           if(info.length > 3 && info[3].length()>0) x2=(int)(Double.parseDouble(info[3]) * ((double)frameWidth));
           if(info.length > 4 && info[4].length()>0) y2=(int)(Double.parseDouble(info[4]) * ((double)frameHeight))+(int)(((double)frameHeight)*headerRatio);
           if(info.length > 5 && info[5].length()>0) width2=(int)(Double.parseDouble(info[5]) * ((double)frameWidth));
           if(info.length > 6 && info[6].length()>0) height2=(int)(Double.parseDouble(info[6]) * ((double)frameHeight));
           checkbox.setBounds(x2, y2,width2,height2);
           if(instrument.updateUILayoutAll){
           Font font=checkbox.getFont();
           if(info[1].trim().length()>0) checkbox.setText(info[1]);
           int fontSize=(int)Math.round(((double)(instrument.isNumeric(info[9])? Integer.parseInt(info[9]):checkbox.getFont().getSize()))/2.0);
           checkbox.setFont(instrument.getFont(font,font.getSize(),info[8],""+fontSize,info[11].equalsIgnoreCase("b"),info[12].equalsIgnoreCase("i")));
           checkbox.setBackground((info.length>7 && info[7].length()>0 && instrument.isNumeric(info[7]))? new Color(Integer.parseInt(info[7])):checkbox.getBackground());
           checkbox.setForeground((info.length>10 && info[10].length()>0 && instrument.isNumeric(info[10]))? new Color(Integer.parseInt(info[10])):checkbox.getForeground());

           if(instrument.selectedUIAreaItem.length()>0 && instrument.selectedUIAreaItem.equalsIgnoreCase(key)){
             Border compound;
             Border redline = BorderFactory.createLineBorder(Color.red);
             checkbox.setBorderPainted(true);
            checkbox.setBorder(redline); 
         } else {
               Border emptyBorder = BorderFactory.createEmptyBorder();
               checkbox.setBorder(emptyBorder);
           }
           }
           checkbox.setVisible(true);
         } else checkbox.setVisible(false);
       } else if(instrument.editUI.size()>0) instrument.sysLog("Warning: label key '"+key+"' not found in editUI.");
   }
   void setRadioButton(String key,JRadioButton radiobutton,int frameWidth,int frameHeight){
     String info[];
     if(instrument.editUI.get(key)!=null){
         info=ylib.csvlinetoarray((String)instrument.editUI.get(key));
         if(info.length>2 && info[2].equalsIgnoreCase("s")){
           int x2=0,y2=0,width2=0,height2=0;
           if(info.length > 3 && info[3].length()>0) x2=(int)(Double.parseDouble(info[3]) * ((double)frameWidth));
           if(info.length > 4 && info[4].length()>0) y2=(int)(Double.parseDouble(info[4]) * ((double)frameHeight))+(int)(((double)frameHeight)*headerRatio);
           if(info.length > 5 && info[5].length()>0) width2=(int)(Double.parseDouble(info[5]) * ((double)frameWidth));
           if(info.length > 6 && info[6].length()>0) height2=(int)(Double.parseDouble(info[6]) * ((double)frameHeight));
           radiobutton.setBounds(x2, y2,width2,height2);
           if(instrument.updateUILayoutAll){
           Font font=radiobutton.getFont();
           if(info[1].trim().length()>0) radiobutton.setText(info[1]);
           int fontSize=(int)Math.round(((double)(instrument.isNumeric(info[9])? Integer.parseInt(info[9]):radiobutton.getFont().getSize()))/2.0);
           radiobutton.setFont(instrument.getFont(font,font.getSize(),info[8],""+fontSize,info[11].equalsIgnoreCase("b"),info[12].equalsIgnoreCase("i")));
           radiobutton.setBackground((info.length>7 && info[7].length()>0 && instrument.isNumeric(info[7]))? new Color(Integer.parseInt(info[7])):radiobutton.getBackground());
           radiobutton.setForeground((info.length>10 && info[10].length()>0 && instrument.isNumeric(info[10]))? new Color(Integer.parseInt(info[10])):radiobutton.getForeground());
           radiobutton.setSelected((info.length>19 && info[19].equalsIgnoreCase("s")));
            if(instrument.selectedUIAreaItem.length()>0 && instrument.selectedUIAreaItem.equalsIgnoreCase(key)){
             Border compound;
             Border redline = BorderFactory.createLineBorder(Color.red);
             radiobutton.setBorderPainted(true);
            radiobutton.setBorder(redline); 
         } else {
                Border emptyBorder = BorderFactory.createEmptyBorder();
               radiobutton.setBorder(emptyBorder);
           }
           }
           radiobutton.setVisible(true);
         } else radiobutton.setVisible(false);
       } else if(instrument.editUI.size()>0) instrument.sysLog("Warning: label key '"+key+"' not found in editUI.");
   }
    void setPanel(String key,JPanel panel,int frameWidth,int frameHeight,String title){
      String info[];

     if(instrument.editUI.get(key)!=null){
    info=ylib.csvlinetoarray((String)instrument.editUI.get(key));

    if(info.length>2 && info[2].equalsIgnoreCase("s")){
        int x=0,y=0,width=0,height=0;
        if(info.length > 3 && info[3].length()>0) x=(int)(Double.parseDouble(info[3]) * ((double)frameWidth));
        if(info.length > 4 && info[4].length()>0) y=(int)(Double.parseDouble(info[4]) * ((double)frameHeight))+(int)(((double)frameHeight)*headerRatio);
        if(info.length > 5 && info[5].length()>0) width=(int)(Double.parseDouble(info[5]) * ((double)frameWidth));
        if(info.length > 6 && info[6].length()>0) height=(int)(Double.parseDouble(info[6]) * ((double)frameHeight));
        panel.setBounds(x, y,width,height);
        if(instrument.updateUILayoutAll){
        panel.setBackground((info.length>7 && info[7].length()>0 && instrument.isNumeric(info[7]))? new Color(Integer.parseInt(info[7])):panel.getBackground());
        panel.setForeground((info.length>10 && info[10].length()>0 && instrument.isNumeric(info[10]))? new Color(Integer.parseInt(info[10])):panel.getForeground());
        panel.setOpaque((info.length>13 && info[13].equalsIgnoreCase("o")));
        if(title.length()<1) title=info[1];
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
        }
        panel.setVisible(true);
    } else panel.setVisible(false);
  } else if(instrument.editUI.size()>0) instrument.sysLog("Warning: panel key '"+key+"' not found in editUI.");
  }
   void setButton(String key,JButton button,int frameWidth,int frameHeight){
     String info[];
       if(instrument.editUI.get(key)!=null){
       info=ylib.csvlinetoarray((String)instrument.editUI.get(key));
       if(info.length>2 && info[2].equalsIgnoreCase("s")){
        int x=0,y=0,width=0,height=0;
        if(info.length>1 && info[1].length() >0 && !(info[0].equalsIgnoreCase("connect button") || info[0].equalsIgnoreCase("start button"))) button.setText(info[1]);
        if(info.length > 3 && info[3].length()>0) x=(int)(Double.parseDouble(info[3]) * ((double)frameWidth));
        if(info.length > 4 && info[4].length()>0) y=(int)(Double.parseDouble(info[4]) * ((double)frameHeight))+(int)(((double)frameHeight)*headerRatio);
        if(info.length > 5 && info[5].length()>0) width=(int)(Double.parseDouble(info[5]) * ((double)frameWidth));
        if(info.length > 6 && info[6].length()>0) height=(int)(Double.parseDouble(info[6]) * ((double)frameHeight));
        button.setBounds(x, y,width,height);
        if(instrument.updateUILayoutAll){
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
        }
        button.setVisible(true);
    } else  button.setVisible(false);
  }   else if(instrument.editUI.size()>0) instrument.sysLog("Warning: button key '"+key+"' not found in editUI.");
   }
   void updateAreaItem(){
    if(instrument.skipUITFChanged) return;
    if(instrument.uiPanel2.jComboBox36.getSelectedIndex()!=-1){
    String sel=(String)instrument.uiPanel2.jComboBox36.getSelectedItem();
    if(instrument.editUI.get(sel)==null) return;
    String info[]=ylib.csvlinetoarray((String)instrument.editUI.get(sel));
    if(instrument.uiPanel2.cbShowFrameItem.isSelected()) info[2]="s"; else info[2]="e";
    info[1]= instrument.uiPanel2.jTextField3.getText().trim();
    info[3]= (instrument.isNumeric(instrument.uiPanel2.jTextField5.getText())? String.valueOf(Double.parseDouble(instrument.uiPanel2.jTextField5.getText())/100.0):"0.0");
    info[4]= (instrument.isNumeric(instrument.uiPanel2.jTextField9.getText())? String.valueOf(Double.parseDouble(instrument.uiPanel2.jTextField9.getText())/100.0):"0.0");
    info[5]= (instrument.isNumeric(instrument.uiPanel2.jTextField10.getText())? String.valueOf(Double.parseDouble(instrument.uiPanel2.jTextField10.getText())/100.0):"0.0");
    info[6]= (instrument.isNumeric(instrument.uiPanel2.jTextField61.getText())? String.valueOf(Double.parseDouble(instrument.uiPanel2.jTextField61.getText())/100.0):"0.0");
    info[8]=(String)instrument.uiPanel2.jComboBox53.getSelectedItem();
    if(info[8].equals(bundle2.getString("CrInstrument.xy.msg150"))) info[8]="";
    info[9]= instrument.uiPanel2.jTextField73.getText().trim();
    if(instrument.uiPanel2.jCheckBox3.isSelected()) info[11]="b"; else info[11]="e";
    if(instrument.uiPanel2.jCheckBox46.isSelected()) info[12]="i"; else info[12]="e";
    info[7]=""+instrument.uiPanel2.jLabel63.getBackground().getRGB();
    info[10]=""+instrument.uiPanel2.jLabel62.getBackground().getRGB();
    if(instrument.uiPanel2.jCheckBox5.isSelected()) info[13]="o"; else info[13]="e";

    info[15]=(String)instrument.uiPanel2.jComboBox2.getSelectedItem();
    info[16]=ylib.tocsv(instrument.uiPanel2.jTextField8.getText().trim());
    info[17]=instrument.uiPanel2.jTextField12.getText().trim();
    info[18]=""+instrument.uiPanel2.jLabel2.getBackground().getRGB();
    info[19]=(instrument.uiPanel2.jCheckBox7.isSelected()? "s":"e");

    instrument.editUI.put(sel,ylib.arrayToCsvLine(info));
    instrument.updateUILayoutAll=true;
    invalidate();
    if(sel.equalsIgnoreCase("data area")) {

        instrument.uiPanel2.updateDataAreaPanel();
        instrument.updateUIDALayoutAll=true;
        instrument.uiDataPanel.invalidate();
    }
     }
   }
   void updateAreaItem_DataArea(){
    if(instrument.skipUITFChanged) return;
    String sel="data area";
    if(instrument.editUI.get(sel)==null) return;
    String info[]=ylib.csvlinetoarray((String)instrument.editUI.get(sel));
    info[3]= (instrument.isNumeric(instrument.uiPanel2.jTextField6.getText())? String.valueOf(Double.parseDouble(instrument.uiPanel2.jTextField6.getText())/100.0):"0.0");
    info[4]= (instrument.isNumeric(instrument.uiPanel2.jTextField7.getText())? String.valueOf(Double.parseDouble(instrument.uiPanel2.jTextField7.getText())/100.0):"0.0");
    info[5]= (instrument.isNumeric(instrument.uiPanel2.jTextField1.getText())? String.valueOf(Double.parseDouble(instrument.uiPanel2.jTextField1.getText())/100.0):"0.0");
    info[6]= (instrument.isNumeric(instrument.uiPanel2.jTextField2.getText())? String.valueOf(Double.parseDouble(instrument.uiPanel2.jTextField2.getText())/100.0):"0.0");
    info[9]= instrument.uiPanel2.jTextField4.getText().trim();
    if(instrument.uiPanel2.jCheckBox2.isSelected()) info[11]="b"; else info[11]="e";
    if(instrument.uiPanel2.jCheckBox4.isSelected()) info[12]="i"; else info[12]="e";
    info[7]=""+instrument.uiPanel2.jLabel14.getBackground().getRGB();
    info[8]=(String)instrument.uiPanel2.jComboBox1.getSelectedItem();
    if(info[8].equals(bundle2.getString("CrInstrument.xy.msg150"))) info[8]="";
    info[10]=""+instrument.uiPanel2.jLabel10.getBackground().getRGB();
    if(instrument.uiPanel2.jCheckBox6.isSelected()) info[13]="o"; else info[12]="e";
    info[18]=""+instrument.uiPanel2.jLabel4.getBackground().getRGB();

    instrument.editUI.put(sel,ylib.arrayToCsvLine(info));
    invalidate();

        instrument.uiPanel2.updateDataAreaPanel();
        instrument.updateUIDALayoutAll=true;
        instrument.uiDataPanel.invalidate();
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
        textLabel01 = new javax.swing.JLabel();
        comboBox01 = new javax.swing.JComboBox();
        textField01 = new javax.swing.JTextField();
        textField02 = new javax.swing.JTextField();
        textField03 = new javax.swing.JTextField();
        textField04 = new javax.swing.JTextField();
        textField05 = new javax.swing.JTextField();
        textField06 = new javax.swing.JTextField();
        textField07 = new javax.swing.JTextField();
        textField08 = new javax.swing.JTextField();
        textField09 = new javax.swing.JTextField();
        textField10 = new javax.swing.JTextField();
        textField11 = new javax.swing.JTextField();
        textField12 = new javax.swing.JTextField();
        textField13 = new javax.swing.JTextField();
        textField14 = new javax.swing.JTextField();
        textField15 = new javax.swing.JTextField();
        textField16 = new javax.swing.JTextField();
        textField17 = new javax.swing.JTextField();
        textField18 = new javax.swing.JTextField();
        textField19 = new javax.swing.JTextField();
        textField20 = new javax.swing.JTextField();
        textField21 = new javax.swing.JTextField();
        textField22 = new javax.swing.JTextField();
        textField23 = new javax.swing.JTextField();
        textField24 = new javax.swing.JTextField();
        textField25 = new javax.swing.JTextField();
        textField26 = new javax.swing.JTextField();
        textField27 = new javax.swing.JTextField();
        textField28 = new javax.swing.JTextField();
        textField29 = new javax.swing.JTextField();
        textField30 = new javax.swing.JTextField();
        textLabel02 = new javax.swing.JLabel();
        textLabel03 = new javax.swing.JLabel();
        textLabel04 = new javax.swing.JLabel();
        textLabel05 = new javax.swing.JLabel();
        textLabel06 = new javax.swing.JLabel();
        textLabel07 = new javax.swing.JLabel();
        textLabel08 = new javax.swing.JLabel();
        textLabel09 = new javax.swing.JLabel();
        textLabel10 = new javax.swing.JLabel();
        textLabel11 = new javax.swing.JLabel();
        textLabel12 = new javax.swing.JLabel();
        textLabel13 = new javax.swing.JLabel();
        textLabel14 = new javax.swing.JLabel();
        textLabel15 = new javax.swing.JLabel();
        textLabel16 = new javax.swing.JLabel();
        textLabel17 = new javax.swing.JLabel();
        textLabel18 = new javax.swing.JLabel();
        textLabel19 = new javax.swing.JLabel();
        textLabel20 = new javax.swing.JLabel();
        textLabel21 = new javax.swing.JLabel();
        textLabel22 = new javax.swing.JLabel();
        textLabel23 = new javax.swing.JLabel();
        textLabel24 = new javax.swing.JLabel();
        textLabel25 = new javax.swing.JLabel();
        textLabel26 = new javax.swing.JLabel();
        textLabel27 = new javax.swing.JLabel();
        textLabel28 = new javax.swing.JLabel();
        textLabel29 = new javax.swing.JLabel();
        textLabel30 = new javax.swing.JLabel();
        comboBox02 = new javax.swing.JComboBox();
        comboBox03 = new javax.swing.JComboBox();
        comboBox04 = new javax.swing.JComboBox();
        comboBox05 = new javax.swing.JComboBox();
        comboBox06 = new javax.swing.JComboBox();
        comboBox07 = new javax.swing.JComboBox();
        comboBox08 = new javax.swing.JComboBox();
        comboBox09 = new javax.swing.JComboBox();
        comboBox10 = new javax.swing.JComboBox();
        comboBox11 = new javax.swing.JComboBox();
        comboBox12 = new javax.swing.JComboBox();
        comboBox13 = new javax.swing.JComboBox();
        comboBox14 = new javax.swing.JComboBox();
        comboBox15 = new javax.swing.JComboBox();
        comboBox16 = new javax.swing.JComboBox();
        comboBox17 = new javax.swing.JComboBox();
        comboBox18 = new javax.swing.JComboBox();
        comboBox19 = new javax.swing.JComboBox();
        comboBox20 = new javax.swing.JComboBox();
        button25 = new javax.swing.JButton();
        button26 = new javax.swing.JButton();
        button27 = new javax.swing.JButton();
        button28 = new javax.swing.JButton();
        button29 = new javax.swing.JButton();
        button30 = new javax.swing.JButton();
        button11 = new javax.swing.JButton();
        button12 = new javax.swing.JButton();
        button13 = new javax.swing.JButton();
        button14 = new javax.swing.JButton();
        button15 = new javax.swing.JButton();
        button16 = new javax.swing.JButton();
        button17 = new javax.swing.JButton();
        button18 = new javax.swing.JButton();
        button19 = new javax.swing.JButton();
        button20 = new javax.swing.JButton();
        button21 = new javax.swing.JButton();
        button22 = new javax.swing.JButton();
        button23 = new javax.swing.JButton();
        button24 = new javax.swing.JButton();
        checkBox01 = new javax.swing.JCheckBox();
        radioButton01 = new javax.swing.JRadioButton();
        checkBox02 = new javax.swing.JCheckBox();
        checkBox03 = new javax.swing.JCheckBox();
        checkBox04 = new javax.swing.JCheckBox();
        checkBox05 = new javax.swing.JCheckBox();
        checkBox06 = new javax.swing.JCheckBox();
        checkBox07 = new javax.swing.JCheckBox();
        checkBox08 = new javax.swing.JCheckBox();
        checkBox09 = new javax.swing.JCheckBox();
        checkBox10 = new javax.swing.JCheckBox();
        checkBox11 = new javax.swing.JCheckBox();
        checkBox12 = new javax.swing.JCheckBox();
        checkBox13 = new javax.swing.JCheckBox();
        checkBox14 = new javax.swing.JCheckBox();
        checkBox15 = new javax.swing.JCheckBox();
        checkBox16 = new javax.swing.JCheckBox();
        checkBox17 = new javax.swing.JCheckBox();
        checkBox18 = new javax.swing.JCheckBox();
        checkBox19 = new javax.swing.JCheckBox();
        checkBox20 = new javax.swing.JCheckBox();
        radioButton02 = new javax.swing.JRadioButton();
        radioButton03 = new javax.swing.JRadioButton();
        radioButton04 = new javax.swing.JRadioButton();
        radioButton05 = new javax.swing.JRadioButton();
        radioButton06 = new javax.swing.JRadioButton();
        radioButton07 = new javax.swing.JRadioButton();
        radioButton08 = new javax.swing.JRadioButton();
        radioButton09 = new javax.swing.JRadioButton();
        radioButton10 = new javax.swing.JRadioButton();
        radioButton11 = new javax.swing.JRadioButton();
        radioButton12 = new javax.swing.JRadioButton();
        radioButton13 = new javax.swing.JRadioButton();
        radioButton14 = new javax.swing.JRadioButton();
        radioButton15 = new javax.swing.JRadioButton();
        radioButton16 = new javax.swing.JRadioButton();
        radioButton17 = new javax.swing.JRadioButton();
        radioButton18 = new javax.swing.JRadioButton();
        radioButton19 = new javax.swing.JRadioButton();
        radioButton20 = new javax.swing.JRadioButton();
        chartOptionPanel = new javax.swing.JPanel();
        dataPanel = new javax.swing.JPanel();
        chartPanel = new javax.swing.JPanel();
        deviceTable = new javax.swing.JPanel();
        stationList = new javax.swing.JPanel();
        panel01 = new javax.swing.JPanel();
        panel02 = new javax.swing.JPanel();
        panel03 = new javax.swing.JPanel();
        panel04 = new javax.swing.JPanel();
        panel05 = new javax.swing.JPanel();
        panel06 = new javax.swing.JPanel();
        panel07 = new javax.swing.JPanel();
        panel08 = new javax.swing.JPanel();
        panel09 = new javax.swing.JPanel();
        panel10 = new javax.swing.JPanel();

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

        timeLabel.setFont(timeLabel.getFont().deriveFont(timeLabel.getFont().getSize()-6f));
        timeLabel.setText("2017/07/10 09:47:12");
        timeLabel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                timeLabelMouseDragged(evt);
            }
        });
        timeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timeLabelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                timeLabelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                timeLabelMouseReleased(evt);
            }
        });
        add(timeLabel);
        timeLabel.setBounds(30, 200, 60, 8);

        btnConnect.setFont(btnConnect.getFont().deriveFont(btnConnect.getFont().getSize()-6f));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ci/Bundle"); 
        btnConnect.setText(bundle.getString("CIUIFramePanel.btnConnect.text")); 
        btnConnect.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                btnConnectMouseDragged(evt);
            }
        });
        btnConnect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnConnectMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnConnectMouseReleased(evt);
            }
        });
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });
        add(btnConnect);
        btnConnect.setBounds(30, 210, 53, 17);

        button02.setFont(button02.getFont().deriveFont(button02.getFont().getSize()-6f));
        button02.setText("Button02");
        button02.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button02MouseDragged(evt);
            }
        });
        button02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button02MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button02MouseReleased(evt);
            }
        });
        button02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button02ActionPerformed(evt);
            }
        });
        add(button02);
        button02.setBounds(30, 230, 55, 17);

        btnStart.setFont(btnStart.getFont().deriveFont(btnStart.getFont().getSize()-6f));
        btnStart.setText(bundle.getString("CIUIFramePanel.btnStart.text")); 
        btnStart.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                btnStartMouseDragged(evt);
            }
        });
        btnStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnStartMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnStartMouseReleased(evt);
            }
        });
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        add(btnStart);
        btnStart.setBounds(30, 250, 45, 17);

        button03.setFont(button03.getFont().deriveFont(button03.getFont().getSize()-6f));
        button03.setText("Button 03");
        button03.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button03MouseDragged(evt);
            }
        });
        button03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button03MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button03MouseReleased(evt);
            }
        });
        button03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button03ActionPerformed(evt);
            }
        });
        add(button03);
        button03.setBounds(30, 180, 57, 17);

        statusLabel.setBackground(new java.awt.Color(51, 255, 255));
        statusLabel.setOpaque(true);
        statusLabel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                statusLabelMouseDragged(evt);
            }
        });
        statusLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                statusLabelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                statusLabelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                statusLabelMouseReleased(evt);
            }
        });
        add(statusLabel);
        statusLabel.setBounds(40, 270, 80, 20);

        button04.setFont(button04.getFont().deriveFont(button04.getFont().getSize()-6f));
        button04.setText("Button 04");
        button04.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button04MouseDragged(evt);
            }
        });
        button04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button04MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button04MouseReleased(evt);
            }
        });
        button04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button04ActionPerformed(evt);
            }
        });
        add(button04);
        button04.setBounds(30, 180, 57, 17);

        button05.setFont(button05.getFont().deriveFont(button05.getFont().getSize()-6f));
        button05.setText("Button 05");
        button05.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button05MouseDragged(evt);
            }
        });
        button05.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button05MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button05MouseReleased(evt);
            }
        });
        button05.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button05ActionPerformed(evt);
            }
        });
        add(button05);
        button05.setBounds(30, 180, 57, 17);

        button06.setFont(button06.getFont().deriveFont(button06.getFont().getSize()-6f));
        button06.setText("Button 06");
        button06.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button06MouseDragged(evt);
            }
        });
        button06.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button06MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button06MouseReleased(evt);
            }
        });
        button06.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button06ActionPerformed(evt);
            }
        });
        add(button06);
        button06.setBounds(30, 180, 57, 17);

        button07.setFont(button07.getFont().deriveFont(button07.getFont().getSize()-6f));
        button07.setText("Button 07");
        button07.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button07MouseDragged(evt);
            }
        });
        button07.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button07MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button07MouseReleased(evt);
            }
        });
        button07.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button07ActionPerformed(evt);
            }
        });
        add(button07);
        button07.setBounds(30, 180, 57, 17);

        button08.setFont(button08.getFont().deriveFont(button08.getFont().getSize()-6f));
        button08.setText("Button 08");
        button08.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button08MouseDragged(evt);
            }
        });
        button08.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button08MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button08MouseReleased(evt);
            }
        });
        button08.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button08ActionPerformed(evt);
            }
        });
        add(button08);
        button08.setBounds(30, 180, 57, 17);

        button09.setFont(button09.getFont().deriveFont(button09.getFont().getSize()-6f));
        button09.setText("Button 09");
        button09.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button09MouseDragged(evt);
            }
        });
        button09.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button09MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button09MouseReleased(evt);
            }
        });
        button09.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button09ActionPerformed(evt);
            }
        });
        add(button09);
        button09.setBounds(30, 180, 57, 17);

        button10.setFont(button10.getFont().deriveFont(button10.getFont().getSize()-6f));
        button10.setText("Button 10");
        button10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button10MouseDragged(evt);
            }
        });
        button10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button10MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button10MouseReleased(evt);
            }
        });
        button10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button10ActionPerformed(evt);
            }
        });
        add(button10);
        button10.setBounds(30, 180, 57, 17);

        lightLabel.setBackground(new java.awt.Color(255, 51, 51));
        lightLabel.setFont(lightLabel.getFont());
        lightLabel.setText(bundle.getString("CIUIFramePanel.lightLabel.text")); 
        lightLabel.setOpaque(true);
        lightLabel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                lightLabelMouseDragged(evt);
            }
        });
        lightLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lightLabelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lightLabelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lightLabelMouseReleased(evt);
            }
        });
        add(lightLabel);
        lightLabel.setBounds(40, 300, 70, 10);

        headerLabel.setBackground(new java.awt.Color(0, 0, 255));
        headerLabel.setFont(headerLabel.getFont());
        headerLabel.setOpaque(true);
        headerLabel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerLabelMouseDragged(evt);
            }
        });
        headerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerLabelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                headerLabelMouseReleased(evt);
            }
        });
        add(headerLabel);
        headerLabel.setBounds(40, 10, 200, 10);

        textLabel01.setFont(textLabel01.getFont().deriveFont(textLabel01.getFont().getSize()-6f));
        textLabel01.setText(bundle.getString("CIUIFramePanel.textLabel01.text")); 
        textLabel01.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel01MouseDragged(evt);
            }
        });
        textLabel01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel01MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel01MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textLabel01MouseReleased(evt);
            }
        });
        add(textLabel01);
        textLabel01.setBounds(30, 120, 29, 8);

        comboBox01.setFont(comboBox01.getFont().deriveFont(comboBox01.getFont().getSize()-6f));
        comboBox01.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                comboBox01MouseDragged(evt);
            }
        });
        comboBox01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBox01MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboBox01MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboBox01MouseReleased(evt);
            }
        });
        add(comboBox01);
        comboBox01.setBounds(30, 140, 21, 14);

        textField01.setFont(textField01.getFont().deriveFont(textField01.getFont().getSize()-6f));
        textField01.setText("textfield01");
        textField01.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField01MouseDragged(evt);
            }
        });
        textField01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField01MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField01MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField01MouseReleased(evt);
            }
        });
        add(textField01);
        textField01.setBounds(30, 160, 34, 14);

        textField02.setFont(textField02.getFont().deriveFont(textField02.getFont().getSize()-6f));
        textField02.setText("textfield02");
        textField02.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField02MouseDragged(evt);
            }
        });
        textField02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField02MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField02MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField02MouseReleased(evt);
            }
        });
        add(textField02);
        textField02.setBounds(30, 160, 34, 14);

        textField03.setFont(textField03.getFont().deriveFont(textField03.getFont().getSize()-6f));
        textField03.setText("textfield03");
        textField03.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField03MouseDragged(evt);
            }
        });
        textField03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField03MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField03MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField03MouseReleased(evt);
            }
        });
        add(textField03);
        textField03.setBounds(30, 160, 34, 14);

        textField04.setFont(textField04.getFont().deriveFont(textField04.getFont().getSize()-6f));
        textField04.setText("textfield04");
        textField04.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField04MouseDragged(evt);
            }
        });
        textField04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField04MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField04MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField04MouseReleased(evt);
            }
        });
        add(textField04);
        textField04.setBounds(30, 160, 34, 14);

        textField05.setFont(textField05.getFont().deriveFont(textField05.getFont().getSize()-6f));
        textField05.setText("textfield05");
        textField05.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField05MouseDragged(evt);
            }
        });
        textField05.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField05MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField05MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField05MouseReleased(evt);
            }
        });
        add(textField05);
        textField05.setBounds(30, 160, 34, 14);

        textField06.setFont(textField06.getFont().deriveFont(textField06.getFont().getSize()-6f));
        textField06.setText("textfield06");
        textField06.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField06MouseDragged(evt);
            }
        });
        textField06.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField06MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField06MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField06MouseReleased(evt);
            }
        });
        add(textField06);
        textField06.setBounds(30, 160, 34, 14);

        textField07.setFont(textField07.getFont().deriveFont(textField07.getFont().getSize()-6f));
        textField07.setText("textfield07");
        textField07.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField07MouseDragged(evt);
            }
        });
        textField07.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField07MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField07MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField07MouseReleased(evt);
            }
        });
        add(textField07);
        textField07.setBounds(30, 160, 34, 14);

        textField08.setFont(textField08.getFont().deriveFont(textField08.getFont().getSize()-6f));
        textField08.setText("textfield08");
        textField08.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField08MouseDragged(evt);
            }
        });
        textField08.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField08MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField08MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField08MouseReleased(evt);
            }
        });
        add(textField08);
        textField08.setBounds(30, 160, 34, 14);

        textField09.setFont(textField09.getFont().deriveFont(textField09.getFont().getSize()-6f));
        textField09.setText("textfield09");
        textField09.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField09MouseDragged(evt);
            }
        });
        textField09.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField09MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField09MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField09MouseReleased(evt);
            }
        });
        add(textField09);
        textField09.setBounds(30, 160, 34, 14);

        textField10.setFont(textField10.getFont().deriveFont(textField10.getFont().getSize()-6f));
        textField10.setText("textfield10");
        textField10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField10MouseDragged(evt);
            }
        });
        textField10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField10MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField10MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField10MouseReleased(evt);
            }
        });
        add(textField10);
        textField10.setBounds(30, 160, 34, 14);

        textField11.setFont(textField11.getFont().deriveFont(textField11.getFont().getSize()-6f));
        textField11.setText("textfield11");
        textField11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField11MouseDragged(evt);
            }
        });
        textField11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField11MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField11MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField11MouseReleased(evt);
            }
        });
        add(textField11);
        textField11.setBounds(30, 160, 34, 14);

        textField12.setFont(textField12.getFont().deriveFont(textField12.getFont().getSize()-6f));
        textField12.setText("textfield12");
        textField12.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField12MouseDragged(evt);
            }
        });
        textField12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField12MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField12MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField12MouseReleased(evt);
            }
        });
        add(textField12);
        textField12.setBounds(30, 160, 34, 14);

        textField13.setFont(textField13.getFont().deriveFont(textField13.getFont().getSize()-6f));
        textField13.setText("textfield13");
        textField13.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField13MouseDragged(evt);
            }
        });
        textField13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField13MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField13MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField13MouseReleased(evt);
            }
        });
        add(textField13);
        textField13.setBounds(30, 160, 34, 14);

        textField14.setFont(textField14.getFont().deriveFont(textField14.getFont().getSize()-6f));
        textField14.setText("textfield14");
        textField14.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField14MouseDragged(evt);
            }
        });
        textField14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField14MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField14MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField14MouseReleased(evt);
            }
        });
        add(textField14);
        textField14.setBounds(30, 160, 34, 14);

        textField15.setFont(textField15.getFont().deriveFont(textField15.getFont().getSize()-6f));
        textField15.setText("textfield15");
        textField15.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField15MouseDragged(evt);
            }
        });
        textField15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField15MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField15MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField15MouseReleased(evt);
            }
        });
        add(textField15);
        textField15.setBounds(30, 160, 34, 14);

        textField16.setFont(textField16.getFont().deriveFont(textField16.getFont().getSize()-6f));
        textField16.setText("textfield16");
        textField16.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField16MouseDragged(evt);
            }
        });
        textField16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField16MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField16MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField16MouseReleased(evt);
            }
        });
        add(textField16);
        textField16.setBounds(30, 160, 34, 14);

        textField17.setFont(textField17.getFont().deriveFont(textField17.getFont().getSize()-6f));
        textField17.setText("textfield17");
        textField17.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField17MouseDragged(evt);
            }
        });
        textField17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField17MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField17MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField17MouseReleased(evt);
            }
        });
        add(textField17);
        textField17.setBounds(30, 160, 34, 14);

        textField18.setFont(textField18.getFont().deriveFont(textField18.getFont().getSize()-6f));
        textField18.setText("textfield18");
        textField18.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField18MouseDragged(evt);
            }
        });
        textField18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField18MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField18MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField18MouseReleased(evt);
            }
        });
        add(textField18);
        textField18.setBounds(30, 160, 34, 14);

        textField19.setFont(textField19.getFont().deriveFont(textField19.getFont().getSize()-6f));
        textField19.setText("textfield19");
        textField19.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField19MouseDragged(evt);
            }
        });
        textField19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField19MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField19MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField19MouseReleased(evt);
            }
        });
        add(textField19);
        textField19.setBounds(30, 160, 34, 14);

        textField20.setFont(textField20.getFont().deriveFont(textField20.getFont().getSize()-6f));
        textField20.setText("textfield20");
        textField20.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField20MouseDragged(evt);
            }
        });
        textField20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField20MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField20MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField20MouseReleased(evt);
            }
        });
        add(textField20);
        textField20.setBounds(30, 160, 34, 14);

        textField21.setFont(textField21.getFont().deriveFont(textField21.getFont().getSize()-6f));
        textField21.setText("textfield21");
        textField21.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField21MouseDragged(evt);
            }
        });
        textField21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField21MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField21MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField21MouseReleased(evt);
            }
        });
        add(textField21);
        textField21.setBounds(30, 160, 34, 14);

        textField22.setFont(textField22.getFont().deriveFont(textField22.getFont().getSize()-6f));
        textField22.setText("textfield22");
        textField22.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField22MouseDragged(evt);
            }
        });
        textField22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField22MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField22MouseReleased(evt);
            }
        });
        add(textField22);
        textField22.setBounds(30, 160, 34, 14);

        textField23.setFont(textField23.getFont().deriveFont(textField23.getFont().getSize()-6f));
        textField23.setText("textfield23");
        textField23.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField23MouseDragged(evt);
            }
        });
        textField23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField23MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField23MouseReleased(evt);
            }
        });
        add(textField23);
        textField23.setBounds(30, 160, 34, 14);

        textField24.setFont(textField24.getFont().deriveFont(textField24.getFont().getSize()-6f));
        textField24.setText("textfield24");
        textField24.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField24MouseDragged(evt);
            }
        });
        textField24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField24MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField24MouseReleased(evt);
            }
        });
        add(textField24);
        textField24.setBounds(30, 160, 34, 14);

        textField25.setFont(textField25.getFont().deriveFont(textField25.getFont().getSize()-6f));
        textField25.setText("textfield25");
        textField25.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField25MouseDragged(evt);
            }
        });
        textField25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField25MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField25MouseReleased(evt);
            }
        });
        add(textField25);
        textField25.setBounds(30, 160, 34, 14);

        textField26.setFont(textField26.getFont().deriveFont(textField26.getFont().getSize()-6f));
        textField26.setText("textfield26");
        textField26.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField26MouseDragged(evt);
            }
        });
        textField26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField26MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField26MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textField26MouseReleased(evt);
            }
        });
        add(textField26);
        textField26.setBounds(30, 160, 34, 14);

        textField27.setFont(textField27.getFont().deriveFont(textField27.getFont().getSize()-6f));
        textField27.setText("textfield27");
        textField27.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField27MouseDragged(evt);
            }
        });
        textField27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField27MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField27MousePressed(evt);
            }
        });
        add(textField27);
        textField27.setBounds(30, 160, 34, 14);

        textField28.setFont(textField28.getFont().deriveFont(textField28.getFont().getSize()-6f));
        textField28.setText("textfield28");
        textField28.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField28MouseDragged(evt);
            }
        });
        textField28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField28MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField28MousePressed(evt);
            }
        });
        add(textField28);
        textField28.setBounds(30, 160, 34, 14);

        textField29.setFont(textField29.getFont().deriveFont(textField29.getFont().getSize()-6f));
        textField29.setText("textfield29");
        textField29.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField29MouseDragged(evt);
            }
        });
        textField29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField29MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField29MousePressed(evt);
            }
        });
        add(textField29);
        textField29.setBounds(30, 160, 34, 14);

        textField30.setFont(textField30.getFont().deriveFont(textField30.getFont().getSize()-6f));
        textField30.setText("textfield30");
        textField30.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textField30MouseDragged(evt);
            }
        });
        textField30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField30MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textField30MousePressed(evt);
            }
        });
        add(textField30);
        textField30.setBounds(30, 160, 34, 14);

        textLabel02.setFont(textLabel02.getFont().deriveFont(textLabel02.getFont().getSize()-6f));
        textLabel02.setText("textlabel02");
        textLabel02.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel02MouseDragged(evt);
            }
        });
        textLabel02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel02MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel02MousePressed(evt);
            }
        });
        add(textLabel02);
        textLabel02.setBounds(30, 120, 29, 8);

        textLabel03.setFont(textLabel03.getFont().deriveFont(textLabel03.getFont().getSize()-6f));
        textLabel03.setText("textlabel03");
        textLabel03.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel03MouseDragged(evt);
            }
        });
        textLabel03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel03MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel03MousePressed(evt);
            }
        });
        add(textLabel03);
        textLabel03.setBounds(30, 120, 29, 8);

        textLabel04.setFont(textLabel04.getFont().deriveFont(textLabel04.getFont().getSize()-6f));
        textLabel04.setText("textlabel04");
        textLabel04.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel04MouseDragged(evt);
            }
        });
        textLabel04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel04MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel04MousePressed(evt);
            }
        });
        add(textLabel04);
        textLabel04.setBounds(30, 120, 29, 8);

        textLabel05.setFont(textLabel05.getFont().deriveFont(textLabel05.getFont().getSize()-6f));
        textLabel05.setText("textlabel05");
        textLabel05.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel05MouseDragged(evt);
            }
        });
        textLabel05.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel05MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel05MousePressed(evt);
            }
        });
        add(textLabel05);
        textLabel05.setBounds(30, 120, 29, 8);

        textLabel06.setFont(textLabel06.getFont().deriveFont(textLabel06.getFont().getSize()-6f));
        textLabel06.setText("textlabel06");
        textLabel06.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel06MouseDragged(evt);
            }
        });
        textLabel06.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel06MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel06MousePressed(evt);
            }
        });
        add(textLabel06);
        textLabel06.setBounds(30, 120, 29, 8);

        textLabel07.setFont(textLabel07.getFont().deriveFont(textLabel07.getFont().getSize()-6f));
        textLabel07.setText("textlabel07");
        textLabel07.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel07MouseDragged(evt);
            }
        });
        textLabel07.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel07MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel07MousePressed(evt);
            }
        });
        add(textLabel07);
        textLabel07.setBounds(30, 120, 29, 8);

        textLabel08.setFont(textLabel08.getFont().deriveFont(textLabel08.getFont().getSize()-6f));
        textLabel08.setText("textlabel08");
        textLabel08.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel08MouseDragged(evt);
            }
        });
        textLabel08.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel08MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel08MousePressed(evt);
            }
        });
        add(textLabel08);
        textLabel08.setBounds(30, 120, 29, 8);

        textLabel09.setFont(textLabel09.getFont().deriveFont(textLabel09.getFont().getSize()-6f));
        textLabel09.setText("textlabel09");
        textLabel09.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel09MouseDragged(evt);
            }
        });
        textLabel09.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel09MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel09MousePressed(evt);
            }
        });
        add(textLabel09);
        textLabel09.setBounds(30, 120, 29, 8);

        textLabel10.setFont(textLabel10.getFont().deriveFont(textLabel10.getFont().getSize()-6f));
        textLabel10.setText("textlabel10");
        textLabel10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel10MouseDragged(evt);
            }
        });
        textLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel10MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel10MousePressed(evt);
            }
        });
        add(textLabel10);
        textLabel10.setBounds(30, 120, 29, 8);

        textLabel11.setFont(textLabel11.getFont().deriveFont(textLabel11.getFont().getSize()-6f));
        textLabel11.setText("textlabel11");
        textLabel11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel11MouseDragged(evt);
            }
        });
        textLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel11MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel11MousePressed(evt);
            }
        });
        add(textLabel11);
        textLabel11.setBounds(30, 120, 29, 8);

        textLabel12.setFont(textLabel12.getFont().deriveFont(textLabel12.getFont().getSize()-6f));
        textLabel12.setText("textlabel12");
        textLabel12.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel12MouseDragged(evt);
            }
        });
        textLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel12MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel12MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textLabel12MouseReleased(evt);
            }
        });
        add(textLabel12);
        textLabel12.setBounds(30, 120, 29, 8);

        textLabel13.setFont(textLabel13.getFont().deriveFont(textLabel13.getFont().getSize()-6f));
        textLabel13.setText("textlabel13");
        textLabel13.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel13MouseDragged(evt);
            }
        });
        textLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel13MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel13MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textLabel13MouseReleased(evt);
            }
        });
        add(textLabel13);
        textLabel13.setBounds(30, 120, 29, 8);

        textLabel14.setFont(textLabel14.getFont().deriveFont(textLabel14.getFont().getSize()-6f));
        textLabel14.setText("textlabel14");
        textLabel14.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel14MouseDragged(evt);
            }
        });
        textLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel14MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel14MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textLabel14MouseReleased(evt);
            }
        });
        add(textLabel14);
        textLabel14.setBounds(30, 120, 29, 8);

        textLabel15.setFont(textLabel15.getFont().deriveFont(textLabel15.getFont().getSize()-6f));
        textLabel15.setText("textlabel15");
        textLabel15.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel15MouseDragged(evt);
            }
        });
        textLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel15MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel15MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textLabel15MouseReleased(evt);
            }
        });
        add(textLabel15);
        textLabel15.setBounds(30, 120, 29, 8);

        textLabel16.setFont(textLabel16.getFont().deriveFont(textLabel16.getFont().getSize()-6f));
        textLabel16.setText("textlabel16");
        textLabel16.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel16MouseDragged(evt);
            }
        });
        textLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel16MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel16MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textLabel16MouseReleased(evt);
            }
        });
        add(textLabel16);
        textLabel16.setBounds(30, 120, 29, 8);

        textLabel17.setFont(textLabel17.getFont().deriveFont(textLabel17.getFont().getSize()-6f));
        textLabel17.setText("textlabel17");
        textLabel17.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel17MouseDragged(evt);
            }
        });
        textLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel17MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel17MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textLabel17MouseReleased(evt);
            }
        });
        add(textLabel17);
        textLabel17.setBounds(30, 120, 29, 8);

        textLabel18.setFont(textLabel18.getFont().deriveFont(textLabel18.getFont().getSize()-6f));
        textLabel18.setText("textlabel18");
        textLabel18.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel18MouseDragged(evt);
            }
        });
        textLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel18MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel18MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textLabel18MouseReleased(evt);
            }
        });
        add(textLabel18);
        textLabel18.setBounds(30, 120, 29, 8);

        textLabel19.setFont(textLabel19.getFont().deriveFont(textLabel19.getFont().getSize()-6f));
        textLabel19.setText("textlabel19");
        textLabel19.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel19MouseDragged(evt);
            }
        });
        textLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel19MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel19MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textLabel19MouseReleased(evt);
            }
        });
        add(textLabel19);
        textLabel19.setBounds(30, 120, 29, 8);

        textLabel20.setFont(textLabel20.getFont().deriveFont(textLabel20.getFont().getSize()-6f));
        textLabel20.setText("textlabel20");
        textLabel20.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel20MouseDragged(evt);
            }
        });
        textLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel20MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel20MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textLabel20MouseReleased(evt);
            }
        });
        add(textLabel20);
        textLabel20.setBounds(30, 120, 29, 8);

        textLabel21.setFont(textLabel21.getFont().deriveFont(textLabel21.getFont().getSize()-6f));
        textLabel21.setText("textlabel21");
        textLabel21.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel21MouseDragged(evt);
            }
        });
        textLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel21MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel21MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textLabel21MouseReleased(evt);
            }
        });
        add(textLabel21);
        textLabel21.setBounds(30, 120, 29, 8);

        textLabel22.setFont(textLabel22.getFont().deriveFont(textLabel22.getFont().getSize()-6f));
        textLabel22.setText("textlabel22");
        textLabel22.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel22MouseDragged(evt);
            }
        });
        textLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel22MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel22MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textLabel22MouseReleased(evt);
            }
        });
        add(textLabel22);
        textLabel22.setBounds(30, 120, 29, 8);

        textLabel23.setFont(textLabel23.getFont().deriveFont(textLabel23.getFont().getSize()-6f));
        textLabel23.setText("textlabel23");
        textLabel23.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel23MouseDragged(evt);
            }
        });
        textLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel23MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel23MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textLabel23MouseReleased(evt);
            }
        });
        add(textLabel23);
        textLabel23.setBounds(30, 120, 29, 8);

        textLabel24.setFont(textLabel24.getFont().deriveFont(textLabel24.getFont().getSize()-6f));
        textLabel24.setText("textlabel24");
        textLabel24.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel24MouseDragged(evt);
            }
        });
        textLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel24MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel24MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textLabel24MouseReleased(evt);
            }
        });
        add(textLabel24);
        textLabel24.setBounds(30, 120, 29, 8);

        textLabel25.setFont(textLabel25.getFont().deriveFont(textLabel25.getFont().getSize()-6f));
        textLabel25.setText("textlabel25");
        textLabel25.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel25MouseDragged(evt);
            }
        });
        textLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel25MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel25MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textLabel25MouseReleased(evt);
            }
        });
        add(textLabel25);
        textLabel25.setBounds(30, 120, 29, 8);

        textLabel26.setFont(textLabel26.getFont().deriveFont(textLabel26.getFont().getSize()-6f));
        textLabel26.setText("textlabel26");
        textLabel26.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel26MouseDragged(evt);
            }
        });
        textLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel26MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel26MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textLabel26MouseReleased(evt);
            }
        });
        add(textLabel26);
        textLabel26.setBounds(30, 120, 29, 8);

        textLabel27.setFont(textLabel27.getFont().deriveFont(textLabel27.getFont().getSize()-6f));
        textLabel27.setText("textlabel27");
        textLabel27.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel27MouseDragged(evt);
            }
        });
        textLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel27MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel27MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textLabel27MouseReleased(evt);
            }
        });
        add(textLabel27);
        textLabel27.setBounds(30, 120, 29, 8);

        textLabel28.setFont(textLabel28.getFont().deriveFont(textLabel28.getFont().getSize()-6f));
        textLabel28.setText("textlabel28");
        textLabel28.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel28MouseDragged(evt);
            }
        });
        textLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel28MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel28MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textLabel28MouseReleased(evt);
            }
        });
        add(textLabel28);
        textLabel28.setBounds(30, 120, 29, 8);

        textLabel29.setFont(textLabel29.getFont().deriveFont(textLabel29.getFont().getSize()-6f));
        textLabel29.setText("textlabel29");
        textLabel29.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel29MouseDragged(evt);
            }
        });
        textLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel29MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel29MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textLabel29MouseReleased(evt);
            }
        });
        add(textLabel29);
        textLabel29.setBounds(30, 120, 29, 8);

        textLabel30.setFont(textLabel30.getFont().deriveFont(textLabel30.getFont().getSize()-6f));
        textLabel30.setText("textlabel30");
        textLabel30.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                textLabel30MouseDragged(evt);
            }
        });
        textLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textLabel30MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textLabel30MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textLabel30MouseReleased(evt);
            }
        });
        add(textLabel30);
        textLabel30.setBounds(30, 120, 29, 8);

        comboBox02.setFont(comboBox02.getFont().deriveFont(comboBox02.getFont().getSize()-6f));
        comboBox02.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox02.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                comboBox02MouseDragged(evt);
            }
        });
        comboBox02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBox02MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboBox02MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboBox02MouseReleased(evt);
            }
        });
        add(comboBox02);
        comboBox02.setBounds(30, 140, 35, 14);

        comboBox03.setFont(comboBox03.getFont().deriveFont(comboBox03.getFont().getSize()-6f));
        comboBox03.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox03.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                comboBox03MouseDragged(evt);
            }
        });
        comboBox03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBox03MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboBox03MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboBox03MouseReleased(evt);
            }
        });
        add(comboBox03);
        comboBox03.setBounds(30, 140, 35, 14);

        comboBox04.setFont(comboBox04.getFont().deriveFont(comboBox04.getFont().getSize()-6f));
        comboBox04.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox04.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                comboBox04MouseDragged(evt);
            }
        });
        comboBox04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBox04MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboBox04MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboBox04MouseReleased(evt);
            }
        });
        add(comboBox04);
        comboBox04.setBounds(30, 140, 35, 14);

        comboBox05.setFont(comboBox05.getFont().deriveFont(comboBox05.getFont().getSize()-6f));
        comboBox05.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox05.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                comboBox05MouseDragged(evt);
            }
        });
        comboBox05.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBox05MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboBox05MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboBox05MouseReleased(evt);
            }
        });
        add(comboBox05);
        comboBox05.setBounds(30, 140, 35, 14);

        comboBox06.setFont(comboBox06.getFont().deriveFont(comboBox06.getFont().getSize()-6f));
        comboBox06.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox06.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                comboBox06MouseDragged(evt);
            }
        });
        comboBox06.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBox06MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboBox06MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboBox06MouseReleased(evt);
            }
        });
        add(comboBox06);
        comboBox06.setBounds(30, 140, 35, 14);

        comboBox07.setFont(comboBox07.getFont().deriveFont(comboBox07.getFont().getSize()-6f));
        comboBox07.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox07.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                comboBox07MouseDragged(evt);
            }
        });
        comboBox07.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBox07MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboBox07MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboBox07MouseReleased(evt);
            }
        });
        add(comboBox07);
        comboBox07.setBounds(30, 140, 35, 14);

        comboBox08.setFont(comboBox08.getFont().deriveFont(comboBox08.getFont().getSize()-6f));
        comboBox08.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox08.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                comboBox08MouseDragged(evt);
            }
        });
        comboBox08.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBox08MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboBox08MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboBox08MouseReleased(evt);
            }
        });
        add(comboBox08);
        comboBox08.setBounds(30, 140, 35, 14);

        comboBox09.setFont(comboBox09.getFont().deriveFont(comboBox09.getFont().getSize()-6f));
        comboBox09.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox09.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                comboBox09MouseDragged(evt);
            }
        });
        comboBox09.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBox09MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboBox09MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboBox09MouseReleased(evt);
            }
        });
        add(comboBox09);
        comboBox09.setBounds(30, 140, 35, 14);

        comboBox10.setFont(comboBox10.getFont().deriveFont(comboBox10.getFont().getSize()-6f));
        comboBox10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                comboBox10MouseDragged(evt);
            }
        });
        comboBox10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBox10MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboBox10MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboBox10MouseReleased(evt);
            }
        });
        add(comboBox10);
        comboBox10.setBounds(30, 140, 35, 14);

        comboBox11.setFont(comboBox11.getFont().deriveFont(comboBox11.getFont().getSize()-6f));
        comboBox11.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                comboBox11MouseDragged(evt);
            }
        });
        comboBox11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBox11MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboBox11MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboBox11MouseReleased(evt);
            }
        });
        add(comboBox11);
        comboBox11.setBounds(30, 140, 35, 14);

        comboBox12.setFont(comboBox12.getFont().deriveFont(comboBox12.getFont().getSize()-6f));
        comboBox12.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox12.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                comboBox12MouseDragged(evt);
            }
        });
        comboBox12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBox12MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboBox12MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboBox12MouseReleased(evt);
            }
        });
        add(comboBox12);
        comboBox12.setBounds(30, 140, 35, 14);

        comboBox13.setFont(comboBox13.getFont().deriveFont(comboBox13.getFont().getSize()-6f));
        comboBox13.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox13.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                comboBox13MouseDragged(evt);
            }
        });
        comboBox13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBox13MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboBox13MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboBox13MouseReleased(evt);
            }
        });
        add(comboBox13);
        comboBox13.setBounds(30, 140, 35, 14);

        comboBox14.setFont(comboBox14.getFont().deriveFont(comboBox14.getFont().getSize()-6f));
        comboBox14.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox14.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                comboBox14MouseDragged(evt);
            }
        });
        comboBox14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBox14MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboBox14MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboBox14MouseReleased(evt);
            }
        });
        add(comboBox14);
        comboBox14.setBounds(30, 140, 35, 14);

        comboBox15.setFont(comboBox15.getFont().deriveFont(comboBox15.getFont().getSize()-6f));
        comboBox15.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox15.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                comboBox15MouseDragged(evt);
            }
        });
        comboBox15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBox15MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboBox15MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboBox15MouseReleased(evt);
            }
        });
        add(comboBox15);
        comboBox15.setBounds(30, 140, 35, 14);

        comboBox16.setFont(comboBox16.getFont().deriveFont(comboBox16.getFont().getSize()-6f));
        comboBox16.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox16.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                comboBox16MouseDragged(evt);
            }
        });
        comboBox16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBox16MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboBox16MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboBox16MouseReleased(evt);
            }
        });
        add(comboBox16);
        comboBox16.setBounds(30, 140, 35, 14);

        comboBox17.setFont(comboBox17.getFont().deriveFont(comboBox17.getFont().getSize()-6f));
        comboBox17.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox17.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                comboBox17MouseDragged(evt);
            }
        });
        comboBox17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBox17MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboBox17MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboBox17MouseReleased(evt);
            }
        });
        add(comboBox17);
        comboBox17.setBounds(30, 140, 35, 14);

        comboBox18.setFont(comboBox18.getFont().deriveFont(comboBox18.getFont().getSize()-6f));
        comboBox18.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox18.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                comboBox18MouseDragged(evt);
            }
        });
        comboBox18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBox18MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboBox18MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboBox18MouseReleased(evt);
            }
        });
        add(comboBox18);
        comboBox18.setBounds(30, 140, 35, 14);

        comboBox19.setFont(comboBox19.getFont().deriveFont(comboBox19.getFont().getSize()-6f));
        comboBox19.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox19.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                comboBox19MouseDragged(evt);
            }
        });
        comboBox19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBox19MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboBox19MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboBox19MouseReleased(evt);
            }
        });
        add(comboBox19);
        comboBox19.setBounds(30, 140, 35, 14);

        comboBox20.setFont(comboBox20.getFont().deriveFont(comboBox20.getFont().getSize()-6f));
        comboBox20.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox20.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                comboBox20MouseDragged(evt);
            }
        });
        comboBox20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBox20MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboBox20MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboBox20MouseReleased(evt);
            }
        });
        add(comboBox20);
        comboBox20.setBounds(30, 140, 35, 14);

        button25.setFont(button25.getFont().deriveFont(button25.getFont().getSize()-6f));
        button25.setText(bundle.getString("CIUIFramePanel.button25.text")); 
        button25.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button25MouseDragged(evt);
            }
        });
        button25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button25MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button25MouseReleased(evt);
            }
        });
        button25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button25ActionPerformed(evt);
            }
        });
        add(button25);
        button25.setBounds(30, 180, 57, 17);

        button26.setFont(button26.getFont().deriveFont(button26.getFont().getSize()-6f));
        button26.setText(bundle.getString("CIUIFramePanel.button26.text")); 
        button26.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button26MouseDragged(evt);
            }
        });
        button26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button26MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button26MouseReleased(evt);
            }
        });
        button26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button26ActionPerformed(evt);
            }
        });
        add(button26);
        button26.setBounds(30, 180, 57, 17);

        button27.setFont(button27.getFont().deriveFont(button27.getFont().getSize()-6f));
        button27.setText(bundle.getString("CIUIFramePanel.button27.text")); 
        button27.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button27MouseDragged(evt);
            }
        });
        button27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button27MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button27MouseReleased(evt);
            }
        });
        button27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button27ActionPerformed(evt);
            }
        });
        add(button27);
        button27.setBounds(30, 180, 57, 17);

        button28.setFont(button28.getFont().deriveFont(button28.getFont().getSize()-6f));
        button28.setText(bundle.getString("CIUIFramePanel.button28.text")); 
        button28.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button28MouseDragged(evt);
            }
        });
        button28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button28MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button28MouseReleased(evt);
            }
        });
        button28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button28ActionPerformed(evt);
            }
        });
        add(button28);
        button28.setBounds(30, 180, 57, 17);

        button29.setFont(button29.getFont().deriveFont(button29.getFont().getSize()-6f));
        button29.setText("Button 29");
        button29.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button29MouseDragged(evt);
            }
        });
        button29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button29MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button29MouseReleased(evt);
            }
        });
        button29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button29ActionPerformed(evt);
            }
        });
        add(button29);
        button29.setBounds(30, 180, 57, 17);

        button30.setFont(button30.getFont().deriveFont(button30.getFont().getSize()-6f));
        button30.setText("Button 30");
        button30.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button30MouseDragged(evt);
            }
        });
        button30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button30MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button30MouseReleased(evt);
            }
        });
        button30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button30ActionPerformed(evt);
            }
        });
        add(button30);
        button30.setBounds(30, 180, 57, 17);

        button11.setFont(button11.getFont().deriveFont(button11.getFont().getSize()-6f));
        button11.setText("Button 11");
        button11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button11MouseDragged(evt);
            }
        });
        button11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button11MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button11MouseReleased(evt);
            }
        });
        button11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11ActionPerformed(evt);
            }
        });
        add(button11);
        button11.setBounds(30, 180, 57, 17);

        button12.setFont(button12.getFont().deriveFont(button12.getFont().getSize()-6f));
        button12.setText("Button 12");
        button12.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button12MouseDragged(evt);
            }
        });
        button12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button12MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button12MouseReleased(evt);
            }
        });
        button12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button12ActionPerformed(evt);
            }
        });
        add(button12);
        button12.setBounds(30, 180, 57, 17);

        button13.setFont(button13.getFont().deriveFont(button13.getFont().getSize()-6f));
        button13.setText("Button 13");
        button13.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button13MouseDragged(evt);
            }
        });
        button13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button13MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button13MouseReleased(evt);
            }
        });
        button13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button13ActionPerformed(evt);
            }
        });
        add(button13);
        button13.setBounds(30, 180, 57, 17);

        button14.setFont(button14.getFont().deriveFont(button14.getFont().getSize()-6f));
        button14.setText("Button 14");
        button14.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button14MouseDragged(evt);
            }
        });
        button14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button14MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button14MouseReleased(evt);
            }
        });
        button14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button14ActionPerformed(evt);
            }
        });
        add(button14);
        button14.setBounds(30, 180, 57, 17);

        button15.setFont(button15.getFont().deriveFont(button15.getFont().getSize()-6f));
        button15.setText("Button 15");
        button15.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button15MouseDragged(evt);
            }
        });
        button15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button15MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button15MouseReleased(evt);
            }
        });
        button15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button15ActionPerformed(evt);
            }
        });
        add(button15);
        button15.setBounds(30, 180, 57, 17);

        button16.setFont(button16.getFont().deriveFont(button16.getFont().getSize()-6f));
        button16.setText("Button 16");
        button16.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button16MouseDragged(evt);
            }
        });
        button16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button16MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button16MouseReleased(evt);
            }
        });
        button16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button16ActionPerformed(evt);
            }
        });
        add(button16);
        button16.setBounds(30, 180, 57, 17);

        button17.setFont(button17.getFont().deriveFont(button17.getFont().getSize()-6f));
        button17.setText("Button 17");
        button17.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button17MouseDragged(evt);
            }
        });
        button17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button17MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button17MouseReleased(evt);
            }
        });
        button17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button17ActionPerformed(evt);
            }
        });
        add(button17);
        button17.setBounds(30, 180, 57, 17);

        button18.setFont(button18.getFont().deriveFont(button18.getFont().getSize()-6f));
        button18.setText("Button 18");
        button18.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button18MouseDragged(evt);
            }
        });
        button18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button18MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button18MouseReleased(evt);
            }
        });
        button18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button18ActionPerformed(evt);
            }
        });
        add(button18);
        button18.setBounds(30, 180, 57, 17);

        button19.setFont(button19.getFont().deriveFont(button19.getFont().getSize()-6f));
        button19.setText("Button 19");
        button19.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button19MouseDragged(evt);
            }
        });
        button19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button19MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button19MouseReleased(evt);
            }
        });
        button19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button19ActionPerformed(evt);
            }
        });
        add(button19);
        button19.setBounds(30, 180, 57, 17);

        button20.setFont(button20.getFont().deriveFont(button20.getFont().getSize()-6f));
        button20.setText("Button 20");
        button20.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button20MouseDragged(evt);
            }
        });
        button20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button20MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button20MouseReleased(evt);
            }
        });
        button20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button20ActionPerformed(evt);
            }
        });
        add(button20);
        button20.setBounds(30, 180, 57, 17);

        button21.setFont(button21.getFont().deriveFont(button21.getFont().getSize()-6f));
        button21.setText("Button 21");
        button21.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button21MouseDragged(evt);
            }
        });
        button21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button21MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button21MouseReleased(evt);
            }
        });
        button21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button21ActionPerformed(evt);
            }
        });
        add(button21);
        button21.setBounds(30, 180, 57, 17);

        button22.setFont(button22.getFont().deriveFont(button22.getFont().getSize()-6f));
        button22.setText("Button 22");
        button22.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button22MouseDragged(evt);
            }
        });
        button22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button22MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button22MouseReleased(evt);
            }
        });
        button22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button22ActionPerformed(evt);
            }
        });
        add(button22);
        button22.setBounds(30, 180, 57, 17);

        button23.setFont(button23.getFont().deriveFont(button23.getFont().getSize()-6f));
        button23.setText("Button 23");
        button23.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button23MouseDragged(evt);
            }
        });
        button23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button23MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button23MouseReleased(evt);
            }
        });
        button23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button23ActionPerformed(evt);
            }
        });
        add(button23);
        button23.setBounds(30, 180, 57, 17);

        button24.setFont(button24.getFont().deriveFont(button24.getFont().getSize()-6f));
        button24.setText("Button 24");
        button24.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                button24MouseDragged(evt);
            }
        });
        button24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button24MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button24MouseReleased(evt);
            }
        });
        button24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button24ActionPerformed(evt);
            }
        });
        add(button24);
        button24.setBounds(30, 180, 57, 17);

        checkBox01.setFont(checkBox01.getFont().deriveFont(checkBox01.getFont().getSize()-6f));
        checkBox01.setText(bundle.getString("CIUIFramePanel.checkBox01.text")); 
        checkBox01.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                checkBox01MouseDragged(evt);
            }
        });
        checkBox01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBox01MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                checkBox01MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                checkBox01MouseReleased(evt);
            }
        });
        add(checkBox01);
        checkBox01.setBounds(170, 180, 60, 10);

        radioButton01.setFont(radioButton01.getFont().deriveFont(radioButton01.getFont().getSize()-6f));
        radioButton01.setText(bundle.getString("CIUIFramePanel.radioButton01.text")); 
        radioButton01.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                radioButton01MouseDragged(evt);
            }
        });
        radioButton01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButton01MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioButton01MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                radioButton01MouseReleased(evt);
            }
        });
        add(radioButton01);
        radioButton01.setBounds(250, 183, 63, 10);

        checkBox02.setFont(checkBox02.getFont().deriveFont(checkBox02.getFont().getSize()-6f));
        checkBox02.setText("checkbox 02");
        checkBox02.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                checkBox02MouseDragged(evt);
            }
        });
        checkBox02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBox02MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                checkBox02MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                checkBox02MouseReleased(evt);
            }
        });
        add(checkBox02);
        checkBox02.setBounds(170, 180, 60, 10);

        checkBox03.setFont(checkBox03.getFont().deriveFont(checkBox03.getFont().getSize()-6f));
        checkBox03.setText("checkbox 03");
        checkBox03.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                checkBox03MouseDragged(evt);
            }
        });
        checkBox03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBox03MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                checkBox03MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                checkBox03MouseReleased(evt);
            }
        });
        add(checkBox03);
        checkBox03.setBounds(170, 180, 60, 10);

        checkBox04.setFont(checkBox04.getFont().deriveFont(checkBox04.getFont().getSize()-6f));
        checkBox04.setText("checkbox 04");
        checkBox04.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                checkBox04MouseDragged(evt);
            }
        });
        checkBox04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBox04MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                checkBox04MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                checkBox04MouseReleased(evt);
            }
        });
        add(checkBox04);
        checkBox04.setBounds(170, 180, 60, 10);

        checkBox05.setFont(checkBox05.getFont().deriveFont(checkBox05.getFont().getSize()-6f));
        checkBox05.setText("checkbox 05");
        checkBox05.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                checkBox05MouseDragged(evt);
            }
        });
        checkBox05.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBox05MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                checkBox05MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                checkBox05MouseReleased(evt);
            }
        });
        add(checkBox05);
        checkBox05.setBounds(170, 180, 60, 10);

        checkBox06.setFont(checkBox06.getFont().deriveFont(checkBox06.getFont().getSize()-6f));
        checkBox06.setText("checkbox 06");
        checkBox06.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                checkBox06MouseDragged(evt);
            }
        });
        checkBox06.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBox06MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                checkBox06MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                checkBox06MouseReleased(evt);
            }
        });
        add(checkBox06);
        checkBox06.setBounds(170, 180, 60, 10);

        checkBox07.setFont(checkBox07.getFont().deriveFont(checkBox07.getFont().getSize()-6f));
        checkBox07.setText("checkbox 07");
        checkBox07.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                checkBox07MouseDragged(evt);
            }
        });
        checkBox07.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBox07MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                checkBox07MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                checkBox07MouseReleased(evt);
            }
        });
        add(checkBox07);
        checkBox07.setBounds(170, 180, 60, 10);

        checkBox08.setFont(checkBox08.getFont().deriveFont(checkBox08.getFont().getSize()-6f));
        checkBox08.setText("checkbox 08");
        checkBox08.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                checkBox08MouseDragged(evt);
            }
        });
        checkBox08.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBox08MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                checkBox08MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                checkBox08MouseReleased(evt);
            }
        });
        add(checkBox08);
        checkBox08.setBounds(170, 180, 60, 10);

        checkBox09.setFont(checkBox09.getFont().deriveFont(checkBox09.getFont().getSize()-6f));
        checkBox09.setText("checkbox 09");
        checkBox09.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                checkBox09MouseDragged(evt);
            }
        });
        checkBox09.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBox09MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                checkBox09MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                checkBox09MouseReleased(evt);
            }
        });
        add(checkBox09);
        checkBox09.setBounds(170, 180, 60, 10);

        checkBox10.setFont(checkBox10.getFont().deriveFont(checkBox10.getFont().getSize()-6f));
        checkBox10.setText("checkbox 10");
        checkBox10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                checkBox10MouseDragged(evt);
            }
        });
        checkBox10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBox10MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                checkBox10MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                checkBox10MouseReleased(evt);
            }
        });
        add(checkBox10);
        checkBox10.setBounds(170, 180, 60, 10);

        checkBox11.setFont(checkBox11.getFont().deriveFont(checkBox11.getFont().getSize()-6f));
        checkBox11.setText("checkbox 11");
        checkBox11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                checkBox11MouseDragged(evt);
            }
        });
        checkBox11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBox11MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                checkBox11MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                checkBox11MouseReleased(evt);
            }
        });
        add(checkBox11);
        checkBox11.setBounds(170, 180, 60, 10);

        checkBox12.setFont(checkBox12.getFont().deriveFont(checkBox12.getFont().getSize()-6f));
        checkBox12.setText("checkbox 12");
        checkBox12.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                checkBox12MouseDragged(evt);
            }
        });
        checkBox12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBox12MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                checkBox12MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                checkBox12MouseReleased(evt);
            }
        });
        add(checkBox12);
        checkBox12.setBounds(170, 180, 60, 10);

        checkBox13.setFont(checkBox13.getFont().deriveFont(checkBox13.getFont().getSize()-6f));
        checkBox13.setText("checkbox 13");
        checkBox13.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                checkBox13MouseDragged(evt);
            }
        });
        checkBox13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBox13MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                checkBox13MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                checkBox13MouseReleased(evt);
            }
        });
        add(checkBox13);
        checkBox13.setBounds(170, 180, 60, 10);

        checkBox14.setFont(checkBox14.getFont().deriveFont(checkBox14.getFont().getSize()-6f));
        checkBox14.setText("checkbox 14");
        checkBox14.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                checkBox14MouseDragged(evt);
            }
        });
        checkBox14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBox14MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                checkBox14MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                checkBox14MouseReleased(evt);
            }
        });
        add(checkBox14);
        checkBox14.setBounds(170, 180, 60, 10);

        checkBox15.setFont(checkBox15.getFont().deriveFont(checkBox15.getFont().getSize()-6f));
        checkBox15.setText("checkbox 15");
        checkBox15.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                checkBox15MouseDragged(evt);
            }
        });
        checkBox15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBox15MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                checkBox15MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                checkBox15MouseReleased(evt);
            }
        });
        add(checkBox15);
        checkBox15.setBounds(170, 180, 60, 10);

        checkBox16.setFont(checkBox16.getFont().deriveFont(checkBox16.getFont().getSize()-6f));
        checkBox16.setText("checkbox 16");
        checkBox16.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                checkBox16MouseDragged(evt);
            }
        });
        checkBox16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBox16MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                checkBox16MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                checkBox16MouseReleased(evt);
            }
        });
        add(checkBox16);
        checkBox16.setBounds(170, 180, 60, 10);

        checkBox17.setFont(checkBox17.getFont().deriveFont(checkBox17.getFont().getSize()-6f));
        checkBox17.setText("checkbox 17");
        checkBox17.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                checkBox17MouseDragged(evt);
            }
        });
        checkBox17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBox17MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                checkBox17MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                checkBox17MouseReleased(evt);
            }
        });
        add(checkBox17);
        checkBox17.setBounds(170, 180, 60, 10);

        checkBox18.setFont(checkBox18.getFont().deriveFont(checkBox18.getFont().getSize()-6f));
        checkBox18.setText("checkbox 18");
        checkBox18.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                checkBox18MouseDragged(evt);
            }
        });
        checkBox18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBox18MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                checkBox18MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                checkBox18MouseReleased(evt);
            }
        });
        add(checkBox18);
        checkBox18.setBounds(170, 180, 60, 10);

        checkBox19.setFont(checkBox19.getFont().deriveFont(checkBox19.getFont().getSize()-6f));
        checkBox19.setText("checkbox 19");
        checkBox19.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                checkBox19MouseDragged(evt);
            }
        });
        checkBox19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBox19MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                checkBox19MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                checkBox19MouseReleased(evt);
            }
        });
        add(checkBox19);
        checkBox19.setBounds(170, 180, 60, 10);

        checkBox20.setFont(checkBox20.getFont().deriveFont(checkBox20.getFont().getSize()-6f));
        checkBox20.setText("checkbox 20");
        checkBox20.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                checkBox20MouseDragged(evt);
            }
        });
        checkBox20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBox20MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                checkBox20MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                checkBox20MouseReleased(evt);
            }
        });
        add(checkBox20);
        checkBox20.setBounds(170, 180, 60, 10);

        radioButton02.setFont(radioButton02.getFont().deriveFont(radioButton02.getFont().getSize()-6f));
        radioButton02.setText("radioButton 02");
        radioButton02.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                radioButton02MouseDragged(evt);
            }
        });
        radioButton02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButton02MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioButton02MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                radioButton02MouseReleased(evt);
            }
        });
        add(radioButton02);
        radioButton02.setBounds(250, 183, 63, 10);

        radioButton03.setFont(radioButton03.getFont().deriveFont(radioButton03.getFont().getSize()-6f));
        radioButton03.setText("radioButton 03");
        radioButton03.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                radioButton03MouseDragged(evt);
            }
        });
        radioButton03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButton03MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioButton03MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                radioButton03MouseReleased(evt);
            }
        });
        add(radioButton03);
        radioButton03.setBounds(250, 183, 63, 10);

        radioButton04.setFont(radioButton04.getFont().deriveFont(radioButton04.getFont().getSize()-6f));
        radioButton04.setText("radioButton 04");
        radioButton04.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                radioButton04MouseDragged(evt);
            }
        });
        radioButton04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButton04MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioButton04MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                radioButton04MouseReleased(evt);
            }
        });
        add(radioButton04);
        radioButton04.setBounds(250, 183, 63, 10);

        radioButton05.setFont(radioButton05.getFont().deriveFont(radioButton05.getFont().getSize()-6f));
        radioButton05.setText("radioButton 05");
        radioButton05.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                radioButton05MouseDragged(evt);
            }
        });
        radioButton05.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButton05MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioButton05MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                radioButton05MouseReleased(evt);
            }
        });
        add(radioButton05);
        radioButton05.setBounds(250, 183, 63, 10);

        radioButton06.setFont(radioButton06.getFont().deriveFont(radioButton06.getFont().getSize()-6f));
        radioButton06.setText("radioButton 06");
        radioButton06.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                radioButton06MouseDragged(evt);
            }
        });
        radioButton06.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButton06MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioButton06MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                radioButton06MouseReleased(evt);
            }
        });
        add(radioButton06);
        radioButton06.setBounds(250, 183, 63, 10);

        radioButton07.setFont(radioButton07.getFont().deriveFont(radioButton07.getFont().getSize()-6f));
        radioButton07.setText("radioButton 07");
        radioButton07.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                radioButton07MouseDragged(evt);
            }
        });
        radioButton07.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButton07MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioButton07MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                radioButton07MouseReleased(evt);
            }
        });
        add(radioButton07);
        radioButton07.setBounds(250, 183, 63, 10);

        radioButton08.setFont(radioButton08.getFont().deriveFont(radioButton08.getFont().getSize()-6f));
        radioButton08.setText("radioButton 08");
        radioButton08.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                radioButton08MouseDragged(evt);
            }
        });
        radioButton08.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButton08MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioButton08MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                radioButton08MouseReleased(evt);
            }
        });
        add(radioButton08);
        radioButton08.setBounds(250, 183, 63, 10);

        radioButton09.setFont(radioButton09.getFont().deriveFont(radioButton09.getFont().getSize()-6f));
        radioButton09.setText("radioButton 09");
        radioButton09.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                radioButton09MouseDragged(evt);
            }
        });
        radioButton09.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButton09MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioButton09MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                radioButton09MouseReleased(evt);
            }
        });
        add(radioButton09);
        radioButton09.setBounds(250, 183, 63, 10);

        radioButton10.setFont(radioButton10.getFont().deriveFont(radioButton10.getFont().getSize()-6f));
        radioButton10.setText("radioButton 10");
        radioButton10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                radioButton10MouseDragged(evt);
            }
        });
        radioButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButton10MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioButton10MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                radioButton10MouseReleased(evt);
            }
        });
        add(radioButton10);
        radioButton10.setBounds(250, 183, 63, 10);

        radioButton11.setFont(radioButton11.getFont().deriveFont(radioButton11.getFont().getSize()-6f));
        radioButton11.setText("radioButton 11");
        radioButton11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                radioButton11MouseDragged(evt);
            }
        });
        radioButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButton11MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioButton11MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                radioButton11MouseReleased(evt);
            }
        });
        add(radioButton11);
        radioButton11.setBounds(250, 183, 63, 10);

        radioButton12.setFont(radioButton12.getFont().deriveFont(radioButton12.getFont().getSize()-6f));
        radioButton12.setText("radioButton 12");
        radioButton12.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                radioButton12MouseDragged(evt);
            }
        });
        radioButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButton12MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioButton12MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                radioButton12MouseReleased(evt);
            }
        });
        add(radioButton12);
        radioButton12.setBounds(250, 183, 63, 10);

        radioButton13.setFont(radioButton13.getFont().deriveFont(radioButton13.getFont().getSize()-6f));
        radioButton13.setText("radioButton 13");
        radioButton13.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                radioButton13MouseDragged(evt);
            }
        });
        radioButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButton13MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioButton13MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                radioButton13MouseReleased(evt);
            }
        });
        add(radioButton13);
        radioButton13.setBounds(250, 183, 63, 10);

        radioButton14.setFont(radioButton14.getFont().deriveFont(radioButton14.getFont().getSize()-6f));
        radioButton14.setText("radioButton 14");
        radioButton14.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                radioButton14MouseDragged(evt);
            }
        });
        radioButton14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButton14MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioButton14MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                radioButton14MouseReleased(evt);
            }
        });
        add(radioButton14);
        radioButton14.setBounds(250, 183, 63, 10);

        radioButton15.setFont(radioButton15.getFont().deriveFont(radioButton15.getFont().getSize()-6f));
        radioButton15.setText("radioButton 15");
        radioButton15.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                radioButton15MouseDragged(evt);
            }
        });
        radioButton15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButton15MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioButton15MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                radioButton15MouseReleased(evt);
            }
        });
        add(radioButton15);
        radioButton15.setBounds(250, 183, 63, 10);

        radioButton16.setFont(radioButton16.getFont().deriveFont(radioButton16.getFont().getSize()-6f));
        radioButton16.setText("radioButton 16");
        radioButton16.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                radioButton16MouseDragged(evt);
            }
        });
        radioButton16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButton16MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioButton16MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                radioButton16MouseReleased(evt);
            }
        });
        add(radioButton16);
        radioButton16.setBounds(250, 183, 63, 10);

        radioButton17.setFont(radioButton17.getFont().deriveFont(radioButton17.getFont().getSize()-6f));
        radioButton17.setText("radioButton 17");
        radioButton17.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                radioButton17MouseDragged(evt);
            }
        });
        radioButton17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButton17MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioButton17MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                radioButton17MouseReleased(evt);
            }
        });
        add(radioButton17);
        radioButton17.setBounds(250, 183, 63, 10);

        radioButton18.setFont(radioButton18.getFont().deriveFont(radioButton18.getFont().getSize()-6f));
        radioButton18.setText("radioButton 18");
        radioButton18.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                radioButton18MouseDragged(evt);
            }
        });
        radioButton18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButton18MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioButton18MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                radioButton18MouseReleased(evt);
            }
        });
        add(radioButton18);
        radioButton18.setBounds(250, 183, 63, 10);

        radioButton19.setFont(radioButton19.getFont().deriveFont(radioButton19.getFont().getSize()-6f));
        radioButton19.setText("radioButton 19");
        radioButton19.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                radioButton19MouseDragged(evt);
            }
        });
        radioButton19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButton19MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioButton19MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                radioButton19MouseReleased(evt);
            }
        });
        add(radioButton19);
        radioButton19.setBounds(250, 183, 63, 10);

        radioButton20.setFont(radioButton20.getFont().deriveFont(radioButton20.getFont().getSize()-6f));
        radioButton20.setText("radioButton 20");
        radioButton20.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                radioButton20MouseDragged(evt);
            }
        });
        radioButton20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButton20MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioButton20MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                radioButton20MouseReleased(evt);
            }
        });
        add(radioButton20);
        radioButton20.setBounds(250, 183, 63, 10);

        chartOptionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), bundle.getString("CIUIFramePanel.chartOptionPanel.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("新細明體", 0, 6))); 
        chartOptionPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                chartOptionPanelMouseDragged(evt);
            }
        });
        chartOptionPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chartOptionPanelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                chartOptionPanelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                chartOptionPanelMouseReleased(evt);
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

        dataPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), bundle.getString("CIUIFramePanel.dataPanel.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("新細明體", 0, 6))); 
        dataPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                dataPanelMouseDragged(evt);
            }
        });
        dataPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataPanelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dataPanelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dataPanelMouseReleased(evt);
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

        chartPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), bundle.getString("CIUIFramePanel.chartPanel.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("新細明體", 0, 6))); 
        chartPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                chartPanelMouseDragged(evt);
            }
        });
        chartPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chartPanelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                chartPanelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                chartPanelMouseReleased(evt);
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

        deviceTable.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), bundle.getString("CIUIFramePanel.deviceTable.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("新細明體", 0, 6))); 
        deviceTable.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                deviceTableMouseDragged(evt);
            }
        });
        deviceTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deviceTableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                deviceTableMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                deviceTableMouseReleased(evt);
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

        stationList.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), bundle.getString("CIUIFramePanel.stationList.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("新細明體", 0, 6))); 
        stationList.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                stationListMouseDragged(evt);
            }
        });
        stationList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stationListMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                stationListMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                stationListMouseReleased(evt);
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
            .addGap(0, 65, Short.MAX_VALUE)
        );

        add(stationList);
        stationList.setBounds(20, 40, 100, 80);

        panel01.setBackground(new java.awt.Color(204, 255, 255));
        panel01.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panel01MouseDragged(evt);
            }
        });
        panel01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel01MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel01MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panel01MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout panel01Layout = new javax.swing.GroupLayout(panel01);
        panel01.setLayout(panel01Layout);
        panel01Layout.setHorizontalGroup(
            panel01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        panel01Layout.setVerticalGroup(
            panel01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        add(panel01);
        panel01.setBounds(140, 220, 40, 40);

        panel02.setBackground(new java.awt.Color(204, 255, 255));
        panel02.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panel02MouseDragged(evt);
            }
        });
        panel02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel02MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel02MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panel02MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout panel02Layout = new javax.swing.GroupLayout(panel02);
        panel02.setLayout(panel02Layout);
        panel02Layout.setHorizontalGroup(
            panel02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        panel02Layout.setVerticalGroup(
            panel02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        add(panel02);
        panel02.setBounds(140, 220, 40, 40);

        panel03.setBackground(new java.awt.Color(204, 255, 255));
        panel03.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panel03MouseDragged(evt);
            }
        });
        panel03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel03MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel03MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panel03MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout panel03Layout = new javax.swing.GroupLayout(panel03);
        panel03.setLayout(panel03Layout);
        panel03Layout.setHorizontalGroup(
            panel03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        panel03Layout.setVerticalGroup(
            panel03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        add(panel03);
        panel03.setBounds(140, 220, 40, 40);

        panel04.setBackground(new java.awt.Color(204, 255, 255));
        panel04.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panel04MouseDragged(evt);
            }
        });
        panel04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel04MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel04MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panel04MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout panel04Layout = new javax.swing.GroupLayout(panel04);
        panel04.setLayout(panel04Layout);
        panel04Layout.setHorizontalGroup(
            panel04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        panel04Layout.setVerticalGroup(
            panel04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        add(panel04);
        panel04.setBounds(140, 220, 40, 40);

        panel05.setBackground(new java.awt.Color(204, 255, 255));
        panel05.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panel05MouseDragged(evt);
            }
        });
        panel05.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel05MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel05MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panel05MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout panel05Layout = new javax.swing.GroupLayout(panel05);
        panel05.setLayout(panel05Layout);
        panel05Layout.setHorizontalGroup(
            panel05Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        panel05Layout.setVerticalGroup(
            panel05Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        add(panel05);
        panel05.setBounds(140, 220, 40, 40);

        panel06.setBackground(new java.awt.Color(204, 255, 255));
        panel06.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panel06MouseDragged(evt);
            }
        });
        panel06.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel06MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel06MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panel06MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout panel06Layout = new javax.swing.GroupLayout(panel06);
        panel06.setLayout(panel06Layout);
        panel06Layout.setHorizontalGroup(
            panel06Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        panel06Layout.setVerticalGroup(
            panel06Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        add(panel06);
        panel06.setBounds(140, 220, 40, 40);

        panel07.setBackground(new java.awt.Color(204, 255, 255));
        panel07.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panel07MouseDragged(evt);
            }
        });
        panel07.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel07MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel07MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panel07MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout panel07Layout = new javax.swing.GroupLayout(panel07);
        panel07.setLayout(panel07Layout);
        panel07Layout.setHorizontalGroup(
            panel07Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        panel07Layout.setVerticalGroup(
            panel07Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        add(panel07);
        panel07.setBounds(140, 220, 40, 40);

        panel08.setBackground(new java.awt.Color(204, 255, 255));
        panel08.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panel08MouseDragged(evt);
            }
        });
        panel08.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel08MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel08MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panel08MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout panel08Layout = new javax.swing.GroupLayout(panel08);
        panel08.setLayout(panel08Layout);
        panel08Layout.setHorizontalGroup(
            panel08Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        panel08Layout.setVerticalGroup(
            panel08Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        add(panel08);
        panel08.setBounds(140, 220, 40, 40);

        panel09.setBackground(new java.awt.Color(204, 255, 255));
        panel09.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panel09MouseDragged(evt);
            }
        });
        panel09.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel09MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel09MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panel09MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout panel09Layout = new javax.swing.GroupLayout(panel09);
        panel09.setLayout(panel09Layout);
        panel09Layout.setHorizontalGroup(
            panel09Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        panel09Layout.setVerticalGroup(
            panel09Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        add(panel09);
        panel09.setBounds(140, 220, 40, 40);

        panel10.setBackground(new java.awt.Color(204, 255, 255));
        panel10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panel10MouseDragged(evt);
            }
        });
        panel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel10MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel10MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panel10MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout panel10Layout = new javax.swing.GroupLayout(panel10);
        panel10.setLayout(panel10Layout);
        panel10Layout.setHorizontalGroup(
            panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        panel10Layout.setVerticalGroup(
            panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        add(panel10);
        panel10.setBounds(140, 220, 40, 40);
    }

  private void formKeyPressed(java.awt.event.KeyEvent evt) {
    System.out.println("key pressed in framepanel2, keycode="+evt.getKeyCode()+",char="+evt.getKeyChar()+",id="+evt.getID()+",modifiers="+evt.getModifiers());
  }

  private void formMouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jTabbedPane1.setSelectedComponent(instrument.uiPanel2.jPanel145);
   instrument.uiPanel2.jComboBox36.setSelectedItem("");
       instrument.selectedUIAreaItem="";
  }

  private void formMouseDragged(java.awt.event.MouseEvent evt) {
         int draggedX=evt.getX(),draggedY=evt.getY();

  }

    private void stationListMouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void deviceTableMouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void chartPanelMouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void dataPanelMouseClicked(java.awt.event.MouseEvent evt) {
       if(evt.getClickCount()==2){
           instrument.jTabbedPane1.setSelectedComponent(instrument.jPanel3);
           instrument.jTabbedPane3.setSelectedComponent(instrument.uiPanel);
           instrument.uiPanel2.jTabbedPane4.setSelectedComponent(instrument.uiPanel2.dataPanel);
       } else {

       }

    }

    private void chartOptionPanelMouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void timeLabelMouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void statusLabelMouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void lightLabelMouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void button02ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button03ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button04ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button05ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button06ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button07ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button08ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button09ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button10ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void stationListKeyPressed(java.awt.event.KeyEvent evt) {
      System.out.println("key pressed in station list, keycode="+evt.getKeyCode()+",char="+evt.getKeyChar()+",id="+evt.getID()+",modifiers="+evt.getModifiers());
    }

    private void button25ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button26ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button27ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button28ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button29ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button30ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button11ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button12ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button13ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button14ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button15ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button16ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button17ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button18ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button19ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button20ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button21ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button22ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button23ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void button24ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void textLabel01MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel02MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel03MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel04MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel05MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel06MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel07MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel08MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel09MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel10MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel11MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel12MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel13MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel14MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel15MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel16MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel17MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel18MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel19MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel20MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel21MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel22MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel23MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel24MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel25MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel26MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel27MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel28MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel29MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textLabel30MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void comboBox01MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void comboBox02MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void comboBox03MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void comboBox04MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void comboBox05MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void comboBox06MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void comboBox07MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void comboBox08MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void comboBox09MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void comboBox10MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void comboBox11MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void comboBox12MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void comboBox13MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void comboBox14MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void comboBox15MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void comboBox16MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void comboBox17MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void comboBox18MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void comboBox19MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void comboBox20MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField01MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField02MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField03MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField04MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField05MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField06MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField07MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField08MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField09MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField10MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField11MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField12MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField13MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField14MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField15MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField16MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField17MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField18MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField19MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField21MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField20MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField22MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField23MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField24MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField25MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField26MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField27MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField28MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField29MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void textField30MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void checkBox01MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void checkBox02MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void checkBox03MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void checkBox04MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void checkBox05MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void checkBox06MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void checkBox07MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void checkBox08MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void checkBox09MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void checkBox10MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void checkBox11MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void checkBox12MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void checkBox13MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void checkBox14MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void checkBox15MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void checkBox16MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void checkBox17MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void checkBox18MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void checkBox19MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void checkBox20MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void radioButton01MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void radioButton02MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void radioButton03MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void radioButton04MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void radioButton05MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void radioButton06MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void radioButton07MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void radioButton08MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void radioButton09MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void radioButton10MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void radioButton11MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void radioButton12MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void radioButton13MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void radioButton14MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void radioButton15MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void radioButton16MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void radioButton17MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void radioButton18MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void radioButton19MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void radioButton20MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void panel01MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void panel02MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void panel03MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void panel04MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void panel05MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void panel06MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void panel07MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void panel08MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void panel09MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void panel10MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void deviceTableMousePressed(java.awt.event.MouseEvent evt) {
       mousePressed(evt,instrument.uiPanel2.jPanel144,"device table area");
    }
void mousePressed(java.awt.event.MouseEvent evt,Component c,String key){
      instrument.uiPanel2.jTabbedPane1.setSelectedComponent((Component)c);
      int x=evt.getX(),y=evt.getY(),width2=0,height2=0;
      localMousePressX=x;
      localMousePressY=y;

      mousePressType=0;
      int panelWidth = this.getWidth();
      int panelHeight = this.getHeight();
      String info[];

      if(instrument.editUI.get(key)!=null){
       info=ylib.csvlinetoarray((String)instrument.editUI.get(key));

       if(info.length>2 && info[2].equalsIgnoreCase("s")){
        int x2=0,y2=0;
        if(info.length > 3 && info[3].length()>0) {
            x2=(int)(Double.parseDouble(info[3]) * ((double)panelWidth));
            originalX=((double)Math.round(Double.parseDouble(info[3])*1000000.0))/1000000.0;
        }
        if(info.length > 4 && info[4].length()>0) {
            y2=(int)(Double.parseDouble(info[4]) * ((double)panelHeight))+(int)(((double)panelHeight)*headerRatio);
            originalY=((double)Math.round(Double.parseDouble(info[4])*1000000.0))/1000000.0;
        }
        if(info.length > 5 && info[5].length()>0) {
            width2=(int)(Double.parseDouble(info[5]) * ((double)panelWidth));
            originalWidth=((double)Math.round(Double.parseDouble(info[5])*1000000.0))/1000000.0;
        }
        if(info.length > 6 && info[6].length()>0) {
            height2=(int)(Double.parseDouble(info[6]) * ((double)panelHeight));
            originalHeight=((double)Math.round(Double.parseDouble(info[6])*1000000.0))/1000000.0;
        }
        globalMousePressX=evt.getXOnScreen();
        globalMousePressY=evt.getYOnScreen();
        if(x<=5 && y<=5)  mousePressType=8;
        else if(x>=width2 -5 &&  y<=5) mousePressType=5;
        else if(x>=width2 -5 &&  y>=height2-5) mousePressType=6;
        else if(x<=5 &&  y>=height2-5) mousePressType=7;
        else if(y<=5) mousePressType=1;
        else if(x>=width2-5) mousePressType=2;
        else if(y>=height2-5)mousePressType=3;
        else if(x<=5) mousePressType=4;
       }
      }
     instrument.uiPanel2.jComboBox36.setSelectedItem(key);

}
void mouseReleased(){
      localMousePressX=-1;
      localMousePressY=-1;
      globalMousePressX=-1;
      globalMousePressY=-1;
      mousePressType=-1;
}
void mouseDragged(java.awt.event.MouseEvent evt,Component c,String key){
      int x=evt.getX(),y=evt.getY();
      int panelWidth = this.getWidth();
      int panelHeight = this.getHeight();
      int diffX=0,diffY=0;
      int x2=0,y2=0,width=0,height=0;
      if(instrument.editUI.get(key)!=null){
       String info[]=ylib.csvlinetoarray((String)instrument.editUI.get(key));

       if(info.length>2 && info[2].equalsIgnoreCase("s")){
        diffX=evt.getXOnScreen()-globalMousePressX;
        diffY=evt.getYOnScreen()-globalMousePressY;
        switch(mousePressType){
          case 0:
            double newInfo3=((double)Math.round((originalX+((double)diffX)/((double)panelWidth))*1000000.0))/1000000.0;
            double newInfo4=((double)Math.round((originalY+((double)diffY)/((double)panelHeight))*1000000.0))/1000000.0;
            instrument.skipUITFChanged=true;
              instrument.uiPanel2.jTextField5.setText(""+((double)Math.round(newInfo3*1000000.0))/10000.0);
              instrument.uiPanel2.jTextField9.setText(""+((double)Math.round(newInfo4*1000000.0))/10000.0);

            if(key.equalsIgnoreCase("data area")){
              instrument.uiPanel2.jTextField6.setText(""+((double)Math.round(newInfo3*1000000.0))/10000.0);
              instrument.uiPanel2.jTextField7.setText(""+((double)Math.round(newInfo4*1000000.0))/10000.0);
            }
            info[3]=""+newInfo3;
            info[4]=""+newInfo4;
            instrument.editUI.put(key,ylib.arrayToCsvLine(info));
            invalidate();

            instrument.uiPanel2.updateDataAreaPanel();
            instrument.updateUIDALayoutAll=true;
            instrument.uiDataPanel.invalidate();
            instrument.skipUITFChanged=false;
            break;
          case 1:
          case 3:

            double newInfo4_2=(mousePressType==3? Double.parseDouble(info[4]):((double)Math.round((originalY+((double)diffY)/((double)panelHeight))*1000000.0))/1000000.0);
            double newInfo6_2=originalHeight+((double)Math.round((((double)diffY)/((double)panelHeight))*10000.0))/10000.0*(mousePressType==3? 1.0:-1.0);
            if(newInfo6_2<0) newInfo6_2=5.0/((double)panelHeight);
            instrument.skipUITFChanged=true;
            instrument.uiPanel2.jTextField9.setText(""+((double)Math.round(newInfo4_2*1000000.0))/10000.0);
            instrument.uiPanel2.jTextField61.setText(""+((double)Math.round(newInfo6_2*1000000.0))/10000.0);
            if(key.equalsIgnoreCase("data area")){
              instrument.uiPanel2.jTextField7.setText(""+((double)Math.round(newInfo4_2*1000000.0))/10000.0);
              instrument.uiPanel2.jTextField2.setText(""+((double)Math.round(newInfo6_2*1000000.0))/10000.0);
            }
            info[4]=""+newInfo4_2;
            info[6]=""+newInfo6_2;
            instrument.editUI.put(key,ylib.arrayToCsvLine(info));
            invalidate();
            instrument.uiPanel2.updateDataAreaPanel();
            instrument.updateUIDALayoutAll=true;
            instrument.uiDataPanel.invalidate();
            instrument.skipUITFChanged=false;
            break;
          case 2:
          case 4:

            double newInfo3_4=(mousePressType==2? Double.parseDouble(info[3]):((double)Math.round((originalX+((double)diffX)/((double)panelWidth))*1000000.0))/1000000.0);
            double newInfo5_4=originalWidth+((double)Math.round((((double)diffX)/((double)panelWidth))*10000.0))/10000.0*(mousePressType==2? 1.0:-1.0);
            if(newInfo5_4<0) newInfo5_4=5.0/((double)panelWidth);
            instrument.skipUITFChanged=true;
            instrument.uiPanel2.jTextField5.setText(""+((double)Math.round(newInfo3_4*1000000.0))/10000.0);
            instrument.uiPanel2.jTextField10.setText(""+((double)Math.round(newInfo5_4*1000000.0))/10000.0);
            if(key.equalsIgnoreCase("data area")){
              instrument.uiPanel2.jTextField7.setText(""+((double)Math.round(newInfo3_4*1000000.0))/10000.0);
              instrument.uiPanel2.jTextField2.setText(""+((double)Math.round(newInfo5_4*1000000.0))/10000.0);
            }
            info[3]=""+newInfo3_4;
            info[5]=""+newInfo5_4;
            instrument.editUI.put(key,ylib.arrayToCsvLine(info));
            invalidate();
            instrument.uiPanel2.updateDataAreaPanel();
            instrument.updateUIDALayoutAll=true;
            instrument.uiDataPanel.invalidate();
            instrument.skipUITFChanged=false;
            break;
          case 5:
          case 6:
          case 7:
          case 8:

            double newInfo3_5=(mousePressType==5 || mousePressType==6? Double.parseDouble(info[3]):((double)Math.round((originalX+((double)diffX)/((double)panelWidth))*1000000.0))/1000000.0);
            double newInfo4_5=(mousePressType==6 || mousePressType==7? Double.parseDouble(info[4]):((double)Math.round((originalY+((double)diffY)/((double)panelHeight))*1000000.0))/1000000.0);
            double newInfo5_5=originalWidth+((double)Math.round((((double)diffX)/((double)panelWidth))*10000.0))/10000.0*(mousePressType==7 || mousePressType==8? -1.0:1.0);
            double newInfo6_5=originalHeight+((double)Math.round((((double)diffY)/((double)panelHeight))*10000.0))/10000.0*(mousePressType==5 || mousePressType==8? -1.0:1.0);
            if(newInfo5_5<0) newInfo5_5=5.0/((double)panelWidth);
            if(newInfo6_5<0) newInfo6_5=5.0/((double)panelHeight);
            instrument.skipUITFChanged=true;
            instrument.uiPanel2.jTextField5.setText(""+((double)Math.round(newInfo3_5*1000000.0))/10000.0);
            instrument.uiPanel2.jTextField9.setText(""+((double)Math.round(newInfo4_5*1000000.0))/10000.0);
            instrument.uiPanel2.jTextField10.setText(""+((double)Math.round(newInfo5_5*1000000.0))/10000.0);
            instrument.uiPanel2.jTextField61.setText(""+((double)Math.round(newInfo6_5*1000000.0))/10000.0);
            if(key.equalsIgnoreCase("data area")){
              instrument.uiPanel2.jTextField6.setText(""+((double)Math.round(newInfo3_5*1000000.0))/10000.0);
              instrument.uiPanel2.jTextField7.setText(""+((double)Math.round(newInfo4_5*1000000.0))/10000.0);
              instrument.uiPanel2.jTextField1.setText(""+((double)Math.round(newInfo5_5*1000000.0))/10000.0);
              instrument.uiPanel2.jTextField2.setText(""+((double)Math.round(newInfo6_5*1000000.0))/10000.0);
            }
            info[3]=""+newInfo3_5;
            info[4]=""+newInfo4_5;
            info[5]=""+newInfo5_5;
            info[6]=""+newInfo6_5;
            instrument.editUI.put(key,ylib.arrayToCsvLine(info));
            invalidate();
            instrument.uiPanel2.updateDataAreaPanel();
            instrument.updateUIDALayoutAll=true;
            instrument.uiDataPanel.invalidate();
            instrument.skipUITFChanged=false;
            break;
         }
       }
      }

}
    private void deviceTableMouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void deviceTableMouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"device table area");

    }

    private void button02MousePressed(java.awt.event.MouseEvent evt) {
       mousePressed(evt,instrument.uiPanel2.jPanel144,"button 02");
    }

    private void button02MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button02MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 02");
    }

    private void timeLabelMousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"time area");
    }

    private void btnConnectMousePressed(java.awt.event.MouseEvent evt) {
       mousePressed(evt,instrument.uiPanel2.jPanel144,"connect button");
    }

    private void btnStartMousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"start button");        
    }

    private void button03MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 03");        
    }

    private void statusLabelMousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"light label area");        
    }

    private void button04MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 04");        
    }

    private void button05MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 05");        
    }

    private void button06MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 06");        
    }

    private void button07MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 07");        
    }

    private void button08MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 08");        
    }

    private void button09MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 09");        
    }

    private void button10MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 10");        
    }

    private void lightLabelMousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"light area");        
    }

    private void headerLabelMousePressed(java.awt.event.MouseEvent evt) {

    }

    private void textLabel01MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textlabel 01");        
    }

    private void comboBox01MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"combobox 01");        
    }

    private void textField01MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 01");        
    }

    private void textField02MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 02");        
    }

    private void textField03MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 03");        
    }

    private void textField04MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 04");        
    }

    private void textField05MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 05");        
    }

    private void textField06MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 06");        
    }

    private void textField07MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 07");        
    }

    private void textField08MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 08");        
    }

    private void textField09MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 09");        
    }

    private void textField10MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 10");        
    }

    private void textField11MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 11");        
    }

    private void textField12MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 12");        
    }

    private void textField13MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 13");        
    }

    private void textField14MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 14");        
    }

    private void textField15MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 15");        
    }

    private void textField16MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 16");        
    }

    private void textField17MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 17");        
    }

    private void textField18MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 18");        
    }

    private void textField19MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 19");        
    }

    private void textField20MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 20");        
    }

    private void textField21MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 21");        
    }

    private void textLabel22MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 22");        
    }

    private void textLabel23MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 23");        
    }

    private void textLabel24MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 24");        
    }

    private void textLabel25MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 25");        
    }

    private void textLabel26MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 26");        
    }

    private void textLabel27MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 27");        
    }

    private void textLabel28MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 28");        
    }

    private void textLabel29MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 29");        
    }

    private void textLabel30MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 30");        
    }

    private void comboBox02MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"combobox 02");        
    }

    private void comboBox03MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"combobox 03");        
    }

    private void comboBox04MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"combobox 04");        
    }

    private void comboBox05MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"combobox 05");        
    }

    private void comboBox06MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"combobox 06");        
    }

    private void comboBox07MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"combobox 07");        
    }

    private void comboBox08MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"combobox 08");        
    }

    private void comboBox09MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"combobox 09");        
    }

    private void comboBox10MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"combobox 10");        
    }

    private void comboBox11MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"combobox 11");        
    }

    private void comboBox12MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"combobox 12");        
    }

    private void comboBox13MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"combobox 13");        
    }

    private void comboBox14MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"combobox 14");        
    }

    private void comboBox15MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"combobox 15");        
    }

    private void comboBox16MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"combobox 16");        
    }

    private void comboBox17MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"combobox 17");        
    }

    private void comboBox18MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"combobox 18");        
    }

    private void comboBox19MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"combobox 19");        
    }

    private void comboBox20MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"combobox 20");        
    }

    private void button25MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 25");        
    }

    private void button26MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 26");        
    }

    private void button27MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 27");        
    }

    private void button28MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 28");        
    }

    private void button29MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 29");        
    }

    private void button30MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 30");        
    }

    private void button11MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 11");        
    }

    private void button12MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 12");        
    }

    private void button13MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 13");        
    }

    private void button14MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 14");        
    }

    private void button15MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 15");        
    }

    private void button16MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 16");        
    }

    private void button17MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 17");        
    }

    private void button18MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 18");        
    }

    private void button19MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 19");        
    }

    private void button20MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 20");        
    }

    private void button21MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 21");        
    }

    private void button22MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 22");        
    }

    private void button23MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 23");        
    }

    private void button24MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"button 24");        
    }

    private void checkBox01MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"checkbox 01");        
    }

    private void radioButton01MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"radiobutton 01");        
    }

    private void checkBox02MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"checkbox 02");        
    }

    private void checkBox03MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"checkbox 03");        
    }

    private void checkBox04MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"checkbox 04");        
    }

    private void checkBox05MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"checkbox 05");        
    }

    private void checkBox06MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"checkbox 06");        
    }

    private void checkBox07MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"checkbox 07");        
    }

    private void checkBox08MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"checkbox 08");        
    }

    private void checkBox09MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"checkbox 09");        
    }

    private void checkBox10MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"checkbox 10");        
    }

    private void checkBox11MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"checkbox 11");        
    }

    private void checkBox12MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"checkbox 12");        
    }

    private void checkBox13MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"checkbox 13");        
    }

    private void checkBox14MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"checkbox 14");        
    }

    private void checkBox15MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"checkbox 15");        
    }

    private void checkBox16MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"checkbox 16");        
    }

    private void checkBox17MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"checkbox 17");        
    }

    private void checkBox18MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"checkbox 18");        
    }

    private void checkBox19MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"checkbox 19");        
    }

    private void checkBox20MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"checkbox 20");        
    }

    private void radioButton02MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"radiobutton 02");        
    }

    private void radioButton03MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"radiobutton 03");        
    }

    private void radioButton04MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"radiobutton 04");        
    }

    private void radioButton05MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"radiobutton 05");        
    }

    private void radioButton06MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"radiobutton 06");        
    }

    private void radioButton07MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"radiobutton 07");        
    }

    private void radioButton08MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"radiobutton 08");        
    }

    private void radioButton09MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"radiobutton 09");        
    }

    private void radioButton10MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"radiobutton 10");        
    }

    private void radioButton11MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"radiobutton 11");        
    }

    private void radioButton12MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"radiobutton 12");        
    }

    private void radioButton13MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"radiobutton 13");        
    }

    private void radioButton14MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"radiobutton 14");        
    }

    private void radioButton15MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"radiobutton 15");        
    }

    private void radioButton16MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"radiobutton 16");        
    }

    private void radioButton17MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"radiobutton 17");        
    }

    private void radioButton18MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"radiobutton 18");        
    }

    private void radioButton19MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"radiobutton 19");        
    }

    private void radioButton20MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"radiobutton 20");        
    }

    private void chartOptionPanelMousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"chart option area");        
    }

    private void dataPanelMousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"data area");        
    }

    private void chartPanelMousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"chart area");        
    }

    private void stationListMousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"station list area");        
    }

    private void panel01MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"panel 01");        
    }

    private void panel02MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"panel 02");        
    }

    private void panel03MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"panel 03");        
    }

    private void panel04MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"panel 04");        
    }

    private void panel05MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"panel 05");        
    }

    private void panel06MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"panel 06");        
    }

    private void panel07MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"panel 07");        
    }

    private void panel08MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"panel 08");        
    }

    private void panel09MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"panel 09");        
    }

    private void panel10MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"panel 10");        
    }

    private void timeLabelMouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void btnConnectMouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void btnStartMouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button03MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void statusLabelMouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button04MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button05MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button06MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button07MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button08MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button09MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void lightLabelMouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void headerLabelMouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textLabel01MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void comboBox01MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField01MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField02MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField03MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField04MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField05MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField06MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField07MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField08MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField09MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField10MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField11MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField12MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField13MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField14MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField15MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField16MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField17MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField18MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField19MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField20MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField21MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField22MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField23MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField24MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField25MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField26MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textField26MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 26");        
    }

    private void textField27MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 27");        
    }

    private void textField28MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 28");        
    }

    private void textField29MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 29");        
    }

    private void textField30MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textfield 30");        
    }

    private void textLabel02MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textlabel 02");        
    }

    private void textLabel03MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textlabel 03");        
    }

    private void textLabel04MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textlabel 04");        
    }

    private void textLabel05MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textlabel 05");        
    }

    private void textLabel06MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textlabel 06");        
    }

    private void textLabel07MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textlabel 07");        
    }

    private void textLabel08MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textlabel 08");        
    }

    private void textLabel09MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textlabel 09");        
    }

    private void textLabel10MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textlabel 10");        
    }

    private void textLabel11MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textlabel 11");        
    }

    private void textLabel12MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textlabel 12");        
    }

    private void textLabel13MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textlabel 13");        
    }

    private void textLabel14MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textlabel 14");        
    }

    private void textLabel15MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textlabel 15");        
    }

    private void textLabel16MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textlabel 16");        
    }

    private void textLabel17MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textlabel 17");        
    }

    private void textLabel18MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textlabel 18");        
    }

    private void textLabel19MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textlabel 19");        
    }

    private void textLabel20MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textlabel 20");        
    }

    private void textLabel21MousePressed(java.awt.event.MouseEvent evt) {
      mousePressed(evt,instrument.uiPanel2.jPanel144,"textlabel 21");        
    }

    private void textLabel26MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textLabel27MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textLabel28MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textLabel29MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textLabel30MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void comboBox02MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void comboBox03MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void comboBox04MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void comboBox05MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void comboBox06MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void comboBox07MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void comboBox08MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void comboBox09MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void comboBox10MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void comboBox11MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void comboBox12MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void comboBox13MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void comboBox14MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void comboBox15MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void comboBox16MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void comboBox17MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void comboBox18MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void comboBox19MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void comboBox20MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button25MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button26MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button27MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button28MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button29MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button30MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button11MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button12MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button13MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button14MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button15MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button16MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button17MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button18MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button19MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button20MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button21MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button22MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button23MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button24MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void checkBox01MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void radioButton01MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void checkBox02MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void checkBox03MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void checkBox04MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void checkBox05MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void checkBox06MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void checkBox07MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void checkBox08MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void checkBox09MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void checkBox10MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void checkBox11MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void checkBox12MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void checkBox13MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void checkBox14MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void checkBox15MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void checkBox16MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void checkBox17MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void checkBox18MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void checkBox19MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void checkBox20MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void radioButton02MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void radioButton03MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void radioButton04MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void radioButton05MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void radioButton06MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void radioButton07MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void radioButton08MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void radioButton09MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void radioButton10MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void radioButton11MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void radioButton12MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void radioButton13MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void radioButton14MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void radioButton15MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void radioButton16MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void radioButton17MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void radioButton18MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void radioButton19MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void radioButton20MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void chartOptionPanelMouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void dataPanelMouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void chartPanelMouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void stationListMouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void panel01MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void panel02MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void panel03MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void panel04MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void panel05MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void panel06MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void panel07MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void panel08MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void panel09MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void panel10MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void timeLabelMouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"time area");
    }

    private void btnConnectMouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"connect button");
    }

    private void btnStartMouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"start button");
    }

    private void button03MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 03");
    }

    private void statusLabelMouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"light label area");
    }

    private void button04MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 04");
    }

    private void button05MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 05");
    }

    private void button06MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 06");
    }

    private void button07MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 07");
    }

    private void button08MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 08");
    }

    private void button09MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 09");
    }

    private void button10MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void button10MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 10");
    }

    private void lightLabelMouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"light area");
    }

    private void headerLabelMouseDragged(java.awt.event.MouseEvent evt) {

    }

    private void textLabel01MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 01");
    }

    private void comboBox01MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"combobox 01");
    }

    private void textField01MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 01");
    }

    private void textField02MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 02");
    }

    private void textField03MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 03");
    }

    private void textField04MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 04");
    }

    private void textField05MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 05");
    }

    private void textField06MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 06");
    }

    private void textField07MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 07");
    }

    private void textField08MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 08");
    }

    private void textField09MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 09");
    }

    private void textField10MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 10");
    }

    private void textField11MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 11");
    }

    private void textField12MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 02");
    }

    private void textField13MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 13");
    }

    private void textField14MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 14");
    }

    private void textField15MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 15");
    }

    private void textField16MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 16");
    }

    private void textField17MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 17");
    }

    private void textField18MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 18");
    }

    private void textField19MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 19");
    }

    private void textField20MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 20");
    }

    private void textField21MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 21");
    }

    private void textField22MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 22");
    }

    private void textField23MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 23");
    }

    private void textField24MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 24");
    }

    private void textField25MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 25");
    }

    private void textField26MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 26");
    }

    private void textField27MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 27");
    }

    private void textField28MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 28");
    }

    private void textField29MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 29");
    }

    private void textField30MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textfield 30");
    }

    private void textLabel02MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 02");
    }

    private void textLabel03MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 03");
    }

    private void textLabel04MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 04");
    }

    private void textLabel05MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 05");
    }

    private void textLabel06MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 06");
    }

    private void textLabel07MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 07");
    }

    private void textLabel08MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 08");
    }

    private void textLabel09MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 09");
    }

    private void textLabel10MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 10");
    }

    private void textLabel11MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 11");
    }

    private void textLabel12MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 12");
    }

    private void textLabel13MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 13");
    }

    private void textLabel12MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textLabel13MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textLabel14MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textLabel15MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textLabel16MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textLabel17MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textLabel18MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textLabel19MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textLabel20MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textLabel21MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textLabel22MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textLabel23MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textLabel24MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textLabel25MouseReleased(java.awt.event.MouseEvent evt) {
     mouseReleased();
    }

    private void textLabel14MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 14");
    }

    private void textLabel15MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 15");
    }

    private void textLabel16MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 16");
    }

    private void textLabel17MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 17");
    }

    private void textLabel18MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 18");
    }

    private void textLabel19MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 19");
    }

    private void textLabel20MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 20");
    }

    private void textLabel21MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 21");
    }

    private void textLabel22MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 22");
    }

    private void textLabel23MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 23");
    }

    private void textLabel24MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 24");
    }

    private void textLabel25MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 25");
    }

    private void textLabel26MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 26");
    }

    private void textLabel27MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 27");
    }

    private void textLabel28MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 28");
    }

    private void textLabel29MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 29");
    }

    private void textLabel30MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"textlabel 30");
    }

    private void comboBox02MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"combobox 02");
    }

    private void comboBox03MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"combobox 03");
    }

    private void comboBox04MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"combobox 04");
    }

    private void comboBox05MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"combobox 05");
    }

    private void comboBox06MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"combobox 06");
    }

    private void comboBox07MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"combobox 07");
    }

    private void comboBox08MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"combobox 08");
    }

    private void comboBox09MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"combobox 09");
    }

    private void comboBox10MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"combobox 10");
    }

    private void comboBox11MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"combobox 11");
    }

    private void comboBox12MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 02");
    }

    private void comboBox13MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"combobox 13");
    }

    private void comboBox14MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"combobox 14");
    }

    private void comboBox15MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"combobox 15");
    }

    private void comboBox16MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"combobox 16");
    }

    private void comboBox17MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"combobox 17");
    }

    private void comboBox18MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"combobox 18");
    }

    private void comboBox19MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"combobox 19");
    }

    private void comboBox20MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"combobox 20");
    }

    private void button25MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 25");
    }

    private void button26MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 26");
    }

    private void button27MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 27");
    }

    private void button28MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 28");
    }

    private void button29MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 29");
    }

    private void button30MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 30");
    }

    private void button11MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 11");
    }

    private void button12MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 12");
    }

    private void button13MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 13");
    }

    private void button14MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 14");
    }

    private void button15MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 15");
    }

    private void button16MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 16");
    }

    private void button17MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 17");
    }

    private void button18MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 18");
    }

    private void button19MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 19");
    }

    private void button20MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 20");
    }

    private void button21MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 21");
    }

    private void button22MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 22");
    }

    private void button23MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 23");
    }

    private void button24MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 24");
    }

    private void checkBox01MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"checkbox 01");
    }

    private void radioButton01MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"radiobutton 01");
    }

    private void checkBox02MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"checkbox 02");
    }

    private void checkBox03MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"checkbox 03");
    }

    private void checkBox04MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"checkbox 04");
    }

    private void checkBox05MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"checkbox 05");
    }

    private void checkBox06MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"checkbox 06");
    }

    private void checkBox07MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"checkbox 07");
    }

    private void checkBox08MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"checkbox 08");
    }

    private void checkBox09MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"checkbox 09");
    }

    private void checkBox10MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"checkbox 10");
    }

    private void checkBox11MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"checkbox 11");
    }

    private void checkBox12MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 02");
    }

    private void checkBox13MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"checkbox 13");
    }

    private void checkBox14MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"checkbox 14");
    }

    private void checkBox15MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"checkbox 15");
    }

    private void checkBox16MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"checkbox 16");
    }

    private void checkBox17MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 02");
    }

    private void checkBox18MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"checkbox 18");
    }

    private void checkBox19MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"checkbox 19");
    }

    private void checkBox20MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"checkbox 20");
    }

    private void radioButton02MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"button 02");
    }

    private void radioButton03MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"radiobutton 03");
    }

    private void radioButton04MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"radiobutton 04");
    }

    private void radioButton05MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"radiobutton 05");
    }

    private void radioButton06MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"radiobutton 06");
    }

    private void radioButton07MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"radiobutton 07");
    }

    private void radioButton08MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"radiobutton 08");
    }

    private void radioButton09MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"radiobutton 09");
    }

    private void radioButton10MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"radiobutton 10");
    }

    private void radioButton11MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"radiobutton 11");
    }

    private void radioButton12MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"radiobutton 12");
    }

    private void radioButton13MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"radiobutton 13");
    }

    private void radioButton14MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"radiobutton 14");
    }

    private void radioButton15MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"radiobutton 15");
    }

    private void radioButton16MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"radiobutton 16");
    }

    private void radioButton17MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"radiobutton 17");
    }

    private void radioButton18MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"radiobutton 18");
    }

    private void radioButton19MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"radiobutton 19");
    }

    private void radioButton20MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"radiobutton 20");
    }

    private void chartOptionPanelMouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"chart option area");
    }

    private void dataPanelMouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"data area");
    }

    private void chartPanelMouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"chart area");
    }

    private void stationListMouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"station list area");
    }

    private void panel01MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"panel 01");
    }

    private void panel02MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"panel 02");
    }

    private void panel03MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"panel 03");
    }

    private void panel04MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"panel 04");
    }

    private void panel05MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"panel 05");
    }

    private void panel06MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"panel 06");
    }

    private void panel07MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"panel 07");
    }

    private void panel08MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"panel 08");
    }

    private void panel09MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"panel 09");
    }

    private void panel10MouseDragged(java.awt.event.MouseEvent evt) {
      mouseDragged(evt,instrument.uiPanel2.jPanel144,"panel 10");
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
    private javax.swing.JButton button11;
    private javax.swing.JButton button12;
    private javax.swing.JButton button13;
    private javax.swing.JButton button14;
    private javax.swing.JButton button15;
    private javax.swing.JButton button16;
    private javax.swing.JButton button17;
    private javax.swing.JButton button18;
    private javax.swing.JButton button19;
    private javax.swing.JButton button20;
    private javax.swing.JButton button21;
    private javax.swing.JButton button22;
    private javax.swing.JButton button23;
    private javax.swing.JButton button24;
    private javax.swing.JButton button25;
    private javax.swing.JButton button26;
    private javax.swing.JButton button27;
    private javax.swing.JButton button28;
    private javax.swing.JButton button29;
    private javax.swing.JButton button30;
    private javax.swing.JPanel chartOptionPanel;
    private javax.swing.JPanel chartPanel;
    private javax.swing.JCheckBox checkBox01;
    private javax.swing.JCheckBox checkBox02;
    private javax.swing.JCheckBox checkBox03;
    private javax.swing.JCheckBox checkBox04;
    private javax.swing.JCheckBox checkBox05;
    private javax.swing.JCheckBox checkBox06;
    private javax.swing.JCheckBox checkBox07;
    private javax.swing.JCheckBox checkBox08;
    private javax.swing.JCheckBox checkBox09;
    private javax.swing.JCheckBox checkBox10;
    private javax.swing.JCheckBox checkBox11;
    private javax.swing.JCheckBox checkBox12;
    private javax.swing.JCheckBox checkBox13;
    private javax.swing.JCheckBox checkBox14;
    private javax.swing.JCheckBox checkBox15;
    private javax.swing.JCheckBox checkBox16;
    private javax.swing.JCheckBox checkBox17;
    private javax.swing.JCheckBox checkBox18;
    private javax.swing.JCheckBox checkBox19;
    private javax.swing.JCheckBox checkBox20;
    private javax.swing.JComboBox comboBox01;
    private javax.swing.JComboBox comboBox02;
    private javax.swing.JComboBox comboBox03;
    private javax.swing.JComboBox comboBox04;
    private javax.swing.JComboBox comboBox05;
    private javax.swing.JComboBox comboBox06;
    private javax.swing.JComboBox comboBox07;
    private javax.swing.JComboBox comboBox08;
    private javax.swing.JComboBox comboBox09;
    private javax.swing.JComboBox comboBox10;
    private javax.swing.JComboBox comboBox11;
    private javax.swing.JComboBox comboBox12;
    private javax.swing.JComboBox comboBox13;
    private javax.swing.JComboBox comboBox14;
    private javax.swing.JComboBox comboBox15;
    private javax.swing.JComboBox comboBox16;
    private javax.swing.JComboBox comboBox17;
    private javax.swing.JComboBox comboBox18;
    private javax.swing.JComboBox comboBox19;
    private javax.swing.JComboBox comboBox20;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JPanel deviceTable;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JLabel lightLabel;
    public javax.swing.JPanel panel01;
    public javax.swing.JPanel panel02;
    public javax.swing.JPanel panel03;
    public javax.swing.JPanel panel04;
    public javax.swing.JPanel panel05;
    public javax.swing.JPanel panel06;
    public javax.swing.JPanel panel07;
    public javax.swing.JPanel panel08;
    public javax.swing.JPanel panel09;
    public javax.swing.JPanel panel10;
    private javax.swing.JRadioButton radioButton01;
    private javax.swing.JRadioButton radioButton02;
    private javax.swing.JRadioButton radioButton03;
    private javax.swing.JRadioButton radioButton04;
    private javax.swing.JRadioButton radioButton05;
    private javax.swing.JRadioButton radioButton06;
    private javax.swing.JRadioButton radioButton07;
    private javax.swing.JRadioButton radioButton08;
    private javax.swing.JRadioButton radioButton09;
    private javax.swing.JRadioButton radioButton10;
    private javax.swing.JRadioButton radioButton11;
    private javax.swing.JRadioButton radioButton12;
    private javax.swing.JRadioButton radioButton13;
    private javax.swing.JRadioButton radioButton14;
    private javax.swing.JRadioButton radioButton15;
    private javax.swing.JRadioButton radioButton16;
    private javax.swing.JRadioButton radioButton17;
    private javax.swing.JRadioButton radioButton18;
    private javax.swing.JRadioButton radioButton19;
    private javax.swing.JRadioButton radioButton20;
    private javax.swing.JPanel stationList;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JTextField textField01;
    private javax.swing.JTextField textField02;
    private javax.swing.JTextField textField03;
    private javax.swing.JTextField textField04;
    private javax.swing.JTextField textField05;
    private javax.swing.JTextField textField06;
    private javax.swing.JTextField textField07;
    private javax.swing.JTextField textField08;
    private javax.swing.JTextField textField09;
    private javax.swing.JTextField textField10;
    private javax.swing.JTextField textField11;
    private javax.swing.JTextField textField12;
    private javax.swing.JTextField textField13;
    private javax.swing.JTextField textField14;
    private javax.swing.JTextField textField15;
    private javax.swing.JTextField textField16;
    private javax.swing.JTextField textField17;
    private javax.swing.JTextField textField18;
    private javax.swing.JTextField textField19;
    private javax.swing.JTextField textField20;
    private javax.swing.JTextField textField21;
    private javax.swing.JTextField textField22;
    private javax.swing.JTextField textField23;
    private javax.swing.JTextField textField24;
    private javax.swing.JTextField textField25;
    private javax.swing.JTextField textField26;
    private javax.swing.JTextField textField27;
    private javax.swing.JTextField textField28;
    private javax.swing.JTextField textField29;
    private javax.swing.JTextField textField30;
    private javax.swing.JLabel textLabel01;
    private javax.swing.JLabel textLabel02;
    private javax.swing.JLabel textLabel03;
    private javax.swing.JLabel textLabel04;
    private javax.swing.JLabel textLabel05;
    private javax.swing.JLabel textLabel06;
    private javax.swing.JLabel textLabel07;
    private javax.swing.JLabel textLabel08;
    private javax.swing.JLabel textLabel09;
    private javax.swing.JLabel textLabel10;
    private javax.swing.JLabel textLabel11;
    private javax.swing.JLabel textLabel12;
    private javax.swing.JLabel textLabel13;
    private javax.swing.JLabel textLabel14;
    private javax.swing.JLabel textLabel15;
    private javax.swing.JLabel textLabel16;
    private javax.swing.JLabel textLabel17;
    private javax.swing.JLabel textLabel18;
    private javax.swing.JLabel textLabel19;
    private javax.swing.JLabel textLabel20;
    private javax.swing.JLabel textLabel21;
    private javax.swing.JLabel textLabel22;
    private javax.swing.JLabel textLabel23;
    private javax.swing.JLabel textLabel24;
    private javax.swing.JLabel textLabel25;
    private javax.swing.JLabel textLabel26;
    private javax.swing.JLabel textLabel27;
    private javax.swing.JLabel textLabel28;
    private javax.swing.JLabel textLabel29;
    private javax.swing.JLabel textLabel30;
    private javax.swing.JLabel timeLabel;

}