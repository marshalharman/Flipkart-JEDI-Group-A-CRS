package com.flipkart.service;
import com.flipkart.bean.Course;

import java.util.List;

public interface ProfessorInterface {
    public int login(String professorname, String password);

    public List<Course> viewCourse(int semID);

    public void registerCourse(int profID, Course courseName);

    public void deregisterCourse(Course courseName);

    public void viewEnrolledStudents();

    public void addGrade();
}
