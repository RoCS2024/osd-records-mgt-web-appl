package com.rocs.osd.service.employee;

import com.rocs.osd.domain.employee.Employee;

import java.util.List;
/**
 * Service interface for managing employees.
 */
public interface EmployeeService {
    /**
     * Retrieves all employees.
     *
     * @return list of all employees
     */
    List<Employee> getAllEmployee();
    /**
     * Retrieves an employee by their employee number.
     *
     * @param employeeNumber employee number
     * @return the employee
     */
    Employee getEmployeeByEmployeeNumber(String employeeNumber);
}
