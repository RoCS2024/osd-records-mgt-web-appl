package com.rocs.osd.exception.domain;

/**
 * Exception thrown when a username already exists
 */
public class UsernameExistsException extends Exception {
    /**
     * Constructs a new UsernameExistsException
     *
     * @param message message which is saved for later retrieval
     */
    public UsernameExistsException(String message) {
        super(message);
    }
}
