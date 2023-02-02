package com.flipkart.service;

import com.flipkart.bean.Course;

import java.util.List;

import com.flipkart.bean.Student;

public interface StudentInterface {
    public void register();
    public int login(String username,String password);
    public void semesterRegister(Student student);
    public List<Course> getCourses(int semID);
    public void addCourse(Student student);
    public void removeCourse(Student student);
    public void dropCourse();
    public void getRegisteredCourses();
    public void viewGrades();

}
