import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    public SettingsPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Settings Section...", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
