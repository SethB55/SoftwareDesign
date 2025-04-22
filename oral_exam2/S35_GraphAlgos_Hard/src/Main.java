
        import java.io.FileWriter;
        import java.io.IOException;
        import java.util.*;

/**
 * Driver class that runs the complete program on words.dat and writes output to file.
 */
public class Main {

    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("/iahome/s/se/sebolen/IdeaProjects/sebolen_swd/oral_exam2/S35_GraphAlgos_Hard/graph_algos_output.txt")) {
            WordGraph graph = new WordGraph();
            graph.buildGraph("/iahome/s/se/sebolen/IdeaProjects/sebolen_swd/oral_exam2/S35_GraphAlgos_Hard/words.dat");

            int isolated = graph.getIsolatedVertexCount();
            List<String> mostConnected = graph.getMostConnectedWords();
            double avg = graph.getAverageEdgesPerVertex();

            writer.write("Easy:\n");
            writer.write("avg num of connections: " + avg + "\n");
            writer.write("num nodes with no edges: " + isolated + "\n");
            writer.write("nodes with the most edges: " + mostConnected + " with " +
                    (graph.getAdjacencyList().get(mostConnected.get(0)).size()) + " vertices each\n\n");

            GraphSearch search = new GraphSearch();
            Set<String> largestComponent = search.findLargestDFS(graph.getAdjacencyList());

            List<String> bfsSample = search.bfs(graph.getAdjacencyList(), largestComponent.iterator().next());
            List<String> dfsSample = new ArrayList<>(largestComponent); // Already depth-first inserted

            writer.write("Medium:\n");
            writer.write("BFS: " + bfsSample.subList(0, Math.min(15, bfsSample.size())) + "\n");
            writer.write("DFS: " + dfsSample.subList(0, Math.min(15, dfsSample.size())) + "\n");
            writer.write("Size of largest connected set: [" + largestComponent.size() + "]\n\n");

            MSTCalculator mstCalc = new MSTCalculator();
            int mstWeight = mstCalc.calculateMSTWeight(largestComponent, graph.getAdjacencyList());

            writer.write("Hard:\n");
            writer.write("Total weight of mst: " + mstWeight + "\n");

            System.out.println("Results written to graph_algos_output.txt");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
