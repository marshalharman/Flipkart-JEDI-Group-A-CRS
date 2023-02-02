package com.flipkart.service;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.data.*;
import java.util.*;

public class ProfessorServiceOperation implements ProfessorInterface {
    public int login(String professorname, String password){
        List<Professor> professorList = Data.professors;
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

    public void viewCourse(){

    }

    public void registerCourse(){

    }

    public void deregisterCourse(){

    }

    public void viewEnrolledStudents(){

    }

    public void addGrade(){

    }
}
