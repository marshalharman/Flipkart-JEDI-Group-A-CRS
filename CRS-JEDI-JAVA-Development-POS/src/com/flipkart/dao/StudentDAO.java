package com.flipkart.dao;
import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.exception.DuplicateUserException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * Interface for Student Dao Operations
 *
 */
public interface StudentDAO {
    /**
     * Method to register a student using sql commands
     * @param studentID
     * @param name
     * @param address
     * @param username
     * @param password
     * @param branch
     * @param degree
     * @throws DuplicateUserException
     */
    public void register(int studentID, String name, String address, String username, String password, String branch, String degree) throws DuplicateUserException;
    /**
     * Method to get list of all semesters
     * @return List of all semesters
     */
    public List<Integer> getSemesterList();
    /**
     * Method to get all courses in a given semester
     * @param semID
     * @return List of all courses in a given semester
     */
    public List<Course> getCourses(int semID);
    /**
     * Method to number of students enrolled in a copurse
     * @param courseID
     * @return map of all courses with number of students enrolled
     */
    public HashMap<Integer,Integer> getCourseEnrollmentCount(int courseID);
    /**
     * Method to add a course to catalog using sql commands
     * @param courseID
     * @param studentID
     * @param semID
     */
    public void registerCourses(int studentID, List<Integer> courseID, int semID);
    /**
     * Method to remove a course from a smeseter using sql commands
     * @param courseID
     * @param studentID
     */
    public void dropCourse(int studentID, int courseID);
    /**
     * Method to get list of courses a student is registered in
     * @param studentID
     * @return list opf courses a student is registered in
     */
    public List<Course> getRegisteredCourses(int studentID);
    /**
     * Method to get grades in courses a student is enrolled in
     * @param studentID
     * @return map of courses with grades a student is enrolled in
     */
    public HashMap<Course, String> viewGrades(int studentID);
    /**
     * Method to student details by id
     * @param studentID
     * @return student id with all the details
     */
    public Student getStudentByID(int studentID);
}
