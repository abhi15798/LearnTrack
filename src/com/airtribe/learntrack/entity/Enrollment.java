package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.util.IdGenerator;

public class Enrollment {

    public enum Status {
        ACTIVE,
        COMPLETED,
        CANCELLED
    }

    private int id;
    private int studentId;
    private int courseId;
    private String enrollmentDate;
    private Status status;

    public Enrollment(int studentId, int courseId, String enrollmentDate) {
        this.id = IdGenerator.getNextEnrollmentId();
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = Status.ACTIVE; // default status when enrollment is created
    }

    public int getId() {
        return id;
    }


    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }


    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        this.status = status;
    }
}
