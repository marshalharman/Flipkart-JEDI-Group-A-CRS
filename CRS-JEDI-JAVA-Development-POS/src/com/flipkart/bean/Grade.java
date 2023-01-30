package com.flipkart.bean;

public class Grade {
    private char score;
    private int courseID;
    private int studentID;

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public char getScore() {
        return score;
    }

    public void setScore(char score) {
        this.score = score;
    }
}
