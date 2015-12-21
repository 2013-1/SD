package run;

import javax.swing.UIManager;
import view.ClientManager;
import view.Teste;

/**
 *
 * @author Yan Kaic
 */
public class Main {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    new Teste().setVisible(true);
    new ClientManager().setVisible(true);
  }

}
