import java.awt.Robot;
import java.net.Socket;
import java.util.Scanner;

public class ReceiveEvents extends Thread{

    public ReceiveEvents(Socket socket, Robot robot) {
        //TODO Auto-generated constructor stub
        this.socket=socket;
        this.robot=robot;
        start();
    }
    Socket socket=null;
    Robot robot=null;
    boolean continueLoop=true;
    public void run(){
        Scanner scanner=null;

        try {
            scanner=new Scanner(socket.getInputStream());

            while (continueLoop){
                int command=scanner.nextInt();
                switch (command) {
                    case -1:
                        robot.mousePress(scanner.nextInt());
                        break;
                
                    case -2:
                        robot.mouseRelease(scanner.nextInt());
                        break;
                    case -3:
                        robot.keyPress(scanner.nextInt());
                        break;
                    case -4:
                        robot.keyRelease(scanner.nextInt());
                        break;
                    case -5:
                        robot.mouseMove(scanner.nextInt(),scanner.nextInt());
                        break;   
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

}

