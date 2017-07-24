
package ci;

import static ci.CrInstrument.isNumeric;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.ResourceBundle;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import y.ylib.ylib;

/**
 *
 * @author peter
 */
public class CIUIPanel extends javax.swing.JPanel {

   CrInstrument instrument;
       KeyboardFocusManager kfm;
   public ResourceBundle bundle2 = java.util.ResourceBundle.getBundle("ci/Bundle");
    public CIUIPanel(CrInstrument instrument) {
        initComponents();
        this.instrument=instrument;
        init();
                    kfm= KeyboardFocusManager.getCurrentKeyboardFocusManager();
        kfm.addKeyEventPostProcessor(new KeyEventPostProcessor() {
            public boolean postProcessKeyEvent(KeyEvent evt) {
                if (evt.getID() == KeyEvent.KEY_PRESSED) {
                     keyPress(evt);
                } else if  (evt.getID() == KeyEvent.KEY_RELEASED){
                     keyRelease(evt);
                    }
                return true;
            }
        });
    }
void init(){
    

    jPanel140.add(instrument.framePanel, BorderLayout.CENTER);
    jPanel162.add(instrument.dataPanel2,BorderLayout.CENTER);

}
private void keyPress(java.awt.event.KeyEvent evt){

  if(evt.getModifiers()==2){
      if(instrument.jTabbedPane1.getSelectedComponent().equals(instrument.jPanel3)){
          if(instrument.jTabbedPane3.getSelectedComponent().equals(instrument.uiPanel)){
              if(this.jTabbedPane4.getSelectedComponent().equals(this.mainPanel)){
                  if(instrument.selectedUIAreaItem.length()>0){
                      int size[]=getFrameSizeFromEditUI();
                      String info[]=null;
                      int frameWidth=size[0],frameHeight=size[1],x=0,y=0;
                      switch(evt.getKeyCode()){
                          case 38:
                              y=(instrument.isNumeric(jTextField9.getText())? ((int)Math.round(Double.parseDouble(jTextField9.getText())*((double)frameHeight)/100.0))-1:0);
                              if(y<0) y=0;
                              jTextField9.setText(""+(((double)((int)(((double)y)/((double)frameHeight)*10000.0*100.0)))/10000.0));
                               if(instrument.editUI.get(instrument.selectedUIAreaItem)!=null){
                                 info=ylib.csvlinetoarray((String)instrument.editUI.get(instrument.selectedUIAreaItem));
                                 info[4]=String.valueOf(Double.parseDouble(jTextField9.getText())/100.0);
                                 instrument.editUI.put(instrument.selectedUIAreaItem, ylib.arrayToCsvLine(info));
                                 instrument.framePanel.invalidate();
                               }
                              break;
                          case 40:
                              y=(instrument.isNumeric(jTextField9.getText())? ((int)Math.round(Double.parseDouble(jTextField9.getText())*((double)frameHeight)/100.0))+1:0);
                              if(y>frameHeight) y=frameHeight;
                              jTextField9.setText(""+(((double)((int)(((double)y)/((double)frameHeight)*1000000.0*100.0)))/1000000.0));
                               if(instrument.editUI.get(instrument.selectedUIAreaItem)!=null){
                                 info=ylib.csvlinetoarray((String)instrument.editUI.get(instrument.selectedUIAreaItem));
                                 info[4]=String.valueOf(Double.parseDouble(jTextField9.getText())/100.0);
                                 instrument.editUI.put(instrument.selectedUIAreaItem, ylib.arrayToCsvLine(info));
                                 instrument.framePanel.invalidate();
                               }
                              break;
                          case 37:
                              x=(instrument.isNumeric(jTextField5.getText())? ((int)Math.round(Double.parseDouble(jTextField5.getText())*((double)frameWidth)/100.0))-1:0);
                              if(x<0) x=0;
                              jTextField5.setText(""+(((double)((int)(((double)x)/((double)frameWidth)*10000.0*100.0)))/10000.0));
                               if(instrument.editUI.get(instrument.selectedUIAreaItem)!=null){
                                 info=ylib.csvlinetoarray((String)instrument.editUI.get(instrument.selectedUIAreaItem));
                                 info[3]=String.valueOf(Double.parseDouble(jTextField5.getText())/100.0);
                                 instrument.editUI.put(instrument.selectedUIAreaItem, ylib.arrayToCsvLine(info));
                                 instrument.framePanel.invalidate();
                               }
                              break;
                          case 39:
                              x=(instrument.isNumeric(jTextField5.getText())? ((int)Math.round(Double.parseDouble(jTextField5.getText())*((double)frameWidth)/100.0))+1:0);
                              if(x>frameWidth) x=frameWidth;
                              jTextField5.setText(""+(((double)((int)(((double)x)/((double)frameWidth)*10000.0*100.0)))/10000.0));
                               if(instrument.editUI.get(instrument.selectedUIAreaItem)!=null){
                                 info=ylib.csvlinetoarray((String)instrument.editUI.get(instrument.selectedUIAreaItem));
                                 info[3]=String.valueOf(Double.parseDouble(jTextField5.getText())/100.0);
                                 instrument.editUI.put(instrument.selectedUIAreaItem, ylib.arrayToCsvLine(info));
                                 instrument.framePanel.invalidate();
                               }
                              break;
                      }
                  }
              }
              else if(this.jTabbedPane4.getSelectedComponent().equals(this.dataPanel)){
                  if(instrument.selectedDataItem.length()>0){
                      int size[]=getFrameSizeFromEditUI();
                      String info[]=null;
                      int frameWidth=size[0],frameHeight=size[1],x=0,y=0;
                      switch(evt.getKeyCode()){
                          case 38:
                              y=(instrument.isNumeric(jTextField85.getText())? ((int)Math.round(Double.parseDouble(jTextField85.getText())*((double)frameHeight)/100.0))-1:0);
                              if(y<0) y=0;
                              jTextField85.setText(""+(((double)((int)(((double)y)/((double)frameHeight)*10000.0*100.0)))/10000.0));
                               if(instrument.editUI.get(instrument.selectedDataItem)!=null){
                                 info=ylib.csvlinetoarray((String)instrument.editUI.get(instrument.selectedDataItem));
                                 info[4]=String.valueOf(Double.parseDouble(jTextField85.getText())/100.0);
                                 instrument.editUI.put(instrument.selectedDataItem, ylib.arrayToCsvLine(info));

                                 instrument.dataPanel2.invalidate();
                               }
                              break;
                          case 40:
                              y=(instrument.isNumeric(jTextField85.getText())? ((int)Math.round(Double.parseDouble(jTextField85.getText())*((double)frameHeight)/100.0))+1:0);
                              if(y>frameHeight) y=frameHeight;
                              jTextField85.setText(""+(((double)((int)(((double)y)/((double)frameHeight)*1000000.0*100.0)))/1000000.0));
                               if(instrument.editUI.get(instrument.selectedDataItem)!=null){
                                 info=ylib.csvlinetoarray((String)instrument.editUI.get(instrument.selectedDataItem));
                                 info[4]=String.valueOf(Double.parseDouble(jTextField85.getText())/100.0);
                                 instrument.editUI.put(instrument.selectedDataItem, ylib.arrayToCsvLine(info));
                                 instrument.dataPanel2.invalidate();
                               }
                              break;
                          case 37:
                              x=(instrument.isNumeric(jTextField84.getText())? ((int)Math.round(Double.parseDouble(jTextField84.getText())*((double)frameWidth)/100.0))-1:0);
                              if(x<0) x=0;
                              jTextField84.setText(""+(((double)((int)(((double)x)/((double)frameWidth)*10000.0*100.0)))/10000.0));
                               if(instrument.editUI.get(instrument.selectedDataItem)!=null){
                                 info=ylib.csvlinetoarray((String)instrument.editUI.get(instrument.selectedDataItem));
                                 info[3]=String.valueOf(Double.parseDouble(jTextField84.getText())/100.0);
                                 instrument.editUI.put(instrument.selectedDataItem, ylib.arrayToCsvLine(info));
                                 instrument.dataPanel2.invalidate();
                               }
                              break;
                          case 39:
                              x=(instrument.isNumeric(jTextField84.getText())? ((int)Math.round(Double.parseDouble(jTextField84.getText())*((double)frameWidth)/100.0))+1:0);
                              if(x>frameWidth) x=frameWidth;
                              jTextField84.setText(""+(((double)((int)(((double)x)/((double)frameWidth)*10000.0*100.0)))/10000.0));
                               if(instrument.editUI.get(instrument.selectedDataItem)!=null){
                                 info=ylib.csvlinetoarray((String)instrument.editUI.get(instrument.selectedDataItem));
                                 info[3]=String.valueOf(Double.parseDouble(jTextField84.getText())/100.0);
                                 instrument.editUI.put(instrument.selectedDataItem, ylib.arrayToCsvLine(info));
                                 instrument.dataPanel2.invalidate();
                               }
                              break;
                      }
                  }
              }
          }
      }
  } else if(evt.getModifiers()==1){
      if(instrument.jTabbedPane1.getSelectedComponent().equals(instrument.jPanel3)){
          if(instrument.jTabbedPane3.getSelectedComponent().equals(instrument.uiPanel)){
              if(this.jTabbedPane4.getSelectedComponent().equals(this.mainPanel)){
                  if(instrument.selectedUIAreaItem.length()>0){
                      int size[]=getFrameSizeFromEditUI();
                      String info[]=null;
                      int frameWidth=size[0],frameHeight=size[1],w=0,h=0;
                      switch(evt.getKeyCode()){
                          case 38:
                              h=(instrument.isNumeric(jTextField61.getText())? ((int)Math.round(Double.parseDouble(jTextField61.getText())*((double)frameHeight)/100.0))-1:0);
                              if(h<1) h=1;
                              jTextField61.setText(""+(((double)((int)(((double)h)/((double)frameHeight)*10000.0*100.0)))/10000.0));
                               if(instrument.editUI.get(instrument.selectedUIAreaItem)!=null){
                                 info=ylib.csvlinetoarray((String)instrument.editUI.get(instrument.selectedUIAreaItem));
                                 info[6]=String.valueOf(Double.parseDouble(jTextField61.getText())/100.0);
                                 instrument.editUI.put(instrument.selectedUIAreaItem, ylib.arrayToCsvLine(info));
                                 instrument.framePanel.invalidate();
                               }
                              break;
                          case 40:
                              h=(instrument.isNumeric(jTextField61.getText())? ((int)Math.round(Double.parseDouble(jTextField61.getText())*((double)frameHeight)/100.0))+1:0);
                              if(h>frameHeight) h=frameHeight;
                              jTextField61.setText(""+(((double)((int)(((double)h)/((double)frameHeight)*1000000.0*100.0)))/1000000.0));
                               if(instrument.editUI.get(instrument.selectedUIAreaItem)!=null){
                                 info=ylib.csvlinetoarray((String)instrument.editUI.get(instrument.selectedUIAreaItem));
                                 info[6]=String.valueOf(Double.parseDouble(jTextField61.getText())/100.0);
                                 instrument.editUI.put(instrument.selectedUIAreaItem, ylib.arrayToCsvLine(info));
                                 instrument.framePanel.invalidate();
                               }
                              break;
                          case 37:
                              w=(instrument.isNumeric(jTextField10.getText())? ((int)Math.round(Double.parseDouble(jTextField10.getText())*((double)frameWidth)/100.0))-1:0);
                              if(w<0) w=0;
                              jTextField10.setText(""+(((double)((int)(((double)w)/((double)frameWidth)*10000.0*100.0)))/10000.0));
                               if(instrument.editUI.get(instrument.selectedUIAreaItem)!=null){
                                 info=ylib.csvlinetoarray((String)instrument.editUI.get(instrument.selectedUIAreaItem));
                                 info[5]=String.valueOf(Double.parseDouble(jTextField10.getText())/100.0);
                                 instrument.editUI.put(instrument.selectedUIAreaItem, ylib.arrayToCsvLine(info));
                                 instrument.framePanel.invalidate();
                               }
                              break;
                          case 39:
                              w=(instrument.isNumeric(jTextField10.getText())? ((int)Math.round(Double.parseDouble(jTextField10.getText())*((double)frameWidth)/100.0))+1:0);
                              if(w>frameWidth) w=frameWidth;
                              jTextField10.setText(""+(((double)((int)(((double)w)/((double)frameWidth)*10000.0*100.0)))/10000.0));
                               if(instrument.editUI.get(instrument.selectedUIAreaItem)!=null){
                                 info=ylib.csvlinetoarray((String)instrument.editUI.get(instrument.selectedUIAreaItem));
                                 info[5]=String.valueOf(Double.parseDouble(jTextField10.getText())/100.0);
                                 instrument.editUI.put(instrument.selectedUIAreaItem, ylib.arrayToCsvLine(info));
                                 instrument.framePanel.invalidate();
                               }
                              break;
                     }
                  }
              }
              else if(this.jTabbedPane4.getSelectedComponent().equals(this.dataPanel)){
                  if(instrument.selectedDataItem.length()>0){
                      int size[]=getFrameSizeFromEditUI();
                      String info[]=null;
                      int frameWidth=size[0],frameHeight=size[1],w=0,h=0;
                      switch(evt.getKeyCode()){
                          case 38:
                              h=(instrument.isNumeric(jTextField87.getText())? ((int)Math.round(Double.parseDouble(jTextField87.getText())*((double)frameHeight)/100.0))-1:0);
                              if(h<1) h=1;
                              jTextField87.setText(""+(((double)((int)(((double)h)/((double)frameHeight)*10000.0*100.0)))/10000.0));
                               if(instrument.editUI.get(instrument.selectedDataItem)!=null){
                                 info=ylib.csvlinetoarray((String)instrument.editUI.get(instrument.selectedDataItem));
                                 info[6]=String.valueOf(Double.parseDouble(jTextField87.getText())/100.0);
                                 instrument.editUI.put(instrument.selectedDataItem, ylib.arrayToCsvLine(info));
                                 instrument.dataPanel2.invalidate();
                               }
                              break;
                          case 40:
                              h=(instrument.isNumeric(jTextField87.getText())? ((int)Math.round(Double.parseDouble(jTextField87.getText())*((double)frameHeight)/100.0))+1:0);
                              if(h>frameHeight) h=frameHeight;
                              jTextField87.setText(""+(((double)((int)(((double)h)/((double)frameHeight)*1000000.0*100.0)))/1000000.0));
                               if(instrument.editUI.get(instrument.selectedDataItem)!=null){
                                 info=ylib.csvlinetoarray((String)instrument.editUI.get(instrument.selectedDataItem));
                                 info[6]=String.valueOf(Double.parseDouble(jTextField87.getText())/100.0);
                                 instrument.editUI.put(instrument.selectedDataItem, ylib.arrayToCsvLine(info));
                                 instrument.dataPanel2.invalidate();
                               }
                              break;
                          case 37:
                              w=(instrument.isNumeric(jTextField86.getText())? ((int)Math.round(Double.parseDouble(jTextField86.getText())*((double)frameWidth)/100.0))-1:0);
                              if(w<0) w=0;
                              jTextField86.setText(""+(((double)((int)(((double)w)/((double)frameWidth)*10000.0*100.0)))/10000.0));
                               if(instrument.editUI.get(instrument.selectedDataItem)!=null){
                                 info=ylib.csvlinetoarray((String)instrument.editUI.get(instrument.selectedDataItem));
                                 info[5]=String.valueOf(Double.parseDouble(jTextField86.getText())/100.0);
                                 instrument.editUI.put(instrument.selectedDataItem, ylib.arrayToCsvLine(info));
                                 instrument.dataPanel2.invalidate();
                               }
                              break;
                          case 39:
                              w=(instrument.isNumeric(jTextField86.getText())? ((int)Math.round(Double.parseDouble(jTextField86.getText())*((double)frameWidth)/100.0))+1:0);
                              if(w>frameWidth) w=frameWidth;
                              jTextField86.setText(""+(((double)((int)(((double)w)/((double)frameWidth)*10000.0*100.0)))/10000.0));
                               if(instrument.editUI.get(instrument.selectedDataItem)!=null){
                                 info=ylib.csvlinetoarray((String)instrument.editUI.get(instrument.selectedDataItem));
                                 info[5]=String.valueOf(Double.parseDouble(jTextField86.getText())/100.0);
                                 instrument.editUI.put(instrument.selectedDataItem, ylib.arrayToCsvLine(info));
                                 instrument.dataPanel2.invalidate();
                               }
                              break;
                     }
                  }
              }
          }
      }
  }
  else if(evt.getKeyCode()==27){
      instrument.soundThread.setStop(2);
  }
}
private void keyRelease(java.awt.event.KeyEvent evt){

}

void setFromUITM(){
    jComboBox36.removeAllItems();
    jComboBox36.addItem("");
    jComboBox15.removeAllItems();
    jComboBox15.addItem("");
    jComboBox21.removeAllItems();
    jComboBox21.addItem("");

     Iterator it=instrument.editUI.keySet().iterator();
     for(;it.hasNext();){
         String id=(String)it.next();
         if(id.toLowerCase().indexOf("area")!=-1 || id.toLowerCase().indexOf("button")!=-1){
            jComboBox36.addItem(id);
         }
         if(id.trim().toLowerCase().indexOf("da_")==0){
            jComboBox15.addItem(id);
         }
         if(id.trim().toLowerCase().indexOf("menuitem")!=-1){
            jComboBox21.addItem(id);
         }
      }

     instrument.skipUICBBChanged=true;
     jComboBox53.removeAllItems();
     jComboBox54.removeAllItems();
     jComboBox1.removeAllItems();
     jComboBox53.addItem("");
     jComboBox54.addItem("");
     jComboBox1.addItem("");
     jComboBox53.addItem(bundle2.getString("CrInstrument.xy.msg150"));
     jComboBox54.addItem(bundle2.getString("CrInstrument.xy.msg150"));
     jComboBox1.addItem(bundle2.getString("CrInstrument.xy.msg150"));
     String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    for ( int i = 0; i < fonts.length; i++ )  {
     jComboBox53.addItem(fonts[i]);
     jComboBox54.addItem(fonts[i]);
     jComboBox1.addItem(fonts[i]);
    }
    instrument.skipUICBBChanged=false;

    setFrameItem();
    setDataAreaItem();

    if(instrument.editUI.get("misc")!=null){
        String info[]=ylib.csvlinetoarray((String)instrument.editUI.get("misc"));
        if(info[1].trim().equalsIgnoreCase("station")) byStation.setSelected(true);
        else if(info[1].trim().equalsIgnoreCase("device")) byDeviceName.setSelected(true);
        else if(info[1].trim().equalsIgnoreCase("model")) byModel.setSelected(true);
        else if(info[1].trim().equalsIgnoreCase("dataname")) byDataName.setSelected(true);
        else if(info[1].trim().equalsIgnoreCase("chartgroup")) byChartGroup.setSelected(true);
        else  byDataName.setSelected(true);

        chartGroupCB.setSelectedItem(info[3]);
        if(info[2].trim().equalsIgnoreCase("station")) byStation1.setSelected(true);
        else if(info[2].trim().equalsIgnoreCase("device")) byDeviceName1.setSelected(true);
        else if(info[2].trim().equalsIgnoreCase("model")) byModel1.setSelected(true);
        else if(info[2].trim().equalsIgnoreCase("dataname")) byDataName1.setSelected(true);
        else if(info[2].trim().equalsIgnoreCase("showall")) showAll.setSelected(true);
        else  byDataName1.setSelected(true);
    }  else {byDataName.setSelected(true); byDataName1.setSelected(true);}
}
void setFrameItem(){
   if(instrument.editUI.get("frame")!=null){
        String info[]=ylib.csvlinetoarray((String)instrument.editUI.get("frame"));
        jTextField45.setText(info[1]);
        jTextField11.setText(info[9]);
        if(info[2].trim().equals("%")){
        jTextField74.setText((instrument.isNumeric(info[3])? String.valueOf(Double.parseDouble(info[3])*100.0):"0.0"));
        jTextField75.setText((instrument.isNumeric(info[4])? String.valueOf(Double.parseDouble(info[4])*100.0):"0.0"));
        jTextField76.setText((instrument.isNumeric(info[5])? String.valueOf(Double.parseDouble(info[5])*100.0):"0.0"));
        jTextField77.setText((instrument.isNumeric(info[6])? String.valueOf(Double.parseDouble(info[6])*100.0):"0.0"));
        } else {
        jTextField74.setText((instrument.isNumeric(info[3])? info[3]:"0"));
        jTextField75.setText((instrument.isNumeric(info[4])? info[4]:"0"));
        jTextField76.setText((instrument.isNumeric(info[5])? info[5]:"1024"));
        jTextField77.setText((instrument.isNumeric(info[6])? info[6]:"712"));
        }
        if(instrument.isNumeric(info[7])) jLabel61.setBackground(new Color(Integer.parseInt(info[7])));
                 else jLabel61.setBackground(Color.white);
        if(info[2].trim().equals("%")) jCheckBox38.setSelected(true); else jCheckBox38.setSelected(false);
        if(info[8].toLowerCase().trim().equals("c")) jCheckBox43.setSelected(true); else jCheckBox43.setSelected(false);
        if(info[13].toLowerCase().trim().equals("f")) jCheckBox44.setSelected(true); else jCheckBox44.setSelected(false);
        if(info[14].toLowerCase().trim().equals("r")) jCheckBox45.setSelected(true); else jCheckBox45.setSelected(false);
    }
}
void setDataAreaItem(){
   if(instrument.editUI.get("data area")!=null){
    String info[]=ylib.csvlinetoarray((String)instrument.editUI.get("data area"));

    jTextField6.setText((instrument.isNumeric(info[3])? String.valueOf(Double.parseDouble(info[3])*100.0):"0.0"));
    jTextField7.setText((instrument.isNumeric(info[4])? String.valueOf(Double.parseDouble(info[4])*100.0):"0.0"));
    jTextField1.setText((instrument.isNumeric(info[5])? String.valueOf(Double.parseDouble(info[5])*100.0):"0.0"));
    jTextField2.setText((instrument.isNumeric(info[6])? String.valueOf(Double.parseDouble(info[6])*100.0):"0.0"));
    jTextField4.setText(info[9]);
    if(info[11].equalsIgnoreCase("b")) jCheckBox2.setSelected(true); else jCheckBox2.setSelected(false);
    if(info[12].equalsIgnoreCase("i")) jCheckBox4.setSelected(true); else jCheckBox4.setSelected(false);
    if(instrument.isNumeric(info[7])) jLabel14.setBackground(new Color(Integer.parseInt(info[7])));
                 else jLabel14.setBackground(Color.white);
    instrument.skipUICBBChanged=true;
    jComboBox1.setSelectedItem(info[8]);
    instrument.skipUICBBChanged=false;
    if(instrument.isNumeric(info[10])) jLabel10.setBackground(new Color(Integer.parseInt(info[10])));
                 else jLabel10.setBackground(Color.white);
    if(instrument.isNumeric(info[13])) jLabel4.setBackground(new Color(Integer.parseInt(info[13])));
                 else jLabel4.setBackground(Color.white);
   }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        mainPanel = new javax.swing.JPanel();
        jPanel140 = new javax.swing.JPanel();
        jButton57 = new javax.swing.JButton();
        btnApplySetting_ui = new javax.swing.JButton();
        jPanel144 = new javax.swing.JPanel();
        jPanel141 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jTextField61 = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jPanel143 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jComboBox53 = new javax.swing.JComboBox();
        jLabel73 = new javax.swing.JLabel();
        jTextField73 = new javax.swing.JTextField();
        jLabel311 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox46 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jComboBox36 = new javax.swing.JComboBox();
        jCheckBox37 = new javax.swing.JCheckBox();
        jLabel31 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel145 = new javax.swing.JPanel();
        jPanel110 = new javax.swing.JPanel();
        jLabel147 = new javax.swing.JLabel();
        jTextField45 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jPanel146 = new javax.swing.JPanel();
        jCheckBox38 = new javax.swing.JCheckBox();
        jCheckBox44 = new javax.swing.JCheckBox();
        jCheckBox45 = new javax.swing.JCheckBox();
        jPanel147 = new javax.swing.JPanel();
        jLabel97 = new javax.swing.JLabel();
        jCheckBox43 = new javax.swing.JCheckBox();
        jLabel105 = new javax.swing.JLabel();
        jTextField74 = new javax.swing.JTextField();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jTextField75 = new javax.swing.JTextField();
        jLabel139 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jTextField76 = new javax.swing.JTextField();
        jLabel156 = new javax.swing.JLabel();
        jLabel141 = new javax.swing.JLabel();
        jTextField77 = new javax.swing.JTextField();
        jLabel154 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        dataPanel = new javax.swing.JPanel();
        jButton18 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jPanel162 = new javax.swing.JPanel();
        jPanel163 = new javax.swing.JPanel();
        jPanel164 = new javax.swing.JPanel();
        jLabel318 = new javax.swing.JLabel();
        jTextField84 = new javax.swing.JTextField();
        jLabel319 = new javax.swing.JLabel();
        jTextField85 = new javax.swing.JTextField();
        jLabel320 = new javax.swing.JLabel();
        jTextField86 = new javax.swing.JTextField();
        jLabel321 = new javax.swing.JLabel();
        jTextField87 = new javax.swing.JTextField();
        jLabel322 = new javax.swing.JLabel();
        jPanel165 = new javax.swing.JPanel();
        jLabel323 = new javax.swing.JLabel();
        jLabel324 = new javax.swing.JLabel();
        jLabel325 = new javax.swing.JLabel();
        jComboBox54 = new javax.swing.JComboBox();
        jLabel326 = new javax.swing.JLabel();
        jTextField88 = new javax.swing.JTextField();
        jLabel327 = new javax.swing.JLabel();
        jLabel328 = new javax.swing.JLabel();
        jCheckBox65 = new javax.swing.JCheckBox();
        jCheckBox66 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jComboBox15 = new javax.swing.JComboBox();
        jCheckBox64 = new javax.swing.JCheckBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        miscPanel = new javax.swing.JPanel();
        jPanel123 = new javax.swing.JPanel();
        jLabel137 = new javax.swing.JLabel();
        byStation = new javax.swing.JRadioButton();
        byDeviceName = new javax.swing.JRadioButton();
        byModel = new javax.swing.JRadioButton();
        byDataName = new javax.swing.JRadioButton();
        byChartGroup = new javax.swing.JRadioButton();
        chartGroupCB = new javax.swing.JComboBox();
        jPanel166 = new javax.swing.JPanel();
        jButton25 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jPanel167 = new javax.swing.JPanel();
        jLabel168 = new javax.swing.JLabel();
        byStation1 = new javax.swing.JRadioButton();
        byDeviceName1 = new javax.swing.JRadioButton();
        byModel1 = new javax.swing.JRadioButton();
        byDevice1 = new javax.swing.JRadioButton();
        byDataName1 = new javax.swing.JRadioButton();
        showAll = new javax.swing.JRadioButton();
        jPanel32 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jComboBox21 = new javax.swing.JComboBox();
        jLabel30 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jCheckBox49 = new javax.swing.JCheckBox();

        setLayout(new java.awt.BorderLayout());

        jTabbedPane4.setFont(jTabbedPane4.getFont());

        mainPanel.setFont(mainPanel.getFont());
        mainPanel.setLayout(null);

        jPanel140.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        jPanel140.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel140KeyPressed(evt);
            }
        });
        jPanel140.setLayout(new java.awt.BorderLayout());
        mainPanel.add(jPanel140);
        jPanel140.setBounds(340, 20, 460, 250);

        jButton57.setFont(jButton57.getFont().deriveFont(jButton57.getFont().getSize()+12f));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ci/Bundle"); 
        jButton57.setText(bundle.getString("CrInstrument.jButton57.text")); 
        jButton57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton57ActionPerformed(evt);
            }
        });
        mainPanel.add(jButton57);
        jButton57.setBounds(20, 30, 260, 60);

        btnApplySetting_ui.setFont(btnApplySetting_ui.getFont().deriveFont(btnApplySetting_ui.getFont().getSize()+12f));
        btnApplySetting_ui.setText(bundle.getString("CrInstrument.btnApplySetting_ui.text")); 
        btnApplySetting_ui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplySetting_uiActionPerformed(evt);
            }
        });
        mainPanel.add(btnApplySetting_ui);
        btnApplySetting_ui.setBounds(20, 170, 260, 60);

        jPanel144.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)), bundle.getString("CrInstrument.jPanel144.border.title"))); 
        jPanel144.setFont(jPanel144.getFont());
        jPanel144.setLayout(null);

        jPanel141.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel46.setFont(jLabel46.getFont());
        jLabel46.setText(bundle.getString("CrInstrument.jLabel46.text")); 
        jPanel141.add(jLabel46);

        jTextField5.setText(bundle.getString("CrInstrument.jTextField5.text")); 
        jTextField5.setPreferredSize(new java.awt.Dimension(76, 25));
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField5FocusLost(evt);
            }
        });
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jPanel141.add(jTextField5);

        jLabel47.setText(bundle.getString("CrInstrument.jLabel47.text")); 
        jPanel141.add(jLabel47);

        jTextField9.setText(bundle.getString("CrInstrument.jTextField9.text")); 
        jTextField9.setPreferredSize(new java.awt.Dimension(76, 25));
        jTextField9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField9FocusLost(evt);
            }
        });
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });
        jPanel141.add(jTextField9);

        jLabel54.setFont(jLabel54.getFont());
        jLabel54.setText(bundle.getString("CrInstrument.jLabel54.text")); 
        jPanel141.add(jLabel54);

        jTextField10.setText(bundle.getString("CrInstrument.jTextField10.text")); 
        jTextField10.setPreferredSize(new java.awt.Dimension(76, 25));
        jTextField10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField10FocusLost(evt);
            }
        });
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });
        jPanel141.add(jTextField10);

        jLabel55.setFont(jLabel55.getFont());
        jLabel55.setText(bundle.getString("CrInstrument.jLabel55.text")); 
        jPanel141.add(jLabel55);

        jTextField61.setText(bundle.getString("CrInstrument.jTextField61.text")); 
        jTextField61.setPreferredSize(new java.awt.Dimension(76, 25));
        jTextField61.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField61FocusLost(evt);
            }
        });
        jTextField61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField61ActionPerformed(evt);
            }
        });
        jPanel141.add(jTextField61);

        jLabel56.setText(bundle.getString("CrInstrument.jLabel56.text")); 
        jPanel141.add(jLabel56);

        jPanel144.add(jPanel141);
        jPanel141.setBounds(20, 60, 910, 35);

        jPanel143.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel57.setFont(jLabel57.getFont());
        jLabel57.setText(bundle.getString("CrInstrument.jLabel57.text")); 
        jPanel143.add(jLabel57);

        jLabel63.setBackground(new java.awt.Color(255, 0, 102));
        jLabel63.setText(bundle.getString("CrInstrument.jLabel63.text")); 
        jLabel63.setOpaque(true);
        jLabel63.setPreferredSize(new java.awt.Dimension(40, 25));
        jLabel63.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel63MouseClicked(evt);
            }
        });
        jPanel143.add(jLabel63);

        jLabel72.setFont(jLabel72.getFont());
        jLabel72.setText(bundle.getString("CrInstrument.jLabel72.text")); 
        jPanel143.add(jLabel72);

        jComboBox53.setEditable(true);
        jComboBox53.setFont(jComboBox53.getFont());
        jComboBox53.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox53.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox53ItemStateChanged(evt);
            }
        });
        jPanel143.add(jComboBox53);

        jLabel73.setFont(jLabel73.getFont());
        jLabel73.setText(bundle.getString("CrInstrument.jLabel73.text")); 
        jPanel143.add(jLabel73);

        jTextField73.setText(bundle.getString("CrInstrument.jTextField73.text")); 
        jTextField73.setPreferredSize(new java.awt.Dimension(56, 21));
        jTextField73.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField73FocusLost(evt);
            }
        });
        jTextField73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField73ActionPerformed(evt);
            }
        });
        jPanel143.add(jTextField73);

        jLabel311.setFont(jLabel311.getFont());
        jLabel311.setText(bundle.getString("CrInstrument.jLabel311.text")); 
        jPanel143.add(jLabel311);

        jLabel62.setBackground(new java.awt.Color(255, 102, 102));
        jLabel62.setText(bundle.getString("CrInstrument.jLabel62.text")); 
        jLabel62.setOpaque(true);
        jLabel62.setPreferredSize(new java.awt.Dimension(40, 25));
        jLabel62.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel62MouseClicked(evt);
            }
        });
        jPanel143.add(jLabel62);

        jCheckBox3.setFont(jCheckBox3.getFont());
        jCheckBox3.setText(bundle.getString("CrInstrument.jCheckBox3.text")); 
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });
        jPanel143.add(jCheckBox3);

        jCheckBox46.setFont(jCheckBox46.getFont());
        jCheckBox46.setText(bundle.getString("CrInstrument.jCheckBox46.text")); 
        jCheckBox46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox46ActionPerformed(evt);
            }
        });
        jPanel143.add(jCheckBox46);

        jPanel144.add(jPanel143);
        jPanel143.setBounds(20, 95, 870, 35);

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jComboBox36.setFont(jComboBox36.getFont());
        jComboBox36.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox36.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox36ItemStateChanged(evt);
            }
        });
        jPanel1.add(jComboBox36);

        jCheckBox37.setFont(jCheckBox37.getFont());
        jCheckBox37.setText(bundle.getString("CrInstrument.jCheckBox37.text")); 
        jCheckBox37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox37ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox37);

        jLabel31.setFont(jLabel31.getFont());
        jLabel31.setText(bundle.getString("CrInstrument.jLabel31.text")); 
        jPanel1.add(jLabel31);

        jTextField3.setText(bundle.getString("CrInstrument.jTextField3.text")); 
        jTextField3.setPreferredSize(new java.awt.Dimension(156, 25));
        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField3FocusLost(evt);
            }
        });
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField3);

        jLabel1.setText(bundle.getString("CIUIPanel.jLabel1.text")); 
        jPanel1.add(jLabel1);

        jLabel2.setBackground(new java.awt.Color(255, 0, 153));
        jLabel2.setText(bundle.getString("CIUIPanel.jLabel2.text")); 
        jLabel2.setOpaque(true);
        jLabel2.setPreferredSize(new java.awt.Dimension(40, 25));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2);

        jPanel144.add(jPanel1);
        jPanel1.setBounds(10, 20, 890, 35);

        mainPanel.add(jPanel144);
        jPanel144.setBounds(30, 410, 940, 140);

        jPanel145.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204)), bundle.getString("CrInstrument.jPanel145.border.title"))); 
        jPanel145.setFont(jPanel145.getFont());
        jPanel145.setLayout(null);

        jPanel110.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel147.setFont(jLabel147.getFont());
        jLabel147.setText(bundle.getString("CrInstrument.jLabel147.text")); 
        jPanel110.add(jLabel147);

        jTextField45.setText(bundle.getString("CrInstrument.jTextField45.text")); 
        jTextField45.setPreferredSize(new java.awt.Dimension(306, 21));
        jTextField45.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField45FocusLost(evt);
            }
        });
        jTextField45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField45ActionPerformed(evt);
            }
        });
        jPanel110.add(jTextField45);

        jLabel8.setText(bundle.getString("CrInstrument.jLabel8.text")); 
        jPanel110.add(jLabel8);

        jTextField11.setText(bundle.getString("CrInstrument.jTextField11.text")); 
        jTextField11.setPreferredSize(new java.awt.Dimension(106, 21));
        jTextField11.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField11FocusLost(evt);
            }
        });
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });
        jPanel110.add(jTextField11);

        jLabel75.setFont(jLabel75.getFont());
        jLabel75.setText(bundle.getString("CrInstrument.jLabel75.text")); 
        jPanel110.add(jLabel75);

        jLabel61.setBackground(new java.awt.Color(255, 51, 153));
        jLabel61.setText(bundle.getString("CrInstrument.jLabel61.text")); 
        jLabel61.setOpaque(true);
        jLabel61.setPreferredSize(new java.awt.Dimension(40, 25));
        jLabel61.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel61MouseClicked(evt);
            }
        });
        jPanel110.add(jLabel61);

        jPanel145.add(jPanel110);
        jPanel110.setBounds(20, 20, 890, 30);

        jPanel146.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox38.setFont(jCheckBox38.getFont());
        jCheckBox38.setText(bundle.getString("CrInstrument.jCheckBox38.text")); 
        jCheckBox38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox38ActionPerformed(evt);
            }
        });
        jPanel146.add(jCheckBox38);

        jCheckBox44.setFont(jCheckBox44.getFont());
        jCheckBox44.setText(bundle.getString("CrInstrument.jCheckBox44.text")); 
        jCheckBox44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox44ActionPerformed(evt);
            }
        });
        jPanel146.add(jCheckBox44);

        jCheckBox45.setFont(jCheckBox45.getFont());
        jCheckBox45.setText(bundle.getString("CrInstrument.jCheckBox45.text")); 
        jCheckBox45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox45ActionPerformed(evt);
            }
        });
        jPanel146.add(jCheckBox45);

        jPanel145.add(jPanel146);
        jPanel146.setBounds(20, 50, 890, 30);

        jPanel147.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel97.setFont(jLabel97.getFont());
        jLabel97.setText(bundle.getString("CrInstrument.jLabel97.text")); 
        jPanel147.add(jLabel97);

        jCheckBox43.setFont(jCheckBox43.getFont());
        jCheckBox43.setText(bundle.getString("CrInstrument.jCheckBox43.text")); 
        jCheckBox43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox43ActionPerformed(evt);
            }
        });
        jPanel147.add(jCheckBox43);

        jLabel105.setText(bundle.getString("CrInstrument.jLabel105.text")); 
        jPanel147.add(jLabel105);

        jTextField74.setText(bundle.getString("CrInstrument.jTextField74.text")); 
        jTextField74.setPreferredSize(new java.awt.Dimension(76, 21));
        jTextField74.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField74FocusLost(evt);
            }
        });
        jTextField74.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField74ActionPerformed(evt);
            }
        });
        jPanel147.add(jTextField74);

        jLabel107.setText(bundle.getString("CrInstrument.jLabel107.text")); 
        jPanel147.add(jLabel107);

        jLabel108.setText(bundle.getString("CrInstrument.jLabel108.text")); 
        jPanel147.add(jLabel108);

        jTextField75.setText(bundle.getString("CrInstrument.jTextField75.text")); 
        jTextField75.setPreferredSize(new java.awt.Dimension(76, 21));
        jTextField75.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField75FocusLost(evt);
            }
        });
        jTextField75.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField75ActionPerformed(evt);
            }
        });
        jPanel147.add(jTextField75);

        jLabel139.setText(bundle.getString("CrInstrument.jLabel139.text")); 
        jPanel147.add(jLabel139);

        jLabel140.setFont(jLabel140.getFont());
        jLabel140.setText(bundle.getString("CrInstrument.jLabel140.text")); 
        jPanel147.add(jLabel140);

        jTextField76.setText(bundle.getString("CrInstrument.jTextField76.text")); 
        jTextField76.setPreferredSize(new java.awt.Dimension(76, 21));
        jTextField76.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField76FocusLost(evt);
            }
        });
        jTextField76.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField76ActionPerformed(evt);
            }
        });
        jPanel147.add(jTextField76);

        jLabel156.setText(bundle.getString("CrInstrument.jLabel156.text")); 
        jPanel147.add(jLabel156);

        jLabel141.setFont(jLabel141.getFont());
        jLabel141.setText(bundle.getString("CrInstrument.jLabel141.text")); 
        jPanel147.add(jLabel141);

        jTextField77.setText(bundle.getString("CrInstrument.jTextField77.text")); 
        jTextField77.setPreferredSize(new java.awt.Dimension(76, 21));
        jTextField77.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField77FocusLost(evt);
            }
        });
        jTextField77.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField77ActionPerformed(evt);
            }
        });
        jPanel147.add(jTextField77);

        jLabel154.setText(bundle.getString("CrInstrument.jLabel154.text")); 
        jPanel147.add(jLabel154);

        jPanel145.add(jPanel147);
        jPanel147.setBounds(20, 80, 890, 30);

        mainPanel.add(jPanel145);
        jPanel145.setBounds(30, 280, 940, 120);

        jButton17.setFont(jButton17.getFont().deriveFont(jButton17.getFont().getSize()+12f));
        jButton17.setText(bundle.getString("CrInstrument.jButton17.text")); 
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        mainPanel.add(jButton17);
        jButton17.setBounds(20, 100, 260, 60);

        jTabbedPane4.addTab(bundle.getString("CIUIPanel.mainPanel.TabConstraints.tabTitle"), mainPanel); 

        dataPanel.setFont(dataPanel.getFont());
        dataPanel.setLayout(null);

        jButton18.setFont(jButton18.getFont().deriveFont(jButton18.getFont().getSize()+12f));
        jButton18.setText(bundle.getString("CrInstrument.jButton18.text")); 
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        dataPanel.add(jButton18);
        jButton18.setBounds(20, 20, 250, 50);

        jButton23.setFont(jButton23.getFont().deriveFont(jButton23.getFont().getSize()+12f));
        jButton23.setText(bundle.getString("CrInstrument.jButton23.text")); 
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        dataPanel.add(jButton23);
        jButton23.setBounds(20, 80, 250, 50);

        jButton24.setFont(jButton24.getFont().deriveFont(jButton24.getFont().getSize()+12f));
        jButton24.setText(bundle.getString("CrInstrument.jButton24.text")); 
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        dataPanel.add(jButton24);
        jButton24.setBounds(20, 140, 250, 50);

        jPanel162.setBackground(new java.awt.Color(153, 255, 255));
        jPanel162.setLayout(new java.awt.BorderLayout());
        dataPanel.add(jPanel162);
        jPanel162.setBounds(360, 10, 380, 280);

        jPanel163.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)), bundle.getString("CrInstrument.jPanel163.border.title"))); 
        jPanel163.setLayout(null);

        jPanel164.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel318.setFont(jLabel318.getFont());
        jLabel318.setText(bundle.getString("CrInstrument.jLabel318.text")); 
        jPanel164.add(jLabel318);

        jTextField84.setText(bundle.getString("CrInstrument.jTextField84.text")); 
        jTextField84.setPreferredSize(new java.awt.Dimension(76, 25));
        jTextField84.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField84FocusLost(evt);
            }
        });
        jTextField84.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField84ActionPerformed(evt);
            }
        });
        jPanel164.add(jTextField84);

        jLabel319.setText(bundle.getString("CrInstrument.jLabel319.text")); 
        jPanel164.add(jLabel319);

        jTextField85.setText(bundle.getString("CrInstrument.jTextField85.text")); 
        jTextField85.setPreferredSize(new java.awt.Dimension(76, 25));
        jTextField85.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField85FocusLost(evt);
            }
        });
        jTextField85.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField85ActionPerformed(evt);
            }
        });
        jPanel164.add(jTextField85);

        jLabel320.setFont(jLabel320.getFont());
        jLabel320.setText(bundle.getString("CrInstrument.jLabel320.text")); 
        jPanel164.add(jLabel320);

        jTextField86.setText(bundle.getString("CrInstrument.jTextField86.text")); 
        jTextField86.setPreferredSize(new java.awt.Dimension(76, 25));
        jTextField86.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField86FocusLost(evt);
            }
        });
        jTextField86.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField86ActionPerformed(evt);
            }
        });
        jPanel164.add(jTextField86);

        jLabel321.setFont(jLabel321.getFont());
        jLabel321.setText(bundle.getString("CrInstrument.jLabel321.text")); 
        jPanel164.add(jLabel321);

        jTextField87.setText(bundle.getString("CrInstrument.jTextField87.text")); 
        jTextField87.setPreferredSize(new java.awt.Dimension(76, 25));
        jTextField87.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField87FocusLost(evt);
            }
        });
        jTextField87.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField87ActionPerformed(evt);
            }
        });
        jPanel164.add(jTextField87);

        jLabel322.setText(bundle.getString("CrInstrument.jLabel322.text")); 
        jPanel164.add(jLabel322);

        jPanel163.add(jPanel164);
        jPanel164.setBounds(20, 60, 900, 40);

        jPanel165.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel323.setFont(jLabel323.getFont());
        jLabel323.setText(bundle.getString("CrInstrument.jLabel323.text")); 
        jPanel165.add(jLabel323);

        jLabel324.setBackground(new java.awt.Color(255, 0, 102));
        jLabel324.setText(bundle.getString("CrInstrument.jLabel324.text")); 
        jLabel324.setOpaque(true);
        jLabel324.setPreferredSize(new java.awt.Dimension(40, 25));
        jLabel324.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel324MouseClicked(evt);
            }
        });
        jPanel165.add(jLabel324);

        jLabel325.setFont(jLabel325.getFont());
        jLabel325.setText(bundle.getString("CIUIPanel.jLabel325.text")); 
        jLabel325.setToolTipText(bundle.getString("CIUIPanel.jLabel325.toolTipText")); 
        jPanel165.add(jLabel325);

        jComboBox54.setEditable(true);
        jComboBox54.setFont(jComboBox54.getFont());
        jComboBox54.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox54.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox54ItemStateChanged(evt);
            }
        });
        jPanel165.add(jComboBox54);

        jLabel326.setFont(jLabel326.getFont());
        jLabel326.setText(bundle.getString("CrInstrument.jLabel326.text")); 
        jPanel165.add(jLabel326);

        jTextField88.setText(bundle.getString("CrInstrument.jTextField88.text")); 
        jTextField88.setPreferredSize(new java.awt.Dimension(56, 21));
        jTextField88.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField88FocusLost(evt);
            }
        });
        jTextField88.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField88ActionPerformed(evt);
            }
        });
        jPanel165.add(jTextField88);

        jLabel327.setFont(jLabel327.getFont());
        jLabel327.setText(bundle.getString("CrInstrument.jLabel327.text")); 
        jPanel165.add(jLabel327);

        jLabel328.setBackground(new java.awt.Color(255, 102, 102));
        jLabel328.setText(bundle.getString("CrInstrument.jLabel328.text")); 
        jLabel328.setOpaque(true);
        jLabel328.setPreferredSize(new java.awt.Dimension(40, 25));
        jLabel328.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel328MouseClicked(evt);
            }
        });
        jPanel165.add(jLabel328);

        jCheckBox65.setFont(jCheckBox65.getFont());
        jCheckBox65.setText(bundle.getString("CrInstrument.jCheckBox65.text")); 
        jCheckBox65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox65ActionPerformed(evt);
            }
        });
        jPanel165.add(jCheckBox65);

        jCheckBox66.setFont(jCheckBox66.getFont());
        jCheckBox66.setText(bundle.getString("CrInstrument.jCheckBox66.text")); 
        jCheckBox66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox66ActionPerformed(evt);
            }
        });
        jPanel165.add(jCheckBox66);

        jPanel163.add(jPanel165);
        jPanel165.setBounds(20, 100, 920, 40);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jComboBox15.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox15.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox15ItemStateChanged(evt);
            }
        });
        jPanel2.add(jComboBox15);

        jCheckBox64.setText(bundle.getString("CrInstrument.jCheckBox64.text")); 
        jCheckBox64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox64ActionPerformed(evt);
            }
        });
        jPanel2.add(jCheckBox64);

        jCheckBox1.setFont(jCheckBox1.getFont());
        jCheckBox1.setText(bundle.getString("CIUIPanel.jCheckBox1.text")); 
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel2.add(jCheckBox1);

        jPanel163.add(jPanel2);
        jPanel2.setBounds(20, 17, 820, 40);

        dataPanel.add(jPanel163);
        jPanel163.setBounds(20, 410, 960, 150);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)), bundle.getString("CIUIPanel.jPanel7.border.title"))); 
        jPanel7.setLayout(null);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel15.setFont(jLabel15.getFont());
        jLabel15.setText(bundle.getString("CIUIPanel.jLabel15.text")); 
        jPanel4.add(jLabel15);

        jTextField6.setFont(jTextField6.getFont());
        jTextField6.setText(bundle.getString("CIUIPanel.jTextField6.text")); 
        jTextField6.setPreferredSize(new java.awt.Dimension(76, 25));
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField6);

        jLabel16.setFont(jLabel16.getFont());
        jLabel16.setText(bundle.getString("CIUIPanel.jLabel16.text")); 
        jPanel4.add(jLabel16);

        jTextField7.setFont(jTextField7.getFont());
        jTextField7.setText(bundle.getString("CIUIPanel.jTextField7.text")); 
        jTextField7.setPreferredSize(new java.awt.Dimension(76, 25));
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField7);

        jLabel5.setFont(jLabel5.getFont());
        jLabel5.setText(bundle.getString("CIUIPanel.jLabel5.text")); 
        jPanel4.add(jLabel5);

        jTextField1.setFont(jTextField1.getFont());
        jTextField1.setText(bundle.getString("CIUIPanel.jTextField1.text")); 
        jTextField1.setPreferredSize(new java.awt.Dimension(76, 25));
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField1);

        jLabel6.setFont(jLabel6.getFont());
        jLabel6.setText(bundle.getString("CIUIPanel.jLabel6.text")); 
        jPanel4.add(jLabel6);

        jTextField2.setFont(jTextField2.getFont());
        jTextField2.setText(bundle.getString("CIUIPanel.jTextField2.text")); 
        jTextField2.setPreferredSize(new java.awt.Dimension(76, 25));
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField2);

        jLabel7.setFont(jLabel7.getFont());
        jLabel7.setText(bundle.getString("CIUIPanel.jLabel7.text")); 
        jPanel4.add(jLabel7);

        jLabel3.setFont(jLabel3.getFont());
        jLabel3.setText(bundle.getString("CIUIPanel.jLabel3.text")); 
        jPanel4.add(jLabel3);

        jLabel4.setBackground(new java.awt.Color(255, 0, 51));
        jLabel4.setFont(jLabel4.getFont());
        jLabel4.setText(bundle.getString("CIUIPanel.jLabel4.text")); 
        jLabel4.setOpaque(true);
        jLabel4.setPreferredSize(new java.awt.Dimension(40, 25));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel4);

        jPanel7.add(jPanel4);
        jPanel4.setBounds(20, 20, 910, 35);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel13.setFont(jLabel13.getFont());
        jLabel13.setText(bundle.getString("CIUIPanel.jLabel13.text")); 
        jPanel6.add(jLabel13);

        jLabel14.setBackground(new java.awt.Color(255, 51, 102));
        jLabel14.setFont(jLabel14.getFont());
        jLabel14.setText(bundle.getString("CIUIPanel.jLabel14.text")); 
        jLabel14.setOpaque(true);
        jLabel14.setPreferredSize(new java.awt.Dimension(40, 25));
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel14);

        jLabel11.setFont(jLabel11.getFont());
        jLabel11.setText(bundle.getString("CIUIPanel.jLabel11.text")); 
        jPanel6.add(jLabel11);

        jComboBox1.setEditable(true);
        jComboBox1.setFont(jComboBox1.getFont());
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jPanel6.add(jComboBox1);

        jLabel12.setFont(jLabel12.getFont());
        jLabel12.setText(bundle.getString("CIUIPanel.jLabel12.text")); 
        jPanel6.add(jLabel12);

        jTextField4.setFont(jTextField4.getFont());
        jTextField4.setText(bundle.getString("CIUIPanel.jTextField4.text")); 
        jTextField4.setPreferredSize(new java.awt.Dimension(56, 25));
        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField4FocusLost(evt);
            }
        });
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel6.add(jTextField4);

        jLabel9.setFont(jLabel9.getFont());
        jLabel9.setText(bundle.getString("CIUIPanel.jLabel9.text")); 
        jPanel6.add(jLabel9);

        jLabel10.setBackground(new java.awt.Color(255, 51, 51));
        jLabel10.setFont(jLabel10.getFont());
        jLabel10.setText(bundle.getString("CIUIPanel.jLabel10.text")); 
        jLabel10.setOpaque(true);
        jLabel10.setPreferredSize(new java.awt.Dimension(40, 25));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel10);

        jCheckBox2.setFont(jCheckBox2.getFont());
        jCheckBox2.setText(bundle.getString("CIUIPanel.jCheckBox2.text")); 
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        jPanel6.add(jCheckBox2);

        jCheckBox4.setFont(jCheckBox4.getFont());
        jCheckBox4.setText(bundle.getString("CIUIPanel.jCheckBox4.text")); 
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });
        jPanel6.add(jCheckBox4);

        jPanel7.add(jPanel6);
        jPanel6.setBounds(20, 60, 910, 40);

        dataPanel.add(jPanel7);
        jPanel7.setBounds(20, 290, 960, 110);

        jTabbedPane4.addTab(bundle.getString("CIUIPanel.dataPanel.TabConstraints.tabTitle"), dataPanel); 

        miscPanel.setFont(miscPanel.getFont());
        miscPanel.setLayout(null);

        jPanel123.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel137.setFont(jLabel137.getFont());
        jLabel137.setText(bundle.getString("CrInstrument.jLabel137.text")); 
        jPanel123.add(jLabel137);

        buttonGroup1.add(byStation);
        byStation.setFont(byStation.getFont());
        byStation.setText(bundle.getString("CrInstrument.byStation.text")); 
        byStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byStationActionPerformed(evt);
            }
        });
        jPanel123.add(byStation);

        buttonGroup1.add(byDeviceName);
        byDeviceName.setFont(byDeviceName.getFont());
        byDeviceName.setText(bundle.getString("CrInstrument.byDevice.text")); 
        byDeviceName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byDeviceNameActionPerformed(evt);
            }
        });
        jPanel123.add(byDeviceName);

        buttonGroup1.add(byModel);
        byModel.setFont(byModel.getFont());
        byModel.setText(bundle.getString("CrInstrument.byModel.text")); 
        byModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byModelActionPerformed(evt);
            }
        });
        jPanel123.add(byModel);

        buttonGroup1.add(byDataName);
        byDataName.setFont(byDataName.getFont());
        byDataName.setText(bundle.getString("CrInstrument.byDataName.text")); 
        byDataName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byDataNameActionPerformed(evt);
            }
        });
        jPanel123.add(byDataName);

        buttonGroup1.add(byChartGroup);
        byChartGroup.setFont(byChartGroup.getFont());
        byChartGroup.setText(bundle.getString("CrInstrument.byChartGroup.text")); 
        byChartGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byChartGroupActionPerformed(evt);
            }
        });
        jPanel123.add(byChartGroup);

        chartGroupCB.setEditable(true);
        chartGroupCB.setFont(chartGroupCB.getFont());
        chartGroupCB.setPreferredSize(new java.awt.Dimension(129, 21));
        chartGroupCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chartGroupCBItemStateChanged(evt);
            }
        });
        jPanel123.add(chartGroupCB);

        miscPanel.add(jPanel123);
        jPanel123.setBounds(20, 170, 930, 40);

        jPanel166.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jButton25.setFont(jButton25.getFont().deriveFont(jButton25.getFont().getSize()+12f));
        jButton25.setText(bundle.getString("CrInstrument.jButton25.text")); 
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jPanel166.add(jButton25);

        jButton28.setFont(jButton28.getFont().deriveFont(jButton28.getFont().getSize()+12f));
        jButton28.setText(bundle.getString("CrInstrument.jButton28.text")); 
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        jPanel166.add(jButton28);

        jButton29.setFont(jButton29.getFont().deriveFont(jButton29.getFont().getSize()+12f));
        jButton29.setText(bundle.getString("CrInstrument.jButton29.text")); 
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        jPanel166.add(jButton29);

        miscPanel.add(jPanel166);
        jPanel166.setBounds(20, 20, 750, 60);

        jPanel167.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel168.setFont(jLabel168.getFont());
        jLabel168.setText(bundle.getString("CrInstrument.jLabel168.text")); 
        jPanel167.add(jLabel168);

        buttonGroup2.add(byStation1);
        byStation1.setFont(byStation1.getFont());
        byStation1.setText(bundle.getString("CrInstrument.byStation1.text")); 
        byStation1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byStation1ActionPerformed(evt);
            }
        });
        jPanel167.add(byStation1);

        buttonGroup2.add(byDeviceName1);
        byDeviceName1.setFont(byDeviceName1.getFont());
        byDeviceName1.setText(bundle.getString("CrInstrument.byDevice1.text")); 
        byDeviceName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byDeviceName1ActionPerformed(evt);
            }
        });
        jPanel167.add(byDeviceName1);

        buttonGroup2.add(byModel1);
        byModel1.setFont(byModel1.getFont());
        byModel1.setText(bundle.getString("CrInstrument.byModel1.text")); 
        byModel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byModel1ActionPerformed(evt);
            }
        });
        jPanel167.add(byModel1);

        buttonGroup2.add(byDevice1);
        byDevice1.setFont(byDevice1.getFont());
        byDevice1.setText(bundle.getString("CIUIPanel.byDevice1.text")); 
        byDevice1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byDevice1ActionPerformed(evt);
            }
        });
        jPanel167.add(byDevice1);

        buttonGroup2.add(byDataName1);
        byDataName1.setFont(byDataName1.getFont());
        byDataName1.setSelected(true);
        byDataName1.setText(bundle.getString("CrInstrument.byDataName1.text")); 
        byDataName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byDataName1ActionPerformed(evt);
            }
        });
        jPanel167.add(byDataName1);

        buttonGroup2.add(showAll);
        showAll.setFont(showAll.getFont());
        showAll.setText(bundle.getString("CrInstrument.byChartGroup1.text")); 
        showAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showAllActionPerformed(evt);
            }
        });
        jPanel167.add(showAll);

        miscPanel.add(jPanel167);
        jPanel167.setBounds(20, 230, 900, 40);

        jPanel32.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel20.setFont(jLabel20.getFont());
        jLabel20.setText(bundle.getString("CrInstrument.jLabel20.text")); 
        jPanel32.add(jLabel20);

        jComboBox21.setFont(jComboBox21.getFont());
        jComboBox21.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox21.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox21ItemStateChanged(evt);
            }
        });
        jPanel32.add(jComboBox21);

        jLabel30.setFont(jLabel30.getFont());
        jLabel30.setText(bundle.getString("CrInstrument.jLabel30.text")); 
        jPanel32.add(jLabel30);

        jTextField22.setFont(jTextField22.getFont());
        jTextField22.setText(bundle.getString("CrInstrument.jTextField22.text")); 
        jTextField22.setPreferredSize(new java.awt.Dimension(156, 25));
        jTextField22.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField22FocusLost(evt);
            }
        });
        jTextField22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField22ActionPerformed(evt);
            }
        });
        jPanel32.add(jTextField22);

        jCheckBox49.setFont(jCheckBox49.getFont());
        jCheckBox49.setText(bundle.getString("CrInstrument.jCheckBox49.text")); 
        jCheckBox49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox49ActionPerformed(evt);
            }
        });
        jPanel32.add(jCheckBox49);

        miscPanel.add(jPanel32);
        jPanel32.setBounds(20, 110, 600, 40);

        jTabbedPane4.addTab(bundle.getString("CIUIPanel.miscPanel.TabConstraints.tabTitle"), miscPanel); 

        add(jTabbedPane4, java.awt.BorderLayout.CENTER);
    }

    private void jButton57ActionPerformed(java.awt.event.ActionEvent evt) {

        Iterator it=instrument.defaultUI.keySet().iterator();
        for(;it.hasNext();){
            String id=(String)it.next();
            if(id.equalsIgnoreCase("frame") || id.toLowerCase().indexOf("area")!=-1 || id.toLowerCase().indexOf("button")!=-1){
                instrument.currentUI.put(id, (String)instrument.defaultUI.get(id));
                instrument.editUI.put(id, (String)instrument.defaultUI.get(id));
            }
        }

        setFrameItem();
        setDataAreaItem();

        instrument.selectedUIAreaItem="";
        jComboBox36.setSelectedItem("");
        updateFramePanel();
        instrument.framePanel.invalidate();
        updateDataAreaPanel();
        instrument.dataPanel2.invalidate();

        instrument.resetFrameSize=true;
        instrument.validate();

    }

    private void btnApplySetting_uiActionPerformed(java.awt.event.ActionEvent evt) {
        Iterator it=instrument.editUI.keySet().iterator();
        for(;it.hasNext();){
            String id=(String)it.next();
            if(id.equalsIgnoreCase("frame") || id.toLowerCase().indexOf("area")!=-1 || id.toLowerCase().indexOf("button")!=-1){
                if(instrument.editUI.get(id)!=null) instrument.currentUI.put(id, (String)instrument.editUI.get(id));
            }
        }

        setDataAreaItem();
        updateDataAreaPanel();
        instrument.dataPanel2.invalidate();

        instrument.resetFrameSize=true;
        instrument.validate();
        instrument.jTabbedPane1.setSelectedComponent(instrument.jPanel1);

    }

    private void jLabel63MouseClicked(java.awt.event.MouseEvent evt) {
        Color newColor = JColorChooser.showDialog(null, "Choose a color", jLabel63.getBackground());
        if(newColor!=null) {
            jLabel63.setBackground(newColor);
            instrument.framePanel.updateAreaItem();
        }
    }

    private void jLabel62MouseClicked(java.awt.event.MouseEvent evt) {
        Color newColor = JColorChooser.showDialog(null, "Choose a color", jLabel62.getBackground());
        if(newColor!=null) {
            jLabel62.setBackground(newColor);
            instrument.framePanel.updateAreaItem();
        }
    }

    private void jLabel61MouseClicked(java.awt.event.MouseEvent evt) {
        Color newColor = JColorChooser.showDialog(null, "Choose a color", jLabel61.getBackground());
        if(newColor!=null) {
            jLabel61.setBackground(newColor);
            updateFrameItem();
        }
    }

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {
        Iterator it=instrument.currentUI.keySet().iterator();
        for(;it.hasNext();){
            String id=(String)it.next();
            if(id.equalsIgnoreCase("frame") || id.toLowerCase().indexOf("area")!=-1 || id.toLowerCase().indexOf("button")!=-1){
                instrument.editUI.put(id, (String)instrument.currentUI.get(id));
            }
        }

      updateFramePanel();
      setDataAreaItem();
      updateDataAreaPanel();
      instrument.framePanel.invalidate();
      instrument.dataPanel2.invalidate();

    }

    private void jLabel324MouseClicked(java.awt.event.MouseEvent evt) {
        Color newColor = JColorChooser.showDialog(null, "Choose a color", jLabel324.getBackground());
        if(newColor!=null) {
            jLabel324.setBackground(newColor);
            instrument.dataPanel2.updateDataItem();
        }
    }

    private void jLabel328MouseClicked(java.awt.event.MouseEvent evt) {
        Color newColor = JColorChooser.showDialog(null, "Choose a color", jLabel328.getBackground());
        if(newColor!=null) {
            jLabel328.setBackground(newColor);
            instrument.dataPanel2.updateDataItem();
        }
    }

    private void byStationActionPerformed(java.awt.event.ActionEvent evt) {
        updateChartTypeItem();
    }

    private void byDeviceNameActionPerformed(java.awt.event.ActionEvent evt) {
        updateChartTypeItem();
    }

    private void byModelActionPerformed(java.awt.event.ActionEvent evt) {
        updateChartTypeItem();
    }

    private void byChartGroupActionPerformed(java.awt.event.ActionEvent evt) {
        updateChartTypeItem();
    }

    private void chartGroupCBItemStateChanged(java.awt.event.ItemEvent evt) {
        if(evt.getStateChange()==evt.SELECTED){
          updateChartTypeItem();
        }
    }

    private void byStation1ActionPerformed(java.awt.event.ActionEvent evt) {
       updateDataAreaType();
    }

    private void byDeviceName1ActionPerformed(java.awt.event.ActionEvent evt) {
        updateDataAreaType();
    }

    private void byModel1ActionPerformed(java.awt.event.ActionEvent evt) {
       updateDataAreaType();
    }

    private void showAllActionPerformed(java.awt.event.ActionEvent evt) {
        updateDataAreaType();
    }

    private void jComboBox15ItemStateChanged(java.awt.event.ItemEvent evt) {
    if(evt.getStateChange()==evt.SELECTED){
      String sel=((String)jComboBox15.getSelectedItem()).trim();
      if(sel.length()>0) showDataItem(sel); 
      instrument.selectedDataItem=sel;
      instrument.dataPanel2.invalidate();
    }
    }
