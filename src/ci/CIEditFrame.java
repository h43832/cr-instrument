
package ci;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javax.swing.*;
import y.ylib.ylib;
public class CIEditFrame extends javax.swing.JFrame {
  ResourceBundle bundle2=java.util.ResourceBundle.getBundle("ci/Bundle");
 CrInstrument instrument;
 boolean firstDone[]=new boolean[1],clipboardHasSomething=false;
 String original="",previousTA[]=new String[1];
 JTextArea selectedTA=null;
 int selectedInx=0;
 CISearchReplace searchReplace;

  JMenuItem itemUndo = new JMenuItem(bundle2.getString("CrInstrument.xy.msg114"));
  JMenuItem itemCopy = new JMenuItem(bundle2.getString("CrInstrument.xy.msg115"));
  JMenuItem itemPaste = new JMenuItem(bundle2.getString("CrInstrument.xy.msg116"));
  JMenuItem itemCut = new JMenuItem(bundle2.getString("CrInstrument.xy.msg117"));
  JMenuItem itemSelectAll = new JMenuItem(bundle2.getString("CrInstrument.xy.msg118"));
  JMenuItem itemSearchReplace = new JMenuItem(bundle2.getString("CrInstrument.xy.msg119"));
  JPopupMenu popupMenu = new JPopupMenu("Popup");

