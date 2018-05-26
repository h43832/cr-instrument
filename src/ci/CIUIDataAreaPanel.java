
package ci;

import static ci.CrInstrument.isNumeric;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;
import y.ylib.ylib;

/**
 *
 * @author peter
 */
public class CIUIDataAreaPanel extends javax.swing.JPanel {

    CrInstrument instrument;
    ResourceBundle bundle2 = java.util.ResourceBundle.getBundle("ci/Bundle");
      int localMousePressX=-1,localMousePressY=-1,globalMousePressX=-1,globalMousePressY=-1,
          mousePressType=-1;
  double originalX=-1.0,originalY=-1.0,originalWidth=-1.0,originalHeight=-1.0,xRatio=0.0,yRatio=0.0;
    public CIUIDataAreaPanel(CrInstrument ci) {
        initComponents();
        this.instrument=ci;
    }
    public void doLayout(){
        super.doLayout();

        if(instrument.editUI.get("data area")!=null){
         String info[]=ylib.csvlinetoarray((String)instrument.editUI.get("data area"));
         if(instrument.isNumeric(info[7])){
          setBackground( new Color(Integer.parseInt(info[7])));
         }

        String title=bundle2.getString("CrInstrument.jPanel162.border.title");
        if(title.length()>0){
        Color fontColor=((info.length>10 && info[10].length()>0 && isNumeric(info[10]))? new Color(Integer.parseInt(info[10])):getForeground());
        Color borderColor=((info.length>13 && info[13].length()>0 && isNumeric(info[13]))? new Color(Integer.parseInt(info[13])):new java.awt.Color(102, 0, 255));

          Font titleFont=getFont();
         int fontSize=(isNumeric(info[9])? Integer.parseInt(info[9]):getFont().getSize());
         titleFont=instrument.getFont(getFont(), fontSize, getFont().getFontName(), ""+fontSize, info[11].equalsIgnoreCase("b"), info[12].equalsIgnoreCase("i"));
         Border titleBorder=javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(borderColor), title, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, titleFont, fontColor);
         setBorder(titleBorder); 
        }

        }
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
       int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
       int frameWidth = instrument.getWidth();
     int frameHeight = instrument.getHeight();
    if(instrument.editUI.get("frame")!=null){
      String frameInfo=(String)instrument.editUI.get("frame");
      String info[]=ylib.csvlinetoarray(frameInfo);

      if(info.length > 5 && info[5].length()>0){
        if(info[2].equals("%")) frameWidth=(int)(Double.parseDouble(info[5]) * ((double)screenWidth)); else frameWidth=Integer.parseInt(info[5]);
      }
      if(info.length > 6 && info[6].length()>0){
        if(info[2].equals("%")) frameHeight=(int)(Double.parseDouble(info[6]) * ((double)screenHeight)); else frameHeight=Integer.parseInt(info[6]);
      }
    }
    setDataArea(frameWidth,frameHeight);
      if(!instrument.initStage) instrument.updateUIDALayoutAll=false;
    }
    void setDataArea(int frameWidth,int frameHeight){

      setLabel("da_station 01",da2_station_01,frameWidth,frameHeight);
      setLabel("da_station 02",da2_station_02,frameWidth,frameHeight);
      setLabel("da_station 03",da2_station_03,frameWidth,frameHeight);
      setLabel("da_station 04",da2_station_04,frameWidth,frameHeight);
      setLabel("da_station 05",da2_station_05,frameWidth,frameHeight);
      setLabel("da_station 06",da2_station_06,frameWidth,frameHeight);
      setLabel("da_station 07",da2_station_07,frameWidth,frameHeight);
      setLabel("da_station 08",da2_station_08,frameWidth,frameHeight);
      setLabel("da_station 09",da2_station_09,frameWidth,frameHeight);
      setLabel("da_station 10",da2_station_10,frameWidth,frameHeight);
      setLabel("da_station 11",da2_station_11,frameWidth,frameHeight);
      setLabel("da_station 12",da2_station_12,frameWidth,frameHeight);
      setLabel("da_station 13",da2_station_13,frameWidth,frameHeight);
      setLabel("da_station 14",da2_station_14,frameWidth,frameHeight);
      setLabel("da_station 15",da2_station_15,frameWidth,frameHeight);
      setLabel("da_station 16",da2_station_16,frameWidth,frameHeight);
      setLabel("da_device 01",da2_device_01,frameWidth,frameHeight);
      setLabel("da_device 02",da2_device_02,frameWidth,frameHeight);
      setLabel("da_device 03",da2_device_03,frameWidth,frameHeight);
      setLabel("da_device 04",da2_device_04,frameWidth,frameHeight);
      setLabel("da_device 05",da2_device_05,frameWidth,frameHeight);
      setLabel("da_device 06",da2_device_06,frameWidth,frameHeight);
      setLabel("da_device 07",da2_device_07,frameWidth,frameHeight);
      setLabel("da_device 08",da2_device_08,frameWidth,frameHeight);
      setLabel("da_device 09",da2_device_09,frameWidth,frameHeight);
      setLabel("da_device 10",da2_device_10,frameWidth,frameHeight);
      setLabel("da_device 11",da2_device_11,frameWidth,frameHeight);
      setLabel("da_device 12",da2_device_12,frameWidth,frameHeight);
      setLabel("da_device 13",da2_device_13,frameWidth,frameHeight);
      setLabel("da_device 14",da2_device_14,frameWidth,frameHeight);
      setLabel("da_device 15",da2_device_15,frameWidth,frameHeight);
      setLabel("da_device 16",da2_device_16,frameWidth,frameHeight);
      setLabel("da_device 17",da2_device_17,frameWidth,frameHeight);
      setLabel("da_device 18",da2_device_18,frameWidth,frameHeight);
      setLabel("da_device 19",da2_device_19,frameWidth,frameHeight);
      setLabel("da_device 20",da2_device_20,frameWidth,frameHeight);
      setLabel("da_device 21",da2_device_21,frameWidth,frameHeight);
      setLabel("da_device 22",da2_device_22,frameWidth,frameHeight);
      setLabel("da_device 23",da2_device_23,frameWidth,frameHeight);
      setLabel("da_device 24",da2_device_24,frameWidth,frameHeight);
      setLabel("da_device 25",da2_device_25,frameWidth,frameHeight);
      setLabel("da_device 26",da2_device_26,frameWidth,frameHeight);
      setLabel("da_device 27",da2_device_27,frameWidth,frameHeight);
      setLabel("da_device 28",da2_device_28,frameWidth,frameHeight);
      setLabel("da_device 29",da2_device_29,frameWidth,frameHeight);
      setLabel("da_device 30",da2_device_30,frameWidth,frameHeight);
      setLabel("da_device 31",da2_device_31,frameWidth,frameHeight);
      setLabel("da_device 32",da2_device_32,frameWidth,frameHeight);
      setLabel("da_dataname 01",da2_dataname_01,frameWidth,frameHeight);
      setLabel("da_dataname 02",da2_dataname_02,frameWidth,frameHeight);
      setLabel("da_dataname 03",da2_dataname_03,frameWidth,frameHeight);
      setLabel("da_dataname 04",da2_dataname_04,frameWidth,frameHeight);
      setLabel("da_dataname 05",da2_dataname_05,frameWidth,frameHeight);
      setLabel("da_dataname 06",da2_dataname_06,frameWidth,frameHeight);
      setLabel("da_dataname 07",da2_dataname_07,frameWidth,frameHeight);
      setLabel("da_dataname 08",da2_dataname_08,frameWidth,frameHeight);
      setLabel("da_dataname 09",da2_dataname_09,frameWidth,frameHeight);
      setLabel("da_dataname 10",da2_dataname_10,frameWidth,frameHeight);
      setLabel("da_dataname 11",da2_dataname_11,frameWidth,frameHeight);
      setLabel("da_dataname 12",da2_dataname_12,frameWidth,frameHeight);
      setLabel("da_dataname 13",da2_dataname_13,frameWidth,frameHeight);
      setLabel("da_dataname 14",da2_dataname_14,frameWidth,frameHeight);
      setLabel("da_dataname 15",da2_dataname_15,frameWidth,frameHeight);
      setLabel("da_dataname 16",da2_dataname_16,frameWidth,frameHeight);
      setLabel("da_dataname 17",da2_dataname_17,frameWidth,frameHeight);
      setLabel("da_dataname 18",da2_dataname_18,frameWidth,frameHeight);
      setLabel("da_dataname 19",da2_dataname_19,frameWidth,frameHeight);
      setLabel("da_dataname 20",da2_dataname_20,frameWidth,frameHeight);
      setLabel("da_dataname 21",da2_dataname_21,frameWidth,frameHeight);
      setLabel("da_dataname 22",da2_dataname_22,frameWidth,frameHeight);
      setLabel("da_dataname 23",da2_dataname_23,frameWidth,frameHeight);
      setLabel("da_dataname 24",da2_dataname_24,frameWidth,frameHeight);
      setLabel("da_dataname 25",da2_dataname_25,frameWidth,frameHeight);
      setLabel("da_dataname 26",da2_dataname_26,frameWidth,frameHeight);
      setLabel("da_dataname 27",da2_dataname_27,frameWidth,frameHeight);
      setLabel("da_dataname 28",da2_dataname_28,frameWidth,frameHeight);
      setLabel("da_dataname 29",da2_dataname_29,frameWidth,frameHeight);
      setLabel("da_dataname 30",da2_dataname_30,frameWidth,frameHeight);
      setLabel("da_dataname 31",da2_dataname_31,frameWidth,frameHeight);
      setLabel("da_dataname 32",da2_dataname_32,frameWidth,frameHeight);
      setLabel("da_dataname 33",da2_dataname_33,frameWidth,frameHeight);
      setLabel("da_dataname 34",da2_dataname_34,frameWidth,frameHeight);
      setLabel("da_dataname 35",da2_dataname_35,frameWidth,frameHeight);
      setLabel("da_dataname 36",da2_dataname_36,frameWidth,frameHeight);
      setLabel("da_dataname 37",da2_dataname_37,frameWidth,frameHeight);
      setLabel("da_dataname 38",da2_dataname_38,frameWidth,frameHeight);
      setLabel("da_dataname 39",da2_dataname_39,frameWidth,frameHeight);
      setLabel("da_dataname 40",da2_dataname_40,frameWidth,frameHeight);
      setLabel("da_dataname 41",da2_dataname_41,frameWidth,frameHeight);
      setLabel("da_dataname 42",da2_dataname_42,frameWidth,frameHeight);
      setLabel("da_dataname 43",da2_dataname_43,frameWidth,frameHeight);
      setLabel("da_dataname 44",da2_dataname_44,frameWidth,frameHeight);
      setLabel("da_dataname 45",da2_dataname_45,frameWidth,frameHeight);
      setLabel("da_dataname 46",da2_dataname_46,frameWidth,frameHeight);
      setLabel("da_dataname 47",da2_dataname_47,frameWidth,frameHeight);
      setLabel("da_dataname 48",da2_dataname_48,frameWidth,frameHeight);
      setLabel("da_datavalue 01",da2_datavalue_01,frameWidth,frameHeight);
      setLabel("da_datavalue 02",da2_datavalue_02,frameWidth,frameHeight);
      setLabel("da_datavalue 03",da2_datavalue_03,frameWidth,frameHeight);
      setLabel("da_datavalue 04",da2_datavalue_04,frameWidth,frameHeight);
      setLabel("da_datavalue 05",da2_datavalue_05,frameWidth,frameHeight);
      setLabel("da_datavalue 06",da2_datavalue_06,frameWidth,frameHeight);
      setLabel("da_datavalue 07",da2_datavalue_07,frameWidth,frameHeight);
      setLabel("da_datavalue 08",da2_datavalue_08,frameWidth,frameHeight);
      setLabel("da_datavalue 09",da2_datavalue_09,frameWidth,frameHeight);
      setLabel("da_datavalue 10",da2_datavalue_10,frameWidth,frameHeight);
      setLabel("da_datavalue 11",da2_datavalue_11,frameWidth,frameHeight);
      setLabel("da_datavalue 12",da2_datavalue_12,frameWidth,frameHeight);
      setLabel("da_datavalue 13",da2_datavalue_13,frameWidth,frameHeight);
      setLabel("da_datavalue 14",da2_datavalue_14,frameWidth,frameHeight);
      setLabel("da_datavalue 15",da2_datavalue_15,frameWidth,frameHeight);
      setLabel("da_datavalue 16",da2_datavalue_16,frameWidth,frameHeight);
      setLabel("da_datavalue 17",da2_datavalue_17,frameWidth,frameHeight);
      setLabel("da_datavalue 18",da2_datavalue_18,frameWidth,frameHeight);
      setLabel("da_datavalue 19",da2_datavalue_19,frameWidth,frameHeight);
      setLabel("da_datavalue 20",da2_datavalue_20,frameWidth,frameHeight);
      setLabel("da_datavalue 21",da2_datavalue_21,frameWidth,frameHeight);
      setLabel("da_datavalue 22",da2_datavalue_22,frameWidth,frameHeight);
      setLabel("da_datavalue 23",da2_datavalue_23,frameWidth,frameHeight);
      setLabel("da_datavalue 24",da2_datavalue_24,frameWidth,frameHeight);
      setLabel("da_datavalue 25",da2_datavalue_25,frameWidth,frameHeight);
      setLabel("da_datavalue 26",da2_datavalue_26,frameWidth,frameHeight);
      setLabel("da_datavalue 27",da2_datavalue_27,frameWidth,frameHeight);
      setLabel("da_datavalue 28",da2_datavalue_28,frameWidth,frameHeight);
      setLabel("da_datavalue 29",da2_datavalue_29,frameWidth,frameHeight);
      setLabel("da_datavalue 30",da2_datavalue_30,frameWidth,frameHeight);
      setLabel("da_datavalue 31",da2_datavalue_31,frameWidth,frameHeight);
      setLabel("da_datavalue 32",da2_datavalue_32,frameWidth,frameHeight);
      setLabel("da_datavalue 33",da2_datavalue_33,frameWidth,frameHeight);
      setLabel("da_datavalue 34",da2_datavalue_34,frameWidth,frameHeight);
      setLabel("da_datavalue 35",da2_datavalue_35,frameWidth,frameHeight);
      setLabel("da_datavalue 36",da2_datavalue_36,frameWidth,frameHeight);
      setLabel("da_datavalue 37",da2_datavalue_37,frameWidth,frameHeight);
      setLabel("da_datavalue 38",da2_datavalue_38,frameWidth,frameHeight);
      setLabel("da_datavalue 39",da2_datavalue_39,frameWidth,frameHeight);
      setLabel("da_datavalue 40",da2_datavalue_40,frameWidth,frameHeight);
      setLabel("da_datavalue 41",da2_datavalue_41,frameWidth,frameHeight);
      setLabel("da_datavalue 42",da2_datavalue_42,frameWidth,frameHeight);
      setLabel("da_datavalue 43",da2_datavalue_43,frameWidth,frameHeight);
      setLabel("da_datavalue 44",da2_datavalue_44,frameWidth,frameHeight);
      setLabel("da_datavalue 45",da2_datavalue_45,frameWidth,frameHeight);
      setLabel("da_datavalue 46",da2_datavalue_46,frameWidth,frameHeight);
      setLabel("da_datavalue 47",da2_datavalue_47,frameWidth,frameHeight);
      setLabel("da_datavalue 48",da2_datavalue_48,frameWidth,frameHeight);
      setLabel("da_xlabel 01",da2_xlabel_01,frameWidth,frameHeight);
      setLabel("da_xlabel 02",da2_xlabel_02,frameWidth,frameHeight);
      setLabel("da_xlabel 03",da2_xlabel_03,frameWidth,frameHeight);
      setLabel("da_xlabel 04",da2_xlabel_04,frameWidth,frameHeight);
      setLabel("da_xlabel 05",da2_xlabel_05,frameWidth,frameHeight);
      setLabel("da_xlabel 06",da2_xlabel_06,frameWidth,frameHeight);
      setLabel("da_xlabel 07",da2_xlabel_07,frameWidth,frameHeight);
      setLabel("da_xlabel 08",da2_xlabel_08,frameWidth,frameHeight);
      setLabel("da_xlabel 09",da2_xlabel_09,frameWidth,frameHeight);
      setLabel("da_xlabel 10",da2_xlabel_10,frameWidth,frameHeight);
   }
   void setLabel(String key,JLabel label,int frameWidth,int frameHeight){
     String info[];
     if(instrument.editUI.get(key)!=null){
         info=ylib.csvlinetoarray((String)instrument.editUI.get(key));
         if(info.length>2 && info[2].equalsIgnoreCase("s")){
           int x2=0,y2=0,width2=0,height2=0;
           label.setVisible(true);
           if(key.startsWith("da_x")) label.setText(info[1]);
           if(info.length > 3 && info[3].length()>0) x2=(int)(Double.parseDouble(info[3]) * ((double)frameWidth));
           if(info.length > 4 && info[4].length()>0) y2=(int)(Double.parseDouble(info[4]) * ((double)frameHeight));
           if(info.length > 5 && info[5].length()>0) width2=(int)(Double.parseDouble(info[5]) * ((double)frameWidth));
           if(info.length > 6 && info[6].length()>0) height2=(int)(Double.parseDouble(info[6]) * ((double)frameHeight));
           label.setBounds((int)(((double)x2)/instrument.dataAreaRatio), (int)(((double)y2)/instrument.dataAreaRatio),
                   (int)(((double)width2)/instrument.dataAreaRatio),(int)(((double)height2)/instrument.dataAreaRatio));

           Font font=label.getFont();
           if(info[1].trim().length()>0) label.setText(info[1]);
           label.setOpaque((info.length>13 && info[13].equalsIgnoreCase("o")));
           label.setFont(instrument.getFont(font,font.getSize(),info[8],info[9],info[11].equalsIgnoreCase("b"),info[12].equalsIgnoreCase("i")));
           label.setBackground((info.length>7 && info[7].length()>0 && instrument.isNumeric(info[7]))? new Color(Integer.parseInt(info[7])):label.getBackground());
           label.setForeground((info.length>10 && info[10].length()>0 && instrument.isNumeric(info[10]))? new Color(Integer.parseInt(info[10])):label.getForeground());
           if(instrument.selectedDataItem.length()>0 && instrument.selectedDataItem.equalsIgnoreCase(key)){
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
   void updateDataItem(){
    if(instrument.skipUITFChanged) return;
    if(instrument.uiPanel2.jComboBox15.getSelectedIndex()!=-1){
       String sel=(String)instrument.uiPanel2.jComboBox15.getSelectedItem();
    if(instrument.editUI.get(sel)==null) return;
    String info[]=ylib.csvlinetoarray((String)instrument.editUI.get(sel));
    if(instrument.uiPanel2.jCheckBox64.isSelected()) info[2]="s"; else info[2]="e";
    info[3]=(instrument.isNumeric(instrument.uiPanel2.jTextField84.getText())? String.valueOf(Double.parseDouble(instrument.uiPanel2.jTextField84.getText())/100.0):"0.0");
    info[4]=(instrument.isNumeric(instrument.uiPanel2.jTextField85.getText())? String.valueOf(Double.parseDouble(instrument.uiPanel2.jTextField85.getText())/100.0):"0.0");
    info[5]=(instrument.isNumeric(instrument.uiPanel2.jTextField86.getText())? String.valueOf(Double.parseDouble(instrument.uiPanel2.jTextField86.getText())/100.0):"0.0");
    info[6]=(instrument.isNumeric(instrument.uiPanel2.jTextField87.getText())? String.valueOf(Double.parseDouble(instrument.uiPanel2.jTextField87.getText())/100.0):"0.0");
    info[8]=(String)instrument.uiPanel2.jComboBox54.getSelectedItem();
    if(info[8].equals(bundle2.getString("CrInstrument.xy.msg150"))) info[8]="";
    info[9]=instrument.uiPanel2.jTextField88.getText().trim();
    if(instrument.uiPanel2.jCheckBox65.isSelected()) info[11]="b"; else info[11]="e";
    if(instrument.uiPanel2.jCheckBox66.isSelected()) info[12]="i"; else info[12]="e";
    info[7]=""+instrument.uiPanel2.jLabel324.getBackground().getRGB();
    info[10]=""+instrument.uiPanel2.jLabel328.getBackground().getRGB();
    if(instrument.uiPanel2.jCheckBox1.isSelected()) info[13]="o"; else info[13]="e";
    info[18]=""+instrument.uiPanel2.jLabel22.getBackground().getRGB();

    instrument.editUI.put(sel,ylib.arrayToCsvLine(info));
    instrument.updateUIDALayoutAll=true;
    invalidate();
     }
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    private void initComponents() {

        da2_datavalue_01 = new javax.swing.JLabel();
        da2_device_01 = new javax.swing.JLabel();
        da2_dataname_01 = new javax.swing.JLabel();
        da2_station_01 = new javax.swing.JLabel();
        da2_station_02 = new javax.swing.JLabel();
        da2_station_03 = new javax.swing.JLabel();
        da2_station_04 = new javax.swing.JLabel();
        da2_station_05 = new javax.swing.JLabel();
        da2_station_06 = new javax.swing.JLabel();
        da2_station_07 = new javax.swing.JLabel();
        da2_station_08 = new javax.swing.JLabel();
        da2_station_09 = new javax.swing.JLabel();
        da2_station_10 = new javax.swing.JLabel();
        da2_station_11 = new javax.swing.JLabel();
        da2_station_12 = new javax.swing.JLabel();
        da2_station_13 = new javax.swing.JLabel();
        da2_station_14 = new javax.swing.JLabel();
        da2_station_15 = new javax.swing.JLabel();
        da2_station_16 = new javax.swing.JLabel();
        da2_device_02 = new javax.swing.JLabel();
        da2_device_03 = new javax.swing.JLabel();
        da2_device_04 = new javax.swing.JLabel();
        da2_device_05 = new javax.swing.JLabel();
        da2_device_06 = new javax.swing.JLabel();
        da2_device_07 = new javax.swing.JLabel();
        da2_device_08 = new javax.swing.JLabel();
        da2_device_09 = new javax.swing.JLabel();
        da2_device_10 = new javax.swing.JLabel();
        da2_device_11 = new javax.swing.JLabel();
        da2_device_12 = new javax.swing.JLabel();
        da2_device_13 = new javax.swing.JLabel();
        da2_device_14 = new javax.swing.JLabel();
        da2_device_15 = new javax.swing.JLabel();
        da2_device_16 = new javax.swing.JLabel();
        da2_device_17 = new javax.swing.JLabel();
        da2_device_18 = new javax.swing.JLabel();
        da2_device_19 = new javax.swing.JLabel();
        da2_device_20 = new javax.swing.JLabel();
        da2_device_21 = new javax.swing.JLabel();
        da2_device_22 = new javax.swing.JLabel();
        da2_device_23 = new javax.swing.JLabel();
        da2_device_24 = new javax.swing.JLabel();
        da2_device_25 = new javax.swing.JLabel();
        da2_device_26 = new javax.swing.JLabel();
        da2_device_27 = new javax.swing.JLabel();
        da2_device_28 = new javax.swing.JLabel();
        da2_device_29 = new javax.swing.JLabel();
        da2_device_30 = new javax.swing.JLabel();
        da2_device_31 = new javax.swing.JLabel();
        da2_device_32 = new javax.swing.JLabel();
        da2_dataname_02 = new javax.swing.JLabel();
        da2_dataname_03 = new javax.swing.JLabel();
        da2_dataname_04 = new javax.swing.JLabel();
        da2_dataname_05 = new javax.swing.JLabel();
        da2_dataname_06 = new javax.swing.JLabel();
        da2_dataname_07 = new javax.swing.JLabel();
        da2_dataname_08 = new javax.swing.JLabel();
        da2_dataname_09 = new javax.swing.JLabel();
        da2_dataname_10 = new javax.swing.JLabel();
        da2_dataname_11 = new javax.swing.JLabel();
        da2_dataname_12 = new javax.swing.JLabel();
        da2_dataname_13 = new javax.swing.JLabel();
        da2_dataname_14 = new javax.swing.JLabel();
        da2_dataname_15 = new javax.swing.JLabel();
        da2_dataname_16 = new javax.swing.JLabel();
        da2_dataname_17 = new javax.swing.JLabel();
        da2_dataname_18 = new javax.swing.JLabel();
        da2_dataname_19 = new javax.swing.JLabel();
        da2_dataname_20 = new javax.swing.JLabel();
        da2_dataname_21 = new javax.swing.JLabel();
        da2_dataname_22 = new javax.swing.JLabel();
        da2_dataname_23 = new javax.swing.JLabel();
        da2_dataname_24 = new javax.swing.JLabel();
        da2_dataname_25 = new javax.swing.JLabel();
        da2_dataname_26 = new javax.swing.JLabel();
        da2_dataname_27 = new javax.swing.JLabel();
        da2_dataname_28 = new javax.swing.JLabel();
        da2_dataname_29 = new javax.swing.JLabel();
        da2_dataname_30 = new javax.swing.JLabel();
        da2_dataname_31 = new javax.swing.JLabel();
        da2_dataname_32 = new javax.swing.JLabel();
        da2_dataname_33 = new javax.swing.JLabel();
        da2_dataname_34 = new javax.swing.JLabel();
        da2_dataname_35 = new javax.swing.JLabel();
        da2_dataname_36 = new javax.swing.JLabel();
        da2_dataname_37 = new javax.swing.JLabel();
        da2_dataname_38 = new javax.swing.JLabel();
        da2_dataname_39 = new javax.swing.JLabel();
        da2_dataname_40 = new javax.swing.JLabel();
        da2_dataname_41 = new javax.swing.JLabel();
        da2_dataname_42 = new javax.swing.JLabel();
        da2_dataname_43 = new javax.swing.JLabel();
        da2_dataname_44 = new javax.swing.JLabel();
        da2_dataname_45 = new javax.swing.JLabel();
        da2_dataname_46 = new javax.swing.JLabel();
        da2_dataname_47 = new javax.swing.JLabel();
        da2_dataname_48 = new javax.swing.JLabel();
        da2_datavalue_02 = new javax.swing.JLabel();
        da2_datavalue_03 = new javax.swing.JLabel();
        da2_datavalue_04 = new javax.swing.JLabel();
        da2_datavalue_05 = new javax.swing.JLabel();
        da2_datavalue_06 = new javax.swing.JLabel();
        da2_datavalue_07 = new javax.swing.JLabel();
        da2_datavalue_08 = new javax.swing.JLabel();
        da2_datavalue_09 = new javax.swing.JLabel();
        da2_datavalue_10 = new javax.swing.JLabel();
        da2_datavalue_11 = new javax.swing.JLabel();
        da2_datavalue_12 = new javax.swing.JLabel();
        da2_datavalue_13 = new javax.swing.JLabel();
        da2_datavalue_14 = new javax.swing.JLabel();
        da2_datavalue_15 = new javax.swing.JLabel();
        da2_datavalue_16 = new javax.swing.JLabel();
        da2_datavalue_17 = new javax.swing.JLabel();
        da2_datavalue_18 = new javax.swing.JLabel();
        da2_datavalue_19 = new javax.swing.JLabel();
        da2_datavalue_20 = new javax.swing.JLabel();
        da2_datavalue_21 = new javax.swing.JLabel();
        da2_datavalue_22 = new javax.swing.JLabel();
        da2_datavalue_23 = new javax.swing.JLabel();
        da2_datavalue_24 = new javax.swing.JLabel();
        da2_datavalue_25 = new javax.swing.JLabel();
        da2_datavalue_26 = new javax.swing.JLabel();
        da2_datavalue_27 = new javax.swing.JLabel();
        da2_datavalue_28 = new javax.swing.JLabel();
        da2_datavalue_29 = new javax.swing.JLabel();
        da2_datavalue_30 = new javax.swing.JLabel();
        da2_datavalue_31 = new javax.swing.JLabel();
        da2_datavalue_32 = new javax.swing.JLabel();
        da2_datavalue_33 = new javax.swing.JLabel();
        da2_datavalue_34 = new javax.swing.JLabel();
        da2_datavalue_35 = new javax.swing.JLabel();
        da2_datavalue_36 = new javax.swing.JLabel();
        da2_datavalue_37 = new javax.swing.JLabel();
        da2_datavalue_38 = new javax.swing.JLabel();
        da2_datavalue_39 = new javax.swing.JLabel();
        da2_datavalue_40 = new javax.swing.JLabel();
        da2_datavalue_41 = new javax.swing.JLabel();
        da2_datavalue_42 = new javax.swing.JLabel();
        da2_datavalue_43 = new javax.swing.JLabel();
        da2_datavalue_44 = new javax.swing.JLabel();
        da2_datavalue_45 = new javax.swing.JLabel();
        da2_datavalue_46 = new javax.swing.JLabel();
        da2_datavalue_47 = new javax.swing.JLabel();
        da2_datavalue_48 = new javax.swing.JLabel();
        da2_xlabel_01 = new javax.swing.JLabel();
        da2_xlabel_02 = new javax.swing.JLabel();
        da2_xlabel_03 = new javax.swing.JLabel();
        da2_xlabel_04 = new javax.swing.JLabel();
        da2_xlabel_05 = new javax.swing.JLabel();
        da2_xlabel_06 = new javax.swing.JLabel();
        da2_xlabel_07 = new javax.swing.JLabel();
        da2_xlabel_08 = new javax.swing.JLabel();
        da2_xlabel_09 = new javax.swing.JLabel();
        da2_xlabel_10 = new javax.swing.JLabel();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        setLayout(null);

        da2_datavalue_01.setFont(da2_datavalue_01.getFont().deriveFont(da2_datavalue_01.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_01.getFont().getSize()+36));
        da2_datavalue_01.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_01.setText("A:+00.0000 ¢X");
        da2_datavalue_01.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_01MouseDragged(evt);
            }
        });
        da2_datavalue_01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_01MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_01MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_01MouseReleased(evt);
            }
        });
        add(da2_datavalue_01);
        da2_datavalue_01.setBounds(60, 150, 300, 60);

        da2_device_01.setFont(da2_device_01.getFont().deriveFont(da2_device_01.getFont().getStyle() | java.awt.Font.BOLD, da2_device_01.getFont().getSize()+6));
        da2_device_01.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_01.setText("Temp:+0.0");
        da2_device_01.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_01MouseDragged(evt);
            }
        });
        da2_device_01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_01MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_01MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_01MouseReleased(evt);
            }
        });
        add(da2_device_01);
        da2_device_01.setBounds(20, 60, 320, 30);

        da2_dataname_01.setFont(da2_dataname_01.getFont().deriveFont(da2_dataname_01.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_01.getFont().getSize()+6));
        da2_dataname_01.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_01.setText("Volt:+0.0");
        da2_dataname_01.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_01MouseDragged(evt);
            }
        });
        da2_dataname_01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_01MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_01MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_01MouseReleased(evt);
            }
        });
        add(da2_dataname_01);
        da2_dataname_01.setBounds(20, 110, 320, 30);

        da2_station_01.setFont(da2_station_01.getFont().deriveFont(da2_station_01.getFont().getStyle() | java.awt.Font.BOLD, da2_station_01.getFont().getSize()+6));
        da2_station_01.setForeground(new java.awt.Color(153, 255, 255));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ci/Bundle"); 
        da2_station_01.setText(bundle.getString("CIUIDataAreaPanel.da2_station_01.text")); 
        da2_station_01.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_station_01MouseDragged(evt);
            }
        });
        da2_station_01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_01MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_station_01MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_station_01MouseReleased(evt);
            }
        });
        add(da2_station_01);
        da2_station_01.setBounds(20, 30, 280, 20);

        da2_station_02.setFont(da2_station_02.getFont().deriveFont(da2_station_02.getFont().getStyle() | java.awt.Font.BOLD, da2_station_02.getFont().getSize()+6));
        da2_station_02.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_02.setText(bundle.getString("CIUIDataAreaPanel.da2_station_02.text")); 
        da2_station_02.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_station_02MouseDragged(evt);
            }
        });
        da2_station_02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_02MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_station_02MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_station_02MouseReleased(evt);
            }
        });
        add(da2_station_02);
        da2_station_02.setBounds(20, 30, 280, 20);

        da2_station_03.setFont(da2_station_03.getFont().deriveFont(da2_station_03.getFont().getStyle() | java.awt.Font.BOLD, da2_station_03.getFont().getSize()+6));
        da2_station_03.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_03.setText(bundle.getString("CIUIDataAreaPanel.da2_station_03.text")); 
        da2_station_03.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_station_03MouseDragged(evt);
            }
        });
        da2_station_03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_03MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_station_03MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_station_03MouseReleased(evt);
            }
        });
        add(da2_station_03);
        da2_station_03.setBounds(20, 30, 280, 20);

        da2_station_04.setFont(da2_station_04.getFont().deriveFont(da2_station_04.getFont().getStyle() | java.awt.Font.BOLD, da2_station_04.getFont().getSize()+6));
        da2_station_04.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_04.setText(bundle.getString("CIUIDataAreaPanel.da2_station_04.text")); 
        da2_station_04.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_station_04MouseDragged(evt);
            }
        });
        da2_station_04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_04MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_station_04MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_station_04MouseReleased(evt);
            }
        });
        add(da2_station_04);
        da2_station_04.setBounds(20, 30, 280, 20);

        da2_station_05.setFont(da2_station_05.getFont().deriveFont(da2_station_05.getFont().getStyle() | java.awt.Font.BOLD, da2_station_05.getFont().getSize()+6));
        da2_station_05.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_05.setText(bundle.getString("CIUIDataAreaPanel.da2_station_05.text")); 
        da2_station_05.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_station_05MouseDragged(evt);
            }
        });
        da2_station_05.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_05MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_station_05MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_station_05MouseReleased(evt);
            }
        });
        add(da2_station_05);
        da2_station_05.setBounds(20, 30, 280, 20);

        da2_station_06.setFont(da2_station_06.getFont().deriveFont(da2_station_06.getFont().getStyle() | java.awt.Font.BOLD, da2_station_06.getFont().getSize()+6));
        da2_station_06.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_06.setText(bundle.getString("CIUIDataAreaPanel.da2_station_06.text")); 
        da2_station_06.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_station_06MouseDragged(evt);
            }
        });
        da2_station_06.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_06MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_station_06MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_station_06MouseReleased(evt);
            }
        });
        add(da2_station_06);
        da2_station_06.setBounds(20, 30, 280, 20);

        da2_station_07.setFont(da2_station_07.getFont().deriveFont(da2_station_07.getFont().getStyle() | java.awt.Font.BOLD, da2_station_07.getFont().getSize()+6));
        da2_station_07.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_07.setText(bundle.getString("CIUIDataAreaPanel.da2_station_07.text")); 
        da2_station_07.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_station_07MouseDragged(evt);
            }
        });
        da2_station_07.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_07MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_station_07MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_station_07MouseReleased(evt);
            }
        });
        add(da2_station_07);
        da2_station_07.setBounds(20, 30, 280, 20);

        da2_station_08.setFont(da2_station_08.getFont().deriveFont(da2_station_08.getFont().getStyle() | java.awt.Font.BOLD, da2_station_08.getFont().getSize()+6));
        da2_station_08.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_08.setText(bundle.getString("CIUIDataAreaPanel.da2_station_08.text")); 
        da2_station_08.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_station_08MouseDragged(evt);
            }
        });
        da2_station_08.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_08MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_station_08MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_station_08MouseReleased(evt);
            }
        });
        add(da2_station_08);
        da2_station_08.setBounds(20, 30, 280, 20);

        da2_station_09.setFont(da2_station_09.getFont().deriveFont(da2_station_09.getFont().getStyle() | java.awt.Font.BOLD, da2_station_09.getFont().getSize()+6));
        da2_station_09.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_09.setText("Station 09");
        da2_station_09.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_station_09MouseDragged(evt);
            }
        });
        da2_station_09.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_09MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_station_09MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_station_09MouseReleased(evt);
            }
        });
        add(da2_station_09);
        da2_station_09.setBounds(20, 30, 280, 20);

        da2_station_10.setFont(da2_station_10.getFont().deriveFont(da2_station_10.getFont().getStyle() | java.awt.Font.BOLD, da2_station_10.getFont().getSize()+6));
        da2_station_10.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_10.setText("Station 10");
        da2_station_10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_station_10MouseDragged(evt);
            }
        });
        da2_station_10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_10MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_station_10MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_station_10MouseReleased(evt);
            }
        });
        add(da2_station_10);
        da2_station_10.setBounds(20, 30, 280, 20);

        da2_station_11.setFont(da2_station_11.getFont().deriveFont(da2_station_11.getFont().getStyle() | java.awt.Font.BOLD, da2_station_11.getFont().getSize()+6));
        da2_station_11.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_11.setText("Station 11");
        da2_station_11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_station_11MouseDragged(evt);
            }
        });
        da2_station_11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_11MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_station_11MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_station_11MouseReleased(evt);
            }
        });
        add(da2_station_11);
        da2_station_11.setBounds(20, 30, 280, 20);

        da2_station_12.setFont(da2_station_12.getFont().deriveFont(da2_station_12.getFont().getStyle() | java.awt.Font.BOLD, da2_station_12.getFont().getSize()+6));
        da2_station_12.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_12.setText("Station 12");
        da2_station_12.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_station_12MouseDragged(evt);
            }
        });
        da2_station_12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_12MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_station_12MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_station_12MouseReleased(evt);
            }
        });
        add(da2_station_12);
        da2_station_12.setBounds(20, 30, 280, 20);

        da2_station_13.setFont(da2_station_13.getFont().deriveFont(da2_station_13.getFont().getStyle() | java.awt.Font.BOLD, da2_station_13.getFont().getSize()+6));
        da2_station_13.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_13.setText("Station 13");
        da2_station_13.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_station_13MouseDragged(evt);
            }
        });
        da2_station_13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_13MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_station_13MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_station_13MouseReleased(evt);
            }
        });
        add(da2_station_13);
        da2_station_13.setBounds(20, 30, 280, 20);

        da2_station_14.setFont(da2_station_14.getFont().deriveFont(da2_station_14.getFont().getStyle() | java.awt.Font.BOLD, da2_station_14.getFont().getSize()+6));
        da2_station_14.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_14.setText("Station 14");
        da2_station_14.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_station_14MouseDragged(evt);
            }
        });
        da2_station_14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_14MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_station_14MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_station_14MouseReleased(evt);
            }
        });
        add(da2_station_14);
        da2_station_14.setBounds(20, 30, 280, 20);

        da2_station_15.setFont(da2_station_15.getFont().deriveFont(da2_station_15.getFont().getStyle() | java.awt.Font.BOLD, da2_station_15.getFont().getSize()+6));
        da2_station_15.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_15.setText("Station 15");
        da2_station_15.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_station_15MouseDragged(evt);
            }
        });
        da2_station_15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_15MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_station_15MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_station_15MouseReleased(evt);
            }
        });
        add(da2_station_15);
        da2_station_15.setBounds(20, 30, 280, 20);

        da2_station_16.setFont(da2_station_16.getFont().deriveFont(da2_station_16.getFont().getStyle() | java.awt.Font.BOLD, da2_station_16.getFont().getSize()+6));
        da2_station_16.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_16.setText("Station 16");
        da2_station_16.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_station_16MouseDragged(evt);
            }
        });
        da2_station_16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_16MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_station_16MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_station_16MouseReleased(evt);
            }
        });
        add(da2_station_16);
        da2_station_16.setBounds(20, 30, 280, 20);

        da2_device_02.setFont(da2_device_02.getFont().deriveFont(da2_device_02.getFont().getStyle() | java.awt.Font.BOLD, da2_device_02.getFont().getSize()+6));
        da2_device_02.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_02.setText("device 02");
        da2_device_02.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_02MouseDragged(evt);
            }
        });
        da2_device_02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_02MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_02MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_02MouseReleased(evt);
            }
        });
        add(da2_device_02);
        da2_device_02.setBounds(20, 60, 320, 30);

        da2_device_03.setFont(da2_device_03.getFont().deriveFont(da2_device_03.getFont().getStyle() | java.awt.Font.BOLD, da2_device_03.getFont().getSize()+6));
        da2_device_03.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_03.setText("device 03");
        da2_device_03.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_03MouseDragged(evt);
            }
        });
        da2_device_03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_03MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_03MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_03MouseReleased(evt);
            }
        });
        add(da2_device_03);
        da2_device_03.setBounds(20, 60, 320, 30);

        da2_device_04.setFont(da2_device_04.getFont().deriveFont(da2_device_04.getFont().getStyle() | java.awt.Font.BOLD, da2_device_04.getFont().getSize()+6));
        da2_device_04.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_04.setText("device 04");
        da2_device_04.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_04MouseDragged(evt);
            }
        });
        da2_device_04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_04MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_04MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_04MouseReleased(evt);
            }
        });
        add(da2_device_04);
        da2_device_04.setBounds(20, 60, 320, 30);

        da2_device_05.setFont(da2_device_05.getFont().deriveFont(da2_device_05.getFont().getStyle() | java.awt.Font.BOLD, da2_device_05.getFont().getSize()+6));
        da2_device_05.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_05.setText("device 05");
        da2_device_05.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_05MouseDragged(evt);
            }
        });
        da2_device_05.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_05MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_05MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_05MouseReleased(evt);
            }
        });
        add(da2_device_05);
        da2_device_05.setBounds(20, 60, 320, 30);

        da2_device_06.setFont(da2_device_06.getFont().deriveFont(da2_device_06.getFont().getStyle() | java.awt.Font.BOLD, da2_device_06.getFont().getSize()+6));
        da2_device_06.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_06.setText("device 06");
        da2_device_06.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_06MouseDragged(evt);
            }
        });
        da2_device_06.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_06MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_06MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_06MouseReleased(evt);
            }
        });
        add(da2_device_06);
        da2_device_06.setBounds(20, 60, 320, 30);

        da2_device_07.setFont(da2_device_07.getFont().deriveFont(da2_device_07.getFont().getStyle() | java.awt.Font.BOLD, da2_device_07.getFont().getSize()+6));
        da2_device_07.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_07.setText("device 07");
        da2_device_07.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_07MouseDragged(evt);
            }
        });
        da2_device_07.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_07MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_07MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_07MouseReleased(evt);
            }
        });
        add(da2_device_07);
        da2_device_07.setBounds(20, 60, 320, 30);

        da2_device_08.setFont(da2_device_08.getFont().deriveFont(da2_device_08.getFont().getStyle() | java.awt.Font.BOLD, da2_device_08.getFont().getSize()+6));
        da2_device_08.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_08.setText("device 08");
        da2_device_08.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_08MouseDragged(evt);
            }
        });
        da2_device_08.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_08MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_08MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_08MouseReleased(evt);
            }
        });
        add(da2_device_08);
        da2_device_08.setBounds(20, 60, 320, 30);

        da2_device_09.setFont(da2_device_09.getFont().deriveFont(da2_device_09.getFont().getStyle() | java.awt.Font.BOLD, da2_device_09.getFont().getSize()+6));
        da2_device_09.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_09.setText("device 09");
        da2_device_09.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_09MouseDragged(evt);
            }
        });
        da2_device_09.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_09MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_09MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_09MouseReleased(evt);
            }
        });
        add(da2_device_09);
        da2_device_09.setBounds(20, 60, 320, 30);

        da2_device_10.setFont(da2_device_10.getFont().deriveFont(da2_device_10.getFont().getStyle() | java.awt.Font.BOLD, da2_device_10.getFont().getSize()+6));
        da2_device_10.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_10.setText("device 10");
        da2_device_10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_10MouseDragged(evt);
            }
        });
        da2_device_10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_10MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_10MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_10MouseReleased(evt);
            }
        });
        add(da2_device_10);
        da2_device_10.setBounds(20, 60, 320, 30);

        da2_device_11.setFont(da2_device_11.getFont().deriveFont(da2_device_11.getFont().getStyle() | java.awt.Font.BOLD, da2_device_11.getFont().getSize()+6));
        da2_device_11.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_11.setText("device 11");
        da2_device_11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_11MouseDragged(evt);
            }
        });
        da2_device_11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_11MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_11MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_11MouseReleased(evt);
            }
        });
        add(da2_device_11);
        da2_device_11.setBounds(20, 60, 320, 30);

        da2_device_12.setFont(da2_device_12.getFont().deriveFont(da2_device_12.getFont().getStyle() | java.awt.Font.BOLD, da2_device_12.getFont().getSize()+6));
        da2_device_12.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_12.setText("device 12");
        da2_device_12.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_12MouseDragged(evt);
            }
        });
        da2_device_12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_12MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_12MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_12MouseReleased(evt);
            }
        });
        add(da2_device_12);
        da2_device_12.setBounds(20, 60, 320, 30);

        da2_device_13.setFont(da2_device_13.getFont().deriveFont(da2_device_13.getFont().getStyle() | java.awt.Font.BOLD, da2_device_13.getFont().getSize()+6));
        da2_device_13.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_13.setText("device 13");
        da2_device_13.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_13MouseDragged(evt);
            }
        });
        da2_device_13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_13MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_13MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_13MouseReleased(evt);
            }
        });
        add(da2_device_13);
        da2_device_13.setBounds(20, 60, 320, 30);

        da2_device_14.setFont(da2_device_14.getFont().deriveFont(da2_device_14.getFont().getStyle() | java.awt.Font.BOLD, da2_device_14.getFont().getSize()+6));
        da2_device_14.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_14.setText("device 14");
        da2_device_14.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_14MouseDragged(evt);
            }
        });
        da2_device_14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_14MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_14MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_14MouseReleased(evt);
            }
        });
        add(da2_device_14);
        da2_device_14.setBounds(20, 60, 320, 30);

        da2_device_15.setFont(da2_device_15.getFont().deriveFont(da2_device_15.getFont().getStyle() | java.awt.Font.BOLD, da2_device_15.getFont().getSize()+6));
        da2_device_15.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_15.setText("device 15");
        da2_device_15.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_15MouseDragged(evt);
            }
        });
        da2_device_15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_15MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_15MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_15MouseReleased(evt);
            }
        });
        add(da2_device_15);
        da2_device_15.setBounds(20, 60, 320, 30);

        da2_device_16.setFont(da2_device_16.getFont().deriveFont(da2_device_16.getFont().getStyle() | java.awt.Font.BOLD, da2_device_16.getFont().getSize()+6));
        da2_device_16.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_16.setText("device 16");
        da2_device_16.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_16MouseDragged(evt);
            }
        });
        da2_device_16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_16MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_16MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_16MouseReleased(evt);
            }
        });
        add(da2_device_16);
        da2_device_16.setBounds(20, 60, 320, 30);

        da2_device_17.setFont(da2_device_17.getFont().deriveFont(da2_device_17.getFont().getStyle() | java.awt.Font.BOLD, da2_device_17.getFont().getSize()+6));
        da2_device_17.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_17.setText("device 17");
        da2_device_17.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_17MouseDragged(evt);
            }
        });
        da2_device_17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_17MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_17MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_17MouseReleased(evt);
            }
        });
        add(da2_device_17);
        da2_device_17.setBounds(20, 60, 320, 30);

        da2_device_18.setFont(da2_device_18.getFont().deriveFont(da2_device_18.getFont().getStyle() | java.awt.Font.BOLD, da2_device_18.getFont().getSize()+6));
        da2_device_18.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_18.setText("device 18");
        da2_device_18.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_18MouseDragged(evt);
            }
        });
        da2_device_18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_18MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_18MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_18MouseReleased(evt);
            }
        });
        add(da2_device_18);
        da2_device_18.setBounds(20, 60, 320, 30);

        da2_device_19.setFont(da2_device_19.getFont().deriveFont(da2_device_19.getFont().getStyle() | java.awt.Font.BOLD, da2_device_19.getFont().getSize()+6));
        da2_device_19.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_19.setText("device 19");
        da2_device_19.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_19MouseDragged(evt);
            }
        });
        da2_device_19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_19MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_19MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_19MouseReleased(evt);
            }
        });
        add(da2_device_19);
        da2_device_19.setBounds(20, 60, 320, 30);

        da2_device_20.setFont(da2_device_20.getFont().deriveFont(da2_device_20.getFont().getStyle() | java.awt.Font.BOLD, da2_device_20.getFont().getSize()+6));
        da2_device_20.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_20.setText("device 20");
        da2_device_20.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_20MouseDragged(evt);
            }
        });
        da2_device_20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_20MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_20MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_20MouseReleased(evt);
            }
        });
        add(da2_device_20);
        da2_device_20.setBounds(20, 60, 320, 30);

        da2_device_21.setFont(da2_device_21.getFont().deriveFont(da2_device_21.getFont().getStyle() | java.awt.Font.BOLD, da2_device_21.getFont().getSize()+6));
        da2_device_21.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_21.setText("device 21");
        da2_device_21.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_21MouseDragged(evt);
            }
        });
        da2_device_21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_21MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_21MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_21MouseReleased(evt);
            }
        });
        add(da2_device_21);
        da2_device_21.setBounds(20, 60, 320, 30);

        da2_device_22.setFont(da2_device_22.getFont().deriveFont(da2_device_22.getFont().getStyle() | java.awt.Font.BOLD, da2_device_22.getFont().getSize()+6));
        da2_device_22.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_22.setText("device 22");
        da2_device_22.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_22MouseDragged(evt);
            }
        });
        da2_device_22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_22MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_22MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_22MouseReleased(evt);
            }
        });
        add(da2_device_22);
        da2_device_22.setBounds(20, 60, 320, 30);

        da2_device_23.setFont(da2_device_23.getFont().deriveFont(da2_device_23.getFont().getStyle() | java.awt.Font.BOLD, da2_device_23.getFont().getSize()+6));
        da2_device_23.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_23.setText("device 23");
        da2_device_23.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_23MouseDragged(evt);
            }
        });
        da2_device_23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_23MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_23MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_23MouseReleased(evt);
            }
        });
        add(da2_device_23);
        da2_device_23.setBounds(20, 60, 320, 30);

        da2_device_24.setFont(da2_device_24.getFont().deriveFont(da2_device_24.getFont().getStyle() | java.awt.Font.BOLD, da2_device_24.getFont().getSize()+6));
        da2_device_24.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_24.setText("device 24");
        da2_device_24.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_24MouseDragged(evt);
            }
        });
        da2_device_24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_24MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_24MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_24MouseReleased(evt);
            }
        });
        add(da2_device_24);
        da2_device_24.setBounds(20, 60, 320, 30);

        da2_device_25.setFont(da2_device_25.getFont().deriveFont(da2_device_25.getFont().getStyle() | java.awt.Font.BOLD, da2_device_25.getFont().getSize()+6));
        da2_device_25.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_25.setText("device 25");
        da2_device_25.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_25MouseDragged(evt);
            }
        });
        da2_device_25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_25MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_25MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_25MouseReleased(evt);
            }
        });
        add(da2_device_25);
        da2_device_25.setBounds(20, 60, 320, 30);

        da2_device_26.setFont(da2_device_26.getFont().deriveFont(da2_device_26.getFont().getStyle() | java.awt.Font.BOLD, da2_device_26.getFont().getSize()+6));
        da2_device_26.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_26.setText("device 26");
        da2_device_26.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_26MouseDragged(evt);
            }
        });
        da2_device_26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_26MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_26MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_26MouseReleased(evt);
            }
        });
        add(da2_device_26);
        da2_device_26.setBounds(20, 60, 320, 30);

        da2_device_27.setFont(da2_device_27.getFont().deriveFont(da2_device_27.getFont().getStyle() | java.awt.Font.BOLD, da2_device_27.getFont().getSize()+6));
        da2_device_27.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_27.setText("device 27");
        da2_device_27.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_27MouseDragged(evt);
            }
        });
        da2_device_27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_27MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_27MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_27MouseReleased(evt);
            }
        });
        add(da2_device_27);
        da2_device_27.setBounds(20, 60, 320, 30);

        da2_device_28.setFont(da2_device_28.getFont().deriveFont(da2_device_28.getFont().getStyle() | java.awt.Font.BOLD, da2_device_28.getFont().getSize()+6));
        da2_device_28.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_28.setText("device 28");
        da2_device_28.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_28MouseDragged(evt);
            }
        });
        da2_device_28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_28MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_28MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_28MouseReleased(evt);
            }
        });
        add(da2_device_28);
        da2_device_28.setBounds(20, 60, 320, 30);

        da2_device_29.setFont(da2_device_29.getFont().deriveFont(da2_device_29.getFont().getStyle() | java.awt.Font.BOLD, da2_device_29.getFont().getSize()+6));
        da2_device_29.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_29.setText("device 29");
        da2_device_29.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_29MouseDragged(evt);
            }
        });
        da2_device_29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_29MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_29MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_29MouseReleased(evt);
            }
        });
        add(da2_device_29);
        da2_device_29.setBounds(20, 60, 320, 30);

        da2_device_30.setFont(da2_device_30.getFont().deriveFont(da2_device_30.getFont().getStyle() | java.awt.Font.BOLD, da2_device_30.getFont().getSize()+6));
        da2_device_30.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_30.setText("device 30");
        da2_device_30.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_30MouseDragged(evt);
            }
        });
        da2_device_30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_30MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_30MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_30MouseReleased(evt);
            }
        });
        add(da2_device_30);
        da2_device_30.setBounds(20, 60, 320, 30);

        da2_device_31.setFont(da2_device_31.getFont().deriveFont(da2_device_31.getFont().getStyle() | java.awt.Font.BOLD, da2_device_31.getFont().getSize()+6));
        da2_device_31.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_31.setText("device 31");
        da2_device_31.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_31MouseDragged(evt);
            }
        });
        da2_device_31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_31MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_31MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_31MouseReleased(evt);
            }
        });
        add(da2_device_31);
        da2_device_31.setBounds(20, 60, 320, 30);

        da2_device_32.setFont(da2_device_32.getFont().deriveFont(da2_device_32.getFont().getStyle() | java.awt.Font.BOLD, da2_device_32.getFont().getSize()+6));
        da2_device_32.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_32.setText("device 32");
        da2_device_32.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_device_32MouseDragged(evt);
            }
        });
        da2_device_32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_32MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_device_32MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_device_32MouseReleased(evt);
            }
        });
        add(da2_device_32);
        da2_device_32.setBounds(20, 60, 320, 30);

        da2_dataname_02.setFont(da2_dataname_02.getFont().deriveFont(da2_dataname_02.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_02.getFont().getSize()+6));
        da2_dataname_02.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_02.setText("dataname02");
        da2_dataname_02.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_02MouseDragged(evt);
            }
        });
        da2_dataname_02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_02MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_02MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_02MouseReleased(evt);
            }
        });
        add(da2_dataname_02);
        da2_dataname_02.setBounds(20, 110, 320, 30);

        da2_dataname_03.setFont(da2_dataname_03.getFont().deriveFont(da2_dataname_03.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_03.getFont().getSize()+6));
        da2_dataname_03.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_03.setText("dataname03");
        da2_dataname_03.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_03MouseDragged(evt);
            }
        });
        da2_dataname_03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_03MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_03MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_03MouseReleased(evt);
            }
        });
        add(da2_dataname_03);
        da2_dataname_03.setBounds(20, 110, 320, 30);

        da2_dataname_04.setFont(da2_dataname_04.getFont().deriveFont(da2_dataname_04.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_04.getFont().getSize()+6));
        da2_dataname_04.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_04.setText("dataname04");
        da2_dataname_04.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_04MouseDragged(evt);
            }
        });
        da2_dataname_04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_04MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_04MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_04MouseReleased(evt);
            }
        });
        add(da2_dataname_04);
        da2_dataname_04.setBounds(20, 110, 320, 30);

        da2_dataname_05.setFont(da2_dataname_05.getFont().deriveFont(da2_dataname_05.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_05.getFont().getSize()+6));
        da2_dataname_05.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_05.setText("dataname05");
        da2_dataname_05.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_05MouseDragged(evt);
            }
        });
        da2_dataname_05.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_05MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_05MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_05MouseReleased(evt);
            }
        });
        add(da2_dataname_05);
        da2_dataname_05.setBounds(20, 110, 320, 30);

        da2_dataname_06.setFont(da2_dataname_06.getFont().deriveFont(da2_dataname_06.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_06.getFont().getSize()+6));
        da2_dataname_06.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_06.setText("dataname06");
        da2_dataname_06.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_06MouseDragged(evt);
            }
        });
        da2_dataname_06.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_06MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_06MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_06MouseReleased(evt);
            }
        });
        add(da2_dataname_06);
        da2_dataname_06.setBounds(20, 110, 320, 30);

        da2_dataname_07.setFont(da2_dataname_07.getFont().deriveFont(da2_dataname_07.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_07.getFont().getSize()+6));
        da2_dataname_07.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_07.setText("dataname07");
        da2_dataname_07.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_07MouseDragged(evt);
            }
        });
        da2_dataname_07.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_07MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_07MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_07MouseReleased(evt);
            }
        });
        add(da2_dataname_07);
        da2_dataname_07.setBounds(20, 110, 320, 30);

        da2_dataname_08.setFont(da2_dataname_08.getFont().deriveFont(da2_dataname_08.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_08.getFont().getSize()+6));
        da2_dataname_08.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_08.setText("dataname08");
        da2_dataname_08.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_08MouseDragged(evt);
            }
        });
        da2_dataname_08.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_08MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_08MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_08MouseReleased(evt);
            }
        });
        add(da2_dataname_08);
        da2_dataname_08.setBounds(20, 110, 320, 30);

        da2_dataname_09.setFont(da2_dataname_09.getFont().deriveFont(da2_dataname_09.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_09.getFont().getSize()+6));
        da2_dataname_09.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_09.setText("dataname09");
        da2_dataname_09.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_09MouseDragged(evt);
            }
        });
        da2_dataname_09.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_09MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_09MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_09MouseReleased(evt);
            }
        });
        add(da2_dataname_09);
        da2_dataname_09.setBounds(20, 110, 320, 30);

        da2_dataname_10.setFont(da2_dataname_10.getFont().deriveFont(da2_dataname_10.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_10.getFont().getSize()+6));
        da2_dataname_10.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_10.setText("dataname10");
        da2_dataname_10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_10MouseDragged(evt);
            }
        });
        da2_dataname_10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_10MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_10MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_10MouseReleased(evt);
            }
        });
        add(da2_dataname_10);
        da2_dataname_10.setBounds(20, 110, 320, 30);

        da2_dataname_11.setFont(da2_dataname_11.getFont().deriveFont(da2_dataname_11.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_11.getFont().getSize()+6));
        da2_dataname_11.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_11.setText("dataname11");
        da2_dataname_11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_11MouseDragged(evt);
            }
        });
        da2_dataname_11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_11MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_11MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_11MouseReleased(evt);
            }
        });
        add(da2_dataname_11);
        da2_dataname_11.setBounds(20, 110, 320, 30);

        da2_dataname_12.setFont(da2_dataname_12.getFont().deriveFont(da2_dataname_12.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_12.getFont().getSize()+6));
        da2_dataname_12.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_12.setText("dataname12");
        da2_dataname_12.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_12MouseDragged(evt);
            }
        });
        da2_dataname_12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_12MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_12MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_12MouseReleased(evt);
            }
        });
        add(da2_dataname_12);
        da2_dataname_12.setBounds(20, 110, 320, 30);

        da2_dataname_13.setFont(da2_dataname_13.getFont().deriveFont(da2_dataname_13.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_13.getFont().getSize()+6));
        da2_dataname_13.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_13.setText("dataname13");
        da2_dataname_13.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_13MouseDragged(evt);
            }
        });
        da2_dataname_13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_13MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_13MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_13MouseReleased(evt);
            }
        });
        add(da2_dataname_13);
        da2_dataname_13.setBounds(20, 110, 320, 30);

        da2_dataname_14.setFont(da2_dataname_14.getFont().deriveFont(da2_dataname_14.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_14.getFont().getSize()+6));
        da2_dataname_14.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_14.setText("dataname14");
        da2_dataname_14.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_14MouseDragged(evt);
            }
        });
        da2_dataname_14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_14MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_14MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_14MouseReleased(evt);
            }
        });
        add(da2_dataname_14);
        da2_dataname_14.setBounds(20, 110, 320, 30);

        da2_dataname_15.setFont(da2_dataname_15.getFont().deriveFont(da2_dataname_15.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_15.getFont().getSize()+6));
        da2_dataname_15.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_15.setText("dataname15");
        da2_dataname_15.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_15MouseDragged(evt);
            }
        });
        da2_dataname_15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_15MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_15MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_15MouseReleased(evt);
            }
        });
        add(da2_dataname_15);
        da2_dataname_15.setBounds(20, 110, 320, 30);

        da2_dataname_16.setFont(da2_dataname_16.getFont().deriveFont(da2_dataname_16.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_16.getFont().getSize()+6));
        da2_dataname_16.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_16.setText("dataname16");
        da2_dataname_16.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_16MouseDragged(evt);
            }
        });
        da2_dataname_16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_16MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_16MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_16MouseReleased(evt);
            }
        });
        add(da2_dataname_16);
        da2_dataname_16.setBounds(20, 110, 320, 30);

        da2_dataname_17.setFont(da2_dataname_17.getFont().deriveFont(da2_dataname_17.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_17.getFont().getSize()+6));
        da2_dataname_17.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_17.setText("dataname17");
        da2_dataname_17.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_17MouseDragged(evt);
            }
        });
        da2_dataname_17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_17MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_17MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_17MouseReleased(evt);
            }
        });
        add(da2_dataname_17);
        da2_dataname_17.setBounds(20, 110, 320, 30);

        da2_dataname_18.setFont(da2_dataname_18.getFont().deriveFont(da2_dataname_18.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_18.getFont().getSize()+6));
        da2_dataname_18.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_18.setText("dataname18");
        da2_dataname_18.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_18MouseDragged(evt);
            }
        });
        da2_dataname_18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_18MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_18MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_18MouseReleased(evt);
            }
        });
        add(da2_dataname_18);
        da2_dataname_18.setBounds(20, 110, 320, 30);

        da2_dataname_19.setFont(da2_dataname_19.getFont().deriveFont(da2_dataname_19.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_19.getFont().getSize()+6));
        da2_dataname_19.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_19.setText("dataname19");
        da2_dataname_19.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_19MouseDragged(evt);
            }
        });
        da2_dataname_19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_19MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_19MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_19MouseReleased(evt);
            }
        });
        add(da2_dataname_19);
        da2_dataname_19.setBounds(20, 110, 320, 30);

        da2_dataname_20.setFont(da2_dataname_20.getFont().deriveFont(da2_dataname_20.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_20.getFont().getSize()+6));
        da2_dataname_20.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_20.setText("dataname20");
        da2_dataname_20.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_20MouseDragged(evt);
            }
        });
        da2_dataname_20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_20MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_20MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_20MouseReleased(evt);
            }
        });
        add(da2_dataname_20);
        da2_dataname_20.setBounds(20, 110, 320, 30);

        da2_dataname_21.setFont(da2_dataname_21.getFont().deriveFont(da2_dataname_21.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_21.getFont().getSize()+6));
        da2_dataname_21.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_21.setText("dataname21");
        da2_dataname_21.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_21MouseDragged(evt);
            }
        });
        da2_dataname_21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_21MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_21MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_21MouseReleased(evt);
            }
        });
        add(da2_dataname_21);
        da2_dataname_21.setBounds(20, 110, 320, 30);

        da2_dataname_22.setFont(da2_dataname_22.getFont().deriveFont(da2_dataname_22.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_22.getFont().getSize()+6));
        da2_dataname_22.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_22.setText("dataname22");
        da2_dataname_22.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_22MouseDragged(evt);
            }
        });
        da2_dataname_22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_22MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_22MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_22MouseReleased(evt);
            }
        });
        add(da2_dataname_22);
        da2_dataname_22.setBounds(20, 110, 320, 30);

        da2_dataname_23.setFont(da2_dataname_23.getFont().deriveFont(da2_dataname_23.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_23.getFont().getSize()+6));
        da2_dataname_23.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_23.setText("dataname23");
        da2_dataname_23.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_23MouseDragged(evt);
            }
        });
        da2_dataname_23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_23MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_23MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_23MouseReleased(evt);
            }
        });
        add(da2_dataname_23);
        da2_dataname_23.setBounds(20, 110, 320, 30);

        da2_dataname_24.setFont(da2_dataname_24.getFont().deriveFont(da2_dataname_24.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_24.getFont().getSize()+6));
        da2_dataname_24.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_24.setText("dataname24");
        da2_dataname_24.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_24MouseDragged(evt);
            }
        });
        da2_dataname_24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_24MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_24MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_24MouseReleased(evt);
            }
        });
        add(da2_dataname_24);
        da2_dataname_24.setBounds(20, 110, 320, 30);

        da2_dataname_25.setFont(da2_dataname_25.getFont().deriveFont(da2_dataname_25.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_25.getFont().getSize()+6));
        da2_dataname_25.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_25.setText("dataname25");
        da2_dataname_25.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_25MouseDragged(evt);
            }
        });
        da2_dataname_25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_25MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_25MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_25MouseReleased(evt);
            }
        });
        add(da2_dataname_25);
        da2_dataname_25.setBounds(20, 110, 320, 30);

        da2_dataname_26.setFont(da2_dataname_26.getFont().deriveFont(da2_dataname_26.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_26.getFont().getSize()+6));
        da2_dataname_26.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_26.setText("dataname26");
        da2_dataname_26.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_26MouseDragged(evt);
            }
        });
        da2_dataname_26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_26MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_26MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_26MouseReleased(evt);
            }
        });
        add(da2_dataname_26);
        da2_dataname_26.setBounds(20, 110, 320, 30);

        da2_dataname_27.setFont(da2_dataname_27.getFont().deriveFont(da2_dataname_27.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_27.getFont().getSize()+6));
        da2_dataname_27.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_27.setText("dataname27");
        da2_dataname_27.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_27MouseDragged(evt);
            }
        });
        da2_dataname_27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_27MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_27MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_27MouseReleased(evt);
            }
        });
        add(da2_dataname_27);
        da2_dataname_27.setBounds(20, 110, 320, 30);

        da2_dataname_28.setFont(da2_dataname_28.getFont().deriveFont(da2_dataname_28.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_28.getFont().getSize()+6));
        da2_dataname_28.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_28.setText("dataname28");
        da2_dataname_28.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_28MouseDragged(evt);
            }
        });
        da2_dataname_28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_28MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_28MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_28MouseReleased(evt);
            }
        });
        add(da2_dataname_28);
        da2_dataname_28.setBounds(20, 110, 320, 30);

        da2_dataname_29.setFont(da2_dataname_29.getFont().deriveFont(da2_dataname_29.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_29.getFont().getSize()+6));
        da2_dataname_29.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_29.setText("dataname29");
        da2_dataname_29.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_29MouseDragged(evt);
            }
        });
        da2_dataname_29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_29MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_29MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_29MouseReleased(evt);
            }
        });
        add(da2_dataname_29);
        da2_dataname_29.setBounds(20, 110, 320, 30);

        da2_dataname_30.setFont(da2_dataname_30.getFont().deriveFont(da2_dataname_30.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_30.getFont().getSize()+6));
        da2_dataname_30.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_30.setText("dataname30");
        da2_dataname_30.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_30MouseDragged(evt);
            }
        });
        da2_dataname_30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_30MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_30MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_30MouseReleased(evt);
            }
        });
        add(da2_dataname_30);
        da2_dataname_30.setBounds(20, 110, 320, 30);

        da2_dataname_31.setFont(da2_dataname_31.getFont().deriveFont(da2_dataname_31.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_31.getFont().getSize()+6));
        da2_dataname_31.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_31.setText("dataname31");
        da2_dataname_31.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_31MouseDragged(evt);
            }
        });
        da2_dataname_31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_31MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_31MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_31MouseReleased(evt);
            }
        });
        add(da2_dataname_31);
        da2_dataname_31.setBounds(20, 110, 320, 30);

        da2_dataname_32.setFont(da2_dataname_32.getFont().deriveFont(da2_dataname_32.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_32.getFont().getSize()+6));
        da2_dataname_32.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_32.setText("dataname32");
        da2_dataname_32.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_32MouseDragged(evt);
            }
        });
        da2_dataname_32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_32MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_32MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_32MouseReleased(evt);
            }
        });
        add(da2_dataname_32);
        da2_dataname_32.setBounds(20, 110, 320, 30);

        da2_dataname_33.setFont(da2_dataname_33.getFont().deriveFont(da2_dataname_33.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_33.getFont().getSize()+6));
        da2_dataname_33.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_33.setText("dataname33");
        da2_dataname_33.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_33MouseDragged(evt);
            }
        });
        da2_dataname_33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_33MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_33MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_33MouseReleased(evt);
            }
        });
        add(da2_dataname_33);
        da2_dataname_33.setBounds(20, 110, 320, 30);

        da2_dataname_34.setFont(da2_dataname_34.getFont().deriveFont(da2_dataname_34.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_34.getFont().getSize()+6));
        da2_dataname_34.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_34.setText("dataname34");
        da2_dataname_34.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_34MouseDragged(evt);
            }
        });
        da2_dataname_34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_34MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_34MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_34MouseReleased(evt);
            }
        });
        add(da2_dataname_34);
        da2_dataname_34.setBounds(20, 110, 320, 30);

        da2_dataname_35.setFont(da2_dataname_35.getFont().deriveFont(da2_dataname_35.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_35.getFont().getSize()+6));
        da2_dataname_35.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_35.setText("dataname35");
        da2_dataname_35.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_35MouseDragged(evt);
            }
        });
        da2_dataname_35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_35MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_35MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_35MouseReleased(evt);
            }
        });
        add(da2_dataname_35);
        da2_dataname_35.setBounds(20, 110, 320, 30);

        da2_dataname_36.setFont(da2_dataname_36.getFont().deriveFont(da2_dataname_36.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_36.getFont().getSize()+6));
        da2_dataname_36.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_36.setText("dataname36");
        da2_dataname_36.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_36MouseDragged(evt);
            }
        });
        da2_dataname_36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_36MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_36MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_36MouseReleased(evt);
            }
        });
        add(da2_dataname_36);
        da2_dataname_36.setBounds(20, 110, 320, 30);

        da2_dataname_37.setFont(da2_dataname_37.getFont().deriveFont(da2_dataname_37.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_37.getFont().getSize()+6));
        da2_dataname_37.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_37.setText("dataname37");
        da2_dataname_37.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_37MouseDragged(evt);
            }
        });
        da2_dataname_37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_37MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_37MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_37MouseReleased(evt);
            }
        });
        add(da2_dataname_37);
        da2_dataname_37.setBounds(20, 110, 320, 30);

        da2_dataname_38.setFont(da2_dataname_38.getFont().deriveFont(da2_dataname_38.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_38.getFont().getSize()+6));
        da2_dataname_38.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_38.setText("dataname38");
        da2_dataname_38.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_38MouseDragged(evt);
            }
        });
        da2_dataname_38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_38MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_38MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_38MouseReleased(evt);
            }
        });
        add(da2_dataname_38);
        da2_dataname_38.setBounds(20, 110, 320, 30);

        da2_dataname_39.setFont(da2_dataname_39.getFont().deriveFont(da2_dataname_39.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_39.getFont().getSize()+6));
        da2_dataname_39.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_39.setText("dataname39");
        da2_dataname_39.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_39MouseDragged(evt);
            }
        });
        da2_dataname_39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_39MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_39MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_39MouseReleased(evt);
            }
        });
        add(da2_dataname_39);
        da2_dataname_39.setBounds(20, 110, 320, 30);

        da2_dataname_40.setFont(da2_dataname_40.getFont().deriveFont(da2_dataname_40.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_40.getFont().getSize()+6));
        da2_dataname_40.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_40.setText("dataname40");
        da2_dataname_40.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_40MouseDragged(evt);
            }
        });
        da2_dataname_40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_40MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_40MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_40MouseReleased(evt);
            }
        });
        add(da2_dataname_40);
        da2_dataname_40.setBounds(20, 110, 320, 30);

        da2_dataname_41.setFont(da2_dataname_41.getFont().deriveFont(da2_dataname_41.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_41.getFont().getSize()+6));
        da2_dataname_41.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_41.setText("dataname41");
        da2_dataname_41.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_41MouseDragged(evt);
            }
        });
        da2_dataname_41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_41MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_41MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_41MouseReleased(evt);
            }
        });
        add(da2_dataname_41);
        da2_dataname_41.setBounds(20, 110, 320, 30);

        da2_dataname_42.setFont(da2_dataname_42.getFont().deriveFont(da2_dataname_42.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_42.getFont().getSize()+6));
        da2_dataname_42.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_42.setText("dataname42");
        da2_dataname_42.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_42MouseDragged(evt);
            }
        });
        da2_dataname_42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_42MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_42MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_42MouseReleased(evt);
            }
        });
        add(da2_dataname_42);
        da2_dataname_42.setBounds(20, 110, 320, 30);

        da2_dataname_43.setFont(da2_dataname_43.getFont().deriveFont(da2_dataname_43.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_43.getFont().getSize()+6));
        da2_dataname_43.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_43.setText("dataname43");
        da2_dataname_43.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_43MouseDragged(evt);
            }
        });
        da2_dataname_43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_43MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_43MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_43MouseReleased(evt);
            }
        });
        add(da2_dataname_43);
        da2_dataname_43.setBounds(20, 110, 320, 30);

        da2_dataname_44.setFont(da2_dataname_44.getFont().deriveFont(da2_dataname_44.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_44.getFont().getSize()+6));
        da2_dataname_44.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_44.setText("dataname44");
        da2_dataname_44.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_44MouseDragged(evt);
            }
        });
        da2_dataname_44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_44MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_44MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_44MouseReleased(evt);
            }
        });
        add(da2_dataname_44);
        da2_dataname_44.setBounds(20, 110, 320, 30);

        da2_dataname_45.setFont(da2_dataname_45.getFont().deriveFont(da2_dataname_45.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_45.getFont().getSize()+6));
        da2_dataname_45.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_45.setText("dataname45");
        da2_dataname_45.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_45MouseDragged(evt);
            }
        });
        da2_dataname_45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_45MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_45MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_45MouseReleased(evt);
            }
        });
        add(da2_dataname_45);
        da2_dataname_45.setBounds(20, 110, 320, 30);

        da2_dataname_46.setFont(da2_dataname_46.getFont().deriveFont(da2_dataname_46.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_46.getFont().getSize()+6));
        da2_dataname_46.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_46.setText("dataname46");
        da2_dataname_46.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_46MouseDragged(evt);
            }
        });
        da2_dataname_46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_46MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_46MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_46MouseReleased(evt);
            }
        });
        add(da2_dataname_46);
        da2_dataname_46.setBounds(20, 110, 320, 30);

        da2_dataname_47.setFont(da2_dataname_47.getFont().deriveFont(da2_dataname_47.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_47.getFont().getSize()+6));
        da2_dataname_47.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_47.setText("dataname47");
        da2_dataname_47.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_47MouseDragged(evt);
            }
        });
        da2_dataname_47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_47MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_47MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_47MouseReleased(evt);
            }
        });
        add(da2_dataname_47);
        da2_dataname_47.setBounds(20, 110, 320, 30);

        da2_dataname_48.setFont(da2_dataname_48.getFont().deriveFont(da2_dataname_48.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_48.getFont().getSize()+6));
        da2_dataname_48.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_48.setText("dataname48");
        da2_dataname_48.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_dataname_48MouseDragged(evt);
            }
        });
        da2_dataname_48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_48MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_dataname_48MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_dataname_48MouseReleased(evt);
            }
        });
        add(da2_dataname_48);
        da2_dataname_48.setBounds(20, 110, 320, 30);

        da2_datavalue_02.setFont(da2_datavalue_02.getFont().deriveFont(da2_datavalue_02.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_02.getFont().getSize()+36));
        da2_datavalue_02.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_02.setText("10000.8");
        da2_datavalue_02.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_02MouseDragged(evt);
            }
        });
        da2_datavalue_02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_02MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_02MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_02MouseReleased(evt);
            }
        });
        add(da2_datavalue_02);
        da2_datavalue_02.setBounds(60, 150, 300, 60);

        da2_datavalue_03.setFont(da2_datavalue_03.getFont().deriveFont(da2_datavalue_03.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_03.getFont().getSize()+36));
        da2_datavalue_03.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_03.setText("10000.8");
        da2_datavalue_03.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_03MouseDragged(evt);
            }
        });
        da2_datavalue_03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_03MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_03MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_03MouseReleased(evt);
            }
        });
        add(da2_datavalue_03);
        da2_datavalue_03.setBounds(60, 150, 300, 60);

        da2_datavalue_04.setFont(da2_datavalue_04.getFont().deriveFont(da2_datavalue_04.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_04.getFont().getSize()+36));
        da2_datavalue_04.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_04.setText("A:+00.0000 ¢X");
        da2_datavalue_04.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_04MouseDragged(evt);
            }
        });
        da2_datavalue_04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_04MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_04MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_04MouseReleased(evt);
            }
        });
        add(da2_datavalue_04);
        da2_datavalue_04.setBounds(60, 150, 300, 60);

        da2_datavalue_05.setFont(da2_datavalue_05.getFont().deriveFont(da2_datavalue_05.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_05.getFont().getSize()+36));
        da2_datavalue_05.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_05.setText("A:+00.0000 ¢X");
        da2_datavalue_05.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_05MouseDragged(evt);
            }
        });
        da2_datavalue_05.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_05MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_05MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_05MouseReleased(evt);
            }
        });
        add(da2_datavalue_05);
        da2_datavalue_05.setBounds(60, 150, 300, 60);

        da2_datavalue_06.setFont(da2_datavalue_06.getFont().deriveFont(da2_datavalue_06.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_06.getFont().getSize()+36));
        da2_datavalue_06.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_06.setText("A:+00.0000 ¢X");
        da2_datavalue_06.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_06MouseDragged(evt);
            }
        });
        da2_datavalue_06.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_06MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_06MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_06MouseReleased(evt);
            }
        });
        add(da2_datavalue_06);
        da2_datavalue_06.setBounds(60, 150, 300, 60);

        da2_datavalue_07.setFont(da2_datavalue_07.getFont().deriveFont(da2_datavalue_07.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_07.getFont().getSize()+36));
        da2_datavalue_07.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_07.setText("A:+00.0000 ¢X");
        da2_datavalue_07.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_07MouseDragged(evt);
            }
        });
        da2_datavalue_07.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_07MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_07MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_07MouseReleased(evt);
            }
        });
        add(da2_datavalue_07);
        da2_datavalue_07.setBounds(60, 150, 300, 60);

        da2_datavalue_08.setFont(da2_datavalue_08.getFont().deriveFont(da2_datavalue_08.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_08.getFont().getSize()+36));
        da2_datavalue_08.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_08.setText("A:+00.0000 ¢X");
        da2_datavalue_08.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_08MouseDragged(evt);
            }
        });
        da2_datavalue_08.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_08MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_08MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_08MouseReleased(evt);
            }
        });
        add(da2_datavalue_08);
        da2_datavalue_08.setBounds(60, 150, 300, 60);

        da2_datavalue_09.setFont(da2_datavalue_09.getFont().deriveFont(da2_datavalue_09.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_09.getFont().getSize()+36));
        da2_datavalue_09.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_09.setText("A:+00.0000 ¢X");
        da2_datavalue_09.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_09MouseDragged(evt);
            }
        });
        da2_datavalue_09.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_09MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_09MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_09MouseReleased(evt);
            }
        });
        add(da2_datavalue_09);
        da2_datavalue_09.setBounds(60, 150, 300, 60);

        da2_datavalue_10.setFont(da2_datavalue_10.getFont().deriveFont(da2_datavalue_10.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_10.getFont().getSize()+36));
        da2_datavalue_10.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_10.setText("A:+00.0000 ¢X");
        da2_datavalue_10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_10MouseDragged(evt);
            }
        });
        da2_datavalue_10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_10MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_10MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_10MouseReleased(evt);
            }
        });
        add(da2_datavalue_10);
        da2_datavalue_10.setBounds(60, 150, 300, 60);

        da2_datavalue_11.setFont(da2_datavalue_11.getFont().deriveFont(da2_datavalue_11.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_11.getFont().getSize()+36));
        da2_datavalue_11.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_11.setText("A:+00.0000 ¢X");
        da2_datavalue_11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_11MouseDragged(evt);
            }
        });
        da2_datavalue_11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_11MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_11MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_11MouseReleased(evt);
            }
        });
        add(da2_datavalue_11);
        da2_datavalue_11.setBounds(60, 150, 300, 60);

        da2_datavalue_12.setFont(da2_datavalue_12.getFont().deriveFont(da2_datavalue_12.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_12.getFont().getSize()+36));
        da2_datavalue_12.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_12.setText("A:+00.0000 ¢X");
        da2_datavalue_12.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_12MouseDragged(evt);
            }
        });
        da2_datavalue_12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_12MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_12MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_12MouseReleased(evt);
            }
        });
        add(da2_datavalue_12);
        da2_datavalue_12.setBounds(60, 150, 300, 60);

        da2_datavalue_13.setFont(da2_datavalue_13.getFont().deriveFont(da2_datavalue_13.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_13.getFont().getSize()+36));
        da2_datavalue_13.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_13.setText("A:+00.0000 ¢X");
        da2_datavalue_13.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_13MouseDragged(evt);
            }
        });
        da2_datavalue_13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_13MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_13MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_13MouseReleased(evt);
            }
        });
        add(da2_datavalue_13);
        da2_datavalue_13.setBounds(60, 150, 300, 60);

        da2_datavalue_14.setFont(da2_datavalue_14.getFont().deriveFont(da2_datavalue_14.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_14.getFont().getSize()+36));
        da2_datavalue_14.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_14.setText("A:+00.0000 ¢X");
        da2_datavalue_14.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_14MouseDragged(evt);
            }
        });
        da2_datavalue_14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_14MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_14MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_14MouseReleased(evt);
            }
        });
        add(da2_datavalue_14);
        da2_datavalue_14.setBounds(60, 150, 300, 60);

        da2_datavalue_15.setFont(da2_datavalue_15.getFont().deriveFont(da2_datavalue_15.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_15.getFont().getSize()+36));
        da2_datavalue_15.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_15.setText("A:+00.0000 ¢X");
        da2_datavalue_15.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_15MouseDragged(evt);
            }
        });
        da2_datavalue_15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_15MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_15MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_15MouseReleased(evt);
            }
        });
        add(da2_datavalue_15);
        da2_datavalue_15.setBounds(60, 150, 300, 60);

        da2_datavalue_16.setFont(da2_datavalue_16.getFont().deriveFont(da2_datavalue_16.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_16.getFont().getSize()+36));
        da2_datavalue_16.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_16.setText("A:+00.0000 ¢X");
        da2_datavalue_16.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_16MouseDragged(evt);
            }
        });
        da2_datavalue_16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_16MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_16MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_16MouseReleased(evt);
            }
        });
        add(da2_datavalue_16);
        da2_datavalue_16.setBounds(60, 150, 300, 60);

        da2_datavalue_17.setFont(da2_datavalue_17.getFont().deriveFont(da2_datavalue_17.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_17.getFont().getSize()+36));
        da2_datavalue_17.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_17.setText("A:+00.0000 ¢X");
        da2_datavalue_17.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_17MouseDragged(evt);
            }
        });
        da2_datavalue_17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_17MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_17MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_17MouseReleased(evt);
            }
        });
        add(da2_datavalue_17);
        da2_datavalue_17.setBounds(60, 150, 300, 60);

        da2_datavalue_18.setFont(da2_datavalue_18.getFont().deriveFont(da2_datavalue_18.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_18.getFont().getSize()+36));
        da2_datavalue_18.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_18.setText("A:+00.0000 ¢X");
        da2_datavalue_18.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_18MouseDragged(evt);
            }
        });
        da2_datavalue_18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_18MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_18MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_18MouseReleased(evt);
            }
        });
        add(da2_datavalue_18);
        da2_datavalue_18.setBounds(60, 150, 300, 60);

        da2_datavalue_19.setFont(da2_datavalue_19.getFont().deriveFont(da2_datavalue_19.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_19.getFont().getSize()+36));
        da2_datavalue_19.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_19.setText("A:+00.0000 ¢X");
        da2_datavalue_19.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_19MouseDragged(evt);
            }
        });
        da2_datavalue_19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_19MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_19MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_19MouseReleased(evt);
            }
        });
        add(da2_datavalue_19);
        da2_datavalue_19.setBounds(60, 150, 300, 60);

        da2_datavalue_20.setFont(da2_datavalue_20.getFont().deriveFont(da2_datavalue_20.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_20.getFont().getSize()+36));
        da2_datavalue_20.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_20.setText("A:+00.0000 ¢X");
        da2_datavalue_20.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_20MouseDragged(evt);
            }
        });
        da2_datavalue_20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_20MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_20MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_20MouseReleased(evt);
            }
        });
        add(da2_datavalue_20);
        da2_datavalue_20.setBounds(60, 150, 300, 60);

        da2_datavalue_21.setFont(da2_datavalue_21.getFont().deriveFont(da2_datavalue_21.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_21.getFont().getSize()+36));
        da2_datavalue_21.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_21.setText("A:+00.0000 ¢X");
        da2_datavalue_21.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_21MouseDragged(evt);
            }
        });
        da2_datavalue_21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_21MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_21MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_21MouseReleased(evt);
            }
        });
        add(da2_datavalue_21);
        da2_datavalue_21.setBounds(60, 150, 300, 60);

        da2_datavalue_22.setFont(da2_datavalue_22.getFont().deriveFont(da2_datavalue_22.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_22.getFont().getSize()+36));
        da2_datavalue_22.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_22.setText("A:+00.0000 ¢X");
        da2_datavalue_22.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_22MouseDragged(evt);
            }
        });
        da2_datavalue_22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_22MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_22MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_22MouseReleased(evt);
            }
        });
        add(da2_datavalue_22);
        da2_datavalue_22.setBounds(60, 150, 300, 60);

        da2_datavalue_23.setFont(da2_datavalue_23.getFont().deriveFont(da2_datavalue_23.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_23.getFont().getSize()+36));
        da2_datavalue_23.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_23.setText("A:+00.0000 ¢X");
        da2_datavalue_23.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_23MouseDragged(evt);
            }
        });
        da2_datavalue_23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_23MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_23MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_23MouseReleased(evt);
            }
        });
        add(da2_datavalue_23);
        da2_datavalue_23.setBounds(60, 150, 300, 60);

        da2_datavalue_24.setFont(da2_datavalue_24.getFont().deriveFont(da2_datavalue_24.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_24.getFont().getSize()+36));
        da2_datavalue_24.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_24.setText("A:+00.0000 ¢X");
        da2_datavalue_24.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_24MouseDragged(evt);
            }
        });
        da2_datavalue_24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_24MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_24MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_24MouseReleased(evt);
            }
        });
        add(da2_datavalue_24);
        da2_datavalue_24.setBounds(60, 150, 300, 60);

        da2_datavalue_25.setFont(da2_datavalue_25.getFont().deriveFont(da2_datavalue_25.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_25.getFont().getSize()+36));
        da2_datavalue_25.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_25.setText("A:+00.0000 ¢X");
        da2_datavalue_25.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_25MouseDragged(evt);
            }
        });
        da2_datavalue_25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_25MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_25MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_25MouseReleased(evt);
            }
        });
        add(da2_datavalue_25);
        da2_datavalue_25.setBounds(60, 150, 300, 60);

        da2_datavalue_26.setFont(da2_datavalue_26.getFont().deriveFont(da2_datavalue_26.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_26.getFont().getSize()+36));
        da2_datavalue_26.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_26.setText("A:+00.0000 ¢X");
        da2_datavalue_26.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_26MouseDragged(evt);
            }
        });
        da2_datavalue_26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_26MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_26MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_26MouseReleased(evt);
            }
        });
        add(da2_datavalue_26);
        da2_datavalue_26.setBounds(60, 150, 300, 60);

        da2_datavalue_27.setFont(da2_datavalue_27.getFont().deriveFont(da2_datavalue_27.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_27.getFont().getSize()+36));
        da2_datavalue_27.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_27.setText("A:+00.0000 ¢X");
        da2_datavalue_27.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_27MouseDragged(evt);
            }
        });
        da2_datavalue_27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_27MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_27MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_27MouseReleased(evt);
            }
        });
        add(da2_datavalue_27);
        da2_datavalue_27.setBounds(60, 150, 300, 60);

        da2_datavalue_28.setFont(da2_datavalue_28.getFont().deriveFont(da2_datavalue_28.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_28.getFont().getSize()+36));
        da2_datavalue_28.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_28.setText("A:+00.0000 ¢X");
        da2_datavalue_28.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_28MouseDragged(evt);
            }
        });
        da2_datavalue_28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_28MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_28MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_28MouseReleased(evt);
            }
        });
        add(da2_datavalue_28);
        da2_datavalue_28.setBounds(60, 150, 300, 60);

        da2_datavalue_29.setFont(da2_datavalue_29.getFont().deriveFont(da2_datavalue_29.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_29.getFont().getSize()+36));
        da2_datavalue_29.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_29.setText("A:+00.0000 ¢X");
        da2_datavalue_29.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_29MouseDragged(evt);
            }
        });
        da2_datavalue_29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_29MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_29MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_29MouseReleased(evt);
            }
        });
        add(da2_datavalue_29);
        da2_datavalue_29.setBounds(60, 150, 300, 60);

        da2_datavalue_30.setFont(da2_datavalue_30.getFont().deriveFont(da2_datavalue_30.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_30.getFont().getSize()+36));
        da2_datavalue_30.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_30.setText("A:+00.0000 ¢X");
        da2_datavalue_30.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_30MouseDragged(evt);
            }
        });
        da2_datavalue_30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_30MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_30MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_30MouseReleased(evt);
            }
        });
        add(da2_datavalue_30);
        da2_datavalue_30.setBounds(60, 150, 300, 60);

        da2_datavalue_31.setFont(da2_datavalue_31.getFont().deriveFont(da2_datavalue_31.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_31.getFont().getSize()+36));
        da2_datavalue_31.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_31.setText("A:+00.0000 ¢X");
        da2_datavalue_31.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_31MouseDragged(evt);
            }
        });
        da2_datavalue_31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_31MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_31MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_31MouseReleased(evt);
            }
        });
        add(da2_datavalue_31);
        da2_datavalue_31.setBounds(60, 150, 300, 60);

        da2_datavalue_32.setFont(da2_datavalue_32.getFont().deriveFont(da2_datavalue_32.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_32.getFont().getSize()+36));
        da2_datavalue_32.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_32.setText("A:+00.0000 ¢X");
        da2_datavalue_32.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_32MouseDragged(evt);
            }
        });
        da2_datavalue_32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_32MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_32MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_32MouseReleased(evt);
            }
        });
        add(da2_datavalue_32);
        da2_datavalue_32.setBounds(60, 150, 300, 60);

        da2_datavalue_33.setFont(da2_datavalue_33.getFont().deriveFont(da2_datavalue_33.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_33.getFont().getSize()+36));
        da2_datavalue_33.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_33.setText("A:+00.0000 ¢X");
        da2_datavalue_33.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_33MouseDragged(evt);
            }
        });
        da2_datavalue_33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_33MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_33MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_33MouseReleased(evt);
            }
        });
        add(da2_datavalue_33);
        da2_datavalue_33.setBounds(60, 150, 300, 60);

        da2_datavalue_34.setFont(da2_datavalue_34.getFont().deriveFont(da2_datavalue_34.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_34.getFont().getSize()+36));
        da2_datavalue_34.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_34.setText("A:+00.0000 ¢X");
        da2_datavalue_34.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_34MouseDragged(evt);
            }
        });
        da2_datavalue_34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_34MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_34MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_34MouseReleased(evt);
            }
        });
        add(da2_datavalue_34);
        da2_datavalue_34.setBounds(60, 150, 300, 60);

        da2_datavalue_35.setFont(da2_datavalue_35.getFont().deriveFont(da2_datavalue_35.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_35.getFont().getSize()+36));
        da2_datavalue_35.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_35.setText("A:+00.0000 ¢X");
        da2_datavalue_35.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_35MouseDragged(evt);
            }
        });
        da2_datavalue_35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_35MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_35MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_35MouseReleased(evt);
            }
        });
        add(da2_datavalue_35);
        da2_datavalue_35.setBounds(60, 150, 300, 60);

        da2_datavalue_36.setFont(da2_datavalue_36.getFont().deriveFont(da2_datavalue_36.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_36.getFont().getSize()+36));
        da2_datavalue_36.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_36.setText("A:+00.0000 ¢X");
        da2_datavalue_36.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_36MouseDragged(evt);
            }
        });
        da2_datavalue_36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_36MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_36MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_36MouseReleased(evt);
            }
        });
        add(da2_datavalue_36);
        da2_datavalue_36.setBounds(60, 150, 300, 60);

        da2_datavalue_37.setFont(da2_datavalue_37.getFont().deriveFont(da2_datavalue_37.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_37.getFont().getSize()+36));
        da2_datavalue_37.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_37.setText("A:+00.0000 ¢X");
        da2_datavalue_37.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_37MouseDragged(evt);
            }
        });
        da2_datavalue_37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_37MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_37MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_37MouseReleased(evt);
            }
        });
        add(da2_datavalue_37);
        da2_datavalue_37.setBounds(60, 150, 300, 60);

        da2_datavalue_38.setFont(da2_datavalue_38.getFont().deriveFont(da2_datavalue_38.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_38.getFont().getSize()+36));
        da2_datavalue_38.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_38.setText("A:+00.0000 ¢X");
        da2_datavalue_38.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_38MouseDragged(evt);
            }
        });
        da2_datavalue_38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_38MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_38MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_38MouseReleased(evt);
            }
        });
        add(da2_datavalue_38);
        da2_datavalue_38.setBounds(60, 150, 300, 60);

        da2_datavalue_39.setFont(da2_datavalue_39.getFont().deriveFont(da2_datavalue_39.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_39.getFont().getSize()+36));
        da2_datavalue_39.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_39.setText("A:+00.0000 ¢X");
        da2_datavalue_39.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_39MouseDragged(evt);
            }
        });
        da2_datavalue_39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_39MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_39MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_39MouseReleased(evt);
            }
        });
        add(da2_datavalue_39);
        da2_datavalue_39.setBounds(60, 150, 300, 60);

        da2_datavalue_40.setFont(da2_datavalue_40.getFont().deriveFont(da2_datavalue_40.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_40.getFont().getSize()+36));
        da2_datavalue_40.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_40.setText("A:+00.0000 ¢X");
        da2_datavalue_40.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_40MouseDragged(evt);
            }
        });
        da2_datavalue_40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_40MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_40MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_40MouseReleased(evt);
            }
        });
        add(da2_datavalue_40);
        da2_datavalue_40.setBounds(60, 150, 300, 60);

        da2_datavalue_41.setFont(da2_datavalue_41.getFont().deriveFont(da2_datavalue_41.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_41.getFont().getSize()+36));
        da2_datavalue_41.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_41.setText("A:+00.0000 ¢X");
        da2_datavalue_41.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_41MouseDragged(evt);
            }
        });
        da2_datavalue_41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_41MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_41MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_41MouseReleased(evt);
            }
        });
        add(da2_datavalue_41);
        da2_datavalue_41.setBounds(60, 150, 300, 60);

        da2_datavalue_42.setFont(da2_datavalue_42.getFont().deriveFont(da2_datavalue_42.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_42.getFont().getSize()+36));
        da2_datavalue_42.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_42.setText("A:+00.0000 ¢X");
        da2_datavalue_42.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_42MouseDragged(evt);
            }
        });
        da2_datavalue_42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_42MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_42MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_42MouseReleased(evt);
            }
        });
        add(da2_datavalue_42);
        da2_datavalue_42.setBounds(60, 150, 300, 60);

        da2_datavalue_43.setFont(da2_datavalue_43.getFont().deriveFont(da2_datavalue_43.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_43.getFont().getSize()+36));
        da2_datavalue_43.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_43.setText("A:+00.0000 ¢X");
        da2_datavalue_43.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_43MouseDragged(evt);
            }
        });
        da2_datavalue_43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_43MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_43MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_43MouseReleased(evt);
            }
        });
        add(da2_datavalue_43);
        da2_datavalue_43.setBounds(60, 150, 300, 60);

        da2_datavalue_44.setFont(da2_datavalue_44.getFont().deriveFont(da2_datavalue_44.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_44.getFont().getSize()+36));
        da2_datavalue_44.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_44.setText("A:+00.0000 ¢X");
        da2_datavalue_44.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_44MouseDragged(evt);
            }
        });
        da2_datavalue_44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_44MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_44MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_44MouseReleased(evt);
            }
        });
        add(da2_datavalue_44);
        da2_datavalue_44.setBounds(60, 150, 300, 60);

        da2_datavalue_45.setFont(da2_datavalue_45.getFont().deriveFont(da2_datavalue_45.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_45.getFont().getSize()+36));
        da2_datavalue_45.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_45.setText("A:+00.0000 ¢X");
        da2_datavalue_45.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_45MouseDragged(evt);
            }
        });
        da2_datavalue_45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_45MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_45MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_45MouseReleased(evt);
            }
        });
        add(da2_datavalue_45);
        da2_datavalue_45.setBounds(60, 150, 300, 60);

        da2_datavalue_46.setFont(da2_datavalue_46.getFont().deriveFont(da2_datavalue_46.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_46.getFont().getSize()+36));
        da2_datavalue_46.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_46.setText("A:+00.0000 ¢X");
        da2_datavalue_46.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_46MouseDragged(evt);
            }
        });
        da2_datavalue_46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_46MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_46MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_46MouseReleased(evt);
            }
        });
        add(da2_datavalue_46);
        da2_datavalue_46.setBounds(60, 150, 300, 60);

        da2_datavalue_47.setFont(da2_datavalue_47.getFont().deriveFont(da2_datavalue_47.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_47.getFont().getSize()+36));
        da2_datavalue_47.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_47.setText("A:+00.0000 ¢X");
        da2_datavalue_47.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_47MouseDragged(evt);
            }
        });
        da2_datavalue_47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_47MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_47MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_47MouseReleased(evt);
            }
        });
        add(da2_datavalue_47);
        da2_datavalue_47.setBounds(60, 150, 300, 60);

        da2_datavalue_48.setFont(da2_datavalue_48.getFont().deriveFont(da2_datavalue_48.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_48.getFont().getSize()+36));
        da2_datavalue_48.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_48.setText("A:+00.0000 ¢X");
        da2_datavalue_48.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_datavalue_48MouseDragged(evt);
            }
        });
        da2_datavalue_48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_48MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_datavalue_48MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_datavalue_48MouseReleased(evt);
            }
        });
        add(da2_datavalue_48);
        da2_datavalue_48.setBounds(60, 150, 300, 60);

        da2_xlabel_01.setFont(da2_xlabel_01.getFont());
        da2_xlabel_01.setText("textlabel01");
        da2_xlabel_01.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_xlabel_01MouseDragged(evt);
            }
        });
        da2_xlabel_01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_xlabel_01MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_xlabel_01MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_xlabel_01MouseReleased(evt);
            }
        });
        add(da2_xlabel_01);
        da2_xlabel_01.setBounds(80, 240, 170, 20);

        da2_xlabel_02.setFont(da2_xlabel_02.getFont());
        da2_xlabel_02.setText("textlabel02");
        da2_xlabel_02.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_xlabel_02MouseDragged(evt);
            }
        });
        da2_xlabel_02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_xlabel_02MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_xlabel_02MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_xlabel_02MouseReleased(evt);
            }
        });
        add(da2_xlabel_02);
        da2_xlabel_02.setBounds(80, 240, 170, 20);

        da2_xlabel_03.setFont(da2_xlabel_03.getFont());
        da2_xlabel_03.setText("jLabel1");
        da2_xlabel_03.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_xlabel_03MouseDragged(evt);
            }
        });
        da2_xlabel_03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_xlabel_03MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_xlabel_03MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_xlabel_03MouseReleased(evt);
            }
        });
        add(da2_xlabel_03);
        da2_xlabel_03.setBounds(80, 240, 170, 20);

        da2_xlabel_04.setFont(da2_xlabel_04.getFont());
        da2_xlabel_04.setText("jLabel1");
        da2_xlabel_04.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_xlabel_04MouseDragged(evt);
            }
        });
        da2_xlabel_04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_xlabel_04MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_xlabel_04MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_xlabel_04MouseReleased(evt);
            }
        });
        add(da2_xlabel_04);
        da2_xlabel_04.setBounds(80, 240, 170, 20);

        da2_xlabel_05.setFont(da2_xlabel_05.getFont());
        da2_xlabel_05.setText("jLabel1");
        da2_xlabel_05.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_xlabel_05MouseDragged(evt);
            }
        });
        da2_xlabel_05.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_xlabel_05MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_xlabel_05MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_xlabel_05MouseReleased(evt);
            }
        });
        add(da2_xlabel_05);
        da2_xlabel_05.setBounds(80, 240, 170, 20);

        da2_xlabel_06.setFont(da2_xlabel_06.getFont());
        da2_xlabel_06.setText("jLabel1");
        da2_xlabel_06.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_xlabel_06MouseDragged(evt);
            }
        });
        da2_xlabel_06.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_xlabel_06MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_xlabel_06MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_xlabel_06MouseReleased(evt);
            }
        });
        add(da2_xlabel_06);
        da2_xlabel_06.setBounds(80, 240, 170, 20);

        da2_xlabel_07.setFont(da2_xlabel_07.getFont());
        da2_xlabel_07.setText("jLabel1");
        da2_xlabel_07.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_xlabel_07MouseDragged(evt);
            }
        });
        da2_xlabel_07.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_xlabel_07MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_xlabel_07MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_xlabel_07MouseReleased(evt);
            }
        });
        add(da2_xlabel_07);
        da2_xlabel_07.setBounds(80, 240, 170, 20);

        da2_xlabel_08.setFont(da2_xlabel_08.getFont());
        da2_xlabel_08.setText("jLabel1");
        da2_xlabel_08.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_xlabel_08MouseDragged(evt);
            }
        });
        da2_xlabel_08.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_xlabel_08MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_xlabel_08MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_xlabel_08MouseReleased(evt);
            }
        });
        add(da2_xlabel_08);
        da2_xlabel_08.setBounds(80, 240, 170, 20);

        da2_xlabel_09.setFont(da2_xlabel_09.getFont());
        da2_xlabel_09.setText("jLabel1");
        da2_xlabel_09.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_xlabel_09MouseDragged(evt);
            }
        });
        da2_xlabel_09.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_xlabel_09MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_xlabel_09MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_xlabel_09MouseReleased(evt);
            }
        });
        add(da2_xlabel_09);
        da2_xlabel_09.setBounds(80, 240, 170, 20);

        da2_xlabel_10.setFont(da2_xlabel_10.getFont());
        da2_xlabel_10.setText("jLabel1");
        da2_xlabel_10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                da2_xlabel_10MouseDragged(evt);
            }
        });
        da2_xlabel_10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_xlabel_10MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                da2_xlabel_10MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                da2_xlabel_10MouseReleased(evt);
            }
        });
        add(da2_xlabel_10);
        da2_xlabel_10.setBounds(80, 240, 170, 20);
    }

    private void da2_datavalue_01MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_02MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_03MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_04MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_05MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_06MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_07MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_08MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_09MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_10MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_11MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_12MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_13MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_14MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_15MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_16MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_17MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_18MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_19MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_20MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_21MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_22MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_23MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_24MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_25MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 25");
    }

    private void da2_datavalue_26MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 26");
    }

    private void da2_datavalue_27MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_28MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_29MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_30MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_31MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_32MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_33MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_34MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_35MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_36MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_37MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_38MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_39MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_40MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_41MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_42MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_43MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_44MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_45MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_46MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_47MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_48MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_01MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_01MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_station_01MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_station_02MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_station_03MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_station_04MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_station_05MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_station_06MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_station_07MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_station_08MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_station_09MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_station_10MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_station_11MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_station_12MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_station_13MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_station_14MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_station_15MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_station_16MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_02MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_03MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_04MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_05MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_06MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_07MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_08MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_09MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_10MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_11MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_12MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_13MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_14MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_15MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_16MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_17MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_18MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_19MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_20MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_21MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_22MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_23MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_24MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_25MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_26MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_27MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_28MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_29MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_30MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_31MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_device_32MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_02MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_03MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_04MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_05MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_06MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_07MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_08MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_09MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_10MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_11MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_12MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_13MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_14MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_15MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_16MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_17MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_18MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_19MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_20MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_21MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_22MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_23MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_24MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_25MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_26MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_27MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_28MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_29MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_30MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_31MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_32MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_33MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_34MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_35MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_36MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_37MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_38MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_39MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_40MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_41MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_42MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_43MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_44MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_45MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_46MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_47MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_dataname_48MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void formMouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("");
       instrument.selectedDataItem="";
    }

    private void da2_xlabel_01MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_xlabel_02MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_xlabel_03MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_xlabel_04MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_xlabel_05MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_xlabel_06MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_xlabel_07MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_xlabel_08MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_xlabel_09MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_xlabel_10MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_01MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_01,"da_datavalue 01");
    }

    private void da2_datavalue_01MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_01MouseDragged(java.awt.event.MouseEvent evt) {
       mouseDragged(evt,"da_datavalue 01");
    }

    private void da2_device_01MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_01,"da_device 01");
    }

    private void da2_dataname_01MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_01,"da_dataname 01");
    }

    private void da2_station_01MousePressed(java.awt.event.MouseEvent evt) {
       mousePressed(evt,da2_station_01,"da_station 01");
    }

    private void da2_station_02MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_station_02,"da_station 02");
    }

    private void da2_station_03MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_station_03,"da_station 03");
    }

    private void da2_station_04MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_station_04,"da_station 04");
    }

    private void da2_station_05MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_station_05,"da_station 05");
    }

    private void da2_station_06MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_station_06,"da_station 06");
    }

    private void da2_station_07MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_station_07,"da_station 07");
    }

    private void da2_station_08MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_station_08,"da_station 08");
    }

    private void da2_station_09MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_station_09,"da_station 09");
    }

    private void da2_station_10MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_station_10,"da_station 10");
    }

    private void da2_station_11MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_station_11,"da_station 11");
    }

    private void da2_station_12MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_station_12,"da_station 12");
    }

    private void da2_station_13MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_station_13,"da_station 13");
    }

    private void da2_station_14MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_station_14,"da_station 14");
    }

    private void da2_station_15MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_station_15,"da_station 15");
    }

    private void da2_station_16MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_station_16,"da_station 16");
    }

    private void da2_device_02MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_02,"da_device 02");
    }

    private void da2_device_03MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_03,"da_device 03");
    }

    private void da2_device_04MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_04,"da_device 04");
    }

    private void da2_device_05MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_05,"da_device 05");
    }

    private void da2_device_06MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_06,"da_device 06");
    }

    private void da2_device_07MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_07,"da_device 07");
    }

    private void da2_device_08MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_08,"da_device 08");
    }

    private void da2_device_09MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_09,"da_device 09");
    }

    private void da2_device_10MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_10,"da_device 10");
    }

    private void da2_device_11MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_11,"da_device 11");
    }

    private void da2_device_12MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_12,"da_device 12");
    }

    private void da2_device_13MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_13,"da_device 13");
    }

    private void da2_device_14MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_14,"da_device 14");
    }

    private void da2_device_15MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_15,"da_device 15");
    }

    private void da2_device_16MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_16,"da_device 16");
    }

    private void da2_device_17MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_17,"da_device 17");
    }

    private void da2_device_18MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_18,"da_device 18");
    }

    private void da2_device_19MousePressed(java.awt.event.MouseEvent evt) {
       mousePressed(evt,da2_device_19,"da_device 19");
    }

    private void da2_device_20MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_20,"da_device 20");
    }

    private void da2_device_21MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_21,"da_device 21");
    }

    private void da2_device_22MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_22,"da_device 22");
    }

    private void da2_device_23MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_23,"da_device 23");
    }

    private void da2_device_24MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_24,"da_device 24");
    }

    private void da2_device_25MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_25,"da_device 25");
    }

    private void da2_device_26MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_26,"da_device 26");
    }

    private void da2_device_27MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_27,"da_device 27");
    }

    private void da2_device_28MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_28,"da_device 28");
    }

    private void da2_device_29MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_29,"da_device 29");
    }

    private void da2_device_30MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_30,"da_device 30");
    }

    private void da2_device_31MousePressed(java.awt.event.MouseEvent evt) {
       mousePressed(evt,da2_device_31,"da_device 31");
    }

    private void da2_device_32MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_device_32,"da_device 32");
    }

    private void da2_dataname_02MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_02,"da_dataname 02");
    }

    private void da2_dataname_03MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_03,"da_dataname 03");
    }

    private void da2_dataname_04MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_04,"da_dataname 04");
    }

    private void da2_dataname_05MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_05,"da_dataname 05");
    }

    private void da2_dataname_06MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_06,"da_dataname 06");
    }

    private void da2_dataname_07MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_07,"da_dataname 07");
    }

    private void da2_dataname_08MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_08,"da_dataname 08");
    }

    private void da2_dataname_09MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_09,"da_dataname 09");
    }

    private void da2_dataname_10MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_10,"da_dataname 10");
    }

    private void da2_dataname_11MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_11,"da_dataname 11");
    }

    private void da2_dataname_12MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_12,"da_dataname 12");
    }

    private void da2_dataname_13MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_13,"da_dataname 13");
    }

    private void da2_dataname_14MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_14,"da_dataname 14");
    }

    private void da2_dataname_15MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_15,"da_dataname 15");
    }

    private void da2_dataname_16MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_16,"da_dataname 16");
    }

    private void da2_dataname_17MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_17,"da_dataname 17");
    }

    private void da2_dataname_18MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_18,"da_dataname 18");
    }

    private void da2_dataname_19MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_19,"da_dataname 19");
    }

    private void da2_dataname_20MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_20,"da_dataname 20");
    }

    private void da2_dataname_21MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_21,"da_dataname 21");
    }

    private void da2_dataname_22MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_22,"da_dataname 22");
    }

    private void da2_dataname_23MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_23,"da_dataname 23");
    }

    private void da2_dataname_24MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_24,"da_dataname 24");
    }

    private void da2_dataname_25MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_25,"da_dataname 25");
    }

    private void da2_dataname_26MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_26,"da_dataname 26");
    }

    private void da2_dataname_27MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_27,"da_dataname 27");
    }

    private void da2_dataname_28MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_28,"da_dataname 28");
    }

    private void da2_dataname_29MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_29,"da_dataname 29");
    }

    private void da2_dataname_30MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_30,"da_dataname 30");
    }

    private void da2_dataname_31MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_31,"da_dataname 31");
    }

    private void da2_dataname_32MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_32,"da_dataname 32");
    }

    private void da2_dataname_33MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_33,"da_dataname 33");
    }

    private void da2_dataname_34MousePressed(java.awt.event.MouseEvent evt) {
       mousePressed(evt,da2_dataname_34,"da_dataname 34");
    }

    private void da2_dataname_35MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_35,"da_dataname 35");
    }

    private void da2_dataname_36MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_36,"da_dataname 36");
    }

    private void da2_dataname_37MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_37,"da_dataname 37");
    }

    private void da2_dataname_38MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_38,"da_dataname 38");
    }

    private void da2_dataname_39MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_39,"da_dataname 39");
    }

    private void da2_dataname_40MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_40,"da_dataname 40");
    }

    private void da2_dataname_41MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_41,"da_dataname 41");
    }

    private void da2_dataname_42MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_42,"da_dataname 42");
    }

    private void da2_dataname_43MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_43,"da_dataname 43");
    }

    private void da2_dataname_44MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_44,"da_dataname 44");
    }

    private void da2_dataname_45MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_45,"da_dataname 45");
    }

    private void da2_dataname_46MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_46,"da_dataname 46");
    }

    private void da2_dataname_47MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_47,"da_dataname 47");
    }

    private void da2_dataname_48MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_dataname_48,"da_dataname 48");
    }

    private void da2_datavalue_02MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_02,"da_datavalue 02");
    }

    private void da2_datavalue_03MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_03,"da_datavalue 03");
    }

    private void da2_datavalue_04MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_04,"da_datavalue 04");
    }

    private void da2_datavalue_05MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_05,"da_datavalue 05");
    }

    private void da2_datavalue_06MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_06,"da_datavalue 06");
    }

    private void da2_datavalue_07MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_07,"da_datavalue 07");
    }

    private void da2_datavalue_08MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_08,"da_datavalue 08");
    }

    private void da2_datavalue_09MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_09,"da_datavalue 09");
    }

    private void da2_datavalue_10MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_10,"da_datavalue 10");
    }

    private void da2_datavalue_11MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_11,"da_datavalue 11");
    }

    private void da2_datavalue_12MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_12,"da_datavalue 12");
    }

    private void da2_datavalue_13MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_13,"da_datavalue 13");
    }

    private void da2_datavalue_14MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_14,"da_datavalue 14");
    }

    private void da2_datavalue_15MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_15,"da_datavalue 15");
    }

    private void da2_datavalue_16MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_16,"da_datavalue 16");
    }

    private void da2_datavalue_17MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_17,"da_datavalue 17");
    }

    private void da2_datavalue_18MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_18,"da_datavalue 18");
    }

    private void da2_datavalue_19MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_19,"da_datavalue 19");
    }

    private void da2_datavalue_20MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_20,"da_datavalue 20");
    }

    private void da2_datavalue_21MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_21,"da_datavalue 21");
    }

    private void da2_datavalue_22MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_22,"da_datavalue 22");
    }

    private void da2_datavalue_23MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_23,"da_datavalue 23");
    }

    private void da2_datavalue_24MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_24,"da_datavalue 24");
    }

    private void da2_datavalue_25MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_25,"da_datavalue 25");
    }

    private void da2_datavalue_26MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_26,"da_datavalue 26");
    }

    private void da2_datavalue_27MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_27,"da_datavalue 27");
    }

    private void da2_datavalue_28MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_28,"da_datavalue 28");
    }

    private void da2_datavalue_29MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_29,"da_datavalue 29");
    }

    private void da2_datavalue_30MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_30,"da_datavalue 30");
    }

    private void da2_datavalue_31MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_31,"da_datavalue 1");
    }

    private void da2_datavalue_32MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_32,"da_datavalue 32");
    }

    private void da2_datavalue_33MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_33,"da_datavalue 33");
    }

    private void da2_datavalue_34MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_34,"da_datavalue 34");
    }

    private void da2_datavalue_35MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_35,"da_datavalue 35");
    }

    private void da2_datavalue_36MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_36,"da_datavalue 36");
    }

    private void da2_datavalue_37MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_37,"da_datavalue 37");
    }

    private void da2_datavalue_38MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_38,"da_datavalue 38");
    }

    private void da2_datavalue_39MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_39,"da_datavalue 39");
    }

    private void da2_datavalue_40MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_40,"da_datavalue 40");
    }

    private void da2_datavalue_41MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_41,"da_datavalue 41");
    }

    private void da2_datavalue_42MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_42,"da_datavalue 42");
    }

    private void da2_datavalue_43MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_43,"da_datavalue 43");
    }

    private void da2_datavalue_44MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_44,"da_datavalue 44");
    }

    private void da2_datavalue_45MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_45,"da_datavalue 45");
    }

    private void da2_datavalue_46MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_46,"da_datavalue 46");
    }

    private void da2_datavalue_47MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_47,"da_datavalue 47");
    }

    private void da2_datavalue_48MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_datavalue_48,"da_datavalue 48");
    }

    private void da2_xlabel_01MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_xlabel_01,"da_xlabel 01");
    }

    private void da2_xlabel_02MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_xlabel_02,"da_xlabel 02");
    }

    private void da2_xlabel_03MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_xlabel_03,"da_xlabel 03");
    }

    private void da2_xlabel_04MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_xlabel_04,"da_xlabel 04");
    }

    private void da2_xlabel_05MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_xlabel_05,"da_xlabel 05");
    }

    private void da2_xlabel_06MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_xlabel_06,"da_xlabel 06");
    }

    private void da2_xlabel_07MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_xlabel_07,"da_xlabel 07");
    }

    private void da2_xlabel_08MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_xlabel_08,"da_xlabel 08");
    }

    private void da2_xlabel_09MousePressed(java.awt.event.MouseEvent evt) {
        mousePressed(evt,da2_xlabel_09,"da_xlabel 09");
    }

    private void da2_xlabel_10MousePressed(java.awt.event.MouseEvent evt) {
       mousePressed(evt,da2_xlabel_10,"da_xlabel 10");
    }

    private void da2_device_01MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_01MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_station_01MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_station_02MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_station_03MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_station_04MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_station_05MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_station_06MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_station_07MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_station_08MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_station_09MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_station_10MouseReleased(java.awt.event.MouseEvent evt) {
       mouseReleased();
    }

    private void da2_station_11MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_station_12MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_station_13MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_station_14MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_station_15MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_station_16MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_02MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_03MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_04MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_05MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_06MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_07MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_08MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_09MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_10MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_11MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_12MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_13MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_14MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_15MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_16MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_17MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_18MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_19MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_20MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_21MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_22MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_23MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_24MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_25MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_26MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_27MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_28MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_29MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_30MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_31MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_32MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_02MouseReleased(java.awt.event.MouseEvent evt) {
       mouseReleased();
    }

    private void da2_dataname_03MouseReleased(java.awt.event.MouseEvent evt) {
       mouseReleased();
    }

    private void da2_dataname_04MouseReleased(java.awt.event.MouseEvent evt) {
       mouseReleased();
    }

    private void da2_dataname_05MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_06MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_07MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_08MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_09MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_10MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_11MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_12MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_13MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_14MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_15MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_16MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_17MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_18MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_19MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_20MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_21MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_22MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_23MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_24MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_25MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_26MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_27MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_28MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_29MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_30MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_31MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_32MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_33MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_34MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_35MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_36MouseReleased(java.awt.event.MouseEvent evt) {
       mouseReleased();
    }

    private void da2_dataname_37MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_38MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_39MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_40MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_41MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_42MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_43MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_44MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_45MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_46MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_47MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_dataname_48MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_02MouseReleased(java.awt.event.MouseEvent evt) {

    }

    private void da2_datavalue_03MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_04MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_05MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_06MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_07MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_08MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_09MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_10MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_11MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_12MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_13MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_14MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_15MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_16MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_17MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_18MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_19MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_20MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_21MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_22MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_23MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_24MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_25MouseReleased(java.awt.event.MouseEvent evt) {
       mouseReleased();
    }

    private void da2_datavalue_26MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_27MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_28MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_29MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_30MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_31MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_32MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_33MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_34MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_35MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_36MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_37MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_38MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_39MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_40MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_41MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_42MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_43MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_44MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_45MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_46MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_47MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_datavalue_48MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_xlabel_01MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_xlabel_02MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_xlabel_03MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_xlabel_04MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_xlabel_05MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_xlabel_06MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_xlabel_07MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_xlabel_08MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_xlabel_09MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_xlabel_10MouseReleased(java.awt.event.MouseEvent evt) {
        mouseReleased();
    }

    private void da2_device_01MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 01");
    }

    private void da2_dataname_01MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 01");
    }

    private void da2_station_01MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_station 01");
    }

    private void da2_station_02MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_station 02");
    }

    private void da2_station_03MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_station 03");
    }

    private void da2_station_04MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_station 04");
    }

    private void da2_station_05MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_station 05");
    }

    private void da2_station_06MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_station 06");
    }

    private void da2_station_07MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_station 07");
    }

    private void da2_station_08MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_station 08");
    }

    private void da2_station_09MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_station 09");
    }

    private void da2_station_10MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_station 10");
    }

    private void da2_station_11MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_station 11");
    }

    private void da2_station_12MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_station 12");
    }

    private void da2_station_13MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_station 13");
    }

    private void da2_station_14MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_station 14");
    }

    private void da2_station_15MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_station 15");
    }

    private void da2_station_16MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_station 16");
    }

    private void da2_device_02MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 02");
    }

    private void da2_device_03MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 03");
    }

    private void da2_device_04MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 04");
    }

    private void da2_device_05MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 05");
    }

    private void da2_device_06MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 06");
    }

    private void da2_device_07MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 07");
    }

    private void da2_device_08MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 08");
    }

    private void da2_device_09MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 09");
    }

    private void da2_device_10MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 10");
    }

    private void da2_device_11MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 11");
    }

    private void da2_device_12MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 12");
    }

    private void da2_device_13MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 13");
    }

    private void da2_device_14MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 14");
    }

    private void da2_device_15MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 15");
    }

    private void da2_device_16MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 16");
    }

    private void da2_device_17MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 17");
    }

    private void da2_device_18MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 18");
    }

    private void da2_device_19MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 19");
    }

    private void da2_device_20MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 20");
    }

    private void da2_device_21MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 21");
    }

    private void da2_device_22MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 22");
    }

    private void da2_device_23MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 23");
    }

    private void da2_device_24MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 24");
    }

    private void da2_device_25MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 25");
    }

    private void da2_device_26MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 26");
    }

    private void da2_device_27MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 27");
    }

    private void da2_device_28MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 28");
    }

    private void da2_device_29MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 29");
    }

    private void da2_device_30MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 30");
    }

    private void da2_device_31MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 31");
    }

    private void da2_device_32MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_device 32");
    }

    private void da2_dataname_02MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 02");
    }

    private void da2_dataname_03MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 03");
    }

    private void da2_dataname_04MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 04");
    }

    private void da2_dataname_05MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 05");
    }

    private void da2_dataname_06MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 06");
    }

    private void da2_dataname_07MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 07");
    }

    private void da2_dataname_08MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 08");
    }

    private void da2_dataname_09MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 09");
    }

    private void da2_dataname_10MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 10");
    }

    private void da2_dataname_11MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 11");
    }

    private void da2_dataname_12MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 12");
    }

    private void da2_dataname_13MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 13");
    }

    private void da2_dataname_14MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 14");
    }

    private void da2_dataname_15MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 15");
    }

    private void da2_dataname_16MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 16");
    }

    private void da2_dataname_17MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 17");
    }

    private void da2_dataname_18MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 18");
    }

    private void da2_dataname_19MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 19");
    }

    private void da2_dataname_20MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 20");
    }

    private void da2_dataname_21MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 21");
    }

    private void da2_dataname_22MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 22");
    }

    private void da2_dataname_23MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 23");
    }

    private void da2_dataname_24MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 24");
    }

    private void da2_dataname_25MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 25");
    }

    private void da2_dataname_26MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 26");
    }

    private void da2_dataname_27MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 27");
    }

    private void da2_dataname_28MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 28");
    }

    private void da2_dataname_29MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 29");
    }

    private void da2_dataname_30MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 30");
    }

    private void da2_dataname_31MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 31");
    }

    private void da2_dataname_32MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 32");
    }

    private void da2_dataname_33MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 33");
    }

    private void da2_dataname_34MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 34");
    }

    private void da2_dataname_35MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 35");
    }

    private void da2_dataname_36MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 36");
    }

    private void da2_dataname_37MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 37");
    }

    private void da2_dataname_38MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 38");
    }

    private void da2_dataname_39MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 39");
    }

    private void da2_dataname_40MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 40");
    }

    private void da2_dataname_41MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 41");
    }

    private void da2_dataname_42MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 42");
    }

    private void da2_dataname_43MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 43");
    }

    private void da2_dataname_44MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 44");
    }

    private void da2_dataname_45MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 45");
    }

    private void da2_dataname_46MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 46");
    }

    private void da2_dataname_47MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 47");
    }

    private void da2_dataname_48MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_dataname 48");
    }

    private void da2_datavalue_02MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 02");
    }

    private void da2_datavalue_03MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 03");
    }

    private void da2_datavalue_04MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 04");
    }

    private void da2_datavalue_05MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 05");
    }

    private void da2_datavalue_06MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 06");
    }

    private void da2_datavalue_07MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 07");
    }

    private void da2_datavalue_08MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 08");
    }

    private void da2_datavalue_09MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 09");
    }

    private void da2_datavalue_10MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 10");
    }

    private void da2_datavalue_11MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 11");
    }

    private void da2_datavalue_12MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 12");
    }

    private void da2_datavalue_13MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 13");
    }

    private void da2_datavalue_14MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 14");
    }

    private void da2_datavalue_15MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 15");
    }

    private void da2_datavalue_16MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 16");
    }

    private void da2_datavalue_17MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 17");
    }

    private void da2_datavalue_18MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 18");
    }

    private void da2_datavalue_19MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 19");
    }

    private void da2_datavalue_20MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 20");
    }

    private void da2_datavalue_21MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 21");
    }

    private void da2_datavalue_22MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 22");
    }

    private void da2_datavalue_23MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 23");
    }

    private void da2_datavalue_24MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 24");
    }

    private void da2_datavalue_25MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 25");
    }

    private void da2_datavalue_26MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 26");
    }

    private void da2_datavalue_27MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 27");
    }

    private void da2_datavalue_28MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 28");
    }

    private void da2_datavalue_29MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 29");
    }

    private void da2_datavalue_30MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 30");
    }

    private void da2_datavalue_31MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 31");
    }

    private void da2_datavalue_32MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 32");
    }

    private void da2_datavalue_33MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 33");
    }

    private void da2_datavalue_34MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 34");
    }

    private void da2_datavalue_35MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 35");
    }

    private void da2_datavalue_36MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 36");
    }

    private void da2_datavalue_37MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 37");
    }

    private void da2_datavalue_38MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 38");
    }

    private void da2_datavalue_39MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 39");
    }

    private void da2_datavalue_40MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 40");
    }

    private void da2_datavalue_41MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 41");
    }

    private void da2_datavalue_42MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 42");
    }

    private void da2_datavalue_43MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 43");
    }

    private void da2_datavalue_44MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 44");
    }

    private void da2_datavalue_45MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 45");
    }

    private void da2_datavalue_46MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 46");
    }

    private void da2_datavalue_47MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 47");
    }

    private void da2_datavalue_48MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_datavalue 48");
    }

    private void da2_xlabel_01MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_xlabel 01");
    }

    private void da2_xlabel_02MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_xlabel 02");
    }

    private void da2_xlabel_03MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_xlabel 03");
    }

    private void da2_xlabel_04MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_xlabel 04");
    }

    private void da2_xlabel_05MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_xlabel 05");
    }

    private void da2_xlabel_06MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_xlabel 06");
    }

    private void da2_xlabel_07MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_xlabel 07");
    }

    private void da2_xlabel_08MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_xlabel 08");
    }

    private void da2_xlabel_09MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_xlabel 09");
    }

    private void da2_xlabel_10MouseDragged(java.awt.event.MouseEvent evt) {
        mouseDragged(evt,"da_xlabel 10");
    }
