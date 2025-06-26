import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AlertsPanel extends JPanel {
    public AlertsPanel() {
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JLabel title = new JLabel("Alerts");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID","Message","Time"},0);
        JTable table = new JTable(model);
        model.addRow(new Object[]{"A01","Disk space low","2025-06-25 10:00"});
        model.addRow(new Object[]{"A02","CPU load high","2025-06-25 11:30"});

        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
