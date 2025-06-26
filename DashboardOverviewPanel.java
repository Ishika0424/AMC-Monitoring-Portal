import javax.swing.*;
import java.awt.*;

public class DashboardOverviewPanel extends JPanel {

    public DashboardOverviewPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title
        JLabel title = new JLabel("Dashboard Overview");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(45, 175, 188));
        add(title, BorderLayout.NORTH);

        // Stats grid
        JPanel statsGrid = new JPanel(new GridLayout(2, 2, 20, 20));
        statsGrid.setBackground(Color.WHITE);

        statsGrid.add(createStatCard("Total Tasks Assigned", "25", new Color(45, 175, 188)));  // Dummy
        statsGrid.add(createStatCard("Tasks Completed", "18", new Color(100, 200, 100)));       // Dummy
        statsGrid.add(createStatCard("Pending Tasks", "5", new Color(255, 165, 0)));            // Dummy
        statsGrid.add(createStatCard("Overdue Tasks", "2", new Color(255, 85, 55)));            // Dummy

        add(statsGrid, BorderLayout.CENTER);

        // Upcoming Tasks list
        JPanel upcomingTasksPanel = new JPanel(new BorderLayout());
        upcomingTasksPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        JLabel upcomingLabel = new JLabel("Upcoming Tasks (Next 7 Days)");
        upcomingLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        upcomingTasksPanel.add(upcomingLabel, BorderLayout.NORTH);

        JTextArea upcomingTasksArea = new JTextArea();
        upcomingTasksArea.setEditable(false);
        upcomingTasksArea.setText(
                "• Server backup - Due 2025-07-01\n" +
                "• Network switch replacement - Due 2025-07-02\n" +
                "• Antivirus license renewal - Due 2025-07-03\n"
        );

        upcomingTasksPanel.add(new JScrollPane(upcomingTasksArea), BorderLayout.CENTER);

        add(upcomingTasksPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates a stat card panel with a title, value, and background color.
     */
    private JPanel createStatCard(String title, String value, Color bgColor) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(bgColor);
        panel.setPreferredSize(new Dimension(200, 120));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);

        JLabel valueLabel = new JLabel(value, JLabel.CENTER);
        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        valueLabel.setForeground(Color.WHITE);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(valueLabel, BorderLayout.CENTER);

        return panel;
    }
}
