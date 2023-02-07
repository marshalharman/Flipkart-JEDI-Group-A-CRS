package com.flipkart.dao;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.*;
public interface ProferssorDAO {

    public List<Course> viewCoursesBySemID(int semID);
    public boolean registerCourseForProfessor(int profID, int courseID, int semID);
    public boolean deregisterCourseForProfessor(int profID, int courseID);
    public List<Student> viewEnrolledStudents(int courseID);
    public Student getStudentByID(int studentID);
    public List<Course> getCoursesByProfessor(int profID);
    public Boolean addGrade(int studentId, int courseID, String grade);
    public int getSemIDbyCourseID(int courseID);
    public Course getCourseByName(String courseName);
    public Professor getProfessorById(int profID);




}