void showDataItem(String sel){
    if(instrument.editUI.get(sel)==null) return;
    String info[]=ylib.csvlinetoarray((String)instrument.editUI.get(sel));
    if(info[2].toLowerCase().trim().equals("s")) jCheckBox64.setSelected(true); else jCheckBox64.setSelected(false);
    jTextField84.setText((instrument.isNumeric(info[3])? String.valueOf(Double.parseDouble(info[3])*100.0):"0.0"));
    jTextField85.setText((instrument.isNumeric(info[4])? String.valueOf(Double.parseDouble(info[4])*100.0):"0.0"));
    jTextField86.setText((instrument.isNumeric(info[5])? String.valueOf(Double.parseDouble(info[5])*100.0):"0.0"));
    jTextField87.setText((instrument.isNumeric(info[6])? String.valueOf(Double.parseDouble(info[6])*100.0):"0.0"));
    instrument.skipUICBBChanged=true;
    jComboBox54.setSelectedItem(info[8]);
    instrument.skipUICBBChanged=false;
    jTextField88.setText(info[9]);
    if(info[11].toLowerCase().trim().equals("b")) jCheckBox65.setSelected(true); else jCheckBox65.setSelected(false);
    if(info[12].toLowerCase().trim().equals("i")) jCheckBox66.setSelected(true); else jCheckBox66.setSelected(false);
    if(info[13].toLowerCase().trim().equals("o")) jCheckBox1.setSelected(true); else jCheckBox1.setSelected(false);
    if(instrument.isNumeric(info[7])) jLabel324.setBackground( new Color(Integer.parseInt(info[7])));
                 else jLabel324.setBackground(Color.darkGray);
    if(instrument.isNumeric(info[10])) jLabel328.setBackground( new Color(Integer.parseInt(info[10])));
                 else jLabel328.setBackground(Color.darkGray);
}
    private void jComboBox36ItemStateChanged(java.awt.event.ItemEvent evt) {
    if(evt.getStateChange()==evt.SELECTED){
      String sel=((String)jComboBox36.getSelectedItem()).trim();
      if(sel.length()>0) showAreaItem(sel); 
      instrument.selectedUIAreaItem=sel;

      instrument.framePanel.invalidate();
    }
    }
