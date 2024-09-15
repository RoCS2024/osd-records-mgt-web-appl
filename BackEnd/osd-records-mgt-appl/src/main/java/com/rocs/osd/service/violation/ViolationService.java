package com.rocs.osd.service.violation;

import com.rocs.osd.domain.violation.Violation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing violations.
 */
public interface ViolationService {
    /**
     * Retrieves all violations
     *
     * @return list of all violations
     */
    List<Violation> getAllViolation();
    /**
     * Retrieves a violation by ID
     *
     * @param id violation ID
     * @return  violation
     */
    Optional<Violation> getViolationById(Long id);

    /**
     * Retrieves all violations
     *
     * @param studentId the ID of the student
     * @return a list of violations
     */
    List<Violation> getAllViolationByStudentId(Long studentId);
    /**
     * Retrieves all violations
     *
     * @param studentNumber the student number
     * @return a list of violations
     */
    List<Violation> getAllViolationByStudentNumber(String studentNumber);
    /**
     * Retrieves all violations
     *
     * @param name the student name
     * @return a list of violations
     */
    List<Violation> getAllViolationByStudentName(String name);
    /**
     * Retrieves all violations that occurred within a specific date range.
     *
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return list of violations that occurred within the specified date range
     */
    List<Violation> getViolationsByDateRange(Date startDate, Date endDate);
    /**
     * Add a new violation
     *
     * @param violation violation to be added
     */
    void addViolation(Violation violation);
    /**
     * Updates an existing violation
     *
     * @param violation the violation with updated information
     */
    void updateViolation(Violation violation);
}