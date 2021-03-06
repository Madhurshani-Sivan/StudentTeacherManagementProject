package com.example.StudentTeacherManagement.service;

import com.example.StudentTeacherManagement.model.Student;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    Student saveStudent(Student student);

    Student editStudent(Student student);

    Student getStudentById(Long id);

    void deleteStudent(Long id);

    JasperPrint exportpdf() throws SQLException, JRException, IOException;
}
