package com.flipkart.service;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.Course;
import com.flipkart.dao.ProfessorDAOImpl;
import com.flipkart.exception.CourseNotFoundByNameException;
import com.flipkart.exception.CourseNotFoundException;

import java.awt.desktop.SystemEventListener;
import java.util.*;

public class ProfessorServiceOperation implements ProfessorInterface {

    ProfessorDAOImpl professorDAO = new ProfessorDAOImpl();

    public List<Course> viewCourse(int semID){

        List<Course> courseList = professorDAO.viewCoursesBySemID(semID);

        return courseList;
    }

    public void registerCourse(int profID, String courseName, int semID) {
        try {
            professorDAO.registerCourseForProfessor(profID, courseName, semID);
        }
        catch (CourseNotFoundByNameException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void deregisterCourse(int profID, String courseName, int semID) throws CourseNotFoundException, CourseNotFoundByNameException {
        try {
            professorDAO.deregisterCourseForProfessor(profID, courseName);
        }
        catch (CourseNotFoundByNameException exception){
            System.out.println(exception.getMessage());
        }
    }

    public List<Student> viewEnrolledStudents(int semID , String courseName){

        List<Student> students = new ArrayList<>();

        try {
            students = professorDAO.viewEnrolledStudents(courseName);
        }
        catch (CourseNotFoundByNameException exception){
            System.out.println(exception.getMessage());
        }

        return students;
    }

    public void addGrade(int profID , String courseName, int studentID , String grade){

        professorDAO.addGrade(studentID, courseName, grade);

    }

    public void  viewRegisteredCourses(int profID){
        List<Course> registeredCourses = professorDAO.getCoursesByProfessor(profID);

        Formatter fmt = new Formatter();
        fmt.format("%15s %15s\n", "CourseID", "CourseName");
        for(Course course: registeredCourses){
            fmt.format("%14s %14s\n",course.getCourseID() , course.getCourseName());
        }
        System.out.println(fmt);

    }
}
