package com.flipkart.service;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.List;

public interface ProfessorInterface {

    public List<Course> viewCourse(int semID);

    public void registerCourse(int profID, int courseID, int semID);

    public void deregisterCourse(int profID, int courseID, int semID);

    public List<Student> viewEnrolledStudents(int semID , int courseID);

    public void addGrade(int profID , int courseID, int studentID , String grade);
}
