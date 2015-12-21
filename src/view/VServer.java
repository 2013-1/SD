package view;

import javax.swing.ImageIcon;

/**
 *
 * @author Yan Kaic
 */
public class VServer extends VHost {


  public VServer() {
    super();
    initComponents();
  }

  private void initComponents() {
    ImageIcon hostIcon = new ImageIcon(getClass().getResource("/images/server.png"));
    setIcon(hostIcon);
    setText("Server");
    ImageIcon loadingIcon = new ImageIcon(getClass().getResource("/images/loading.gif"));
    setOverlapIcon(loadingIcon);
  }
  
 
  @Override
  public void turnOnLight() {
    super.turnOnLight();
  }

  @Override
  public void turnOffLight() {
    super.turnOffLight();
    
  }

}
