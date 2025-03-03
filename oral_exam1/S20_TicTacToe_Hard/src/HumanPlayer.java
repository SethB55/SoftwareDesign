public class HumanPlayer extends Player{
    public HumanPlayer(String name, char side) {
        super(name, side);
    }

    // Called to ask user to make a move then it takes the input and makes move.
    public void makeMove(Board board){
        int row;
        int col;
        int stayInLoopCondition = 1;
        System.out.printlnsuper.name + ", it is your turn, please say where you would like to place your "
                + super.getSide() + "in");
        if(decision.equals("human")){
            stayInLoopCondition = 0;

            System.out.print("Say your name second guy: ");
            String name2 = input.nextLine();
            Player player2= new HumanPlayer(name2, 'O');
            System.out.println("You are O's, " + name2);
        } else if(decision.equals("bot")){
            stayInLoopCondition = 0;
            Player player2= new HumanPlayer("bot", 'O');
            System.out.println("Cool, bot will be O's");
        } else {
            System.out.println("Bro you didnt choose right, try again");
        }
    }
}
