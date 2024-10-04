package com.rocs.osd.service.user;

import com.rocs.osd.domain.user.User;
import com.rocs.osd.exception.domain.*;
import jakarta.mail.MessagingException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * Service interface for managing user operations.
 *
 */
public interface UserService {

    /**
     * this register a new user in the system.
     *
     * @param user the user containing registration details like username, email, password, etc.
     * @return the registered user with information
     * @throws UsernameNotFoundException if the username is not found during registration
     * @throws UsernameExistsException if the username already exists in the system
     * @throws EmailExistsException if the email is already associated with an existing account
     * @throws MessagingException if an error occurs when sending an email
     * @throws PersonExistsException if the person being registered already exists
     * @throws UserNotFoundException if the user details are not found during the process
     */
    User register(User user) throws UsernameNotFoundException, UsernameExistsException, EmailExistsException, MessagingException, PersonExistsException, UserNotFoundException;

    /**
     * this handles password recovery for a user by generating and sending an OTP.
     *
     * @param user user object containing the username for password recovery
     * @return the updated user object with an OTP assigned
     * @throws UsernameNotFoundException if the username is not found
     * @throws MessagingException if an error occurs when sending the OTP via email
     */
    User forgotPassword(User user) throws UsernameNotFoundException, MessagingException;

    /**
     * Retrieves a list of all users in the system.
     * @return a list of all users
     */
    List<User> getUsers();

    /**
     * Finds a user by their username.
     *
     * @param username username of the user to be found
     * @return the user corresponding to the provided username
     */
    User findUserByUsername(String username);

    /**
     * Verifies the OTP for resetting a password during the password recovery process.
     *
     * @param newUser user object containing the OTP and new password
     * @return the updated user object with the new password set
     * @throws PersonExistsException if the user details are invalid or missing
     * @throws OtpExistsException if the provided OTP is invalid or expired
     */
    User verifyOtpForgotPassword(User newUser) throws PersonExistsException, OtpExistsException;

    /**
     * Verifies the OTP provided for activating a user account.
     *
     * @param username the username of the user whose account is being activated
     * @param otp the OTP code provided for account verification
     * @return true if the OTP is valid and the account is successfully activated, false otherwise
     */
    boolean verifyOtp(String username, String otp);
}
