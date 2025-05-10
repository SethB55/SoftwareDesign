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
        // Initialize all 50 states and Washington D.C. with their electoral votes
        stateVotes.put("Alabama", 9);                  // Alabama has 9 electoral votes
        stateVotes.put("Alaska", 3);                   // Alaska has 3 electoral votes
        stateVotes.put("Arizona", 11);                 // Arizona has 11 electoral votes
        stateVotes.put("Arkansas", 6);                 // Arkansas has 6 electoral votes
        stateVotes.put("California", 54);              // California has 54 electoral votes
        stateVotes.put("Colorado", 10);                // Colorado has 10 electoral votes
        stateVotes.put("Connecticut", 7);              // Connecticut has 7 electoral votes
        stateVotes.put("Delaware", 3);                 // Delaware has 3 electoral votes
        stateVotes.put("District of Columbia", 3);     // Washington D.C. has 3 electoral votes
        stateVotes.put("Florida", 30);                 // Florida has 30 electoral votes
        stateVotes.put("Georgia", 16);                 // Georgia has 16 electoral votes
        stateVotes.put("Hawaii", 4);                   // Hawaii has 4 electoral votes
        stateVotes.put("Idaho", 4);                    // Idaho has 4 electoral votes
        stateVotes.put("Illinois", 19);                // Illinois has 19 electoral votes
        stateVotes.put("Indiana", 11);                 // Indiana has 11 electoral votes
        stateVotes.put("Iowa", 6);                     // Iowa has 6 electoral votes
        stateVotes.put("Kansas", 6);                   // Kansas has 6 electoral votes
        stateVotes.put("Kentucky", 8);                 // Kentucky has 8 electoral votes
        stateVotes.put("Louisiana", 8);                // Louisiana has 8 electoral votes
        stateVotes.put("Maine", 2);                    // Maine has 2 state-wide electoral votes
        stateVotes.put("Maryland", 10);                // Maryland has 10 electoral votes
        stateVotes.put("Massachusetts", 11);           // Massachusetts has 11 electoral votes
        stateVotes.put("Michigan", 15);                // Michigan has 15 electoral votes
        stateVotes.put("Minnesota", 10);               // Minnesota has 10 electoral votes
        stateVotes.put("Mississippi", 6);              // Mississippi has 6 electoral votes
        stateVotes.put("Missouri", 10);                // Missouri has 10 electoral votes
        stateVotes.put("Montana", 4);                  // Montana has 4 electoral votes
        stateVotes.put("Nebraska", 2);                 // Nebraska has 2 state-wide electoral votes
        stateVotes.put("Nevada", 6);                   // Nevada has 6 electoral votes
        stateVotes.put("New Hampshire", 4);            // New Hampshire has 4 electoral votes
        stateVotes.put("New Jersey", 14);              // New Jersey has 14 electoral votes
        stateVotes.put("New Mexico", 5);               // New Mexico has 5 electoral votes
        stateVotes.put("New York", 28);                // New York has 28 electoral votes
        stateVotes.put("North Carolina", 16);          // North Carolina has 16 electoral votes
        stateVotes.put("North Dakota", 3);             // North Dakota has 3 electoral votes
        stateVotes.put("Ohio", 17);                    // Ohio has 17 electoral votes
        stateVotes.put("Oklahoma", 7);                 // Oklahoma has 7 electoral votes
        stateVotes.put("Oregon", 8);                   // Oregon has 8 electoral votes
        stateVotes.put("Pennsylvania", 19);            // Pennsylvania has 19 electoral votes
        stateVotes.put("Rhode Island", 4);             // Rhode Island has 4 electoral votes
        stateVotes.put("South Carolina", 9);           // South Carolina has 9 electoral votes
        stateVotes.put("South Dakota", 3);             // South Dakota has 3 electoral votes
        stateVotes.put("Tennessee", 11);               // Tennessee has 11 electoral votes
        stateVotes.put("Texas", 40);                   // Texas has 40 electoral votes
        stateVotes.put("Utah", 6);                     // Utah has 6 electoral votes
        stateVotes.put("Vermont", 3);                  // Vermont has 3 electoral votes
        stateVotes.put("Virginia", 13);                // Virginia has 13 electoral votes
        stateVotes.put("Washington", 12);              // Washington has 12 electoral votes
        stateVotes.put("West Virginia", 4);            // West Virginia has 4 electoral votes
        stateVotes.put("Wisconsin", 10);               // Wisconsin has 10 electoral votes
        stateVotes.put("Wyoming", 3);                  // Wyoming has 3 electoral votes

        // Initialize congressional districts for Maine and Nebraska
        districtVotes.put("Maine-1", 1);               // Maine's 1st congressional district
        districtVotes.put("Maine-2", 1);               // Maine's 2nd congressional district
        districtVotes.put("Nebraska-1", 1);            // Nebraska's 1st congressional district
        districtVotes.put("Nebraska-2", 1);            // Nebraska's 2nd congressional district
        districtVotes.put("Nebraska-3", 1);            // Nebraska's 3rd congressional district

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