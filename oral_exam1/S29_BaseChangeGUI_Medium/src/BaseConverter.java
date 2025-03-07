public class BaseConverter {

    /**
     * Converts a number from one base to another.
     * @param number The number to convert, represented as a string.
     * @param currentBase The base of the given number.
     * @param desiredBase The base to convert to.
     * @return The converted number as a string.
     * @throws IllegalArgumentException if the input number is invalid for the given base.
     */
    public static String convert(String number, int currentBase, int desiredBase) {
        if (!isValidInput(number, currentBase)) {
            throw new IllegalArgumentException("Invalid number for base " + currentBase);
        }
        int decimalValue = toDecimal(number, currentBase);
        return fromDecimal(decimalValue, desiredBase);
    }

    /**
     * Converts a number from a given base to decimal.
     * @param number The number in the given base.
     * @param base The base of the given number.
     * @return The decimal equivalent of the number.
     */
    static int toDecimal(String number, int base) {
        int decimal = 0;
        int power = 0;

        // Convert the input number (string) to decimal value
        for (int i = number.length() - 1; i >= 0; i--) {
            char digit = number.charAt(i);
            int value = charToValue(digit);
            decimal += value * Math.pow(base, power);
            power++;
        }
        return decimal;
    }

    /**
     * Converts a decimal number to the specified base.
     * @param decimal The decimal number to convert.
     * @param base The base to convert to.
     * @return The number in the desired base as a string.
     */
    public static String fromDecimal(int decimal, int base) {
        if (decimal == 0) { // Base case
            return "0";
        }
        String result = ""; // Start with an empty string to build the result
        // Convert decimal to the desired base
        while (decimal > 0) {
            int remainder = decimal % base;
            result = valueToChar(remainder) + result; // Prepend the character (build the string in reverse order)
            decimal /= base;
        }
        return result; // Return the final result
    }

    /**
     * Validates whether the given number is valid for the specified base.
     * @param number The number to validate.
     * @param base The base to check against.
     * @return True if the number is valid for the base, false otherwise.
     */
    private static boolean isValidInput(String number, int base) {
        for (int i = 0; i < number.length(); i++) {
            char c = Character.toUpperCase(number.charAt(i)); // Convert to uppercase for easier comparison

            if (base == 2 && (c != '0' && c != '1')) {
                return false; // Only 0 and 1 are valid for base 2
            } else if (base == 8 && (c < '0' || c > '7')) {
                return false; // Only 0-7 are valid for base 8
            } else if (base == 10 && (c < '0' || c > '9')) {
                return false; // Only 0-9 are valid for base 10
            } else if (base == 16 && (c < '0' || c > '9') && (c < 'A' || c > 'F')) {
                return false; // 0-9 and A-F are valid for base 16
            } else if (base != 2 && base != 8 && base != 10 && base != 16) {
                return false; // Invalid base
            }
        }
        return true; // If no invalid chars are found, return true
    }

    /**
     * Converts a character to its numerical value.
     * @param c The character to convert.
     * @return The numerical value of the character.
     */
    private static int charToValue(char c) {
        if (Character.isDigit(c)) {
            return (c - '0');
        }
        return (10 + (Character.toUpperCase(c) - 'A'));
    }

    /**
     * Converts a numerical value to its corresponding character.
     * @param value The numerical value to convert.
     * @return The character representation of the value.
     */
    private static char valueToChar(int value) {
        if (value < 10) {
            return ((char) ('0' + value));
        }
        return ((char) ('A' + (value - 10)));
    }
}
