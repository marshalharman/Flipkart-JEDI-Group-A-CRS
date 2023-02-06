package com.flipkart.dao;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

import java.util.*;
public interface ProferssorDAO {

    public boolean registerCourseForProfessor(int profID, String courseName, int semID);
    public List<Course> getCoursesByProfessor(int profID);
    public Boolean addGrade(int studentId,String courseCode,String grade);
    public Professor getProfessorById(int profID);

    public int getSemIDbyCourseID(int courseID);
    public Course getCourseByName(String courseName);

}
