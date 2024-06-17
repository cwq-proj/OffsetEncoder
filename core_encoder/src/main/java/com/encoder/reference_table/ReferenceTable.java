package com.encoder.reference_table;

// Concrete implementation of the reference table for the specific character set
public class ReferenceTable extends AbstractIndexCharTable {
    // Define min and max index for the reference table
    private static final int LOWER_BOUND = 0;
    private static final int UPPER_BOUND = 43;
    // Define constants for the characters and the ranges to be added to the table
    private static final int ASCII_UPPER_CASE_START = 'A';
    private static final int ASCII_UPPER_CASE_END = 'Z';
    private static final int ASCII_DIGIT_START = '0';
    private static final int ASCII_DIGIT_END = '9';
    private static final int ASCII_SYMBOL_START = '(';
    private static final int ASCII_SYMBOL_END = '/';
    private final int CIRCULAR_ADJUSTMENT_OFFSET = 1; // Adjust index value if out of bounds

    // Constructor to initialize the bounds and the mapping table
    public ReferenceTable() {
        super(LOWER_BOUND, UPPER_BOUND);
    }

    // Initialize the mapping table with predefined character ranges
    @Override
    public void initializeMappingTable() {
        int counter = LOWER_BOUND;
        // Add uppercase letters to both hashmaps
        for (int i = ASCII_UPPER_CASE_START; i <= ASCII_UPPER_CASE_END; i++) {
            addMapping(counter, (char) i);
            addReverseMapping((char) i, counter);
            counter++;
        }
        // Add digits to both hashmaps
        for (int i = ASCII_DIGIT_START; i <= ASCII_DIGIT_END; i++) {
            addMapping(counter, (char) i);
            addReverseMapping((char) i, counter);
            counter++;
        }
        // Add the other operators to both hashmaps
        for (int i = ASCII_SYMBOL_START; i <= ASCII_SYMBOL_END; i++) {
            addMapping(counter, (char) i);
            addReverseMapping((char) i, counter);
            counter++;
        }
    }

    // Adjusts the index in the table if it goes out of bounds
    @Override
    public int adjustIndexInRange(int idx) {
        if (idx < getMinIndex() || idx > getMaxIndex()) {
            return idx < getMinIndex() ? idx + getMaxIndex() + CIRCULAR_ADJUSTMENT_OFFSET
                    : idx - getMaxIndex() - CIRCULAR_ADJUSTMENT_OFFSET;
        } else {
            return idx;
        }
    }
}
