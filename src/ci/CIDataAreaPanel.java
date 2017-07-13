
package ci;

import java.awt.Color;
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
public class CIDataAreaPanel extends javax.swing.JPanel {

    CrInstrument instrument;
    ResourceBundle bundle2 = java.util.ResourceBundle.getBundle("ci/Bundle");
    public CIDataAreaPanel(CrInstrument ci) {
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

         Color fontColor=((info.length>10 && info[10].length()>0 && instrument.isNumeric(info[10]))? new Color(Integer.parseInt(info[10])):getForeground());

         setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 255)), bundle2.getString("CrInstrument.jPanel162.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, this.getFont(), fontColor)); 
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
   }
   void setLabel(String key,JLabel label,int frameWidth,int frameHeight){
     String info[];
     if(instrument.editUI.get(key)!=null){
         info=ylib.csvlinetoarray((String)instrument.editUI.get(key));
         if(info.length>2 && info[2].equalsIgnoreCase("s")){
           int x2=0,y2=0,width2=0,height2=0;
           label.setVisible(true);
           if(info.length > 3 && info[3].length()>0) x2=(int)(Double.parseDouble(info[3]) * ((double)frameWidth));
           if(info.length > 4 && info[4].length()>0) y2=(int)(Double.parseDouble(info[4]) * ((double)frameHeight));
           if(info.length > 5 && info[5].length()>0) width2=(int)(Double.parseDouble(info[5]) * ((double)frameWidth));
           if(info.length > 6 && info[6].length()>0) height2=(int)(Double.parseDouble(info[6]) * ((double)frameHeight));
           label.setBounds((int)(((double)x2)/instrument.dataAreaRatio), (int)(((double)y2)/instrument.dataAreaRatio),
                   (int)(((double)width2)/instrument.dataAreaRatio),(int)(((double)height2)/instrument.dataAreaRatio));
           Font font=label.getFont();
           if(info[1].trim().length()>0) label.setText(info[1]);
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
     if(instrument.uiPanel2.jComboBox15.getSelectedIndex()!=-1){
       String sel=(String)instrument.uiPanel2.jComboBox15.getSelectedItem();
    if(instrument.editUI.get(sel)==null) return;
    String info[]=ylib.csvlinetoarray((String)instrument.editUI.get(sel));
    if(instrument.uiPanel2.jCheckBox64.isSelected()) info[2]="s"; else info[2]="e";
    info[3]=(instrument.isNumeric(instrument.uiPanel2.jTextField84.getText())? String.valueOf(Double.parseDouble(instrument.uiPanel2.jTextField84.getText())/100.0):"0.0");
    info[4]=(instrument.isNumeric(instrument.uiPanel2.jTextField85.getText())? String.valueOf(Double.parseDouble(instrument.uiPanel2.jTextField85.getText())/100.0):"0.0");
    info[5]=(instrument.isNumeric(instrument.uiPanel2.jTextField86.getText())? String.valueOf(Double.parseDouble(instrument.uiPanel2.jTextField86.getText())/100.0):"0.0");
    info[6]=(instrument.isNumeric(instrument.uiPanel2.jTextField87.getText())? String.valueOf(Double.parseDouble(instrument.uiPanel2.jTextField87.getText())/100.0):"0.0");
    info[9]=instrument.uiPanel2.jTextField88.getText().trim();
    if(instrument.uiPanel2.jCheckBox65.isSelected()) info[11]="b"; else info[11]="e";
    if(instrument.uiPanel2.jCheckBox66.isSelected()) info[12]="i"; else info[12]="e";
    info[7]=""+instrument.uiPanel2.jLabel324.getBackground().getRGB();
    info[10]=""+instrument.uiPanel2.jLabel328.getBackground().getRGB();
    instrument.editUI.put(sel,ylib.arrayToCsvLine(info));
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

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        setLayout(null);

        da2_datavalue_01.setFont(da2_datavalue_01.getFont().deriveFont(da2_datavalue_01.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_01.getFont().getSize()+36));
        da2_datavalue_01.setForeground(new java.awt.Color(255, 0, 51));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ci/Bundle"); 
        da2_datavalue_01.setText(bundle.getString("CrInstrument.da2_datavalue_01.text")); 
        da2_datavalue_01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_01MouseClicked(evt);
            }
        });
        add(da2_datavalue_01);
        da2_datavalue_01.setBounds(60, 150, 300, 60);

        da2_device_01.setFont(da2_device_01.getFont().deriveFont(da2_device_01.getFont().getStyle() | java.awt.Font.BOLD, da2_device_01.getFont().getSize()+6));
        da2_device_01.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_01.setText(bundle.getString("CrInstrument.da2_device_01.text")); 
        da2_device_01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_01MouseClicked(evt);
            }
        });
        add(da2_device_01);
        da2_device_01.setBounds(20, 60, 320, 30);

        da2_dataname_01.setFont(da2_dataname_01.getFont().deriveFont(da2_dataname_01.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_01.getFont().getSize()+6));
        da2_dataname_01.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_01.setText(bundle.getString("CrInstrument.da2_dataname_01.text")); 
        da2_dataname_01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_01MouseClicked(evt);
            }
        });
        add(da2_dataname_01);
        da2_dataname_01.setBounds(20, 110, 320, 30);

        da2_station_01.setFont(da2_station_01.getFont().deriveFont(da2_station_01.getFont().getStyle() | java.awt.Font.BOLD, da2_station_01.getFont().getSize()+6));
        da2_station_01.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_01.setText(bundle.getString("CrInstrument.da2_station_01.text")); 
        da2_station_01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_01MouseClicked(evt);
            }
        });
        add(da2_station_01);
        da2_station_01.setBounds(20, 30, 280, 20);

        da2_station_02.setFont(da2_station_02.getFont().deriveFont(da2_station_02.getFont().getStyle() | java.awt.Font.BOLD, da2_station_02.getFont().getSize()+6));
        da2_station_02.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_02.setText(bundle.getString("CrInstrument.da2_station_02.text")); 
        da2_station_02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_02MouseClicked(evt);
            }
        });
        add(da2_station_02);
        da2_station_02.setBounds(20, 30, 280, 20);

        da2_station_03.setFont(da2_station_03.getFont().deriveFont(da2_station_03.getFont().getStyle() | java.awt.Font.BOLD, da2_station_03.getFont().getSize()+6));
        da2_station_03.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_03.setText(bundle.getString("CrInstrument.da2_station_03.text")); 
        da2_station_03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_03MouseClicked(evt);
            }
        });
        add(da2_station_03);
        da2_station_03.setBounds(20, 30, 280, 20);

        da2_station_04.setFont(da2_station_04.getFont().deriveFont(da2_station_04.getFont().getStyle() | java.awt.Font.BOLD, da2_station_04.getFont().getSize()+6));
        da2_station_04.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_04.setText(bundle.getString("CrInstrument.da2_station_04.text")); 
        da2_station_04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_04MouseClicked(evt);
            }
        });
        add(da2_station_04);
        da2_station_04.setBounds(20, 30, 280, 20);

        da2_station_05.setFont(da2_station_05.getFont().deriveFont(da2_station_05.getFont().getStyle() | java.awt.Font.BOLD, da2_station_05.getFont().getSize()+6));
        da2_station_05.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_05.setText(bundle.getString("CrInstrument.da2_station_05.text")); 
        da2_station_05.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_05MouseClicked(evt);
            }
        });
        add(da2_station_05);
        da2_station_05.setBounds(20, 30, 280, 20);

        da2_station_06.setFont(da2_station_06.getFont().deriveFont(da2_station_06.getFont().getStyle() | java.awt.Font.BOLD, da2_station_06.getFont().getSize()+6));
        da2_station_06.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_06.setText(bundle.getString("CrInstrument.da2_station_06.text")); 
        da2_station_06.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_06MouseClicked(evt);
            }
        });
        add(da2_station_06);
        da2_station_06.setBounds(20, 30, 280, 20);

        da2_station_07.setFont(da2_station_07.getFont().deriveFont(da2_station_07.getFont().getStyle() | java.awt.Font.BOLD, da2_station_07.getFont().getSize()+6));
        da2_station_07.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_07.setText(bundle.getString("CrInstrument.da2_station_07.text")); 
        da2_station_07.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_07MouseClicked(evt);
            }
        });
        add(da2_station_07);
        da2_station_07.setBounds(20, 30, 280, 20);

        da2_station_08.setFont(da2_station_08.getFont().deriveFont(da2_station_08.getFont().getStyle() | java.awt.Font.BOLD, da2_station_08.getFont().getSize()+6));
        da2_station_08.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_08.setText(bundle.getString("CrInstrument.da2_station_08.text")); 
        da2_station_08.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_08MouseClicked(evt);
            }
        });
        add(da2_station_08);
        da2_station_08.setBounds(20, 30, 280, 20);

        da2_station_09.setFont(da2_station_09.getFont().deriveFont(da2_station_09.getFont().getStyle() | java.awt.Font.BOLD, da2_station_09.getFont().getSize()+6));
        da2_station_09.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_09.setText(bundle.getString("CrInstrument.da2_station_09.text")); 
        da2_station_09.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_09MouseClicked(evt);
            }
        });
        add(da2_station_09);
        da2_station_09.setBounds(20, 30, 280, 20);

        da2_station_10.setFont(da2_station_10.getFont().deriveFont(da2_station_10.getFont().getStyle() | java.awt.Font.BOLD, da2_station_10.getFont().getSize()+6));
        da2_station_10.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_10.setText(bundle.getString("CrInstrument.da2_station_10.text")); 
        da2_station_10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_10MouseClicked(evt);
            }
        });
        add(da2_station_10);
        da2_station_10.setBounds(20, 30, 280, 20);

        da2_station_11.setFont(da2_station_11.getFont().deriveFont(da2_station_11.getFont().getStyle() | java.awt.Font.BOLD, da2_station_11.getFont().getSize()+6));
        da2_station_11.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_11.setText(bundle.getString("CrInstrument.da2_station_11.text")); 
        da2_station_11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_11MouseClicked(evt);
            }
        });
        add(da2_station_11);
        da2_station_11.setBounds(20, 30, 280, 20);

        da2_station_12.setFont(da2_station_12.getFont().deriveFont(da2_station_12.getFont().getStyle() | java.awt.Font.BOLD, da2_station_12.getFont().getSize()+6));
        da2_station_12.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_12.setText(bundle.getString("CrInstrument.da2_station_12.text")); 
        da2_station_12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_12MouseClicked(evt);
            }
        });
        add(da2_station_12);
        da2_station_12.setBounds(20, 30, 280, 20);

        da2_station_13.setFont(da2_station_13.getFont().deriveFont(da2_station_13.getFont().getStyle() | java.awt.Font.BOLD, da2_station_13.getFont().getSize()+6));
        da2_station_13.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_13.setText(bundle.getString("CrInstrument.da2_station_13.text")); 
        da2_station_13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_13MouseClicked(evt);
            }
        });
        add(da2_station_13);
        da2_station_13.setBounds(20, 30, 280, 20);

        da2_station_14.setFont(da2_station_14.getFont().deriveFont(da2_station_14.getFont().getStyle() | java.awt.Font.BOLD, da2_station_14.getFont().getSize()+6));
        da2_station_14.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_14.setText(bundle.getString("CrInstrument.da2_station_14.text")); 
        da2_station_14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_14MouseClicked(evt);
            }
        });
        add(da2_station_14);
        da2_station_14.setBounds(20, 30, 280, 20);

        da2_station_15.setFont(da2_station_15.getFont().deriveFont(da2_station_15.getFont().getStyle() | java.awt.Font.BOLD, da2_station_15.getFont().getSize()+6));
        da2_station_15.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_15.setText(bundle.getString("CrInstrument.da2_station_15.text")); 
        da2_station_15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_15MouseClicked(evt);
            }
        });
        add(da2_station_15);
        da2_station_15.setBounds(20, 30, 280, 20);

        da2_station_16.setFont(da2_station_16.getFont().deriveFont(da2_station_16.getFont().getStyle() | java.awt.Font.BOLD, da2_station_16.getFont().getSize()+6));
        da2_station_16.setForeground(new java.awt.Color(153, 255, 255));
        da2_station_16.setText(bundle.getString("CrInstrument.da2_station_16.text")); 
        da2_station_16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_station_16MouseClicked(evt);
            }
        });
        add(da2_station_16);
        da2_station_16.setBounds(20, 30, 280, 20);

        da2_device_02.setFont(da2_device_02.getFont().deriveFont(da2_device_02.getFont().getStyle() | java.awt.Font.BOLD, da2_device_02.getFont().getSize()+6));
        da2_device_02.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_02.setText(bundle.getString("CrInstrument.da2_device_02.text")); 
        da2_device_02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_02MouseClicked(evt);
            }
        });
        add(da2_device_02);
        da2_device_02.setBounds(20, 60, 320, 30);

        da2_device_03.setFont(da2_device_03.getFont().deriveFont(da2_device_03.getFont().getStyle() | java.awt.Font.BOLD, da2_device_03.getFont().getSize()+6));
        da2_device_03.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_03.setText(bundle.getString("CrInstrument.da2_device_03.text")); 
        da2_device_03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_03MouseClicked(evt);
            }
        });
        add(da2_device_03);
        da2_device_03.setBounds(20, 60, 320, 30);

        da2_device_04.setFont(da2_device_04.getFont().deriveFont(da2_device_04.getFont().getStyle() | java.awt.Font.BOLD, da2_device_04.getFont().getSize()+6));
        da2_device_04.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_04.setText(bundle.getString("CrInstrument.da2_device_04.text")); 
        da2_device_04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_04MouseClicked(evt);
            }
        });
        add(da2_device_04);
        da2_device_04.setBounds(20, 60, 320, 30);

        da2_device_05.setFont(da2_device_05.getFont().deriveFont(da2_device_05.getFont().getStyle() | java.awt.Font.BOLD, da2_device_05.getFont().getSize()+6));
        da2_device_05.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_05.setText(bundle.getString("CrInstrument.da2_device_05.text")); 
        da2_device_05.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_05MouseClicked(evt);
            }
        });
        add(da2_device_05);
        da2_device_05.setBounds(20, 60, 320, 30);

        da2_device_06.setFont(da2_device_06.getFont().deriveFont(da2_device_06.getFont().getStyle() | java.awt.Font.BOLD, da2_device_06.getFont().getSize()+6));
        da2_device_06.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_06.setText(bundle.getString("CrInstrument.da2_device_06.text")); 
        da2_device_06.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_06MouseClicked(evt);
            }
        });
        add(da2_device_06);
        da2_device_06.setBounds(20, 60, 320, 30);

        da2_device_07.setFont(da2_device_07.getFont().deriveFont(da2_device_07.getFont().getStyle() | java.awt.Font.BOLD, da2_device_07.getFont().getSize()+6));
        da2_device_07.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_07.setText(bundle.getString("CrInstrument.da2_device_07.text")); 
        da2_device_07.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_07MouseClicked(evt);
            }
        });
        add(da2_device_07);
        da2_device_07.setBounds(20, 60, 320, 30);

        da2_device_08.setFont(da2_device_08.getFont().deriveFont(da2_device_08.getFont().getStyle() | java.awt.Font.BOLD, da2_device_08.getFont().getSize()+6));
        da2_device_08.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_08.setText(bundle.getString("CrInstrument.da2_device_08.text")); 
        da2_device_08.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_08MouseClicked(evt);
            }
        });
        add(da2_device_08);
        da2_device_08.setBounds(20, 60, 320, 30);

        da2_device_09.setFont(da2_device_09.getFont().deriveFont(da2_device_09.getFont().getStyle() | java.awt.Font.BOLD, da2_device_09.getFont().getSize()+6));
        da2_device_09.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_09.setText(bundle.getString("CrInstrument.da2_device_09.text")); 
        da2_device_09.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_09MouseClicked(evt);
            }
        });
        add(da2_device_09);
        da2_device_09.setBounds(20, 60, 320, 30);

        da2_device_10.setFont(da2_device_10.getFont().deriveFont(da2_device_10.getFont().getStyle() | java.awt.Font.BOLD, da2_device_10.getFont().getSize()+6));
        da2_device_10.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_10.setText(bundle.getString("CrInstrument.da2_device_10.text")); 
        da2_device_10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_10MouseClicked(evt);
            }
        });
        add(da2_device_10);
        da2_device_10.setBounds(20, 60, 320, 30);

        da2_device_11.setFont(da2_device_11.getFont().deriveFont(da2_device_11.getFont().getStyle() | java.awt.Font.BOLD, da2_device_11.getFont().getSize()+6));
        da2_device_11.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_11.setText(bundle.getString("CrInstrument.da2_device_11.text")); 
        da2_device_11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_11MouseClicked(evt);
            }
        });
        add(da2_device_11);
        da2_device_11.setBounds(20, 60, 320, 30);

        da2_device_12.setFont(da2_device_12.getFont().deriveFont(da2_device_12.getFont().getStyle() | java.awt.Font.BOLD, da2_device_12.getFont().getSize()+6));
        da2_device_12.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_12.setText(bundle.getString("CrInstrument.da2_device_12.text")); 
        da2_device_12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_12MouseClicked(evt);
            }
        });
        add(da2_device_12);
        da2_device_12.setBounds(20, 60, 320, 30);

        da2_device_13.setFont(da2_device_13.getFont().deriveFont(da2_device_13.getFont().getStyle() | java.awt.Font.BOLD, da2_device_13.getFont().getSize()+6));
        da2_device_13.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_13.setText(bundle.getString("CrInstrument.da2_device_13.text")); 
        da2_device_13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_13MouseClicked(evt);
            }
        });
        add(da2_device_13);
        da2_device_13.setBounds(20, 60, 320, 30);

        da2_device_14.setFont(da2_device_14.getFont().deriveFont(da2_device_14.getFont().getStyle() | java.awt.Font.BOLD, da2_device_14.getFont().getSize()+6));
        da2_device_14.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_14.setText(bundle.getString("CrInstrument.da2_device_14.text")); 
        da2_device_14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_14MouseClicked(evt);
            }
        });
        add(da2_device_14);
        da2_device_14.setBounds(20, 60, 320, 30);

        da2_device_15.setFont(da2_device_15.getFont().deriveFont(da2_device_15.getFont().getStyle() | java.awt.Font.BOLD, da2_device_15.getFont().getSize()+6));
        da2_device_15.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_15.setText(bundle.getString("CrInstrument.da2_device_15.text")); 
        da2_device_15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_15MouseClicked(evt);
            }
        });
        add(da2_device_15);
        da2_device_15.setBounds(20, 60, 320, 30);

        da2_device_16.setFont(da2_device_16.getFont().deriveFont(da2_device_16.getFont().getStyle() | java.awt.Font.BOLD, da2_device_16.getFont().getSize()+6));
        da2_device_16.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_16.setText(bundle.getString("CrInstrument.da2_device_16.text")); 
        da2_device_16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_16MouseClicked(evt);
            }
        });
        add(da2_device_16);
        da2_device_16.setBounds(20, 60, 320, 30);

        da2_device_17.setFont(da2_device_17.getFont().deriveFont(da2_device_17.getFont().getStyle() | java.awt.Font.BOLD, da2_device_17.getFont().getSize()+6));
        da2_device_17.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_17.setText(bundle.getString("CrInstrument.da2_device_17.text")); 
        da2_device_17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_17MouseClicked(evt);
            }
        });
        add(da2_device_17);
        da2_device_17.setBounds(20, 60, 320, 30);

        da2_device_18.setFont(da2_device_18.getFont().deriveFont(da2_device_18.getFont().getStyle() | java.awt.Font.BOLD, da2_device_18.getFont().getSize()+6));
        da2_device_18.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_18.setText(bundle.getString("CrInstrument.da2_device_18.text")); 
        da2_device_18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_18MouseClicked(evt);
            }
        });
        add(da2_device_18);
        da2_device_18.setBounds(20, 60, 320, 30);

        da2_device_19.setFont(da2_device_19.getFont().deriveFont(da2_device_19.getFont().getStyle() | java.awt.Font.BOLD, da2_device_19.getFont().getSize()+6));
        da2_device_19.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_19.setText(bundle.getString("CrInstrument.da2_device_19.text")); 
        da2_device_19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_19MouseClicked(evt);
            }
        });
        add(da2_device_19);
        da2_device_19.setBounds(20, 60, 320, 30);

        da2_device_20.setFont(da2_device_20.getFont().deriveFont(da2_device_20.getFont().getStyle() | java.awt.Font.BOLD, da2_device_20.getFont().getSize()+6));
        da2_device_20.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_20.setText(bundle.getString("CrInstrument.da2_device_20.text")); 
        da2_device_20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_20MouseClicked(evt);
            }
        });
        add(da2_device_20);
        da2_device_20.setBounds(20, 60, 320, 30);

        da2_device_21.setFont(da2_device_21.getFont().deriveFont(da2_device_21.getFont().getStyle() | java.awt.Font.BOLD, da2_device_21.getFont().getSize()+6));
        da2_device_21.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_21.setText(bundle.getString("CrInstrument.da2_device_21.text")); 
        da2_device_21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_21MouseClicked(evt);
            }
        });
        add(da2_device_21);
        da2_device_21.setBounds(20, 60, 320, 30);

        da2_device_22.setFont(da2_device_22.getFont().deriveFont(da2_device_22.getFont().getStyle() | java.awt.Font.BOLD, da2_device_22.getFont().getSize()+6));
        da2_device_22.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_22.setText(bundle.getString("CrInstrument.da2_device_22.text")); 
        da2_device_22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_22MouseClicked(evt);
            }
        });
        add(da2_device_22);
        da2_device_22.setBounds(20, 60, 320, 30);

        da2_device_23.setFont(da2_device_23.getFont().deriveFont(da2_device_23.getFont().getStyle() | java.awt.Font.BOLD, da2_device_23.getFont().getSize()+6));
        da2_device_23.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_23.setText(bundle.getString("CrInstrument.da2_device_23.text")); 
        da2_device_23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_23MouseClicked(evt);
            }
        });
        add(da2_device_23);
        da2_device_23.setBounds(20, 60, 320, 30);

        da2_device_24.setFont(da2_device_24.getFont().deriveFont(da2_device_24.getFont().getStyle() | java.awt.Font.BOLD, da2_device_24.getFont().getSize()+6));
        da2_device_24.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_24.setText(bundle.getString("CrInstrument.da2_device_24.text")); 
        da2_device_24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_24MouseClicked(evt);
            }
        });
        add(da2_device_24);
        da2_device_24.setBounds(20, 60, 320, 30);

        da2_device_25.setFont(da2_device_25.getFont().deriveFont(da2_device_25.getFont().getStyle() | java.awt.Font.BOLD, da2_device_25.getFont().getSize()+6));
        da2_device_25.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_25.setText(bundle.getString("CrInstrument.da2_device_25.text")); 
        da2_device_25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_25MouseClicked(evt);
            }
        });
        add(da2_device_25);
        da2_device_25.setBounds(20, 60, 320, 30);

        da2_device_26.setFont(da2_device_26.getFont().deriveFont(da2_device_26.getFont().getStyle() | java.awt.Font.BOLD, da2_device_26.getFont().getSize()+6));
        da2_device_26.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_26.setText(bundle.getString("CrInstrument.da2_device_26.text")); 
        da2_device_26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_26MouseClicked(evt);
            }
        });
        add(da2_device_26);
        da2_device_26.setBounds(20, 60, 320, 30);

        da2_device_27.setFont(da2_device_27.getFont().deriveFont(da2_device_27.getFont().getStyle() | java.awt.Font.BOLD, da2_device_27.getFont().getSize()+6));
        da2_device_27.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_27.setText(bundle.getString("CrInstrument.da2_device_27.text")); 
        da2_device_27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_27MouseClicked(evt);
            }
        });
        add(da2_device_27);
        da2_device_27.setBounds(20, 60, 320, 30);

        da2_device_28.setFont(da2_device_28.getFont().deriveFont(da2_device_28.getFont().getStyle() | java.awt.Font.BOLD, da2_device_28.getFont().getSize()+6));
        da2_device_28.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_28.setText(bundle.getString("CrInstrument.da2_device_28.text")); 
        da2_device_28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_28MouseClicked(evt);
            }
        });
        add(da2_device_28);
        da2_device_28.setBounds(20, 60, 320, 30);

        da2_device_29.setFont(da2_device_29.getFont().deriveFont(da2_device_29.getFont().getStyle() | java.awt.Font.BOLD, da2_device_29.getFont().getSize()+6));
        da2_device_29.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_29.setText(bundle.getString("CrInstrument.da2_device_29.text")); 
        da2_device_29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_29MouseClicked(evt);
            }
        });
        add(da2_device_29);
        da2_device_29.setBounds(20, 60, 320, 30);

        da2_device_30.setFont(da2_device_30.getFont().deriveFont(da2_device_30.getFont().getStyle() | java.awt.Font.BOLD, da2_device_30.getFont().getSize()+6));
        da2_device_30.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_30.setText(bundle.getString("CrInstrument.da2_device_30.text")); 
        da2_device_30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_30MouseClicked(evt);
            }
        });
        add(da2_device_30);
        da2_device_30.setBounds(20, 60, 320, 30);

        da2_device_31.setFont(da2_device_31.getFont().deriveFont(da2_device_31.getFont().getStyle() | java.awt.Font.BOLD, da2_device_31.getFont().getSize()+6));
        da2_device_31.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_31.setText(bundle.getString("CrInstrument.da2_device_31.text")); 
        da2_device_31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_31MouseClicked(evt);
            }
        });
        add(da2_device_31);
        da2_device_31.setBounds(20, 60, 320, 30);

        da2_device_32.setFont(da2_device_32.getFont().deriveFont(da2_device_32.getFont().getStyle() | java.awt.Font.BOLD, da2_device_32.getFont().getSize()+6));
        da2_device_32.setForeground(new java.awt.Color(0, 255, 102));
        da2_device_32.setText(bundle.getString("CrInstrument.da2_device_32.text")); 
        da2_device_32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_device_32MouseClicked(evt);
            }
        });
        add(da2_device_32);
        da2_device_32.setBounds(20, 60, 320, 30);

        da2_dataname_02.setFont(da2_dataname_02.getFont().deriveFont(da2_dataname_02.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_02.getFont().getSize()+6));
        da2_dataname_02.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_02.setText(bundle.getString("CrInstrument.da2_dataname_02.text")); 
        da2_dataname_02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_02MouseClicked(evt);
            }
        });
        add(da2_dataname_02);
        da2_dataname_02.setBounds(20, 110, 320, 30);

        da2_dataname_03.setFont(da2_dataname_03.getFont().deriveFont(da2_dataname_03.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_03.getFont().getSize()+6));
        da2_dataname_03.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_03.setText(bundle.getString("CrInstrument.da2_dataname_03.text")); 
        da2_dataname_03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_03MouseClicked(evt);
            }
        });
        add(da2_dataname_03);
        da2_dataname_03.setBounds(20, 110, 320, 30);

        da2_dataname_04.setFont(da2_dataname_04.getFont().deriveFont(da2_dataname_04.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_04.getFont().getSize()+6));
        da2_dataname_04.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_04.setText(bundle.getString("CrInstrument.da2_dataname_04.text")); 
        da2_dataname_04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_04MouseClicked(evt);
            }
        });
        add(da2_dataname_04);
        da2_dataname_04.setBounds(20, 110, 320, 30);

        da2_dataname_05.setFont(da2_dataname_05.getFont().deriveFont(da2_dataname_05.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_05.getFont().getSize()+6));
        da2_dataname_05.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_05.setText(bundle.getString("CrInstrument.da2_dataname_05.text")); 
        da2_dataname_05.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_05MouseClicked(evt);
            }
        });
        add(da2_dataname_05);
        da2_dataname_05.setBounds(20, 110, 320, 30);

        da2_dataname_06.setFont(da2_dataname_06.getFont().deriveFont(da2_dataname_06.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_06.getFont().getSize()+6));
        da2_dataname_06.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_06.setText(bundle.getString("CrInstrument.da2_dataname_06.text")); 
        da2_dataname_06.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_06MouseClicked(evt);
            }
        });
        add(da2_dataname_06);
        da2_dataname_06.setBounds(20, 110, 320, 30);

        da2_dataname_07.setFont(da2_dataname_07.getFont().deriveFont(da2_dataname_07.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_07.getFont().getSize()+6));
        da2_dataname_07.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_07.setText(bundle.getString("CrInstrument.da2_dataname_07.text")); 
        da2_dataname_07.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_07MouseClicked(evt);
            }
        });
        add(da2_dataname_07);
        da2_dataname_07.setBounds(20, 110, 320, 30);

        da2_dataname_08.setFont(da2_dataname_08.getFont().deriveFont(da2_dataname_08.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_08.getFont().getSize()+6));
        da2_dataname_08.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_08.setText(bundle.getString("CrInstrument.da2_dataname_08.text")); 
        da2_dataname_08.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_08MouseClicked(evt);
            }
        });
        add(da2_dataname_08);
        da2_dataname_08.setBounds(20, 110, 320, 30);

        da2_dataname_09.setFont(da2_dataname_09.getFont().deriveFont(da2_dataname_09.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_09.getFont().getSize()+6));
        da2_dataname_09.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_09.setText(bundle.getString("CrInstrument.da2_dataname_09.text")); 
        da2_dataname_09.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_09MouseClicked(evt);
            }
        });
        add(da2_dataname_09);
        da2_dataname_09.setBounds(20, 110, 320, 30);

        da2_dataname_10.setFont(da2_dataname_10.getFont().deriveFont(da2_dataname_10.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_10.getFont().getSize()+6));
        da2_dataname_10.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_10.setText(bundle.getString("CrInstrument.da2_dataname_10.text")); 
        da2_dataname_10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_10MouseClicked(evt);
            }
        });
        add(da2_dataname_10);
        da2_dataname_10.setBounds(20, 110, 320, 30);

        da2_dataname_11.setFont(da2_dataname_11.getFont().deriveFont(da2_dataname_11.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_11.getFont().getSize()+6));
        da2_dataname_11.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_11.setText(bundle.getString("CrInstrument.da2_dataname_11.text")); 
        da2_dataname_11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_11MouseClicked(evt);
            }
        });
        add(da2_dataname_11);
        da2_dataname_11.setBounds(20, 110, 320, 30);

        da2_dataname_12.setFont(da2_dataname_12.getFont().deriveFont(da2_dataname_12.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_12.getFont().getSize()+6));
        da2_dataname_12.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_12.setText(bundle.getString("CrInstrument.da2_dataname_12.text")); 
        da2_dataname_12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_12MouseClicked(evt);
            }
        });
        add(da2_dataname_12);
        da2_dataname_12.setBounds(20, 110, 320, 30);

        da2_dataname_13.setFont(da2_dataname_13.getFont().deriveFont(da2_dataname_13.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_13.getFont().getSize()+6));
        da2_dataname_13.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_13.setText(bundle.getString("CrInstrument.da2_dataname_13.text")); 
        da2_dataname_13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_13MouseClicked(evt);
            }
        });
        add(da2_dataname_13);
        da2_dataname_13.setBounds(20, 110, 320, 30);

        da2_dataname_14.setFont(da2_dataname_14.getFont().deriveFont(da2_dataname_14.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_14.getFont().getSize()+6));
        da2_dataname_14.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_14.setText(bundle.getString("CrInstrument.da2_dataname_14.text")); 
        da2_dataname_14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_14MouseClicked(evt);
            }
        });
        add(da2_dataname_14);
        da2_dataname_14.setBounds(20, 110, 320, 30);

        da2_dataname_15.setFont(da2_dataname_15.getFont().deriveFont(da2_dataname_15.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_15.getFont().getSize()+6));
        da2_dataname_15.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_15.setText(bundle.getString("CrInstrument.da2_dataname_15.text")); 
        da2_dataname_15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_15MouseClicked(evt);
            }
        });
        add(da2_dataname_15);
        da2_dataname_15.setBounds(20, 110, 320, 30);

        da2_dataname_16.setFont(da2_dataname_16.getFont().deriveFont(da2_dataname_16.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_16.getFont().getSize()+6));
        da2_dataname_16.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_16.setText(bundle.getString("CrInstrument.da2_dataname_16.text")); 
        da2_dataname_16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_16MouseClicked(evt);
            }
        });
        add(da2_dataname_16);
        da2_dataname_16.setBounds(20, 110, 320, 30);

        da2_dataname_17.setFont(da2_dataname_17.getFont().deriveFont(da2_dataname_17.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_17.getFont().getSize()+6));
        da2_dataname_17.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_17.setText(bundle.getString("CrInstrument.da2_dataname_17.text")); 
        da2_dataname_17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_17MouseClicked(evt);
            }
        });
        add(da2_dataname_17);
        da2_dataname_17.setBounds(20, 110, 320, 30);

        da2_dataname_18.setFont(da2_dataname_18.getFont().deriveFont(da2_dataname_18.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_18.getFont().getSize()+6));
        da2_dataname_18.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_18.setText(bundle.getString("CrInstrument.da2_dataname_18.text")); 
        da2_dataname_18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_18MouseClicked(evt);
            }
        });
        add(da2_dataname_18);
        da2_dataname_18.setBounds(20, 110, 320, 30);

        da2_dataname_19.setFont(da2_dataname_19.getFont().deriveFont(da2_dataname_19.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_19.getFont().getSize()+6));
        da2_dataname_19.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_19.setText(bundle.getString("CrInstrument.da2_dataname_19.text")); 
        da2_dataname_19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_19MouseClicked(evt);
            }
        });
        add(da2_dataname_19);
        da2_dataname_19.setBounds(20, 110, 320, 30);

        da2_dataname_20.setFont(da2_dataname_20.getFont().deriveFont(da2_dataname_20.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_20.getFont().getSize()+6));
        da2_dataname_20.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_20.setText(bundle.getString("CrInstrument.da2_dataname_20.text")); 
        da2_dataname_20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_20MouseClicked(evt);
            }
        });
        add(da2_dataname_20);
        da2_dataname_20.setBounds(20, 110, 320, 30);

        da2_dataname_21.setFont(da2_dataname_21.getFont().deriveFont(da2_dataname_21.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_21.getFont().getSize()+6));
        da2_dataname_21.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_21.setText(bundle.getString("CrInstrument.da2_dataname_21.text")); 
        da2_dataname_21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_21MouseClicked(evt);
            }
        });
        add(da2_dataname_21);
        da2_dataname_21.setBounds(20, 110, 320, 30);

        da2_dataname_22.setFont(da2_dataname_22.getFont().deriveFont(da2_dataname_22.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_22.getFont().getSize()+6));
        da2_dataname_22.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_22.setText(bundle.getString("CrInstrument.da2_dataname_22.text")); 
        da2_dataname_22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_22MouseClicked(evt);
            }
        });
        add(da2_dataname_22);
        da2_dataname_22.setBounds(20, 110, 320, 30);

        da2_dataname_23.setFont(da2_dataname_23.getFont().deriveFont(da2_dataname_23.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_23.getFont().getSize()+6));
        da2_dataname_23.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_23.setText(bundle.getString("CrInstrument.da2_dataname_23.text")); 
        da2_dataname_23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_23MouseClicked(evt);
            }
        });
        add(da2_dataname_23);
        da2_dataname_23.setBounds(20, 110, 320, 30);

        da2_dataname_24.setFont(da2_dataname_24.getFont().deriveFont(da2_dataname_24.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_24.getFont().getSize()+6));
        da2_dataname_24.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_24.setText(bundle.getString("CrInstrument.da2_dataname_24.text")); 
        da2_dataname_24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_24MouseClicked(evt);
            }
        });
        add(da2_dataname_24);
        da2_dataname_24.setBounds(20, 110, 320, 30);

        da2_dataname_25.setFont(da2_dataname_25.getFont().deriveFont(da2_dataname_25.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_25.getFont().getSize()+6));
        da2_dataname_25.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_25.setText(bundle.getString("CrInstrument.da2_dataname_25.text")); 
        da2_dataname_25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_25MouseClicked(evt);
            }
        });
        add(da2_dataname_25);
        da2_dataname_25.setBounds(20, 110, 320, 30);

        da2_dataname_26.setFont(da2_dataname_26.getFont().deriveFont(da2_dataname_26.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_26.getFont().getSize()+6));
        da2_dataname_26.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_26.setText(bundle.getString("CrInstrument.da2_dataname_26.text")); 
        da2_dataname_26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_26MouseClicked(evt);
            }
        });
        add(da2_dataname_26);
        da2_dataname_26.setBounds(20, 110, 320, 30);

        da2_dataname_27.setFont(da2_dataname_27.getFont().deriveFont(da2_dataname_27.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_27.getFont().getSize()+6));
        da2_dataname_27.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_27.setText(bundle.getString("CrInstrument.da2_dataname_27.text")); 
        da2_dataname_27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_27MouseClicked(evt);
            }
        });
        add(da2_dataname_27);
        da2_dataname_27.setBounds(20, 110, 320, 30);

        da2_dataname_28.setFont(da2_dataname_28.getFont().deriveFont(da2_dataname_28.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_28.getFont().getSize()+6));
        da2_dataname_28.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_28.setText(bundle.getString("CrInstrument.da2_dataname_28.text")); 
        da2_dataname_28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_28MouseClicked(evt);
            }
        });
        add(da2_dataname_28);
        da2_dataname_28.setBounds(20, 110, 320, 30);

        da2_dataname_29.setFont(da2_dataname_29.getFont().deriveFont(da2_dataname_29.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_29.getFont().getSize()+6));
        da2_dataname_29.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_29.setText(bundle.getString("CrInstrument.da2_dataname_29.text")); 
        da2_dataname_29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_29MouseClicked(evt);
            }
        });
        add(da2_dataname_29);
        da2_dataname_29.setBounds(20, 110, 320, 30);

        da2_dataname_30.setFont(da2_dataname_30.getFont().deriveFont(da2_dataname_30.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_30.getFont().getSize()+6));
        da2_dataname_30.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_30.setText(bundle.getString("CrInstrument.da2_dataname_30.text")); 
        da2_dataname_30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_30MouseClicked(evt);
            }
        });
        add(da2_dataname_30);
        da2_dataname_30.setBounds(20, 110, 320, 30);

        da2_dataname_31.setFont(da2_dataname_31.getFont().deriveFont(da2_dataname_31.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_31.getFont().getSize()+6));
        da2_dataname_31.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_31.setText(bundle.getString("CrInstrument.da2_dataname_31.text")); 
        da2_dataname_31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_31MouseClicked(evt);
            }
        });
        add(da2_dataname_31);
        da2_dataname_31.setBounds(20, 110, 320, 30);

        da2_dataname_32.setFont(da2_dataname_32.getFont().deriveFont(da2_dataname_32.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_32.getFont().getSize()+6));
        da2_dataname_32.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_32.setText(bundle.getString("CrInstrument.da2_dataname_32.text")); 
        da2_dataname_32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_32MouseClicked(evt);
            }
        });
        add(da2_dataname_32);
        da2_dataname_32.setBounds(20, 110, 320, 30);

        da2_dataname_33.setFont(da2_dataname_33.getFont().deriveFont(da2_dataname_33.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_33.getFont().getSize()+6));
        da2_dataname_33.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_33.setText(bundle.getString("CrInstrument.da2_dataname_33.text")); 
        da2_dataname_33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_33MouseClicked(evt);
            }
        });
        add(da2_dataname_33);
        da2_dataname_33.setBounds(20, 110, 320, 30);

        da2_dataname_34.setFont(da2_dataname_34.getFont().deriveFont(da2_dataname_34.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_34.getFont().getSize()+6));
        da2_dataname_34.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_34.setText(bundle.getString("CrInstrument.da2_dataname_34.text")); 
        da2_dataname_34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_34MouseClicked(evt);
            }
        });
        add(da2_dataname_34);
        da2_dataname_34.setBounds(20, 110, 320, 30);

        da2_dataname_35.setFont(da2_dataname_35.getFont().deriveFont(da2_dataname_35.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_35.getFont().getSize()+6));
        da2_dataname_35.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_35.setText(bundle.getString("CrInstrument.da2_dataname_35.text")); 
        da2_dataname_35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_35MouseClicked(evt);
            }
        });
        add(da2_dataname_35);
        da2_dataname_35.setBounds(20, 110, 320, 30);

        da2_dataname_36.setFont(da2_dataname_36.getFont().deriveFont(da2_dataname_36.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_36.getFont().getSize()+6));
        da2_dataname_36.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_36.setText(bundle.getString("CrInstrument.da2_dataname_36.text")); 
        da2_dataname_36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_36MouseClicked(evt);
            }
        });
        add(da2_dataname_36);
        da2_dataname_36.setBounds(20, 110, 320, 30);

        da2_dataname_37.setFont(da2_dataname_37.getFont().deriveFont(da2_dataname_37.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_37.getFont().getSize()+6));
        da2_dataname_37.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_37.setText(bundle.getString("CrInstrument.da2_dataname_37.text")); 
        da2_dataname_37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_37MouseClicked(evt);
            }
        });
        add(da2_dataname_37);
        da2_dataname_37.setBounds(20, 110, 320, 30);

        da2_dataname_38.setFont(da2_dataname_38.getFont().deriveFont(da2_dataname_38.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_38.getFont().getSize()+6));
        da2_dataname_38.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_38.setText(bundle.getString("CrInstrument.da2_dataname_38.text")); 
        da2_dataname_38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_38MouseClicked(evt);
            }
        });
        add(da2_dataname_38);
        da2_dataname_38.setBounds(20, 110, 320, 30);

        da2_dataname_39.setFont(da2_dataname_39.getFont().deriveFont(da2_dataname_39.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_39.getFont().getSize()+6));
        da2_dataname_39.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_39.setText(bundle.getString("CrInstrument.da2_dataname_39.text")); 
        da2_dataname_39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_39MouseClicked(evt);
            }
        });
        add(da2_dataname_39);
        da2_dataname_39.setBounds(20, 110, 320, 30);

        da2_dataname_40.setFont(da2_dataname_40.getFont().deriveFont(da2_dataname_40.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_40.getFont().getSize()+6));
        da2_dataname_40.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_40.setText(bundle.getString("CrInstrument.da2_dataname_40.text")); 
        da2_dataname_40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_40MouseClicked(evt);
            }
        });
        add(da2_dataname_40);
        da2_dataname_40.setBounds(20, 110, 320, 30);

        da2_dataname_41.setFont(da2_dataname_41.getFont().deriveFont(da2_dataname_41.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_41.getFont().getSize()+6));
        da2_dataname_41.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_41.setText(bundle.getString("CrInstrument.da2_dataname_41.text")); 
        da2_dataname_41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_41MouseClicked(evt);
            }
        });
        add(da2_dataname_41);
        da2_dataname_41.setBounds(20, 110, 320, 30);

        da2_dataname_42.setFont(da2_dataname_42.getFont().deriveFont(da2_dataname_42.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_42.getFont().getSize()+6));
        da2_dataname_42.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_42.setText(bundle.getString("CrInstrument.da2_dataname_42.text")); 
        da2_dataname_42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_42MouseClicked(evt);
            }
        });
        add(da2_dataname_42);
        da2_dataname_42.setBounds(20, 110, 320, 30);

        da2_dataname_43.setFont(da2_dataname_43.getFont().deriveFont(da2_dataname_43.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_43.getFont().getSize()+6));
        da2_dataname_43.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_43.setText(bundle.getString("CrInstrument.da2_dataname_43.text")); 
        da2_dataname_43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_43MouseClicked(evt);
            }
        });
        add(da2_dataname_43);
        da2_dataname_43.setBounds(20, 110, 320, 30);

        da2_dataname_44.setFont(da2_dataname_44.getFont().deriveFont(da2_dataname_44.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_44.getFont().getSize()+6));
        da2_dataname_44.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_44.setText(bundle.getString("CrInstrument.da2_dataname_44.text")); 
        da2_dataname_44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_44MouseClicked(evt);
            }
        });
        add(da2_dataname_44);
        da2_dataname_44.setBounds(20, 110, 320, 30);

        da2_dataname_45.setFont(da2_dataname_45.getFont().deriveFont(da2_dataname_45.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_45.getFont().getSize()+6));
        da2_dataname_45.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_45.setText(bundle.getString("CrInstrument.da2_dataname_45.text")); 
        da2_dataname_45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_45MouseClicked(evt);
            }
        });
        add(da2_dataname_45);
        da2_dataname_45.setBounds(20, 110, 320, 30);

        da2_dataname_46.setFont(da2_dataname_46.getFont().deriveFont(da2_dataname_46.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_46.getFont().getSize()+6));
        da2_dataname_46.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_46.setText(bundle.getString("CrInstrument.da2_dataname_46.text")); 
        da2_dataname_46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_46MouseClicked(evt);
            }
        });
        add(da2_dataname_46);
        da2_dataname_46.setBounds(20, 110, 320, 30);

        da2_dataname_47.setFont(da2_dataname_47.getFont().deriveFont(da2_dataname_47.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_47.getFont().getSize()+6));
        da2_dataname_47.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_47.setText(bundle.getString("CrInstrument.da2_dataname_47.text")); 
        da2_dataname_47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_47MouseClicked(evt);
            }
        });
        add(da2_dataname_47);
        da2_dataname_47.setBounds(20, 110, 320, 30);

        da2_dataname_48.setFont(da2_dataname_48.getFont().deriveFont(da2_dataname_48.getFont().getStyle() | java.awt.Font.BOLD, da2_dataname_48.getFont().getSize()+6));
        da2_dataname_48.setForeground(new java.awt.Color(255, 255, 51));
        da2_dataname_48.setText(bundle.getString("CrInstrument.da2_dataname_48.text")); 
        da2_dataname_48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_dataname_48MouseClicked(evt);
            }
        });
        add(da2_dataname_48);
        da2_dataname_48.setBounds(20, 110, 320, 30);

        da2_datavalue_02.setFont(da2_datavalue_02.getFont().deriveFont(da2_datavalue_02.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_02.getFont().getSize()+36));
        da2_datavalue_02.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_02.setText(bundle.getString("CrInstrument.da2_datavalue_02.text")); 
        da2_datavalue_02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_02MouseClicked(evt);
            }
        });
        add(da2_datavalue_02);
        da2_datavalue_02.setBounds(60, 150, 300, 60);

        da2_datavalue_03.setFont(da2_datavalue_03.getFont().deriveFont(da2_datavalue_03.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_03.getFont().getSize()+36));
        da2_datavalue_03.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_03.setText(bundle.getString("CrInstrument.da2_datavalue_03.text")); 
        da2_datavalue_03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_03MouseClicked(evt);
            }
        });
        add(da2_datavalue_03);
        da2_datavalue_03.setBounds(60, 150, 300, 60);

        da2_datavalue_04.setFont(da2_datavalue_04.getFont().deriveFont(da2_datavalue_04.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_04.getFont().getSize()+36));
        da2_datavalue_04.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_04.setText(bundle.getString("CrInstrument.da2_datavalue_04.text")); 
        da2_datavalue_04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_04MouseClicked(evt);
            }
        });
        add(da2_datavalue_04);
        da2_datavalue_04.setBounds(60, 150, 300, 60);

        da2_datavalue_05.setFont(da2_datavalue_05.getFont().deriveFont(da2_datavalue_05.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_05.getFont().getSize()+36));
        da2_datavalue_05.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_05.setText(bundle.getString("CrInstrument.da2_datavalue_05.text")); 
        da2_datavalue_05.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_05MouseClicked(evt);
            }
        });
        add(da2_datavalue_05);
        da2_datavalue_05.setBounds(60, 150, 300, 60);

        da2_datavalue_06.setFont(da2_datavalue_06.getFont().deriveFont(da2_datavalue_06.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_06.getFont().getSize()+36));
        da2_datavalue_06.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_06.setText(bundle.getString("CrInstrument.da2_datavalue_06.text")); 
        da2_datavalue_06.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_06MouseClicked(evt);
            }
        });
        add(da2_datavalue_06);
        da2_datavalue_06.setBounds(60, 150, 300, 60);

        da2_datavalue_07.setFont(da2_datavalue_07.getFont().deriveFont(da2_datavalue_07.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_07.getFont().getSize()+36));
        da2_datavalue_07.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_07.setText(bundle.getString("CrInstrument.da2_datavalue_07.text")); 
        da2_datavalue_07.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_07MouseClicked(evt);
            }
        });
        add(da2_datavalue_07);
        da2_datavalue_07.setBounds(60, 150, 300, 60);

        da2_datavalue_08.setFont(da2_datavalue_08.getFont().deriveFont(da2_datavalue_08.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_08.getFont().getSize()+36));
        da2_datavalue_08.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_08.setText(bundle.getString("CrInstrument.da2_datavalue_08.text")); 
        da2_datavalue_08.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_08MouseClicked(evt);
            }
        });
        add(da2_datavalue_08);
        da2_datavalue_08.setBounds(60, 150, 300, 60);

        da2_datavalue_09.setFont(da2_datavalue_09.getFont().deriveFont(da2_datavalue_09.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_09.getFont().getSize()+36));
        da2_datavalue_09.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_09.setText(bundle.getString("CrInstrument.da2_datavalue_09.text")); 
        da2_datavalue_09.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_09MouseClicked(evt);
            }
        });
        add(da2_datavalue_09);
        da2_datavalue_09.setBounds(60, 150, 300, 60);

        da2_datavalue_10.setFont(da2_datavalue_10.getFont().deriveFont(da2_datavalue_10.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_10.getFont().getSize()+36));
        da2_datavalue_10.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_10.setText(bundle.getString("CrInstrument.da2_datavalue_10.text")); 
        da2_datavalue_10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_10MouseClicked(evt);
            }
        });
        add(da2_datavalue_10);
        da2_datavalue_10.setBounds(60, 150, 300, 60);

        da2_datavalue_11.setFont(da2_datavalue_11.getFont().deriveFont(da2_datavalue_11.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_11.getFont().getSize()+36));
        da2_datavalue_11.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_11.setText(bundle.getString("CrInstrument.da2_datavalue_11.text")); 
        da2_datavalue_11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_11MouseClicked(evt);
            }
        });
        add(da2_datavalue_11);
        da2_datavalue_11.setBounds(60, 150, 300, 60);

        da2_datavalue_12.setFont(da2_datavalue_12.getFont().deriveFont(da2_datavalue_12.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_12.getFont().getSize()+36));
        da2_datavalue_12.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_12.setText(bundle.getString("CrInstrument.da2_datavalue_12.text")); 
        da2_datavalue_12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_12MouseClicked(evt);
            }
        });
        add(da2_datavalue_12);
        da2_datavalue_12.setBounds(60, 150, 300, 60);

        da2_datavalue_13.setFont(da2_datavalue_13.getFont().deriveFont(da2_datavalue_13.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_13.getFont().getSize()+36));
        da2_datavalue_13.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_13.setText(bundle.getString("CrInstrument.da2_datavalue_13.text")); 
        da2_datavalue_13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_13MouseClicked(evt);
            }
        });
        add(da2_datavalue_13);
        da2_datavalue_13.setBounds(60, 150, 300, 60);

        da2_datavalue_14.setFont(da2_datavalue_14.getFont().deriveFont(da2_datavalue_14.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_14.getFont().getSize()+36));
        da2_datavalue_14.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_14.setText(bundle.getString("CrInstrument.da2_datavalue_14.text")); 
        da2_datavalue_14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_14MouseClicked(evt);
            }
        });
        add(da2_datavalue_14);
        da2_datavalue_14.setBounds(60, 150, 300, 60);

        da2_datavalue_15.setFont(da2_datavalue_15.getFont().deriveFont(da2_datavalue_15.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_15.getFont().getSize()+36));
        da2_datavalue_15.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_15.setText(bundle.getString("CrInstrument.da2_datavalue_15.text")); 
        da2_datavalue_15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_15MouseClicked(evt);
            }
        });
        add(da2_datavalue_15);
        da2_datavalue_15.setBounds(60, 150, 300, 60);

        da2_datavalue_16.setFont(da2_datavalue_16.getFont().deriveFont(da2_datavalue_16.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_16.getFont().getSize()+36));
        da2_datavalue_16.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_16.setText(bundle.getString("CrInstrument.da2_datavalue_16.text")); 
        da2_datavalue_16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_16MouseClicked(evt);
            }
        });
        add(da2_datavalue_16);
        da2_datavalue_16.setBounds(60, 150, 300, 60);

        da2_datavalue_17.setFont(da2_datavalue_17.getFont().deriveFont(da2_datavalue_17.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_17.getFont().getSize()+36));
        da2_datavalue_17.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_17.setText(bundle.getString("CrInstrument.da2_datavalue_17.text")); 
        da2_datavalue_17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_17MouseClicked(evt);
            }
        });
        add(da2_datavalue_17);
        da2_datavalue_17.setBounds(60, 150, 300, 60);

        da2_datavalue_18.setFont(da2_datavalue_18.getFont().deriveFont(da2_datavalue_18.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_18.getFont().getSize()+36));
        da2_datavalue_18.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_18.setText(bundle.getString("CrInstrument.da2_datavalue_18.text")); 
        da2_datavalue_18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_18MouseClicked(evt);
            }
        });
        add(da2_datavalue_18);
        da2_datavalue_18.setBounds(60, 150, 300, 60);

        da2_datavalue_19.setFont(da2_datavalue_19.getFont().deriveFont(da2_datavalue_19.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_19.getFont().getSize()+36));
        da2_datavalue_19.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_19.setText(bundle.getString("CrInstrument.da2_datavalue_19.text")); 
        da2_datavalue_19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_19MouseClicked(evt);
            }
        });
        add(da2_datavalue_19);
        da2_datavalue_19.setBounds(60, 150, 300, 60);

        da2_datavalue_20.setFont(da2_datavalue_20.getFont().deriveFont(da2_datavalue_20.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_20.getFont().getSize()+36));
        da2_datavalue_20.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_20.setText(bundle.getString("CrInstrument.da2_datavalue_20.text")); 
        da2_datavalue_20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_20MouseClicked(evt);
            }
        });
        add(da2_datavalue_20);
        da2_datavalue_20.setBounds(60, 150, 300, 60);

        da2_datavalue_21.setFont(da2_datavalue_21.getFont().deriveFont(da2_datavalue_21.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_21.getFont().getSize()+36));
        da2_datavalue_21.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_21.setText(bundle.getString("CrInstrument.da2_datavalue_21.text")); 
        da2_datavalue_21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_21MouseClicked(evt);
            }
        });
        add(da2_datavalue_21);
        da2_datavalue_21.setBounds(60, 150, 300, 60);

        da2_datavalue_22.setFont(da2_datavalue_22.getFont().deriveFont(da2_datavalue_22.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_22.getFont().getSize()+36));
        da2_datavalue_22.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_22.setText(bundle.getString("CrInstrument.da2_datavalue_22.text")); 
        da2_datavalue_22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_22MouseClicked(evt);
            }
        });
        add(da2_datavalue_22);
        da2_datavalue_22.setBounds(60, 150, 300, 60);

        da2_datavalue_23.setFont(da2_datavalue_23.getFont().deriveFont(da2_datavalue_23.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_23.getFont().getSize()+36));
        da2_datavalue_23.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_23.setText(bundle.getString("CrInstrument.da2_datavalue_23.text")); 
        da2_datavalue_23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_23MouseClicked(evt);
            }
        });
        add(da2_datavalue_23);
        da2_datavalue_23.setBounds(60, 150, 300, 60);

        da2_datavalue_24.setFont(da2_datavalue_24.getFont().deriveFont(da2_datavalue_24.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_24.getFont().getSize()+36));
        da2_datavalue_24.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_24.setText(bundle.getString("CrInstrument.da2_datavalue_24.text")); 
        da2_datavalue_24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_24MouseClicked(evt);
            }
        });
        add(da2_datavalue_24);
        da2_datavalue_24.setBounds(60, 150, 300, 60);

        da2_datavalue_25.setFont(da2_datavalue_25.getFont().deriveFont(da2_datavalue_25.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_25.getFont().getSize()+36));
        da2_datavalue_25.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_25.setText(bundle.getString("CrInstrument.da2_datavalue_25.text")); 
        da2_datavalue_25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_25MouseClicked(evt);
            }
        });
        add(da2_datavalue_25);
        da2_datavalue_25.setBounds(60, 150, 300, 60);

        da2_datavalue_26.setFont(da2_datavalue_26.getFont().deriveFont(da2_datavalue_26.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_26.getFont().getSize()+36));
        da2_datavalue_26.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_26.setText(bundle.getString("CrInstrument.da2_datavalue_26.text")); 
        da2_datavalue_26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_26MouseClicked(evt);
            }
        });
        add(da2_datavalue_26);
        da2_datavalue_26.setBounds(60, 150, 300, 60);

        da2_datavalue_27.setFont(da2_datavalue_27.getFont().deriveFont(da2_datavalue_27.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_27.getFont().getSize()+36));
        da2_datavalue_27.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_27.setText(bundle.getString("CrInstrument.da2_datavalue_27.text")); 
        da2_datavalue_27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_27MouseClicked(evt);
            }
        });
        add(da2_datavalue_27);
        da2_datavalue_27.setBounds(60, 150, 300, 60);

        da2_datavalue_28.setFont(da2_datavalue_28.getFont().deriveFont(da2_datavalue_28.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_28.getFont().getSize()+36));
        da2_datavalue_28.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_28.setText(bundle.getString("CrInstrument.da2_datavalue_28.text")); 
        da2_datavalue_28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_28MouseClicked(evt);
            }
        });
        add(da2_datavalue_28);
        da2_datavalue_28.setBounds(60, 150, 300, 60);

        da2_datavalue_29.setFont(da2_datavalue_29.getFont().deriveFont(da2_datavalue_29.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_29.getFont().getSize()+36));
        da2_datavalue_29.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_29.setText(bundle.getString("CrInstrument.da2_datavalue_29.text")); 
        da2_datavalue_29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_29MouseClicked(evt);
            }
        });
        add(da2_datavalue_29);
        da2_datavalue_29.setBounds(60, 150, 300, 60);

        da2_datavalue_30.setFont(da2_datavalue_30.getFont().deriveFont(da2_datavalue_30.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_30.getFont().getSize()+36));
        da2_datavalue_30.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_30.setText(bundle.getString("CrInstrument.da2_datavalue_30.text")); 
        da2_datavalue_30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_30MouseClicked(evt);
            }
        });
        add(da2_datavalue_30);
        da2_datavalue_30.setBounds(60, 150, 300, 60);

        da2_datavalue_31.setFont(da2_datavalue_31.getFont().deriveFont(da2_datavalue_31.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_31.getFont().getSize()+36));
        da2_datavalue_31.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_31.setText(bundle.getString("CrInstrument.da2_datavalue_31.text")); 
        da2_datavalue_31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_31MouseClicked(evt);
            }
        });
        add(da2_datavalue_31);
        da2_datavalue_31.setBounds(60, 150, 300, 60);

        da2_datavalue_32.setFont(da2_datavalue_32.getFont().deriveFont(da2_datavalue_32.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_32.getFont().getSize()+36));
        da2_datavalue_32.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_32.setText(bundle.getString("CrInstrument.da2_datavalue_32.text")); 
        da2_datavalue_32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_32MouseClicked(evt);
            }
        });
        add(da2_datavalue_32);
        da2_datavalue_32.setBounds(60, 150, 300, 60);

        da2_datavalue_33.setFont(da2_datavalue_33.getFont().deriveFont(da2_datavalue_33.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_33.getFont().getSize()+36));
        da2_datavalue_33.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_33.setText(bundle.getString("CrInstrument.da2_datavalue_33.text")); 
        da2_datavalue_33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_33MouseClicked(evt);
            }
        });
        add(da2_datavalue_33);
        da2_datavalue_33.setBounds(60, 150, 300, 60);

        da2_datavalue_34.setFont(da2_datavalue_34.getFont().deriveFont(da2_datavalue_34.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_34.getFont().getSize()+36));
        da2_datavalue_34.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_34.setText(bundle.getString("CrInstrument.da2_datavalue_34.text")); 
        da2_datavalue_34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_34MouseClicked(evt);
            }
        });
        add(da2_datavalue_34);
        da2_datavalue_34.setBounds(60, 150, 300, 60);

        da2_datavalue_35.setFont(da2_datavalue_35.getFont().deriveFont(da2_datavalue_35.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_35.getFont().getSize()+36));
        da2_datavalue_35.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_35.setText(bundle.getString("CrInstrument.da2_datavalue_35.text")); 
        da2_datavalue_35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_35MouseClicked(evt);
            }
        });
        add(da2_datavalue_35);
        da2_datavalue_35.setBounds(60, 150, 300, 60);

        da2_datavalue_36.setFont(da2_datavalue_36.getFont().deriveFont(da2_datavalue_36.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_36.getFont().getSize()+36));
        da2_datavalue_36.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_36.setText(bundle.getString("CrInstrument.da2_datavalue_36.text")); 
        da2_datavalue_36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_36MouseClicked(evt);
            }
        });
        add(da2_datavalue_36);
        da2_datavalue_36.setBounds(60, 150, 300, 60);

        da2_datavalue_37.setFont(da2_datavalue_37.getFont().deriveFont(da2_datavalue_37.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_37.getFont().getSize()+36));
        da2_datavalue_37.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_37.setText(bundle.getString("CrInstrument.da2_datavalue_37.text")); 
        da2_datavalue_37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_37MouseClicked(evt);
            }
        });
        add(da2_datavalue_37);
        da2_datavalue_37.setBounds(60, 150, 300, 60);

        da2_datavalue_38.setFont(da2_datavalue_38.getFont().deriveFont(da2_datavalue_38.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_38.getFont().getSize()+36));
        da2_datavalue_38.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_38.setText(bundle.getString("CrInstrument.da2_datavalue_38.text")); 
        da2_datavalue_38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_38MouseClicked(evt);
            }
        });
        add(da2_datavalue_38);
        da2_datavalue_38.setBounds(60, 150, 300, 60);

        da2_datavalue_39.setFont(da2_datavalue_39.getFont().deriveFont(da2_datavalue_39.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_39.getFont().getSize()+36));
        da2_datavalue_39.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_39.setText(bundle.getString("CrInstrument.da2_datavalue_39.text")); 
        da2_datavalue_39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_39MouseClicked(evt);
            }
        });
        add(da2_datavalue_39);
        da2_datavalue_39.setBounds(60, 150, 300, 60);

        da2_datavalue_40.setFont(da2_datavalue_40.getFont().deriveFont(da2_datavalue_40.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_40.getFont().getSize()+36));
        da2_datavalue_40.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_40.setText(bundle.getString("CrInstrument.da2_datavalue_40.text")); 
        da2_datavalue_40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_40MouseClicked(evt);
            }
        });
        add(da2_datavalue_40);
        da2_datavalue_40.setBounds(60, 150, 300, 60);

        da2_datavalue_41.setFont(da2_datavalue_41.getFont().deriveFont(da2_datavalue_41.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_41.getFont().getSize()+36));
        da2_datavalue_41.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_41.setText(bundle.getString("CrInstrument.da2_datavalue_41.text")); 
        da2_datavalue_41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_41MouseClicked(evt);
            }
        });
        add(da2_datavalue_41);
        da2_datavalue_41.setBounds(60, 150, 300, 60);

        da2_datavalue_42.setFont(da2_datavalue_42.getFont().deriveFont(da2_datavalue_42.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_42.getFont().getSize()+36));
        da2_datavalue_42.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_42.setText(bundle.getString("CrInstrument.da2_datavalue_42.text")); 
        da2_datavalue_42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_42MouseClicked(evt);
            }
        });
        add(da2_datavalue_42);
        da2_datavalue_42.setBounds(60, 150, 300, 60);

        da2_datavalue_43.setFont(da2_datavalue_43.getFont().deriveFont(da2_datavalue_43.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_43.getFont().getSize()+36));
        da2_datavalue_43.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_43.setText(bundle.getString("CrInstrument.da2_datavalue_43.text")); 
        da2_datavalue_43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_43MouseClicked(evt);
            }
        });
        add(da2_datavalue_43);
        da2_datavalue_43.setBounds(60, 150, 300, 60);

        da2_datavalue_44.setFont(da2_datavalue_44.getFont().deriveFont(da2_datavalue_44.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_44.getFont().getSize()+36));
        da2_datavalue_44.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_44.setText(bundle.getString("CrInstrument.da2_datavalue_44.text")); 
        da2_datavalue_44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_44MouseClicked(evt);
            }
        });
        add(da2_datavalue_44);
        da2_datavalue_44.setBounds(60, 150, 300, 60);

        da2_datavalue_45.setFont(da2_datavalue_45.getFont().deriveFont(da2_datavalue_45.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_45.getFont().getSize()+36));
        da2_datavalue_45.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_45.setText(bundle.getString("CrInstrument.da2_datavalue_45.text")); 
        da2_datavalue_45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_45MouseClicked(evt);
            }
        });
        add(da2_datavalue_45);
        da2_datavalue_45.setBounds(60, 150, 300, 60);

        da2_datavalue_46.setFont(da2_datavalue_46.getFont().deriveFont(da2_datavalue_46.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_46.getFont().getSize()+36));
        da2_datavalue_46.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_46.setText(bundle.getString("CrInstrument.da2_datavalue_46.text")); 
        da2_datavalue_46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_46MouseClicked(evt);
            }
        });
        add(da2_datavalue_46);
        da2_datavalue_46.setBounds(60, 150, 300, 60);

        da2_datavalue_47.setFont(da2_datavalue_47.getFont().deriveFont(da2_datavalue_47.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_47.getFont().getSize()+36));
        da2_datavalue_47.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_47.setText(bundle.getString("CrInstrument.da2_datavalue_47.text")); 
        da2_datavalue_47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_47MouseClicked(evt);
            }
        });
        add(da2_datavalue_47);
        da2_datavalue_47.setBounds(60, 150, 300, 60);

        da2_datavalue_48.setFont(da2_datavalue_48.getFont().deriveFont(da2_datavalue_48.getFont().getStyle() | java.awt.Font.BOLD, da2_datavalue_48.getFont().getSize()+36));
        da2_datavalue_48.setForeground(new java.awt.Color(255, 0, 51));
        da2_datavalue_48.setText(bundle.getString("CrInstrument.da2_datavalue_48.text")); 
        da2_datavalue_48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                da2_datavalue_48MouseClicked(evt);
            }
        });
        add(da2_datavalue_48);
        da2_datavalue_48.setBounds(60, 150, 300, 60);
    }

    private void da2_datavalue_01MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 01");
    }

    private void da2_datavalue_02MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 02");
    }

    private void da2_datavalue_03MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 03");
    }

    private void da2_datavalue_04MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 04");
    }

    private void da2_datavalue_05MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 05");
    }

    private void da2_datavalue_06MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 06");
    }

    private void da2_datavalue_07MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 07");
    }

    private void da2_datavalue_08MouseClicked(java.awt.event.MouseEvent evt) {
     instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 08");
    }

    private void da2_datavalue_09MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 09");
    }

    private void da2_datavalue_10MouseClicked(java.awt.event.MouseEvent evt) {
     instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 10");
    }

    private void da2_datavalue_11MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 11");
    }

    private void da2_datavalue_12MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 12");
    }

    private void da2_datavalue_13MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 13");
    }

    private void da2_datavalue_14MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 14");
    }

    private void da2_datavalue_15MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 15");
    }

    private void da2_datavalue_16MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 16");
    }

    private void da2_datavalue_17MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 17");
    }

    private void da2_datavalue_18MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 18");
    }

    private void da2_datavalue_19MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 19");
    }

    private void da2_datavalue_20MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 20");
    }

    private void da2_datavalue_21MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 21");
    }

    private void da2_datavalue_22MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 22");
    }

    private void da2_datavalue_23MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 23");
    }

    private void da2_datavalue_24MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 24");
    }

    private void da2_datavalue_25MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 25");
    }

    private void da2_datavalue_26MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 26");
    }

    private void da2_datavalue_27MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 27");
    }

    private void da2_datavalue_28MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 28");
    }

    private void da2_datavalue_29MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 29");
    }

    private void da2_datavalue_30MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 30");
    }

    private void da2_datavalue_31MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 31");
    }

    private void da2_datavalue_32MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 32");
    }

    private void da2_datavalue_33MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 33");
    }

    private void da2_datavalue_34MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 34");
    }

    private void da2_datavalue_35MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 35");
    }

    private void da2_datavalue_36MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 36");
    }

    private void da2_datavalue_37MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 37");
    }

    private void da2_datavalue_38MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 38");
    }

    private void da2_datavalue_39MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 39");
    }

    private void da2_datavalue_40MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 40");
    }

    private void da2_datavalue_41MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 41");
    }

    private void da2_datavalue_42MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 42");
    }

    private void da2_datavalue_43MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 43");
    }

    private void da2_datavalue_44MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 44");
    }

    private void da2_datavalue_45MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 45");
    }

    private void da2_datavalue_46MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 46");
    }

    private void da2_datavalue_47MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 47");
    }

    private void da2_datavalue_48MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_datavalue 48");
    }

    private void da2_device_01MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 01");
    }

    private void da2_dataname_01MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 01");
    }

    private void da2_station_01MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_station 01");
    }

    private void da2_station_02MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_station 02");
    }

    private void da2_station_03MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_station 03");
    }

    private void da2_station_04MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_station 04");
    }

    private void da2_station_05MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_station 05");
    }

    private void da2_station_06MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_station 06");
    }

    private void da2_station_07MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_station 07");
    }

    private void da2_station_08MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_station 08");
    }

    private void da2_station_09MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_station 09");
    }

    private void da2_station_10MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_station 10");
    }

    private void da2_station_11MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_station 11");
    }

    private void da2_station_12MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_station 12");
    }

    private void da2_station_13MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_station 13");
    }

    private void da2_station_14MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_station 14");
    }

    private void da2_station_15MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_station 15");
    }

    private void da2_station_16MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_station 16");
    }

    private void da2_device_02MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 02");
    }

    private void da2_device_03MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 03");
    }

    private void da2_device_04MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 04");
    }

    private void da2_device_05MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 05");
    }

    private void da2_device_06MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 06");
    }

    private void da2_device_07MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 07");
    }

    private void da2_device_08MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 08");
    }

    private void da2_device_09MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 09");
    }

    private void da2_device_10MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 10");
    }

    private void da2_device_11MouseClicked(java.awt.event.MouseEvent evt) {
         instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 11");
    }

    private void da2_device_12MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 12");
    }

    private void da2_device_13MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 13");
    }

    private void da2_device_14MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 14");
    }

    private void da2_device_15MouseClicked(java.awt.event.MouseEvent evt) {
         instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 15");
    }

    private void da2_device_16MouseClicked(java.awt.event.MouseEvent evt) {
         instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 16");
    }

    private void da2_device_17MouseClicked(java.awt.event.MouseEvent evt) {
         instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 17");
    }

    private void da2_device_18MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 18");
    }

    private void da2_device_19MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 19");
    }

    private void da2_device_20MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 20");
    }

    private void da2_device_21MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 21");
    }

    private void da2_device_22MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 22");
    }

    private void da2_device_23MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 23");
    }

    private void da2_device_24MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 24");
    }

    private void da2_device_25MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 25");
    }

    private void da2_device_26MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 26");
    }

    private void da2_device_27MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 27");
    }

    private void da2_device_28MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 28");
    }

    private void da2_device_29MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 29");
    }

    private void da2_device_30MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 30");
    }

    private void da2_device_31MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 31");
    }

    private void da2_device_32MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_device 32");
    }

    private void da2_dataname_02MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 02");
    }

    private void da2_dataname_03MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 03");
    }

    private void da2_dataname_04MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 04");
    }

    private void da2_dataname_05MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 05");
    }

    private void da2_dataname_06MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 06");
    }

    private void da2_dataname_07MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 07");
    }

    private void da2_dataname_08MouseClicked(java.awt.event.MouseEvent evt) {
     instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 08");
    }

    private void da2_dataname_09MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 09");
    }

    private void da2_dataname_10MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 10");
    }

    private void da2_dataname_11MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 11");
    }

    private void da2_dataname_12MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 12");
    }

    private void da2_dataname_13MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 13");
    }

    private void da2_dataname_14MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 14");
    }

    private void da2_dataname_15MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 15");
    }

    private void da2_dataname_16MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 16");
    }

    private void da2_dataname_17MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 17");
    }

    private void da2_dataname_18MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 18");
    }

    private void da2_dataname_19MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 19");
    }

    private void da2_dataname_20MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 20");
    }

    private void da2_dataname_21MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 21");
    }

    private void da2_dataname_22MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 22");
    }

    private void da2_dataname_23MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 23");
    }

    private void da2_dataname_24MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 24");
    }

    private void da2_dataname_25MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 25");
    }

    private void da2_dataname_26MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 26");
    }

    private void da2_dataname_27MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 27");
    }

    private void da2_dataname_28MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 28");
    }

    private void da2_dataname_29MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 29");
    }

    private void da2_dataname_30MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 30");
    }

    private void da2_dataname_31MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 31");
    }

    private void da2_dataname_32MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 32");
    }

    private void da2_dataname_33MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 33");
    }

    private void da2_dataname_34MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 34");
    }

    private void da2_dataname_35MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 35");
    }

    private void da2_dataname_36MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 36");
    }

    private void da2_dataname_37MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 37");
    }

    private void da2_dataname_38MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 38");
    }

    private void da2_dataname_39MouseClicked(java.awt.event.MouseEvent evt) {
      instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 39");
    }

    private void da2_dataname_40MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 40");
    }

    private void da2_dataname_41MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 41");
    }

    private void da2_dataname_42MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 42");
    }

    private void da2_dataname_43MouseClicked(java.awt.event.MouseEvent evt) {
        instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 43");
    }

    private void da2_dataname_44MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 44");
    }

    private void da2_dataname_45MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 45");
    }

    private void da2_dataname_46MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 46");
    }

    private void da2_dataname_47MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 47");
    }

    private void da2_dataname_48MouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("da_dataname 48");
    }

    private void formMouseClicked(java.awt.event.MouseEvent evt) {
       instrument.uiPanel2.jComboBox15.setSelectedItem("");
       instrument.selectedDataItem="";
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

}