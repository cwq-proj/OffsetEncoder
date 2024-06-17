package com.encoder.encoder;

// Custom exception class for invalid offsets
public class InvalidOffsetException extends RuntimeException {
    public InvalidOffsetException(String message) {
        super(message);
    }
}
