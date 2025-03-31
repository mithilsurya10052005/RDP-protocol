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
        //TODO Auto-generated constructor stub
        Robot robot=null;
        Rectangle rectangle=null;
        try {
            System.out.println("waiting for client to get connected");
            socket=new ServerSocket(port);
            GraphicsEnvironment gEnv=GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gDev=gEnv.getDefaultScreenDevice();
            Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
            String width=""+dim.getWidth();
            String height=""+dim.getHeight();
            rectangle=new Rectangle(dim);
            robot=new Robot(gDev);
            drawGUI(); 
            while(true){
                Socket sc=socket.accept();
                password=new DataInputStream(sc.getInputStream());
                verify=new DataOutputStream(sc.getOutputStream());

                String passWord=password.readUTF();
                if(passWord.equals(value1)){
                    verify.writeUTF("valid");
                    verify.writeUTF(width);
                    verify.writeUTF(height);
                    new SendScreen(sc,robot,rectangle);
                    new ReceiveEvents(sc,robot);
                }
                else{
                    verify.writeUTF("invalid");
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }{

        }
    }
    ServerSocket socket=null;
    DataInputStream password=null;
    DataOutputStream verify=null;
    String width="";
    String height="";
    private void drawGUI(){

    }
}
