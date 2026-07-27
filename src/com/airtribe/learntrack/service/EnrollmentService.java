package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class EnrollmentService {
    StudentService studentService ;
    CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    private List<Enrollment> enrollments = new ArrayList<>();

    public int addEnrollment(int studentId,int courseId){
        String enrollmentDate = LocalDate.now().toString();
        Course course = courseService.getCourseById(courseId);
        Student student = studentService.getStudentById(studentId);
        Enrollment enrollment = new Enrollment(student.getId(),course.getId(),enrollmentDate);
        enrollments.add(enrollment);
        return enrollment.getId();
    }
    public List<Enrollment> getAllEnrollments(){
        return enrollments;
    }
    public List<Enrollment> getAllEnrollmentsOfStudent(int studentId){
        List<Enrollment> studentEnrollments = new ArrayList<>();
        for(Enrollment e : enrollments){
            if(e.getStudentId() == studentId){
                studentEnrollments.add(e);
            }
        }
        if(studentEnrollments.isEmpty()){
            throw  new EntityNotFoundException("No Enrollment found for the Student with Id :" + studentId);
        }
        return studentEnrollments;
    }
    public void updateEnrollmentStatus(int enrollmentId, Enrollment.Status status){
        for(Enrollment e: enrollments){
            if(e.getId() == enrollmentId){
                e.setStatus(status);
                return;
            }
        }
        throw new EntityNotFoundException("Enrollment not found with Id :"+ enrollmentId);
    }
}
