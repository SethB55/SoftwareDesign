import java.io.*;

public class part2Solver{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/iahome/s/se/sebolen/IdeaProjects/sebolen_swd/scratch/Algo HW4/src/input2.txt"));

        // Read and parse prices
        String[] priceStrings = br.readLine().replaceAll("\\[|\\]", "").split(",");
        int N = priceStrings.length;
        int[] prices = new int[N];
        for (int i = 0; i < N; i++) {
            prices[i] = Integer.parseInt(priceStrings[i].trim());
        }

        // Read gold capacity G
        int G = Integer.parseInt(br.readLine().trim());
        br.close();

        int[][] dp = new int[N + 1][G + 1];
        int[][] cuts = new int[N + 1][G + 1];
        boolean[][] coated = new boolean[N + 1][G + 1];

        // DP computation
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

        // Output results
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