package com.example.encoder.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.encoder.encoder.InvalidOffsetException;
import com.encoder.reference_table.AbstractIndexCharTable;
import com.encoder.reference_table.ReferenceTable;

public class EncoderServiceImplTest {
    @Test
    public void testEncodeWithValidOffset() {
        AbstractIndexCharTable table = new ReferenceTable();
        EncoderService encoderService = new EncoderServiceImpl(table);
        // Given
        String plainText = "Hello";
        char offset = 'A';
        String encodedString = "AHello";
        String result = encoderService.encodeWithOffset(offset, plainText);
        assertEquals(encodedString, result);
    }

    @Test
    public void testEncodeWithInvalidOffset() {
        AbstractIndexCharTable table = new ReferenceTable();
        EncoderService encoderService = new EncoderServiceImpl(table);
        String plainText = "Hello";
        char offset = 'a';
        assertThrows(InvalidOffsetException.class, () -> {
            encoderService.encodeWithOffset(offset, plainText);
        });
    }

    @Test
    public void testDecodeWithValidOffset() {
        AbstractIndexCharTable table = new ReferenceTable();
        EncoderService encoderService = new EncoderServiceImpl(table);
        String encodedText = "BGDKKN VNQKC";
        assertEquals("HELLO WORLD", encoderService.decode(encodedText));
    }

    @Test
    public void testDecodeWithInvalidOffset() {
        AbstractIndexCharTable table = new ReferenceTable();
        EncoderService encoderService = new EncoderServiceImpl(table);
        String encodedText = "aGDKKN VNQKC";
        assertThrows(InvalidOffsetException.class, () -> {
            encoderService.decode(encodedText);
        });
    }
}
