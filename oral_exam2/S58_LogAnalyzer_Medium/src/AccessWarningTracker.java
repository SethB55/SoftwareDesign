import java.util.concurrent.BlockingQueue;

/**
 * Analyzes WARN-level logs, including unauthorized access attempts.
 */
public class AccessWarningTracker extends LogHandler {

    private int warningCount = 0;
    private int unauthCount = 0;

    /**
     * Constructs a new AccessWarningTracker with the given log queue.
     *
     * @param buffer the log buffer for WARN entries
     */
    public AccessWarningTracker(BlockingQueue<LogRecord> buffer) {
        super(buffer);
    }

    /**
     * Processes WARN-level logs and counts unauthorized access attempts.
     */
    @Override
    public void run() {
        try {
            while (true) {
                LogRecord record = getNextRecord();
                if (!shouldContinue(record)) break;
                if (record == null) continue;

                warningCount++;
                if (record.getMessage().toLowerCase().contains("unauthorized access")) {
                    unauthCount++;
                }
            }
        } catch (InterruptedException e) {
            // Clean exit
        }
    }

    /**
     * Prints the number of WARN logs and unauthorized attempts counted.
     */
    @Override
    public void report() {
        System.out.println("Warning logs processed: " + warningCount);
        System.out.println("Unauthorized warnings: " + unauthCount);
    }
}
