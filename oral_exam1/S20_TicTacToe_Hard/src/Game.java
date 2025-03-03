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

    }
}
