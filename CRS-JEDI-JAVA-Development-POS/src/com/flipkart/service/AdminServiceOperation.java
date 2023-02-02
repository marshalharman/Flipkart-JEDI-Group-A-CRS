package com.flipkart.service;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.data.Data;

import java.util.List;

public class AdminServiceOperation implements AdminInterface {

    public int login(String adminName, String password) {
        Data d= new Data();
        List<Admin> adminsList= d.admins;
        int userId = -1;
        for(Admin s:adminsList)
        {
            if(s.getName().equals(adminName) && s.getPassword().equals(password)) {
                userId = s.getUserID();
                break;
            }
        }
        return userId;
    }

    public void approveStudentRegistration() {
        List<Student> unapprostudents =Data.unapprovedStudents;
        for(Student s:unapprostudents)
        {
            s.setApproved(true);
            Data.students.add(s);
        }
        Data.unapprovedStudents.clear();

    }

    public boolean addProfessor(Professor p) {
        List<Professor> professorsList= Data.professors;
        for(Professor prof : professorsList)
        {
            if(p.getUserID()==prof.getUserID())
            {
                System.out.println("Already exists\n");
                return false;
            }
        }
        Data.professors.add(p);
        System.out.println("Added Professor");
        return true;
    }

    public boolean removeProfessor(String profName) {
        List<Professor> professorsList = Data.professors;
        for(Professor prof : professorsList)
        {
            if(profName.equals(prof.getName()))
            {
                System.out.println("Professor deleted successfully\n");
                Data.professors.remove(prof);
                return true;
            }
        }
        System.out.println("Professor doesn't exist\n");
        return false;
    }

    public void addCourse() {

    }

    public void removeCourse() {

    }

    public void generateGradeCard() {
        
    }
}
