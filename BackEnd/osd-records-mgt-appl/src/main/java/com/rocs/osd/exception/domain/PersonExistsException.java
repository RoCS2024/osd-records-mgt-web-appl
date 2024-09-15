package com.rocs.osd.exception.domain;
/**
 * Exception thrown when a person already exists
 */
public class PersonExistsException extends Exception {
    /**
     * Constructs a new PersonExistsException
     *
     * @param message message which is saved for later retrieval
     */
    public PersonExistsException(String message) {
        super(message);
    }
}
