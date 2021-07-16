package org.wildstang.framework.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import org.wildstang.framework.CoreUtils;
import org.wildstang.framework.logger.Log;

/**
 * This class represents the config parameters. It is a map of key/value pairs.
 *
 * @author Steve
 */
public class Config {

    private HashMap<String, Object> m_configMap = new HashMap<>();

    /**
     * Load in a config from file.
     * @param p_reader BufferedReader on a config file.
     */
    protected void load(BufferedReader p_reader) {
        CoreUtils.checkNotNull(p_reader, "p_reader is null");

        String currentLine;

        String[] keyValue;
        String key = null;
        String value = null;
        Object parsedValue = null;

        try {
            while ((currentLine = p_reader.readLine()) != null) {
                currentLine = stripComments(currentLine.trim());

                if (currentLine.length() > 0) {
                    // Split token on = to get name/value
                    keyValue = getKeyValuePair(currentLine);

                    key = keyValue[0];
                    if (key != null && key.length() > 0 && keyValue.length > 1) {
                        value = keyValue[1];

                        // Parse value
                        parsedValue = parseValue(value);

                        if (parsedValue != null) {
                            m_configMap.put(key, parsedValue);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes any part of a line following a hash character - '#'. Returns any part
     * of the line that comes before that character, which will be an empty string
     * if the line starts with a '#'. If a null string is passed in, a
     * NullPointerException is thrown. The String is trimmed before being returned
     * to remove any whitespace on either end.
     * @param p_line a String representing a line of text
     * @return the String, minus any present # character, and any characters that
     *         follow it, if any. May return an empty String, but will never return null.
     * @throws NullPointerException True if the input String is null.
     */
    protected String stripComments(String p_line) {
        CoreUtils.checkNotNull(p_line, "p_line is null");

        // split line into parts
        String result;
        int commentIdx = p_line.indexOf("#");
        // get text before #
        if (commentIdx > 0) {
            result = p_line.split("#")[0].trim();
        }
        // no comment, return line
        else if (commentIdx < 0) {
            result = p_line;
        }
        // whole line comment, return nothing
        else {
            result = "";
        }

        return result;
    }

    /**
     * Splits the input String on an equals (=) character, and returns the two parts
     * as an array of two Strings.
     * @param p_line String containing an '='.
     * @return Array of p_line split by '='.
     * @throws NullPointerException True if the String passed in is null.
     */
    protected String[] getKeyValuePair(String p_line) {
        String[] result;
        result = p_line.split("=");
        return result;
    }

    /**
     * Parses the value from a String. It attempts to parse it into a primitive
     * type, returns as its wrapper class. The order it attempts is: double, int,
     * boolean, String.
     * @param p_valueStr String containing a value.
     * @return Value as type object.
     */
    protected Object parseValue(String p_valueStr) {
        CoreUtils.checkNotNull(p_valueStr, "p_valueStr is null");

        // 1. if it contains . try to parse as double, otherwise take as string
        Object result = parseDouble(p_valueStr);

        // 2. parse as int
        if (result == null) {
            result = parseInt(p_valueStr);
        }

        // 3. if it equals 'true' or 'false' ignoring case, parse as boolean
        if (result == null) {
            result = parseBoolean(p_valueStr);
        }

        // 4. if all these tests or parses fail, leave as string
        if (result == null) {
            if (p_valueStr != null && !p_valueStr.equals("")) {
                result = p_valueStr;
            }
        }

        return result;
    }

    /**
     * Converts a String containing a double to a Double.
     * @param p_valueStr String representation of a double.
     * @return Parsed Double or null if not a double.
     */
    protected Double parseDouble(String p_valueStr) {
        CoreUtils.checkNotNull(p_valueStr, "p_valueStr is null");

        Double d = null;
        if (p_valueStr.indexOf('.') >= 0) {
            try {
                d = Double.valueOf(p_valueStr);
            }
            catch (NumberFormatException e) {}
        }

        return d;
    }

    /**
     * Converts a String containing a int to a Int.
     * @param p_valueStr String representation of a int.
     * @return Parsed Int or null if not a int.
     */
    protected Integer parseInt(String p_valueStr) {
        CoreUtils.checkNotNull(p_valueStr, "p_valueStr is null");

        Integer i = null;

        try {
            i = Integer.valueOf(p_valueStr);
        }
        catch (NumberFormatException e) {}

        return i;
    }

    /**
     * Converts a String containing a boolean to a Boolean.
     * @param p_valueStr String representation of a boolean.
     * @return Parsed Boolean or null if not a boolean.
     */
    protected Boolean parseBoolean(String p_valueStr) {
        CoreUtils.checkNotNull(p_valueStr, "p_valueStr is null");

        Boolean b = null;

        if (p_valueStr.equalsIgnoreCase("true") || p_valueStr.equalsIgnoreCase("false")) {
            try {
                b = Boolean.valueOf(p_valueStr);
            }
            catch (NumberFormatException e) {}
        }

        return b;
    }

    /**
     * Gets the value corresponding to a key in the config.
     * @param p_key Key to find corresponding value to.
     * @return Value corresponding to key.
     */
    public Object getValue(String p_key) {
        CoreUtils.checkNotNull(p_key, "p_key is null");

        return m_configMap.get(p_key);
    }

    /**
     * Gets the value corresponding to a key in the config, with default.
     * @param p_key Key to find corresponding value to.
     * @param p_default Default value if p_key doesn't exist.
     * @return Value corresponding to key, or default value.
     */
    public Object getValue(String p_key, Object p_default) {
        CoreUtils.checkNotNull(p_key, "p_key is null");

        Object value = m_configMap.get(p_key);

        if (value == null) {
            Log.info("No value for key " + p_key + ". Using default value: " + p_default);
            value = p_default;
        }

        return value;
    }

    /**
     * Gets the double corresponding to a key in the config, with default.
     * @param p_key Key to find corresponding double to.
     * @param p_default Default double if p_key doesn't exist.
     * @return double corresponding to key, or default double.
     */
    public double getDouble(String p_key, double p_default) {
        Object value = getValue(p_key, p_default);
        if (!(value instanceof Double)) {
            throw new NumberFormatException("Value found for " + p_key
                    + " but value was not a Double: " + value.getClass().getName());
        }

        return (Double) value;
    }

    /**
     * Gets the int corresponding to a key in the config, with default.
     * @param p_key Key to find corresponding int to.
     * @param p_default Default int if p_key doesn't exist.
     * @return int corresponding to key, or default int.
     */
    public int getInt(String p_key, int p_default) {
        Object value = getValue(p_key, p_default);
        if (!(value instanceof Integer)) {
            throw new NumberFormatException("Value found for " + p_key
                    + " but value was not a Integer: " + value.getClass().getName());
        }

        return (Integer) value;
    }

    /**
     * Gets the boolean corresponding to a key in the config, with default.
     * @param p_key Key to find corresponding boolean to.
     * @param p_default Default boolean if p_key doesn't exist.
     * @return boolean corresponding to key, or default boolean.
     */
    public boolean getBoolean(String p_key, boolean p_default) {
        Object value = getValue(p_key, p_default);
        if (!(value instanceof Boolean)) {
            throw new NumberFormatException("Value found for " + p_key
                    + " but value was not a Boolean: " + value.getClass().getName());
        }

        return (Boolean) value;
    }

    /**
     * Gets the String corresponding to a key in the config, with default.
     * @param p_key Key to find corresponding String to.
     * @param p_default Default String if p_key doesn't exist.
     * @return String corresponding to key, or default String.
     */
    public String getString(String p_key, String p_default) {
        Object value = getValue(p_key, p_default);
        if (!(value instanceof String)) {
            throw new NumberFormatException("Value found for " + p_key
                    + " but value was not a String: " + value.getClass().getName());
        }
        return (String) value;
    }

    /**
     * Returns the size of the total config map.
     * @return Size of config map.
     */
    public int size() {
        return m_configMap.size();
    }
}
