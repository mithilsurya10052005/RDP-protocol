import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.net.SocketException;

public class App {
    public static void main(String[] args) throws SocketException {
        JFrame frame = new JFrame("RDP_Share");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);

        // Gradient background panel
        JPanel mainPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(255, 255, 255), 0, getHeight(), new Color(200, 200, 255));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        frame.setContentPane(mainPanel);
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // IP Label
        IpAddr ipAddr = new IpAddr();
        JLabel label = new JLabel("Your IP address is : " + ipAddr.getIp());
        label.setForeground(new Color(52, 73, 94));
        label.setFont(new Font("Segoe UI", Font.BOLD, 26));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Server Button
        RoundedButton serverButton = new RoundedButton("Server", "ClientSide/server.png", new Color(46, 204, 113));
        serverButton.addActionListener(e -> {
            frame.dispose();
            Start.start();
        });

        // Client Button
        RoundedButton clientButton = new RoundedButton("Client", "ClientSide/client.png", new Color(52, 152, 219));
        clientButton.addActionListener(e -> {
            frame.dispose();
            StartClient.start();
        });

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(serverButton);
        buttonPanel.add(clientButton);

        // Add all to main panel
        mainPanel.add(Box.createVerticalStrut(150));
        mainPanel.add(label);
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(buttonPanel);

        frame.setVisible(true);
    }
}

// Custom rounded button class
class RoundedButton extends JButton {
    private Color baseColor;

    public RoundedButton(String text, String iconPath, Color baseColor) {
        super(text);
        this.baseColor = baseColor;
        setPreferredSize(new Dimension(220, 60));
        setFont(new Font("Segoe UI", Font.BOLD, 18));
        setForeground(Color.WHITE);
        setBackground(baseColor);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);

        // Load icon
        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaledImage));
        setHorizontalTextPosition(SwingConstants.RIGHT);
        setIconTextGap(15);

        // Hover effect
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(baseColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(baseColor);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getBackground());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // No border
    }

    @Override
    public boolean isOpaque() {
        return false;
    }
}
