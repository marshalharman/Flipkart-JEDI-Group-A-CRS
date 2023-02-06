package com.flipkart.service;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.List;

public interface ProfessorInterface {
    public int login(String professorName, String password);

    public List<Course> viewCourse(int semID);

    public void registerCourse(int profID, String courseName, int semID);

    public void deregisterCourse(int profID, String courseName, int semID);

    public List<Student> viewEnrolledStudents(int semID , String courseName);

    public void addGrade(int profID , String courseName, int studentID , String grade);
}
