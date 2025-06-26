import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ViewAllTasksPanel extends JPanel {
    private JTable table;
    private JTextField searchField;
    private DefaultTableModel tableModel;

    public ViewAllTasksPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        // Title
        JLabel title = new JLabel("All Tasks Overview");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.add(new JLabel("Search:"));
        searchField = new JTextField(20);
        searchPanel.add(searchField);
        add(searchPanel, BorderLayout.NORTH);

        // Table with columns
        String[] columns = {"ID", "Task Name", "Frequency", "Due Date", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Dummy data — later you can populate this from DB
        addRow("T101", "Server maintenance", "Daily", "2025-06-27", "Pending");
        addRow("T102", "Network audit", "Weekly", "2025-07-02", "Completed");
        addRow("T103", "Compliance report prep", "Monthly", "2025-07-31", "In Progress");
        addRow("T104", "User access review", "Monthly", "2025-07-28", "Pending");

        // Filter table as you type
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterTable(searchField.getText());
            }
        });
    }

    private void addRow(String id, String taskName, String freq, String dueDate, String status) {
        tableModel.addRow(new Object[]{id, taskName, freq, dueDate, status});
    }

    private void filterTable(String keyword) {
        keyword = keyword.toLowerCase();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String rowText = "";
            for (int col = 0; col < tableModel.getColumnCount(); col++) {
                rowText += tableModel.getValueAt(i, col).toString().toLowerCase() + " ";
            }
            table.setRowHeight(i, rowText.contains(keyword) ? table.getRowHeight() : 0);
        }
    }
}
