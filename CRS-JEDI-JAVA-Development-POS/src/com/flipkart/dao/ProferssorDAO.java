package com.flipkart.dao;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotFoundByNameException;
import com.flipkart.exception.CourseNotFoundException;

import java.util.*;

/**
 *
 * Interface for Professor Dao Operations
 *
 */
public interface ProferssorDAO {
    /**
     * Method to view courses in a sem using sql command
     * @param semID
     * @return list of courses in a semester
     */
    public List<Course> viewCoursesBySemID(int semID);
    /**
     * Method to register a professor to a course
     * @param profID
     * @param courseName
     * @param semID
     * @return a boolean indicating if professor is registered from a course
     */
    public boolean registerCourseForProfessor(int profID, String courseName, int semID) throws CourseNotFoundByNameException;
    /**
     * Method to de- register a professor to a course
     * @param profID
     * @param courseName
     * @return a boolean indicating if professor is de-registered from a course
     */
    public boolean deregisterCourseForProfessor(int profID, String courseName) throws CourseNotFoundByNameException;
    /**
     * Method to view enrolled students in a course
     * @param courseName
     * @return list of enrolled students in a course
     */
    public List<Student> viewEnrolledStudents( String courseName) throws CourseNotFoundByNameException;
    /**
     * Method to get student details with id
     * @param studentID
     * @return student object with all details
     */
    public Student getStudentByID(int studentID);
    /**
     * Method to list of courses taught by a professor
     * @param profID
     * @return list of courses taught by a professor
     */
    public List<Course> getCoursesByProfessor(int profID);
    /**
     * Method to assign grade to students ina course
     * @param studentId
     * @param courseName
     * @param grade
     * @return boolean indicating if grades are assigned to the students
     */
    public Boolean addGrade(int studentId, String courseName, String grade);
    /**
     * Method to get in which sem the course is being offered
     * @param courseID
     * @return sem number in which the course is being offered
     */
    public int getSemIDbyCourseID(int courseID);
    /**
     * Method to get course details by name
     * @param courseName
     * @return course object with all the details
     */
    public Course getCourseByName(String courseName);
    /**
     * Method to get Course details by id
     * @param courseID
     * @return course object with all the details
     */
    public Course getCourseByID(int courseID);
    /**
     * Method to get professor details by id
     * @param profID
     * @return professor object with all details
     */
    public Professor getProfessorById(int profID);

}
