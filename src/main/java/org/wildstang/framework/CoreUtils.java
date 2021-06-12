package org.wildstang.framework;

public class CoreUtils {
    public static void checkNotNull(Object p_param, String p_message) {
        if (p_param == null) {
            throw new NullPointerException(p_message);
        }
    }
}