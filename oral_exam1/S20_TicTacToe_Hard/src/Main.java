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
        Player player2 = null;
        System.out.print("Please enter your name player1: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        String name2 = null;
        Player player1 = new HumanPlayer(name, 'X');
        String namwe = player1.getName();
        System.out.println("You are X's, " + namwe);
        while(stayInLoopCondition == 1){
            System.out.println("Would you like to play against another human or a bot? (human/bot)");
            String decision = input.nextLine();
            if(decision.equals("human")){
                stayInLoopCondition = 0;
                System.out.print("Please enter your name player2: ");
                name2 = input.nextLine();
                player2 = new HumanPlayer(name2, 'O');

                System.out.println("You are O's, " + name2);
            } else if(decision.equals("bot")){
                stayInLoopCondition = 0;
                name2 = "bot";
                player2 = new ComputerPlayer(name2, 'O');
                System.out.println("Bot will be O's");
            } else {
                System.out.println("Bro you didnt choose right, try again");
            }
        }


        System.out.println("Great! Starting game....");
        Board newBoard = new Board();
        newBoard.resetBoard();

        Game game = new Game(player1, player2);
        game.startGame();
    }

}
