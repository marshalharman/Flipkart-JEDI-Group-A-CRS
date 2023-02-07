package com.flipkart.service;

import com.flipkart.bean.Course;

import java.util.HashMap;
import java.util.List;

import com.flipkart.bean.Student;
/**
 *
 * Interface for Student Service Operations
 *
 */
public interface StudentInterface {
    /**
     * Method to register a student, although student can't login until it's approved by admin
     * @param studentId
     * @param name
     * @param address
     * @param username
     * @param password
     * @param branch
     * @param degree
     */
    public void register(int studentId, String name, String address, String username, String password, String branch, String degree);
    /**
     * Method to assign respective semID to the student
     * @param studentID
     * @param semID
     */
    public void setSemID(int studentID, int semID);
    /**
     * Method to get list of all semesters
     * @param studentID
     * @return List of all semesters
     */
    public List<Integer> getSemesterList(int studentID);
    /**
     * Method to get all courses in a given semester
     * @param semID
     * @return List of all courses in a given semester
     */
    public List<Course> getCourses(int semID);
    /**
     * Method for a student to drop a course they are enrolled in
     * @param studentID
     * @param courseID
     */
    public void dropCourse(int studentID, int courseID);
    /**
     * Method for a student to submit course preference
     * @param studentID
     * @param primaryCourses
     * @param alternateCourses
     */
    public void submitPreferences(int studentID, List<Course> primaryCourses, List<Course> alternateCourses);
    /**
     * Method for a student to get all the courses they are registered in
     * @param studentID
     */
    public void getRegisteredCourses(int studentID);
    /**
     * Method to view grades assigned to a student in respective courses
     * @param studentId
     * @return Map of courses and respective grades assigned
     */
    public HashMap<Course, String> viewGrades(int studentId);

}
