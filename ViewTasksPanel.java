import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewTasksPanel extends JPanel {

    public ViewTasksPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(45, 45, 45)); // Dark background

        // Title label
        JLabel titleLabel = new JLabel("All Tasks", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(45, 175, 188));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Dummy data columns and rows
        String[] columnNames = {"Task ID", "Description", "Category", "Assigned To", "Due Date", "Status"};
        Object[][] dummyData = {
            {"T101", "Check equipment status", "daily", "User 1001", "2025-06-26", "Pending"},
            {"T102", "Replace light bulbs", "weekly", "User 1002", "2025-06-28", "Completed"},
            {"T103", "Test backup generator", "monthly", "User 1003", "2025-06-30", "Pending"},
        };
        DefaultTableModel model = new DefaultTableModel(dummyData, columnNames);
        JTable taskTable = new JTable(model);
        taskTable.setFillsViewportHeight(true);
        taskTable.setRowHeight(24);
        taskTable.setBackground(Color.WHITE);
        taskTable.setSelectionBackground(new Color(45, 175, 188));
        taskTable.setSelectionForeground(Color.WHITE);
        taskTable.getTableHeader().setBackground(new Color(70, 130, 180));
        taskTable.getTableHeader().setForeground(Color.WHITE);
        taskTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(taskTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // ✅ Later you can replace dummyData with actual DB data
    }
}
