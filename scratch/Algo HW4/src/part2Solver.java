import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class part2Solver {

    public static void main(String[] args) {
        try {
            // Read input from file
            BufferedReader reader = new BufferedReader(new FileReader("/iahome/s/se/sebolen/IdeaProjects/sebolen_swd/scratch/Algo HW4/src/input2.txt"));


            // First line: comma-separated prices for each segment length
            String[] priceStrings = reader.readLine().trim().split(",");
            int[] prices = new int[priceStrings.length];
            for (int i = 0; i < priceStrings.length; i++) {
                prices[i] = Integer.parseInt(priceStrings[i].trim());
            }

            // Second line: total amount of gold available
            int G = Integer.parseInt(reader.readLine().trim());

            reader.close();

            // Compute and display the result
            int result = computeMaxRevenue(prices, G);
            System.out.println("Maximum Revenue: " + result);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format: " + e.getMessage());
        }
    }

    /**
     * Computes the maximum revenue obtainable by cutting a rod and optionally coating pieces with gold.
     *
     * @param prices Array where prices[i] is the price of a rod segment of length i + 1 (1-based).
     * @param G      Total units of gold available for coating.
     * @return The maximum revenue possible.
     */
    public static int computeMaxRevenue(int[] prices, int G) {
        int N = prices.length;

        // dp[len][g] stores the maximum revenue for rod length 'len' with 'g' units of gold left
        int[][] dp = new int[N + 1][G + 1];

        // Build the DP table in a bottom-up manner
        for (int len = 1; len <= N; len++) {
            for (int g = 0; g <= G; g++) {
                int maxVal = 0;

                // Try all possible cuts
                for (int cut = 1; cut <= len; cut++) {
                    int price = prices[cut - 1];

                    // Option 1: Don't coat this segment
                    int option1 = price + dp[len - cut][g];
                    maxVal = Math.max(maxVal, option1);

                    // Option 2: Coat this segment if we have enough gold
                    if (g >= cut) {
                        int option2 = 2 * price + dp[len - cut][g - cut];
                        maxVal = Math.max(maxVal, option2);
                    }
                }

                // Store the best result for current len and g
                dp[len][g] = maxVal;
            }
        }

        // Return the maximum revenue for the full rod length with all available gold
        return dp[N][G];
    }
}
