package ci;

import javax.swing.JInternalFrame;

import java.awt.event.*;
import java.awt.*;
import java.util.Iterator;
import java.util.TreeMap;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import y.ylib.OneVar;
import y.ylib.ylib;

public class DataViewIFrame extends JInternalFrame {
    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;
    int selColumn=0;
    public boolean currentSizeIsMax=false;
    public DefaultTableModel tableModel=null;
    public DataViewFrame uFrame;
    CrInstrument instrument;
    ListSelectionModel listSelectionModel;

    JMenuItem itemSelStat = new JMenuItem("Selection Summary");
    JMenuItem itemSelLeft = new JMenuItem("Set as Left Axis");
    JMenuItem itemSelRight = new JMenuItem("Set as Right Axis");
    JPopupMenu popupMenuTbl1 = new JPopupMenu("Popup");
    Statistical statistical;
    public DataViewIFrame(DataViewFrame uFrame) {
        initComponents();
        this.uFrame=uFrame;
        this.instrument=uFrame.instrument;
        this.setTitle("Data");
        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(false);
        itemSelLeft.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            selectColumn(selColumn,true,true);
        }
        });
        itemSelRight.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            selectColumn(selColumn,false,true);
        }
        });
        itemSelStat.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
             analysis(selColumn);
        }
        });
        popupMenuTbl1.add(itemSelLeft);
        popupMenuTbl1.add(itemSelRight);
        popupMenuTbl1.add(itemSelStat);
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableModel=(DefaultTableModel) jTable1.getModel();
        jTable1.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent e) {
          int col =jTable1.columnAtPoint(e.getPoint());
          selColumn=col;
          if(SwingUtilities.isRightMouseButton(e)){
           if (selColumn>1) {
             popupMenuTbl1.show(e.getComponent(), e.getX(), e.getY());
           }
          } else if (selColumn>1) selectColumn(col,true,false);
         }
        });
        jTable1.setDefaultRenderer(Object.class, new MyTableCellRenderer());

        listSelectionModel =jTable1.getSelectionModel();
        listSelectionModel.addListSelectionListener(new SharedListSelectionHandler());

        listSelectionModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTable1.setSelectionModel(listSelectionModel);
   }
  public class MyTableCellRenderer extends DefaultTableCellRenderer {
    Color lightlightgray=new Color(240,240,240);
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
      if(column>1 && uFrame.columnColor.length>column && uFrame.columnColor[column]!=null && uFrame.cellColor[column][row]>0){

          comp.setBackground(uFrame.colorSequence[uFrame.cellColor[column][row]]);
          comp.setForeground(Color.white);

      } else { 
          comp.setBackground(Color.white);  
          comp.setForeground(Color.black);
        }
      return comp;
    }
  }
class SharedListSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) { 
            

        boolean isAdjusting = e.getValueIsAdjusting(); 
        if(!isAdjusting){
        int[] selectedRows = jTable1.getSelectedRows();
        int[] selectedColumns = jTable1.getSelectedColumns();
        int count=0;
        for (int col = 0; col < selectedColumns.length; col++) {
            if(selectedColumns[col]>1) {
              for(int row=0;row<selectedRows.length;row++){
                count++;
              }
            }
          }
        if( count >1){
          for (int col = 0; col < selectedColumns.length; col++) {
            if(selectedColumns[col]>1) {
              uFrame.columnColor[selectedColumns[col]]=uFrame.colorSequence[uFrame.nextColorInx];
              for(int i=0;i<uFrame.dataStrTM[0].size();i++) uFrame.cellColor[selectedColumns[col]][i]=0;
              for(int row=0;row<selectedRows.length;row++){
                uFrame.cellColor[selectedColumns[col]][selectedRows[row]]=uFrame.nextColorInx;
              }
            }
          }
          uFrame.nextColorInx++;
          if(uFrame.nextColorInx>=uFrame.colorSequence.length) uFrame.nextColorInx=1;
          jTable1.updateUI();
          uFrame.gFrame.showCurve();
        }
        }
    }
}
public void analysis(int col){
    if(statistical==null) statistical=new Statistical(instrument,true);
    statistical.setPlace(col);
    statistical.setVisible(true);
}

