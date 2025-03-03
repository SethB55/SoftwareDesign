//MAIN GAME LOOP THAT WE RUN TO USE EVERY PART OF OUR FUNCTIONALITY
//NEED PRIVATE BOARD AND PLAYERS
public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    public Game(Player player1, Player player2){
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
    }

    public void startGame(){

        Player currentPlayer = player1;
        board.displayBoard();

        while(true){
            System.out.print(currentPlayer.getName() + "'s turn:");
            currentPlayer.makeMove(board);
            board.displayBoard();

            if(board.checkWin(currentPlayer.getSide())){
                System.out.println(currentPlayer.getSide() + "Wins tic tac toe!");
                break;
            }

            if(board.checkFUll()){
                System.out.println("CAT! (It's a draw!)");
                break;
            }

            //switch turns
            if(currentPlayer == player1){
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }
        }
    }
}
