import React, { useState } from "react";

const Decode = ({ reverseMappingTable }) => {
  const [encodedText, setEncodedText] = useState("");
  const [inputError, setInputError] = useState("");
  const [decodeError, setDecodeError] = useState("");
  const [decodedText, setDecodedText] = useState("");

  const handleTextChange = (text) => {
    setEncodedText(text);
  };

  const validateInputs = () => {
    // Reset errors
    setInputError("");
    setDecodeError("");

    // Validate input
    if (!encodedText) {
      setInputError("Please provide an input!");
      setDecodedText("");
      return false;
    } else if (encodedText.length < 2) {
      setInputError("Invalid input! Please provide more than 2 characters.");
      setDecodedText("");
      return false;
    }

    // Check first character
    const firstCharacter = encodedText.charAt(0);
    if (!reverseMappingTable.has(firstCharacter)) {
      setInputError(
        "Invalid offset character in the first character position."
      );
      setDecodedText("");
      return false;
    }

    return true;
  };

  const handleDecode = async () => {
    // If input is not valid, return
    if (!validateInputs()) return;

    const DECODE_BASE_ENDPOINT = "http://localhost:8080/api/decode";
    const encodedInputText = encodeURIComponent(encodedText);
    const ENDPOINT = `${DECODE_BASE_ENDPOINT}?encodedString=${encodedInputText}`;

    const fetchDecodedText = async () => {
      try {
        const response = await fetch(ENDPOINT);
        if (response.status >= 200 && response.status < 300) {
          const result = await response.json();
          setDecodedText(result.decodedString);
        } else if (response.status === 400) {
          const errorMessage = await response.json(); // Extract error message from response
          setDecodeError(errorMessage.message);
          setDecodedText("");
        } else {
          throw new Error("Failed to decode. Please try again.");
        }
      } catch (error) {
        setDecodeError("Error decoding: " + error.message);
        setDecodedText("");
      }
    };

    fetchDecodedText();
  };

  return (
    <div>
      <h3 className="text-2xl text-black font-semibold mb-4">Decoding Tool</h3>

      <table className="border-collapse w-full">
        <tbody>
          <tr>
            <td
              className="py-2 px-4 whitespace-nowrap text-left text-gray-800"
              style={{ width: "25%" }}
            >
              Encoded Text:
            </td>
            <td className="py-2 px-4 text-gray-800">
              <input
                type="text"
                className="p-2 border border-gray-300 rounded-md w-full"
                value={encodedText}
                placeholder="Please enter the encoded text"
                onChange={(e) => handleTextChange(e.target.value)}
              />
            </td>
          </tr>
          <tr>
            <td></td>
            <td className="py-2 px-4">
              {inputError && (
                <p className="text-red text-sm mt-1">{inputError}</p>
              )}
            </td>
          </tr>
          <tr>
            <td></td>
            <td className="py-2 px-4">
              <button
                onClick={handleDecode}
                className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
              >
                Decode
              </button>
            </td>
          </tr>
          <tr>
            <td className="py-2 px-4 whitespace-nowrap text-left text-gray-800">
              Decoded Text:
            </td>
            <td className="py-2 px-4 text-gray-800">
              <textarea
                className="p-2 border border-gray-300 rounded-md w-full"
                value={decodedText}
                readOnly
              />
            </td>
          </tr>
          <tr>
            <td className="py-2 px-4 w-10"> </td>
            <td className="py-2 px-4">
              {decodeError && (
                <p className="text-red text-sm mt-1">{decodeError}</p>
              )}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default Decode;
