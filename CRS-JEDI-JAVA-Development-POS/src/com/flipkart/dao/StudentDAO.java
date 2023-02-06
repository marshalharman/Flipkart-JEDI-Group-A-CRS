package com.flipkart.dao;

import com.flipkart.bean.Course;

import java.util.HashMap;
import java.util.List;

public interface StudentDAO {

    public List<Integer> getSemesterList();

//    public void setSemester(int studentID, int semID);

    public List<Course> getCourses(int semID);


    public int getCourseEnrollmentCount(int courseID);

    // registerCourse/SubmitPreference()
    public void registerCourses(int studentID, List<Integer> courseID, int semID);

    // dropCourse()
    public void dropCourse(int studentID, int courseID);

    // getRegisteredCourses()
    public void getRegisteredCourses(int studentID);

    // viewGrades()
    public HashMap<Course, String> viewGrades(int studentID);
}
