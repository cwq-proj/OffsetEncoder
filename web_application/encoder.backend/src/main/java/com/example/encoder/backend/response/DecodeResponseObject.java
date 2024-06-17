package com.example.encoder.backend.response;

public class DecodeResponseObject {
    private String decodedString;

    public DecodeResponseObject() {
    }

    public DecodeResponseObject(String decodedString) {
        this.decodedString = decodedString;
    }

    public String getDecodedString() {
        return decodedString;
    }

    public void setDecodedString(String decodedString) {
        this.decodedString = decodedString;
    }
}
