package com.airtribe.learntrack.util;

public class IdGenerator {
    private static int studentIdCounter = 1000;
    private static int courseIdCounter = 10000;
    private static int enrollmentIdCounter = 0;

   // Static method to generate studentId
    public static int getNextStudentId() {
        return ++studentIdCounter;
    }
    // Static method to generate courseId
    public static int getNextCourseId() {
        return ++courseIdCounter;
    }
    // Static method to generate EnrollmentId
    public static int getNextEnrollmentId() {
        return ++enrollmentIdCounter;
    }
}