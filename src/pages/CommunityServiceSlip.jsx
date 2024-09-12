import React, { useState, useEffect } from "react";
import '../styles/CommunityServiceSlip.css';
import axios from "axios";
import { debounce } from 'lodash';
import { useNavigate } from "react-router-dom";
import logo from '../assets/logo_new.png';
import user from '../assets/user.png';

const CsSlipPageAdmin = () => {
    const [formData, setFormData] = useState({
        studentId: '',
        deduction: '',
        areaId: '',
        reasonOfCs: '',
        name: '',
        section: '',
        head: ''
    });

    /// track and manage different piece of data
    const [stations, setStations] = useState([]);
    const [violations, setViolations] = useState([]);
    const [totalHoursRequired, setTotalHoursRequired] = useState('');
    const [students, setStudents] = useState([]);
    const [message, setMessage] = useState('');
    const [errors, setErrors] = useState({});
    const [successMessage, setSuccessMessage] = useState('');
    const [isFormValid, setIsFormValid] = useState(false);

    const navigate = useNavigate();

    useEffect(() => {
        const role = localStorage.getItem('role');
        let exp = localStorage.getItem('exp');
        let currentDate = new Date();

        if (exp * 1000 < currentDate.getTime()) {
            navigate('/login');
        }
        if (role !== "ROLE_ROLE_ADMIN") {
            if (role === "ROLE_ROLE_EMPLOYEE") {
                navigate('/employee/cs-list');
            } else if (role === "ROLE_ROLE_STUDENT") {
                navigate('/student/violation');
            } else if (role === "ROLE_ROLE_GUEST") {
                navigate('/guest/violation');
            } else {
                navigate('/login');
            }
        }
        const fetchData = async () => {
            try {
                const token = localStorage.getItem('token');
                const headers = {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                    'Authorization': `Bearer ${token}`,
                };
                const [stationsResponse, studentsResponse] = await Promise.all([
                    axios.get('http://localhost:8080/Station/stations', { headers }),
                    axios.get('http://localhost:8080/Student/students', { headers })
                ]);
                setStations(stationsResponse.data);
                setStudents(studentsResponse.data);
            } catch (error) {
                console.error('Error fetching data:', error);
                setMessage('An error occurred while fetching data.');
            }
        };

        fetchData();
    }, [navigate]);
    useEffect(() => {
        const fetchData = async () => {
            try {
                if (formData.studentId.trim() !== '' && !errors.studentId) {
                    debouncedFetchStudentDetails(formData.studentId);
                    debouncedFetchStudentViolation(formData.studentId);
                    fetchTotalHoursRequired(formData.studentId);
                } else {
                    setFormData(prevState => ({
                        ...prevState,
                        name: '',
                        section: '',
                        head: ''
                    }));
                    setViolations([]);
                    setTotalHoursRequired('');
                }
            } catch (error) {
                console.error('Error:', error);
                setMessage('An error occurred while fetching data.');
            }
        };

        fetchData();
    }, [formData.studentId, errors.studentId]);

    useEffect(() => {
        setIsFormValid(Object.keys(errors).length === 0 && formData.studentId && formData.deduction && formData.areaId && formData.reasonOfCs);
    }, [errors, formData]);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: value
        }));
    
        let newErrors = { ...errors };
    
        if (name === 'studentId') {
            const studentIdPattern = /^CT\d{2}-\d{4}$/;
            if (!studentIdPattern.test(value)) {
                newErrors.studentId = 'Invalid Input. Please try again.';
            } else {
                const studentExists = students.some(student => student.studentNumber === value);
                if (!studentExists) {
                    newErrors.studentId = 'Student not registered. Please register first.';
                } else {
                    delete newErrors.studentId;
                }
            }
        }
    
        if (name === 'reasonOfCs') {
            const letterPattern = /^[a-zA-Z\s]*$/;
            if (!letterPattern.test(value)) {
                newErrors.reasonOfCs = 'Reason for Community Service should only contain letters and spaces.';
            } else {
                delete newErrors.reasonOfCs;
            }
        }
    
        if (name === 'deduction') {
            if (!value) {
                newErrors.deduction = 'Hours to Deduct are required';
            } else if (isNaN(value) || value <= 0) {
                newErrors.deduction = 'Hours to Deduct must be a positive number';
            } else {
                delete newErrors.deduction;
            }
        }
    
        if (name === 'areaId') {
            if (!value) {
                newErrors.areaId = 'Area of Community Service is required';
            } else {
                delete newErrors.areaId;
            }
        }
    
        setErrors(newErrors);
    };

    const validate = () => {
        const errors = {};
        const studentIdPattern = /^CT\d{2}-\d{4}$/;
        
        if (!formData.studentId) {
            errors.studentId = 'Student ID is required';
        } else if (!studentIdPattern.test(formData.studentId)) {
            errors.studentId = 'Invalid Input. Please try again.';
        }
    
        if (!formData.deduction) {
            errors.deduction = 'Hours to Deduct are required';
        } else if (isNaN(formData.deduction) || formData.deduction <= 0) {
            errors.deduction = 'Hours to Deduct must be a positive number';
        }
    
        if (!formData.areaId) {
            errors.areaId = 'Area of Community Service is required';
        }
    
        if (!formData.reasonOfCs) {
            errors.reasonOfCs = 'Reason for Community Service is required';
        } else {
            const spaceAllowedPattern = /^[a-zA-Z\s]*$/;
            if (!spaceAllowedPattern.test(formData.reasonOfCs)) {
                errors.reasonOfCs = 'Reason for Community Service should only contain letters and spaces.';
            }
        }
    
        return errors;
    };

    const fetchStudentDetails = async (studentId) => {
        try {
            const student = students.find(student => student.studentNumber === studentId);
            const id = student && student.id;
            if (id) {
                const token = localStorage.getItem('token');
                const response = await axios.get(`http://localhost:8080/Student/student/${id}`, {
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json',
                        'Authorization': `Bearer ${token}`,
                    }
                });
                const studentData = response.data;
                if (studentData !== null) {
                    setFormData(prevState => ({
                        ...prevState,
                        name: `${studentData.lastName}, ${studentData.firstName} ${studentData.middleName}`,
                        section: studentData.section.sectionName,
                        head: studentData.section.clusterHead
                    }));
                }
            }
        } catch (error) {
            console.error('Error getting student info:', error);
        }
    };

    const fetchStudentViolation = async (studentId) => {
        try {
            const student = students.find(student => student.studentNumber === studentId);
            const id = student && student.id;
            if (id) {
                const token = localStorage.getItem('token');
                const response = await axios.get(`http://localhost:8080/Violation/violation/student/${id}`, {
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json',
                        'Authorization': `Bearer ${token}`,
                    }
                });
                setViolations(response.data);
            }
        } catch (error) {
            console.error('Error getting violations:', error);
        }
    };

    const fetchTotalHoursRequired = async (studentId) => {
        try {
            const student = students.find(student => student.studentNumber === studentId);
            const id = student && student.id;
            if (id) {
                const token = localStorage.getItem('token');
                const response = await axios.get(`http://localhost:8080/CSSlip/totalCsHours/${id}`, {
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json',
                        'Authorization': `Bearer ${token}`,
                    }
                });
                setTotalHoursRequired(response.data);
            }
        } catch (error) {
            console.error('Error getting total hours required:', error);
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const validationErrors = validate();
        if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors);
            return;
        }
        try {
            const token = localStorage.getItem('token');
            const student = students.find(student => student.studentNumber === formData.studentId);
            const id = student && student.id;
            if (id) {
                const payload = {
                    id,
                    student: { id },
                    reasonOfCs: formData.reasonOfCs,
                    areaOfCommServ: { id: formData.areaId },
                    deduction: formData.deduction
                };
                const response = await axios.post('http://localhost:8080/CSSlip/csSlip', payload, {
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json',
                        'Authorization': `Bearer ${token}`,
                    }
                });

                if (response && response.data) {
                    setMessage('');
                    setSuccessMessage('Community Service Slip created Successfully!');
                    setTimeout(() => {
                        setSuccessMessage('');
                    }, 5000);
                } else {
                    console.error('Response data is Undefined:', response);
                    setMessage('Unexpected error occurred.');
                }
            }
        } catch (error) {
            console.error('Error:', error);
            if (error.response && error.response.data && error.response.data.message) {
                setMessage(error.response.data.message);
            } else {
                setMessage('An error occurred while processing the request.');
            }
        }
    };

    // Prevent too many function calls in a short time.
    const debouncedFetchStudentDetails = debounce(fetchStudentDetails, 300);
    const debouncedFetchStudentViolation = debounce(fetchStudentViolation, 300);

    const handleLogout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('role');
        localStorage.removeItem('exp');
        navigate('/login');
    };
    return (
        <div className="cs-slip-page-admin">

            <nav className="nav-bar">

                <img src={logo} alt="Logo" className="rc-logo"/>

                <div className="nav-links">
                    <a className="nav-link" href="/admin/offense">Offense</a>
                    <a className="nav-link" href="/admin/violation">Violation</a>
                    <a className="nav-link" href="/admin/cs-list">CS Slips</a>
                    <a className="nav-link" href="#" onMouseDown={handleLogout}>Logout</a>
                    <img src={user} alt="profile" className="profile"/>
                </div>

            </nav>
            <div className="csslip-box">

                <h1>COMMUNITY SERVICE SLIP</h1>

                <div className="cs-slip-container">

                    <form onSubmit={handleSubmit}>

                        <div className="input-container">

                            <div className="field-container">
                                <label>Student ID:</label>
                                <input type="text" className="cs-input-field" name="studentId" value={formData.studentId} onChange={handleInputChange} />
                                {errors.studentId && <span className="error">{errors.studentId}</span>}
                            </div>

                            <div className="field-container">
                                <label>Full Name:</label>
                                <input type="text" disabled className="cs-input-field" name="name" value={                                formData.name} onChange={handleInputChange} />
                            </div>

                            <div className="field-container">
                                <label>Section:</label>
                                <input type="text" disabled className="cs-input-field" name="section" value={formData.section} onChange={handleInputChange} />
                            </div>

                            <div className="field-container">
                                <label>Cluster Head:</label>
                                <input type="text" disabled className="cs-input-field" name="head" value={formData.head} onChange={handleInputChange} />
                            </div>

                            <div className="field-container">
                                <label>Hours to Deduct:</label>
                                <input type="text" className="cs-input-field" name="deduction" value={formData.deduction} onChange={handleInputChange} />
                                {errors.deduction && <span className="error">{errors.deduction}</span>}
                            </div>

                            <div className="field-container">
                                <label>Area of Community Service:</label>
                                <select className="select-field" name="areaId" value={formData.areaId} onChange={handleInputChange}>
                                    <option value="">Select an area</option>
                                    {stations.map(station => (
                                        <option key={station.id} value={station.id}>{station.stationName}</option>
                                    ))}
                                </select>
                                {errors.areaId && <span className="error">{errors.areaId}</span>}
                            </div>

                            <div className="field-container">
                            <label>Reason for Community Service:</label>
                            <input type="text" className="cs-input-field" name="reasonOfCs" value={formData.reasonOfCs} onChange={handleInputChange} />
                            {errors.reasonOfCs && <span className="error">{errors.reasonOfCs}</span>}
                            </div>

                        </div>

                        <table className="cs-slip-table">

                            <thead>
                                <tr>
                                    <th>STUDENT</th>
                                    <th>OFFENSE</th>
                                    <th>CS HOURS</th>
                                </tr>
                            </thead>

                            <tbody>
                                {violations.map(violation => (
                                    <tr key={violation.id}>
                                        <td>{violation.student.studentNumber}</td>
                                        <td>{violation.offense.description}</td>
                                        <td>{violation.csHours}</td>
                                    </tr>
                                ))}
                            </tbody>

                        </table>

                        <div className="bottom-container">

                            <div className="total-container">
                                <label>Total Hours Required: </label>
                                <input type="text" disabled className="input-hours" name="hoursRequired" value={totalHoursRequired} readOnly />
                            </div>

                            <button type="submit" className="create-button" disabled={!isFormValid} >CREATE</button>
                            {message && <div className="message error">{message}</div>}
                            {successMessage && <div className="message success">{successMessage}</div>}
                            
                        </div>

                    </form>

                </div>

            </div>
            
        </div>
    );
};

export default CsSlipPageAdmin;
