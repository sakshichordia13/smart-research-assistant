package com.sakshi.sra.smartresearchassistant.exception;

/**
 * Thrown when a requested resource does not exist.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
