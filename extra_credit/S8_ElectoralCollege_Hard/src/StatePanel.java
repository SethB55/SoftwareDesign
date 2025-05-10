import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StatePanel extends JPanel {
    private final ElectionModel model;
    private final String stateName;
    private final JLabel voteLabel;

    public StatePanel(String stateName, int votes, ElectionModel model, ResultsPanel resultsPanel) {
        this.model = model;
        this.stateName = stateName;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(stateName + " (" + votes + " votes)"));

        // Vote count label - initialize with current selection
        voteLabel = new JLabel("Current: Undecided");
        add(voteLabel, BorderLayout.NORTH);

        // Radio buttons
        ButtonGroup group = new ButtonGroup();
        JRadioButton demButton = new JRadioButton("Democrat");
        JRadioButton repButton = new JRadioButton("Republican");
        JRadioButton undecidedButton = new JRadioButton("Undecided", true);

        ActionListener listener = e -> {
            String party = "Undecided";
            if (demButton.isSelected()) party = "Democrat";
            if (repButton.isSelected()) party = "Republican";

            model.setStateVote(stateName, party);
            voteLabel.setText("Current: " + party);
            resultsPanel.updateResults();
        };

        demButton.addActionListener(listener);
        repButton.addActionListener(listener);
        undecidedButton.addActionListener(listener);

        group.add(demButton);
        group.add(repButton);
        group.add(undecidedButton);

        JPanel radioPanel = new JPanel(new GridLayout(3, 1));
        radioPanel.add(demButton);
        radioPanel.add(repButton);
        radioPanel.add(undecidedButton);

        add(radioPanel, BorderLayout.CENTER);
    }
}