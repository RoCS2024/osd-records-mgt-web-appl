package com.rocs.osd.exception.domain;

/**
 * This exception is thrown when a person already exists in the system.
 */
public class PersonExistsException extends Exception {
    public PersonExistsException(String message) {
        super(message);
    }
}
