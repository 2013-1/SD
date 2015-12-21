package run;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.VHost;
import view.VMap;
import view.VServer;

/**
 *
 * @author Yan Kaic
 */
public class Server {

  private static final int PORT = 3030;
  public static final String CLOSE_MESSAGE = "bye";
  private VServer visual;
  private VMap visualMap;

  public Server() {
    this.visual = new VServer();
    this.visualMap = new VMap();
  }

  public void start() {
    new Thread() {
      @Override
      public void run() {
        try {
          ServerSocket welcome = new ServerSocket(PORT);
          while (true) {
            Socket socket = welcome.accept();
            new Communicator(socket).start();
          }
        }
        catch (IOException ex) {
          Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

      }
    }.start();
  }

  public VServer getVisual() {
    return visual;
  }

  public void setVisual(VServer visual) {
    this.visual = visual;
  }

  public VMap getVisualMap() {
    return visualMap;
  }

  public void setVisualMap(VMap visualMap) {
    this.visualMap = visualMap;
  }

  public class Communicator extends Thread {

    private final Socket socket;

    public Communicator(Socket socket) {
      this.socket = socket;
      visual.highlight();
    }

    @Override
    public void run() {
      VHost visualHost = new VHost();
      visualHost.setText(socket.getInetAddress().getHostName() + " | Port: " + socket.getPort());
      visualMap.create(visualHost);
      visualHost.highlight();

      try {
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        PrintStream output = new PrintStream(socket.getOutputStream());

        while (socket.isConnected()) {
          Color[][] colors = (Color[][]) input.readObject();
          output.println("ok");
          
          visualHost.setColors(colors);
          visualHost.highlight();
          visual.highlight();
        }
        visualHost.disconnect();
      }
      catch (IOException ex) {
        visualHost.disconnect();
      }
      catch (ClassNotFoundException ex) {
        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
      }

    }

  }

}
