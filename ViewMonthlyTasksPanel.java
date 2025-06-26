import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ViewMonthlyTasksPanel extends JPanel {
    private JTable table;
    private JTextField searchField;
    private DefaultTableModel tableModel;

    public ViewMonthlyTasksPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        // Title
        JLabel title = new JLabel("Monthly Tasks");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.add(new JLabel("Search:"));
        searchField = new JTextField(20);
        searchPanel.add(searchField);
        add(searchPanel, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(new Object[]{"ID", "Task Name", "Deadline", "Status"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Dummy data — you can load real data here
        addRow("M001", "Backup Databases", "2025-07-25", "Pending");
        addRow("M002", "Review Performance", "2025-07-28", "Completed");
        addRow("M003", "Clean Temp Files", "2025-07-30", "In Progress");

        // Filter logic
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterTable(searchField.getText());
            }
        });
    }

    private void addRow(String id, String taskName, String deadline, String status) {
        tableModel.addRow(new Object[]{id, taskName, deadline, status});
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
