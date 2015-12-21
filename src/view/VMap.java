package view;

import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Yan Kaic
 */
public class VMap extends JPanel {

  private VServer server;
  private ArrayList<VHost> hosts;

  public VMap() {
    initComponents();
  }

  private void initComponents() {
    setLayout(null);
    setPreferredSize(new Dimension(500, 500));
    server = new VServer();
    add(server);
    setMinimumSize(server.getSize());

    hosts = new ArrayList<>();
    
    addComponentListener(new screenResize());
    
    new Thread(){
      @Override
      public void run(){
       while(true){
         try {
           repaint();
           sleep(1000);
         }
         catch (InterruptedException ex) {
           
         }
       }
        
      }
    }.start();
  }

  public void setServer(VServer server) {
    remove(this.server);
    this.server = server;
    add(server);
  }

  public VServer getServer() {
    return server;
  }

  public Point[] getPoints(int n) {
    int radius = (getHeight() - server.getHeight()) / 2;
    final double circunference = 360.0;
    final double step = circunference / n;
    Point[] vertexes = new Point[n];
    Point center = new Point(getWidth() / 2, getHeight() / 2);
    int rotation = -90;
    double angle = rotation;

    for (int i = 0; i < n; angle += step, i++) {
      double radian = angle * Math.PI / 180;
      vertexes[i] = new Point(center);
      vertexes[i].x += (int) (radius * Math.cos(radian));
      vertexes[i].y += (int) (radius * Math.sin(radian));
    }
    return vertexes;
  }

  public void create(VHost host) {
    hosts.add(host);
    add(host);
    relocate();
    repaint();
  }

  private void relocate() {
    Point center = new Point(getWidth(), getHeight());
    center.x /= 2;
    center.y /= 2;
    center.x -= (server.getWidth() / 2);
    center.y -= (server.getHeight() / 2);
    server.setLocation(center);

    Point[] points = getPoints(hosts.size());
    for (int i = 0; i < points.length; i++) {
      points[i].x -= hosts.get(i).getWidth() / 2;
      points[i].y -= hosts.get(i).getHeight() / 2;
      hosts.get(i).setLocation(points[i]);
    }
  }

  @Override
  public void paintComponent(Graphics gph) {
    super.paintComponent(gph);
    Graphics2D graphics = (Graphics2D) gph.create();
    graphics.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

    graphics.setStroke(new BasicStroke(2));
    Point center = new Point(getWidth() / 2, getHeight() / 2);
    for (VHost host : hosts) {
      if (host.isConected()) {
        graphics.setPaint(host.getLightColor());
        graphics.drawLine(center.x, center.y,
                host.getX() + host.getWidth() / 2,
                host.getY() + host.getHeight() / 2);
      }
    }
  }

  private class screenResize implements ComponentListener {

    public screenResize() {
    }

    @Override
    public void componentResized(ComponentEvent e) {
      relocate();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
  }

}
