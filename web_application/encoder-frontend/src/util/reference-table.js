// referenceTable.js
const LOWER_BOUND = 0;
const UPPER_BOUND = 43;
const ASCII_UPPER_CASE_START = 'A'.charCodeAt(0);
const ASCII_UPPER_CASE_END = 'Z'.charCodeAt(0);
const ASCII_DIGIT_START = '0'.charCodeAt(0);
const ASCII_DIGIT_END = '9'.charCodeAt(0);
const ASCII_SYMBOL_START = '('.charCodeAt(0);
const ASCII_SYMBOL_END = '/'.charCodeAt(0);

const mappingTable = new Map();
const reverseMappingTable = new Map();

const initializeMappingTable = () => {
  let counter = LOWER_BOUND;
  // Add uppercase letters to both hashmaps
  for (let i = ASCII_UPPER_CASE_START; i <= ASCII_UPPER_CASE_END; i++) {
    const char = String.fromCharCode(i);
    mappingTable.set(counter, char);
    reverseMappingTable.set(char, counter);
    counter++;
  }
  // Add digits to both hashmaps
  for (let i = ASCII_DIGIT_START; i <= ASCII_DIGIT_END; i++) {
    const char = String.fromCharCode(i);
    mappingTable.set(counter, char);
    reverseMappingTable.set(char, counter);
    counter++;
  }
  // Add the other operators to both hashmaps
  for (let i = ASCII_SYMBOL_START; i <= ASCII_SYMBOL_END; i++) {
    const char = String.fromCharCode(i);
    mappingTable.set(counter, char);
    reverseMappingTable.set(char, counter);
    counter++;
  }
};

// Initialize the mapping table
initializeMappingTable();

export { mappingTable, reverseMappingTable };
