/**
 * Utility methods for word comparisons and magnitude calculations.
 * <p>
 * Provides functionality to check the edit distance between words
 * and compute the magnitude of a word based on the sum of its character values.
 */
public class WordUtils {

    /**
     * Checks whether two words differ by exactly one character (edit distance of one).
     * This method assumes both words must be of the same length to compare.
     *
     * @param w1 the first word to compare.
     * @param w2 the second word to compare.
     * @return true if the words differ by exactly one character, false otherwise.
     */
    public static boolean isEditDistanceOne(String w1, String w2) {
        if (w1.length() != w2.length()) return false; // Words of different lengths can't have edit distance of one

        int diff = 0; // Counter for differing characters
        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                diff++;
                if (diff > 1) return false; // More than one difference, not valid
            }
        }
        return diff == 1; // Valid only if exactly one difference
    }

    /**
     * Calculates the magnitude of a word.
     * The magnitude is defined as the sum of the numeric values of its characters.
     * Characters are converted using {@link Character#getNumericValue(char)}.
     *
     * @param word the word whose magnitude is to be calculated.
     * @return the magnitude of the word.
     */
    public static int magnitude(String word) {
        int sum = 0;
        for (char c : word.toCharArray()) {
            sum += Character.getNumericValue(c); // Sum the numeric value of each character
        }
        return sum;
    }
}
