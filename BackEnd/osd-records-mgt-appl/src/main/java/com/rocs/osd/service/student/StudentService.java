package com.rocs.osd.service.student;

import com.rocs.osd.domain.student.Student;

import java.util.List;
import java.util.Optional;
/**
 * Service interface for managing Student entities.
 */
public interface StudentService {
    /**
     * Retrieves all students.
     *
     * @return list of all students
     */
    List<Student> getAllStudent();
    /**
     * Retrieves student by ID.
     *
     * @param id student ID
     */
    Optional<Student> getStudentById(Long id);
    /**
     * Retrieves student by student number.
     *
     * @param studentNumber the student number
     * @return the Student
     */
    Student getStudentByNumber(String studentNumber);
}
