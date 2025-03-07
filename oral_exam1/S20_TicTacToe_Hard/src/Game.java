//MAIN GAME LOOP THAT WE RUN TO USE EVERY PART OF OUR FUNCTIONALITY
//NEED PRIVATE BOARD AND PLAYERS
/**
 * Represents the main game loop for a Tic-Tac-Toe game.
 * Manages the board and players, enforcing game rules and turn-taking.
 */
public class Game {
    private Board board;
    private Player player1;
    private Player player2;

    /**
     * Constructs a Game instance with two players.
     *
     * @param player1 The first player.
     * @param player2 The second player.
     */
    public Game(Player player1, Player player2) {
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Starts and runs the Tic-Tac-Toe game loop.
     * Resets the board, takes turns making moves, checks for a winner or draw,
     * and displays the board state after each move.
     */
    public void startGame() {
        board.resetBoard();
        Player currentPlayer = player1;
        board.displayBoard();

        while (true) {
            currentPlayer.makeMove(board);
            board.displayBoard();

            if (board.checkWin(currentPlayer.getSide())) {
                System.out.println(currentPlayer.getSide() + " Wins tic-tac-toe!");
                break;
            }

            if (board.checkFUll()) {
                System.out.println("CAT! (It's a draw!)");
                break;
            }

            // Switch turns
            if (currentPlayer == player1) {
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }
        }
    }
}
