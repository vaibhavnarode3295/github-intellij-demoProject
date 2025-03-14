package com.example.demoproject.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Data
public class StudentVo {
    @Size(max = 20,min = 2)
    private String name;
    @NotEmpty
    private String city;
    private String email;

    LocalDate dob;
}
