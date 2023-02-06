package com.flipkart.service;

import com.flipkart.bean.Course;

import java.util.HashMap;
import java.util.List;

import com.flipkart.bean.Student;

public interface StudentInterface {
    public void register(int studentId, String name, String address, String username, String password, String branch, String degree);
    public void setSemID(int studentID, int semID);
    public List<Integer> getSemesterList(int studentID);
    public List<Course> getCourses(int semID);

    public void dropCourse(int studentID, int courseID);
    public void submitPreferences(int studentID, List<Course> primaryCourses, List<Course> alternateCourses);
    public void getRegisteredCourses(int studentID);
    public HashMap<Course, String> viewGrades(int studentId);

}
