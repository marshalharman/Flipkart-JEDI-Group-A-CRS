package com.flipkart.service;

import com.flipkart.bean.Course;

import java.util.HashMap;
import java.util.List;

import com.flipkart.bean.Student;

public interface StudentInterface {
    public void register(int studentId, String name, String address, String username, String password, String branch);
    public List<Integer> getSemesterList(int studentID);

    public List<Course> getCourses(int semID);

    public HashMap<Integer, Integer> getCourseEnrollmentCount(int courseID);
//    public void addCourse(Student student);
//    public void removeCourse(Student student, int choice);
    public void dropCourse(Student student, String courseName);
    public void submitPreferences(Student student);
    public void getRegisteredCourses(Student student);
    public void viewGrades(int studentId);

}
