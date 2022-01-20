package com.example.StudentTeacherManagement.repository;

import com.example.StudentTeacherManagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