public void selectColumn(int col,boolean leftaxis,boolean byRightClick){
    if(col>1){
      if(uFrame.columnColor[col]==null || byRightClick){
        uFrame.columnColor[col]=uFrame.colorSequence[uFrame.nextColorInx];
        for(int i=0;i<uFrame.dataStrTM[0].size();i++) uFrame.cellColor[col][i]=uFrame.nextColorInx;
        if(leftaxis){
          uFrame.columnOneVar[col]=OneVar.remove(uFrame.columnOneVar[col], 0);
        } else {
          uFrame.columnOneVar[col]=OneVar.add(uFrame.columnOneVar[col], 0);
        }
        uFrame.nextColorInx++;
        if(uFrame.nextColorInx>=uFrame.colorSequence.length) uFrame.nextColorInx=1;
      } else {
          uFrame.columnColor[col]=null;
          for(int i=0;i<uFrame.dataStrTM[0].size();i++) uFrame.cellColor[col][i]=0;
      }
      jTable1.updateUI();
      uFrame.gFrame.showCurve();
    }
}
public void setData(){

  if(uFrame.dataTitle != null && uFrame.dataStrTM != null){
    int rcount=jTable1.getRowCount();
    for(int i=rcount-1;i>-1;i--)  ((DefaultTableModel)jTable1.getModel()).removeRow(i);
    tableModel.setColumnIdentifiers(uFrame.dataTitle);
    uFrame.nextColorInx=1;
    uFrame.columnColor=new Color[uFrame.dataTitle.length];
    uFrame.columnOneVar=new long[uFrame.dataTitle.length];
    for(int i=0;i<uFrame.dataTitle.length;i++){
        uFrame.columnColor[i]=null;
        uFrame.columnOneVar[i]=0;
    }
    Iterator it=uFrame.dataStrTM[0].keySet().iterator();
    int inx=0;
    for(;it.hasNext();){
      if(jTable1.getModel().getRowCount()<inx+1) tableModel.addRow(new Object[tableModel.getColumnCount()]);
      Object key=it.next();
      for(int j=0;j<uFrame.dataStrTM.length;j++) tableModel.setValueAt(uFrame.dataStrTM[j].get(key), inx, j);
      inx++;
    }
  }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Time", "RN", "Title 1", "Title 2"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }

    private void formInternalFrameIconified(javax.swing.event.InternalFrameEvent evt) {

        uFrame.dMax=false;
        uFrame.gMax=true;

        uFrame.revalidate();
        uFrame.gFrame.revalidate();
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

        if(uFrame.changeFile) {uFrame.changeFile=false; return;}
        if(!currentSizeIsMax && this.isMaximum) {

            uFrame.dMax=true;
            uFrame.gMax=false;
            currentSizeIsMax=true;

            uFrame.revalidate();
            uFrame.gFrame.revalidate();
        }
        else if(currentSizeIsMax && !this.isMaximum) {

            uFrame.dMax=false;
            currentSizeIsMax=false;

            uFrame.validate();
        }
         else if(currentSizeIsMax && this.isMaximum) {

            uFrame.dMax=false;
            currentSizeIsMax=false;

            uFrame.validate();
        }
    }

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
        uFrame.dMax=false;
        uFrame.gMax=true;
        System.out.println("5, uFrame.dMax="+uFrame.dMax);
        uFrame.revalidate();
        uFrame.gFrame.revalidate();
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {
         if(SwingUtilities.isRightMouseButton(evt)){
              int selRows[]=jTable1.getSelectedRows();
              int r = jTable1.rowAtPoint(evt.getPoint());
              selColumn=jTable1.columnAtPoint(evt.getPoint());
              if (r >= 0 && r < jTable1.getRowCount()){
                boolean selected=false;
                if(selRows.length>0){
                  for(int i=0;i<selRows.length;i++){
                      if(selRows[i]==r){selected=true; break;}
                  }
                }
                if(!selected){
                  jTable1.setRowSelectionInterval(r, r);
                }
              } else {
                 jTable1.clearSelection();
               }
           }
        int rowindex = jTable1.getSelectedRow();
        if (rowindex < 0) return;
        if (evt.isPopupTrigger() && evt.getComponent() instanceof JTable && selColumn>1) {
           popupMenuTbl1.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }

    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTable1;

}