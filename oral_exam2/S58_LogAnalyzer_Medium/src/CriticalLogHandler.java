import java.io.*;
import java.util.concurrent.BlockingQueue;

/**
 * Handles and records ERROR-level logs into a specified output file.
 */
public class CriticalLogHandler extends LogHandler {

    private int criticalCount = 0;
    private BufferedWriter writer;

    /**
     * Constructs a new CriticalLogHandler with an output file and buffer.
     *
     * @param buffer   queue of log records to process
     * @param filename output file path for error logs
     * @throws IOException if file cannot be written
     */
    public CriticalLogHandler(BlockingQueue<LogRecord> buffer, String filename) throws IOException {
        super(buffer);
        this.writer = new BufferedWriter(new FileWriter(filename));
    }

    /**
     * Continuously processes logs from the buffer and writes ERROR entries to file.
     */
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

    /**
     * Prints the number of critical logs processed.
     */
    @Override
    public void report() {
        System.out.println("Critical logs processed: " + criticalCount);
    }
}
