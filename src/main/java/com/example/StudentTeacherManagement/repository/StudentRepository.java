package com.example.StudentTeacherManagement.repository;

import com.example.StudentTeacherManagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAllByOrderByIdDesc();

    Student findStudentById(int id);

}
