import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * The entry point of the log analyzer application.
 * <p>
 * This class sets up buffers and log analyzers, reads from a log input file,
 * routes log entries based on severity level, and prints a summary report.
 */
public class Main {

    /**
     * Initializes the analyzer system, starts analyzer threads, processes input logs,
     * and prints a summary report.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        final int MAX_BATCH = 100;
        final String INPUT_PATH = "/iahome/s/se/sebolen/IdeaProjects/sebolen_swd/oral_exam2/S58_LogAnalyzer_Medium/src/input.txt";
        final String OUTPUT_PATH = "/iahome/s/se/sebolen/IdeaProjects/sebolen_swd/oral_exam2/S58_LogAnalyzer_Medium/critical_output.log";

        ExecutorService pool = Executors.newCachedThreadPool();

        // Create buffers
        BlockingQueue<LogRecord> infoQ = new ArrayBlockingQueue<>(10);
        BlockingQueue<LogRecord> warnQ = new ArrayBlockingQueue<>(10);
        BlockingQueue<LogRecord> errorQ = new ArrayBlockingQueue<>(10);

        try {
            // Instantiate analyzers
            CriticalLogHandler errorHandler = new CriticalLogHandler(errorQ, OUTPUT_PATH);
            SessionLogTracker infoHandler = new SessionLogTracker(infoQ);
            AccessWarningTracker warnHandler = new AccessWarningTracker(warnQ);

            // Start analyzers *before* feeding logs
            pool.execute(errorHandler);
            pool.execute(infoHandler);
            pool.execute(warnHandler);

            // Feed log entries
            readAndDispatchLogs(INPUT_PATH, MAX_BATCH, infoQ, warnQ, errorQ);

            // Signal analyzers to stop
            sendPoison(infoQ);
            sendPoison(warnQ);
            sendPoison(errorQ);

            // Await completion
            pool.shutdown();
            pool.awaitTermination(1, TimeUnit.MINUTES);

            // Print report
            System.out.println("\n--- Summary Report ---");
            errorHandler.report();
            infoHandler.report();
            warnHandler.report();

        } catch (Exception e) {
            System.err.println("Log analysis failed: " + e.getMessage());
        }
    }

    /**
     * Reads the input file in chunks and sends log records to appropriate queues.
     *
     * @param filePath  path to the input log file
     * @param batchSize number of entries per batch
     * @param infoBuf   INFO log buffer
     * @param warnBuf   WARN log buffer
     * @param errorBuf  ERROR log buffer
     * @throws IOException if the file cannot be read
     */
    private static void readAndDispatchLogs(String filePath, int batchSize,
                                            BlockingQueue<LogRecord> infoBuf,
                                            BlockingQueue<LogRecord> warnBuf,
                                            BlockingQueue<LogRecord> errorBuf) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<LogRecord> chunk = new ArrayList<>(batchSize);
            String line;

            while ((line = reader.readLine()) != null) {
                LogRecord rec = parseLine(line);
                if (rec != null) {
                    chunk.add(rec);
                    if (chunk.size() == batchSize) {
                        dispatch(chunk, infoBuf, warnBuf, errorBuf);
                        chunk.clear();
                    }
                }
            }

            // Final batch
            if (!chunk.isEmpty()) {
                dispatch(chunk, infoBuf, warnBuf, errorBuf);
            }
        }
    }

    /**
     * Sends log records from a batch to the appropriate buffer based on severity level.
     *
     * @param records  list of log entries
     * @param infoBuf  INFO queue
     * @param warnBuf  WARN queue
     * @param errorBuf ERROR queue
     */
    private static void dispatch(List<LogRecord> records,
                                 BlockingQueue<LogRecord> infoBuf,
                                 BlockingQueue<LogRecord> warnBuf,
                                 BlockingQueue<LogRecord> errorBuf) {
        for (LogRecord entry : records) {
            try {
                switch (entry.getLevel()) {
                    case "INFO": infoBuf.put(entry); break;
                    case "WARN": warnBuf.put(entry); break;
                    case "ERROR": errorBuf.put(entry); break;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Sends a termination record (poison pill) to signal the end of log input.
     *
     * @param queue the target buffer
     */
    private static void sendPoison(BlockingQueue<LogRecord> queue) {
        try {
            queue.put(new LogRecord("TERMINATE", ""));
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * Converts a raw line of text from the log file into a structured LogRecord.
     *
     * @param line raw log line
     * @return parsed LogRecord or null on error
     */
    private static LogRecord parseLine(String line) {
        try {
            String[] parts = line.split(" - ", 2);
            String level = parts[0].trim().split("\\s+")[0];
            String message = parts[1].trim();
            return new LogRecord(level, message);
        } catch (Exception e) {
            return null;
        }
    }
}
