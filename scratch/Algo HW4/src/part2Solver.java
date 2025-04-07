import java.io.*;

public class part2Solver {
    public static void main(String[] args) throws IOException {
        // Read input file
        BufferedReader br = new BufferedReader(new FileReader("/iahome/s/se/sebolen/IdeaProjects/sebolen_swd/scratch/Algo HW4/src/input2.txt"));

        // Parse prices array
        String[] priceStrings = br.readLine().replaceAll("\\[|\\]", "").split(",");
        int N = priceStrings.length; // Rod length
        int[] prices = new int[N];
        for (int i = 0; i < N; i++) {
            prices[i] = Integer.parseInt(priceStrings[i].trim());
        }

        // Read gold capacity G
        int G = Integer.parseInt(br.readLine().trim());
        br.close();

        // dp[i][g] = max revenue for rod of length i with g units of gold
        int[][] dp = new int[N + 1][G + 1];
        int[][] cuts = new int[N + 1][G + 1];
        boolean[][] coated = new boolean[N + 1][G + 1];

        /*
         * Recurrence relation:
         * For each rod length i (1 to N), and available gold g (0 to G), try all cut lengths j (1 to i):
         * - If we don't coat it in gold:
         *     dp[i][g] = max(dp[i][g], prices[j-1] + dp[i-j][g])
         * - If we do coat it in gold (only if g >= j):
         *     dp[i][g] = max(dp[i][g], 2 * prices[j-1] + dp[i-j][g-j])
         * This ensures that we always make the optimal decision at each stage.
         */

        for (int i = 1; i <= N; i++) {
            for (int g = 0; g <= G; g++) {
                for (int j = 1; j <= i; j++) {
                    int revenue = prices[j - 1] + dp[i - j][g];
                    if (revenue > dp[i][g]) {
                        dp[i][g] = revenue;
                        cuts[i][g] = j;
                        coated[i][g] = false;
                    }
                    if (g >= j) {
                        int goldRevenue = 2 * prices[j - 1] + dp[i - j][g - j];
                        if (goldRevenue > dp[i][g]) {
                            dp[i][g] = goldRevenue;
                            cuts[i][g] = j;
                            coated[i][g] = true;
                        }
                    }
                }
            }
        }

        // Output max revenue and corresponding cuts
        System.out.println("Maximum Revenue: " + dp[N][G]);
        System.out.print("Cuts: ");
        printCuts(N, G, cuts, coated);
    }

    private static void printCuts(int N, int G, int[][] cuts, boolean[][] coated) {
        while (N > 0) {
            int cut = cuts[N][G];
            System.out.print(cut + (coated[N][G] ? "(gold) " : " "));
            if (coated[N][G]) G -= cut;
            N -= cut;
        }
        System.out.println();
    }
}

/*
 * I think this runtime analysis is correct:
 * - Let N = rod length, G = gold available.
 * - There are O(N * G) states in dp[][]
 * - For each state, we check all possible cut lengths j from 1 to i (at most N operations)
 * - Therefore, time complexity is O(N^2 * G)
 * - Space complexity is O(N * G) due to the 2D dp table and tracking arrays.
 */
