import javafx.application.Application;

/**
 * Main run that launches the JavaFX Screen Saver application.
 */
public class MainApp {

    /**
     * Starts the JavaFX application by calling Application.launch.
     * Also prints starting and exits statements to the command line
     * That I used for testing
     */
    public static void main(String[] args) {
        System.out.println("Launching JavaFX Screen Saver Application...");
        Application.launch(ScreenSaverGUI.class);
        System.out.println("JavaFX Application has exited.");
    }
}
