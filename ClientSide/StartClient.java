import java.net.*;
import javax.swing.*;
import java.awt.*;

public class StartClient {
  static String port = "4907";

  public static void main(String[] args) throws SocketException {
    StartClient.start();
  }

  public static void start() {
    ImageIcon icon = new ImageIcon("anydesk.png");
    String[] options = {"QUIT", "GO"};

    // Build a panel instead of raw Object[] for better control
    JPanel panel = new JPanel(new BorderLayout(10, 10));
    JLabel label = new JLabel("Enter the IP Address:");
    label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    JTextField ipField = new JTextField(15);
    ipField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

    panel.add(label, BorderLayout.NORTH);
    panel.add(ipField, BorderLayout.CENTER);
    panel.setPreferredSize(new Dimension(300, 60));

    int choice = JOptionPane.showOptionDialog(
            null,
            panel,
            "RDP-Access",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            icon,
            options,
            options[1]
    );

    if (choice == 1) { // index 1 = GO
      String ip = ipField.getText().trim();
      System.out.println("IP received: " + ip);
      if (!ip.isEmpty()) {
        new StartClient().initialize(ip, Integer.parseInt(port));
      } else {
        JOptionPane.showMessageDialog(null, "IP address cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
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

      Authentication frame1 = new Authentication(sc);
      frame1.setSize(400, 150); // Larger size for better layout
      frame1.setResizable(false);
      frame1.setLocationRelativeTo(null); // Centered on screen
      frame1.setVisible(true);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Failed to connect: " + e.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
      e.printStackTrace();
    }
  }
}
