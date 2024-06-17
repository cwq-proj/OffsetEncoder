package com.encoder.encoder;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.encoder.reference_table.AbstractIndexCharTable;
import com.encoder.reference_table.ReferenceTable;

public class CharacterOffsetEncoderTest {
    private AbstractIndexCharTable refTable;
    private CharacterOffsetEncoder encoder;

    @BeforeEach
    void setUp() {
        refTable = Mockito.mock(AbstractIndexCharTable.class);
    }

    @Test
    void testConstructorWithoutOffset() {
        encoder = new CharacterOffsetEncoder(refTable);
        assertNotNull(encoder);
    }

    @Test
    void testConstructorWithoutOffsetAndOffsetIsNull() {
        encoder = new CharacterOffsetEncoder(refTable);
        assertNull(encoder.getOffset());
    }

    @Test
    void testConstructorWithValidOffset() {
        char offset = 'B';
        encoder = new CharacterOffsetEncoder(refTable, offset);
        assertNotNull(encoder);
    }

    @Test
    void testConstructorWithInvalidOffset() {
        char offset = 'b';
        encoder = new CharacterOffsetEncoder(refTable, offset);
        assertNotNull(encoder);
    }

    @Test
    void testGetOffsetWithValidOffset() {
        char offset = 'B';
        when(refTable.containsRef(offset)).thenReturn(true);
        encoder = new CharacterOffsetEncoder(refTable, offset);
        assertEquals(offset, encoder.getOffset());
    }

    @Test
    void testGetOffsetWithInvalidOffset() {
        char offset = 'b';
        when(refTable.containsRef(offset)).thenReturn(false);
        encoder = new CharacterOffsetEncoder(refTable, offset);
        assertNull(encoder.getOffset());
    }

    @Test
    void testSetValidOffset() {
        char offset = 'B';
        when(refTable.containsRef(offset)).thenReturn(true);
        encoder = new CharacterOffsetEncoder(refTable);
        encoder.setOffset(offset);
        assertEquals(offset, encoder.getOffset());
    }

    @Test
    void testSetOffsetInvalid() {
        char offset = 'b';
        when(refTable.containsRef(offset)).thenReturn(true);
        encoder = new CharacterOffsetEncoder(refTable);
        encoder.setOffset(offset);
        assertEquals(offset, encoder.getOffset());
    }

    @Test
    void testValidatePlainTextWithInvalidInput() {
        char offset = 'A';
        encoder = new CharacterOffsetEncoder(refTable, offset);
        assertThrows(IllegalArgumentException.class, () -> encoder.encode(null));
        assertThrows(IllegalArgumentException.class, () -> encoder.encode(""));
    }

    @Test
    void testValidatePlainTextWithValidOffset() {
        char offset = 'A';
        when(refTable.containsRef(offset)).thenReturn(true);
        encoder = new CharacterOffsetEncoder(refTable, offset);
        assertDoesNotThrow(() -> encoder.encode("ValidText"));
    }

    @Test
    void testValidatePlainTextWithInvalidOffset() {
        char offset = 'b';
        when(refTable.containsRef(offset)).thenReturn(false);
        encoder = new CharacterOffsetEncoder(refTable, offset);
        assertThrows(InvalidOffsetException.class, () -> encoder.encode("ValidText"));
    }

    @Test
    void testEncode() {
        AbstractIndexCharTable table = new ReferenceTable();
        encoder = new CharacterOffsetEncoder(table, 'B');
        assertEquals("BGDKKN VNQKC", encoder.encode("HELLO WORLD"));

        encoder = new CharacterOffsetEncoder(table, 'F');
        assertEquals("FC/GGJ RJMG.", encoder.encode("HELLO WORLD"));

        encoder = new CharacterOffsetEncoder(table, 'A');
        assertEquals("AHELLO WORLD", encoder.encode("HELLO WORLD"));
    }

    @Test
    void testValidateEncodedTextWithInvalidInput() {
        encoder = new CharacterOffsetEncoder(refTable);
        assertThrows(IllegalArgumentException.class, () -> encoder.decode(null));
        assertThrows(IllegalArgumentException.class, () -> encoder.decode(""));
    }

    @Test
    void testValidateEncodedTextWithValidOffset() {
        AbstractIndexCharTable table = new ReferenceTable();
        encoder = new CharacterOffsetEncoder(table);
        assertDoesNotThrow(() -> encoder.decode("BGDKKN VNQKC"));
        assertDoesNotThrow(() -> encoder.decode("FC/GGJ RJMG."));
        assertDoesNotThrow(() -> encoder.decode("HELLO WORLD"));
    }

    @Test
    void testValidateEncodedTextWithInvalidOffset() {
        AbstractIndexCharTable table = new ReferenceTable();
        encoder = new CharacterOffsetEncoder(table);
        assertThrows(InvalidOffsetException.class, () -> encoder.decode("aa"));
        assertThrows(InvalidOffsetException.class, () -> encoder.decode("!a"));
        assertThrows(InvalidOffsetException.class, () -> encoder.decode("bHELLO WORLD"));
        assertThrows(InvalidOffsetException.class, () -> encoder.decode("bGDKKN VNQKC"));
        assertThrows(InvalidOffsetException.class, () -> encoder.decode("fC/GGJ RJMG."));
    }

    @Test
    void testDecode() {
        AbstractIndexCharTable table = new ReferenceTable();
        encoder = new CharacterOffsetEncoder(table, 'B');
        assertEquals("HELLO WORLD", encoder.decode("BGDKKN VNQKC"));

        encoder = new CharacterOffsetEncoder(table, 'F');
        assertEquals("HELLO WORLD", encoder.decode("FC/GGJ RJMG."));

        encoder = new CharacterOffsetEncoder(table, 'A');
        assertEquals("HELLO WORLD", encoder.decode("AHELLO WORLD"));

        encoder = new CharacterOffsetEncoder(table);
        assertEquals("HELLO WORLD", encoder.decode("BGDKKN VNQKC"));
        assertEquals("HELLO WORLD", encoder.decode("FC/GGJ RJMG."));
        assertEquals("HELLO WORLD", encoder.decode("AHELLO WORLD"));
    }
}
