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
  JPanel panel;
  JLabel label, label2;
  String width = "", height = "";
  JTextField text1;

  Authentication(Socket csocket) {
    label2 = new JLabel();
    label2.setText("Password");
    text1 = new JTextField();
    this.cSocket = csocket;
    label = new JLabel();
    label.setText("");
    this.setLayout(new BorderLayout());
    submit = new JButton("submit");
    panel = new JPanel(new GridLayout(2, 2));
    panel.add(label2);
    panel.add(text1);
    panel.add(label);
    panel.add(submit);
    add(panel, BorderLayout.CENTER);
    submit.addActionListener(this);
    setTitle("Login form");
  }

  public void actionPerformed(ActionEvent ae) {
    String value1 = text1.getText();
    try {
      passchk = new DataOutputStream(cSocket.getOutputStream());
      verification = new DataInputStream(cSocket.getInputStream());
      passchk.writeUTF(value1);
      verify = verification.readUTF();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (verify.equals("valid")) {
      try {
        width = verification.readUTF();
        height = verification.readUTF();
      } catch (IOException e) {
        e.printStackTrace();
      }
      CreateFrame abc = new CreateFrame(cSocket, width, height);
      dispose();
    } else {
      System.out.println("PLS enter valid password ");
      JOptionPane.showMessageDialog(this, "Incorrect password ", "Error", JOptionPane.ERROR_MESSAGE);
      dispose();
    }
  }
}
