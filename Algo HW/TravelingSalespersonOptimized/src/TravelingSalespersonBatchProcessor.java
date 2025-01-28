import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TravelingSalespersonBatchProcessor {

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

    public static void solveTSP(String inputFile, Result result) throws IOException {
        double[][] graph = readGraph(inputFile);
        int n = graph.length;
        int[] nodes = new int[n - 1]; // Nodes excluding the starting node (0)

        for (int i = 1; i < n; i++) {
            nodes[i - 1] = i;
        }

        int[] routeBuffer = new int[n + 1]; // Buffer for routes: [0, ..., ..., 0]

        long startTime = System.currentTimeMillis();

        // Generate permutations and calculate distances
        permute(nodes, 0, graph, result, routeBuffer);

        long endTime = System.currentTimeMillis();
        result.executionTime = endTime - startTime;
    }

    static class Result {
        double bestDistance;
        int[] bestRoute;
        long executionTime;

        Result() {
            this.bestDistance = Double.MAX_VALUE;
        }
    }

    public static void processBatch(String outputFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            bw.write("Input File, Best Distance, Best Route, Time Taken (ms)\n");

            // Process only input4.txt through input8.txt
            for (int i = 4; i <= 13; i++) {
                String inputFile = "input" + i + ".txt";
                System.out.println("Processing " + inputFile + "...");

                Result result = new Result();

                solveTSP(inputFile, result);

                // Write results to the consolidated output file
                bw.write(String.format("input%d.txt, %.2f, ", i, result.bestDistance));
                for (int node : result.bestRoute) {
                    bw.write(node + " ");
                }
                bw.write(String.format(", %d%n", result.executionTime));

                // Print results to console for quick verification
                System.out.printf("input%d.txt - Best Distance: %.2f, Time Taken: %dms%n", i, result.bestDistance, result.executionTime);
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Change this path if you want a different output location
        String outputFile = "batch_output_limited.txt";
        processBatch(outputFile);
    }
}
