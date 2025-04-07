import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class part1Solver {

    public static void main(String[] args) {
        try {
            String filePath = "/iahome/s/se/sebolen/IdeaProjects/sebolen_swd/scratch/Algo HW4/src/input1.txt";
            Scanner scanner = new Scanner(new File(filePath));
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");

            int turnPenalty = Integer.parseInt(parts[0]);
            int numNodes = Integer.parseInt(parts[1]);

            List<Integer> weights = new ArrayList<>();
            for (int i = 2; i < parts.length; i++) {
                weights.add(Integer.parseInt(parts[i]));
            }

            int result = solve(turnPenalty, numNodes, weights);

            // Handle the result more cleanly
            if (result == Integer.MIN_VALUE) {
                System.out.println("No valid path found.");
            } else {
                System.out.println(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int solve(int turnPenalty, int numNodes, List<Integer> weights) {
        // Initialize the DP table with a small value
        int[][] dp = new int[numNodes + 1][2];
        for (int i = 0; i <= numNodes; i++) {
            dp[i][0] = Integer.MIN_VALUE;
            dp[i][1] = Integer.MIN_VALUE;
        }
        dp[1][0] = 0;
        dp[1][1] = 0;

        // Build the graph edges based on the diamond structure
        List<Edge> edges = buildGraphEdges(numNodes, weights);

        // Process each edge and update the DP table
        for (Edge edge : edges) {
            int from = edge.from;
            int to = edge.to;
            int weight = edge.weight;
            boolean isUpward = edge.isUpward;

            for (int dir = 0; dir < 2; dir++) {
                int currentWeight = dp[from][dir];

                if (currentWeight > Integer.MIN_VALUE) {
                    int penalty = (dir == (isUpward ? 1 : 0)) ? 0 : turnPenalty;
                    int newWeight = currentWeight + weight - penalty;

                    // Make sure newWeight is valid before updating
                    if (newWeight > Integer.MIN_VALUE) {
                        int toDir = isUpward ? 1 : 0;
                        dp[to][toDir] = Math.max(dp[to][toDir], newWeight);
                    }
                }
            }
        }

        // Optional: Debug print the DP table
        for (int i = 1; i <= numNodes; i++) {
            System.out.println("Node " + i + ": DOWN=" + dp[i][0] + ", UP=" + dp[i][1]);
        }

        // Return the best result if valid
        int maxResult = Math.max(dp[numNodes][0], dp[numNodes][1]);
        return (maxResult == Integer.MIN_VALUE) ? Integer.MIN_VALUE : maxResult;
    }

    private static List<Edge> buildGraphEdges(int numNodes, List<Integer> weights) {
        List<Edge> edges = new ArrayList<>();
        int k = (int) Math.sqrt(numNodes); // Assuming numNodes = k + (k - 1)
        int weightIndex = 0;

        // Upper part of the diamond
        for (int row = 1, id = 1; row < k; row++) {
            for (int i = 0; i < row; i++, id++) {
                int rightUp = id + row;
                int rightDown = id + row + 1;
                edges.add(new Edge(id, rightUp, weights.get(weightIndex++), true));
                edges.add(new Edge(id, rightDown, weights.get(weightIndex++), false));
            }
        }

        // Lower part of the diamond
        for (int row = k, id = k * (k - 1) / 2 + 1; row > 1; row--) {
            for (int i = 0; i < row - 1; i++, id++) {
                int rightUp = id + row - 1;
                int rightDown = id + row;
                edges.add(new Edge(id, rightUp, weights.get(weightIndex++), true));
                edges.add(new Edge(id, rightDown, weights.get(weightIndex++), false));
            }
        }

        return edges;
    }

    static class Edge {
        int from;
        int to;
        int weight;
        boolean isUpward;

        Edge(int from, int to, int weight, boolean isUpward) {
            this.from = from;
            this.to = to;
            this.weight = weight;
            this.isUpward = isUpward;
        }
    }
}