void showAreaItem(String sel){
    if(instrument.editUI.get(sel)==null) return;
    String info[]=ylib.csvlinetoarray((String)instrument.editUI.get(sel));
    if(info[2].toLowerCase().trim().equals("s")) jCheckBox37.setSelected(true); else jCheckBox37.setSelected(false);
    jTextField3.setText(info[1]);
    jTextField5.setText((instrument.isNumeric(info[3])? String.valueOf(Double.parseDouble(info[3])*100.0):"0.0"));
    jTextField9.setText((instrument.isNumeric(info[4])? String.valueOf(Double.parseDouble(info[4])*100.0):"0.0"));
    jTextField10.setText((instrument.isNumeric(info[5])? String.valueOf(Double.parseDouble(info[5])*100.0):"0.0"));
    jTextField61.setText((instrument.isNumeric(info[6])? String.valueOf(Double.parseDouble(info[6])*100.0):"0.0"));
    instrument.skipUICBBChanged=true;
    jComboBox53.setSelectedItem(info[8]);
    instrument.skipUICBBChanged=false;
    jTextField73.setText(info[9]);
    if(info[11].toLowerCase().trim().equals("b")) jCheckBox3.setSelected(true); else jCheckBox3.setSelected(false);
    if(info[12].toLowerCase().trim().equals("i")) jCheckBox46.setSelected(true); else jCheckBox46.setSelected(false);
    if(instrument.isNumeric(info[7])) jLabel63.setBackground( new Color(Integer.parseInt(info[7])));
                 else jLabel63.setBackground(Color.darkGray);
    if(instrument.isNumeric(info[10])) jLabel62.setBackground( new Color(Integer.parseInt(info[10])));
                 else jLabel62.setBackground(Color.darkGray);
    if(instrument.isNumeric(info[13])) jLabel2.setBackground( new Color(Integer.parseInt(info[13])));
                 else jLabel2.setBackground(Color.darkGray);
}
    private void jComboBox21ItemStateChanged(java.awt.event.ItemEvent evt) {
    if(evt.getStateChange()==evt.SELECTED){
      String sel=((String)jComboBox21.getSelectedItem()).trim();
      if(sel.length()>0) showMenuItem(sel); 
      instrument.selectedMenuItem=sel;
    }
    }

