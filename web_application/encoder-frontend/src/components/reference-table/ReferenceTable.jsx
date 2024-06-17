import React from "react";
import DisplayTable from "./DisplayTable";
import EncodingExplanation from "./EncodingExplanation";

const ReferenceTable = ({ table }) => {
  const entries = Array.from(table.entries());
  const numEntries = entries.length;
  const numCols = 8;
  const numRows = Math.ceil(numEntries / (numCols / 2));

  // Create a 2D array to store the table data
  const tableData = Array.from({ length: numRows }, () =>
    Array(numCols).fill(null)
  );

  // Populate the table data with map entries
  entries.forEach(([index, reference], i) => {
    const colGroup = Math.floor(i / numRows) * 2;
    const row = i % numRows;
    tableData[row][colGroup] = { index };
    tableData[row][colGroup + 1] = { reference };
  });

  return (
    <div>
      <DisplayTable numCols={numCols} tableData={tableData} />
      <EncodingExplanation />
    </div>
  );
};
export default ReferenceTable;
