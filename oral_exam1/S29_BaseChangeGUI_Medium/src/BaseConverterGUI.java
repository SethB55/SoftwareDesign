import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A graphical user interface for converting numbers between different bases.
 * Supports bases 2, 8, 10, and 16.
 */
public class BaseConverterGUI extends JFrame implements ActionListener {
    private JTextField numberField;
    private JTextField currentBaseField;
    private JTextField desiredBaseField;
    private JTextField resultField;
    private JButton convertButton;

    /**
     * Constructs the BaseConverterGUI and initializes the user interface.
     */
    public BaseConverterGUI() {
        setTitle("Base Converter");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Number:"));
        numberField = new JTextField();
        add(numberField);

        add(new JLabel("Current Base (2,8,10,16):"));
        currentBaseField = new JTextField();
        add(currentBaseField);

        add(new JLabel("Desired Base (2,8,10,16):"));
        desiredBaseField = new JTextField();
        add(desiredBaseField);

        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);
        add(convertButton);

        resultField = new JTextField();
        resultField.setEditable(false);
        add(resultField);

        setVisible(true);
    }

    /**
     * Handles button click events to perform base conversion.
     * @param e The ActionEvent triggered by user interaction.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String number = numberField.getText().trim().toUpperCase();
            int currentBase = convertBaseToInt(currentBaseField.getText().trim());
            int desiredBase = convertBaseToInt(desiredBaseField.getText().trim());

            if (currentBase == -1 || desiredBase == -1) {
                showError("Base must be 2, 8, 10, or 16.");
                return;
            }

            // Call the conversion logic
            String result = BaseConverter.convert(number, currentBase, desiredBase);
            resultField.setText(result);
        } catch (NumberFormatException ex) {
            showError("Please enter valid numbers for bases.");
        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage());
        }
    }

    /**
     * Converts a string representation of a base into an integer.
     * @param baseStr The string representing the base.
     * @return The integer value of the base if valid, otherwise -1.
     */
    private int convertBaseToInt(String baseStr) {
        int base = 0;
        for (int i = 0; i < baseStr.length(); i++) {
            char c = baseStr.charAt(i);
            if (c >= '0' && c <= '9') {
                base = base * 10 + (c - '0');
            } else if (c >= 'A' && c <= 'F') {
                base = base * 10 + (c - 'A' + 10);
            } else {
                return -1; // Invalid character
            }
        }
        return isValidBase(base) ? base : -1;
    }

    /**
     * Checks if a given base is valid (2, 8, 10, or 16).
     * @param base The base to check.
     * @return true if the base is valid, false otherwise.
     */
    private boolean isValidBase(int base) {
        return (base == 2 || base == 8 || base == 10 || base == 16);
    }

    /**
     * Displays an error message to the user in a dialog box.
     * @param message The error message to display.
     */
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
