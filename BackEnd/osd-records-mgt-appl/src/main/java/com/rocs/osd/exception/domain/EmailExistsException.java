package com.rocs.osd.exception.domain;
/**
 * The exception thrown when an email address already exists
 */
public class EmailExistsException extends Exception {
    /**
     * Constructs a new EmailExistsException
     *
     * @param message message which is saved for later retrieval
     */
    public EmailExistsException(String message) {
        super(message);
    }
}
