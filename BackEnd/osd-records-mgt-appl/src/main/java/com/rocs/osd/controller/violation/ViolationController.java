package com.rocs.osd.controller.violation;

import com.rocs.osd.domain.violation.Violation;
import com.rocs.osd.service.violation.ViolationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
/**
 * REST controller for managing violations.
 */
@RestController
@RequestMapping("/Violation")
public class ViolationController {

    private ViolationService violationService;
    /**
     * Constructs a ViolationController with ViolationService.
     *
     * @param violationService the service handling violation operations
     */
    @Autowired
    public ViolationController(ViolationService violationService) {
        this.violationService = violationService;
    }
    /**
     * Retrieves all violations.
     *
     * @return a list of all violations
     */
    @GetMapping("/violations")
    public ResponseEntity<List<Violation>> getAllViolation() {
        return new ResponseEntity<>(violationService.getAllViolation(), HttpStatus.OK);
    }
    /**
     * Retrieves a violation by ID.
     *
     * @param id the violation ID
     * @return the violation with the given ID
     */
    @GetMapping("/violation/{id}")
    public ResponseEntity<Optional<Violation>> getViolationById(@PathVariable Long id) {
        return new ResponseEntity<>(this.violationService.getViolationById(id), HttpStatus.OK);
    }
    /**
     * Retrieves violations by a student ID.
     *
     * @param studentId student ID
     * @return a list of violations
     */
    @GetMapping("/violation/student/{studentId}")
    public ResponseEntity<List<Violation>> getAllViolationByStudentId(@PathVariable Long studentId) {
        return new ResponseEntity<>(this.violationService.getAllViolationByStudentId(studentId), HttpStatus.OK);
    }
    /**
     * Retrieves all violations by student number.
     *
     * @param studentNumber the student's number
     * @return a list of violations for the given student number
     */
    @GetMapping("/violation/student/studentNumber/{studentNumber}")
    public ResponseEntity<List<Violation>> getAllViolationByStudentNumber(@PathVariable String studentNumber) {
        return new ResponseEntity<>(this.violationService.getAllViolationByStudentNumber(studentNumber), HttpStatus.OK);
    }
    /**
     * Retrieves all violations by student name.
     *
     * @param name the student's name
     * @return a list of violations for the given student's name
     */
    @GetMapping("/violation/studentName/{name}")
    public ResponseEntity<List<Violation>> getAllViolationByStudentName(@PathVariable String name) {
        return new ResponseEntity<>(violationService.getAllViolationByStudentName(name), HttpStatus.OK);
    }
    /**
     * Retrieves violations within a specific date range.
     *
     * @param startDate start date of the range
     * @param endDate end date of the range
     * @return a list of violations within the given date range
     */
    @GetMapping("/date-range")
    public ResponseEntity<List<Violation>> getViolationsByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return new ResponseEntity<>(this.violationService.getViolationsByDateRange(startDate, endDate), HttpStatus.OK);
    }
    /**
     * Add a new violation.
     *
     * @param violation violation to add
     * @return a success or error message
     */
    @PostMapping("/violation/addViolation")
    public ResponseEntity<String> addViolation(@RequestBody Violation violation) {
        try {
            violationService.addViolation(violation);
            return new ResponseEntity<>("Violation successfully added", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Violation cannot be added", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Updates an existing violation.
     *
     * @param violation violation to update
     * @return a success or error message
     */
    @PutMapping("/violation/updateViolation")
    public ResponseEntity<String> updateViolation(@RequestBody Violation violation) {
        try {
            violationService.updateViolation(violation);
            return new ResponseEntity<>("Violation successfully updated", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Violation not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}