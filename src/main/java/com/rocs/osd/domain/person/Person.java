package com.rocs.osd.domain.person;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
/**
 * Represents the person entity.
 */
@MappedSuperclass
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(length = 32)
    private String lastName;
    @Column(length = 64)
    private String firstName;
    @Column(length = 32)
    private String middleName;
    private Date birthdate;
    private String birthplace;
    private String sex;
    private String civilStatus;
    private String citizenship;
    @Column(length = 32)
    private String religion;
    @Column(length = 32)
    private String email;
    private String address;
    @Column(length = 11)
    private String contactNumber;
}