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
        System.out.println("Enter SemId: ");
        int semId=sc.nextInt();
        System.out.println("Enter courseId: ");
        int courseId=sc.nextInt();
        System.out.println("Enter courseName: ");
        String courseName=sc.next();

        List<Course> temp=Data.semCourseList.get(semId);
        Course c1=new Course();
        c1.setCourseID(courseId);
        c1.setCourseName(courseName);
        temp.add(c1);
        Data.semCourseList.put(semId,temp);
        System.out.println(courseName + " added successfully.");
    }

    public void removeCourse() {
        System.out.println("Enter SemId: ");
        int semId=sc.nextInt();
        System.out.println("Enter courseId: ");
        int courseId=sc.nextInt();
        //String courseName=sc.next();

        List<Course> temp=Data.semCourseList.get(semId);
        for(Course c1:temp){
            if(c1.getCourseID()==courseId){
                temp.remove(c1);
                break;
            }
        }
        Data.semCourseList.put(semId,temp);
        System.out.println("removed successfully.");
    }

    public void generateGradeCard() {
        
    }
}
