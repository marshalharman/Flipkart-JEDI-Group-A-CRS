package com.flipkart.service;

import com.flipkart.bean.Course;

import java.util.List;

import com.flipkart.bean.Student;

public interface StudentInterface {
    public void register();
    public boolean login(String username,String password);
    public void semesterRegister(Student student);
    public List<Course> getCourses(int semID);
    public void addCourse();
    public void dropCourse();
    public void getRegisteredCourses();
    public void payFees();
    public void viewGrades();

}
