import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.SwingUtilities; // Add this import

public class ChatBox extends Thread {
    private final int port = 6969;
    ServerSocket serversocket;
    Socket socket;
    DataInputStream in;
    static DataOutputStream out;
    boolean running = true;
    ChatDisplayServer server;

    ChatBox(ChatDisplayServer serverUI,ServerSocket serverSocket) {
        this.server = serverUI;
        this.serversocket = serverSocket;
        start();
    }

    @Override
    public void run() {
        try {

            System.out.println("Server chat box started");
            System.out.println("Waiting for client on port " + port);
            socket = serversocket.accept();
            System.out.println("Client connected.");

            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());

            while (running) {
                try {
                    String message = in.readUTF();

                   System.out.println("Message received: " + message);
                  server.putmessage("Client", message);

                } catch (IOException e) {
                    System.out.println("Client disconnected.");
                    running = false;
                }
            }

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
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
            if (serversocket != null && !serversocket.isClosed()) serversocket.close();
        } catch (IOException e) {
            System.out.println("Error while closing server resources.");
        }
    }
}
