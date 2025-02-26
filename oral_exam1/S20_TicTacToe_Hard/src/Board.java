// BOARD CLASS OUTLINE BEFORE CODING
//makes board
//Holds the current board configuration and ensures only valid moves are made.
//AddX and AddO methods that add to the board but must check if valid
//Check Win method: Must check after every valid move made if there is a winner or both losers
// reset method is called and resets the board
public class Board {
    private char[][] board; //blank to set to
    public Board(){ //base case
        board = new char[3][3]; //sizeing

    }

    public void resetBoard(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = 'O';
            }
        }
    }

    public void displayBoard(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(board[i][j]);
            }
            System.out.println("___");
        }
    }
}
