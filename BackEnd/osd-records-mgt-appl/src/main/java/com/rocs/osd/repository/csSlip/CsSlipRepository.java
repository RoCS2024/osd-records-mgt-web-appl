package com.rocs.osd.repository.csSlip;


import com.rocs.osd.domain.csSlip.CsSlip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * An interface to the Community Service Slip repository.
 */
public interface CsSlipRepository extends JpaRepository<CsSlip, Long> {
    /**
     * Finds CsSlips where any part of the student's first, middle, or last name contains the specified string.
     *
     * @param firstName first name to search for
     * @param middleName middle name to search for
     * @param lastName last name to search for
     * @return list of CsSlips
     */
    List<CsSlip> findByStudent_FirstNameContainingOrStudent_MiddleNameContainingOrStudent_LastNameContaining(String firstName, String middleName, String lastName);
    /**
     * Finds CsSlips by the name of the community service station
     *
     * @param name the name of the station
     * @return a list of CsSlips
     */
    List<CsSlip> findByAreaOfCommServ_StationNameIgnoreCase(String name);
    /**
     * Finds CsSlips by the student number.
     *
     * @param studentStudentNumber the student number
     * @return list of CsSlips
     */
    List<CsSlip> findByStudentStudentNumber(String studentStudentNumber);
    /**
     * Finds CsSlips by the student ID.
     *
     * @param id the student ID
     * @return list of CsSlips
     */
    List<CsSlip> findByStudent_Id(Long id);

}
