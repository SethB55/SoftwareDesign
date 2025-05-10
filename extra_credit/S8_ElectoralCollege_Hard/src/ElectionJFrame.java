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
        JPanel panel = new JPanel(new GridLayout(0, 4, 10, 10));  // 4 columns, dynamic rows, with spacing

        // Add all states (would normally loop through a collection)
        addState(panel, "Alabama", 9);                            // Alabama panel
        addState(panel, "Alaska", 3);                             // Alaska panel
        // ... [other state additions remain unchanged]

        // Add districts (Maine and Nebraska)
        addState(panel, "Maine-1", 1);                            // Maine CD-1
        addState(panel, "Maine-2", 1);                            // Maine CD-2
        // ... [other district additions remain unchanged]

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