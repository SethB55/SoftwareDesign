import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TravelingSalespersonOptimized {

    public static double[][] readGraph(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        List<double[]> graphList = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
            String[] values = line.split("\\s+");
            double[] row = new double[values.length];
            for (int i = 0; i < values.length; i++) {
                row[i] = values[i].isEmpty() ? 0.0 : Double.parseDouble(values[i]);
            }
            graphList.add(row);
        }
        br.close();

        // Convert to a square matrix, filling missing values with 0
        int size = graphList.size();
        double[][] graph = new double[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(graphList.get(i), 0, graph[i], 0, graphList.get(i).length);
        }
        return graph;
    }

    public static double calculateRouteDistance(int[] route, double[][] graph) {
        double distance = 0.0;
        for (int i = 0; i < route.length - 1; i++) {
            distance += graph[route[i]][route[i + 1]];
        }
        return distance;
    }

    public static void permute(int[] nodes, int start, double[][] graph, Result result, int[] routeBuffer) {
        if (start == nodes.length) {
            // Build the route: [0, nodes..., 0]
            System.arraycopy(nodes, 0, routeBuffer, 1, nodes.length);
            routeBuffer[0] = 0; // Start at node 0
            routeBuffer[routeBuffer.length - 1] = 0; // End at node 0

            // Calculate the distance for this route
            double distance = calculateRouteDistance(routeBuffer, graph);
            if (distance < result.bestDistance) {
                result.bestDistance = distance;
                result.bestRoute = routeBuffer.clone(); // Copy the route
            }
            return;
        }

        for (int i = start; i < nodes.length; i++) {
            swap(nodes, i, start);
            permute(nodes, start + 1, graph, result, routeBuffer);
            swap(nodes, i, start); // Backtrack
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void solveTSP(String inputFile, String outputFile) throws IOException {
        double[][] graph = readGraph(inputFile);
        int n = graph.length;
        int[] nodes = new int[n - 1]; // Nodes excluding the starting node (0)

        for (int i = 1; i < n; i++) {
            nodes[i - 1] = i;
        }

        // Result object to store the best result
        Result result = new Result(Double.MAX_VALUE, null);
        int[] routeBuffer = new int[n + 1]; // Buffer for routes: [0, ..., ..., 0]

        long startTime = System.currentTimeMillis();

        // Generate permutations and calculate distances
        permute(nodes, 0, graph, result, routeBuffer);

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        // Write results to output file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            bw.write(String.format("Best Distance: %.2f%n", result.bestDistance));
            bw.write("Best Route: ");
            for (int node : result.bestRoute) {
                bw.write(node + " ");
            }
            bw.newLine();
            bw.write(String.format("Time Taken: %dms%n", executionTime));
        }

        // Print results to console for quick verification
        System.out.printf("Best Distance: %.2f%n", result.bestDistance);
        System.out.print("Best Route: ");
        for (int node : result.bestRoute) {
            System.out.print(node + " ");
        }
        System.out.println();
        System.out.printf("Time Taken: %dms%n", executionTime);
    }

    static class Result {
        double bestDistance;
        int[] bestRoute;

        Result(double bestDistance, int[] bestRoute) {
            this.bestDistance = bestDistance;
            this.bestRoute = bestRoute;
        }
    }

    public static void main(String[] args) {
        try {
            // Use absolute or relative paths for your input and output files
            String inputFile = "input4.txt"; // Replace with your input file path
            String outputFile = "output.txt"; // Replace with your desired output file path
            solveTSP(inputFile, outputFile);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
