package com.encoder.encoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.encoder.reference_table.AbstractIndexCharTable;

public class OffsetEncoderFactoryTest {
    private AbstractIndexCharTable refTable;

    @BeforeEach
    void setUp() {
        refTable = mock(AbstractIndexCharTable.class);
    }

    @Test
    void testCreateOffsetEncoderWithoutOffset() {
        AbstractOffsetEncoder<Character> encoder = OffsetEncoderFactory.createOffsetEncoder(refTable);
        assertEquals(CharacterOffsetEncoder.class, encoder.getClass());
    }

    @Test
    void testCreateOffsetEncoderWithOffset() {
        char offset = 'A';
        AbstractOffsetEncoder<Character> encoder = OffsetEncoderFactory.createOffsetEncoder(refTable, offset);
        assertEquals(CharacterOffsetEncoder.class, encoder.getClass());
    }
}
