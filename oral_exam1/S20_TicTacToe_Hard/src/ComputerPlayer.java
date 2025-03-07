//just gonna use a random name generator, it isnt gonna be smart just a brute force luck factor
import java.util.Random;

/**
 * Represents a computer-controlled player in a Tic-Tac-Toe game.
 * The computer selects moves randomly until a valid move is found.
 */
public class ComputerPlayer extends Player {
    private Random random = new Random();

    /**
     * Constructs a ComputerPlayer with a specified name and symbol.
     *
     * @param name   The name of the computer player.
     * @param symbol The symbol ('X' or 'O') the computer player uses.
     */
    public ComputerPlayer(String name, char symbol) {
        super(name, symbol);
    }

    /**
     * Makes a move by randomly selecting an available position on the board.
     *
     * @param board The game board where the move will be made.
     */
    @Override
    public void makeMove(Board board) {
        int row;
        int col;
        int stayInLoopCondition = 1;
        while (stayInLoopCondition == 1) {
            row = random.nextInt(3);
            col = random.nextInt(3);
            if (board.checkSpace(row, col)) {
                stayInLoopCondition = 0;
                board.makeMove(row, col, super.getSide());
                System.out.println("Placing " + super.getSide() + " at row " + row + " and col " + col + ".");
            }
        }
    }
}
