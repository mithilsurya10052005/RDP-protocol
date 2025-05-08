import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatBoxClient extends Thread {
    Socket socket;
    private final int port = 6969;
    String ip;
    DataInputStream in;
    static DataOutputStream out;
    volatile boolean running = true;
    ChatDisplayClient chatDisplayClient;
    ChatBoxClient(String ip, ChatDisplayClient client) {
        this.ip = ip;
        chatDisplayClient = client;
        start();
    }

    @Override
    public void run() {
        try {
            //ChatDisplayClient chatDisplayClient = new ChatDisplayClient();
            socket = new Socket(ip, port);
            System.out.println("Connected to server");
            //ChatDisplayClient chatDisplayClient = new ChatDisplayClient();
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());

            while (running) {
                try {
                    String message = in.readUTF();
                    chatDisplayClient.putMessage("Server", message);
                    // Call function to display message
                } catch (IOException e) {
                    System.out.println("Server disconnected.");
                    running = false;
                }
            }

        } catch (IOException e) {
            System.out.println("Unable to connect to server: " + e.getMessage());
        } finally {
            closeResources();
        }
    }

    public static void sendMessage(String message) throws IOException {

        out.writeUTF(message);
        out.flush();
    }

    void closeResources() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (IOException e) {
            System.out.println("Error while closing resources.");
        }
    }
}