 String firstLine=bundle2.getString("CrInstrument.xy.msg120");
  public CIEditFrame(CrInstrument p) {
    initComponents();
      instrument=p;
        if(searchReplace==null) searchReplace=new CISearchReplace(this,false);;
        int width=Toolkit.getDefaultToolkit().getScreenSize().width;
        int h=Toolkit.getDefaultToolkit().getScreenSize().height-20;
        int w2=1024;
        int h2=712;
        setSize(w2,h2);
        setLocation((width-w2)/2,(h-h2)/2);

        setIconImage(instrument.iconImage);
        init();
  }
  void init(){
    selectedTA=jTextArea1;

        popupMenu.add(itemUndo);
        popupMenu.add(itemCopy);
        popupMenu.add(itemPaste);
        popupMenu.add(itemCut);   
        popupMenu.add(itemSelectAll);
        popupMenu.add(itemSearchReplace);   
      itemUndo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String tmp=selectedTA.getText();
        selectedTA.setText(previousTA[selectedInx]);
        previousTA[selectedInx]=tmp;
      }
    });
   itemCopy.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        selectedTA.copy();
        clipboardHasSomething=true;
      }
    });
   itemPaste.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        previousTA[selectedInx]=selectedTA.getText();
        selectedTA.paste();
        firstDone[selectedInx]=true;
      }
    });
   itemCut.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        previousTA[selectedInx]=selectedTA.getText();
        selectedTA.cut();
        clipboardHasSomething=true;
        firstDone[selectedInx]=true;
      }
    });
   itemSelectAll.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        selectedTA.selectAll();
      }
    });
      itemSearchReplace.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        searchReplace.setAlwaysOnTop(true);
        searchReplace.setVisible(true);
      }
    });  

  }
   void taPopup(java.awt.event.MouseEvent evt){
      if (evt.isPopupTrigger()) {
        java.awt.datatransfer.Clipboard systemClipboard = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
        java.awt.datatransfer.Transferable clipboardContents = systemClipboard.getContents(null);

        if (clipboardContents.equals(null)) {
          clipboardHasSomething=false;
        } else clipboardHasSomething=true;
	  selectedTA=(JTextArea)evt.getSource();
          itemUndo.setEnabled(firstDone[selectedInx]);
          itemPaste.setEnabled(clipboardHasSomething);
          itemCopy.setEnabled(selectedTA.getSelectedText()!=null && selectedTA.getSelectedText().length()>0);
          itemCut.setEnabled(selectedTA.getSelectedText()!=null && selectedTA.getSelectedText().length()>0);
          itemSelectAll.setEnabled(true);
          popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
}
  public void setContent(){
    int lineN=0;
    StringBuffer sb=new StringBuffer(firstLine);
    Iterator it = instrument.sensors.values().iterator();

    for (; it.hasNext();) {

      sb.append("\r\n" + (String) it.next());

      lineN++;
    }
    jTextArea1.setText(sb.toString());
      if(lineN==1) resetDeviceAddr();
      original=removeFirstLine();
   }
public void setFile(String filename){
    try{

      FileInputStream in=new FileInputStream(filename);
      BufferedReader d = new BufferedReader(new InputStreamReader(in));
     StringBuffer sb=new StringBuffer(firstLine);
     int lineN=0;
     boolean first=false;
      while(true){

        String str1=d.readLine();

        if(str1==null) {in.close(); d.close(); break; }
        str1=str1.trim();
        if(str1.length()>0){
          sb.append((first? "":"\r\n")+str1);
          first=false;
          lineN++;
        }
      }
      jTextArea1.setText(sb.toString());
      if(lineN==1) resetDeviceAddr();
      original=removeFirstLine();
    } catch (IOException e) {

        System.out.println("reading file (filename="+instrument.sensorFile+") IOException, error message: "+e.getMessage()+"\r\n");
        e.printStackTrace();
      }
      catch (Exception e) {

        System.out.println("reading file (filename="+instrument.sensorFile+") error, error message: "+e.getMessage()+"\n");
        e.printStackTrace();
      }
}

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnApply = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ci/Bundle"); 
        setTitle(bundle.getString("CIEditFrame.title")); 
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jButton1.setFont(jButton1.getFont());
        jButton1.setText(bundle.getString("CIEditFrame.jButton1.text")); 
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jButton3.setFont(jButton3.getFont());
        jButton3.setText(bundle.getString("CIEditFrame.jButton3.text")); 
        jPanel1.add(jButton3);

        jButton2.setFont(jButton2.getFont());
        jButton2.setText(bundle.getString("CIEditFrame.jButton2.text")); 
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        btnApply.setFont(btnApply.getFont());
        btnApply.setText(bundle.getString("CIEditFrame.btnApply.text")); 
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });
        jPanel1.add(btnApply);

        jButton4.setFont(jButton4.getFont());
        jButton4.setText(bundle.getString("CIEditFrame.jButton4.text")); 
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextArea1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextArea1MouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    apply();
      saveFile(instrument.sensorFile);

    this.setVisible(false);
  }

  private void formWindowClosing(java.awt.event.WindowEvent evt) {
    String tmp=removeFirstLine();

    if(!tmp.equals(original)){

      int an=JOptionPane.showConfirmDialog(this, bundle2.getString("CrInstrument.xy.msg109"), bundle2.getString("CrInstrument.xy.msg110"), JOptionPane.YES_NO_CANCEL_OPTION);
      if(an==JOptionPane.YES_OPTION)  {saveFile(instrument.sensorFile); setVisible(false);}
      else if(an==JOptionPane.NO_OPTION) setVisible(false);
    } else setVisible(false);
  }

  private void jTextArea1MousePressed(java.awt.event.MouseEvent evt) {
  taPopup(evt);
  }

  private void jTextArea1MouseReleased(java.awt.event.MouseEvent evt) {
  taPopup(evt);
  }

  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
  resetDeviceAddr();
  }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
      setVisible(false);
    }

    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {
        apply();
    }
