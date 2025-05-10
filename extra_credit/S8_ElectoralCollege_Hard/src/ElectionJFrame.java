import javax.swing.*;
import java.awt.*;

/**
 * The ElectionJFrame class is the main window for the US election simulator application.
 */
public class ElectionJFrame extends JFrame {
    private final ElectionModel model;      // The election data model
    private final ResultsPanel resultsPanel; // The results display panel

    /**
     * Constructs the main application window.
     */
    public ElectionJFrame() {
        model = new ElectionModel();                               // Create new election model
        resultsPanel = new ResultsPanel(model);                    // Create results panel
        initializeUI();                                            // Set up the UI
    }

    /**
     * Initializes the user interface components.
     */
    private void initializeUI() {
        setTitle("US Election Simulator - Live Results");          // Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           // Close on exit
        setSize(1200, 800);                                       // Initial window size

        JTabbedPane tabbedPane = new JTabbedPane();               // Create tabbed interface
        tabbedPane.addTab("States", createStatesPanel());         // Add states tab
        tabbedPane.addTab("Results", resultsPanel);               // Add results tab

        add(tabbedPane);                                          // Add tabs to frame
        setLocationRelativeTo(null);                              // Center window
    }

    /**
     * Creates the panel containing all state/district voting controls.
     * @return A JComponent containing all state panels
     */
    private JComponent createStatesPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 7, 10, 10));  // 7 columns, dynamic rows, with spacing

        // Add all 50 states and Washington D.C.
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
        addState(panel, "Maine", 2); // State-wide votes
        addState(panel, "Maryland", 10);
        addState(panel, "Massachusetts", 11);
        addState(panel, "Michigan", 15);
        addState(panel, "Minnesota", 10);
        addState(panel, "Mississippi", 6);
        addState(panel, "Missouri", 10);
        addState(panel, "Montana", 4);
        addState(panel, "Nebraska", 2); // State-wide votes
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

        // Add congressional districts for Maine and Nebraska
        addState(panel, "Maine-1", 1); // Maine's 1st congressional district
        addState(panel, "Maine-2", 1); // Maine's 2nd congressional district
        addState(panel, "Nebraska-1", 1); // Nebraska's 1st congressional district
        addState(panel, "Nebraska-2", 1); // Nebraska's 2nd congressional district
        addState(panel, "Nebraska-3", 1); // Nebraska's 3rd congressional district

        return new JScrollPane(panel);                            // Make scrollable
    }

    /**
     * Helper method to add a state panel to the container.
     * @param panel The container panel
     * @param name The state/district name
     * @param votes The number of electoral votes
     */
    private void addState(JPanel panel, String name, int votes) {
        panel.add(new StatePanel(name, votes, model, resultsPanel)); // Create and add state panel
    }

    /**
     * The main entry point for the application.
     * @param args Command line arguments (unused)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                ElectionJFrame frame = new ElectionJFrame();      // Create main frame
                frame.setVisible(true);                           // Make visible
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,               // Show error dialog if startup fails
                        "Error starting application: " + e.getMessage(),
                        "Startup Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}