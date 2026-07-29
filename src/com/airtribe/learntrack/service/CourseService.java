package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CourseService {
    private List<Course> courses = new ArrayList<>();

    public int addCourse(String courseName,String description,int durationInWeeks ){
        Course course = new Course(courseName,description,durationInWeeks);
        courses.add(course);
        return course.getId();
    }

    public List<Course> getAllCourses(){
        return new ArrayList<>(courses);
    }
    public Course getCourseById(int courseId)  {
        for(Course c: courses){
            if(c.getId() == courseId && c.isActive()){
                return c;
            }
        }
        throw new EntityNotFoundException("Either Course is Not Active or the Course ID is invalid : "+ courseId);
    }
    public void updateCourse(int courseId, boolean status){
        for(Course c: courses){
            if(c.getId() == courseId){
                c.setActive(status);
                return;
            }
        }
        throw new EntityNotFoundException("Course Not Found with Id :" + courseId);
    }
}
