package org.wildstang.framework.logger;

/**
 * Static class wrapping System.out/err logging functions for nicer and controlled output.
 * The level controls which log functions work the higher the set level, the fewers logs.
 * Lowest to highest: info, warn, error, none.
 */
public class Log {
    
    private static LogLevel level = LogLevel.WARN;

    /**
     * Internal function to build stack trace note.
     * Each log starts with "[Class.method():line] "
     * @return The trace note.
     */
    private static String getTrace() {
        StackTraceElement e = Thread.currentThread().getStackTrace()[3];
        try {
            return "[" + Class.forName(e.getClassName()).getSimpleName() + "." + e.getMethodName() + ":" + e.getLineNumber() + "] ";
        }
        catch (Exception ex) {
            return "";
        }
    }

    /**
     * Log at level "info".
     * This is meant for normal messages that mean no alarm.
     * @param msg Log message text.
     */
    public static void info(String msg) {
        if (level.ordinal() >= LogLevel.INFO.ordinal()) {
            System.out.println(getTrace() + msg);
        }
    }

    /**
     * Log at level "warn".
     * This is meant for warning messages that mean something is approaching problematic.
     * @param msg Log message text.
     */
    public static void warn(String msg) {
        if (level.ordinal() >= LogLevel.WARN.ordinal()) {
            System.out.println(getTrace() + msg);
        }
    }

    /**
     * Log at level "error".
     * This is meant for error messages that mean something went wrong and require attention.
     * @param msg Log message text.
     */
    public static void error(String msg) {
        if (level.ordinal() >= LogLevel.ERROR.ordinal()) {
            System.err.println(getTrace() + msg);
        }
    }
    
    /**
     * Log no matter what.
     * This is meant for messages that mean the robot has entered a potentially dangerous state.
     * E.g. The robot entering autonomous mode.
     * @param msg Log message text.
     */
    public static void danger(String msg) {
        System.out.println(getTrace() + msg);
    }

    /**
     * Sets the minimum LogLevel to log at.
     * @param level Lowest case at which it should log.
     */
    public static void setLevel(LogLevel level) {
        Log.level = level;
    }

    /**
     * An enum representing the 4 LogLevels.
     */
    public enum LogLevel {
        NONE,
        ERROR,
        WARN,
        INFO
    }
}