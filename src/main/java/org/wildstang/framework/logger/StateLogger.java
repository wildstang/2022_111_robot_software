package org.wildstang.framework.logger;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

import org.wildstang.framework.CoreUtils;

/**
 * Manages logging in a Thread. Includes logging control and JSON formatting.
 */
public class StateLogger implements Runnable {

    private static final long s_defaultWriteInterval = 200;

    private Writer m_output;
    private boolean m_infoWritten = false;
    private boolean m_firstState = true;
    private boolean m_stateWritten = false;
    private boolean m_running = false;
    private StateTracker m_tracker;
    private long m_writeInterval = s_defaultWriteInterval;

    /**
     * StateLogger requires a StateTracker where it pulls logging info from.
     * @param p_tracker StateTracker to log from.
     */
    public StateLogger(StateTracker p_tracker) {
        m_tracker = p_tracker;
    }

    /**
     * Sets the Writer used for logging.
     * @param p_output New writer to log to.
     */
    public void setWriter(Writer p_output) {
        m_output = p_output;
    }

    /**
     * Returns the Writer used for logging.
     * @return Writer used for logging.
     */
    public Writer getOutput() {
        return m_output;
    }

    /**
     * Returns true if the logger is running.
     * @return True if the logger is running
     */
    public boolean isRunning() {
        return m_running;
    }

    /**
     * Resume logging.
     */
    public void start() {
        m_running = true;
    }

    /**
     * Pause logging.
     */
    public void stop() {
        m_running = false;
    }

    /**
     * Thread that handles all logging. Pauses while m_running is false.
     */
    @Override
    public void run() {
        try {
            // create log
            writeStart();

            // continuously write until told to stop
            while (m_running) {
                try {
                    Thread.sleep(m_writeInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // write if tracking state
                if (m_tracker.isTrackingState()) {
                    if (!m_infoWritten) {
                        writeInfo(m_tracker.getIoSet());
                        m_infoWritten = true;
                    } else {
                        writeState(m_tracker.getStateList());
                    }
                }
            }

            // write any remaining state information, complete log, and flush
            writeState(m_tracker.getStateList());
            writeEnd();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the interval to write to log in milliseconds.
     * @param p_interval Inverval between logging in milliseconds.
     */
    public void setWriteInterval(long p_interval) {
        m_writeInterval = p_interval;
    }

    /**
     * Write an IOSet to log.
     * @param p_set IOSet to write to log.
     * @throws IOException If write fails.
     */
    protected void writeInfo(IOSet p_set) throws IOException {
        // The set should not be null - this would indicate a code error
        CoreUtils.checkNotNull(p_set, "p_set is null");
        // The Writer may be null - it is not fatal. Errors causing a null Writer
        // should be caught by the caller

        Iterator<IOInfo> iter = p_set.getInfoList().iterator();
        StringBuilder builder = new StringBuilder();

        builder.append("\"ioinfo\":[\n");

        while (iter.hasNext()) {
            builder.append(formatIOInfo(iter.next()));
            if (iter.hasNext()) {
                builder.append(",\n");
            }
        }
        builder.append("\n],\n");

        if (m_output != null) {
            m_output.write(builder.toString());
        }
    }

    /**
     * Write a StateGroup List to log.
     * @param p_set States to write to log.
     * @throws IOException If write fails.
     */
    protected void writeState(List<StateGroup> p_stateList) throws IOException {
        // The list should never be null - this would indicate a code error
        CoreUtils.checkNotNull(p_stateList, "p_stateList is null");
        // The Writer could be null - this is not fatal. Errors that would
        // lead to a null writer should be caught before calling this

        StringBuilder builder = new StringBuilder();

        if (m_firstState) {
            builder.append("\"state\":[\n");
            // Set flag that this has run once
            m_firstState = false;
        }

        if (p_stateList.size() > 0) {
            if (m_stateWritten) {
                builder.append(",\n");
            }

            Iterator<StateGroup> iter = p_stateList.iterator();

            while (iter.hasNext()) {
                builder.append(formatState(iter.next()));
                if (iter.hasNext()) {
                    builder.append(",\n");
                }

            }

            m_stateWritten = true;
        }

        // If the writer is null, don't write it
        // This is non-fatal. If we miss a few values, it doesn't matter
        // Don't log that it is null - it gets called too frequently
        if (m_output != null) {
            m_output.write(builder.toString());
        }

    }

    /**
     * Create beginning of JSON and write to log.
     * @throws IOException If write fails.
     */
    protected void writeStart() throws IOException {

        StringBuilder builder = new StringBuilder();

        builder.append("{\n");

        // It's not critical that this is not written out
        // Don't log that it is null - it gets called too frequently
        if (m_output != null) {
            m_output.write(builder.toString());
        }
    }

    /**
     * Create end of JSON, write to log, and flush.
     * @throws IOException If write fails.
     */
    protected void writeEnd() throws IOException {

        StringBuilder builder = new StringBuilder();

        builder.append("\n]\n}\n");

        // It's not critical that this is not written out
        // Don't log that it is null - it gets called too frequently
        if (m_output != null) {
            m_output.write(builder.toString());
        }
        m_output.flush();
    }

    /**
     * Format a single StateGroup to JSON before logging.
     * @param p_group StateGroup to format.
     * @return Formatted StateGroup as JSON String.
     */
    protected String formatState(StateGroup p_group) {
        StringBuilder builder = new StringBuilder();

        builder.append("\t{\n\t\t\"timestamp\":\"");
        builder.append(p_group.getTimestamp().getTime());
        builder.append("\",\n\t\t\"values\":[\n");

        Iterator<StateInfo> iter = p_group.getStateList().values().iterator();
        StateInfo temp;

        while (iter.hasNext()) {
            temp = iter.next();

            builder.append("\t\t\t{\"name\":\"");
            builder.append(temp.getName());
            // builder.append("\",\"parent\":\"");
            // builder.append(temp.getParent());
            builder.append("\",\"value\":\"");
            builder.append(temp.getValue());
            builder.append("\"}");

            if (iter.hasNext()) {
                builder.append(",");
            }

            builder.append("\n");
        }

        builder.append("\t\t]\n\t}");

        return builder.toString();
    }

    /**
     * Format a single IOInfo to JSON before logging.
     * @param p_group IOInfo to format.
     * @return Formatted IOInfo as JSON String.
     */
    protected String formatIOInfo(IOInfo p_info) {
        StringBuilder builder = new StringBuilder();

        // Format the info set
        builder.append("\t{\"name\":\"");
        builder.append(p_info.getName());
        builder.append("\",\"type\":\"");
        builder.append(p_info.getType());
        builder.append("\",\"direction\":\"");
        builder.append(p_info.getDirection());
        builder.append("\",\"port\":");

        Object portInfo = p_info.getPort();
        if (portInfo == null) {
            builder.append("{}");
        } else {
            builder.append(portInfo);
        }

        builder.append("}");

        return builder.toString();
    }
}
