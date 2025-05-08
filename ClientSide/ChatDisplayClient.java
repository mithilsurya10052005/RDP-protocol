import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatDisplayClient {
     JFrame frame;
     JPanel mainPanel, bottomPanel;
     JScrollPane scrollPane;
     JTextField textField;

    ChatDisplayClient() {
        createBox();
    }

    private  void createBox() {
        frame = new JFrame("chatbox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        frame.setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        textField = new JTextField(30);
        textField.setEditable(true);
        JButton button = new JButton("Send");

        button.addActionListener(e -> {
            try {
                handelsend();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.ORANGE);
        bottomPanel.setPreferredSize(new Dimension(0, 40));
        bottomPanel.add(textField, BorderLayout.CENTER);
        bottomPanel.add(button, BorderLayout.EAST);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private  void handelsend() throws IOException {
        String message = textField.getText();
        textField.setText("");
        ChatBoxClient.sendMessage(message);
        putmessage("you", message);
    }

    public void putmessage(String sender, String message) {
        String htmlText = "<html><b>" + sender + ":</b><br>" + message + "<br><small>";
        JLabel messageLabel = new JLabel(htmlText);
        messageLabel.setOpaque(true);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        if (sender.equals("you")) {
            messageLabel.setBackground(new Color(220, 248, 198));
        } else {
            messageLabel.setBackground(new Color(230, 230, 230));
        }

        messageLabel.setMaximumSize(new Dimension(250, Integer.MAX_VALUE));

        JPanel wrapper = new JPanel(new FlowLayout(sender.equals("you") ? FlowLayout.LEFT : FlowLayout.RIGHT));
        wrapper.setBackground(Color.WHITE);
        wrapper.add(messageLabel);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(Color.WHITE);
        container.add(wrapper);
        container.add(Box.createVerticalStrut(10));

        mainPanel.add(container);
        mainPanel.revalidate();
        mainPanel.repaint();

        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }
}