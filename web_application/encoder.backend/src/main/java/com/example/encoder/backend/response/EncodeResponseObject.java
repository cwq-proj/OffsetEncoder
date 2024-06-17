package com.example.encoder.backend.response;

public class EncodeResponseObject {
    private String encodedString;

    public EncodeResponseObject() {
    }

    public EncodeResponseObject(String encodedString) {
        this.encodedString = encodedString;
    }

    public String getEncodedString() {
        return encodedString;
    }

    public void setEncodedString(String encodedString) {
        this.encodedString = encodedString;
    }
}
