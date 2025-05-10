import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The StatePanel class represents a GUI panel for a single state/district's voting controls.
 */
public class StatePanel extends JPanel {
    private final ElectionModel model;      // Reference to the election model
    private final String stateName;        // Name of the state/district
    private final JLabel voteLabel;        // Label showing current selection

    /**
     * Constructs a new StatePanel for a state/district.
     * @param stateName The name of the state/district
     * @param votes The number of electoral votes
     * @param model The election model to update
     * @param resultsPanel The results panel to refresh
     * @throws IllegalArgumentException if parameters are null or invalid
     */
    public StatePanel(String stateName, int votes, ElectionModel model, ResultsPanel resultsPanel) {
        if (model == null || resultsPanel == null) {
            throw new IllegalArgumentException("Model and resultsPanel cannot be null");
        }
        if (stateName == null || stateName.isEmpty()) {
            throw new IllegalArgumentException("State name cannot be null or empty");
        }

        this.model = model;
        this.stateName = stateName;

        setLayout(new BorderLayout());                              // Use border layout
        setBorder(BorderFactory.createTitledBorder(stateName + " (" + votes + " votes)")); // Title with votes

        voteLabel = new JLabel("Current: " + model.getStateSelection(stateName)); // Current selection label
        add(voteLabel, BorderLayout.NORTH);                        // Add label to top

        // Create radio button group for party selection
        ButtonGroup group = new ButtonGroup();
        JRadioButton demButton = new JRadioButton("Democrat");      // Democrat option
        JRadioButton repButton = new JRadioButton("Republican");    // Republican option
        JRadioButton undecidedButton = new JRadioButton("Undecided"); // Undecided option

        // Set initial selection based on current state
        String currentSelection = model.getStateSelection(stateName);
        demButton.setSelected("Democrat".equals(currentSelection)); // Select Democrat if chosen
        repButton.setSelected("Republican".equals(currentSelection)); // Select Republican if chosen
        undecidedButton.setSelected(!demButton.isSelected() && !repButton.isSelected()); // Select undecided otherwise

        // Action listener for radio button changes
        ActionListener listener = e -> {
            String party = undecidedButton.isSelected() ? "Undecided" :
                    demButton.isSelected() ? "Democrat" : "Republican";

            model.setStateVote(stateName, party);                   // Update model
            voteLabel.setText("Current: " + party);                 // Update label
            resultsPanel.updateResults();                           // Refresh results
        };

        // Add listeners to all buttons
        demButton.addActionListener(listener);
        repButton.addActionListener(listener);
        undecidedButton.addActionListener(listener);

        // Add buttons to group (mutual exclusivity)
        group.add(demButton);
        group.add(repButton);
        group.add(undecidedButton);

        // Create panel for radio buttons
        JPanel radioPanel = new JPanel(new GridLayout(3, 1));       // 3 rows, 1 column
        radioPanel.add(demButton);                                  // Add Democrat button
        radioPanel.add(repButton);                                  // Add Republican button
        radioPanel.add(undecidedButton);                            // Add Undecided button

        add(radioPanel, BorderLayout.CENTER);                       // Add buttons to center
    }
}