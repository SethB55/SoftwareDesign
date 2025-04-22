//BFS and the DFS algos
import java.util.*;

/**
 * Performs DFS and BFS to identify connected components in the graph.
 */
public class GraphSearch {
    public Set<String> findLargestDFS(Map<String, List<String>> graph) {
        Set<String> visited = new HashSet<>();
        Set<String> largest = new HashSet<>();

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                Set<String> component = new HashSet<>();
                dfs(graph, node, visited, component);
                if (component.size() > largest.size()) {
                    largest = component;
                }
            }
        }

        return largest;
    }

    private void dfs(Map<String, List<String>> graph, String node, Set<String> visited, Set<String> component) {
        visited.add(node);
        component.add(node);
        for (String neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                dfs(graph, neighbor, visited, component);
            }
        }
    }

    public List<String> bfs(Map<String, List<String>> graph, String start) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        List<String> result = new ArrayList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            String node = queue.poll();
            result.add(node);
            for (String neighbor : graph.get(node)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return result;
    }
}
