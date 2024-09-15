package com.rocs.osd.exception.domain;
/**
 * Exception thrown when a username is not found
 */
public class UsernameNotFoundException extends Exception {

    /**
     * Constructs a new UsernameNotFoundException
     */
    public UsernameNotFoundException() {
        super();
    }
    /**
     * Constructs a new UsernameNotFoundException
     *
     * @param message the detail message which is saved for later retrieval
     */
    public UsernameNotFoundException(String message) {
        super(message);
    }
    /**
     * Constructs a new UsernameNotFoundException
     *
     * @param message detail message which is saved for later retrieval
     */
    public UsernameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     * Constructs a new UsernameNotFoundException
     *
     * @param cause cause (which is saved for later retrieval
     */
    public UsernameNotFoundException(Throwable cause) {
        super(cause);
    }
}
