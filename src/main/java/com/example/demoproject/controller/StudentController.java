package com.example.demoproject.controller;

import ch.qos.logback.core.model.Model;
import com.example.demoproject.model.Student;
import com.example.demoproject.model.StudentVo;
import com.example.demoproject.services.StudentServices;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class StudentController {
    @Autowired
    private StudentServices studentServices;

    @GetMapping("/")
    public String indexPage()
    {
        return "index";
    }
    @GetMapping("/addStudentForm")
    public ModelAndView addStudentFormPage() {
        return new ModelAndView("addStudentForm",Map.of("studentVo",new StudentVo()));
    }
    @PostMapping("/submit")
    public ModelAndView submitForm(@ModelAttribute StudentVo studentVo) {
        Student savedStudent = studentServices.addStudent(studentVo);
        return new ModelAndView("success", Map.of("student",studentVo));
    }
    @GetMapping("/table")
    public ModelAndView getAll(Model model) {
        List<Student> studentList= studentServices.getAllRecords();
        return new ModelAndView("table",Map.of("students",studentList));

    }
}
