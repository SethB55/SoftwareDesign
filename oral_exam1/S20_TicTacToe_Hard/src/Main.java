//
//  Use inheritance and polymorphism.
//        Classes needed: Board, Player (abstract), HumanPlayer, and ComputerPlayer.
//        No player-specific code in the main interface beyond instantiation.
//        No printing in ComputerPlayer and Player classes; only HumanPlayer can handle input prompt
//        Need scanner functionality to get input from user
import java.util.Scanner; //weird glitch?

public class Main {
    public static void main(String[] args) {
        int stayInLoopCondition = 1;
        System.out.print("Say your name user: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        Player player1 = new HumanPlayer(name, 'X');
        String namwe = player1.getName();
        System.out.println("You are X's, " + namwe);
        while(stayInLoopCondition == 1){
            System.out.println("Would you like to play against another human or a bot? (human/bot)");
            String decision = input.nextLine();
            if(decision.equals("human")){
                stayInLoopCondition = 0;

                System.out.print("Say your name player2: ");
                String name2 = input.nextLine();
                Player player2= new HumanPlayer(name2, 'O');
                System.out.println("You are O's, " + name2);
            } else if(decision.equals("bot")){
                stayInLoopCondition = 0;
                Player player2= new HumanPlayer("bot", 'O');
                System.out.println("Bot will be O's");
            } else {
                System.out.println("Bro you didnt choose right, try again");
            }
        }
        System.out.println("Great! Starting game....");
        Board newBoard = new Board();

        newBoard.resetBoard();
        newBoard.displayBoard();
    }

}
