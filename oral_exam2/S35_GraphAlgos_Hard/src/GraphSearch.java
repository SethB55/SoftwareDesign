//BFS and the DFS algos
import java.util.*;

/**
 * Provides graph search algorithms, including Depth-First Search (DFS)
 * and Breadth-First Search (BFS), to explore connected components within a graph.
 */
public class GraphSearch {

    /**
     * Finds the largest connected component in the graph using DFS.
     *
     * @param graph the adjacency list representation of the graph.
     * @return a set of nodes representing the largest connected component.
     */
    public Set<String> findLargestDFS(Map<String, List<String>> graph) {
        Set<String> visited = new HashSet<>(); // Tracks visited nodes
        Set<String> largest = new HashSet<>(); // Tracks the largest connected component found

        // Iterate over all nodes to explore disconnected components
        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                Set<String> component = new HashSet<>(); // Holds the current component
                dfs(graph, node, visited, component); // Perform DFS from the unvisited node
                if (component.size() > largest.size()) {
                    largest = component; // Update if the current component is larger
                }
            }
        }

        return largest;
    }

    /**
     * Recursive helper method for DFS traversal.
     *
     * @param graph the adjacency list representation of the graph.
     * @param node the current node being explored.
     * @param visited the set of already visited nodes.
     * @param component the current connected component being built.
     */
    private void dfs(Map<String, List<String>> graph, String node, Set<String> visited, Set<String> component) {
        visited.add(node);
        component.add(node);
        // Recur for all unvisited neighbors
        for (String neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                dfs(graph, neighbor, visited, component);
            }
        }
    }

    /**
     * Performs Breadth-First Search (BFS) starting from a given node.
     *
     * @param graph the adjacency list representation of the graph.
     * @param start the starting node for BFS.
     * @return a list of nodes visited in BFS order.
     */
    public List<String> bfs(Map<String, List<String>> graph, String start) {
        Set<String> visited = new HashSet<>(); // Tracks visited nodes
        Queue<String> queue = new LinkedList<>(); // Queue for BFS
        List<String> result = new ArrayList<>(); // Stores BFS traversal order

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            String node = queue.poll(); // Dequeue node
            result.add(node); // Process node
            for (String neighbor : graph.get(node)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor); // Mark neighbor as visited
                    queue.add(neighbor); // Enqueue neighbor
                }
            }
        }

        return result;
    }
}
