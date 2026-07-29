package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class StudentService{
    private List<Student> students = new ArrayList<>();

    public int addStudent(String firstName,String lastName,String email,String batch){
        Student student = new Student(firstName, lastName, email, batch);
        students.add(student);
        return student.getId();
    }
    // method that add student without batch no
    public int addStudent(String firstName, String lastName, String email) {
        Student student = new Student(firstName, lastName, email);
        students.add(student);
        return student.getId();
    }

    public List<Student> getAllStudents(){
        return new ArrayList<>(students);
    }
    public Student getStudentById(int id)  {
        for(Student s: students){
            if(s.getId() == id){
                return s;
            }
        }
      throw new EntityNotFoundException("Student Not Found with Id : "+ id);
    }
    public void deactivateStudent(int id){
        for(Student s: students){
            if(s.getId() == id){
                s.setActive(false);
                return;
            }
        }
        throw new EntityNotFoundException("Student Not Found with Id "+ id);
    }

}

