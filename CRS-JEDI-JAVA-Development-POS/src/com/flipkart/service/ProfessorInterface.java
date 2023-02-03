package com.flipkart.service;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

import java.util.List;

public interface ProfessorInterface {
    public int login(String professorname, String password);

    public List<Course> viewCourse(int semID);

    public void registerCourse(int profID, Course courseName);

    public void deregisterCourse(Course courseName);

    public void viewEnrolledStudents(int semID , String courseName);

    public void addGrade(Professor professor , int courseID , int studentID , int score);
}
