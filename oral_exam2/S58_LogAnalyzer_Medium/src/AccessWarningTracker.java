import java.util.concurrent.BlockingQueue;

public class AccessWarningTracker extends LogHandler {

    private int warningCount = 0;
    private int unauthCount = 0;

    public AccessWarningTracker(BlockingQueue<LogRecord> buffer) {
        super(buffer);
    }

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
            // clean exit
        }
    }

    @Override
    public void report() {
        System.out.println("Warning logs processed: " + warningCount);
        System.out.println("Unauthorized warnings: " + unauthCount);
    }
}
