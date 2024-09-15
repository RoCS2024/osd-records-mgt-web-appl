package com.rocs.osd.exception.domain;
/**
 * The exception thrown when an email address does not exist
 */
public class EmailNotFoundException extends Exception{
    /**
     * Constructs a new EmailNotFoundException with the specified detail message.
     *
     * @param message message which is saved for later retrieval
     * */
    public EmailNotFoundException(String message) {
        super(message);
    }
}
