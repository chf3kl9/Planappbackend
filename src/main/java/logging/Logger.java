package logging;

import java.util.ArrayList;
import java.util.List;

public class Logger implements ILogger {

    private static ILogger instance = null;

    public static ILogger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    private ArrayList<LogMessage> messages = new ArrayList<>();

    public void log(Exception ex) {
        log(ex.getMessage(), LogLevel.FATAL);
    }

    public void log(String message, LogLevel logLevel)
    {
        messages.add(new LogMessage(message, logLevel));
        if (logLevel == LogLevel.ERROR || logLevel == LogLevel.FATAL || logLevel == LogLevel.WARNING)
            System.out.println((char)27 + "[31m" + logLevel + ": " + message);
        else
            System.out.println((char)27 + "[34m" + logLevel + ": " + message);
    }

    public LogMessage getLastLog() {
        if (messages.isEmpty())
            return messages.get(messages.size() - 1);
        else
            return new LogMessage("", LogLevel.DEBUG);
    }

    public List<LogMessage> getMessages() {
        return messages;
    }
}
