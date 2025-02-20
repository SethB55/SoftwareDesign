import javax.swing.*;
import java.awt.*;
    public class AdderFrame extends JFrame {
        // Fig. 26.10: TextFieldTest.java
// Testing TextFieldFrame.
        // end class TextFieldTest
        private final JLabel label1;
        private final JLabel label2;
        private final JLabel label3;
        private final JTextField integer1;
        private final JTextField integer2;
        private final JTextField integer3;
        private final JTextField result;
        private final JButton calculate;

        public AdderFrame() {
            super("Add two integers");
            setLayout(new FlowLayout());

            label1 = new JLabel("Enter integer 1: ");
            add(label1);
            integer1 = new JTextField(15);
            add(integer1);
            label2 = new JLabel("Enter integer 2: ");
            add(label2);
            integer2 = new JTextField(15);
            add(integer2);
            label3 = new JLabel("Enter integer 2: ");
            add(label3);
            integer3 = new JTextField(15);
            add(integer3);
            result = new JTextField(15);
            result.setEditable(false);
            add(result);
            calculate = new JButton("Calculate");
            add(calculate);
        }
        public static void main(String[] args) {
            AdderFrame adderFrame = new AdderFrame();
            adderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            adderFrame.setSize(350, 100);
            adderFrame.setVisible(true);
        }
    }

