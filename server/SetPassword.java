import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetPassword extends JFrame implements ActionListener {
    static String port = "4907";
    JButton submitButton;
    JPasswordField passwordField;
    String value1;
    JLabel errorLabel;

    SetPassword() {
        setTitle("Set Client Password");
        setSize(300, 250); // Fixed size
        setResizable(false); // Prevent maximizing
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Gradient background panel
        JPanel background = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(255, 255, 255),
                        0, getHeight(), new Color(210, 225, 255));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        background.setLayout(new GridBagLayout());
        setContentPane(background);

        // Layout constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Label
        JLabel titleLabel = new JLabel("Set Password:");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(44, 62, 80));
        background.add(titleLabel, gbc);

        // Password field
        gbc.gridy++;
        passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordField.setEchoChar('•');
        background.add(passwordField, gbc);

        // Submit button
        gbc.gridy++;
        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(150, 45));
        submitButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(new Color(41, 128, 185));
        submitButton.setFocusPainted(false);
        submitButton.setBorderPainted(false);
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.setContentAreaFilled(false);
        submitButton.setOpaque(true);
        submitButton.setBorder(BorderFactory.createEmptyBorder());

        submitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitButton.setBackground(new Color(31, 97, 141));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitButton.setBackground(new Color(41, 128, 185));
            }
        });

        background.add(submitButton, gbc);

        // Error label (optional usage)
        gbc.gridy++;
        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        background.add(errorLabel, gbc);

        // Add action listener
        submitButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        value1 = new String(passwordField.getPassword());
        dispose();

        new Thread(() -> {
            new InitConnection(Integer.parseInt(port), value1);
        }).start();
    }

    public String getValue1() {
        return value1;
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            new SetPassword().setVisible(true);
//        });
//    }
}
