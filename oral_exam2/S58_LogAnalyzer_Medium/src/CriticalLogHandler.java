import java.io.*;
import java.util.concurrent.BlockingQueue;

public class CriticalLogHandler extends LogHandler {

    private int criticalCount = 0;
    private BufferedWriter writer;

    public CriticalLogHandler(BlockingQueue<LogRecord> buffer, String filename) throws IOException {
        super(buffer);
        this.writer = new BufferedWriter(new FileWriter(filename));
    }

    @Override
    public void run() {
        try {
            while (true) {
                LogRecord record = getNextRecord();
                if (!shouldContinue(record)) break;
                if (record == null) continue;

                criticalCount++;
                writer.write(record.getLevel() + " - " + record.getMessage());
                writer.newLine();

                if (criticalCount % 10 == 0) {
                    writer.flush();
                }
            }
        } catch (InterruptedException | IOException e) {
            System.err.println("Critical log handler error: " + e.getMessage());
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.err.println("Failed to close critical log file: " + e.getMessage());
            }
        }
    }

    @Override
    public void report() {
        System.out.println("Critical logs processed: " + criticalCount);
    }
}
