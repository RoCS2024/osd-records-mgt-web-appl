package com.rocs.osd.controller.csSlip;

import com.rocs.osd.domain.csSlip.CsSlip;
import com.rocs.osd.service.csSlip.CsSlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
/**
 * REST controller for managing Community Service Slip.
 */
@RestController
@RequestMapping("/CsSlip")
public class CsSlipController {

    private CsSlipService csSlipService;
    /**
     * This construct a new CsSlipController
     * @param csSlipService handles community service slip
     */
    @Autowired
    public CsSlipController(CsSlipService csSlipService) {
        this.csSlipService = csSlipService;
    }
    /**
     * Retrieves the list of all community service slip.
     *
     * @return The list of all community service slip
     */
    @GetMapping("/commServSlips")
    public ResponseEntity<List<CsSlip>> getAllCsSlip() {
        return new ResponseEntity<>(csSlipService.getAllCsSlip(), HttpStatus.OK);
    }
    /**
     * Retrieves a community service slip by ID.
     *
     * @param id ID of the community service slip
     * @return  The CsSlip
     */
    @GetMapping("/commServSlip/{id}")
    public ResponseEntity<Optional<CsSlip>> getCsSlipById(@PathVariable Long id) {
        return new ResponseEntity<>(this.csSlipService.getCsSlipById(id), HttpStatus.OK);
    }
    /**
     * Retrieves a list of community service slip by Student Number.
     *
     * @return List of community service slip
     */
    @GetMapping("/commServSlip/studentNumber/{studentNumber}")
    public ResponseEntity<List<CsSlip>> getCsSlipByStudentNumber(@PathVariable String studentNumber) {
        return new ResponseEntity<>(this.csSlipService.getCsSlipByStudentNumber(studentNumber), HttpStatus.OK);
    }
    /**
     * Retrieves a list of community service slip by Student ID.
     *
     * @return List of community service slip
     */
    @GetMapping("/studentId/{studentId}")
    public ResponseEntity<List<CsSlip>> getCsSlipsByStudentId(@PathVariable Long studentId) {
        List<CsSlip> slips = csSlipService.getCsSlipByStudentId(studentId);
        return new ResponseEntity<>(slips, HttpStatus.OK);
    }
    /**
     * Retrieves the total hours of Community Service By student ID.
     *
     * @return total hours of Community Service
     */
    @GetMapping("/totalCsHours/{studentId}")
    public ResponseEntity<Integer> getTotalCsHoursByStudent(@PathVariable Long studentId) {
        int totalCsHours = csSlipService.getTotalCsHoursByStudent(studentId);
        return new ResponseEntity<>(totalCsHours, HttpStatus.OK);
    }
    /**
     * Retrieves the list of community service slip by Student name.
     *
     * @return List of community service slip
     */
    @GetMapping("/commServSlipsByName/{name}")
    public ResponseEntity<List<CsSlip>> getCsSlipsByStudentName(@PathVariable String name) {
        List<CsSlip> csSlips = csSlipService.getCsSlipByStudentName(name);
        if (!csSlips.isEmpty()) {
            return new ResponseEntity<>(csSlips, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    /**
     * Retrieves the list of community service report by Student name.
     *
     * @return List of community service report
     */
    @GetMapping("/commServReportByName/{name}")
    public ResponseEntity<List<CsSlip>> getCsSlipReportByStudentName(@PathVariable String name) {
        List<CsSlip> csSlips = csSlipService.getCsSlipReportByStudentName(name);
        if (!csSlips.isEmpty()) {
            return new ResponseEntity<>(csSlips, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    /**
     * Retrieves the list of community service slip report by Station name.
     *
     * @return List of community service slip report
     */
    @GetMapping("/commServSlip/areaOfCs/{name}")
    public ResponseEntity<List<CsSlip>> getCsSlipReportByStationName(@PathVariable String name) {
        List<CsSlip> csSlips = csSlipService.getCsSlipReportByStationName(name);
        if (!csSlips.isEmpty()) {
            return new ResponseEntity<>(csSlips, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    /**
     * Updates the deduction for a community service slip.
     *
     * @param csSlipId ID of community service slip
     * @param deduction deduction amount to update
    */
    @PutMapping("/updateDeduction/{csSlipId}")
    public ResponseEntity<String> updateDeduction(@PathVariable Long csSlipId, @RequestParam int deduction) {
        try {
            csSlipService.updateDeduction(csSlipId, deduction);
            return new ResponseEntity<>("Deduction updated successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update deduction: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   /**
     * Add a new community service slip.
     *
     * @param csSlip community service slip to be added
    */
    @PostMapping("/addCsSlip")
    public ResponseEntity<String> addCsSlip(@RequestBody CsSlip csSlip){
        try {
            csSlipService.addCsSlip(csSlip);
            return new ResponseEntity<>("Community Service Slip successfully added", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Community Service Slip cannot be added", HttpStatus.OK);
        }
    }
}