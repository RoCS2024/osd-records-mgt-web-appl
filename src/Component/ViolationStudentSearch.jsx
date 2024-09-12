import React from "react";

const ViolationStudentSearch = ({ searchInput, setSearchInput }) => {
    return (
        <input
            type="text"
            placeholder="Search by Student Name"
            className="violation-search-input"
            value={searchInput}
            onChange={(e) => setSearchInput(e.target.value)}
        />
    );
};

export default ViolationStudentSearch;
