import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Abstract base class for log analyzers that process log entries from a shared buffer.
 * Subclasses must define their own report mechanism.
 */
public abstract class LogHandler implements Runnable {

    private final BlockingQueue<LogRecord> buffer;
    private boolean running = true;

    /**
     * Constructs a LogHandler with the specified buffer.
     *
     * @param buffer the shared buffer containing log records
     */
    public LogHandler(BlockingQueue<LogRecord> buffer) {
        this.buffer = buffer;
    }

    /**
     * Signals this analyzer to stop running.
     */
    public final void stop() {
        running = false;
    }

    /**
     * Retrieves and processes a log entry from the buffer.
     *
     * @return the next LogRecord, or null if none is available
     * @throws InterruptedException if polling is interrupted
     */
    public final LogRecord getNextRecord() throws InterruptedException {
        return buffer.poll(10, TimeUnit.MILLISECONDS);
    }

    /**
     * Checks if the analyzer should continue processing.
     *
     * @param record the record to check for termination
     * @return true to continue, false to stop
     */
    public final boolean shouldContinue(LogRecord record) {
        if (record != null && "TERMINATE".equals(record.getLevel())) {
            running = false;
            return false;
        }
        return running;
    }

    /**
     * Generates a final report after all logs have been processed.
     */
    public abstract void report();
}
