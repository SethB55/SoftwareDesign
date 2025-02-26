//PLAYER CLASS OUTLINE BEFORE CODING
//Parent class for both the ComputerPlayer and HumanPlayer classes
public abstract class Player {
    protected String name;
    protected char side;

    public Player(String name, char side) {
        this.name = name;
        this.side = side;
    }
    //initialize board
    //public abstract void makeMove(Board board);
    //getters
    public String getName() {
        return name;
    }
    public char getSide() {
        return side;
    }
}