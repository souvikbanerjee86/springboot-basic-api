package com.example.demo.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent(){
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
           Optional<Student> student1= studentRepository.findStudentByEmail(student.getEmail());
           if(student1.isPresent()){
               throw new IllegalStateException("Email Taken");
           }
           studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        Boolean exists =studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("User Doesn't exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student =studentRepository.findById(studentId)
                .orElseThrow(()->new IllegalStateException("User Doesn't exists"));


        if(name!=null && name.length()>0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        if(email!=null && email.length()>0 && !Objects.equals(student.getEmail(),email)){

            Optional<Student> student1= studentRepository.findStudentByEmail(student.getEmail());
            if(student1.isPresent()){
                throw new IllegalStateException("Email Taken");
            }
            student.setEmail(email);
        }

    }
}
