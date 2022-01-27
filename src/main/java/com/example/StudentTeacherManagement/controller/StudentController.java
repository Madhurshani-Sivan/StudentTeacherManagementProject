package com.example.StudentTeacherManagement.controller;

import com.example.StudentTeacherManagement.model.Student;
import com.example.StudentTeacherManagement.service.StudentService;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Controller
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/add")
    public String addStudents(Model model) {

        Student student = new Student();
        model.addAttribute("student", student);

        List<String> listTeachers = Arrays.asList("Ms.Anne", "Mr.Perera", "Mrs.Kamala");
        model.addAttribute("listTeachers", listTeachers);
        return "addStudent";
    }

    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            studentService.saveStudent(student);
            return "redirect:/students";
        } else {
            List<String> listTeachers = Arrays.asList("Ms.Anne", "Mr.Perera", "Mrs.Kamala");
            model.addAttribute("listTeachers", listTeachers);
            return "addStudent";
        }
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

    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
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
