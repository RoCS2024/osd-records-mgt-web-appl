package com.rocs.osd.exception.domain;
/**
 * Exception thrown when a user is not found
 */
public class UserNotFoundException extends Exception {
    /**
     * Constructs a new UserNotFoundException
     * @param message the detail message which is saved for later retrieval
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
