import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ResultsPanel extends JPanel {
    private final ElectionModel model;
    private final JLabel resultsLabel;

    public ResultsPanel(ElectionModel model) {
        this.model = model;
        setLayout(new BorderLayout());

        resultsLabel = new JLabel("No votes recorded yet", SwingConstants.CENTER);
        resultsLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton calculateButton = new JButton("Calculate Results");
        calculateButton.addActionListener(e -> updateResults());

        add(resultsLabel, BorderLayout.CENTER);
        add(calculateButton, BorderLayout.SOUTH);
    }

    public void updateResults() {
        Map<String, Integer> results = model.calculateResults();
        int dem = results.get("Democrat");
        int rep = results.get("Republican");

        String text = String.format("Democrat: %d | Republican: %d", dem, rep);

        if (model.hasWinner()) {
            text += " | WINNER: " + model.getWinner() + "!";
        }

        resultsLabel.setText(text);
    }
}