package com.flipkart.service;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.Course;
import com.flipkart.dao.ProfessorDAOImpl;
import com.flipkart.data.*;

import java.awt.desktop.SystemEventListener;
import java.util.*;

public class ProfessorServiceOperation implements ProfessorInterface {

    ProfessorDAOImpl professorDAO = new ProfessorDAOImpl();

    public List<Course> viewCourse(int semID){

        List<Course> courseList = professorDAO.viewCoursesBySemID(semID);

        return courseList;
    }

    public void registerCourse(int profID, int courseID, int semID){
        professorDAO.registerCourseForProfessor(profID, courseID, semID);
    }

    public void deregisterCourse(int profID, int courseID, int semID){
        professorDAO.deregisterCourseForProfessor(profID, courseID);
    }

    public List<Student> viewEnrolledStudents(int semID , int courseID){
        List<Student> students = professorDAO.viewEnrolledStudents(courseID);

        return students;
    }

    public void addGrade(int profID , int courseID, int studentID , String grade){

        professorDAO.addGrade(studentID, courseID, grade);

    }
}
