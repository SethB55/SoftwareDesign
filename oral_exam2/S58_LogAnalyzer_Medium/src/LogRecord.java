public class LogRecord {

    private final String level;
    private final String message;

    public LogRecord(String level, String message) {
        this.level = level;
        this.message = message;
    }

    public String getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }
}
