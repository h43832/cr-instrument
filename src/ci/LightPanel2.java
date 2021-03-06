
package ci;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;

/**
 *
 * @author Administrator
 */
public class LightPanel2 extends javax.swing.JPanel {

Color c1=Color.green,c2=Color.red;
  public LightPanel2() {
    initComponents();
  }
  public static void main(String args[]){
    JFrame frame=new JFrame();
    frame.setLayout(new java.awt.BorderLayout());
    LightPanel2 panel=new LightPanel2();
    frame.add(panel, java.awt.BorderLayout.CENTER);
        int width=Toolkit.getDefaultToolkit().getScreenSize().width;
        int h=Toolkit.getDefaultToolkit().getScreenSize().height-20;

        int w2=400;
        int h2=202;

    frame.setSize(w2,h2);
    frame.setVisible(true);
    frame.setLocation((width-w2)/2,(h-h2)/2-10);
  }
@Override
protected void paintComponent(Graphics g){
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    int x1=0,y1=0,x1a=0,y1a=0,x2=0,y2=0,x2a=0,y2a=0;

    GradientPaint redtowhite1 = new GradientPaint(2, 2, c1, 2, 62, Color.white);
    g2.setPaint(redtowhite1);
    g2.fill(new Ellipse2D.Double(2, 2, 50, 50));;
    GradientPaint redtowhite1a = new GradientPaint(10, 2,Color.white , 2, 38, c1);
    g2.setPaint(redtowhite1a);
    g2.fill(new Ellipse2D.Double(7, 3, 40, 31));

    if(c2!=null){
    GradientPaint redtowhite2 = new GradientPaint(70,2, c2, 70,62, Color.white);
    g2.setPaint(redtowhite2);
    g2.fill(new Ellipse2D.Double(70, 2, 50, 50));
    GradientPaint redtowhite2a = new GradientPaint(75, 2, Color.white, 75, 38,c2 );
    g2.setPaint(redtowhite2a);
    g2.fill(new Ellipse2D.Double(75, 3, 40, 31));
    } else {
      g2.setColor(Color.white);
      g2.fillRect(70, 2, 70, 70);
    }
}
public void setColor(Color c1,Color c2){
  this.c1=c1;
  this.c2=c2;
  repaint();
}

public void setColor(int inx,Color c){
  if(inx==1) this.c1=c;
  else this.c2=c;
  repaint();
}
  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")

  private void initComponents() {

    setBackground(new java.awt.Color(255, 255, 255));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 123, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 59, Short.MAX_VALUE)
    );
  }

}