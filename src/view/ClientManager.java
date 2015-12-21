/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author Yan Kaic
 */
public class ClientManager extends JFrame {

  public ClientManager() throws HeadlessException {
    super("Clientes");
    initComponents();
  }

  private void initComponents() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(520, 500);
    setLayout(new FlowLayout());
    setLocation(800, 0);
    for (int x = 0; x < 8; x++) {
      VClient vClient = new VClient();
      add(vClient);
    }
    setVisible(true);
  }

}
