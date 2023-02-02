package com.flipkart.service;

import com.flipkart.bean.Course;

import java.util.List;

public interface StudentInterface {
    public void register(String name, String studentID, String password);
    public boolean login(String studentname,String password);
    public void semesterRegister();
    public List<Course> getCourses(int semID);
    public void addCourse();
    public void dropCourse();
    public void getRegisteredCourses();
    public void viewGrades();

}
