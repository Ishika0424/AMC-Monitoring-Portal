import javax.swing.*;
import java.awt.*;

public class AssignTasksPanel extends JPanel {
    public AssignTasksPanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(45, 45, 45)); // Dark background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Assign a New Task");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(45, 175, 188));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        gbc.gridwidth = 1;

        // Description
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel descLabel = new JLabel("Description:");
        descLabel.setForeground(Color.WHITE);
        add(descLabel, gbc);

        gbc.gridx = 1;
        JTextArea descriptionField = new JTextArea(4, 20);
        descriptionField.setLineWrap(true);
        descriptionField.setWrapStyleWord(true);
        descriptionField.setBackground(Color.WHITE);
        add(new JScrollPane(descriptionField), gbc);

        // Category
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel catLabel = new JLabel("Category:");
        catLabel.setForeground(Color.WHITE);
        add(catLabel, gbc);

        gbc.gridx = 1;
        JComboBox<String> categoryBox = new JComboBox<>(new String[]{"daily", "weekly", "monthly"});
        categoryBox.setBackground(Color.WHITE);
        add(categoryBox, gbc);

        // Assigned To
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel assignedLabel = new JLabel("Assigned To (User ID):");
        assignedLabel.setForeground(Color.WHITE);
        add(assignedLabel, gbc);

        gbc.gridx = 1;
        JTextField assignedField = new JTextField(15);
        assignedField.setBackground(Color.WHITE);
        add(assignedField, gbc);

        // Due Date
        gbc.gridx = 0; gbc.gridy = 4;
        JLabel dueLabel = new JLabel("Due Date (yyyy-mm-dd):");
        dueLabel.setForeground(Color.WHITE);
        add(dueLabel, gbc);

        gbc.gridx = 1;
        JTextField dueField = new JTextField(15);
        dueField.setBackground(Color.WHITE);
        add(dueField, gbc);

        // Submit Button
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        JButton assignButton = new JButton("Assign Task");
        assignButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        assignButton.setBackground(new Color(45, 175, 188));
        assignButton.setForeground(Color.WHITE);
        assignButton.setFocusPainted(false);
        add(assignButton, gbc);

        // ✅ Later you can add assignButton.addActionListener(...) here to perform DB insert.
    }
}
