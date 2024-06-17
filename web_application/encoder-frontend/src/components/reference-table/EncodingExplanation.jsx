import React from "react";

const EncodingExplanation = () => {
  return (
    <div className="p-6 text-left">
      <h3 className="text-xl text-gray-950 font-bold mb-2">Usage</h3>
      <ul className="list-disc text-gray-800 list-inside mb-4">
        <li>This encoder transforms plain text into an obfuscated string.</li>
        <li>
          The reference table above shows the supported characters for encoding.
        </li>
      </ul>

      <h3 className="text-xl text-gray-950 font-semibold mb-2">How It Works</h3>
      <div className="grid grid-cols-2 gap-4">
        <div>
          <h4 className="text-lg text-gray-900 font-semibold">Encoding</h4>
          <ul className="list-disc text-gray-800 list-inside mb-4">
            <li>Choose an offset character from the reference table.</li>
            <li>
              The offset character will be used to shift references by the index of the offset.
            </li>
            <li>
              Any character not in the reference table remains unchanged.
            </li>
            <li>
              The offset character will be added as the first character of the encoded message.
            </li>
          </ul>
        </div>
        <div>
          <h4 className="text-lg text-gray-900 font-semibold">Decoding</h4>
          <ul className="list-disc text-gray-800 list-inside mb-4">
            <li>The first character of the encoded string will be taken as the offset character.</li>
            <li>
              The offset character will be used to shift the references backward to obtain the original plaintext.
            </li>
            <li>
              Any character not in the reference table remains unchanged.
            </li>
          </ul>
        </div>
      </div>

      <h3 className="text-xl text-gray-950 font-semibold mb-2">Encoding/Decoding Examples</h3>
      <ul className="list-disc text-gray-800 list-inside mb-4">
        <li>
          <strong>Offset Character:</strong> B (index 1)
        </li>
        <li>
          <strong>Plaintext:</strong> HELLO WORLD
        </li>
        <li>
          <strong>Encoded Text:</strong> BGDKKN VNQKC
        </li>
      </ul>
      <div className="text-gray-800">
      <p>
        In this example, "HELLO WORLD" is encoded as "BGDKKN VNQKC" by shifting
        each character based on the index of the offset character.
      </p>
      <p>
        Decoding is the reverse process, where the first character of the
        encoded text is the offset character, and each character is shifted back
        accordingly.
      </p>
      </div>
    </div>
  );
};

export default EncodingExplanation;
