import javax.swing.*;
import java.awt.*;

public class ElectionJFrame extends JFrame {
    private final ElectionModel model;
    private final ResultsPanel resultsPanel;

    public ElectionJFrame() {
        model = new ElectionModel();
        resultsPanel = new ResultsPanel(model);
        initializeUI();
    }

    private void initializeUI() {
        setTitle("US Election Simulator - Live Results");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("States", createStatesPanel());
        tabbedPane.addTab("Results", resultsPanel);

        add(tabbedPane);
        setLocationRelativeTo(null);
    }

    private JComponent createStatesPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 7, 10, 10)); // 3 columns for better layout

        // Add all states
        addState(panel, "Alabama", 9);
        addState(panel, "Alaska", 3);
        addState(panel, "Arizona", 11);
        addState(panel, "Arkansas", 6);
        addState(panel, "California", 54);
        addState(panel, "Colorado", 10);
        addState(panel, "Connecticut", 7);
        addState(panel, "Delaware", 3);
        addState(panel, "District of Columbia", 3);
        addState(panel, "Florida", 30);
        addState(panel, "Georgia", 16);
        addState(panel, "Hawaii", 4);
        addState(panel, "Idaho", 4);
        addState(panel, "Illinois", 19);
        addState(panel, "Indiana", 11);
        addState(panel, "Iowa", 6);
        addState(panel, "Kansas", 6);
        addState(panel, "Kentucky", 8);
        addState(panel, "Louisiana", 8);
        addState(panel, "Maryland", 10);
        addState(panel, "Massachusetts", 11);
        addState(panel, "Michigan", 15);
        addState(panel, "Minnesota", 10);
        addState(panel, "Mississippi", 6);
        addState(panel, "Missouri", 10);
        addState(panel, "Montana", 4);
        addState(panel, "Nevada", 6);
        addState(panel, "New Hampshire", 4);
        addState(panel, "New Jersey", 14);
        addState(panel, "New Mexico", 5);
        addState(panel, "New York", 28);
        addState(panel, "North Carolina", 16);
        addState(panel, "North Dakota", 3);
        addState(panel, "Ohio", 17);
        addState(panel, "Oklahoma", 7);
        addState(panel, "Oregon", 8);
        addState(panel, "Pennsylvania", 19);
        addState(panel, "Rhode Island", 4);
        addState(panel, "South Carolina", 9);
        addState(panel, "South Dakota", 3);
        addState(panel, "Tennessee", 11);
        addState(panel, "Texas", 40);
        addState(panel, "Utah", 6);
        addState(panel, "Vermont", 3);
        addState(panel, "Virginia", 13);
        addState(panel, "Washington", 12);
        addState(panel, "West Virginia", 4);
        addState(panel, "Wisconsin", 10);
        addState(panel, "Wyoming", 3);

        // Add Maine and Nebraska districts
        addDistrict(panel, "Maine-1", 1);
        addDistrict(panel, "Maine-2", 1);
        addState(panel, "Maine", 2); // State-wide votes
        addDistrict(panel, "Nebraska-1", 1);
        addDistrict(panel, "Nebraska-2", 1);
        addDistrict(panel, "Nebraska-3", 1);
        addState(panel, "Nebraska", 2); // State-wide votes


        return new JScrollPane(panel);
    }

    private void addState(JPanel panel, String name, int votes) {
        panel.add(new StatePanel(name, votes, model, resultsPanel));
    }

    private void addDistrict(JPanel panel, String name, int votes) {
        // Could create a special DistrictPanel if needed, using StatePanel for now
        panel.add(new StatePanel(name, votes, model, resultsPanel));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ElectionJFrame frame = new ElectionJFrame();
            frame.setVisible(true);
        });
    }
}