package com.rocs.osd.exception.domain;
/**
 * The exception thrown when an OTP (One-Time Password) already exists
 */
public class OtpExistsException extends Exception {
    /**
     * Constructs a new OtpExistsException
     *
     * @param message message which is saved for later retrieval
     */
    public OtpExistsException(String message) {
        super(message);
    }
}
