import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportsPanel extends JPanel {
    private DefaultTableModel tableModel;
    private JTable reportsTable;

    public ReportsPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        // Title
        JLabel title = new JLabel("Reports");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);

        // Table setup
        tableModel = new DefaultTableModel(new Object[]{"Report Name", "Date Generated", "Status"}, 0);
        reportsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(reportsTable);
        add(scrollPane, BorderLayout.CENTER);

        // Dummy report data
        addReportRow("Monthly Activity Summary", "2025-06-01", "Completed");
        addReportRow("Server Health Report", "2025-06-15", "Completed");
        addReportRow("User Login Report", "2025-06-24", "Pending");
        addReportRow("Error Logs Report", "2025-06-25", "Pending");

        // Refresh button
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(ReportsPanel.this,
                        "This is where you'd reload report data from a database.", 
                        "Refresh Action", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.add(title, BorderLayout.WEST);
        topPanel.add(refreshButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
    }

    private void addReportRow(String name, String date, String status) {
        tableModel.addRow(new Object[]{name, date, status});
    }
}
