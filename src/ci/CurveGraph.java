
package ci;
import java.awt.*;
import golib.*;
import java.awt.geom.AffineTransform;
import java.awt.event.*;
public class CurveGraph implements CustomizeClass{
  Color sensorColor=new Color(240,240,240);
  Color colorA=new Color(-3355648),colorB=new Color(-16737946);
  @Override
  public void beforePlotData(MultiPanel2 panel2,Graphics2D g2,Config cfg,Status status,String para[],double x0,double y0,double width, double height,double x3,double y3,double w3,double h3){

  }
  @Override
  public void afterPlotData(MultiPanel2 panel2,Graphics2D g2,Config cfg,Status status,String para[],double x0,double y0,double width, double height,double x3,double y3,double w3,double h3){

    int x1K=(int)x3-40;
    int y1K=(int)(y3+h3/2+20);

    g2.setColor(Color.blue);
    AffineTransform at = g2.getTransform();
        at.rotate(- Math.PI / 2);
        g2.setTransform(at);

        g2.drawString("角 度", y1K*(-1), x1K);

        at.rotate( Math.PI / 2);
    g2.setTransform(at);
    g2.setColor(Color.blue);
    g2.fillRoundRect(3,3,90,20,3,3);
    g2.setColor(Color.white);
    g2.drawString(para[0],5, 17);
    g2.setColor(Color.BLACK);
    g2.drawString("A 軸",130, 25);
    g2.drawString("B 軸",185, 25);
    g2.drawString("警戒值",245,25);
    g2.drawString("行動值",340,25);
    g2.setColor(Color.blue);
    g2.drawString("時 間",(int) (x3+w3/2.0-25.0), (int)(y3+h3+30.0));
    g2.setColor(colorA);
    g2.setStroke(cfg.getStroke(2,0));
    g2.drawLine(100,20,125,20);
    g2.setColor(colorB);
    g2.setStroke(cfg.getStroke(2,0));
    g2.drawLine(155,20,180,20);
    g2.setColor(cfg.c[4]);
    g2.setStroke(cfg.getStroke(2,11));
    g2.drawLine(215,20,240,20);
    g2.setColor(cfg.c[53]);
    g2.setStroke(cfg.getStroke(2,11));
    g2.drawLine(310,20,335,20);
  }
  @Override
    public void formMouseMoved(MultiPanel2 panel2,Graphics2D g2,int inx,Config cfg,Status status,String para[],double x0,double y0,double width, double height,double x3,double y3,double w3,double h3,MouseEvent evt){
  }
  public void formMousePressed(MultiPanel2 panel2,Graphics2D g2,int inx,Config cfg,Status stat,String para[],double bx,double by,double width, double height,double x3,double y3,double width3,double height3,java.awt.event.MouseEvent evt){

  }
  public void formMouseReleased(MultiPanel2 panel2,Graphics2D g2,int inx,Config cfg,Status stat,String para[],double bx,double by,double width, double height,double x3,double y3,double width3,double height3,java.awt.event.MouseEvent evt){

  }
  @Override
  public void formMouseClicked(MultiPanel2 panel2,Graphics2D g2,int inx,Config cfg,Status status,String para[],double x0,double y0,double width, double height,double x3,double y3,double w3,double h3,MouseEvent evt){
  }
  public void formMouseDragged(MultiPanel2 panel2,Graphics2D g2,int inx,Config cfg,Status status,String para[],double x0,double y0,double width, double height,double x3,double y3,double w3,double h3,MouseEvent evt){
  }
  public void formMouseWheelMoved(MultiPanel2 panel2,Graphics2D g2,int inx,Config cfg,Status stat,String para[],double x0,double y0,double width, double height,double x3,double y3,double w3,double h3,java.awt.event.MouseWheelEvent evt){

  }                                   
}