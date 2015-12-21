package run;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yan Kaic
 */
public class Client extends Thread {

  private Socket socket;
  private final String address;
  private final int port;
  private ObjectOutputStream output;

  public Client(String address, int port) {
    this.address = address;
    this.port = port;
  }

  public void send(Object object) {
    try {
      output.writeObject(object);
    }
    catch (IOException ex) {
      Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void disconnect() {
    try {
      output.close();
      socket.close();
    }
    catch (IOException ex) {
      Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void connect() throws IOException {
    this.socket = new Socket(address, port);
    OutputStream socketOut = socket.getOutputStream();
    output = new ObjectOutputStream(socketOut);
  }

  public int getPort() {
    return socket.getLocalPort();
  }

  public boolean isConnected() {
    if (socket == null) {
      return false;    
    }
    return socket.isConnected();
  }

}
