import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX application class that loads the screen saver layout from an FXML file.
 */
public class ScreenSaverGUI extends Application {

    /**
     * Initializes and displays the JavaFX primary stage using the FXML layout.
     *
     * @param primaryStage the main window stage provided by the JavaFX framework.
     * @throws Exception if the FXML file cannot be loaded.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("myscreensaver.fxml"));
        VBox root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Screen Saver");
        primaryStage.show(); // Show the main stage
    }
}
