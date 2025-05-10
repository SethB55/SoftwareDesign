import java.util.*;

/**
 * The ElectionModel class represents the data model for the US election simulator.
 * It tracks votes for each state and district, calculates totals, and determines the winner.
 */
public class ElectionModel {
    // Maps to store electoral votes and current selections
    private final Map<String, Integer> stateVotes;       // State names to electoral votes
    private final Map<String, String> stateChoices;     // State names to current party choice
    private final Map<String, Integer> districtVotes;    // District names to electoral votes
    private final Map<String, String> districtChoices;  // District names to current party choice
    private int democratTotal;                           // Running total of Democrat votes
    private int republicanTotal;                        // Running total of Republican votes

    /**
     * Constructs a new ElectionModel and initializes all states and districts.
     */
    public ElectionModel() {
        stateVotes = new HashMap<>();
        stateChoices = new HashMap<>();
        districtVotes = new HashMap<>();
        districtChoices = new HashMap<>();
        democratTotal = 0;
        republicanTotal = 0;
        initializeAllStates(); // Initialize with all state and district data
    }

    /**
     * Initializes all states and districts with their electoral votes and default choices.
     */
    private void initializeAllStates() {
        // Regular winner-takes-all states
        stateVotes.put("Alabama", 9);                  // Alabama has 9 electoral votes
        stateVotes.put("Alaska", 3);                   // Alaska has 3 electoral votes
        // ... [other state initializations remain unchanged]

        // Maine and Nebraska districts (split electoral votes)
        districtVotes.put("Maine-1", 1);               // Maine's 1st district
        districtVotes.put("Maine-2", 1);               // Maine's 2nd district
        // ... [other district initializations remain unchanged]

        // Initialize all states and districts to "Undecided"
        for (String state : stateVotes.keySet()) {
            stateChoices.put(state, "Undecided");      // Default to undecided
        }
        for (String district : districtVotes.keySet()) {
            districtChoices.put(district, "Undecided"); // Default to undecided
        }
    }

    /**
     * Sets the vote for a state or district.
     * @param name The name of the state or district
     * @param party The party to vote for ("Democrat", "Republican", or "Undecided")
     * @throws IllegalArgumentException if name or party is null or invalid
     */
    public void setStateVote(String name, String party) {
        if (name == null || party == null) {
            throw new IllegalArgumentException("Name and party cannot be null");
        }

        // First check if it's a state
        if (stateVotes.containsKey(name)) {
            String previousChoice = stateChoices.get(name); // Get current selection
            int votes = stateVotes.get(name);               // Get electoral votes

            updateTotals(previousChoice, party, votes);     // Update running totals
            stateChoices.put(name, party);                  // Store new selection
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

    /**
     * Updates the running vote totals when a selection changes.
     * @param previousChoice The previous party selection
     * @param newChoice The new party selection
     * @param votes The number of electoral votes to adjust by
     */
    private void updateTotals(String previousChoice, String newChoice, int votes) {
        // Remove votes from previous choice
        if ("Democrat".equals(previousChoice)) {
            democratTotal -= votes; // Subtract from Democrat total
        } else if ("Republican".equals(previousChoice)) {
            republicanTotal -= votes; // Subtract from Republican total
        }

        // Add votes to new choice
        if ("Democrat".equals(newChoice)) {
            democratTotal += votes; // Add to Democrat total
        } else if ("Republican".equals(newChoice)) {
            republicanTotal += votes; // Add to Republican total
        }
    }

    /**
     * Gets the current party selection for a state or district.
     * @param state The name of the state or district
     * @return The current party selection ("Democrat", "Republican", or "Undecided")
     */
    public String getStateSelection(String state) {
        if (stateVotes.containsKey(state)) {
            return stateChoices.get(state); // Return state selection
        }
        return districtChoices.getOrDefault(state, "Undecided"); // Return district selection or default
    }

    /**
     * Gets the current total Democrat electoral votes.
     * @return Total Democrat votes
     */
    public int getDemocratTotal() {
        return democratTotal;
    }

    /**
     * Gets the current total Republican electoral votes.
     * @return Total Republican votes
     */
    public int getRepublicanTotal() {
        return republicanTotal;
    }

    /**
     * Checks if either party has won the election (≥270 votes).
     * @return true if there is a winner, false otherwise
     */
    public boolean hasWinner() {
        return democratTotal >= 270 || republicanTotal >= 270; // 270 needed to win
    }

    /**
     * Gets the winning party if there is one.
     * @return "DEMOCRAT" or "REPUBLICAN" if a winner exists, null otherwise
     */
    public String getWinner() {
        if (democratTotal >= 270) return "DEMOCRAT";    // Democrat wins
        if (republicanTotal >= 270) return "REPUBLICAN"; // Republican wins
        return null; // No winner yet
    }
}