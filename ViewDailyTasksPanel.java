import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ViewDailyTasksPanel extends JPanel {
    private JTable table;
    private JTextField searchField;
    private DefaultTableModel tableModel;

    public ViewDailyTasksPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        // Title
        JLabel title = new JLabel("Daily Tasks");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);

        // Search
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.add(new JLabel("Search:"));
        searchField = new JTextField(20);
        searchPanel.add(searchField);
        add(searchPanel, BorderLayout.NORTH);

        // Table
        String[] columns = {"Task ID", "Description", "Time", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Dummy data
        addRow("T001", "Check server logs", "10:00 AM", "Pending");
        addRow("T002", "Run backup scripts", "11:00 AM", "Completed");
        addRow("T003", "Check network status", "02:00 PM", "In Progress");

        // Filter as you type
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterTable(searchField.getText());
            }
        });
    }

    private void addRow(String id, String desc, String time, String status) {
        tableModel.addRow(new Object[]{id, desc, time, status});
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
