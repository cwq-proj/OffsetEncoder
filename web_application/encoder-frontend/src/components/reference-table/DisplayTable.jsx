import React from "react";

const DisplayTable = ({ numCols, tableData }) => {
  return (
    <>
      <h2 className="text-xl text-black font-bold py-4">Encoding Reference Table</h2>
        <table className="table-auto mx-auto">
          <thead>
            <tr>
              {Array.from({ length: numCols }, (_, i) => (
                <th key={i} className="border px-4 py-2">
                  {i % 2 === 0 ? "Index" : "Reference"}
                </th>
              ))}
            </tr>
          </thead>
          <tbody>
            {tableData.map((row, rowIndex) => (
              <tr key={rowIndex}>
                {row.map((cell, colIndex) => (
                  <td key={colIndex} className="border px-4 py-2">
                    {cell
                      ? colIndex % 2 === 0
                        ? cell.index
                        : cell.reference
                      : ""}
                  </td>
                ))}
              </tr>
            ))}
          </tbody>
        </table>
    </>
  );
};

export default DisplayTable;
