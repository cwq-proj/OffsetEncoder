package com.example.encoder.backend.service;

public interface EncoderService {
    String decode(String encodedText);
    String encodeWithOffset(char offset, String plainText);
}
