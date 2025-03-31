import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.imageio.ImageIO;

public class SendScreen extends Thread{
    Socket socket=null;
    Robot robot=null;
    Rectangle rectangle=null;
    boolean continueLoop=true;
    OutputStream oos=null;
    public SendScreen(Socket socket, Robot robot, Rectangle rectangle) {
        //TODO Auto-generated constructor stub
        this.socket=socket;
        this.robot=robot;
        this.rectangle=rectangle;
        start();
    }
    public void run(){
        try {
            oos=socket.getOutputStream();
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        while(continueLoop){
            BufferedImage image=robot.createScreenCapture(rectangle);
           try {
                ImageIO.write(image,"jpeg", oos);
            } catch (IOException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

}
