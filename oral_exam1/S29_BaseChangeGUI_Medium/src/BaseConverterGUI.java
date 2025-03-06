import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BaseConverterGUI extends JFrame implements ActionListener {
    private JTextField numberField;
    private JTextField currentBaseField;
    private JTextField desiredBaseField;
    private JTextField resultField;
    private JButton convertButton;

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
        //add(new JLabel("Result:"));
        add(resultField);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String number = numberField.getText().trim().toUpperCase();
            int currentBase = convertBaseToInt(currentBaseField.getText().trim());  // Manual conversion here, used .trim to weed out the whitespace
            int desiredBase = convertBaseToInt(desiredBaseField.getText().trim());  // Manual conversion here

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

    private int convertBaseToInt(String baseStr) {
        // Convert the base string to an integer manually
        int base = 0;
        // Loop through each character of the base string
        for (int i = 0; i < baseStr.length(); i++) {
            char c = baseStr.charAt(i);
            if (c >= '0' && c <= '9') { // If the character is a digit (0-9)
                base = base * 10 + (c - '0');
            }
            else if (c >= 'A' && c <= 'F') { // If the character is a letter (A-F for base 16)
                base = base * 10 + (c - 'A' + 10);  // A = 10, B = 11, ..., F = 15
            }
            else {            // If the character is invalid (not a digit or letter A-F)
                return -1;  // Return -1 to indicate invalid input
            }
        }
        if (base == 2 || base == 8 || base == 10 || base == 16) { // Only return the base if it's one of the valid bases (2, 8, 10, 16)
            return base;
        }
        return -1;  // Return -1 if the base is not valid
    }

    private boolean isValidBase(int base) {
        return (base == 2 || base == 8 || base == 10 || base == 16);
    }
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
