//PLAYER CLASS OUTLINE BEFORE CODING
//Parent class for both the ComputerPlayer and HumanPlayer classes
/**
 * Abstract class representing a player in the Tic-Tac-Toe game.
 * This class stores the player's name and side, and provides methods
 * to get the player's information and make a move.
 */
public abstract class Player {
    protected String name;
    protected char side;

    /**
     * Constructs a Player with the given name and side.
     *
     * @param name the name of the player
     * @param side the side (character) the player will use, typically 'X' or 'O'
     */
    public Player(String name, char side) {
        this.name = name;
        this.side = side;
    }

    // initialize board
    //public abstract void makeMove(Board board);

    /**
     * Abstract method that must be implemented by subclasses to allow
     * the player to make a move on the game board.
     *
     * @param board the game board where the move will be made
     */
    public abstract void makeMove(Board board);

    /**
     * Gets the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the side of the player.
     *
     * @return the side (character) of the player
     */
    public char getSide() {
        return side;
    }
}
