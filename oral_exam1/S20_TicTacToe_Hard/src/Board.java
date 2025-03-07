/**
 * Represents a Tic-Tac-Toe board that manages the game state,
 * ensures valid moves, checks for a winner, and allows resetting.
 */
public class Board {
    private char[][] board; //blank to set to

    /**
     * Constructor initializes a 3x3 board.
     */
    public Board(){ //base case
        board = new char[3][3]; //sizeing
    }

    /**
     * Resets the board by setting all positions to an empty space (' ').
     */
    public void resetBoard(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = ' ';
            }
        }
    }

    /**
     * Displays the current board state in a formatted manner.
     */
    public void displayBoard(){
        int count=0;
        int count2=0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(board[i][j]);
                if(count != 2){
                    count += 1;
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if(count2 != 2){
                count2 += 1;
                System.out.println("---------");
            }

            count = 0;
        }
    }

    /**
     * Makes a move by placing the given symbol at the specified row and column.
     * Assumes the move is valid.
     *
     * @param row    The row index (0-2).
     * @param col    The column index (0-2).
     * @param symbol The player's symbol ('X' or 'O').
     */
    public void makeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    /**
     * Checks if the board is full.
     *
     * @return true if all positions are filled, false otherwise.
     */
    public boolean checkFUll() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if a specific board position is empty.
     *
     * @param row The row index (0-2).
     * @param col The column index (0-2).
     * @return true if the position is empty, false otherwise.
     */
    public boolean checkSpace(int row, int col) {
        if(board[row][col] != ' '){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if the given player has won the game.
     *
     * @param side The player's symbol ('X' or 'O').
     * @return true if the player has a winning combination, false otherwise.
     */
    public boolean checkWin(char side){
        for( int i = 0; i < 3; i++){ //row and collumn being the same side check
            if((board[i][0] == side && board[i][1] == side && board[i][2] == side) || // Row
                    (board[0][i] == side && board[1][i] == side && board[2][i] == side)){
                return true;
            }
        }
        //else check diagonals but since there are no other cases, im using return and not if
        return ((board[0][0] == side && board[1][1] == side && board[2][2] == side) ||
                (board[0][2] == side && board[1][1] == side && board[2][0] == side));
    }
}
