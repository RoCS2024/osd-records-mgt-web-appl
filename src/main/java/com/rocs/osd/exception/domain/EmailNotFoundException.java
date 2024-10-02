package com.rocs.osd.exception.domain;

/**
 * This exception is thrown when an email address is not found in the system.
 */
public class EmailNotFoundException extends Exception{
    public EmailNotFoundException(String message) {
        super(message);
    }
}
