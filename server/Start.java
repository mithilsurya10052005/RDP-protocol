import java.net.ServerSocket;

public class Start {
    public static void main(String[] args) {
        start();
    }
    public static void start() {

        try {
            ChatDisplayServer serverUI = new ChatDisplayServer();

            new ChatBox(serverUI,new ServerSocket(6969));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        SetPassword frame1=new SetPassword();
        frame1.setVisible(true);


    }
}
