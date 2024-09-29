import React from 'react';

const DropdownFilterCsSlipGuest = ({ students, selectedStudentNumber, handleStudentChange }) => {
    return (
        <div className="dropdown-filter">
            <select
                className="beneficiary-button"
                onChange={handleStudentChange}
                value={selectedStudentNumber}
            >
                <option value="">All Students</option>
                {students.map(studentNumber => {
                    const student = students.find(csSlip => csSlip.studentNumber === studentNumber)?.student;
                    return (
                        <option key={studentNumber} value={studentNumber}>
                            {student ? `${student.lastName}, ${student.firstName} ${student.middleName}` : ''}
                        </option>
                    );
                })}
            </select>
        </div>
    );
};

export default DropdownFilterCsSlipGuest;
