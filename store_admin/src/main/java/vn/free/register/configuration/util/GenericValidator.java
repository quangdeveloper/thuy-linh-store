package vn.free.register.configuration.util;

import java.util.Collection;
import java.util.Map;

public class GenericValidator {

    public static boolean isBlankOrNull(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isBlankOrNull(Collection<?> c) {
        return c == null || c.isEmpty();
    }

    public static boolean isBlankOrNull(Map<?, ?> m) {
        return m == null || m.isEmpty();
    }

    public static boolean isBlankOrNull(Number n) {
        return n == null || n.doubleValue() == 0;
    }

    public static boolean isBlankOrNull(Object[] a) {
        return a == null || a.length == 0;
    }

    public static String padStart(String string, int minLength, char padChar) {
        if (null == string) {
            string = "";
        }
        if (string.length() >= minLength) {
            return string;
        }
        StringBuilder sb = new StringBuilder(minLength);
        for (int i = string.length(); i < minLength; i++) {
            sb.append(padChar);
        }
        sb.append(string);
        return sb.toString();
    }

    public static String padEnd(String string, int minLength, char padChar) {
        if (null == string) {
            string = "";
        }
        if (string.length() >= minLength) {
            return string;
        }
        StringBuilder sb = new StringBuilder(minLength);
        sb.append(string);
        for (int i = string.length(); i < minLength; i++) {
            sb.append(padChar);
        }
        return sb.toString();
    }

}
