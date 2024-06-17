package com.encoder;

import com.encoder.encoder.AbstractOffsetEncoder;
import com.encoder.encoder.InvalidOffsetException;
import com.encoder.encoder.OffsetEncoderFactory;
import com.encoder.reference_table.AbstractIndexCharTable;
import com.encoder.reference_table.IndexCharTableFactory;

public class Main {
    public static void main(String[] args) {
        // To test encoding/decoding update the variables here
        // Test encoding
        char offset = 'B';
        String plainText = "HELLO WORLD";
        // Test decoding
        String encodedString = "FC/GGJ RJMG.";


        AbstractIndexCharTable table = IndexCharTableFactory.createReferenceTable();
        AbstractOffsetEncoder<Character> encoder = OffsetEncoderFactory.createOffsetEncoder(table, offset);
        
        try{
            String result = encoder.encode(plainText);
            System.out.println(result);
        } catch (InvalidOffsetException e){
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }

        try{
            System.out.println(encoder.decode(encodedString));
        } catch(InvalidOffsetException e){
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }
}