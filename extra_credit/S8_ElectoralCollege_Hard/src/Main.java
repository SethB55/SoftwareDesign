import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ElectionJFrame frame = new ElectionJFrame();
            frame.setVisible(true);
        });
    }
}