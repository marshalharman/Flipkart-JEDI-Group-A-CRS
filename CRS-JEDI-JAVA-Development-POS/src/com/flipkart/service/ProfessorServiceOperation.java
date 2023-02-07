package com.flipkart.service;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.Course;
import com.flipkart.dao.ProfessorDAOImpl;
import com.flipkart.data.*;

import java.awt.desktop.SystemEventListener;
import java.util.*;

/**
 *
 * Implementations of Professor Interface
 *
 */
public class ProfessorServiceOperation implements ProfessorInterface {

    ProfessorDAOImpl professorDAO = new ProfessorDAOImpl();

    /**
     * Method to view course to the professor
     * @param semID
     * @return List of courses in the semester
     */
    public List<Course> viewCourse(int semID){

        List<Course> courseList = professorDAO.viewCoursesBySemID(semID);

        return courseList;
    }
    /**
     * Method to register the professor to the course
     * @param profID
     * @param courseID
     * @param semID
     */
    public void registerCourse(int profID, int courseID, int semID){
        professorDAO.registerCourseForProfessor(profID, courseID, semID);
    }
    /**
     * Method to de-register the professor from the course
     * @param profID
     * @param courseID
     * @param semID
     */
    public void deregisterCourse(int profID, int courseID, int semID){
        professorDAO.deregisterCourseForProfessor(profID, courseID);
    }
    /**
     * Method to students enrolled in a course
     * @param semID
     * @param courseID
     * @return List of enrolled students in the course
     */
    public List<Student> viewEnrolledStudents(int semID , int courseID){
        List<Student> students = professorDAO.viewEnrolledStudents(courseID);

        return students;
    }
    /**
     * Method to assign grades to a student enrolled in the course
     * @param profID
     * @param courseID
     * @param studentID
     * @param grade
     */
    public void addGrade(int profID , int courseID, int studentID , String grade){

        professorDAO.addGrade(studentID, courseID, grade);

    }
}
