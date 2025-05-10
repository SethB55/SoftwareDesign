
import javax.swing.*;
        import java.awt.*;

public class StatePanel extends JPanel {
    public StatePanel(String stateName, int electoralVotes) {
        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new BorderLayout());

        // State name and votes label
        add(new JLabel(stateName + " (" + electoralVotes + " votes)"), BorderLayout.NORTH);

        // Radio buttons
        JPanel radioPanel = new JPanel(new GridLayout(3, 1));
        ButtonGroup group = new ButtonGroup();
        JRadioButton demButton = new JRadioButton("Democrat");
        JRadioButton repButton = new JRadioButton("Republican");
        JRadioButton undecidedButton = new JRadioButton("Undecided", true);
        group.add(demButton);
        group.add(repButton);
        group.add(undecidedButton);
        radioPanel.add(demButton);
        radioPanel.add(repButton);
        radioPanel.add(undecidedButton);

        add(radioPanel, BorderLayout.CENTER);
    }
}