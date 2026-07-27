package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.util.IdGenerator;

public class Student extends Person {
    private String batch;
    private boolean active;

    public Student(String firstName, String lastName, String email, String batch) {
        super(IdGenerator.getNextStudentId(),firstName,lastName,email);
        this.id = IdGenerator.getNextStudentId();
        this.batch = batch;
        this.active = true;
    }

    // Overloaded constructor 1: without batch (batch assigned later)
    public Student(String firstName, String lastName, String email) {
        super(IdGenerator.getNextStudentId(),firstName,lastName,email);
        this.id = IdGenerator.getNextStudentId();
        this.batch = "UNASSIGNED";
        this.active = true;
    }

    public int getId() {
        return id;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getDisplayName() {
        return getFirstName() + " " + getLastName() + " Batch: " + batch + ")";
    }
}
