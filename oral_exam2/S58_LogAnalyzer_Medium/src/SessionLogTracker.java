import java.util.*;
import java.util.concurrent.BlockingQueue;

/**
 * Tracks INFO-level logs, specifically user login events and login frequency.
 */
public class SessionLogTracker extends LogHandler {

    private int sessionTotal = 0;
    private final List<String> userList = new ArrayList<>();
    private final List<Integer> loginTally = new ArrayList<>();

    /**
     * Constructs a new SessionLogTracker.
     *
     * @param buffer the log buffer queue
     */
    public SessionLogTracker(BlockingQueue<LogRecord> buffer) {
        super(buffer);
    }

    /**
     * Processes INFO-level logs and records login statistics.
     */
    @Override
    public void run() {
        try {
            while (true) {
                LogRecord record = getNextRecord();
                if (!shouldContinue(record)) break;
                if (record == null) continue;

                sessionTotal++;
                handleLogin(record);
            }
        } catch (InterruptedException e) {
            // Graceful exit
        }
    }

    /**
     * Extracts login user from the log message and updates tally.
     *
     * @param record the log record to parse
     */
    private void handleLogin(LogRecord record) {
        String msg = record.getMessage().toLowerCase();
        if (msg.contains("user logged in:")) {
            String user = msg.split(":")[1].trim();
            int index = userList.indexOf(user);
            if (index >= 0) {
                loginTally.set(index, loginTally.get(index) + 1);
            } else {
                userList.add(user);
                loginTally.add(1);
            }
        }
    }

    /**
     * Prints the number of INFO logs and login counts per user.
     */
    @Override
    public synchronized void report() {
        System.out.println("Session logs processed: " + sessionTotal);
        System.out.println("User login summary:");
        for (int i = 0; i < userList.size(); i++) {
            System.out.println("  " + userList.get(i) + ": " + loginTally.get(i));
        }
    }
}
