
import React from 'react';

const SearchBar = ({ searchInput, onSearchChange, filterType, onFilterChange }) => {
  return (
    <div className="search-offense-filter">
      <input
        type="text"
        placeholder="Search by offense"
        className="search-offense-input"
        value={searchInput}
        onChange={onSearchChange}
      />
    </div>
  );
};




export default SearchBar;
