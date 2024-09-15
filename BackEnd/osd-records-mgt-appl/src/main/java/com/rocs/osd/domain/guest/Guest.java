package com.rocs.osd.domain.guest;

import com.rocs.osd.domain.person.Person;
import com.rocs.osd.domain.student.Student;
import com.rocs.osd.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
/**
 * Represents the guest entity.
 */
@Entity
@Data
public class Guest extends Person implements Serializable {

    private Long guestId;
    private String guestNumber;
    @OneToMany
    private List<Student> beneficiary;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
