package com.example.StudentTeacherManagement.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;


@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;



    @Column(name = "email")
    @Email(message = "Please enter a valid email address")
    @NotBlank
    private String email;

    @Column(name = "gender")
    @NotBlank
    private String gender;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "teacher")
    @NotBlank
    private String teacher;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "full_name")
private String fullName;
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Student() {

    }

    public Student(Integer id, String email, String gender, Date dob, String teacher, String fullName) {
        super();
        this.id = id;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.teacher = teacher;
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
