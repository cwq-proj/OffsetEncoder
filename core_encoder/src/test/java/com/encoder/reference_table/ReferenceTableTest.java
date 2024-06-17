package com.encoder.reference_table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReferenceTableTest {
    private ReferenceTable refTable;

    @BeforeEach
    void setUp() {
        refTable = new ReferenceTable();
    }

    @Test
    void testConstructor() {
        assertEquals(0, refTable.getMinIndex());
        assertEquals(43, refTable.getMaxIndex());
    }

    @Test
    void testAdjustIndexInRange() {
        refTable.initializeMappingTable();

        // Test mappings for uppercase letters for both mappins
        for (int i = 'A', j = 0; i <= 'Z'; i++, j++) {
            // test index to char mapping
            assertTrue(refTable.containsRef((char) i));
            assertEquals(j, refTable.getIndexFromRef((char) i));
            // test reverse mapping
            assertEquals(i, refTable.getRefFromIndex(j));
        }

        // Test mappings for digits
        for (int i = '0', j = 26; i <= '9'; i++, j++) {
            assertTrue(refTable.containsRef((char) i));
            assertEquals(j, refTable.getIndexFromRef((char) i));
            // test reverse mapping
            assertEquals((char) i, refTable.getRefFromIndex(j));
        }

        // Test mappings for symbols
        for (int i = '(', j = 36; i <= '/'; i++, j++) {
            assertTrue(refTable.containsRef((char) i));
            assertEquals(j, refTable.getIndexFromRef((char) i));
            // test reverse mapping
            assertEquals((char) i, refTable.getRefFromIndex(j));
        }
    }

    @Test
    void testInitializeMappingTable() {
        assertEquals(39, refTable.adjustIndexInRange(-5)); // Adjusts to upper bound
        assertEquals(43, refTable.adjustIndexInRange(-1)); // Adjusts to upper bound
        assertEquals(0, refTable.adjustIndexInRange(44)); // Adjusts to lower bound
        assertEquals(6, refTable.adjustIndexInRange(50)); // Adjusts to lower bound
        assertEquals(20, refTable.adjustIndexInRange(20)); // Stays the same within bounds
        assertEquals(5, refTable.adjustIndexInRange(5)); // Stays the same within bounds
    }
}
