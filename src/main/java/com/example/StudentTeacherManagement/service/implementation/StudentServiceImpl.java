package com.example.StudentTeacherManagement.service.implementation;

import com.example.StudentTeacherManagement.model.Student;
import com.example.StudentTeacherManagement.model.StudentDaoImpl;
import com.example.StudentTeacherManagement.repository.StudentRepository;
import com.example.StudentTeacherManagement.service.StudentService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepo;

    public StudentServiceImpl(StudentRepository studentRepo) {
        super();
        this.studentRepo = studentRepo;
    }

    @Autowired
    private StudentDaoImpl studentDao;

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public Student editStudent(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepo.findById(id).get();
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepo.deleteById(id);
    }

    @Override
    public JasperPrint exportpdf() throws SQLException, JRException, IOException {
        return studentDao.export();
    }


}
