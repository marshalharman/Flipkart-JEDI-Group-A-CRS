package com.flipkart.service;

public interface ProfessorInterface {
    public int login(String professorname, String password);

    public void viewCourse();

    public void registerCourse();

    public void deregisterCourse();

    public void viewEnrolledStudents();

    public void addGrade();
}
