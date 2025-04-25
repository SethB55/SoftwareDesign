import java.util.*;

/**
 * Computes the Minimum Spanning Tree (MST) weight for the largest connected component
 * of a graph using Prim's algorithm. The graph is represented with words as nodes, and
 * edge weights are determined by the minimum magnitude of two connected words.
 */
public class MSTCalculator {  //THIS DOESNT WORK BRUV

    /**
     * Represents an edge in the graph with a target vertex and a weight.
     */
    private static class Edge implements Comparable<Edge> {
        String target; // Target vertex of the edge
        int weight;    // Weight of the edge

        /**
         * Constructs an Edge with the given target and weight.
         * @param target the target vertex.
         * @param weight the weight of the edge.
         */
        Edge(String target, int weight) {
            this.target = target;
            this.weight = weight;
        }

        /**
         * Compares this edge with another based on weight.
         * @param other the other edge to compare against.
         * @return a negative integer, zero, or a positive integer as this edge's weight
         *         is less than, equal to, or greater than the other edge's weight.
         */
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    /**
     * Calculates the total weight of the MST for a given connected component of a graph.
     * @param component the set of vertices in the connected component.
     * @param graph the adjacency list representation of the graph.
     * @return the total weight of the MST for the connected component.
     */
    public int calculateMSTWeight(Set<String> component, Map<String, List<String>> graph) {
        int totalWeight = 0;
        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        // Start with any node in the component
        String start = component.iterator().next();
        visited.add(start);

        // Add initial edges from the start node to the priority queue
        for (String neighbor : graph.get(start)) {
            if (component.contains(neighbor)) {
                int weight = Math.min(WordUtils.magnitude(start), WordUtils.magnitude(neighbor));
                pq.add(new Edge(neighbor, weight));
            }
        }

        // Continue adding the minimum weight edges connecting new vertices
        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            if (visited.contains(current.target)) continue;

            visited.add(current.target);
            totalWeight += current.weight; // Add the weight of the selected edge

            // Add all edges from the newly visited vertex to the priority queue
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
