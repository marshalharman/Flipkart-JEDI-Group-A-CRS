package com.flipkart.service;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.List;
/**
 *
 * Interface for Professor Service Operations
 *
 */
public interface ProfessorInterface {

    /**
     * Method to view course to the professor
     * @param semID
     * @return List of courses in the semester
     */
    public List<Course> viewCourse(int semID);
    /**
     * Method to register the professor to the course
     * @param profID
     * @param courseID
     * @param semID
     */
    public void registerCourse(int profID, int courseID, int semID);
    /**
     * Method to de-register the professor from the course
     * @param profID
     * @param courseID
     * @param semID
     */
    public void deregisterCourse(int profID, int courseID, int semID);
    /**
     * Method to students enrolled in a course
     * @param semID
     * @param courseID
     * @return List of enrolled students in the course
     */
    public List<Student> viewEnrolledStudents(int semID , int courseID);
    /**
     * Method to assign grades to a student enrolled in the course
     * @param profID
     * @param courseID
     * @param studentID
     * @param grade
     */
    public void addGrade(int profID , int courseID, int studentID , String grade);
}