int[] getFrameSizeFromEditUI(){
  int size[]={1024,712};
  String info[]=null;
  int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
  int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
 if(instrument.editUI.get("frame")!=null){
    String frameInfo=(String)instrument.editUI.get("frame");
    info=ylib.csvlinetoarray(frameInfo);

    if(info.length > 5 && info[5].length()>0){
        if(info[2].equals("%")) size[0]=(int)Math.round(Double.parseDouble(info[5]) * ((double)screenWidth)); else size[0]=Integer.parseInt(info[5]);
    }
    if(info.length > 6 && info[6].length()>0){
        if(info[2].equals("%")) size[1]=(int)Math.round(Double.parseDouble(info[6]) * ((double)screenHeight)); else size[1]=Integer.parseInt(info[6]);
    }
 }
    return size;
}
    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {
        Color newColor = JColorChooser.showDialog(null, "Choose a color", jLabel2.getBackground());

        if(newColor!=null) {
            jLabel2.setBackground(newColor);
            instrument.framePanel.updateAreaItem();
        }
    }

    private void jTextField45ActionPerformed(java.awt.event.ActionEvent evt) {
       updateFrameItem();
    }

    private void jCheckBox38ActionPerformed(java.awt.event.ActionEvent evt) {
    int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    int frameWidth = instrument.getWidth();
    int frameHeight = instrument.getHeight();

        if(jCheckBox38.isSelected()){
            jLabel107.setText("%");
            jLabel139.setText("%");
            jLabel156.setText("%");
            jLabel154.setText("%");
            String x=jTextField74.getText().trim();
            String y=jTextField75.getText().trim();
            String w=jTextField76.getText().trim();
            String h=jTextField77.getText().trim();
            if(instrument.isNumeric(x)){
               double xD=Double.parseDouble(x);
               if(xD<0) xD=0.0;
               xD=xD/((double)screenWidth)*100.0;
               xD=((double)(Math.round(xD*1000000.0)))/1000000.0;
               if(xD>100.0) xD=100.0;
               jTextField74.setText(""+xD);
            }
            if(instrument.isNumeric(y)){
               double yD=Double.parseDouble(x);
               if(yD<0) yD=0.0;
               yD=yD/((double)screenHeight)*100.0;
               yD=((double)(Math.round(yD*1000000.0)))/1000000.0;
               if(yD>100.0) yD=100.0;
               jTextField75.setText(""+yD);
            }
            if(instrument.isNumeric(w)){
               double wD=Double.parseDouble(w);
               if(wD<0) wD=0.0;
               wD=wD/((double)screenWidth)*100.0;
               wD=((double)(Math.round(wD*1000000.0)))/1000000.0;
               if(wD>100.0) wD=100.0;
               jTextField76.setText(""+wD);
            }
            if(instrument.isNumeric(h)){
               double hD=Double.parseDouble(h);
               if(hD<0) hD=0.0;
               hD=hD/((double)screenHeight)*100.0;
               hD=((double)(Math.round(hD*1000000.0)))/1000000.0;
               if(hD>100.0) hD=100.0;
               jTextField77.setText(""+hD);
            }
        } else{
            jLabel107.setText("");
            jLabel139.setText("");
            jLabel156.setText("");
            jLabel154.setText("");
            String x=jTextField74.getText().trim();
            String y=jTextField75.getText().trim();
            String w=jTextField76.getText().trim();
            String h=jTextField77.getText().trim();
            if(instrument.isNumeric(x)){
               double xD=Double.parseDouble(x);
               if(xD<0) xD=0.0;
               xD=xD*((double)screenWidth)/100.0;

               jTextField74.setText(""+(int)Math.round(xD));
            }
            if(instrument.isNumeric(y)){
               double yD=Double.parseDouble(y);
               if(yD<0) yD=0.0;
               yD=yD*((double)screenHeight)/100.0;

               jTextField75.setText(""+(int)Math.round(yD));
            }
            if(instrument.isNumeric(w)){
               double wD=Double.parseDouble(w);
               if(wD<0) wD=0.0;
               wD=wD*((double)screenWidth)/100.0;

               jTextField76.setText(""+(int)Math.round(wD));
            }
            if(instrument.isNumeric(h)){
               double hD=Double.parseDouble(h);
               if(hD<0) hD=0.0;
               hD=hD*((double)screenHeight)/100.0;

               jTextField77.setText(""+(int)Math.round(hD));
            }
        }
        updateFrameItem();
    }

    private void jCheckBox43ActionPerformed(java.awt.event.ActionEvent evt) {
            String x=jTextField74.getText().trim();
            String y=jTextField75.getText().trim();
            if(instrument.isNumeric(x)){
               double xD=Double.parseDouble(x);
               if(xD<0) {
                   xD=0.0;
               jTextField74.setText(""+xD);
               }
            } else jTextField74.setText("0.0");
            if(instrument.isNumeric(y)){
               double yD=Double.parseDouble(x);
               if(yD<0) {
                   yD=0.0;
               jTextField75.setText(""+yD);
               }
            } else jTextField75.setText("0.0");
        updateFrameItem();
    }

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {
     updateFrameItem();
    }

    private void jCheckBox44ActionPerformed(java.awt.event.ActionEvent evt) {
      updateFrameItem();
    }

    private void jCheckBox45ActionPerformed(java.awt.event.ActionEvent evt) {
      updateFrameItem();
    }

    private void jTextField77ActionPerformed(java.awt.event.ActionEvent evt) {
        updateFrameItem();
    }

    private void jTextField76ActionPerformed(java.awt.event.ActionEvent evt) {
      updateFrameItem();
    }

    private void jTextField75ActionPerformed(java.awt.event.ActionEvent evt) {
       updateFrameItem();
    }

    private void jTextField74ActionPerformed(java.awt.event.ActionEvent evt) {
      updateFrameItem();
    }

    private void jCheckBox37ActionPerformed(java.awt.event.ActionEvent evt) {
       instrument.framePanel.updateAreaItem();
    }

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {
      updateButtonName();
    }
