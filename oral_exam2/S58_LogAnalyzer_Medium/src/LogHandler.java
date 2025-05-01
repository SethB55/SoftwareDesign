import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public abstract class LogHandler implements Runnable {

    private final BlockingQueue<LogRecord> buffer;
    private boolean running = true;

    public LogHandler(BlockingQueue<LogRecord> buffer) {
        this.buffer = buffer;
    }

    public final void stop() {
        running = false;
    }

    public abstract void report();

    public final LogRecord getNextRecord() throws InterruptedException {
        return buffer.poll(10, TimeUnit.MILLISECONDS);
    }

    public final boolean shouldContinue(LogRecord record) {
        if (record != null && "TERMINATE".equals(record.getLevel())) {
            running = false;
            return false;
        }
        return running;
    }
}
