package com.rocs.osd.exception.domain;

/**
 * This exception is thrown when a username is not found in the system.
 */
public class UsernameNotFoundException extends Exception {
    public UsernameNotFoundException() {
        super();
    }

    public UsernameNotFoundException(String message) {
        super(message);
    }

    public UsernameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameNotFoundException(Throwable cause) {
        super(cause);
    }
}
