package com.rocs.osd.exception.domain;

/**
 * This exception is thrown when a username already exists in the system.
 */
public class UsernameExistsException extends Exception {
    public UsernameExistsException(String message) {
        super(message);
    }
}
