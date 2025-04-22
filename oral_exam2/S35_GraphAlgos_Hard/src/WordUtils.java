//general utilities
/**
 * Utility methods for word comparisons and magnitude calculations.
 */
public class WordUtils {
    public static boolean isEditDistanceOne(String w1, String w2) {
        if (w1.length() != w2.length()) return false;
        int diff = 0;
        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                diff++;
                if (diff > 1) return false;
            }
        }
        return diff == 1;
    }

    public static int magnitude(String word) {
        int sum = 0;
        for (char c : word.toCharArray()) {
            sum += Character.getNumericValue(c);
        }
        return sum;
    }
}
