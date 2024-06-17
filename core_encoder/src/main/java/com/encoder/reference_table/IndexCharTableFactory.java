package com.encoder.reference_table;

// Factory class to create reference tables
public class IndexCharTableFactory {
    public static AbstractIndexCharTable createReferenceTable() {
        return new ReferenceTable();
    }
}