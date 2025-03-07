import java.util.Scanner;

/**
 * Represents a human player in a Tic-Tac-Toe game.
 * Extends the Player class to handle user input for making a move.
 */
public class HumanPlayer extends Player {
    Scanner input = new Scanner(System.in);

    /**
     * Constructs a HumanPlayer with the given name and side.
     *
     * @param name the name of the player
     * @param side the side (character) the player will use, typically 'X' or 'O'
     */
    public HumanPlayer(String name, char side) {
        super(name, side);
    }

    /**
     * Asks the user for input to make a move and places their piece on the board.
     * This method keeps prompting the user until a valid move is made.
     *
     * @param board the game board on which the move will be made
     */
    @Override
    public void makeMove(Board board) {
        int row;
        int col;
        int stayInLoopCondition = 1;

        // Loop until the user provides a valid move
        while (stayInLoopCondition == 1) {
            System.out.println(super.name + ", it is your turn, please say where you would like to place your "
                    + super.getSide() + " by entering a single digit for row (0-2) then column (0-2) ");

            // User input for row and column
            row = input.nextInt();
            col = input.nextInt();

            // Check if the provided move is within valid bounds and the space is empty
            if (row >= 0 && row <= 3 && col >= 0 && col <= 3 && board.checkSpace(row, col)) {
                stayInLoopCondition = 0;
                System.out.println("Placing " + super.getSide() + " at row " + row + " and col " + col + ".");

                // Make the move on the board
                board.makeMove(row, col, super.getSide());
            } else {
                System.out.println("Bro you didnt do it right, try again");
            }
        }
    }
}