void apply(){
   String s=removeFirstLine();
    TreeMap senso=new TreeMap();
   try{
      String str1="";
      StringReader sin=new StringReader(s);
        BufferedReader d = new BufferedReader(sin);
        while(true){
          str1=d.readLine();
          if (str1 == null) {
            sin.close();
            d.close();
            break;
          }
          if (str1.length() > 5) {
            String info[] = ylib.csvlinetoarray(str1);
            if(info.length>4){
              senso.put(info[0] + "," + info[1]+","+info[2]+","+info[3]+","+info[4], str1);
            }
          }
        }
        d.close();
        instrument.sensors=senso;
    } catch (IOException e){e.printStackTrace();}
   instrument.updateSensorTable();
}
void resetDeviceAddr(){
      String content=jTextArea1.getText();  
    boolean firstLine=true;
    StringBuffer sb=new StringBuffer();
    try{
        String str1="";
          StringReader sin=new StringReader(content);
          BufferedReader d = new BufferedReader(sin);
          boolean modifiedAll=false;
          while(true){
            str1=d.readLine();
            if(str1==null) {d.close(); break; }
            if(firstLine){
              if(str1.length()>0) {

              }  else {
              }
            } else {
                if(str1.length()==0) {}
                 else {
                  boolean modifiedLine=false;
                  String info[]=ylib.csvlinetoarray(str1);
                  if(info.length>11)  {info[11]=""; modifiedLine=true; modifiedAll=true;}
                  if(info.length>23)  {info[23]=""; modifiedLine=true; modifiedAll=true;}
                  if(info.length>27)  {info[27]=""; modifiedLine=true; modifiedAll=true;}
                  if(modifiedLine) str1=ylib.arrayToCsvLine(info);
                 }
            }
            sb.append(firstLine? str1:"\r\n"+str1);
            if(firstLine)  firstLine=false;
          }
          if(modifiedAll) jTextArea1.setText(sb.toString());
      } catch (IOException e){e.printStackTrace();}
}
void resetOffsetValue(){
      String content=jTextArea1.getText();  
    boolean firstLine=true;
    StringBuffer sb=new StringBuffer();
    try{
        String str1="";
          StringReader sin=new StringReader(content);
          BufferedReader d = new BufferedReader(sin);
          boolean modifiedAll=false;
          while(true){
            str1=d.readLine();
            if(str1==null) {d.close(); break; }
            if(firstLine){
              if(str1.length()>0) {

              }  else {
              }
            } else {
                if(str1.length()==0) {}
                 else {
                  boolean modifiedLine=false;
                  String info[]=ylib.csvlinetoarray(str1);
                  if(info.length>11)  {info[12]="100000000.0"; modifiedLine=true; modifiedAll=true;}
                  if(modifiedLine) str1=ylib.arrayToCsvLine(info);
                 }
            }
            sb.append(firstLine? str1:"\r\n"+str1);
            if(firstLine)  firstLine=false;
          }
          if(modifiedAll) jTextArea1.setText(sb.toString());
      } catch (IOException e){e.printStackTrace();}
}
  public boolean saveFile(String filename){
  if(chk()){
   if(instrument.wn.w.chkValue(instrument.props.getProperty("ci-demo")) && instrument.wn.chkProps("run_my_ap_only")) return false;
   String str1=removeFirstLine();

  try{
    FileOutputStream out = new FileOutputStream (filename);
    byte [] b=str1.getBytes();
    out.write(b);
    out.close();
    

  }catch(IOException e){e.printStackTrace();}   
   } else return false;
     return true;
}

String removeFirstLine(){
    String content=jTextArea1.getText(); 
    boolean first=true;
    int inx=0;
    StringBuffer sb=new StringBuffer();
        try{
        String str1="";
          StringReader sin=new StringReader(content);
          BufferedReader d = new BufferedReader(sin);
          while(true){
            str1=d.readLine();
            if(str1==null) {d.close(); break; }
            if(first){
              if(str1.length()>0) {inx++; first=false;}
              else {}
            }else {sb.append((inx<2? "":"\r\n")+str1); inx++; first=false;}
          }
      } catch (IOException e){e.printStackTrace();}
    return sb.toString();
}

