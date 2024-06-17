package com.example.encoder.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encoder.encoder.AbstractOffsetEncoder;
import com.encoder.encoder.InvalidOffsetException;
import com.encoder.encoder.OffsetEncoderFactory;
import com.encoder.reference_table.AbstractIndexCharTable;

@Service
public class EncoderServiceImpl implements EncoderService{
        private final AbstractOffsetEncoder<Character> encoder;

    @Autowired
    public EncoderServiceImpl(AbstractIndexCharTable refTable) {
        this.encoder = OffsetEncoderFactory.createOffsetEncoder(refTable);
    }

    @Override
    public String encodeWithOffset(char offset, String plainText) {
        if (!encoder.getRefTable().containsRef(offset)) {
            throw new InvalidOffsetException("Invalid Offset!");
        }
        encoder.setOffset(offset);
        return encoder.encode(plainText);
    }

    @Override
    public String decode(String encodedText) {
        return encoder.decode(encodedText);
    }
}
