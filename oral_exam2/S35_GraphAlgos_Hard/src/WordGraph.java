//core graph structure + the builder

import java.io.BufferedReader;
        import java.io.FileReader;
        import java.io.IOException;
        import java.util.*;

/**
 * Builds and stores the graph representation of the word connections.
 */
public class WordGraph {
    private Map<String, List<String>> adjacencyList = new HashMap<>();

    public void buildGraph(String filePath) throws IOException {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String word;
            while ((word = reader.readLine()) != null) {
                words.add(word);
                adjacencyList.put(word, new ArrayList<>());
            }
        }

        for (int i = 0; i < words.size(); i++) {
            for (int j = i + 1; j < words.size(); j++) {
                if (WordUtils.isEditDistanceOne(words.get(i), words.get(j))) {
                    adjacencyList.get(words.get(i)).add(words.get(j));
                    adjacencyList.get(words.get(j)).add(words.get(i));
                }
            }
        }
    }

    public Map<String, List<String>> getAdjacencyList() {
        return adjacencyList;
    }

    public int getIsolatedVertexCount() {
        int count = 0;
        for (List<String> neighbors : adjacencyList.values()) {
            if (neighbors.isEmpty()) count++;
        }
        return count;
    }

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

    public double getAverageEdgesPerVertex() {
        int total = 0;
        for (List<String> neighbors : adjacencyList.values()) {
            total += neighbors.size();
        }
        return (double) total / adjacencyList.size();
    }
}
