 // private JPanel createAssignTasksPage() {
    //     JPanel panel = new JPanel(new GridBagLayout());
    //     panel.setBackground(new Color(45, 45, 45));
    //     GridBagConstraints gbc = new GridBagConstraints();
    //     gbc.insets = new Insets(10, 10, 10, 10);
    //     gbc.fill = GridBagConstraints.HORIZONTAL;

    //     // Title
    //     JLabel titleLabel = new JLabel("Assign a New Task");
    //     titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
    //     titleLabel.setForeground(new Color(45, 175, 188));
    //     gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
    //     panel.add(titleLabel, gbc);
    //     gbc.gridwidth = 1;

    //     // Description
    //     gbc.gridx = 0; gbc.gridy = 1;
    //     JLabel descLabel = new JLabel("Description:");
    //     descLabel.setForeground(Color.WHITE);
    //     panel.add(descLabel, gbc);
    //     gbc.gridx = 1;
    //     panel.add(new JTextField(20), gbc);

    //     // Category
    //     gbc.gridx = 0; gbc.gridy = 2;
    //     JLabel categoryLabel = new JLabel("Category:");
    //     categoryLabel.setForeground(Color.WHITE);
    //     panel.add(categoryLabel, gbc);
    //     gbc.gridx = 1;
    //     panel.add(new JComboBox<>(new String[]{"daily", "weekly", "monthly"}), gbc);

    //     // Assigned To
    //     gbc.gridx = 0; gbc.gridy = 3;
    //     JLabel assignedLabel = new JLabel("Assigned To (User ID):");
    //     assignedLabel.setForeground(Color.WHITE);
    //     panel.add(assignedLabel, gbc);
    //     gbc.gridx = 1;
    //     panel.add(new JTextField(20), gbc);

    //     // Due Date
    //     gbc.gridx = 0; gbc.gridy = 4;
    //     JLabel dueDateLabel = new JLabel("Due Date (yyyy-mm-dd):");
    //     dueDateLabel.setForeground(Color.WHITE);
    //     panel.add(dueDateLabel, gbc);
    //     gbc.gridx = 1;
    //     panel.add(new JTextField(20), gbc);

    //     // Submit Button
    //     gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
    //     JButton submitButton = new JButton("Assign Task");
    //     submitButton.setFont(new Font("SansSerif", Font.BOLD, 14));
    //     submitButton.setForeground(Color.WHITE);
    //     submitButton.setBackground(new Color(45, 175, 188));
    //     panel.add(submitButton, gbc);

    //     return panel;
    // }