package com.rocs.osd.repository.student;


import com.rocs.osd.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * An interface to the Student repository.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
    /**
     * Checks if a student exists with the specified student number and email.
     *
     * @param studentNumber the student number
     * @param email the email of the student
     */
    boolean existsByStudentNumberAndEmail(String studentNumber, String email);
    /**
     * Finds a student by student number.
     *
     * @param studentNumber the student number
     */
    Student findByStudentNumber(String studentNumber);
}
