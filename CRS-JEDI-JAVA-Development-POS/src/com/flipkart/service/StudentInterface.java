package com.flipkart.service;

import com.flipkart.bean.Course;

import java.util.List;

import com.flipkart.bean.Student;

public interface StudentInterface {
    public void register(String name, String studentID, String password);
    public boolean login(int studentID,String password);
    public void semesterRegister(Student student);
    public void getCourses();
    public boolean login(String studentname,String password);
    public void semesterRegister();
    public List<Course> getCourses(int semID);
    public void addCourse();
    public void dropCourse();
    public void getRegisteredCourses();
    public void payFees();
    public void viewGrades();

}
