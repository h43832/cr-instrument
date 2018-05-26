
package ci;

import java.awt.Toolkit;
import java.util.Date;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;

/**
 *
 * @author peter
 */
public class CIOptions extends javax.swing.JDialog {

    CrInstrument instrument;
    boolean originalAutoUpdate=false,runConfig=false;
    public ResourceBundle bundle2 = java.util.ResourceBundle.getBundle("ci/Bundle");
    public CIOptions(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        instrument=(CrInstrument)parent;
            int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int h = Toolkit.getDefaultToolkit().getScreenSize().height - 20;

    int w2 = 300;
    int h2 = 200;

    setSize(w2, h2);

    setLocation((width - w2) / 2, (h - h2) / 2 - 10);

    setIconImage(instrument.iconImage);
    if(instrument.wn.w.chkValue(instrument.props.getProperty("auto-update"))) {
        jCheckBox2.setSelected(true);
        originalAutoUpdate=true;
    } else {
        jCheckBox2.setSelected(false);
        originalAutoUpdate=false;
    }
    jCheckBox2.setVisible(false);
    jCheckBox1.setSelected(instrument.wn.w.getHVar("run_config")==null || instrument.wn.w.getHVar("run_config").equalsIgnoreCase("true"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ci/Bundle"); 
        setTitle(bundle.getString("CIOptions.title")); 
        getContentPane().setLayout(null);

        jCheckBox1.setFont(jCheckBox1.getFont());
        jCheckBox1.setText(bundle.getString("CIOptions.jCheckBox1.text")); 
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBox1);
        jCheckBox1.setBounds(40, 30, 260, 23);

        jCheckBox2.setFont(jCheckBox2.getFont());
        jCheckBox2.setText(bundle.getString("CIOptions.jCheckBox2.text")); 
        getContentPane().add(jCheckBox2);
        jCheckBox2.setBounds(40, 70, 230, 23);

        jButton1.setFont(jButton1.getFont());
        jButton1.setText(bundle.getString("CIOptions.jButton1.text")); 
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(43, 110, 100, 23);

        jButton2.setFont(jButton2.getFont());
        jButton2.setText(bundle.getString("CIOptions.jButton2.text")); 
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(160, 110, 100, 23);

        pack();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
       setVisible(false);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        instrument.props.setProperty("auto-upddate", (jCheckBox2.isSelected()? "Y":"N"));
        instrument.wn.w.setHVar("run_config",(jCheckBox1.isSelected()? "true":"false"),instrument.wn.getPid(instrument,instrument.format3.format(new Date())+"wsn"));

        setVisible(false);
        if(instrument.wn.w.chkValue(instrument.props.getProperty("auto-upddate")) && instrument.wn.w.upd==null) {
          if (instrument.wn.chkProps("run_my_ap_only")) {
            instrument.wn.w.startUpd("http://cloud-rain.com/web/ci_version.txt", instrument.apId, instrument.version, 10L);
          }
        }
        if(instrument.wn.w.chkValue(instrument.props.getProperty("ci-demo")) && instrument.wn.chkProps("run_my_ap_only")) return;
        else instrument.wn.w.saveSysFile("wsn_properties");
    }

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CIOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CIOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CIOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CIOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CIOptions dialog = new CIOptions(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;

}