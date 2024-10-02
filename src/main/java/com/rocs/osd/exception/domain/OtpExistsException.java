package com.rocs.osd.exception.domain;

/**
 * This exception is thrown when an OTP (One-Time Password) already exists in the system.
 */
public class OtpExistsException extends Exception {
    public OtpExistsException(String message) {
        super(message);
    }
}
