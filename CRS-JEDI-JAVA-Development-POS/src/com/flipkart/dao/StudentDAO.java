package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.HashMap;
import java.util.List;

public interface StudentDAO {

    public List<Integer> getSemesterList();

//    public void setSemester(int studentID, int semID);

    public List<Course> getCourses(int semID);


    public HashMap<Integer,Integer> getCourseEnrollmentCount(int courseID);

    // registerCourse/SubmitPreference()
    public void registerCourses(int studentID, List<Integer> courseID, int semID);

    // dropCourse()
    public void dropCourse(int studentID, int courseID);

    // getRegisteredCourses()
    public List<Course> getRegisteredCourses(int studentID);

    // viewGrades()
    public HashMap<Course, String> viewGrades(int studentID);

    public Student getStudentByID(int studentID);
}
