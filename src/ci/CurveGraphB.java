
package ci;
import java.awt.*;
import golib.*;
import java.awt.geom.AffineTransform;
import java.awt.event.*;
public class CurveGraphB implements CustomizeClass{
  Color sensorColor=new Color(240,240,240);
  Color colorA=new Color(-3355648),colorB=new Color(-16737946);
  @Override
  public void beforePlotData(MultiPanel2 panel2,Graphics2D g2,Config cfg,Status status,String para[],double x0,double y0,double width, double height,double x3,double y3,double w3,double h3){

  }
  @Override
  public void afterPlotData(MultiPanel2 panel2,Graphics2D g2,Config cfg,Status status,String para[],double x0,double y0,double width, double height,double x3,double y3,double w3,double h3){

    int x1K=(int)x3-55;
    int y1K=(int)(y3+h3/2+20);

    g2.setColor(Color.blue);
    AffineTransform at = g2.getTransform();
        at.rotate(- Math.PI / 2);
        g2.setTransform(at);

        g2.setFont(panel2.getFont("", 0, true, false));
        g2.drawString((cfg.intValue[23]==3? "Angle (¢X)":"Angle (\")"), y1K*(-1), x1K);

        at.rotate( Math.PI / 2);
    g2.setTransform(at);

    g2.setColor(Color.blue);
    g2.drawString("Time",(int) (x3+w3/2.0-25.0), (int)(y3+h3+32.0));

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