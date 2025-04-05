import java.awt.*;
import java.net.*;
import javax.swing.*;

public class StartClient {
  static String port = "4907";

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
      if (ip != null && !ip.trim().isEmpty()) {
        new StartClient().intialize(ip, Integer.parseInt(port));
      }
    } else {
      System.out.println("User chose to quit or closed the dialog.");
    }
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
