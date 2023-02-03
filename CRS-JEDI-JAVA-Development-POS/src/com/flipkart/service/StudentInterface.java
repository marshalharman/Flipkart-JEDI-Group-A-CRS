package com.flipkart.service;

import com.flipkart.bean.Course;

import java.util.List;

import com.flipkart.bean.Student;

public interface StudentInterface {
    public void register(int studentID, String name, String address, String username, String password, String branch);
    public int login(String username,String password);
    public void semesterRegister(Student student);
    public List<Course> getCourses(int semID);
    public void addCourse(Student student, int choice, int semID, String courseName);
    public void removeCourse(Student student, int choice);
    public void dropCourse(Student student, String courseName);
    public void submitPreferences(Student student);
    public void getRegisteredCourses(Student student, int userId);
    public void viewGrades(int studentId);

}
