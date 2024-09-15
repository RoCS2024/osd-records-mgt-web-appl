package com.rocs.osd.repository.violation;


import com.rocs.osd.domain.violation.Violation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
/**
 * An interface to the Violation repository.
 */
public interface ViolationRepository extends JpaRepository<Violation, Long> {
    /**
     * Finds violations by the student ID.
     *
     * @param studentId student ID
     * @return list of violations
     */
    List<Violation> findByStudentId(Long studentId);
    /**
     * Finds violations by student number.
     *
     * @param studentStudentNumber student number
     * @return list of violations
     */
    List<Violation> findByStudentStudentNumber(String studentStudentNumber);
    /**
     * Finds violations where the date of notice falls between the specified start and end dates.
     *
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return list of violations within the specified date range
     */
    @Query("SELECT v FROM Violation v WHERE v.dateOfNotice BETWEEN :startDate AND :endDate")
    List<Violation> findByDateOfNoticeBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * Finds violations where the student's first, middle, or last name contains the specified string.
     *
     * @param firstName the first name to search for
     * @param middleName the middle name to search for
     * @param lastName the last name to search for
     * @return list of violations
     */
    List<Violation> findByStudentFirstNameContainingIgnoreCaseOrStudentMiddleNameContainingIgnoreCaseOrStudentLastNameContainingIgnoreCase(String firstName, String middleName, String lastName);
}