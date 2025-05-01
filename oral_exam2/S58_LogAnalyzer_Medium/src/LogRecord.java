/**
 * Represents a single log message with severity level and message content.
 */
public class LogRecord {

    private final String level;
    private final String message;

    /**
     * Creates a new log record.
     *
     * @param level   the severity level of the log (INFO, WARN, ERROR)
     * @param message the log content
     */
    public LogRecord(String level, String message) {
        this.level = level;
        this.message = message;
    }

    /**
     * Returns the severity level of this log.
     *
     * @return the log level
     */
    public String getLevel() {
        return level;
    }

    /**
     * Returns the message associated with this log.
     *
     * @return the log message
     */
    public String getMessage() {
        return message;
    }
}
