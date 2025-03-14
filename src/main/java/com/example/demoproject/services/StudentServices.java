package com.example.demoproject.services;

import com.example.demoproject.model.Student;
import com.example.demoproject.model.StudentVo;
import com.example.demoproject.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class StudentServices {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private JavaMailSender javaMailSender;
    public Student addStudent(StudentVo studentVo)
    {
        Student student = new Student();
        student.setName(studentVo.getName());
        student.setCity(studentVo.getCity());
        student.setAge(getDateOfBirth(studentVo.getDob()));
        student.getEmail(studentVo.getEmail());
        String email= studentVo.getEmail();
        String body= "Hello, "+studentVo.getName()+"\n\nYour Name:"+studentVo.getName()+"\nCity:"+studentVo.getCity()+"\nDate Of Birth:"+studentVo.getDob()+"\nKindely check Your Records\nYour Recoreds stored in database";
        sendEmail(email,"Conformation",body);
        return studentRepo.save(student);
    }
    public void sendEmail(String toEmail,String subject,String body)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("vaibhavnarode01@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        javaMailSender.send(message);
        System.out.println("Mail send Successfully....");
    }
    private int getDateOfBirth(LocalDate dob){
        return Period.between(dob, LocalDate.now()).getYears();
    }
    public List<Student> getAllRecords()
    {
        return studentRepo.findAll();
    }
}
