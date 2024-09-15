package com.rocs.osd.service.employee.impl;

import com.rocs.osd.domain.employee.Employee;
import com.rocs.osd.repository.employee.EmployeeRepository;
import com.rocs.osd.service.employee.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of Employee interface.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    /**
     * This is for injecting the EmployeeRepository.
     *
     * @param employeeRepository accessing employee data
     */
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeByEmployeeNumber(String employeeNumber) {
        return employeeRepository.findByEmployeeNumber(employeeNumber);
    }
}
