package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.*;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static int readIntInput(Scanner sc, String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService(studentService,courseService);
        Scanner sc = new Scanner(System.in); // Scanner class to take input from user
        System.out.println("Welcome to LearnTrack [ Student & Course Management System]");
        boolean isMainMenu= true;
        while(isMainMenu) {
            int input = readIntInput(sc, "Please select option from below:" +
                    "\n1. Student Management" +
                    "\n2. Course Management" +
                    "\n3. Enrollment Management" +
                    "\n4. Exit");

            switch (input) {
                case 1: {
                    System.out.println("Welcome to Student Management !!");
                    boolean isStudentMenu = true;
                    while (isStudentMenu) {
                        int choice = readIntInput(sc, "Please select the operation from below which you want to perform !!\n" +
                                "1. Add New Student\n" +
                                "2. List Students\n" +
                                "3. Get Student By Id\n" +
                                "4. Deactivate Student\n" +
                                "0. Main Menu");
                        if (choice == 1) {
                            System.out.println("Please enter Student details!");
                            System.out.println("Enter First Name");
                            String firstName = sc.nextLine();
                            System.out.println("Enter Last Name");
                            String lastName = sc.nextLine();
                            System.out.println("Enter Email");
                            String email = sc.nextLine();
                            System.out.println("Enter Batch (Note : if not known enter null)");
                            String batch = sc.nextLine();
                            int id;
                            if (batch.equals("null")) {
                                id = studentService.addStudent(firstName, lastName, email);
                            } else {
                                id = studentService.addStudent(firstName, lastName, email, batch);
                            }

                            System.out.println("Student Added SuccessFully : Student ID = " + id);

                        } else if (choice == 2) {
                            if (studentService.getAllStudents().isEmpty()) {
                                System.out.println("No Student Details Present Currently in the system");
                            } else {
                                for (Student s : studentService.getAllStudents()) {
                                    System.out.println("ID :" + s.getId() + " | First Name :" + s.getFirstName() + " | Last Name :" + s.getLastName() + " | Email :" + s.getEmail() + " | Batch :" + s.getBatch() + " !|  Active :" + s.isActive());
                                }
                            }
                        } else if (choice == 3) {
                            int id = readIntInput(sc, "Enter the Id of Student");
                            try {
                                Student s = studentService.getStudentById(id);
                                System.out.println("ID :" + s.getId() + " | First Name :" + s.getFirstName() + " | Last Name :" + s.getLastName() + " | Email :" + s.getEmail() + " | Batch :" + s.getBatch());
                            } catch (EntityNotFoundException e) {
                                System.out.println("Error :" + e.getMessage() + " | Please enter a valid Student Id");
                            }

                        } else if (choice == 4) {
                            int id = readIntInput(sc, "Please provide the Student Id to deactivate");
                            try {
                                studentService.deactivateStudent(id);
                                System.out.println("Student Deactivated Successfully");
                            } catch (EntityNotFoundException e) {
                                System.out.println("Error :" + e.getMessage() + " | Please enter a valid Student Id");
                            }
                        } else if (choice == 0) {
                            isStudentMenu = false;
                        } else {
                            System.out.println("Please select valid option");
                        }
                    }
                    break;
                }
                case 2: {
                    System.out.println("Welcome to Course Management !!");
                    boolean isCourseMenu = true;
                    while (isCourseMenu) {
                        int choice = readIntInput(sc, "Please select the operation from below which you want to perform !!\n" +
                                "1. Add New Course\n" +
                                "2. List Courses\n" +
                                "3. Activate/Deactivate Course\n" +
                                "0. Main Menu");
                        if (choice == 1) {
                            System.out.println("Please enter Course Details!");
                            System.out.println("Enter Course Name");
                            String courseName = sc.nextLine();
                            System.out.println("Enter Course Description");
                            String description = sc.nextLine();
                            int duration = readIntInput(sc, "Enter Course Duration (in Weeks)");
                            int courseId= 0;
                            try{
                                courseId = courseService.addCourse(courseName, description, duration);
                            }catch(IllegalArgumentException e){
                                System.out.println("Error: "+ e.getMessage());
                            }
                            System.out.println("Course Added Successfully : Course ID = " + courseId);
                        } else if (choice == 2) {
                            if (courseService.getAllCourses().isEmpty()) {
                                System.out.println("No Course Present currently");
                            } else {
                                for (Course c : courseService.getAllCourses()) {
                                    String status;
                                    if (c.isActive()) {
                                        status = "Active";
                                    } else {
                                        status = "Deactivate";
                                    }
                                    System.out.println("Course ID :" + c.getId() + " | Course Name :" + c.getCourseName() + " | Course Description :" + c.getDescription() + " | Course Duration :" + c.getDurationInWeeks() + " Weeks | Course Status : " + status);
                                }
                            }

                        } else if (choice == 3) {
                            System.out.println("Please select if you want to deactivate or activate a course");
                            System.out.println("a. Activate");
                            System.out.println("b. Deactivate");
                            String inputChoice = sc.nextLine();
                            if (inputChoice.equalsIgnoreCase("a")) {
                                int courseId = readIntInput(sc, "Please select a Course Id to Activate");
                                try {
                                    courseService.updateCourse(courseId, true);
                                    System.out.println("Course Status updated successfully to Active");
                                } catch (EntityNotFoundException e) {
                                    System.out.println("Error : " + e.getMessage());
                                }
                            } else {
                                int courseId = readIntInput(sc, "Please select a Course Id to Deactivate");
                                try {
                                    courseService.updateCourse(courseId, false);
                                    System.out.println("Course Status updated successfully to Deactivate");
                                } catch (EntityNotFoundException e) {
                                    System.out.println("Error : " + e.getMessage());
                                }
                            }
                        } else if (choice == 0) {
                            isCourseMenu = false;
                        } else {
                            System.out.println("Please select valid option");
                        }
                    }
                    break;
                }
                case 3: {
                    System.out.println("Welcome to Enrollment Management !!");
                    boolean isEnrollentMenu = true;
                    while (isEnrollentMenu) {
                        int enrollmentChoice = readIntInput(sc, "Please select the operation from below which you want to perform !!\n" +
                                "1. Add Student to Course\n" +
                                "2. List Student Enrollments\n" +
                                "3. Update Enrollment (Completed/Cancelled)\n" +
                                "0. Main Menu");
                        if (enrollmentChoice == 1) {
                            int studentId = readIntInput(sc, "Please enter Student Id");
                            int courseId = readIntInput(sc, "Please enter Course Id");
                            try {
                                int enrollmentId = enrollmentService.addEnrollment(studentId, courseId);
                                System.out.println("Enrollment created!! Enrollment Id :" + enrollmentId);
                            } catch (EntityNotFoundException e) {
                                System.out.println("Error : " + e.getMessage());
                            }
                        } else if (enrollmentChoice == 2) {
                            int studentId = readIntInput(sc, "Please Enter Student Id");
                            try {
                                List<Enrollment> studentEnrollment = enrollmentService.getAllEnrollmentsOfStudent(studentId);
                                for (Enrollment enrollment : studentEnrollment) {
                                    System.out.println("Enrollment Id :" + enrollment.getId() + " | Course Id :" + enrollment.getCourseId() + " | Enrollment Date :" + enrollment.getEnrollmentDate() + " | Enrollment Status :" + enrollment.getStatus());
                                }
                            } catch (EntityNotFoundException e) {
                                System.out.println("Error : " + e.getMessage());
                            }
                        } else if (enrollmentChoice == 3) {
                            int enrollmentId = readIntInput(sc, "Please enter Enrollment Id");
                            boolean validChoice = false;
                            Enrollment.Status status = null;
                            while (!validChoice) {
                                int enrollmentStatusChoice = readIntInput(sc, "Please provide enrollment Status\n" +
                                        "1. Completed\n" +
                                        "2. Cancelled");
                                if (enrollmentStatusChoice == 1) {
                                    status = Enrollment.Status.COMPLETED;
                                    validChoice = true;
                                } else if (enrollmentStatusChoice == 2) {
                                    status = Enrollment.Status.CANCELLED;
                                    validChoice = true;
                                } else {
                                    System.out.println("Invalid choice! Please select valid option");
                                }
                            }
                            try {
                                enrollmentService.updateEnrollmentStatus(enrollmentId, status);
                                System.out.println("Enrollment Status Updated Successfully!");
                            } catch (EntityNotFoundException e) {
                                System.out.println("Error : " + e.getMessage());
                            }
                        } else if (enrollmentChoice == 0) {
                            isEnrollentMenu = false;
                        } else {
                            System.out.println("Please select a valid option");
                        }
                    }
                    break;
                }
                case 4: {
                    System.out.println("Thank you for Visiting !!");
                    isMainMenu = false;
                    break;
                }
                default: {
                    System.out.println("Please select valid option");
                }
            }
        }
    }
}