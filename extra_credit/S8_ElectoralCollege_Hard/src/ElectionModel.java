import java.util.*;

public class ElectionModel {
    private final Map<String, Integer> stateVotes;
    private final Map<String, String> stateChoices;
    private final Map<String, Integer> districtVotes;
    private final Map<String, String> districtChoices;

    public ElectionModel() {
        stateVotes = new HashMap<>();
        stateChoices = new HashMap<>();
        districtVotes = new HashMap<>();
        districtChoices = new HashMap<>();

        // Initialize all states and votes (2024 values)
        initializeStates();
    }

    private void initializeStates() {
        // Winner-takes-all states
        stateVotes.put("Alabama", 9);
        stateVotes.put("Alaska", 3);
        stateVotes.put("Arizona", 11);
        // Add all other states...
        stateVotes.put("California", 54);
        stateVotes.put("Florida", 30);
        stateVotes.put("Texas", 40);

        // Maine and Nebraska districts
        stateVotes.put("Maine", 2);
        districtVotes.put("Maine-1", 1);
        districtVotes.put("Maine-2", 1);

        stateVotes.put("Nebraska", 2);
        districtVotes.put("Nebraska-1", 1);
        districtVotes.put("Nebraska-2", 1);
        districtVotes.put("Nebraska-3", 1);

        // Initialize all to Undecided
        stateVotes.keySet().forEach(s -> stateChoices.put(s, "Undecided"));
        districtVotes.keySet().forEach(d -> districtChoices.put(d, "Undecided"));
    }

    public void setStateVote(String state, String party) {
        stateChoices.put(state, party);
    }

    public void setDistrictVote(String district, String party) {
        districtChoices.put(district, party);
    }

    public Map<String, Integer> calculateResults() {
        Map<String, Integer> results = new HashMap<>();
        results.put("Democrat", 0);
        results.put("Republican", 0);

        // Count state votes (winner-takes-all)
        stateVotes.forEach((state, votes) -> {
            String choice = stateChoices.get(state);
            if (!choice.equals("Undecided")) {
                results.put(choice, results.get(choice) + votes);
            }
        });

        // Count district votes
        districtVotes.forEach((district, votes) -> {
            String choice = districtChoices.get(district);
            if (!choice.equals("Undecided")) {
                results.put(choice, results.get(choice) + votes);
            }
        });

        return results;
    }

    public boolean hasWinner() {
        Map<String, Integer> results = calculateResults();
        return results.get("Democrat") >= 270 || results.get("Republican") >= 270;
    }

    public String getWinner() {
        Map<String, Integer> results = calculateResults();
        if (results.get("Democrat") >= 270) return "Democrat";
        if (results.get("Republican") >= 270) return "Republican";
        return null;
    }
}