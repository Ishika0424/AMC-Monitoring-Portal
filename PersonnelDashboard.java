import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PersonnelDashboard extends JFrame {
    private JPanel mainPanel; // Content area
    private CardLayout cardLayout;

    public PersonnelDashboard() {
        setTitle("AMC Personnel Dashboard");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(7, 1, 0, 5));
        sidebar.setBackground(Color.BLACK);
        sidebar.setPreferredSize(new Dimension(200, getHeight()));

        // Main panel setup with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(createContentPanel("Dashboard Overview (Coming Soon)"), "Dashboard Overview");
        mainPanel.add(new ViewDailyTasksPanel(), "View Daily Tasks");
 mainPanel.add(new ViewWeeklyTasksPanel(), "View Weekly Tasks");
mainPanel.add(new ViewMonthlyTasksPanel(), "View Monthly Tasks");
mainPanel.add(new ReportsPanel(), "Reports");
mainPanel.add(new AlertsPanel(), "Alerts");

        mainPanel.add(createContentPanel("Logout (Coming Soon)"), "Logout");

        // Add all the buttons to the sidebar
        String[] labels = {
                "Dashboard Overview",
                "View Daily Tasks",
                "View Weekly Tasks",
                "View Monthly Tasks",
                "Reports",
                "Alerts",
                "Logout"
        };
        for (String label : labels) {
            JButton btn = new JButton(label);
            btn.setFocusPainted(false);
            btn.setFont(new Font("Arial", Font.BOLD, 14));
            btn.setForeground(Color.BLACK);
            btn.setBackground(new Color(0, 128, 128));
            btn.setMargin(new Insets(10, 10, 10, 10));
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(mainPanel, label); // switch card
                }
            });
            sidebar.add(btn);
        }

        setLayout(new BorderLayout());
        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createContentPanel(String message) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        JLabel label = new JLabel(message);
        label.setFont(new Font("SansSerif", Font.PLAIN, 18));
        panel.add(label);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PersonnelDashboard().setVisible(true));
    }
}
