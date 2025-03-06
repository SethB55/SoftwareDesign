public class BaseConverter {

    public static String convert(String number, int currentBase, int desiredBase) {
        if (!isValidInput(number, currentBase)) {
            throw new IllegalArgumentException("Invalid number for base " + currentBase);
        }

        int decimalValue = toDecimal(number, currentBase);
        return fromDecimal(decimalValue, desiredBase);
    }

    private static int toDecimal(String number, int base) {
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

    private static String fromDecimal(int decimal, int base) {
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


    private static boolean isValidInput(String number, int base) {
        // Validate that the number only contains valid digits for the given base
        // Loop through each character in the number string
        for (int i = 0; i < number.length(); i++) {
            char c = Character.toUpperCase(number.charAt(i));  // Convert to uppercase to easy compare
            // Check if the character is valid for the given base
            if (base == 2) {
                if (c != '0' && c != '1') {
                    return false;  // Only 0 and 1 are valid for base 2
                }
            } else if (base == 8) {
                if (c < '0' || c > '7') {
                    return false;  // Only 0-7 are valid for base 8
                }
            } else if (base == 10) {
                if (c < '0' || c > '9') {
                    return false;  // Only 0-9 are valid for base 10
                }
            } else if (base == 16) {
                if ((c < '0' || c > '9') && (c < 'A' || c > 'F')) {
                    return false;  // 0-9 and A-F are valid for base 16
                }
            } else {
                return false;  // Invalid base
            }
        }
        return true;  // If no invalid chars are found, return true
    }

    private static int charToValue(char c) {
        if (Character.isDigit(c)) {
            return (c - '0');
        }
        return (10 + (Character.toUpperCase(c) - 'A'));
    }

    private static char valueToChar(int value) { //just overloading to char
        if (value < 10) {
            return ((char) ('0' + value));
        }
        return ((char) ('A' + (value - 10)));
    }
}