boolean chk(){
    String content=jTextArea1.getText();  
    boolean first=true,rtn=true;
    String itemName[]=ylib.csvlinetoarray(firstLine);
    int itemN=itemName.length,lineN=0;
    StringBuffer sb=new StringBuffer();

    try{
        String str1="";
          StringReader sin=new StringReader(content);
          BufferedReader d = new BufferedReader(sin);
          boolean modifiedAll=false;
          while(true){
            str1=d.readLine();
            if(str1==null) {d.close(); break; }
            lineN++;
            if(first){
              if(str1.length()>0) {
                if(!str1.equals(firstLine)) {JOptionPane.showMessageDialog(this, "第一為標題行, 內容請勿修改。"); 
                  sb.append(firstLine).append("\r\n").append(removeFirstLine()); 

                  jTextArea1.setText(sb.toString());
                  return false;

                } 
                first=false;
              }  else {
              }
            } else {
                if(str1.length()==0) {}
                 else {
                  boolean modifiedLine=false;

                String info[]=ylib.csvlinetoarray(str1);
                int itemN2=info.length;
                if(itemN>itemN2){JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg121")+" "+lineN+" "+bundle2.getString("CrInstrument.xy.msg122")+" "+itemN2+" "+bundle2.getString("CrInstrument.xy.msg123")+itemN+" "+bundle2.getString("CrInstrument.xy.msg124"));return false;}

                if(!CrInstrument.isNumeric(info[5])) {JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg125")+" "+lineN+" "+bundle2.getString("CrInstrument.xy.msg126")+" \""+itemName[5]+"\" "+bundle2.getString("CrInstrument.xy.msg127")+" \""+info[5]+"\" "+bundle2.getString("CrInstrument.xy.msg128")); return false;}
                if(!CrInstrument.isNumeric(info[6])) {JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg125")+" "+lineN+" "+bundle2.getString("CrInstrument.xy.msg126")+" \""+itemName[6]+"\" "+bundle2.getString("CrInstrument.xy.msg127")+" \""+info[6]+"\" "+bundle2.getString("CrInstrument.xy.msg128")); return false;}
                if(!CrInstrument.isNumeric(info[7])) {JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg125")+" "+lineN+" "+bundle2.getString("CrInstrument.xy.msg126")+" \""+itemName[7]+"\" "+bundle2.getString("CrInstrument.xy.msg127")+" \""+info[7]+"\" "+bundle2.getString("CrInstrument.xy.msg128")); return false;}
                if(!CrInstrument.isNumeric(info[8])) {JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg125")+" "+lineN+" "+bundle2.getString("CrInstrument.xy.msg126")+" \""+itemName[8]+"\" "+bundle2.getString("CrInstrument.xy.msg127")+" \""+info[8]+"\" "+bundle2.getString("CrInstrument.xy.msg128")); return false;}
                if(!CrInstrument.isNumeric(info[14])) {JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg125")+" "+lineN+" "+bundle2.getString("CrInstrument.xy.msg126")+" \""+itemName[1]+"\" "+bundle2.getString("CrInstrument.xy.msg127")+" \""+info[1]+"\" "+bundle2.getString("CrInstrument.xy.msg128")); return false;}
                if(!CrInstrument.isNumeric(info[20])) {JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg125")+" "+lineN+" "+bundle2.getString("CrInstrument.xy.msg126")+" \""+itemName[2]+"\" "+bundle2.getString("CrInstrument.xy.msg127")+" \""+info[2]+"\" "+bundle2.getString("CrInstrument.xy.msg128")); return false;}
                if(!CrInstrument.isNumeric(info[22])) {JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg125")+" "+lineN+" "+bundle2.getString("CrInstrument.xy.msg126")+" \""+itemName[3]+"\" "+bundle2.getString("CrInstrument.xy.msg127")+" \""+info[3]+"\" "+bundle2.getString("CrInstrument.xy.msg128")); return false;}
                if(!CrInstrument.isNumeric(info[24])) {JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg125")+" "+lineN+" "+bundle2.getString("CrInstrument.xy.msg126")+" \""+itemName[4]+"\" "+bundle2.getString("CrInstrument.xy.msg127")+" \""+info[4]+"\" "+bundle2.getString("CrInstrument.xy.msg128")); return false;}

                double d5=Double.parseDouble(info[5]),d6=Double.parseDouble(info[6]),d7=Double.parseDouble(info[7]),d8=Double.parseDouble(info[8]);
                if(d5<=d6) {JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg129")+" "+lineN+" "+bundle2.getString("CrInstrument.xy.msg130")+" \""+info[5]+"\" "+bundle2.getString("CrInstrument.xy.msg131")+" \""+info[6]+"\"."); return false;}
                if(d7<=d8) {JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg132")+" "+lineN+" "+bundle2.getString("CrInstrument.xy.msg133")+" \""+info[7]+"\" "+bundle2.getString("CrInstrument.xy.msg134")+" \""+info[8]+"\"."); return false;}

                if(info[1].length()==1) {info[1]="0"+info[1]; modifiedLine=true; modifiedAll=true;}
                if(modifiedLine) str1=ylib.arrayToCsvLine(info);
                 }
            }
            sb.append(first? "":"\r\n"+str1);
          }
          if(modifiedAll) jTextArea1.setText(sb.toString());
      } catch (IOException e){e.printStackTrace();}

    return rtn;
}
  /**
   * @param args the command line arguments
   */

    private javax.swing.JButton btnApply;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;

}