package com.rocs.osd.controller.guest;

import com.rocs.osd.domain.guest.Guest;
import com.rocs.osd.service.guest.GuestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * REST controller for managing guests.
 */
@RestController
@RequestMapping("/Guest")
public class GuestController {

    private GuestService guestService;
    /**
     * This construct a new GuestController with the provided GuestService.
     *
     * @param guestService this handle guest operations
     */
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }
    /**
     * Retrieves the list of all guests.
     *
     * @return The list of all guests
     */
    @GetMapping("/guests")
    public ResponseEntity<List<Guest>> getAllGuest() {
        return new ResponseEntity<>(guestService.getAllGuest(), HttpStatus.OK);
    }
    /**
     * Retrieves a specific guest based on the guest number.
     *
     * @param guestNumber guest number of the guest to retrieve
     * @return The guest
     */
    @GetMapping("/getGuestByGuestNumber/{guestNumber}")
    public ResponseEntity<Guest> getGuestByGuestNumber(@PathVariable String guestNumber) {
        Guest guest = guestService.getStudentByStudentNumber(guestNumber);
        return new ResponseEntity<>(guest, HttpStatus.OK);
    }
    /**
     * Retrieves a list of beneficiaries for a specific guest.
     *
     * @param guestId ID of the guest whose beneficiaries are to be retrieved
     * @return The list of beneficiaries
     */
    @GetMapping("/guests/{guestId}/getBeneficiaries")
    public ResponseEntity<List<Guest>> getGuestBeneficiaries(@PathVariable Long guestId) {
        List<Guest> beneficiaries = guestService.getGuestBeneficiaries(guestId);
        return new ResponseEntity<>(beneficiaries, HttpStatus.OK);
    }
    /**
     * Adds a new guest.
     *
     * @param guest The guest containing the details to be added
     */
    @PostMapping("/addGuest")
    public ResponseEntity<String> addGuest(@RequestBody Guest guest){
        try {
            guestService.addGuest(guest);
            return new ResponseEntity<>("Guest Successfully Added", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Guest Has Not Been Added", HttpStatus.OK);
        }
    }
}
