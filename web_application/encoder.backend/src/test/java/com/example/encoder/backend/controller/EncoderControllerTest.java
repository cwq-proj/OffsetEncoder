package com.example.encoder.backend.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.encoder.backend.service.EncoderService;

@WebMvcTest(controllers = EncoderController.class)
@ExtendWith(MockitoExtension.class)
public class EncoderControllerTest {
    @MockBean
    private EncoderService encoderService;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private EncoderController encoderController;

    @Test
    public void testEncodeSuccess() throws Exception {
        String plaintext = "Hello";
        Character offset = 'A';

        mockMvc.perform(get("/api/encode")
                .param("offset", offset.toString())
                .param("plaintext", plaintext)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testEncodeEmptyOffset() throws Exception {
        String plaintext = "HELLO WORLD";
        mockMvc.perform(get("/api/encode")
                .param("plaintext", plaintext)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testEncodeEmptyPlainText() throws Exception {
        Character offset = 'A';
        mockMvc.perform(get("/api/encode")
                .param("offset", offset.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDecode() throws Exception {
        String encodedString = "BGDKKN VNQKC";
        mockMvc.perform(get("/api/decode")
                .param("encodedString", encodedString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