void updateButtonName(){
           if(jComboBox36.getSelectedItem()!=null){
           String sel=(String)jComboBox36.getSelectedItem();
            String info[]=ylib.csvlinetoarray((String)instrument.editUI.get(sel));
           if(sel.toLowerCase().indexOf("button")==0){
            info[1]=jTextField3.getText().trim();
            instrument.editUI.put(sel,ylib.arrayToCsvLine(info));

           }
       }
}
    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {
        instrument.framePanel.updateAreaItem();
    }

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {
       instrument.framePanel.updateAreaItem();
    }

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {
       instrument.framePanel.updateAreaItem();
    }

    private void jTextField61ActionPerformed(java.awt.event.ActionEvent evt) {
      instrument.framePanel.updateAreaItem();
    }

    private void jComboBox53ItemStateChanged(java.awt.event.ItemEvent evt) {
      if(evt.getStateChange()==evt.SELECTED && !instrument.skipUICBBChanged){
         instrument.framePanel.updateAreaItem();
      }
    }

    private void jTextField73ActionPerformed(java.awt.event.ActionEvent evt) {
      instrument.framePanel.updateAreaItem();
    }

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {
      instrument.framePanel.updateAreaItem();
    }

    private void jCheckBox46ActionPerformed(java.awt.event.ActionEvent evt) {
      instrument.framePanel.updateAreaItem();
    }

    private void jCheckBox64ActionPerformed(java.awt.event.ActionEvent evt) {
       instrument.dataPanel2.updateDataItem();
    }

    private void jTextField84ActionPerformed(java.awt.event.ActionEvent evt) {
      instrument.dataPanel2.updateDataItem();
    }

    private void jTextField85ActionPerformed(java.awt.event.ActionEvent evt) {
      instrument.dataPanel2.updateDataItem();
    }

    private void jTextField86ActionPerformed(java.awt.event.ActionEvent evt) {
      instrument.dataPanel2.updateDataItem();
    }

    private void jTextField87ActionPerformed(java.awt.event.ActionEvent evt) {
     instrument.dataPanel2.updateDataItem();
    }

    private void jComboBox54ItemStateChanged(java.awt.event.ItemEvent evt) {
      if(evt.getStateChange()==evt.SELECTED && !instrument.skipUICBBChanged){
          instrument.dataPanel2.updateDataItem();
      }
    }

    private void jTextField88ActionPerformed(java.awt.event.ActionEvent evt) {
       instrument.dataPanel2.updateDataItem();
    }

    private void jCheckBox65ActionPerformed(java.awt.event.ActionEvent evt) {
      instrument.dataPanel2.updateDataItem();
    }

    private void jCheckBox66ActionPerformed(java.awt.event.ActionEvent evt) {
      instrument.dataPanel2.updateDataItem();
    }

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {
        Iterator it=instrument.editUI.keySet().iterator();
        for(;it.hasNext();){
            String id=(String)it.next();
            if(id.toLowerCase().indexOf("da_")!=-1 || id.toLowerCase().indexOf("data area")!=-1){
                if(instrument.editUI.get(id)!=null) instrument.currentUI.put(id, (String)instrument.editUI.get(id));
            }
        }

        if(((String)jComboBox36.getSelectedItem()).equalsIgnoreCase("data area")) showAreaItem("data area"); 

        instrument.resetFrameSize=true;
        instrument.validate();
        instrument.jTabbedPane1.setSelectedComponent(instrument.jPanel1);
    }

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {
        Iterator it=instrument.currentUI.keySet().iterator();
        for(;it.hasNext();){
            String id=(String)it.next();
            if(id.toLowerCase().indexOf("da_")!=-1 || id.equalsIgnoreCase("data area")){
                instrument.editUI.put(id, (String)instrument.currentUI.get(id));
            }
        }
        instrument.selectedDataItem="";
        jComboBox15.setSelectedItem("");
        setDataAreaItem();

    }

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {

        Iterator it=instrument.defaultUI.keySet().iterator();
        for(;it.hasNext();){
            String id=(String)it.next();
            if(id.toLowerCase().indexOf("da_")!=-1 || id.equalsIgnoreCase("data area")){
                instrument.currentUI.put(id, (String)instrument.defaultUI.get(id));
                instrument.editUI.put(id, (String)instrument.defaultUI.get(id));
            }
        }

        instrument.selectedDataItem="";
        jComboBox15.setSelectedItem("");

        setDataAreaItem();
        instrument.resetFrameSize=true;
        instrument.validate();
        instrument.dataPanel2.invalidate();
    }

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {

     boolean needUpdateChartProfile=false;
     if(instrument.editUI.get("misc")!=null){
       String info[]=ylib.csvlinetoarray((String)instrument.editUI.get("misc"));
       if(instrument.currentUI.get("misc")!=null){
         String info2[]=ylib.csvlinetoarray((String)instrument.currentUI.get("misc"));
         if(!info[1].equalsIgnoreCase(info2[1]) || (info[1].equalsIgnoreCase(info2[1]) && info[1].equalsIgnoreCase("chartgroup") && !info[3].equalsIgnoreCase(info2[3]))){
           needUpdateChartProfile=true;
         }
       }
     }

        Iterator it=instrument.editUI.keySet().iterator();
        for(;it.hasNext();){
            String id=(String)it.next();
            if(id.equalsIgnoreCase("misc") || id.toLowerCase().indexOf("menuitem")!=-1){
                if(instrument.editUI.get(id)!=null) instrument.currentUI.put(id, (String)instrument.editUI.get(id));
            }
        }

        instrument.resetFrameSize=true;
        instrument.validate();
        instrument.jTabbedPane1.setSelectedComponent(instrument.jPanel1);

        if(needUpdateChartProfile) instrument.updateChartProfile();
    }

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {

     boolean needUpdateChartProfile=false;
     if(instrument.editUI.get("misc")!=null){
       String info[]=ylib.csvlinetoarray((String)instrument.editUI.get("misc"));
       if(instrument.currentUI.get("misc")!=null){
         String info2[]=ylib.csvlinetoarray((String)instrument.currentUI.get("misc"));
         if(!info[1].equalsIgnoreCase(info2[1]) || (info[1].equalsIgnoreCase(info2[1]) && info[1].equalsIgnoreCase("chartgroup") && !info[3].equalsIgnoreCase(info2[3]))){
           needUpdateChartProfile=true;
         }
       }
     }

     Iterator it=instrument.defaultUI.keySet().iterator();
        for(;it.hasNext();){
            String id=(String)it.next();
            if(id.equalsIgnoreCase("misc") || id.toLowerCase().indexOf("menuitem")!=-1){
                instrument.currentUI.put(id, (String)instrument.defaultUI.get(id));
                instrument.editUI.put(id, (String)instrument.defaultUI.get(id));
            }
        }

        instrument.selectedMenuItem="";
        jComboBox21.setSelectedItem("");

        instrument.resetFrameSize=true;
        instrument.validate();

        if(needUpdateChartProfile) instrument.updateChartProfile();

     if(instrument.editUI.get("misc")!=null){
       String info[]=ylib.csvlinetoarray((String)instrument.editUI.get("misc"));
       if(info[1].equalsIgnoreCase("staion")) byStation.setSelected(true);
       else if(info[1].equalsIgnoreCase("device")) byDeviceName.setSelected(true);
       else if(info[1].equalsIgnoreCase("model")) byModel.setSelected(true);
       else if(info[1].equalsIgnoreCase("dataname")) byDataName.setSelected(true);
       else if(info[1].equalsIgnoreCase("chartgroup")) byChartGroup.setSelected(true);
       chartGroupCB.setSelectedItem(info[3]);
       if(info[2].equalsIgnoreCase("staion")) byStation1.setSelected(true);
       else if(info[2].equalsIgnoreCase("device")) byDeviceName1.setSelected(true);
       else if(info[2].equalsIgnoreCase("model")) byModel1.setSelected(true);
       else if(info[2].equalsIgnoreCase("dataname")) byDataName1.setSelected(true);
       else if(info[2].equalsIgnoreCase("showall")) showAll.setSelected(true);
     }
    }

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {
        Iterator it=instrument.currentUI.keySet().iterator();
        for(;it.hasNext();){
            String id=(String)it.next();
            if(id.equalsIgnoreCase("misc") || id.toLowerCase().indexOf("menuitem")!=-1){
                instrument.editUI.put(id, (String)instrument.currentUI.get(id));
            }
        }

        instrument.selectedMenuItem="";
        jComboBox21.setSelectedItem("");

     if(instrument.editUI.get("misc")!=null){
       String info[]=ylib.csvlinetoarray((String)instrument.editUI.get("misc"));
       if(info[1].equalsIgnoreCase("staion")) byStation.setSelected(true);
       else if(info[1].equalsIgnoreCase("device")) byDeviceName.setSelected(true);
       else if(info[1].equalsIgnoreCase("model")) byModel.setSelected(true);
       else if(info[1].equalsIgnoreCase("dataname")) byDataName.setSelected(true);
       else if(info[1].equalsIgnoreCase("chartgroup")) byChartGroup.setSelected(true);
       chartGroupCB.setSelectedItem(info[3]);
       if(info[2].equalsIgnoreCase("staion")) byStation1.setSelected(true);
       else if(info[2].equalsIgnoreCase("device")) byDeviceName1.setSelected(true);
       else if(info[2].equalsIgnoreCase("model")) byModel1.setSelected(true);
       else if(info[2].equalsIgnoreCase("dataname")) byDataName1.setSelected(true);
       else if(info[2].equalsIgnoreCase("showall")) showAll.setSelected(true);
     }

    }

    private void jPanel140KeyPressed(java.awt.event.KeyEvent evt) {
     System.out.println("key pressed in frame panel1, keycode="+evt.getKeyCode()+",char="+evt.getKeyChar()+",id="+evt.getID()+",modifiers="+evt.getModifiers());
    }

    private void jTextField22ActionPerformed(java.awt.event.ActionEvent evt) {
        updateMenuItem();
    }

    private void jCheckBox49ActionPerformed(java.awt.event.ActionEvent evt) {
        updateMenuItem();
    }

    private void byDataNameActionPerformed(java.awt.event.ActionEvent evt) {
       updateChartTypeItem();
    }

    private void byDataName1ActionPerformed(java.awt.event.ActionEvent evt) {
       updateDataAreaType();
    }

    private void jTextField22FocusLost(java.awt.event.FocusEvent evt) {
     updateMenuItem();        
    }

    private void jTextField45FocusLost(java.awt.event.FocusEvent evt) {
      updateFrameItem();
    }

    private void jTextField11FocusLost(java.awt.event.FocusEvent evt) {
      updateFrameItem();
    }

    private void jTextField74FocusLost(java.awt.event.FocusEvent evt) {
        updateFrameItem();
    }

    private void jTextField75FocusLost(java.awt.event.FocusEvent evt) {
       updateFrameItem();
    }

    private void jTextField76FocusLost(java.awt.event.FocusEvent evt) {
      updateFrameItem();
    }

    private void jTextField77FocusLost(java.awt.event.FocusEvent evt) {
      updateFrameItem();
    }

    private void jTextField3FocusLost(java.awt.event.FocusEvent evt) {
       updateButtonName();
    }

    private void jTextField5FocusLost(java.awt.event.FocusEvent evt) {
       instrument.framePanel.updateAreaItem();
    }

    private void jTextField9FocusLost(java.awt.event.FocusEvent evt) {
       instrument.framePanel.updateAreaItem();
    }

    private void jTextField10FocusLost(java.awt.event.FocusEvent evt) {
       instrument.framePanel.updateAreaItem();
    }

    private void jTextField61FocusLost(java.awt.event.FocusEvent evt) {
        instrument.framePanel.updateAreaItem();
    }

    private void jTextField73FocusLost(java.awt.event.FocusEvent evt) {
       instrument.framePanel.updateAreaItem();
    }

    private void jTextField84FocusLost(java.awt.event.FocusEvent evt) {
       instrument.dataPanel2.updateDataItem();
    }

    private void jTextField85FocusLost(java.awt.event.FocusEvent evt) {
       instrument.dataPanel2.updateDataItem();
    }

    private void jTextField86FocusLost(java.awt.event.FocusEvent evt) {
      instrument.dataPanel2.updateDataItem();
    }

    private void jTextField87FocusLost(java.awt.event.FocusEvent evt) {
       instrument.dataPanel2.updateDataItem();
    }

    private void jTextField88FocusLost(java.awt.event.FocusEvent evt) {
       instrument.dataPanel2.updateDataItem();
    }

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
       instrument.dataPanel2.updateDataItem();
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
      instrument.framePanel.updateAreaItem_DataArea();
    }

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {
      instrument.framePanel.updateAreaItem_DataArea();
    }

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {
       instrument.framePanel.updateAreaItem_DataArea();
    }

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {
      instrument.framePanel.updateAreaItem_DataArea();
    }

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {
       instrument.framePanel.updateAreaItem_DataArea();
    }

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {
      if(evt.getStateChange()==evt.SELECTED && !instrument.skipUICBBChanged){
          instrument.framePanel.updateAreaItem_DataArea();
      }
    }

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {
        Color newColor = JColorChooser.showDialog(null, "Choose a color", jLabel14.getBackground());
        if(newColor!=null) {
            jLabel14.setBackground(newColor);
            instrument.framePanel.updateAreaItem_DataArea();
        }
    }

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {
        Color newColor = JColorChooser.showDialog(null, "Choose a color", jLabel4.getBackground());
        if(newColor!=null) {
            jLabel4.setBackground(newColor);
            instrument.framePanel.updateAreaItem_DataArea();
        }
    }

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {
         Color newColor = JColorChooser.showDialog(null, "Choose a color", jLabel10.getBackground());
        if(newColor!=null) {
            jLabel10.setBackground(newColor);
            instrument.framePanel.updateAreaItem_DataArea();
        }
    }

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {
      instrument.framePanel.updateAreaItem_DataArea();
    }

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {
      instrument.framePanel.updateAreaItem_DataArea();
    }

    private void jTextField4FocusLost(java.awt.event.FocusEvent evt) {
      instrument.framePanel.updateAreaItem_DataArea();
    }

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {
       instrument.framePanel.updateAreaItem_DataArea();
    }

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {
        instrument.framePanel.updateAreaItem_DataArea();
    }

    private void byDevice1ActionPerformed(java.awt.event.ActionEvent evt) {
             updateDataAreaType();
    }
