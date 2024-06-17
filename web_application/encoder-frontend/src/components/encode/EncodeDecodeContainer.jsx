import React from "react";
import Encode from "./Encode";
import Decode from "./Decode"
import { reverseMappingTable } from "../../util/reference-table";

const EncodeDecodeContainer = () => {
  return (
    <div>
      <Encode reverseMappingTable={reverseMappingTable} />
      <Decode reverseMappingTable={reverseMappingTable} />
    </div>
  );
};

export default EncodeDecodeContainer;
