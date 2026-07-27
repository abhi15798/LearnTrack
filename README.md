# LearnTrack — Student & Course Management System

## Project Description

LearnTrack is a console-based Student & Course Management System built using Core Java.
It allows an admin to manage Students, Courses, and Enrollments through a menu-driven
console interface.

### Features
**1. Student Management**
- Add New Student
- List Students
- Get Student By Id
- Deactivate Student

**2. Course Management**
- Add New Course
- List Courses
- Activate/Deactivate Course

**3. Enrollment Management**
- Add Student to Course
- List Student Enrollments
- Update Enrollment (Completed/Cancelled)

com.airtribe.learntrack

### Package Structure
-  **entity** → Person, Student, Trainer, Course, Enrollment
- **service** → StudentService, CourseService, EnrollmentService
- **ui** → Main.java (menu-driven console UI)
- **exception** → EntityNotFoundException
- **utility** → IdGenerator

## How to Compile and Run

### Using IntelliJ IDEA
1. Open the project folder in IntelliJ.
2. Navigate to `src/com/airtribe/learntrack/ui/Main.java`.
3. Right-click `Main.java` → **Run 'Main.main()'**.

### Using terminal / command line
From the project's `src` directory:

```bash
# Compile
javac com/airtribe/learntrack/entity/*.java com/airtribe/learntrack/util/*.java com/airtribe/learntrack/exception/*.java com/airtribe/learntrack/service/*.java com/airtribe/learntrack/ui/*.java

# Run
java com.airtribe.learntrack.ui.Main
```
