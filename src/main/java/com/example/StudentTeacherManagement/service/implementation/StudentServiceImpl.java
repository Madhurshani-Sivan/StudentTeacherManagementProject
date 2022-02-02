package com.example.StudentTeacherManagement.service.implementation;

import com.example.StudentTeacherManagement.DTO.ErrorDto;
import com.example.StudentTeacherManagement.model.Student;
import com.example.StudentTeacherManagement.model.StudentDaoImpl;
import com.example.StudentTeacherManagement.repository.StudentRepository;
import com.example.StudentTeacherManagement.service.StudentService;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private StudentDaoImpl studentDao;

    public StudentServiceImpl(StudentRepository studentRepo) {
        super();
        this.studentRepo = studentRepo;
    }


    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }


    public ErrorDto saveStudent(Student student) {
        if (student.getFirstName().isEmpty() || student.getLastName().isEmpty() || student.getEmail().isEmpty() || student.getGender().isEmpty() || student.getTeacher().isEmpty() || student.getDob() == null) {
            return new ErrorDto(400, "Contains Empty Values");
        }
        if (!student.getEmail().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            return new ErrorDto(400, "Invalid Email Address");

        }
        if (!student.getFirstName().matches("[A-Za-z]*") || !student.getLastName().matches("[A-Za-z]*") || !student.getTeacher().matches("[A-Za-z]*")) {
            return new ErrorDto(400, "Invalid Name Format");

        }
        if (student.getDob().after(new Date())) {
            return new ErrorDto(400, "Check your birthdate again");
        }

        studentRepo.save(student);
        return new ErrorDto(200, "Saved Successfully");
    }


    public Student editStudent(Student student) {
        return studentRepo.save(student);
    }


    public Student getStudentById(Integer id) {
        return studentRepo.findById(id).get();
    }


    public ErrorDto deleteStudent(Integer id) {
        studentRepo.deleteById(id);
        return new ErrorDto(300,"Deleted Sucessfully");
    }


    public JasperPrint exportpdf() throws JRException, SQLException, IOException {
        return studentDao.export();
    }


}
