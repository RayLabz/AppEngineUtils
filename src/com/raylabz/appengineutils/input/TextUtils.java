package com.raylabz.appengineutils.input;

/**
 * Provides utility functions related to text manipulation and checking.
 */
public class TextUtils {

    /**
     * Checks of a string contains only English alphabetic characters.
     * @param text The text to check.
     * @return Returns true if string contains only English alphabetic characters, false otherwise.
     */
    public static boolean isAlphabetic(String text) {
        return text.matches("[a-zA-Z]+");
    }

    /**
     * Checks of a string contains only numeric characters.
     * @param text The text to check.
     * @return Returns true if string contains only numeric characters, false otherwise.
     */
    public static boolean isNumeric(String text) {
        return text.matches("[0-9]+");
    }

    /**
     * Checks of a string contains only alphabetic and numeric characters.
     * @param text The text to check.
     * @return Returns true if string contains only alphabetic and numeric characters, false otherwise.
     */
    public static boolean isAlphanumeric(String text) {
        return text.matches("[a-zA-Z0-9]+");
    }

    /**
     * Checks of a string contains only alphabetic, numeric and space characters.
     * @param text The text to check.
     * @return Returns true if string contains only alphabetic, numeric and space characters, false otherwise.
     */
    public static boolean isAlphanumericAndSpace(String text) {
        return text.matches("[a-zA-Z0-9\\s]+");
    }

    /**
     * Checks of a string contains only alphabetic, numeric and underscore characters.
     * @param text The text to check.
     * @return Returns true if string contains only alphabetic, numeric and underscore characters, false otherwise.
     */
    public static boolean isAlphanumericAndUnderscore(String text) {
        return text.matches("[a-zA-Z0-9_]+");
    }

    /**
     * Replaces HTML (XML) tags (< and >) from an HTML-formatted text.
     * @param text The HTML-formatted text.
     * @return Returns a String.
     */
    public static String escapeHTMLSymbols(final String text) {
        if (text == null) return null;
        return text.replace("<", "&lt;").replace(">", "&gt;");
    }

}
