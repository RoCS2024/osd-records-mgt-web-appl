package com.rocs.osd.exception.domain;

/**
 * This exception is thrown when a user is not found in the system.
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
