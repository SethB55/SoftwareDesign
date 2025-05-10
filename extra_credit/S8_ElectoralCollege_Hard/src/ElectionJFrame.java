
import javax.swing.*;
        import java.awt.*;

public class ElectionJFrame extends JFrame {
    public ElectionJFrame() {
        setTitle("Basic Election Simulator");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create tabs
        JTabbedPane tabbedPane = new JTabbedPane();

        // States tab (just 3 sample states for now)
        JPanel statesPanel = new JPanel(new GridLayout(0, 2));
        statesPanel.add(new StatePanel("California", 54));
        statesPanel.add(new StatePanel("Texas", 40));
        statesPanel.add(new StatePanel("Florida", 30));

        tabbedPane.addTab("States", new JScrollPane(statesPanel));

        // Results tab (just placeholder)
        tabbedPane.addTab("Results", new JLabel("Results will appear here"));

        add(tabbedPane);
    }
}