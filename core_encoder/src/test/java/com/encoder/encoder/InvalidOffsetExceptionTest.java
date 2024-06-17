package com.encoder.encoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class InvalidOffsetExceptionTest {
    @Test
    void testExceptionType() {
        assertThrows(InvalidOffsetException.class, () -> {
            throw new InvalidOffsetException("Invalid offset value");
        });
    }

    @Test
    void testExceptionMessage() {
        String message = "Invalid offset value";
        InvalidOffsetException exception = assertThrows(InvalidOffsetException.class, () -> {
            throw new InvalidOffsetException(message);
        });
        assertEquals(message, exception.getMessage());
    }
}
