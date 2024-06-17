package com.encoder.encoder;

import com.encoder.reference_table.AbstractIndexCharTable;

// Abstract class for encoders with an offset
public abstract class AbstractOffsetEncoder<T> {
    private final AbstractIndexCharTable refTable;
    private T offset;

    public AbstractOffsetEncoder(AbstractIndexCharTable refTable) {
        this.refTable = refTable;
    }

    public AbstractOffsetEncoder(AbstractIndexCharTable refTable, T offset) {
        this.refTable = refTable;
        setOffset(offset);
    }

    public AbstractIndexCharTable getRefTable() {
        return refTable;
    }

    public T getOffset() {
        return offset;
    }

    public void setOffset(T offset) {
        this.offset = offset;
    }

    public abstract String encode(String plainText);

    public abstract String decode(String encodedText);
}
