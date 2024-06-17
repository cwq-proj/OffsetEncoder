package com.encoder.reference_table;

import java.util.HashMap;
import java.util.Map;

// Abstract base class for reference tables with integer index to character mappings
public abstract class AbstractIndexCharTable {
    private int minIndex; // min index in the reference table
    private int maxIndex; // max index in the reference table
    private final Map<Integer, Character> indexToCharMap = new HashMap<>(); // Maps index to reference table character
    private final Map<Character, Integer> charToIndexMap = new HashMap<>(); // Maps reference table character to index
    private final int DEFAULT_INDEX_VALUE = 0; // Default index to return if not found in reference table
    private final char DEFAULT_REFERENCE_VALUE = '\0'; // Default value to return if not found in reference table

    // Set bounds of reference table and create table mappings
    public AbstractIndexCharTable(int minIndex, int maxIndex) {
        this.minIndex = minIndex;
        this.maxIndex = maxIndex;
        initializeMappingTable();
    }

    public int getMinIndex() {
        return minIndex;
    }

    public int getMaxIndex() {
        return maxIndex;
    }

    // Adds a mapping from index to reference character
    public void addMapping(int idx, char ref) {
        indexToCharMap.put(idx, ref);
    }

    // Adds a reverse mapping from reference character to index
    public void addReverseMapping(char ref, int idx) {
        charToIndexMap.put(ref, idx);
    }

    // Gets the reference character from table using the corresponding index
    public char getRefFromIndex(int idx) {
        return indexToCharMap.getOrDefault(idx, DEFAULT_REFERENCE_VALUE);
    }

    // Gets the index from the reference table using the corresponding character
    public int getIndexFromRef(char ref) {
        return charToIndexMap.getOrDefault(ref, DEFAULT_INDEX_VALUE);
    }

    // Checks if the character is in reference table
    public boolean containsRef(char ref) {
        return charToIndexMap.containsKey(ref);
    }

    public abstract void initializeMappingTable();

    // Adjusts an index to stay within bounds in a circular manner
    public abstract int adjustIndexInRange(int idx);
}
