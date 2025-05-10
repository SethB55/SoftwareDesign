import javax.swing.*;

/**
 * The Main class serves as the entry point for the U.S. Presidential Election
 * simulation program. It creates and displays the main application window (ElectionJFrame)
 * on the Event Dispatch Thread to ensure thread-safe GUI operations.
 */
public class Main {
    /**
     * The main method launches the election simulation GUI.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ElectionJFrame frame = new ElectionJFrame();
            frame.setVisible(true);
        });
    }
}
