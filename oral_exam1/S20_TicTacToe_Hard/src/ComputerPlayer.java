//just gonna use a random name generator, it isnt gonna be smart just a brute force luck factor

import java.util.Random;
public class ComputerPlayer extends Player{
    private Random random = new Random();
    public ComputerPlayer(String name, char symbol) {
        super(name, symbol);
    }
    @Override
    public void makeMove(Board board){
        int row;
        int col;
        int stayInLoopCondition = 1;
        while(stayInLoopCondition == 1){
            row = random.nextInt(3);
            col = random.nextInt(3);
            if(board.checkSpace(row, col)){
                stayInLoopCondition = 0;
                board.makeMove(row, col, super.getSide());
                System.out.println("Placing " + super.getSide() + " at row " + row + " and col " + col + ".");
            }
        }
    }
}
