import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.*;

public class LoginScreen extends JFrame {
    JTextField usernameField;
    JPasswordField passwordField;
    JComboBox<String> roleBox;

    public LoginScreen() {
        setTitle("AMC Monitoring Portal - Login");
        setSize(850, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Background panel
        BackgroundPanel bgPanel = new BackgroundPanel("/lib_bg.jpg"); // Place image in resources
        bgPanel.setLayout(new BorderLayout());
        setContentPane(bgPanel);

        JLabel titleLabel = new JLabel("AMC Monitoring Portal", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        titleLabel.setForeground(new Color(0x2DB1AC));
        titleLabel.setBorder(new EmptyBorder(20, 10, 20, 10));
        bgPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formContainer = new RoundedPanel();
        formContainer.setBackground(new Color(0x2D2D2D));
        formContainer.setPreferredSize(new Dimension(350, 300));
        formContainer.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels with white text
        JLabel loginAsLabel = new JLabel("Login as:");
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        loginAsLabel.setForeground(Color.WHITE);
        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);

        // Role
        gbc.gridx = 0; gbc.gridy = 0;
        formContainer.add(loginAsLabel, gbc);
        roleBox = new JComboBox<>(new String[]{"Admin", "Personnel"});
        gbc.gridx = 1;
        formContainer.add(roleBox, gbc);
        roleBox.setBackground(Color.WHITE);

        // Username
        gbc.gridx = 0; gbc.gridy = 1;
        formContainer.add(usernameLabel, gbc);
        usernameField = new JTextField(15);
        gbc.gridx = 1;
        formContainer.add(usernameField, gbc);
        usernameField.setBackground(Color.WHITE);

        // Password
        gbc.gridx = 0; gbc.gridy = 2;
        formContainer.add(passwordLabel, gbc);
        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        formContainer.add(passwordField, gbc);
passwordField.setBackground(Color.WHITE);

        // Login button
        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(0x2DB1AC));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        loginBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginBtn.addActionListener(e -> login());

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        formContainer.add(loginBtn, gbc);

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.add(formContainer);
        bgPanel.add(centerWrapper, BorderLayout.CENTER);

        setVisible(true);
    }

    void login() {
        String user = usernameField.getText().trim();
        String pass = new String(passwordField.getPassword()).trim();
        String role = roleBox.getSelectedItem().toString().toLowerCase();

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        boolean loggedIn = false;

        // ✅ DB authentication
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE username=? AND password=? AND role=?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, user);
                ps.setString(2, pass);
                ps.setString(3, role);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login successful as " + role);
                    dispose();
                    if (role.equals("admin")) {
                        new AdminDashboard().setVisible(true);
                    } else {
                        new PersonnelDashboard().setVisible(true);
                    }
                    loggedIn = true;
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }

        // ✅ Fallback hardcoded credentials
        if (!loggedIn) {
            if (role.equals("admin")) {
                if (user.equals("admin1") && pass.equals("pass123")) {
                    JOptionPane.showMessageDialog(this, "Login successful as Admin");
                    dispose();
                    new AdminDashboard().setVisible(true);
                    loggedIn = true;
                }
            } else if (role.equals("personnel")) {
                if (user.equals("person1") && pass.equals("pass456")) {
                    JOptionPane.showMessageDialog(this, "Login successful as Personnel");
                    dispose();
                    new PersonnelDashboard().setVisible(true);
                    loggedIn = true;
                }
            }
        }

        if (!loggedIn) {
            JOptionPane.showMessageDialog(this, "Invalid credentials or role.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginScreen::new);
    }
}

// Background panel class
class BackgroundPanel extends JPanel {
    private Image background;

    public BackgroundPanel(String imagePath) {
        try {
            background = new ImageIcon(getClass().getResource(imagePath)).getImage();
        } catch (Exception e) {
            System.err.println("Background image not found: " + imagePath);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

// Rounded panel class
class RoundedPanel extends JPanel {
    public RoundedPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
        g2.dispose();
        super.paintComponent(g);
    }
}
