
package ci;
import java.awt.*;
import golib.*;
import java.awt.geom.AffineTransform;
import java.awt.event.*;
public class CurveGraphA implements CustomizeClass{
  Color sensorColor=new Color(240,240,240);
  Color colorA=Color.black,
          colorB=new Color(-16737946);
  @Override
  public void beforeData(MultiPanel2 panel2,Graphics2D g2,Config cfg,Status status,String para[],double x0,double y0,double width, double height,double x3,double y3,double w3,double h3){

  }
  @Override
  public void afterData(MultiPanel2 panel2,Graphics2D g2,Config cfg,Status status,String para[],double x0,double y0,double width, double height,double x3,double y3,double w3,double h3){

    int x1K=(int)x3-55;
    int y1K=(int)(y3+h3/2+25);

    g2.setColor(Color.blue);
    AffineTransform at = g2.getTransform();
        at.rotate(- Math.PI / 2);
        g2.setTransform(at);

        g2.setFont(panel2.getFont("", 0, true, false));
        g2.drawString((cfg.intValue[23]==3? "Angle (¢X)":"Angle (\")"), y1K*(-1), x1K);

        at.rotate( Math.PI / 2);
    g2.setTransform(at);
    g2.setColor(Color.blue);
    g2.fillRoundRect(3,3,125,20,3,3);
    g2.setColor(Color.white);
    g2.drawString(para[0],5, 17);
    g2.setColor(Color.BLACK);
    g2.drawString("A",170, 25);
    g2.drawString("B",215, 25);
    g2.drawString("Alert",265,25);
    g2.drawString("Action",345,25);

    g2.setColor(colorA);
    g2.setStroke(cfg.getStroke(2,0));
    g2.drawLine(140,20,165,20);
    g2.setColor(colorB);
    g2.setStroke(cfg.getStroke(2,0));
    g2.drawLine(190,20,210,20);
    g2.setColor(cfg.c[4]);
    g2.setStroke(cfg.getStroke(2,11));
    g2.drawLine(240,20,260,20);
    g2.setColor(cfg.c[53]);
    g2.setStroke(cfg.getStroke(2,11));
    g2.drawLine(315,20,340,20);
  }
    @Override
    public void formMouseMoved(MultiPanel2 panel2,Graphics2D g2,int inx,Config cfg,Status status,String para[],double x0,double y0,double width, double height,double x3,double y3,double w3,double h3,MouseEvent evt){
  }
  @Override
  public void formMouseClicked(MultiPanel2 panel2,Graphics2D g2,int inx,Config cfg,Status status,String para[],double x0,double y0,double width, double height,double x3,double y3,double w3,double h3,MouseEvent evt){
  }
}