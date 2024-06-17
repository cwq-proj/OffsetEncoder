package com.example.encoder.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.encoder.reference_table.AbstractIndexCharTable;
import com.encoder.reference_table.ReferenceTable;

@Configuration
public class EncoderConfig {
    @Bean
    public AbstractIndexCharTable referenceTable() {
        return new ReferenceTable();
    }
}
