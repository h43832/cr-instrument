
package ci;

import javax.swing.DefaultListModel;

/**
 *
 * @author peter
 */
public class CINodeMgntPanel extends javax.swing.JPanel {
DefaultListModel listModel9 = new DefaultListModel();
 CrInstrument instrument;
    public CINodeMgntPanel(CrInstrument instrument) {
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

        btnApplySetting_node = new javax.swing.JButton();
        jScrollPane15 = new javax.swing.JScrollPane();
        jList9 = new javax.swing.JList(listModel9);
        jScrollPane16 = new javax.swing.JScrollPane();
        jList10 = new javax.swing.JList();

        setLayout(null);

        btnApplySetting_node.setFont(btnApplySetting_node.getFont().deriveFont(btnApplySetting_node.getFont().getSize()+12f));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ci/Bundle"); 
        btnApplySetting_node.setText(bundle.getString("CrInstrument.btnApplySetting_node.text")); 
        add(btnApplySetting_node);
        btnApplySetting_node.setBounds(780, 70, 200, 60);

        jScrollPane15.setViewportView(jList9);

        add(jScrollPane15);
        jScrollPane15.setBounds(40, 40, 80, 190);

        jScrollPane16.setViewportView(jList10);

        add(jScrollPane16);
        jScrollPane16.setBounds(70, 380, 70, 160);
    }

    private javax.swing.JButton btnApplySetting_node;
    private javax.swing.JList jList10;
    private javax.swing.JList jList9;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;

}