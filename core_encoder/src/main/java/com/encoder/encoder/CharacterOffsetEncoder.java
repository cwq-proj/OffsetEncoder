package com.encoder.encoder;

import com.encoder.reference_table.AbstractIndexCharTable;

// Concrete implementaion of AbstractOffsetEncoder
public class CharacterOffsetEncoder extends AbstractOffsetEncoder<Character> {
    public CharacterOffsetEncoder(AbstractIndexCharTable refTable) {
        super(refTable);
    }

    public CharacterOffsetEncoder(AbstractIndexCharTable refTable, Character offset) {
        super(refTable);
        this.setOffset(offset);
    }

    @Override
    public void setOffset(Character offset) {
        if (getRefTable().containsRef(offset)) {
            super.setOffset(offset);
        }
    }

    @Override
    public String encode(String plainText) {
        validatePlainText(plainText);
        return performEncoding(plainText);
    }

    @Override
    public String decode(String encodedText) {
        validateEncodedText(encodedText);
        return performDecoding(encodedText);
    }

    // Validates plaintext provided
    private void validatePlainText(String plainText) {
        if (plainText == null || plainText.isEmpty()) // validate plain text
            throw new IllegalArgumentException("Invalid input plain text!");
        if (getOffset() == null || !getRefTable().containsRef(getOffset())) // validate offset character
            throw new InvalidOffsetException("Invalid offset provided!");
    }

    // Encodes the plaintext using the offset character
    private String performEncoding(String plainText) {
        StringBuilder encodedText = new StringBuilder();
        encodedText.append(getOffset()); // Append the offset character
        // Iterate through the string and encode each character
        for (int i = 0; i < plainText.length(); i++) {
            char curr = plainText.charAt(i);
            if (getRefTable().containsRef(curr)) {
                // Get the index and adjust the index if it is out of range
                int offsetIndex = getRefTable().getIndexFromRef(curr) -
                        getRefTable().getIndexFromRef(getOffset());
                offsetIndex = getRefTable().adjustIndexInRange(offsetIndex);
                char temp = getRefTable().getRefFromIndex(offsetIndex);
                if (getRefTable().containsRef(temp)) // ensure that ref exists before appending
                    encodedText.append(temp);
                else
                    encodedText.append(curr);
            } else
                encodedText.append(curr);
        }
        return encodedText.toString();
    }

    // Validates the encoded string
    private void validateEncodedText(String encodedText) {
        if (encodedText == null || encodedText.length() <= 1) // validate encoded text
            throw new IllegalArgumentException("Invalid encoded text!");
        // Extract and validate the offset character
        char offset = encodedText.charAt(0);
        if (!getRefTable().containsRef(offset))
            throw new InvalidOffsetException("Invalid offset character for encoded text!");
    }

    // Decodes the string using the offset taken from the first character
    private String performDecoding(String encodedText) {
        StringBuilder decodedText = new StringBuilder();
        // get index from the offset character
        int firstCharIndex = getRefTable().getIndexFromRef(encodedText.charAt(0));
        // iterate through the string, skip the first offset and decode each character
        for (int i = 1; i < encodedText.length(); i++) {
            char curr = encodedText.charAt(i);
            if (getRefTable().containsRef(curr)) {
                int offsetIndex = getRefTable().getIndexFromRef(curr) +
                        firstCharIndex;
                offsetIndex = getRefTable().adjustIndexInRange(offsetIndex);
                char temp = getRefTable().getRefFromIndex(offsetIndex);
                if (getRefTable().containsRef(temp)) // ensure that ref exists before appending
                    decodedText.append(temp);
                else
                    decodedText.append(curr);
            } else
                decodedText.append(curr);
        }
        return decodedText.toString();
    }
}
