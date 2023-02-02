package com.flipkart.service;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.Course;
import com.flipkart.data.*;

import java.awt.desktop.SystemEventListener;
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
        System.out.println("List of courses : ");
        for( Course course : c ){
            System.out.println(course.getCourseID() + " - " + course.getCourseName());
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
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Semester ID : ");
        int semID = Integer.parseInt(sc.nextLine());

        viewCourse(semID);

        System.out.println("Enter course name : ");
        String courseName = sc.nextLine();

        Course course = null;
        for( Course c : Data.semCourseList.get(semID) ){
            if( c.getCourseName().equalsIgnoreCase(courseName) ){
                course = c; break;
            }
        }

        List<Integer> idList = new ArrayList<Integer>();

        for( Integer studentID : Data.registeredCourses.keySet() ){
            if( Data.registeredCourses.get(studentID).contains(course) ){
                idList.add(studentID);
            }
        }

        for(Integer id: idList){
            for(Student s: Data.students){
                if( s.getUserID() == id){
                    System.out.println(s.getUserID() + " - " + s.getName() );
                    break;
                }
            }
        }
    }

    public void addGrade(Professor professor){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter course ID : ");
        int courseID = Integer.parseInt(sc.nextLine());

        System.out.println("Enter student ID : ");
        int studentID = Integer.parseInt(sc.nextLine());

        System.out.println("Enter score : ");
        int score = Integer.parseInt(sc.nextLine());

        for(int semID : Data.semCourseList.keySet()){
            for(Course c: Data.semCourseList.get(semID) ){
                if( c.getCourseID() == courseID && c.getProfID() != professor.getUserID() ){
                    System.out.println("This course is not taken by you. Grade cannot be added");
                    return;
                }
            }
        }

        boolean isStudent = false;
        boolean isStudentRegistered = false;
        for(Student s: Data.students){
            if( s.getUserID() == studentID ){
                isStudent = true;
                for(Course c: Data.registeredCourses.get(studentID) ){
                    if( c.getCourseID() == courseID ){
                        isStudentRegistered = true;
                        break;
                    }
                }
                break;
            }
        }

        if(!isStudent){
            System.out.println("Student does not exist. Grade cannot be added");
            return;
        }

        if(!isStudentRegistered){
            System.out.println("Student is not registered for the course. Grade cannot be added");
            return;
        }

        Grade grade = new Grade();

        grade.setCourseID(courseID);
        grade.setStudentID(studentID);
        grade.setScore(score);

        Data.gradeList.get(studentID).add(grade);

    }
}
