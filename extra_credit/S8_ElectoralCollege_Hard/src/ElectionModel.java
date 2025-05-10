import java.util.*;

public class ElectionModel {
    private final Map<String, Integer> stateVotes;
    private final Map<String, String> stateChoices;
    private final Map<String, Integer> districtVotes;
    private final Map<String, String> districtChoices;
    private int democratTotal;
    private int republicanTotal;

    public ElectionModel() {
        stateVotes = new HashMap<>();
        stateChoices = new HashMap<>();
        districtVotes = new HashMap<>();
        districtChoices = new HashMap<>();
        democratTotal = 0;
        republicanTotal = 0;
        initializeAllStates();
    }

    private void initializeAllStates() {
        // Regular winner-takes-all states
        stateVotes.put("Alabama", 9);
        stateVotes.put("Alaska", 3);
        stateVotes.put("Arizona", 11);
        stateVotes.put("Arkansas", 6);
        stateVotes.put("California", 54);
        stateVotes.put("Colorado", 10);
        stateVotes.put("Connecticut", 7);
        stateVotes.put("Delaware", 3);
        stateVotes.put("District of Columbia", 3);
        stateVotes.put("Florida", 30);
        stateVotes.put("Georgia", 16);
        stateVotes.put("Hawaii", 4);
        stateVotes.put("Idaho", 4);
        stateVotes.put("Illinois", 19);
        stateVotes.put("Indiana", 11);
        stateVotes.put("Iowa", 6);
        stateVotes.put("Kansas", 6);
        stateVotes.put("Kentucky", 8);
        stateVotes.put("Louisiana", 8);
        stateVotes.put("Maine", 2); // State-wide votes
        stateVotes.put("Maryland", 10);
        stateVotes.put("Massachusetts", 11);
        stateVotes.put("Michigan", 15);
        stateVotes.put("Minnesota", 10);
        stateVotes.put("Mississippi", 6);
        stateVotes.put("Missouri", 10);
        stateVotes.put("Montana", 4);
        stateVotes.put("Nebraska", 2); // State-wide votes
        stateVotes.put("Nevada", 6);
        stateVotes.put("New Hampshire", 4);
        stateVotes.put("New Jersey", 14);
        stateVotes.put("New Mexico", 5);
        stateVotes.put("New York", 28);
        stateVotes.put("North Carolina", 16);
        stateVotes.put("North Dakota", 3);
        stateVotes.put("Ohio", 17);
        stateVotes.put("Oklahoma", 7);
        stateVotes.put("Oregon", 8);
        stateVotes.put("Pennsylvania", 19);
        stateVotes.put("Rhode Island", 4);
        stateVotes.put("South Carolina", 9);
        stateVotes.put("South Dakota", 3);
        stateVotes.put("Tennessee", 11);
        stateVotes.put("Texas", 40);
        stateVotes.put("Utah", 6);
        stateVotes.put("Vermont", 3);
        stateVotes.put("Virginia", 13);
        stateVotes.put("Washington", 12);
        stateVotes.put("West Virginia", 4);
        stateVotes.put("Wisconsin", 10);
        stateVotes.put("Wyoming", 3);

        // Maine and Nebraska districts
        districtVotes.put("Maine-1", 1);
        districtVotes.put("Maine-2", 1);
        districtVotes.put("Nebraska-1", 1);
        districtVotes.put("Nebraska-2", 1);
        districtVotes.put("Nebraska-3", 1);

        // Initialize all to Undecided
        for (String state : stateVotes.keySet()) {
            stateChoices.put(state, "Undecided");
        }
        for (String district : districtVotes.keySet()) {
            districtChoices.put(district, "Undecided");
        }
    }

    public void setStateVote(String name, String party) {
        if (name == null || party == null) {
            throw new IllegalArgumentException("Name and party cannot be null");
        }

        // First check if it's a state
        if (stateVotes.containsKey(name)) {
            String previousChoice = stateChoices.get(name);
            int votes = stateVotes.get(name);

            updateTotals(previousChoice, party, votes);
            stateChoices.put(name, party);
        }
        // Then check if it's a district
        else if (districtVotes.containsKey(name)) {
            String previousChoice = districtChoices.get(name);
            int votes = districtVotes.get(name);

            updateTotals(previousChoice, party, votes);
            districtChoices.put(name, party);
        } else {
            throw new IllegalArgumentException("Unknown state or district: " + name);
        }
    }

    private void updateTotals(String previousChoice, String newChoice, int votes) {
        // Remove votes from previous choice
        if ("Democrat".equals(previousChoice)) {
            democratTotal -= votes;
        } else if ("Republican".equals(previousChoice)) {
            republicanTotal -= votes;
        }

        // Add votes to new choice
        if ("Democrat".equals(newChoice)) {
            democratTotal += votes;
        } else if ("Republican".equals(newChoice)) {
            republicanTotal += votes;
        }
    }

    public String getStateSelection(String state) {
        if (stateVotes.containsKey(state)) {
            return stateChoices.get(state);
        }
        return districtChoices.getOrDefault(state, "Undecided");
    }

    public int getDemocratTotal() {
        return democratTotal;
    }

    public int getRepublicanTotal() {
        return republicanTotal;
    }

    public boolean hasWinner() {
        return democratTotal >= 270 || republicanTotal >= 270;
    }

    public String getWinner() {
        if (democratTotal >= 270) return "DEMOCRAT";
        if (republicanTotal >= 270) return "REPUBLICAN";
        return null;
    }
}