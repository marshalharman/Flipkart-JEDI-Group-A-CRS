package com.flipkart.service;
import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotFoundByNameException;
import com.flipkart.exception.CourseNotFoundException;

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
     * @param courseName
     * @param semID
     */
    public void registerCourse(int profID, String courseName, int semID) throws CourseNotFoundException, CourseNotFoundByNameException;
    /**
     * Method to de-register the professor from the course
     * @param profID
     * @param courseName
     * @param semID
     */
    public void deregisterCourse(int profID, String courseName, int semID) throws CourseNotFoundException, CourseNotFoundByNameException;
    /**
     * Method to students enrolled in a course
     * @param semID
     * @param courseName
     * @return List of enrolled students in the course
     */
    public List<Student> viewEnrolledStudents(int semID , String courseName);
    /**
     * Method to assign grades to a student enrolled in the course
     * @param profID
     * @param courseName
     * @param studentID
     * @param grade
     */
    public void addGrade(int profID , String courseName, int studentID , String grade);

    public void  viewRegisteredCourses(int profID);
}
