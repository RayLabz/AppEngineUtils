package com.raylabz.appengineutils.input;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides utility functions related to input validation.
 */
public class Validator {

    /**
     * A pattern containing a regular expression that matches email addresses.
     */
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     * The minimum allowed length of a username.
     */
    private final int minUsernameLength;

    /**
     * The maximum allowed length of a username.
     */
    private final int maxUsernameLength;

    /**
     * The minimum allowed password length.
     */
    private final int minPasswordLength;

    /**
     * The maximum allowed password length.
     */
    private final int maxPasswordLength;

    /**
     * Constructs a validator.
     * @param minUsernameLength The minimum allowed username length.
     * @param maxUsernameLength The maximum allowed username length.
     * @param minPasswordLength The minimum allowed password length.
     * @param maxPasswordLength The maximum allowed password length.
     */
    public Validator(int minUsernameLength, int maxUsernameLength, int minPasswordLength, int maxPasswordLength) {
        this.minUsernameLength = minUsernameLength;
        this.maxUsernameLength = maxUsernameLength;
        this.minPasswordLength = minPasswordLength;
        this.maxPasswordLength = maxPasswordLength;
    }

    /**
     * Validates a username.
     * @param username The username.
     * @return Returns true if the username is valid, false otherwise.
     */
    public final boolean validateUsername(final String username) {
        return TextUtils.isAlphanumericAndUnderscore(username) && username.length() <= maxUsernameLength && username.length() >= minUsernameLength;
    }

    /**
     * Validates a password.
     * @param password The password.
     * @return Returns true if the password is valid, false otherwise.
     */
    public final boolean validatePassword(final String password) {
        return password.length() >= minPasswordLength && password.length() <= maxPasswordLength;
    }

    /**
     * Validates an email address.
     * @param email The email address.
     * @return Returns true if the email address is valid, false otherwise.
     */
    public final boolean validateEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

}
