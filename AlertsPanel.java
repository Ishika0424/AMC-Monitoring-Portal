import javax.swing.*;
import java.awt.*;

public class AlertsPanel extends JPanel {
    public AlertsPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Alerts Section...", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
