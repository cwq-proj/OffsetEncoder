package com.encoder.encoder;

import com.encoder.reference_table.AbstractIndexCharTable;

// Factory class to create offset encoders
public class OffsetEncoderFactory {
    public static AbstractOffsetEncoder<Character> createOffsetEncoder(AbstractIndexCharTable refTable) {
        return new CharacterOffsetEncoder(refTable);
    };

    public static AbstractOffsetEncoder<Character> createOffsetEncoder(AbstractIndexCharTable refTable,
            Character offset) {
        return new CharacterOffsetEncoder(refTable, offset);
    };
}