void mousePressed(java.awt.event.MouseEvent evt,Component component,String key){
      int x=evt.getX(),y=evt.getY(),width2=0,height2=0;
      localMousePressX=x;
      localMousePressY=y;

      mousePressType=0;
      int panelWidth = this.getWidth();
      int panelHeight = this.getHeight();
      int frameWidth = instrument.getWidth();
     int frameHeight = instrument.getHeight();

     int itemWidth=component.getWidth();
     int itemHeight=component.getHeight();
      String info[];

      if(instrument.editUI.get("data area")!=null){
         info=ylib.csvlinetoarray((String)instrument.editUI.get("data area"));
         double width3=(instrument.isNumeric(info[5])? Double.parseDouble(info[5]):0.1);
         double height3=(instrument.isNumeric(info[6])? Double.parseDouble(info[6]):0.1);
         width3=width3*((double)frameWidth);
         height3=height3*((double)frameHeight);
         xRatio=((double)panelWidth)/width3;
         yRatio=((double)panelHeight)/height3;
      } else return;
      if(instrument.editUI.get(key)!=null){
       info=ylib.csvlinetoarray((String)instrument.editUI.get(key));

       if(info.length>2 && info[2].equalsIgnoreCase("s")){
        if(info.length > 3 && info[3].length()>0) {
            originalX=((double)Math.round(Double.parseDouble(info[3])*1000000.0))/1000000.0;
        }
        if(info.length > 4 && info[4].length()>0) {
            originalY=((double)Math.round(Double.parseDouble(info[4])*1000000.0))/1000000.0;
        }
        if(info.length > 5 && info[5].length()>0) {
            originalWidth=((double)Math.round(Double.parseDouble(info[5])*1000000.0))/1000000.0;
        }
        if(info.length > 6 && info[6].length()>0) {
            originalHeight=((double)Math.round(Double.parseDouble(info[6])*1000000.0))/1000000.0;
        }
        globalMousePressX=evt.getXOnScreen();
        globalMousePressY=evt.getYOnScreen();
        if(x<=5 && y<=5)  mousePressType=8;
        else if(x>=itemWidth -5 &&  y<=5) mousePressType=5;
        else if(x>=itemWidth -5 &&  y>=itemHeight-5) mousePressType=6;
        else if(x<=5 &&  y>=itemHeight-5) mousePressType=7;
        else if(y<=5) mousePressType=1;
        else if(x>=itemWidth-5) mousePressType=2;
        else if(y>=itemHeight-5)mousePressType=3;
        else if(x<=5) mousePressType=4;
       }
      }
     instrument.uiPanel2.jComboBox15.setSelectedItem(key);

}
void mouseReleased(){
      localMousePressX=-1;
      localMousePressY=-1;
      globalMousePressX=-1;
      globalMousePressY=-1;
      mousePressType=-1;
}
void mouseDragged(java.awt.event.MouseEvent evt,String key){
     int x=evt.getX(),y=evt.getY();
     int panelWidth = this.getWidth();
     int panelHeight = this.getHeight();
     int frameWidth = instrument.getWidth();
     int frameHeight = instrument.getHeight();
      int diffX=0,diffY=0;
      int x2=0,y2=0,width=0,height=0;
      if(instrument.editUI.get(key)!=null){
       String info[]=ylib.csvlinetoarray((String)instrument.editUI.get(key));

       if(info.length>2 && info[2].equalsIgnoreCase("s")){
        diffX=evt.getXOnScreen()-globalMousePressX;
        diffY=evt.getYOnScreen()-globalMousePressY;
        double diffXPersen=((double)diffX)/xRatio/((double)frameWidth);
        double diffYPersen=((double)diffY)/yRatio/((double)frameHeight);
        switch(mousePressType){
          case 0:
            double newInfo3=((double)Math.round((originalX+diffXPersen)*1000000.0))/1000000.0;
            double newInfo4=((double)Math.round((originalY+diffYPersen)*1000000.0))/1000000.0;
            instrument.skipUITFChanged=true;
              instrument.uiPanel2.jTextField84.setText(""+((double)Math.round(newInfo3*1000000.0))/10000.0);
              instrument.uiPanel2.jTextField85.setText(""+((double)Math.round(newInfo4*1000000.0))/10000.0);

            info[3]=""+newInfo3;
            info[4]=""+newInfo4;
            instrument.editUI.put(key,ylib.arrayToCsvLine(info));
            instrument.updateUIDALayoutAll=true;
            invalidate();
            instrument.skipUITFChanged=false;
            break;
          case 1:
          case 3:

            double newInfo4_2=(mousePressType==3? Double.parseDouble(info[4]):((double)Math.round((originalY+diffYPersen)*1000000.0))/1000000.0);
            double newInfo6_2=originalHeight+((double)Math.round(diffYPersen*10000.0))/10000.0*(mousePressType==3? 1.0:-1.0);
            if(newInfo6_2<0) newInfo6_2=5.0/((double)panelHeight);
            instrument.skipUITFChanged=true;
            instrument.uiPanel2.jTextField85.setText(""+((double)Math.round(newInfo4_2*1000000.0))/10000.0);
            instrument.uiPanel2.jTextField87.setText(""+((double)Math.round(newInfo6_2*1000000.0))/10000.0);
            info[4]=""+newInfo4_2;
            info[6]=""+newInfo6_2;
            instrument.editUI.put(key,ylib.arrayToCsvLine(info));
            instrument.updateUIDALayoutAll=true;
            invalidate();
            instrument.skipUITFChanged=false;
            break;
          case 2:
          case 4:

            double newInfo3_4=(mousePressType==2? Double.parseDouble(info[3]):((double)Math.round((originalX+diffXPersen)*1000000.0))/1000000.0);
            double newInfo5_4=originalWidth+((double)Math.round((diffXPersen)*10000.0))/10000.0*(mousePressType==2? 1.0:-1.0);
            if(newInfo5_4<0) newInfo5_4=5.0/((double)panelWidth);
            instrument.skipUITFChanged=true;
            instrument.uiPanel2.jTextField84.setText(""+((double)Math.round(newInfo3_4*1000000.0))/10000.0);
            instrument.uiPanel2.jTextField86.setText(""+((double)Math.round(newInfo5_4*1000000.0))/10000.0);
            info[3]=""+newInfo3_4;
            info[5]=""+newInfo5_4;
            instrument.editUI.put(key,ylib.arrayToCsvLine(info));
            instrument.updateUIDALayoutAll=true;
            invalidate();
            instrument.skipUITFChanged=false;
            break;
          case 5:
          case 6:
          case 7:
          case 8:

            double newInfo3_5=(mousePressType==5 || mousePressType==6? Double.parseDouble(info[3]):((double)Math.round((originalX+diffXPersen)*1000000.0))/1000000.0);
            double newInfo4_5=(mousePressType==6 || mousePressType==7? Double.parseDouble(info[4]):((double)Math.round((originalY+diffYPersen)*1000000.0))/1000000.0);
            double newInfo5_5=originalWidth+((double)Math.round((((double)diffX)/((double)panelWidth))*10000.0))/10000.0*(mousePressType==7 || mousePressType==8? -1.0:1.0);
            double newInfo6_5=originalHeight+((double)Math.round((((double)diffY)/((double)panelHeight))*10000.0))/10000.0*(mousePressType==5 || mousePressType==8? -1.0:1.0);
            if(newInfo5_5<0) newInfo5_5=5.0/((double)panelWidth);
            if(newInfo6_5<0) newInfo6_5=5.0/((double)panelHeight);
            instrument.skipUITFChanged=true;
            instrument.uiPanel2.jTextField84.setText(""+((double)Math.round(newInfo3_5*1000000.0))/10000.0);
            instrument.uiPanel2.jTextField85.setText(""+((double)Math.round(newInfo4_5*1000000.0))/10000.0);
            instrument.uiPanel2.jTextField86.setText(""+((double)Math.round(newInfo5_5*1000000.0))/10000.0);
            instrument.uiPanel2.jTextField87.setText(""+((double)Math.round(newInfo6_5*1000000.0))/10000.0);
            info[3]=""+newInfo3_5;
            info[4]=""+newInfo4_5;
            info[5]=""+newInfo5_5;
            info[6]=""+newInfo6_5;
            instrument.editUI.put(key,ylib.arrayToCsvLine(info));
            instrument.updateUIDALayoutAll=true;
            invalidate();
            instrument.skipUITFChanged=false;
            break;
         }
       }
      }

}

    private javax.swing.JLabel da2_dataname_01;
    private javax.swing.JLabel da2_dataname_02;
    private javax.swing.JLabel da2_dataname_03;
    private javax.swing.JLabel da2_dataname_04;
    private javax.swing.JLabel da2_dataname_05;
    private javax.swing.JLabel da2_dataname_06;
    private javax.swing.JLabel da2_dataname_07;
    private javax.swing.JLabel da2_dataname_08;
    private javax.swing.JLabel da2_dataname_09;
    private javax.swing.JLabel da2_dataname_10;
    private javax.swing.JLabel da2_dataname_11;
    private javax.swing.JLabel da2_dataname_12;
    private javax.swing.JLabel da2_dataname_13;
    private javax.swing.JLabel da2_dataname_14;
    private javax.swing.JLabel da2_dataname_15;
    private javax.swing.JLabel da2_dataname_16;
    private javax.swing.JLabel da2_dataname_17;
    private javax.swing.JLabel da2_dataname_18;
    private javax.swing.JLabel da2_dataname_19;
    private javax.swing.JLabel da2_dataname_20;
    private javax.swing.JLabel da2_dataname_21;
    private javax.swing.JLabel da2_dataname_22;
    private javax.swing.JLabel da2_dataname_23;
    private javax.swing.JLabel da2_dataname_24;
    private javax.swing.JLabel da2_dataname_25;
    private javax.swing.JLabel da2_dataname_26;
    private javax.swing.JLabel da2_dataname_27;
    private javax.swing.JLabel da2_dataname_28;
    private javax.swing.JLabel da2_dataname_29;
    private javax.swing.JLabel da2_dataname_30;
    private javax.swing.JLabel da2_dataname_31;
    private javax.swing.JLabel da2_dataname_32;
    private javax.swing.JLabel da2_dataname_33;
    private javax.swing.JLabel da2_dataname_34;
    private javax.swing.JLabel da2_dataname_35;
    private javax.swing.JLabel da2_dataname_36;
    private javax.swing.JLabel da2_dataname_37;
    private javax.swing.JLabel da2_dataname_38;
    private javax.swing.JLabel da2_dataname_39;
    private javax.swing.JLabel da2_dataname_40;
    private javax.swing.JLabel da2_dataname_41;
    private javax.swing.JLabel da2_dataname_42;
    private javax.swing.JLabel da2_dataname_43;
    private javax.swing.JLabel da2_dataname_44;
    private javax.swing.JLabel da2_dataname_45;
    private javax.swing.JLabel da2_dataname_46;
    private javax.swing.JLabel da2_dataname_47;
    private javax.swing.JLabel da2_dataname_48;
    private javax.swing.JLabel da2_datavalue_01;
    private javax.swing.JLabel da2_datavalue_02;
    private javax.swing.JLabel da2_datavalue_03;
    private javax.swing.JLabel da2_datavalue_04;
    private javax.swing.JLabel da2_datavalue_05;
    private javax.swing.JLabel da2_datavalue_06;
    private javax.swing.JLabel da2_datavalue_07;
    private javax.swing.JLabel da2_datavalue_08;
    private javax.swing.JLabel da2_datavalue_09;
    private javax.swing.JLabel da2_datavalue_10;
    private javax.swing.JLabel da2_datavalue_11;
    private javax.swing.JLabel da2_datavalue_12;
    private javax.swing.JLabel da2_datavalue_13;
    private javax.swing.JLabel da2_datavalue_14;
    private javax.swing.JLabel da2_datavalue_15;
    private javax.swing.JLabel da2_datavalue_16;
    private javax.swing.JLabel da2_datavalue_17;
    private javax.swing.JLabel da2_datavalue_18;
    private javax.swing.JLabel da2_datavalue_19;
    private javax.swing.JLabel da2_datavalue_20;
    private javax.swing.JLabel da2_datavalue_21;
    private javax.swing.JLabel da2_datavalue_22;
    private javax.swing.JLabel da2_datavalue_23;
    private javax.swing.JLabel da2_datavalue_24;
    private javax.swing.JLabel da2_datavalue_25;
    private javax.swing.JLabel da2_datavalue_26;
    private javax.swing.JLabel da2_datavalue_27;
    private javax.swing.JLabel da2_datavalue_28;
    private javax.swing.JLabel da2_datavalue_29;
    private javax.swing.JLabel da2_datavalue_30;
    private javax.swing.JLabel da2_datavalue_31;
    private javax.swing.JLabel da2_datavalue_32;
    private javax.swing.JLabel da2_datavalue_33;
    private javax.swing.JLabel da2_datavalue_34;
    private javax.swing.JLabel da2_datavalue_35;
    private javax.swing.JLabel da2_datavalue_36;
    private javax.swing.JLabel da2_datavalue_37;
    private javax.swing.JLabel da2_datavalue_38;
    private javax.swing.JLabel da2_datavalue_39;
    private javax.swing.JLabel da2_datavalue_40;
    private javax.swing.JLabel da2_datavalue_41;
    private javax.swing.JLabel da2_datavalue_42;
    private javax.swing.JLabel da2_datavalue_43;
    private javax.swing.JLabel da2_datavalue_44;
    private javax.swing.JLabel da2_datavalue_45;
    private javax.swing.JLabel da2_datavalue_46;
    private javax.swing.JLabel da2_datavalue_47;
    private javax.swing.JLabel da2_datavalue_48;
    private javax.swing.JLabel da2_device_01;
    private javax.swing.JLabel da2_device_02;
    private javax.swing.JLabel da2_device_03;
    private javax.swing.JLabel da2_device_04;
    private javax.swing.JLabel da2_device_05;
    private javax.swing.JLabel da2_device_06;
    private javax.swing.JLabel da2_device_07;
    private javax.swing.JLabel da2_device_08;
    private javax.swing.JLabel da2_device_09;
    private javax.swing.JLabel da2_device_10;
    private javax.swing.JLabel da2_device_11;
    private javax.swing.JLabel da2_device_12;
    private javax.swing.JLabel da2_device_13;
    private javax.swing.JLabel da2_device_14;
    private javax.swing.JLabel da2_device_15;
    private javax.swing.JLabel da2_device_16;
    private javax.swing.JLabel da2_device_17;
    private javax.swing.JLabel da2_device_18;
    private javax.swing.JLabel da2_device_19;
    private javax.swing.JLabel da2_device_20;
    private javax.swing.JLabel da2_device_21;
    private javax.swing.JLabel da2_device_22;
    private javax.swing.JLabel da2_device_23;
    private javax.swing.JLabel da2_device_24;
    private javax.swing.JLabel da2_device_25;
    private javax.swing.JLabel da2_device_26;
    private javax.swing.JLabel da2_device_27;
    private javax.swing.JLabel da2_device_28;
    private javax.swing.JLabel da2_device_29;
    private javax.swing.JLabel da2_device_30;
    private javax.swing.JLabel da2_device_31;
    private javax.swing.JLabel da2_device_32;
    private javax.swing.JLabel da2_station_01;
    private javax.swing.JLabel da2_station_02;
    private javax.swing.JLabel da2_station_03;
    private javax.swing.JLabel da2_station_04;
    private javax.swing.JLabel da2_station_05;
    private javax.swing.JLabel da2_station_06;
    private javax.swing.JLabel da2_station_07;
    private javax.swing.JLabel da2_station_08;
    private javax.swing.JLabel da2_station_09;
    private javax.swing.JLabel da2_station_10;
    private javax.swing.JLabel da2_station_11;
    private javax.swing.JLabel da2_station_12;
    private javax.swing.JLabel da2_station_13;
    private javax.swing.JLabel da2_station_14;
    private javax.swing.JLabel da2_station_15;
    private javax.swing.JLabel da2_station_16;
    private javax.swing.JLabel da2_xlabel_01;
    private javax.swing.JLabel da2_xlabel_02;
    private javax.swing.JLabel da2_xlabel_03;
    private javax.swing.JLabel da2_xlabel_04;
    private javax.swing.JLabel da2_xlabel_05;
    private javax.swing.JLabel da2_xlabel_06;
    private javax.swing.JLabel da2_xlabel_07;
    private javax.swing.JLabel da2_xlabel_08;
    private javax.swing.JLabel da2_xlabel_09;
    private javax.swing.JLabel da2_xlabel_10;

}