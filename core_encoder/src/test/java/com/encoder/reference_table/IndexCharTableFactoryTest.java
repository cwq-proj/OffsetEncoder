package com.encoder.reference_table;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IndexCharTableFactoryTest {
    @Test
    void testCreateReferenceTable() {
        AbstractIndexCharTable table = IndexCharTableFactory.createReferenceTable();
        assertTrue(table instanceof ReferenceTable);
    }
}
