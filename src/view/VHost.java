package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class VHost extends JLabel {

  private Color color;
  private boolean connected;
  private ColorLabel overlapLabel;

  public VHost() {
    super();
    initComponents();
  }

  @Override
  public void paintComponent(Graphics gph) {
    Point center = getIconLocation();
    Graphics2D graphics = (Graphics2D) gph.create();
    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    graphics.setPaint(color);
    graphics.fillOval(center.x, center.y, getIcon().getIconWidth(), getIcon().getIconHeight());
    graphics.setPaint(Color.black);
    super.paintComponent(gph);

    overlapLabel.setLocation(getIconLocation());
  }

  public Point getIconLocation() {
    Point center = new Point(getWidth() / 2, getHeight() / 2);
    center.x -= (getIcon().getIconWidth()) / 2;
    center.y -= (getIcon().getIconHeight() + 20) / 2;
    return center;
  }

  private void initComponents() {
    setLayout(null);
    setText("Host");
    ImageIcon hostIcon = new ImageIcon(getClass().getResource("/images/host.png"));
    setIcon(hostIcon);
    overlapLabel = new ColorLabel();
    overlapLabel.setSize(new Dimension(hostIcon.getIconWidth(), hostIcon.getIconHeight()));
    overlapLabel.setForeground(Color.MAGENTA);
    overlapLabel.setOpaque(false);

    setVerticalTextPosition(BOTTOM);
    setHorizontalTextPosition(CENTER);
    setHorizontalAlignment(CENTER);
    turnOffLight();
    connect();
  }

  @Override
  public void setIcon(Icon icon) {
    super.setIcon(icon);
    if (icon != null) {
      setPreferredSize(new Dimension(icon.getIconWidth() + 50, icon.getIconHeight() + 20));
      setSize(getPreferredSize());
      if (overlapLabel != null) {
        overlapLabel.setSize(icon.getIconWidth(), icon.getIconHeight());
      }
    }
  }

  public void setOverlapIcon(Icon icon) {
    overlapLabel.setIcon(icon);
  }

  public void turnOnLight() {
    color = new Color(153, 153, 255);
    add(overlapLabel,0);
    repaint();
  }

  public void turnOffLight() {
    color = Color.LIGHT_GRAY;
    try {
      remove(overlapLabel);
    }
    catch (NullPointerException e) {
    }
    repaint();
  }

  public boolean isHighlight() {
    return color != Color.LIGHT_GRAY;
  }

  public Color getLightColor() {
    return color;
  }

  public void highlight() {
    if (isHighlight()) {
      return;
    }
    new Thread() {
      @Override
      public void run() {
        try {
          turnOnLight();
          sleep(1000);
          turnOffLight();
        }
        catch (InterruptedException ex) {
        }
      }
    }.start();
  }

  public void connect() {
    this.connected = true;
  }

  public void disconnect() {
    this.connected = false;
  }

  public boolean isConected() {
    return connected;
  }

  public void setColors(Color[][] colors) {
   overlapLabel.setColors(colors);
  }

}
