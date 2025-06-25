import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    public DashboardPanel() {
        setLayout(new GridLayout(2, 2, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(createStatCard("Total Tasks", 0, new Color(45, 175, 188)));
        add(createStatCard("Completed Tasks", 0, new Color(100, 200, 100)));
        add(createStatCard("Pending Tasks", 0, new Color(255, 165, 0)));
        add(createStatCard("Overdue Tasks", 0, new Color(255, 85, 55)));
    }

    private JPanel createStatCard(String title, int value, Color bgColor) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(bgColor);
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);

        JLabel valueLabel = new JLabel(String.valueOf(value), SwingConstants.CENTER);
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
        panel.add(valueLabel, BorderLayout.CENTER);

        return panel;
    }
}
