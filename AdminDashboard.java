import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class AdminDashboard extends JFrame {

    private JPanel contentPanel;
    private CardLayout cardLayout;
    private JTextField taskNameField;
    private JComboBox<String> frequencyComboBox;
    private JTextArea descriptionArea;

    public AdminDashboard() {
        setTitle("Admin Dashboard - AMC Monitoring Portal");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebar = new JPanel(new GridLayout(7, 1, 0, 5));
        sidebar.setBackground(Color.DARK_GRAY);
        sidebar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(sidebar, BorderLayout.WEST);

        // Content Panel with CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Pages
        JPanel dashboardPage = createDashboardPage();
        JPanel assignTasksPage = createAssignTasksPage();
        JPanel viewTasksPage = createPlaceholderPage("View All Tasks Section...");
        JPanel reportsPage = createPlaceholderPage("Reports Section...");
        JPanel alertsPage = createPlaceholderPage("Alerts Section...");
        JPanel settingsPage = createPlaceholderPage("Settings Section...");

        contentPanel.add(dashboardPage, "dashboard");
        contentPanel.add(assignTasksPage, "assignTasks");
        contentPanel.add(viewTasksPage, "viewTasks");
        contentPanel.add(reportsPage, "reports");
        contentPanel.add(alertsPage, "alerts");
        contentPanel.add(settingsPage, "settings");

        add(contentPanel, BorderLayout.CENTER);

        // Sidebar Buttons
        String[] options = {
            "Dashboard Overview",
            "Assign Tasks",
            "View All Tasks",
            "Reports",
            "Alerts",
            "Settings",
            "Logout"
        };
        String[] keys = {
            "dashboard",
            "assignTasks",
            "viewTasks",
            "reports",
            "alerts",
            "settings",
            "logout"
        };
        for (int i = 0; i < options.length; i++) {
            JButton btn = new JButton(options[i]);
            btn.setFocusPainted(false);
            btn.setBackground(new Color(45, 175, 188));
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("SansSerif", Font.BOLD, 14));
            String key = keys[i];
            btn.addActionListener(e -> {
                if (key.equals("logout")) {
                    dispose();
                    new LoginScreen().setVisible(true); // Back to login
                } else {
                    cardLayout.show(contentPanel, key);
                }
            });
            sidebar.add(btn);
        }

        setVisible(true);
    }

    // ⭐ Updated Assign Tasks page
    private JPanel createAssignTasksPage() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(45,45,45));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Assign a New Task");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(45,175,188));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);
        gbc.gridwidth = 1;

        // Task Name
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel nameLabel = new JLabel("Task Name:");
        nameLabel.setForeground(Color.WHITE);
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        taskNameField = new JTextField(20);
        panel.add(taskNameField, gbc);

        // Frequency
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel freqLabel = new JLabel("Frequency:");
        freqLabel.setForeground(Color.WHITE);
        panel.add(freqLabel, gbc);
        gbc.gridx = 1;
        frequencyComboBox = new JComboBox<>(new String[]{"daily","weekly","monthly"});
        panel.add(frequencyComboBox, gbc);

        // Description
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel descLabel = new JLabel("Description:");
        descLabel.setForeground(Color.WHITE);
        panel.add(descLabel, gbc);
        gbc.gridx = 1;
        descriptionArea = new JTextArea(4, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        panel.add(scrollPane, gbc);

        // Submit Button
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        JButton submitButton = new JButton("Assign Task");
        submitButton.setBackground(new Color(45,175,188));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> saveTask());
        panel.add(submitButton, gbc);

        return panel;
    }

    // ⭐ Save to DB
    private void saveTask() {
        String taskName = taskNameField.getText().trim();
        String frequency = frequencyComboBox.getSelectedItem().toString();
        String description = descriptionArea.getText().trim();

        if (taskName.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO tasks (task_name, frequency, description) VALUES (?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, taskName);
                ps.setString(2, frequency);
                ps.setString(3, description);

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Task assigned successfully!");
                    taskNameField.setText("");
                    descriptionArea.setText("");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Dashboard page
    private JPanel createDashboardPage() {
        JPanel statsPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        statsPanel.add(createStatCard("Total Tasks", 0, new Color(45, 175, 188)));     // Blue
        statsPanel.add(createStatCard("Completed Tasks", 0, new Color(100, 200, 100))); // Green
        statsPanel.add(createStatCard("Pending Tasks", 0, new Color(255, 165, 0)));   // Orange
        statsPanel.add(createStatCard("Overdue Tasks", 0, new Color(255, 85, 55)));   // Red
        return statsPanel;
    }

    private JPanel createStatCard(String title, int value, Color bgColor) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(bgColor);

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);

        JLabel valueLabel = new JLabel(String.valueOf(value), SwingConstants.CENTER);
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
        panel.add(valueLabel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createPlaceholderPage(String message) {
        JPanel page = new JPanel(new BorderLayout());
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.PLAIN, 24));
        page.add(label, BorderLayout.CENTER);
        return page;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminDashboard().setVisible(true));
    }
}
