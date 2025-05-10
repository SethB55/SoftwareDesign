import javax.swing.*;
import java.awt.*;

public class ResultsPanel extends JPanel {
    private final ElectionModel model;
    private final JLabel demLabel;
    private final JLabel repLabel;
    private final JLabel winnerLabel;

    public ResultsPanel(ElectionModel model) {
        this.model = model;
        setLayout(new GridLayout(3, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        demLabel = new JLabel("Democrat: 0 votes", SwingConstants.CENTER);
        repLabel = new JLabel("Republican: 0 votes", SwingConstants.CENTER);
        winnerLabel = new JLabel("No winner yet", SwingConstants.CENTER);

        demLabel.setFont(new Font("Arial", Font.BOLD, 16));
        repLabel.setFont(new Font("Arial", Font.BOLD, 16));
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 20));

        add(demLabel);
        add(repLabel);
        add(winnerLabel);

        updateResults();
    }

    public void updateResults() {
        demLabel.setText("Democrat: " + model.getDemocratTotal() + " votes");
        repLabel.setText("Republican: " + model.getRepublicanTotal() + " votes");

        if (model.hasWinner()) {
            winnerLabel.setText(model.getWinner() + " WINS THE ELECTION!");
            winnerLabel.setForeground(model.getWinner().equals("DEMOCRAT") ? Color.BLUE : Color.RED);
        } else {
            winnerLabel.setText("No winner yet - Need 270 to win");
            winnerLabel.setForeground(Color.BLACK);
        }
    }
}