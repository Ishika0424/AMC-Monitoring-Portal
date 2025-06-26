import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ViewWeeklyTasksPanel extends JPanel {
    private JTable table;
    private JTextField searchField;
    private DefaultTableModel tableModel;

    public ViewWeeklyTasksPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Weekly Tasks");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.add(new JLabel("Search:"));
        searchField = new JTextField(20);
        searchPanel.add(searchField);
        add(searchPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Task Name", "Due Date", "Status"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        addRow("W101", "Server patching", "2025-07-03", "Pending");
        addRow("W102", "Network optimization", "2025-07-05", "Completed");

        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) { filterTable(searchField.getText()); }
        });
    }

    private void addRow(String id, String name, String dueDate, String status) {
        tableModel.addRow(new Object[]{id, name, dueDate, status});
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
