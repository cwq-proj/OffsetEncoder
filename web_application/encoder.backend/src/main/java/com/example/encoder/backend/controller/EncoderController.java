package com.example.encoder.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.encoder.encoder.InvalidOffsetException;
import com.example.encoder.backend.response.DecodeResponseObject;
import com.example.encoder.backend.response.EncodeResponseObject;
import com.example.encoder.backend.service.EncoderService;

@RestController
@RequestMapping("/api")
public class EncoderController {
    private final EncoderService encoderServiceImpl;

    @Autowired
    public EncoderController(EncoderService encoderServiceImpl) {
        this.encoderServiceImpl = encoderServiceImpl;
    }

    @GetMapping("/encode")
    @ResponseStatus(HttpStatus.OK)
    public EncodeResponseObject encode(
            @RequestParam(name = "offset", required = true) Character offset,
            @RequestParam(name = "plaintext", required = true) String plaintext) {
        try {
            String encodedString = encoderServiceImpl.encodeWithOffset(offset, plaintext);
            return new EncodeResponseObject(encodedString);

        } catch (IllegalArgumentException e) {
            String errorMessage = "Invalid input text provided, make sure it is not empty!";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
        } catch (InvalidOffsetException e) {
            String errorMessage = String.format("Invalid offset character '%s'", offset);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
        }
    }

    @GetMapping("/decode")
    @ResponseStatus(HttpStatus.OK)
    public DecodeResponseObject decode(@RequestParam(name = "encodedString", required = true) String encodedText) {
        try {
            String decodedString = encoderServiceImpl.decode(encodedText);
            return new DecodeResponseObject(decodedString);
        } catch (IllegalArgumentException e) {
            String errorMessage = String.format("Invalid input text provided, make sure it is an encoded string!");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
        } catch (InvalidOffsetException e) {
            String errorMessage = String.format("Invalid offset character provided in input!");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
        }
    }
}
