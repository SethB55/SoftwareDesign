import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StatePanel extends JPanel {
    private final ElectionModel model;
    private final String stateName;

    public StatePanel(String stateName, int votes, ElectionModel model) {
        this.model = model;
        this.stateName = stateName;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(stateName + " (" + votes + ")"));

        ButtonGroup group = new ButtonGroup();
        JRadioButton demButton = new JRadioButton("Democrat");
        JRadioButton repButton = new JRadioButton("Republican");
        JRadioButton undecidedButton = new JRadioButton("Undecided", true);

        // Add action listeners
        ActionListener listener = e -> {
            if (demButton.isSelected()) {
                model.setStateVote(stateName, "Democrat");
            } else if (repButton.isSelected()) {
                model.setStateVote(stateName, "Republican");
            } else {
                model.setStateVote(stateName, "Undecided");
            }
        };

        demButton.addActionListener(listener);
        repButton.addActionListener(listener);
        undecidedButton.addActionListener(listener);

        // Add to panel
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