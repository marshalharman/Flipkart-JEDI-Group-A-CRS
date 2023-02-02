package com.flipkart.service;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.data.Data;

import java.util.*;

public class AdminServiceOperation implements AdminInterface {

    Scanner sc=new Scanner(System.in);
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
        Data d= new Data();
        List<Student> unapprostudents =d.unapprovedStudents;
        for(Student s:unapprostudents)
        {
            s.setApproved(true);
        }
        d.deleteandAddUnApprovedStudents();
    }

    public boolean addProfessor(Professor p) {
        Data d= new Data();
        List<Professor> professorsList= d.professors;
        for(Professor prof : professorsList)
        {
            if(p.getUserID()==prof.getUserID())
            {
                System.out.println("Already exists\n");
                return false;
            }
        }
        d.addProfessor(p);
        System.out.println("Added Professor");
        return true;
    }

    public boolean removeProfessor(String profName) {
        Data d=new Data();
        List<Professor> professorsList = d.professors;
        for(Professor prof : professorsList)
        {
            if(profName.equals(prof.getName()))
            {
                System.out.println("Professor deleted successfully\n");
                d.deleteProfessor(prof);
                return true;
            }
        }
        System.out.println("Professor doesn't exist\n");
        return false;
    }

    public void addCourse() {
        int semId=sc.nextInt();
        int courseId=sc.nextInt();
        String courseName=sc.next();

        Data d=new Data();
        List<Course> temp=d.semCourseList.get(semId);
        Course c1=new Course();
        c1.setCourseID(courseId);
        c1.setCourseName(courseName);
        temp.add(c1);
        d.semCourseList.put(semId,temp);
    }

    public void removeCourse() {

    }

    public void generateGradeCard() {
        
    }
}