void showMenuItem(String sel){
    if(instrument.editUI.get(sel)==null) return;
    String info[]=ylib.csvlinetoarray((String)instrument.editUI.get(sel));
    if(info[2].toLowerCase().trim().equals("s")) jCheckBox49.setSelected(true); else jCheckBox49.setSelected(false);
    jTextField22.setText(info[1]);
}
public void updateDataAreaPanel(){

  if(instrument.editUI!=null && instrument.editUI.get("data area")!=null){
    instrument.dataAreaRatio=1.0;
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
    String info[]=ylib.csvlinetoarray((String)instrument.editUI.get("data area"));
    int w3=380,h3=280;
    double ratio3=((double)w3)/((double)h3);
    int w4=w3,h4=h3;

    if(info.length > 5 && info[5].length()>0 && info.length > 6 && info[6].length()>0) {
            w4=(int)(Double.parseDouble(info[5]) * ((double)frameWidth));
            h4=(int)(Double.parseDouble(info[6]) * ((double)frameHeight));
    }
    double ratio4=((double)w4)/((double)h4);
    if(w4>w3 || h4>h3){
        if(w4>w3 && h4>h3){
            if(ratio4>=ratio3){
                instrument.dataAreaRatio=((double)w4)/((double)w3);
            h4=(int)(((double)h4)*((double)w3)/((double)w4));
            w4=w3;
            } else {
                instrument.dataAreaRatio=((double)h4)/((double)h3);
            w4=(int)(((double)w4)*((double)h3)/((double)h4));
            h4=h3;
            }
        } else if(w4>w3){
            instrument.dataAreaRatio=((double)w4)/((double)w3);
            h4=(int)(((double)h4)*((double)w3)/((double)w4));
            w4=w3;
        } else if(h4>h3){
            instrument.dataAreaRatio=((double)h4)/((double)h3);
            w4=(int)(((double)w4)*((double)h3)/((double)h4));
            h4=h3;
        }
    } else {
        instrument.dataAreaRatio=1.0;
    }

    jPanel162.setBounds(360, 10, w4, h4);
  }
}
void updateFramePanel(){

    int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    int frameWidth=0,frameHeight=0;
    String info[]=new String[3];
    if(instrument.editUI.get("frame")!=null){

    String frameInfo=(String)instrument.editUI.get("frame");
    info=ylib.csvlinetoarray(frameInfo);

    if(info.length>13 && info[13].equalsIgnoreCase("f")){
        frameWidth=screenWidth; frameHeight=screenHeight;
    } else {
    if(info.length > 5 && info[5].length()>0){
        if(info[2].equals("%")) frameWidth=(int)(Double.parseDouble(info[5]) * ((double)screenWidth)); else frameWidth=(int)Double.parseDouble(info[5]);
    }
    if(info.length > 6 && info[6].length()>0){
        if(info[2].equals("%")) frameHeight=(int)(Double.parseDouble(info[6]) * ((double)screenHeight)); else frameHeight=(int)Double.parseDouble(info[6]);
    }
    }
    }

    double ratio2=((double)frameWidth)/((double)frameHeight);
    int w3=460,h3=250;
    double ratio3=((double)w3)/((double)h3);
    

     if(ratio2 >  ratio3 ) {
           h3=(int)(((double)w3)/ratio2);
    } else {
          w3=(int)(((double)h3)*ratio2);
      }

    jPanel140.setBounds(340, 20, w3, h3);

}
void updateMenuItem(){
   String sel=null;
   if(jComboBox21.getSelectedIndex()!=-1){
    sel=(String)jComboBox21.getSelectedItem();
    if(instrument.editUI.get(sel)==null) return;
    String info[]=ylib.csvlinetoarray((String)instrument.editUI.get(sel));
    if(jCheckBox49.isSelected()) info[2]="s"; else info[2]="h";
    info[1]=jTextField22.getText().trim();
    if(info[1].length()<1) info[1]=info[0];
    instrument.editUI.put(sel,ylib.arrayToCsvLine(info));

     }
}
void updateChartTypeItem(){
    if(instrument.editUI.get("misc")==null) return;
    String info[]=ylib.csvlinetoarray((String)instrument.editUI.get("misc"));
    if(byStation.isSelected()) info[1]="station";
    else if(byDeviceName.isSelected()) info[1]="devicename";
    else if(byModel.isSelected()) info[1]="model";
    else if(byDataName.isSelected()) info[1]="dataname";
    else if(byChartGroup.isSelected()) info[1]="chartgroup";
    info[3]=(String)chartGroupCB.getSelectedItem();
    instrument.editUI.put("misc", ylib.arrayToCsvLine(info));
}
void updateDataAreaType(){
    if(instrument.editUI.get("misc")==null) return;
    String info[]=ylib.csvlinetoarray((String)instrument.editUI.get("misc"));
    if(byStation1.isSelected()) info[2]="station";
    else if(byDeviceName1.isSelected()) info[2]="devicename";
    else if(byModel1.isSelected()) info[2]="model";
    else if(byDevice1.isSelected()) info[2]="device";
    else if(byDataName1.isSelected()) info[2]="dataname";
    else if(showAll.isSelected()) info[2]="showall";
    instrument.editUI.put("misc", ylib.arrayToCsvLine(info));
}
  void updateFrameItem(){
    if(instrument.editUI.get("frame")==null) return;
    String info[]=ylib.csvlinetoarray((String)instrument.editUI.get("frame"));
    info[1]=jTextField45.getText().trim();
    if(jCheckBox38.isSelected()) {
        info[2]="%";
    info[3]= (instrument.isNumeric(jTextField74.getText())? String.valueOf(Double.parseDouble(jTextField74.getText())/100.0):"0.0");
    info[4]= (instrument.isNumeric(jTextField75.getText())? String.valueOf(Double.parseDouble(jTextField75.getText())/100.0):"0.0");
    info[5]= (instrument.isNumeric(jTextField76.getText())? String.valueOf(Double.parseDouble(jTextField76.getText())/100.0):"0.0");
    info[6]= (instrument.isNumeric(jTextField77.getText())? String.valueOf(Double.parseDouble(jTextField77.getText())/100.0):"0.0");
    } else {
        info[2]="e";
    info[3]= (instrument.isNumeric(jTextField74.getText())? jTextField74.getText():"0.0");
    info[4]= (instrument.isNumeric(jTextField75.getText())? jTextField75.getText():"0.0");
    info[5]= (instrument.isNumeric(jTextField76.getText())? jTextField76.getText():"0.0");
    info[6]= (instrument.isNumeric(jTextField77.getText())? jTextField77.getText():"0.0");
    }

    info[7]=""+instrument.uiPanel2.jLabel61.getBackground().getRGB();
    if(jCheckBox43.isSelected()) info[8]="c"; else info[8]="e";
    info[9]= instrument.uiPanel2.jTextField11.getText().trim();
    if(jCheckBox44.isSelected()) info[13]="f"; else info[13]="e";
    if(jCheckBox45.isSelected()) info[14]="r"; else info[14]="e";
    instrument.editUI.put("frame",ylib.arrayToCsvLine(info));

    updateFramePanel();
    updateDataAreaPanel();
    instrument.framePanel.invalidate();
    instrument.dataPanel2.invalidate();
  }

    private javax.swing.JButton btnApplySetting_ui;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    javax.swing.JRadioButton byChartGroup;
    javax.swing.JRadioButton byDataName;
    javax.swing.JRadioButton byDataName1;
    private javax.swing.JRadioButton byDevice1;
    javax.swing.JRadioButton byDeviceName;
    javax.swing.JRadioButton byDeviceName1;
    javax.swing.JRadioButton byModel;
    javax.swing.JRadioButton byModel1;
    javax.swing.JRadioButton byStation;
    javax.swing.JRadioButton byStation1;
    javax.swing.JComboBox chartGroupCB;
    javax.swing.JPanel dataPanel;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton57;
    javax.swing.JCheckBox jCheckBox1;
    javax.swing.JCheckBox jCheckBox2;
    javax.swing.JCheckBox jCheckBox3;
    javax.swing.JCheckBox jCheckBox37;
    javax.swing.JCheckBox jCheckBox38;
    javax.swing.JCheckBox jCheckBox4;
    javax.swing.JCheckBox jCheckBox43;
    javax.swing.JCheckBox jCheckBox44;
    javax.swing.JCheckBox jCheckBox45;
    javax.swing.JCheckBox jCheckBox46;
    javax.swing.JCheckBox jCheckBox49;
    javax.swing.JCheckBox jCheckBox64;
    javax.swing.JCheckBox jCheckBox65;
    javax.swing.JCheckBox jCheckBox66;
    javax.swing.JComboBox jComboBox1;
    javax.swing.JComboBox jComboBox15;
    javax.swing.JComboBox jComboBox21;
    javax.swing.JComboBox jComboBox36;
    javax.swing.JComboBox jComboBox53;
    javax.swing.JComboBox jComboBox54;
    private javax.swing.JLabel jLabel1;
    javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel105;
    javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel137;
    javax.swing.JLabel jLabel139;
    javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel15;
    javax.swing.JLabel jLabel154;
    javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel168;
    javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel311;
    private javax.swing.JLabel jLabel318;
    private javax.swing.JLabel jLabel319;
    private javax.swing.JLabel jLabel320;
    private javax.swing.JLabel jLabel321;
    private javax.swing.JLabel jLabel322;
    private javax.swing.JLabel jLabel323;
    javax.swing.JLabel jLabel324;
    private javax.swing.JLabel jLabel325;
    private javax.swing.JLabel jLabel326;
    private javax.swing.JLabel jLabel327;
    javax.swing.JLabel jLabel328;
    javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    javax.swing.JLabel jLabel61;
    javax.swing.JLabel jLabel62;
    javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel110;
    private javax.swing.JPanel jPanel123;
    javax.swing.JPanel jPanel140;
    private javax.swing.JPanel jPanel141;
    private javax.swing.JPanel jPanel143;
    private javax.swing.JPanel jPanel144;
    private javax.swing.JPanel jPanel145;
    private javax.swing.JPanel jPanel146;
    private javax.swing.JPanel jPanel147;
    javax.swing.JPanel jPanel162;
    private javax.swing.JPanel jPanel163;
    private javax.swing.JPanel jPanel164;
    private javax.swing.JPanel jPanel165;
    private javax.swing.JPanel jPanel166;
    private javax.swing.JPanel jPanel167;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    javax.swing.JTabbedPane jTabbedPane4;
    javax.swing.JTextField jTextField1;
    javax.swing.JTextField jTextField10;
    javax.swing.JTextField jTextField11;
    javax.swing.JTextField jTextField2;
    javax.swing.JTextField jTextField22;
    javax.swing.JTextField jTextField3;
    javax.swing.JTextField jTextField4;
    javax.swing.JTextField jTextField45;
    javax.swing.JTextField jTextField5;
    javax.swing.JTextField jTextField6;
    javax.swing.JTextField jTextField61;
    javax.swing.JTextField jTextField7;
    javax.swing.JTextField jTextField73;
    javax.swing.JTextField jTextField74;
    javax.swing.JTextField jTextField75;
    javax.swing.JTextField jTextField76;
    javax.swing.JTextField jTextField77;
    javax.swing.JTextField jTextField84;
    javax.swing.JTextField jTextField85;
    javax.swing.JTextField jTextField86;
    javax.swing.JTextField jTextField87;
    javax.swing.JTextField jTextField88;
    javax.swing.JTextField jTextField9;
    javax.swing.JPanel mainPanel;
    javax.swing.JPanel miscPanel;
    javax.swing.JRadioButton showAll;

}