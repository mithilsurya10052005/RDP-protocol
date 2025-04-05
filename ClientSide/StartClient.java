import java.net.*;
import javax.swing.JOptionPane;

public class StartClient {
  static String port = "4907";

  public static void main(String args[]) {
    // a text box to enter the server ip
    String ip = JOptionPane.showInputDialog("Enter the IP Address");

    new StartClient().intialize(ip, Integer.parseInt(port));
  }

  public void intialize(String ip, int port) {
    try {
      Socket sc = new Socket(ip, port);
      System.out.println("Connecting to server");
      Authentication frame1 = new Authentication(sc);
      frame1.setSize(300, 80);
      frame1.setLocation(500, 300);

      frame1.setVisible(true);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}