import javax.swing.*;
import java.awt.*;

/**
 * The ResultsPanel class displays the current election results and winner status.
 */
public class ResultsPanel extends JPanel {
    private final ElectionModel model;      // Reference to the election model
    private final JLabel demLabel;         // Label for Democrat votes
    private final JLabel repLabel;         // Label for Republican votes
    private final JLabel winnerLabel;      // Label for winner announcement

    /**
     * Constructs a new ResultsPanel.
     * @param model The election model to display results from
     * @throws IllegalArgumentException if model is null
     */
    public ResultsPanel(ElectionModel model) {
        if (model == null) {
            throw new IllegalArgumentException("Model cannot be null");
        }

        this.model = model;
        setLayout(new GridLayout(3, 1, 10, 10));                   // 3 rows, 1 column, with spacing
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Create and configure labels
        demLabel = new JLabel("Democrat: 0 votes", SwingConstants.CENTER); // Centered Democrat label
        repLabel = new JLabel("Republican: 0 votes", SwingConstants.CENTER); // Centered Republican label
        winnerLabel = new JLabel("No winner yet", SwingConstants.CENTER); // Centered winner label

        // Set fonts for better visibility
        demLabel.setFont(new Font("Arial", Font.BOLD, 16));        // Bold 16pt font
        repLabel.setFont(new Font("Arial", Font.BOLD, 16));        // Bold 16pt font
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 20));     // Larger bold 20pt font

        // Add labels to panel
        add(demLabel);                                             // Add Democrat label
        add(repLabel);                                             // Add Republican label
        add(winnerLabel);                                          // Add winner label

        updateResults();                                           // Initialize with current results
    }

    /**
     * Updates the displayed results based on current model state.
     */
    public void updateResults() {
        demLabel.setText("Democrat: " + model.getDemocratTotal() + " votes"); // Update Democrat count
        repLabel.setText("Republican: " + model.getRepublicanTotal() + " votes"); // Update Republican count

        if (model.hasWinner()) {
            String winner = model.getWinner();                      // Get winning party
            winnerLabel.setText(winner + " WINS THE ELECTION!");    // Show winner
            winnerLabel.setForeground("DEMOCRAT".equals(winner) ? Color.BLUE : Color.RED); // Blue for Democrat, red for Republican
        } else {
            winnerLabel.setText("No winner yet - Need 270 to win"); // Show no winner yet
            winnerLabel.setForeground(Color.BLACK);                 // Default black text
        }
    }
}