
package ci;

/**
 *
 * @author peter
 */
import java.util.ResourceBundle;
import wsn.*;
public class CIConfigDialog extends wsn.WSNConfigDialog2{
  public ResourceBundle bundle2 = java.util.ResourceBundle.getBundle("ci/Bundle");
  public CIConfigDialog(){
    super();
    jPanel5.setVisible(false);
    jPanel9.setVisible(false);
    setTitle(bundle2.getString("CrInstrument.xy.msg103"));
  }
}