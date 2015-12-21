/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JLabel;

/**
 *
 * @author Yan Kaic
 */
public class ColorLabel extends JLabel {


  public ColorLabel() {
    super();
  }
  
  private Color[][] colors;

  public void setColors(Color[][] colors) {
    this.colors = colors;
  }

  @Override
  public void paintComponent(Graphics gph) {
    if (colors != null) {
      Point initialPoint = new Point(22, 21);
      for (int i = 0; i < colors.length; i++) {
        for (int j = 0; j < colors[0].length; j++) {
          gph.setColor(colors[i][j]);
          gph.fillRect(initialPoint.x + j * 10, initialPoint.y + 7 * i, 9, 6);
        }
      }
    }
    super.paintComponent(gph);

  }

}
