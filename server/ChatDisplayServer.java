import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ChatDisplayServer {
    JFrame frame;
    JPanel mainPanel, bottomPanel;
    JScrollPane scrollPane;
    JTextField textField;
    JButton button;

    ChatDisplayServer() {
        createBox();
    }

    private void createBox() {
        frame = new JFrame("Chatbox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 650);
        frame.setLocationRelativeTo(null); // Centered on screen
        frame.setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        button = new JButton("Send");
        styleButton(button, new Color(41, 128, 185));
        button.addActionListener(e -> {
            try {
                handleSend();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        bottomPanel.add(textField, BorderLayout.CENTER);
        bottomPanel.add(button, BorderLayout.EAST);
        bottomPanel.setPreferredSize(new Dimension(0, 50));

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void styleButton(JButton button, Color baseColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(baseColor);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(80, 35));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(baseColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(baseColor);
            }
        });
    }

    private void handleSend() throws IOException {
        String message = textField.getText().trim();
        if (message.isEmpty()) return;

        textField.setText("");

        ChatBox.sendMessage(message);
        putMessage("you", message);
    }

    public void putMessage(String sender, String message) {
        String htmlText = "<html><body style='width: 200px'><b>" + sender + ":</b><br>" + message + "</body></html>";
        JLabel messageLabel = new JLabel(htmlText);
        messageLabel.setOpaque(true);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
        messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messageLabel.setBackground(sender.equals("you") ? new Color(220, 248, 198) : new Color(230, 230, 230));
        messageLabel.setForeground(Color.BLACK);

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
