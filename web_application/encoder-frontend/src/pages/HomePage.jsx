import React from "react";
import EncodeDecodeContainer from "../components/encode/EncodeDecodeContainer";
import ReferenceTable from "../components/reference-table/ReferenceTable";
import { mappingTable } from "../util/reference-table";

const HomePage = () => {
  return (
    <div className="flex justify-center items-center min-h-screen rounded shadow-lg">
      <div className="container">
        <ReferenceTable table={mappingTable} />
        <EncodeDecodeContainer />
      </div>
    </div>
  );
};

export default HomePage;
