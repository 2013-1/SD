/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

/**
 *
 * @author Yan Kaic
 */
public class ColorButton extends JButton {

  private Color[] colors;
  public static final Color RED = new Color(255, 54, 54);
  public static final Color BLUE = new Color(102, 102, 204);
  public static final Color YELLOW = new Color(255, 204, 0);
  public static final Color PINK = new Color(255, 0, 102);
  public static final Color VIOLET = new Color(153, 0, 153);
  public static final Color GREEN = new Color(51, 204, 51);

  ;

  public ColorButton() {
    initComponents();
  }

  @Override
  public void paintComponent(Graphics gph) {
    super.paintComponent(gph);
    Graphics2D graphics = (Graphics2D) gph.create();
    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    final int ovalSize = getHeight() - 8;
    graphics.fillOval((getWidth() - ovalSize) / 2, 4, ovalSize, ovalSize);
  }

  @Override
  public void setText(String text) {

  }

  private void initComponents() {
    colors = new Color[]{RED, BLUE, YELLOW, PINK, GREEN, VIOLET};
    setForeground(RED);
    addActionListener((ActionEvent e) -> {

      for (int i = 0; i < colors.length; i++) {
        if (getForeground() == colors[i]) {
          setForeground(colors[(++i % colors.length)]);
          break;
        }
      }
    });
  }
}
