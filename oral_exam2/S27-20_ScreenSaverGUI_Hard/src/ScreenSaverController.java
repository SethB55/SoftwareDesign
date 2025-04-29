import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration; //all of the imports

import java.util.Random; //for random numb of line length

/**
 * Controller class responsible for handling the user input and automatic line drawing on the canvas.
 * This class binds GUI elements (Canvas and TextField) to animation logic.
 */
public class ScreenSaverController {

    @FXML private Canvas displayCanvas;              // Canvas to draw on
    @FXML private TextField lineLimitField;          // TextField for max lines input

    private GraphicsContext context;                 // Graphics context for drawing
    private int maxLinesAllowed = 100;               // Maximum lines before clearing
    private int currentLineCount = 0;                // Current line count on screen
    private final Random rng = new Random();         // Random number generator for coordinates
    private Timeline animationTimeline;              // Timeline for automatic drawing

    /**
     * Initializes the controller after FXML loading.
     * Sets up the graphics context, text field listener, and starts the drawing animation.
     *
     * No parameters and no return value.
     */
    @FXML
    public void initialize() {
        context = displayCanvas.getGraphicsContext2D();

        // Listener that resets the line counter when a new value is typed in the input field
        lineLimitField.setOnKeyPressed((KeyEvent event) -> {
            try {
                maxLinesAllowed = Integer.parseInt(lineLimitField.getText()); // Set new max from input
                currentLineCount = 0; // Reset counter
            } catch (NumberFormatException ignored) {
                // Ignore invalid input
            }
        });

        // Set up and start the animation timeline
        animationTimeline = new Timeline(new KeyFrame(Duration.millis(200), e -> drawRandomLine()));
        animationTimeline.setCycleCount(Timeline.INDEFINITE);
        animationTimeline.play(); // Start drawing animation
    }

    /**
     * Draws a single random line on the canvas. If the max line count is reached,
     * the canvas is cleared and the line count is reset.
     *
     * No parameters and no return value.
     */
    private void drawRandomLine() {
        // Clear canvas if line count reaches limit
        if (currentLineCount >= maxLinesAllowed) {
            context.clearRect(0, 0, displayCanvas.getWidth(), displayCanvas.getHeight());
            currentLineCount = 0;
        }

        // Generate random start and end coordinates
        double xStart = rng.nextDouble() * displayCanvas.getWidth();
        double yStart = rng.nextDouble() * displayCanvas.getHeight();
        double xEnd = rng.nextDouble() * displayCanvas.getWidth();
        double yEnd = rng.nextDouble() * displayCanvas.getHeight();

        context.setStroke(Color.BLACK);
        context.strokeLine(xStart, yStart, xEnd, yEnd); // Draw random line
        currentLineCount++;
    }
}
