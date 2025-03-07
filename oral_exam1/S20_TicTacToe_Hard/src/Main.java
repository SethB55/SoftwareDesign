//
//  Use inheritance and polymorphism.
//        Classes needed: Board, Player (abstract), HumanPlayer, and ComputerPlayer.
//        No player-specific code in the main interface beyond instantiation.
//        No printing in ComputerPlayer and Player classes; only HumanPlayer can handle input prompt
//        Need scanner functionality to get input from user
import java.util.Scanner; // weird glitch?

/**
 * Main class to run the Tic-Tac-Toe game.
 * Initializes players, handles player input, and starts the game.
 */
public class Main {

    /**
     * The main method that runs the Tic-Tac-Toe game.
     * It prompts the user for player names, selects opponents (human or bot),
     * and starts the game with the chosen players.
     *
     * @param args command line arguments (not used in this program)
     */
    public static void main(String[] args) {
        int stayInLoopCondition = 1;
        Player player2 = null;

        // Prompt for player 1's name
        System.out.print("Please enter your name player1: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        String name2 = null;

        // Create player 1 as a human player with 'X' as the side
        Player player1 = new HumanPlayer(name, 'X');
        String namwe = player1.getName();
        System.out.println("You are X's, " + namwe);

        // Loop to decide if player 2 is a human or a bot
        while (stayInLoopCondition == 1) {
            System.out.println("Would you like to play against another human or a bot? (human/bot)");
            String decision = input.nextLine();

            if (decision.equals("human")) {
                stayInLoopCondition = 0;
                System.out.print("Please enter your name player2: ");
                name2 = input.nextLine();
                player2 = new HumanPlayer(name2, 'O');

                System.out.println("You are O's, " + name2);
            } else if (decision.equals("bot")) {
                stayInLoopCondition = 0;
                name2 = "bot";
                player2 = new ComputerPlayer(name2, 'O');
                System.out.println("Bot will be O's");
            } else {
                System.out.println("Bro you didnt choose right, try again");
            }
        }

        // Start the game with the selected players
        System.out.println("Great! Starting game....");

        // Initialize the game board and reset it
        Board newBoard = new Board();
        newBoard.resetBoard();

        // Start the game with the selected players and the board
        Game game = new Game(player1, player2);
        game.startGame();
    }
}
