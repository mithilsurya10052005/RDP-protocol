import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.awt.*;
import java.io.*;

public class Authentication extends JFrame implements ActionListener {

  private Socket cSocket = null;
  DataInputStream verification = null;
  DataOutputStream passchk = null;
  String verify = "";
  JButton submit;
  JPasswordField passwordField;
  JLabel titleLabel;
  String width = "", height = "";

  Authentication(Socket csocket) {
    this.cSocket = csocket;

    setTitle("Login Form");
    setSize(400, 200);
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    // Gradient background panel
    JPanel background = new JPanel() {
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(
                0, 0, new Color(255, 255, 255),
                0, getHeight(), new Color(210, 225, 255));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
      }
    };
    background.setLayout(new GridBagLayout());
    setContentPane(background);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(12, 12, 12, 12);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // Label
    titleLabel = new JLabel("Enter Password:");
    titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
    titleLabel.setForeground(new Color(44, 62, 80));
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    background.add(titleLabel, gbc);

    // Password field
    passwordField = new JPasswordField(15);
    passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    passwordField.setEchoChar('•');
    gbc.gridy++;
    background.add(passwordField, gbc);

    // Submit button
    submit = new JButton("Submit");
    styleButton(submit, new Color(41, 128, 185));
    gbc.gridy++;
    background.add(submit, gbc);

    submit.addActionListener(this);
  }

  private void styleButton(JButton button, Color baseColor) {
    button.setFont(new Font("Segoe UI", Font.BOLD, 14));
    button.setForeground(Color.WHITE);
    button.setBackground(baseColor);
    button.setOpaque(true);
    button.setBorderPainted(false);
    button.setFocusPainted(false);
    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    button.setPreferredSize(new Dimension(150, 40));

    button.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        button.setBackground(baseColor.darker());
      }

      public void mouseExited(java.awt.event.MouseEvent evt) {
        button.setBackground(baseColor);
      }
    });
  }

  public void actionPerformed(ActionEvent ae) {
    String value1 = new String(passwordField.getPassword());

    try {
      passchk = new DataOutputStream(cSocket.getOutputStream());
      verification = new DataInputStream(cSocket.getInputStream());

      passchk.writeUTF(value1);
      verify = verification.readUTF();

      if (verify.equals("valid")) {
        width = verification.readUTF();
        height = verification.readUTF();
        new CreateFrame(cSocket, width, height);
        dispose();
      } else {
        JOptionPane.showMessageDialog(this, "Incorrect password", "Error", JOptionPane.ERROR_MESSAGE);
        dispose();
      }
    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(this, "Connection error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      dispose();
    }
  }
}
