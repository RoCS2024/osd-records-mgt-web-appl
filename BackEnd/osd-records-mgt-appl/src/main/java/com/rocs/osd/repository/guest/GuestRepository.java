package com.rocs.osd.repository.guest;

import com.rocs.osd.domain.guest.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * An interface to the Guest repository.
 */
public interface GuestRepository extends JpaRepository<Guest, Long> {
    /**
     * Finds a guest by guest id.
     *
     * @param guestId the guest ID
     * @return a list of guests with the specified ID
     */
    List<Guest> findGuestById(Long guestId);
    /**
     * Finds a guest by guest number.
     *
     * @param guestNumber the guest number
     * @return the guest with the specified guest number
     */
    Guest findByGuestNumber(String guestNumber);
}
