# Design Notes

## Why ArrayList instead of array

If Arrays are used then it has to be created with fixed size for which
Admin or the operator of the APP needs to know in advance how many students,courses,enrollments 
which this project has no guarantee

ArrayList was used instead because:
- It grows dynamically
- It provides built-in methods (`add`, `remove`, `removeIf`, iteration via for-each)
  that would otherwise need to be hand-written for a plain array (e.g. shifting
  elements after a removal).
- It integrates directly with the Collections framework, making operations like
  filtering (e.g. `getAllEnrollmentsOfStudent`) simple to write with a for-each loop.

Example: `StudentService`, `CourseService`, and `EnrollmentService` each hold their
data in a `private final List<X> ... = new ArrayList<>();` field, since the number
of students/courses/enrollments is not known in advance and changes as the
application runs.

## Where static members were used and why

Static members were used specifically in `IdGenerator` (in the `utility` package):

```java
private static int studentIdCounter = 100;
private static int courseIdCounter = 10000;
private static int enrollmentIdCounter = 0;

public static int getNextStudentId() { return ++studentIdCounter; }
public static int getNextCourseId()  { return ++courseIdCounter; }
public static int getNextEnrollmentId() { return ++enrollmentIdCounter; }
```

These are `static` because ID generation must be **shared across the entire
application**, not tied to any single object. If `studentIdCounter` were an
instance field instead, every new `Student` object would carry its own separate
counter starting from the same seed value, which would produce duplicate IDs
instead of a single, ever-increasing sequence. Making it `static` ensures there is
exactly one counter, shared by every part of the program that generates a new ID,
regardless of how many `Student`, `Course`, or `Enrollment` objects are created.

`IdGenerator` also has a `private` constructor, since the class is only ever used
through its static methods and is never meant to be instantiated.

## Where inheritance was used and what was gained from it

A small hierarchy was introduced with `Person` as a base class, and `Student` and
`Trainer` extending it:

- **`super(...)` in constructors** — both `Student` and `Trainer` call
  `super(id, firstName, lastName, email)` to let `Person`'s constructor handle
  the fields common to every person, instead of duplicating that assignment
  logic in each subclass.
- **Method overriding** — `Person` defines a default `getDisplayName()`
  (`firstName + " " + lastName`). `Student` and `Trainer` each `@Override` it to
  add their own specialized detail (batch for a student, specialty for a
  trainer).

**What is gained is**
- **Code reuse** — common fields (`id`, `firstName`, `lastName`, `email`) and
    their getters/setters are written once, in `Person`, instead of being
    duplicated in every subclass.