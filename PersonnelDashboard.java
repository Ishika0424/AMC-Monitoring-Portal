import javax.swing.*;

public class PersonnelDashboard extends JFrame {
    public PersonnelDashboard() {
        setTitle("Personnel Dashboard");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel label = new JLabel("Welcome, AMC Personnel!", JLabel.CENTER);
        label.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 24));
        add(label);
    }
}
