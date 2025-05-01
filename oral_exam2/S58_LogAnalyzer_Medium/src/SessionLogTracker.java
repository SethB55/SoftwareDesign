import java.util.*;
import java.util.concurrent.BlockingQueue;

public class SessionLogTracker extends LogHandler {

    private int sessionTotal = 0;
    private final List<String> userList = new ArrayList<>();
    private final List<Integer> loginTally = new ArrayList<>();

    public SessionLogTracker(BlockingQueue<LogRecord> buffer) {
        super(buffer);
    }

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
            // thread exit
        }
    }

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

    @Override
    public synchronized void report() {
        System.out.println("Session logs processed: " + sessionTotal);
        System.out.println("User login summary:");
        for (int i = 0; i < userList.size(); i++) {
            System.out.println("  " + userList.get(i) + ": " + loginTally.get(i));
        }
    }
}
