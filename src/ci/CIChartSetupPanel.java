
package ci;

import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;
import y.ylib.ylib;

/**
 *
 * @author peter
 */
public class CIChartSetupPanel extends javax.swing.JPanel {

    CrInstrument instrument;
    public DefaultListModel chartListModel = new DefaultListModel();
    public CIChartSetupPanel(CrInstrument instrument) {
        initComponents();
        this.instrument=instrument;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    private void initComponents() {

        jScrollPane9 = new javax.swing.JScrollPane();
        chartList = new javax.swing.JList(chartListModel);
        jLabel82 = new javax.swing.JLabel();
        jPanel93 = new javax.swing.JPanel();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jLabel146 = new javax.swing.JLabel();
        jTextField44 = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        jComboBox42 = new javax.swing.JComboBox();
        jPanel96 = new javax.swing.JPanel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jTextField33 = new javax.swing.JTextField();
        jLabel117 = new javax.swing.JLabel();
        jTextField34 = new javax.swing.JTextField();
        jCheckBox12 = new javax.swing.JCheckBox();
        jPanel97 = new javax.swing.JPanel();
        jLabel118 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jTextField35 = new javax.swing.JTextField();
        jLabel119 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jTextField36 = new javax.swing.JTextField();
        jLabel120 = new javax.swing.JLabel();
        jTextField37 = new javax.swing.JTextField();
        jLabel121 = new javax.swing.JLabel();
        jTextField38 = new javax.swing.JTextField();
        jLabel122 = new javax.swing.JLabel();
        jPanel98 = new javax.swing.JPanel();
        jLabel123 = new javax.swing.JLabel();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jPanel99 = new javax.swing.JPanel();
        jLabel124 = new javax.swing.JLabel();
        jCheckBox15 = new javax.swing.JCheckBox();
        jCheckBox16 = new javax.swing.JCheckBox();
        jPanel100 = new javax.swing.JPanel();
        jCheckBox19 = new javax.swing.JCheckBox();
        jCheckBox20 = new javax.swing.JCheckBox();
        jPanel101 = new javax.swing.JPanel();
        jLabel129 = new javax.swing.JLabel();
        jCheckBox21 = new javax.swing.JCheckBox();
        jCheckBox22 = new javax.swing.JCheckBox();
        jPanel103 = new javax.swing.JPanel();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jTextField47 = new javax.swing.JTextField();
        jLabel133 = new javax.swing.JLabel();
        jTextField48 = new javax.swing.JTextField();
        jLabel134 = new javax.swing.JLabel();
        jTextField49 = new javax.swing.JTextField();
        jLabel135 = new javax.swing.JLabel();
        jTextField50 = new javax.swing.JTextField();
        jLabel136 = new javax.swing.JLabel();
        jButton44 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jPanel106 = new javax.swing.JPanel();
        jLabel143 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jTextField51 = new javax.swing.JTextField();
        jButton42 = new javax.swing.JButton();
        jPanel111 = new javax.swing.JPanel();
        jCheckBox24 = new javax.swing.JCheckBox();
        jCheckBox26 = new javax.swing.JCheckBox();
        jTextField52 = new javax.swing.JTextField();
        jPanel142 = new javax.swing.JPanel();
        jPanel112 = new javax.swing.JPanel();
        jCheckBox23 = new javax.swing.JCheckBox();
        jTextField43 = new javax.swing.JTextField();
        jPanel148 = new javax.swing.JPanel();
        jCheckBox13 = new javax.swing.JCheckBox();
        jPanel149 = new javax.swing.JPanel();
        jCheckBox14 = new javax.swing.JCheckBox();

        setLayout(null);

        chartList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chartListMouseClicked(evt);
            }
        });
        chartList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                chartListKeyReleased(evt);
            }
        });
        jScrollPane9.setViewportView(chartList);

        add(jScrollPane9);
        jScrollPane9.setBounds(20, 50, 80, 220);

        jLabel82.setFont(jLabel82.getFont());
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ci/Bundle"); 
        jLabel82.setText(bundle.getString("CIChartSetupPanel.jLabel82.text")); 
        add(jLabel82);
        jLabel82.setBounds(20, 30, 70, 20);

        jPanel93.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jButton35.setFont(jButton35.getFont());
        jButton35.setText(bundle.getString("CIChartSetupPanel.jButton35.text")); 
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });
        jPanel93.add(jButton35);

        jButton36.setFont(jButton36.getFont());
        jButton36.setText(bundle.getString("CIChartSetupPanel.jButton36.text")); 
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });
        jPanel93.add(jButton36);

        jButton37.setFont(jButton37.getFont());
        jButton37.setText(bundle.getString("CIChartSetupPanel.jButton37.text")); 
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });
        jPanel93.add(jButton37);

        jLabel146.setFont(jLabel146.getFont());
        jLabel146.setText(bundle.getString("CIChartSetupPanel.jLabel146.text")); 
        jPanel93.add(jLabel146);

        jTextField44.setFont(jTextField44.getFont());
        jTextField44.setText(bundle.getString("CIChartSetupPanel.jTextField44.text")); 
        jTextField44.setPreferredSize(new java.awt.Dimension(156, 25));
        jPanel93.add(jTextField44);

        jLabel84.setFont(jLabel84.getFont());
        jLabel84.setText(bundle.getString("CIChartSetupPanel.jLabel84.text")); 
        jPanel93.add(jLabel84);

        jComboBox42.setEditable(true);
        jComboBox42.setFont(jComboBox42.getFont());
        jPanel93.add(jComboBox42);

        add(jPanel93);
        jPanel93.setBounds(110, 30, 890, 40);

        jPanel96.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel115.setFont(jLabel115.getFont());
        jLabel115.setText(bundle.getString("CIChartSetupPanel.jLabel115.text")); 
        jPanel96.add(jLabel115);

        jLabel116.setFont(jLabel116.getFont());
        jLabel116.setText(bundle.getString("CIChartSetupPanel.jLabel116.text")); 
        jPanel96.add(jLabel116);

        jTextField33.setText(bundle.getString("CIChartSetupPanel.jTextField33.text")); 
        jTextField33.setPreferredSize(new java.awt.Dimension(106, 25));
        jPanel96.add(jTextField33);

        jLabel117.setFont(jLabel117.getFont());
        jLabel117.setText(bundle.getString("CIChartSetupPanel.jLabel117.text")); 
        jPanel96.add(jLabel117);

        jTextField34.setText(bundle.getString("CIChartSetupPanel.jTextField34.text")); 
        jTextField34.setPreferredSize(new java.awt.Dimension(106, 25));
        jPanel96.add(jTextField34);

        jCheckBox12.setFont(jCheckBox12.getFont());
        jCheckBox12.setText(bundle.getString("CIChartSetupPanel.jCheckBox12.text")); 
        jPanel96.add(jCheckBox12);

        add(jPanel96);
        jPanel96.setBounds(120, 110, 880, 40);

        jPanel97.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel118.setFont(jLabel118.getFont());
        jLabel118.setText(bundle.getString("CIChartSetupPanel.jLabel118.text")); 
        jPanel97.add(jLabel118);

        jRadioButton2.setFont(jRadioButton2.getFont());
        jRadioButton2.setText(bundle.getString("CIChartSetupPanel.jRadioButton2.text")); 
        jPanel97.add(jRadioButton2);

        jTextField35.setText(bundle.getString("CIChartSetupPanel.jTextField35.text")); 
        jTextField35.setPreferredSize(new java.awt.Dimension(56, 25));
        jPanel97.add(jTextField35);

        jLabel119.setFont(jLabel119.getFont());
        jLabel119.setText(bundle.getString("CIChartSetupPanel.jLabel119.text")); 
        jPanel97.add(jLabel119);

        jRadioButton3.setFont(jRadioButton3.getFont());
        jRadioButton3.setText(bundle.getString("CIChartSetupPanel.jRadioButton3.text")); 
        jPanel97.add(jRadioButton3);

        jTextField36.setText(bundle.getString("CIChartSetupPanel.jTextField36.text")); 
        jTextField36.setPreferredSize(new java.awt.Dimension(56, 25));
        jPanel97.add(jTextField36);

        jLabel120.setFont(jLabel120.getFont());
        jLabel120.setText(bundle.getString("CIChartSetupPanel.jLabel120.text")); 
        jPanel97.add(jLabel120);

        jTextField37.setText(bundle.getString("CIChartSetupPanel.jTextField37.text")); 
        jTextField37.setPreferredSize(new java.awt.Dimension(36, 25));
        jPanel97.add(jTextField37);

        jLabel121.setFont(jLabel121.getFont());
        jLabel121.setText(bundle.getString("CIChartSetupPanel.jLabel121.text")); 
        jPanel97.add(jLabel121);

        jTextField38.setText(bundle.getString("CIChartSetupPanel.jTextField38.text")); 
        jTextField38.setPreferredSize(new java.awt.Dimension(36, 25));
        jPanel97.add(jTextField38);

        jLabel122.setFont(jLabel122.getFont());
        jLabel122.setText(bundle.getString("CIChartSetupPanel.jLabel122.text")); 
        jPanel97.add(jLabel122);

        add(jPanel97);
        jPanel97.setBounds(150, 190, 840, 40);

        jPanel98.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel123.setFont(jLabel123.getFont());
        jLabel123.setText(bundle.getString("CIChartSetupPanel.jLabel123.text")); 
        jPanel98.add(jLabel123);

        jRadioButton4.setFont(jRadioButton4.getFont());
        jRadioButton4.setText(bundle.getString("CIChartSetupPanel.jRadioButton4.text")); 
        jPanel98.add(jRadioButton4);

        jRadioButton5.setFont(jRadioButton5.getFont());
        jRadioButton5.setText(bundle.getString("CIChartSetupPanel.jRadioButton5.text")); 
        jPanel98.add(jRadioButton5);

        add(jPanel98);
        jPanel98.setBounds(150, 230, 510, 40);

        jPanel99.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel124.setFont(jLabel124.getFont());
        jLabel124.setText(bundle.getString("CIChartSetupPanel.jLabel124.text")); 
        jPanel99.add(jLabel124);

        jCheckBox15.setFont(jCheckBox15.getFont());
        jCheckBox15.setText(bundle.getString("CIChartSetupPanel.jCheckBox15.text")); 
        jPanel99.add(jCheckBox15);

        jCheckBox16.setFont(jCheckBox16.getFont());
        jCheckBox16.setText(bundle.getString("CIChartSetupPanel.jCheckBox16.text")); 
        jPanel99.add(jCheckBox16);

        add(jPanel99);
        jPanel99.setBounds(150, 310, 550, 40);

        jPanel100.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox19.setFont(jCheckBox19.getFont());
        jCheckBox19.setText(bundle.getString("CIChartSetupPanel.jCheckBox19.text")); 
        jPanel100.add(jCheckBox19);

        jCheckBox20.setFont(jCheckBox20.getFont());
        jCheckBox20.setText(bundle.getString("CIChartSetupPanel.jCheckBox20.text")); 
        jPanel100.add(jCheckBox20);

        add(jPanel100);
        jPanel100.setBounds(170, 350, 530, 33);

        jPanel101.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel129.setFont(jLabel129.getFont());
        jLabel129.setText(bundle.getString("CIChartSetupPanel.jLabel129.text")); 
        jPanel101.add(jLabel129);

        jCheckBox21.setFont(jCheckBox21.getFont());
        jCheckBox21.setText(bundle.getString("CIChartSetupPanel.jCheckBox21.text")); 
        jPanel101.add(jCheckBox21);

        jCheckBox22.setFont(jCheckBox22.getFont());
        jCheckBox22.setText(bundle.getString("CIChartSetupPanel.jCheckBox22.text")); 
        jPanel101.add(jCheckBox22);

        add(jPanel101);
        jPanel101.setBounds(30, 390, 670, 40);

        jPanel103.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel131.setFont(jLabel131.getFont());
        jLabel131.setText(bundle.getString("CIChartSetupPanel.jLabel131.text")); 
        jPanel103.add(jLabel131);

        jLabel132.setFont(jLabel132.getFont());
        jLabel132.setText(bundle.getString("CIChartSetupPanel.jLabel132.text")); 
        jPanel103.add(jLabel132);

        jTextField47.setText(bundle.getString("CIChartSetupPanel.jTextField47.text")); 
        jTextField47.setPreferredSize(new java.awt.Dimension(56, 25));
        jPanel103.add(jTextField47);

        jLabel133.setFont(jLabel133.getFont());
        jLabel133.setText(bundle.getString("CIChartSetupPanel.jLabel133.text")); 
        jPanel103.add(jLabel133);

        jTextField48.setText(bundle.getString("CIChartSetupPanel.jTextField48.text")); 
        jTextField48.setPreferredSize(new java.awt.Dimension(56, 25));
        jPanel103.add(jTextField48);

        jLabel134.setFont(jLabel134.getFont());
        jLabel134.setText(bundle.getString("CIChartSetupPanel.jLabel134.text")); 
        jPanel103.add(jLabel134);

        jTextField49.setText(bundle.getString("CIChartSetupPanel.jTextField49.text")); 
        jTextField49.setPreferredSize(new java.awt.Dimension(56, 25));
        jPanel103.add(jTextField49);

        jLabel135.setFont(jLabel135.getFont());
        jLabel135.setText(bundle.getString("CIChartSetupPanel.jLabel135.text")); 
        jPanel103.add(jLabel135);

        jTextField50.setText(bundle.getString("CIChartSetupPanel.jTextField50.text")); 
        jTextField50.setPreferredSize(new java.awt.Dimension(56, 25));
        jPanel103.add(jTextField50);

        jLabel136.setFont(jLabel136.getFont());
        jLabel136.setText(bundle.getString("CIChartSetupPanel.jLabel136.text")); 
        jPanel103.add(jLabel136);

        add(jPanel103);
        jPanel103.setBounds(120, 70, 880, 30);

        jButton44.setFont(jButton44.getFont());
        jButton44.setText(bundle.getString("CIChartSetupPanel.jButton44.text")); 
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });
        add(jButton44);
        jButton44.setBounds(20, 270, 120, 30);

        jButton45.setFont(jButton45.getFont());
        jButton45.setText(bundle.getString("CIChartSetupPanel.jButton45.text")); 
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });
        add(jButton45);
        jButton45.setBounds(20, 300, 120, 30);

        jPanel106.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel143.setFont(jLabel143.getFont());
        jLabel143.setText(bundle.getString("CIChartSetupPanel.jLabel143.text")); 
        jPanel106.add(jLabel143);

        jLabel114.setFont(jLabel114.getFont());
        jLabel114.setText(bundle.getString("CIChartSetupPanel.jLabel114.text")); 
        jPanel106.add(jLabel114);

        jLabel60.setBackground(new java.awt.Color(255, 51, 51));
        jLabel60.setText(bundle.getString("CIChartSetupPanel.jLabel60.text")); 
        jLabel60.setOpaque(true);
        jLabel60.setPreferredSize(new java.awt.Dimension(40, 25));
        jLabel60.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel60MouseClicked(evt);
            }
        });
        jPanel106.add(jLabel60);

        jLabel138.setFont(jLabel138.getFont());
        jLabel138.setText(bundle.getString("CIChartSetupPanel.jLabel138.text")); 
        jPanel106.add(jLabel138);

        jTextField51.setText(bundle.getString("CIChartSetupPanel.jTextField51.text")); 
        jTextField51.setPreferredSize(new java.awt.Dimension(156, 25));
        jPanel106.add(jTextField51);

        jButton42.setFont(jButton42.getFont());
        jButton42.setText(bundle.getString("CIChartSetupPanel.jButton42.text")); 
        jPanel106.add(jButton42);

        add(jPanel106);
        jPanel106.setBounds(30, 530, 730, 40);

        jPanel111.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox24.setFont(jCheckBox24.getFont());
        jCheckBox24.setText(bundle.getString("CIChartSetupPanel.jCheckBox24.text")); 
        jPanel111.add(jCheckBox24);

        jCheckBox26.setFont(jCheckBox26.getFont());
        jCheckBox26.setText(bundle.getString("CIChartSetupPanel.jCheckBox26.text")); 
        jPanel111.add(jCheckBox26);

        jTextField52.setText(bundle.getString("CIChartSetupPanel.jTextField52.text")); 
        jTextField52.setPreferredSize(new java.awt.Dimension(156, 25));
        jPanel111.add(jTextField52);

        add(jPanel111);
        jPanel111.setBounds(30, 480, 670, 35);

        jPanel142.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 255)));
        jPanel142.setLayout(new java.awt.BorderLayout());
        add(jPanel142);
        jPanel142.setBounds(730, 260, 250, 230);

        jPanel112.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox23.setFont(jCheckBox23.getFont());
        jCheckBox23.setText(bundle.getString("CIChartSetupPanel.jCheckBox23.text")); 
        jPanel112.add(jCheckBox23);

        jTextField43.setText(bundle.getString("CIChartSetupPanel.jTextField43.text")); 
        jTextField43.setPreferredSize(new java.awt.Dimension(65, 25));
        jPanel112.add(jTextField43);

        add(jPanel112);
        jPanel112.setBounds(90, 430, 580, 40);

        jPanel148.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox13.setFont(jCheckBox13.getFont());
        jCheckBox13.setText(bundle.getString("CIChartSetupPanel.jCheckBox13.text")); 
        jPanel148.add(jCheckBox13);

        add(jPanel148);
        jPanel148.setBounds(230, 150, 550, 30);

        jPanel149.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox14.setFont(jCheckBox14.getFont());
        jCheckBox14.setText(bundle.getString("CIChartSetupPanel.jCheckBox14.text")); 
        jPanel149.add(jCheckBox14);

        add(jPanel149);
        jPanel149.setBounds(290, 270, 390, 30);
    }

    private void chartListMouseClicked(java.awt.event.MouseEvent evt) {
        instrument.showChart();
    }

    private void chartListKeyReleased(java.awt.event.KeyEvent evt) {
        if(evt.getKeyCode()==38 || evt.getKeyCode()==40 ) instrument.showChart();
    }

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {
        int cnt=chartList.getModel().getSize();
        int max=0;
        if(cnt>0){
            for(int i=0;i<cnt;i++) {
                String tm=(String)chartList.getModel().getElementAt(i);
                int v=Integer.parseInt(tm.substring(2));
                if(v>max) max=v;
            }
        }
        max=max+1;
        String newItem="CT"+instrument.zero(4-String.valueOf(max).length())+max;
        chartListModel.addElement(newItem);
        chartList.setSelectedValue(newItem, true);
        jTextField44.setText(newItem);
        jTextField47.setText("0");
        jTextField48.setText("0");
        jTextField49.setText("100");
        jTextField50.setText("100");
        jCheckBox13.setSelected(false);
        jCheckBox14.setSelected(false);
        jCheckBox15.setSelected(false);
        jCheckBox16.setSelected(false);
        jCheckBox19.setSelected(false);
        jCheckBox20.setSelected(false);
        jCheckBox21.setSelected(false);
        jCheckBox22.setSelected(false);
        jCheckBox23.setSelected(false);
        jCheckBox24.setSelected(false);
        instrument.chartTM.put(newItem, newItem+",");
    }

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {
        if(chartList.getSelectedIndex()>-1){
            int selNo=chartList.getSelectedIndex();
            String sel=(String)chartList.getSelectedValue();
            chartListModel.removeElement(sel);
            instrument.chartTM.remove(sel);
            if(chartListModel.size() < selNo+1) selNo=chartListModel.size()-1;
            if(selNo>-1){
                chartList.setSelectedIndex(selNo);
                sel=(String)chartList.getSelectedValue();
                instrument.showChart();
            }
        }
    }

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {
        if(chartList.getSelectedIndex()>-1){
            String sel=(String)chartList.getSelectedValue();
            String data[]=new String[instrument.chartMaxArrCnt];
            for(int i=0;i<data.length;i++) data[i]="";
            if(instrument.chartTM.get(sel)!=null){
                String data2[]=ylib.csvlinetoarray((String)instrument.chartTM.get(sel));
            for(int i=0;i<data2.length && i<data.length;i++) data[i]=data2[i];
        }
        data[0]=sel;

        data[1]=String.valueOf(jLabel60.getBackground().getRGB());
        data[2]=jTextField47.getText();
        data[3]=jTextField48.getText();
        data[4]=jTextField49.getText();
        data[5]=jTextField50.getText();
        data[6]=jTextField33.getText();
        data[7]=jTextField34.getText();
        if(jCheckBox12.isSelected()) data[8]="Y"; else data[8]="N";
        if(jCheckBox13.isSelected()) data[9]="Y"; else data[9]="N";
        if(jRadioButton2.isSelected()) data[10]="1"; else data[10]="2";
        data[11]=jTextField35.getText();
        data[12]=jTextField36.getText();
        data[13]=jTextField37.getText();
        data[14]=jTextField38.getText();
        if(jRadioButton4.isSelected()) data[15]="1"; else data[15]="2";
        if(jCheckBox14.isSelected()) data[16]="Y"; else data[16]="N";

        if(jCheckBox15.isSelected()) data[19]="Y"; else data[19]="N";
        if(jCheckBox16.isSelected()) data[20]="Y"; else data[20]="N";

        if(jCheckBox19.isSelected()) data[23]="Y"; else data[23]="N";
        if(jCheckBox20.isSelected()) data[24]="Y"; else data[24]="N";
        if(jCheckBox21.isSelected()) data[25]="Y"; else data[25]="N";
        if(jCheckBox22.isSelected()) data[26]="Y"; else data[26]="N";
        if(jCheckBox23.isSelected()) data[27]="Y"; else data[27]="N";
        data[28]=jTextField43.getText();
        data[29]=jTextField44.getText();
        if(jCheckBox24.isSelected()) data[30]="Y"; else data[30]="N";
        data[31]=(String)jComboBox42.getSelectedItem();
        if(jCheckBox26.isSelected()) data[32]="Y"; else data[32]="N";
        data[33]=jTextField52.getText();
        data[34]=jTextField51.getText();
        instrument.chartTM.put(sel, data);

        }
    }

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {
        int sel=chartList.getSelectedIndex();
        if(sel>0){
            String key=(String)chartList.getSelectedValue();
            String data=(String)instrument.chartTM.get(key);
            chartList.setSelectedIndex(sel-1);
            String key2=(String)chartList.getSelectedValue();
            String data2=(String)instrument.chartTM.get(key2);
            instrument.chartTM.put(key, data2);
            instrument.chartTM.put(key2, data);
        }

    }

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {
        int sel=chartList.getSelectedIndex();
        if(sel>-1 && sel<(chartListModel.size()-1)){
            String key=(String)chartList.getSelectedValue();
            String data=(String)instrument.chartTM.get(key);
            chartList.setSelectedIndex(sel+1);
            String key2=(String)chartList.getSelectedValue();
            String data2=(String)instrument.chartTM.get(key2);
            instrument.chartTM.put(key, data2);
            instrument.chartTM.put(key2, data);
        }
    }

    private void jLabel60MouseClicked(java.awt.event.MouseEvent evt) {
        Color newColor = JColorChooser.showDialog(null, "Choose a color", jLabel60.getBackground());
        if(newColor!=null) jLabel60.setBackground(newColor);
    }

    public javax.swing.JList chartList;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    public javax.swing.JCheckBox jCheckBox12;
    public javax.swing.JCheckBox jCheckBox13;
    public javax.swing.JCheckBox jCheckBox14;
    public javax.swing.JCheckBox jCheckBox15;
    public javax.swing.JCheckBox jCheckBox16;
    public javax.swing.JCheckBox jCheckBox19;
    public javax.swing.JCheckBox jCheckBox20;
    public javax.swing.JCheckBox jCheckBox21;
    public javax.swing.JCheckBox jCheckBox22;
    public javax.swing.JCheckBox jCheckBox23;
    public javax.swing.JCheckBox jCheckBox24;
    public javax.swing.JCheckBox jCheckBox26;
    public javax.swing.JComboBox jComboBox42;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel146;
    public javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JPanel jPanel100;
    private javax.swing.JPanel jPanel101;
    private javax.swing.JPanel jPanel103;
    private javax.swing.JPanel jPanel106;
    private javax.swing.JPanel jPanel111;
    private javax.swing.JPanel jPanel112;
    public javax.swing.JPanel jPanel142;
    private javax.swing.JPanel jPanel148;
    private javax.swing.JPanel jPanel149;
    private javax.swing.JPanel jPanel93;
    private javax.swing.JPanel jPanel96;
    private javax.swing.JPanel jPanel97;
    private javax.swing.JPanel jPanel98;
    private javax.swing.JPanel jPanel99;
    public javax.swing.JRadioButton jRadioButton2;
    public javax.swing.JRadioButton jRadioButton3;
    public javax.swing.JRadioButton jRadioButton4;
    public javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JScrollPane jScrollPane9;
    public javax.swing.JTextField jTextField33;
    public javax.swing.JTextField jTextField34;
    public javax.swing.JTextField jTextField35;
    public javax.swing.JTextField jTextField36;
    public javax.swing.JTextField jTextField37;
    public javax.swing.JTextField jTextField38;
    public javax.swing.JTextField jTextField43;
    public javax.swing.JTextField jTextField44;
    public javax.swing.JTextField jTextField47;
    public javax.swing.JTextField jTextField48;
    public javax.swing.JTextField jTextField49;
    public javax.swing.JTextField jTextField50;
    public javax.swing.JTextField jTextField51;
    public javax.swing.JTextField jTextField52;

}