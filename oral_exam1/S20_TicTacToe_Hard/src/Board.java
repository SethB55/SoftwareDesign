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
                board[i][j] = ' ';
            }
        }
    }

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

    public boolean makeMove(int row, int col, char symbol) {

        return true;
    }
    public boolean checkSpace(int row, int col) {
        if(board[row][col] != ' '){
            return false;
        } else {
            return true;
        }
    }
}
