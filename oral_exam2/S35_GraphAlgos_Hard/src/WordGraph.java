import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Represents a graph of words where edges exist between words that differ by exactly one edit distance.
 * Provides functionality to build the graph from a file, analyze connectivity, and compute graph metrics.
 */
public class WordGraph {
    private Map<String, List<String>> adjacencyList = new HashMap<>();

    /**
     * Builds the graph by reading words from the given file and connecting words
     * that have an edit distance of one.
     * @param filePath the path to the file containing words, one per line.
     * @throws IOException if there is an issue reading the file.
     */
    public void buildGraph(String filePath) throws IOException {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String word;
            while ((word = reader.readLine()) != null) { //keep going till end
                words.add(word);
                adjacencyList.put(word, new ArrayList<>()); // Initialize adjacency list for each word
            }
        }

        // Connect words with edit distance of one
        for (int i = 0; i < words.size(); i++) {
            for (int j = i + 1; j < words.size(); j++) {
                if (isEditDistanceOne(words.get(i), words.get(j))) {
                    adjacencyList.get(words.get(i)).add(words.get(j));
                    adjacencyList.get(words.get(j)).add(words.get(i));
                }
            }
        }
    }


    /**
     * Checks whether two words differ by exactly one character (edit distance of one).
     * This method assumes both words must be of the same length to compare.
     *
     * @param w1 the first word to compare.
     * @param w2 the second word to compare.
     * @return true if the words differ by exactly one character, false otherwise.
     */
    public static boolean isEditDistanceOne(String w1, String w2) {
        if (w1.length() != w2.length()) {
            return false; // Words of different lengths can't have edit distance of one
        }

        int diff = 0; // Counter for differing characters
        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                diff++;
                if (diff > 1) return false; // More than one difference, not valid
            }
        }
        return diff == 1; // Valid only if exactly one difference
    }
    /**
     * Returns the adjacency list of the graph.
     * @return the adjacency list.
     */
    public Map<String, List<String>> getAdjacencyList() {
        return adjacencyList;
    }

    /**
     * Counts the number of isolated vertices (vertices with no neighbors).
     * @return the count of isolated vertices.
     */
    public int getIsolatedVertexCount() {
        int count = 0;
        for (List<String> neighbors : adjacencyList.values()) {
            if (neighbors.isEmpty()) count++;
        }
        return count;
    }

    /**
     * Finds the words with the highest number of connections (most neighbors).
     * @return a list of the most connected words.
     */
    public List<String> getMostConnectedWords() {
        int max = 0;
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
            int size = entry.getValue().size();
            if (size > max) {
                max = size;
                result.clear();
                result.add(entry.getKey());
            } else if (size == max) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    /**
     * Calculates the average number of edges per vertex in the graph.
     * @return the average edges per vertex.
     */
    public double getAverageEdgesPerVertex() {
        int total = 0;
        for (List<String> neighbors : adjacencyList.values()) {
            total += neighbors.size();
        }
        return (double) total / adjacencyList.size();
    }
}
