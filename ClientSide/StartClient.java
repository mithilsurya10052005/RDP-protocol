import java.net.*;
import javax.swing.*;

public class StartClient {
  static String port = "4907";
  public static void main(String[] args) throws SocketException {
   new StartClient().start();
  }
  public static void start() {
    ImageIcon icon = new ImageIcon("anydesk.png");
    String[] options = {"QUIT", "GO"};

    JTextField ipField = new JTextField();
    Object[] message = {
            "Enter the IP Address:", ipField
    };

    int choice = JOptionPane.showOptionDialog(
            null,
            message,
            "RDP-Access",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            icon,
            options,
            options[1]
    );

    if (choice == 1) { // index 1 corresponds to "GO"
      String ip = ipField.getText();
      System.out.println("ip received "+ip);
      if (ip != null && !ip.trim().isEmpty()) {
        new StartClient().initialize(ip, Integer.parseInt(port));
      }
    } else {
      System.out.println("User chose to quit or closed the dialog.");
    }
  }

  public void initialize(String ip, int port) {
    try {
      ChatDisplayClient client = new ChatDisplayClient();
      new ChatBoxClient(ip, client);
      Socket sc = new Socket(ip, port);

      //new ChatBoxClient(ip,client);
      Authentication frame1 = new Authentication(sc);

      frame1.setSize(300, 80);
      frame1.setLocation(500, 300);
      frame1.setVisible(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
