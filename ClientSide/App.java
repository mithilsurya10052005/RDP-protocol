import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;

public class App {
    public static void main(String[] args) throws SocketException {
        JFrame frame = new JFrame("RDP_Share");
        frame.getContentPane().setBackground(new Color(55, 55, 56));
        IpAddr ipAddr = new IpAddr();
        JLabel label = new JLabel("Your IP address is : " + ipAddr.getIp());
        label.setForeground(new Color(232, 82, 66));
        label.setFont(new Font("Arial", Font.BOLD, 24));
        Button button = new Button("Server");
        button.addActionListener(e ->{
            frame.dispose();
            Start.start();
        });
        Button button1 = new Button("Client");
        button1.addActionListener(e ->{
            frame.dispose();
            StartClient.start();

        });
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 100));
        frame.add(label);
        frame.add(button);
        frame.add(button1);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

//    public static String getIP() throws SocketException {
//        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
//        for (NetworkInterface netint : Collections.list(nets)){
//            System.out.println(netint);
//        }
//    return null;
//    }
}
