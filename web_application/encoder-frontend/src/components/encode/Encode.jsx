import React, { useState } from "react";

const Encode = ({ reverseMappingTable }) => {
  const [offsetChar, setOffsetChar] = useState("");
  const [plainText, setPlainText] = useState("");
  const [offsetCharError, setOffsetCharError] = useState("");
  const [plainTextError, setPlainTextError] = useState("");
  const [encodedText, setEncodedText] = useState("");
  const [encodedTextError, setEncodedTextError] = useState("");

  const validateInputs = () => {
    setOffsetCharError("");
    setPlainTextError("");

    let isValid = true;
    if (!offsetChar || offsetChar.length > 1) {
      setOffsetCharError("Offset character must be a single character.");
      isValid = false;
    } else if (
      !reverseMappingTable.has(offsetChar) &&
      offsetChar.length === 1
    ) {
      setOffsetCharError("Offset character not found in reference table!");
      isValid = false;
    }

    if (!plainText) {
      setPlainTextError("Plain text cannot be empty.");
      isValid = false;
    }

    if (!isValid) {
      setEncodedText("");
    }
    return isValid;
  };

  const encodeText = () => {
    if (!validateInputs()) return;

    const ENCODE_BASE_ENDPOINT = "http://localhost:8080/api/encode";
    const encodedOffsetChar = encodeURIComponent(offsetChar);
    const encodedPlainText = encodeURIComponent(plainText);
    const ENDPOINT = `${ENCODE_BASE_ENDPOINT}?offset=${encodedOffsetChar}&plaintext=${encodedPlainText}`;

    const fetchEncodedText = async () => {
      try {
        const response = await fetch(ENDPOINT);
        if (response.status >= 200 && response.status < 300) {
          const result = await response.json();
          setEncodedText(result.encodedString);
          setEncodedTextError("");
        } else if (response.status === 400) {
          const errorMessage = await response.json();
          setEncodedTextError(errorMessage.message);
          setEncodedText("");
        } else {
          throw new Error("Failed to encode. Please try again.");
        }
      } catch (error) {
        setEncodedTextError("Error encoding: " + error.message);
        setEncodedText("");
      }
    };

    fetchEncodedText();
  };

  return (
    <div>
      <h3 className="text-2xl text-black font-semibold mb-4">Encoding Tool</h3>

      <table className="border-collapse w-full">
        <tbody>
          <tr>
            <td
              className="py-2 px-4 whitespace-nowrap text-left text-gray-800"
              style={{ width: "25%" }}
            >
              Offset Character:
            </td>
            <td className="py-2 px-4 text-gray-800">
              <input
                type="text"
                className="p-2 border border-gray-300 rounded-md w-full"
                value={offsetChar}
                placeholder="Please enter a single character"
                onInput={(e) => setOffsetChar(e.target.value)}
              />
            </td>
          </tr>

          <tr>
            <td></td>
            <td className="py-2 px-4">
              {offsetCharError && (
                <p className="text-red text-sm mt-1">{offsetCharError}</p>
              )}
            </td>
          </tr>
          <tr>
            <td className="py-2 px-4 whitespace-nowrap text-left text-gray-800">
              Plain Text:
            </td>
            <td className="py-2 px-4 text-gray-800">
              <textarea
                className="p-2 border border-gray-300 rounded-md w-full"
                value={plainText}
                placeholder="Please enter a string"
                onInput={(e) => setPlainText(e.target.value)}
              />
            </td>
          </tr>
          <tr>
            <td></td>
            <td className="py-2 px-4">
              {plainTextError && (
                <p className="text-red text-sm mt-1">{plainTextError}</p>
              )}
            </td>
          </tr>
          <tr>
            <td></td>
            <td className="py-2 px-4">
              <button
                onClick={encodeText}
                className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
              >
                Encode
              </button>
            </td>
          </tr>
          <tr>
            <td className="py-2 px-4 whitespace-nowrap text-left text-gray-800">
              Encoded Text:
            </td>
            <td className="py-2 px-4 text-gray-800">
              <textarea
                className="p-2 border border-gray-300 rounded-md w-full"
                value={encodedText}
                readOnly
              />
            </td>
          </tr>
          <tr>
            <td></td>
            <td className="py-2 px-4">
              {encodedTextError && (
                <p className="text-red text-sm mt-1">{encodedTextError}</p>
              )}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default Encode;
