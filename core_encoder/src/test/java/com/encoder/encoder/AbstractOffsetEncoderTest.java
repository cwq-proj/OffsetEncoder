package com.encoder.encoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.encoder.reference_table.AbstractIndexCharTable;

public class AbstractOffsetEncoderTest {
    private static class TestEncoder extends AbstractOffsetEncoder<Character> {
        public TestEncoder(AbstractIndexCharTable refTable) {
            super(refTable);
        }

        public TestEncoder(AbstractIndexCharTable refTable, Character offset) {
            super(refTable, offset);
        }

        // Tested in concrete implementation of subclasses
        @Override
        public String encode(String plainText) {
            return "";
        }

        // Tested in concrete implementation of subclasses
        @Override
        public String decode(String encodedText) {
            return "";
        }
    }

    private AbstractIndexCharTable refTable;
    private AbstractOffsetEncoder<Character> encoder;

    @BeforeEach
    void setUp() {
        refTable = Mockito.mock(AbstractIndexCharTable.class);
    }

    @Test
    void testConstructor() {
        encoder = new TestEncoder(refTable);
        assertNotNull(encoder);
        encoder = new TestEncoder(refTable, 'c');
        assertNotNull(encoder);
    }

    @Test
    void testGetOffset() {
        encoder = new TestEncoder(refTable);
        // getter should retrieve null
        assertEquals(null, encoder.getOffset());

        encoder = new TestEncoder(refTable, 'c');
        // getter should retrieve 'c'
        assertEquals('c', encoder.getOffset());
    }

    @Test
    void testSetOffset() {
        encoder = new TestEncoder(refTable);
        encoder.setOffset('a');
        // assert equals
        assertEquals('a', encoder.getOffset());

        encoder = new TestEncoder(refTable, 'c');
        encoder.setOffset('d');
        // assert equals
        assertEquals('d', encoder.getOffset());
    }

    @Test
    void testGetReferenceTable() {
        encoder = new TestEncoder(refTable);
        assertEquals(refTable, encoder.getRefTable());
    }
}
