import javax.swing.*;
import java.awt.*;

public class ElectionJFrame extends JFrame {
    private ElectionModel model;

    // Constructor - no return type needed
    public ElectionJFrame() {
        model = new ElectionModel();
        initializeUI();
    }

    // Method to set up the UI - void return type specified
    private void initializeUI() {
        setTitle("US Election Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("States", createStatesPanel());
        tabbedPane.addTab("Results", new ResultsPanel(model));

        add(tabbedPane);
        setLocationRelativeTo(null); // Center the window
    }

    // Method to create states panel - returns JComponent
    private JComponent createStatesPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        // Add some sample states
        panel.add(new StatePanel("Alabama", 9, model));
        panel.add(new StatePanel("Alaska", 3, model));


        panel.add(new StatePanel("California", 54, model));
        panel.add(new StatePanel("Texas", 40, model));
        panel.add(new StatePanel("Florida", 30, model));

        return new JScrollPane(panel);
    }

    // Main method - static with void return type
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ElectionJFrame frame = new ElectionJFrame();
            frame.setVisible(true);
        });
    }
}