package com.flipkart.service;

import com.flipkart.bean.Course;

import java.util.HashMap;
import java.util.List;

import com.flipkart.bean.Student;

public interface StudentInterface {
    public void register();

    public void setSemID(int studentID, int semID);
//    public int login(String username,String pa
    public List<Integer> getSemesterList(int studentID);

    public List<Course> getCourses(int semID);
    public void addCourse(Student student);
    public void removeCourse(Student student);
    public void dropCourse(int studentID, int courseID);
    public void submitPreferences(Student student);
    public void getRegisteredCourses(Student student);
    public HashMap<Course, String> viewGrades(int studentId);

}
