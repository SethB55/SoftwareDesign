import javax.swing.JFrame;

public class AdderFrameDriver {
    public static void main(String[] args){
        AdderFrame adderFrame = new AdderFrame();
        adderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adderFrame.setSize(350, 100);
        adderFrame.setVisible(true);
    }

}
