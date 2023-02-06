package com.flipkart.service;

import com.flipkart.bean.Course;

import java.util.HashMap;
import java.util.List;

import com.flipkart.bean.Student;

public interface StudentInterface {
    public void register(int studentId, String name, String address, String username, String password, String branch);
    public void setSemID(int studentID, int semID);
    public List<Integer> getSemesterList(int studentID);
    public List<Course> getCourses(int semID);
    public HashMap<Integer, Integer> getCourseEnrollmentCount(int courseID);
    public void dropCourse(int studentID, int courseID);
    public void submitPreferences(Student student);
    public void getRegisteredCourses(Student student);
    public HashMap<Course, String> viewGrades(int studentId);

}
