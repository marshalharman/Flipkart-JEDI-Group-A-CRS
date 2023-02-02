package com.flipkart.service;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.data.Data;

import java.util.List;
import java.util.Scanner;

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
        Data d= new Data();
        Scanner sc = new Scanner(System.in);
        List<Student> unapprovedStudents =d.unapprovedStudents;
        System.out.println("List of unapproved students : ");
        for(Student s: unapprovedStudents){
            System.out.println(s.getUserID() + " - " + s.getUsername());
        }

        System.out.println("Enter the student ID to approve : ");
        int studentID = Integer.parseInt(sc.nextLine());

        for(Student s: unapprovedStudents){
            if( s.getUserID() == studentID ){
                d.students.add(s);
                d.unapprovedStudents.remove(s);
                break;
            }
        }

    }

    public boolean addProfessor(Professor p) {
        List<Professor> professorsList = Data.professors;
        for(Professor prof : professorsList)
        {
            if(p.getUserID()==prof.getUserID())return false;
        }
        Data.professors.add(p);
        return true;
    }

    public boolean removeProfessor(Professor p) {
//        List<Professor> professorsList = Data.professors;
//        for(Professor prof : professorsList)
//        {
//            if(p.getUserID()==prof.getUserID())
//            {
//                Data.
//                return true;
//            }
//        }
        return false;
    }

    public void addCourse() {


    }

    public void removeCourse() {

    }

    public void generateGradeCard() {
        
    }
}
