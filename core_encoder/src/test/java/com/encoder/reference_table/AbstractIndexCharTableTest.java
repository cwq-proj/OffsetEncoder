package com.encoder.reference_table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AbstractIndexCharTableTest {
    private static class TestIndexCharTable extends AbstractIndexCharTable {
        public TestIndexCharTable(int minIndex, int maxIndex) {
            super(minIndex, maxIndex);
        }

        // To be tested in concrete subclasses
        @Override
        public void initializeMappingTable() {
        }

        // To be tested in concrete subclasses
        @Override
        public int adjustIndexInRange(int idx) {
            return 0;
        }
    }

    private AbstractIndexCharTable refTable;
    private final int DEFAULT_INDEX_VALUE = 0; // Default index to return if not found in reference table
    private final char DEFAULT_REFERENCE_VALUE = '\0'; // Default value to return if not found in reference table

    @BeforeEach
    void setUp() {
        refTable = new TestIndexCharTable(1, 5);
    }

    @Test
    void testGetMinIndex() {
        int minIndex = 1;
        assertEquals(minIndex, refTable.getMinIndex());
    }

    @Test
    void testGetMaxIndex() {
        int maxIndex = 5;
        assertEquals(maxIndex, refTable.getMaxIndex());
    }

    @Test
    void testAddMapping() {
        int idx = 2;
        char ref = 'B';
        refTable.addMapping(idx, ref);
        assertEquals(ref, refTable.getRefFromIndex(idx));
    }

    @Test
    void testAddReverseMapping() {
        int idx = 2;
        char ref = 'B';
        refTable.addReverseMapping(ref, idx);
        assertEquals(idx, refTable.getIndexFromRef(ref));
    }

    @Test
    void testGetRefFromIndex() {
        int idx = 2;
        char ref = 'B';
        refTable.addMapping(idx, ref);
        assertEquals(ref, refTable.getRefFromIndex(idx));
        // test return of default value
        assertEquals(DEFAULT_REFERENCE_VALUE, refTable.getRefFromIndex(0));
    }

    @Test
    void testGetIndexFromRef() {
        int idx = 2;
        char ref = 'B';
        refTable.addReverseMapping(ref, idx);
        assertEquals(idx, refTable.getIndexFromRef(ref));
        // test return of default value
        assertEquals(DEFAULT_INDEX_VALUE, refTable.getIndexFromRef('@'));
    }

    @Test
    void testContainsRef() {
        int idx = 2;
        char ref = 'B';
        refTable.addReverseMapping(ref, idx);
        assertTrue(refTable.containsRef(ref));
    }
}
