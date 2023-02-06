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

    public int login(String professorName, String password){
        List<Professor> professorList = Data.professors;
        Course course = new Course();

        int userID = -1;
        for(Professor p:professorList)
        {
            if(p.getName().equals(professorName) && p.getPassword().equals(password)) {
                userID = p.getUserID();
                break;
            }
        }
        return userID;
    }

    public List<Course> viewCourse(int semID){

        List<Course> courseList = professorDAO.viewCoursesBySemID(semID);

        return courseList;
    }

    public void registerCourse(int profID, String courseName, int semID){
        professorDAO.registerCourseForProfessor(profID, courseName, semID);
    }

    public void deregisterCourse(int profID, String courseName, int semID){
        professorDAO.deregisterCourseForProfessor(profID, courseName);
    }

    public List<Student> viewEnrolledStudents(int semID , String courseName){
        List<Student> students = professorDAO.viewEnrolledStudents(courseName);

        return students;
    }

    public void addGrade(int profID , String courseName, int studentID , String grade){

        professorDAO.addGrade(studentID, courseName, grade);

    }
}
