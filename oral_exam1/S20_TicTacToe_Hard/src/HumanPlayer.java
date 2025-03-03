import java.util.Scanner;

public class HumanPlayer extends Player{
    Scanner input = new Scanner(System.in);
    public HumanPlayer(String name, char side) {
        super(name, side);
    }

    // Called to ask user to make a move then it takes the input and makes move.
    @Override
    public void makeMove(Board board){
        int row;
        int col;
        int stayInLoopCondition = 1;
        while(stayInLoopCondition == 1) {
            System.out.println(super.name + ", it is your turn, please say where you would like to place your "
                    + super.getSide() + " by entering a single digit for row (0-2) then column (0-2) ");
            row = input.nextInt();
            col = input.nextInt();
            if(row >= 0 && row <= 3 && col >= 0 && col <= 3 && board.checkSpace(row, col)){ //in house check
                stayInLoopCondition = 0;
                System.out.println("Placing " + super.getSide() + " at row " + row + " and col " + col + ".");
                board.makeMove(row, col, super.getSide()); //no check built into makeMove because we only get here if its valid
            } else {
                System.out.println("Bro you didnt do it right, try again");
            }
        }

        }

}
