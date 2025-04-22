//MST (Prim's)
import java.util.*;

/**
 * Computes MST for the largest connected component using Prim's algorithm.
 */
public class MSTCalculator {

    private static class Edge implements Comparable<Edge> {
        String target;
        int weight;

        Edge(String target, int weight) {
            this.target = target;
            this.weight = weight;
        }

        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public int calculateMSTWeight(Set<String> component, Map<String, List<String>> graph) {
        int totalWeight = 0;
        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        String start = component.iterator().next();
        visited.add(start);

        for (String neighbor : graph.get(start)) {
            if (component.contains(neighbor)) {
                int weight = Math.min(WordUtils.magnitude(start), WordUtils.magnitude(neighbor));
                pq.add(new Edge(neighbor, weight));
            }
        }

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            if (visited.contains(current.target)) continue;

            visited.add(current.target);
            totalWeight += current.weight;

            for (String neighbor : graph.get(current.target)) {
                if (!visited.contains(neighbor) && component.contains(neighbor)) {
                    int weight = Math.min(WordUtils.magnitude(current.target), WordUtils.magnitude(neighbor));
                    pq.add(new Edge(neighbor, weight));
                }
            }
        }

        return totalWeight;
    }
}
