package com.rocs.osd.exception.domain;

/**
 * This exception is thrown when an email address already exists in the system.
 */
public class EmailExistsException extends Exception {
    public EmailExistsException(String message) {
        super(message);
    }
}
