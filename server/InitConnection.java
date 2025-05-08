import javax.swing.*;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class InitConnection {
    public InitConnection(int port, String value1) {
        new Thread(() -> {
            try {
                Robot robot;
                Rectangle rectangle;
                System.out.println("Waiting for client to connect...");
                ServerSocket socket = new ServerSocket(port);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                String width = String.valueOf(dim.getWidth());
                String height = String.valueOf(dim.getHeight());
                rectangle = new Rectangle(dim);
                GraphicsDevice gDev = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
                robot = new Robot(gDev);

                while (true) {
                    Socket sc = socket.accept();
                    DataInputStream password = new DataInputStream(sc.getInputStream());
                    DataOutputStream verify = new DataOutputStream(sc.getOutputStream());
                    String passWord = password.readUTF();

                    if (passWord.equals(value1)) {
                        verify.writeUTF("valid");
                        verify.writeUTF(width);
                        verify.writeUTF(height);
                        new SendScreen(sc, robot, rectangle);
                        new ReceiveEvents(sc, robot);
                    } else {
                        verify.writeUTF("invalid");
                        sc.close();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    private void drawGUI() {}
}