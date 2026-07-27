package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.util.IdGenerator;

public class Trainer extends Person{
    private String specialty;

    public Trainer(int id, String firstName, String lastName, String email, String specialty) {
        super(id, firstName, lastName, email);
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String getDisplayName() {
        return getFirstName() + " " + getLastName() + " Specialized in : " + specialty + ")";
    }
}
