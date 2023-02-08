package com.flipkart.bean;

import java.util.List;

public class GradeCard {
    private int studentID;
    private int semID;
    private double cgpa;
    private List<Integer> registeredCourseIDs;
    private List<Grade> grades;

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getSemID() {
        return semID;
    }

    public void setSemID(int semID) {
        this.semID = semID;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public List<Integer> getRegisteredCourseIDs() {
        return registeredCourseIDs;
    }

    public void setRegisteredCourseIDs(List<Integer> registeredCourseIDs) {
        this.registeredCourseIDs = registeredCourseIDs;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
