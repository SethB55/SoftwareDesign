import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StatePanel extends JPanel {
    private final ElectionModel model;
    private final String stateName;
    private final JLabel voteLabel;

    public StatePanel(String stateName, int votes, ElectionModel model, ResultsPanel resultsPanel) {
        if (model == null || resultsPanel == null) {
            throw new IllegalArgumentException("Model and resultsPanel cannot be null");
        }
        if (stateName == null || stateName.isEmpty()) {
            throw new IllegalArgumentException("State name cannot be null or empty");
        }

        this.model = model;
        this.stateName = stateName;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(stateName + " (" + votes + " votes)"));

        voteLabel = new JLabel("Current: " + model.getStateSelection(stateName));
        add(voteLabel, BorderLayout.NORTH);

        ButtonGroup group = new ButtonGroup();
        JRadioButton demButton = new JRadioButton("Democrat");
        JRadioButton repButton = new JRadioButton("Republican");
        JRadioButton undecidedButton = new JRadioButton("Undecided");

        String currentSelection = model.getStateSelection(stateName);
        demButton.setSelected("Democrat".equals(currentSelection));
        repButton.setSelected("Republican".equals(currentSelection));
        undecidedButton.setSelected(!demButton.isSelected() && !repButton.isSelected());

        ActionListener listener = e -> {
            String party = undecidedButton.isSelected() ? "Undecided" :
                    demButton.isSelected() ? "Democrat" : "Republican";

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