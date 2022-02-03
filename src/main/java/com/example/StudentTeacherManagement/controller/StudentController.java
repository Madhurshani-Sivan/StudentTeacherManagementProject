package com.example.StudentTeacherManagement.controller;

import com.example.StudentTeacherManagement.DTO.ErrorDto;
import com.example.StudentTeacherManagement.model.Student;
import com.example.StudentTeacherManagement.service.StudentService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost/8080")
@RequestMapping
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public List<Student> listStudents(Model model) {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }

    @PostMapping("/add")
    public ErrorDto addStudents(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping("/students/edit/{id}")
    public String editStudent(@PathVariable Integer id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        List<String> listTeachers = Arrays.asList("Ms.Anne", "Mr.Perera", "Mrs.Kamala");
        model.addAttribute("listTeachers", listTeachers);
        return "editStudent";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Integer id, @ModelAttribute("student") Student student, Model model) {
        Student stu = studentService.getStudentById(id);
        stu.setId(id);
        stu.setFirstName(student.getFirstName());
        stu.setLastName(student.getLastName());
        stu.setEmail(student.getEmail());
        stu.setGender(student.getGender());
        stu.setDob(student.getDob());
        stu.setTeacher(student.getTeacher());
        studentService.editStudent(stu);
        return "redirect:/students";
    }

    @DeleteMapping("/students/{id}")
    public ErrorDto deleteStudent(@PathVariable Integer id) {
        return studentService.deleteStudent(id);
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(ModelAndView model, HttpServletResponse res) throws IOException, JRException, SQLException {
        JasperPrint jasperPrint = null;
        res.setContentType("application/x-download");
        res.setHeader("Content-Disposition", String.format("attachment;filename=\"students.pdf\""));
        OutputStream out = res.getOutputStream();
        jasperPrint = studentService.exportpdf();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
    }
}
