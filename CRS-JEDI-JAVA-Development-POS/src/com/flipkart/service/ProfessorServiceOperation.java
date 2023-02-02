package com.flipkart.service;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.Course;
import com.flipkart.data.*;
import java.util.*;

public class ProfessorServiceOperation implements ProfessorInterface {
    public int login(String professorname, String password){
        List<Professor> professorList = Data.professors;
        Course course = new Course();

        int userID = -1;
        for(Professor p:professorList)
        {
            if(p.getName().equals(professorname) && p.getPassword().equals(password)) {
                userID = p.getUserID();
                break;
            }
        }
        return userID;
    }

    public List<Course> viewCourse(int semID){
        List<Course> c = Data.semCourseList.get(semID);
        System.out.println("List of courses");
        for(int i=0;i<c.size();i++)
        {
            System.out.println(c.get(i)+"\n");
        }
        return c;
    }

    public void registerCourse(int profID, Course courseName){
        courseName.setProfID(profID);
    }

    public void deregisterCourse(Course courseName){
        courseName.setProfID(-1);
    }

    public void viewEnrolledStudents(){
        

    }

    public void addGrade(){

    }
}
