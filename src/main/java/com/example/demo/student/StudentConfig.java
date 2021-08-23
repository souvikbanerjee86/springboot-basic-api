package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
          Student souvik=  new Student(
        "Souvik",
        "souvik.mlindia@gmail.com",
        LocalDate.of(1986, Month.OCTOBER,16));

          Student arpita=  new Student(
                    "Arpita",
                    "arpita.mlindia@gmail.com",
                    LocalDate.of(1985, Month.OCTOBER,16));

            Student rashbehari=  new Student(
                    "Rashbehari",
                    "Rashbehari.mlindia@gmail.com",
                    LocalDate.of(1952, Month.FEBRUARY,16));


          studentRepository.saveAll(Arrays.asList(souvik,arpita,rashbehari));

        };
    }
}
