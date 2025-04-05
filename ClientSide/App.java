import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("RDP_Share");
        frame.getContentPane().setBackground(new Color(55, 55, 56));
        JLabel label = new JLabel("Your IP address is : " + getIP());
        label.setForeground(new Color(232, 82, 66));
        label.setFont(new Font("Arial", Font.BOLD, 24));
        Button button = new Button("Server");
        button.addActionListener(e ->{
            frame.dispose();
            StartClient.start();
        });
        Button button1 = new Button("Client");
        button1.addActionListener(e ->{
            frame.dispose();
            Start.start();
        });
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 100));
        frame.add(label);
        frame.add(button);
        frame.add(button1);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static String getIP() {
        try {
            URL whatIsMyIp = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(whatIsMyIp.openStream()));
            String publicIp = in.readLine();
            System.out.println("Public IP Address: " + publicIp);
            in.close();
            return publicIp;
        } catch (IOException e) {
            System.err.println("Error getting public IP: " + e.getMessage());
        }
        return "xxx";
    }
}
