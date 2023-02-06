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

        for (Course course: courseList){
            System.out.println(course.getCourseID() + " - " + course.getCourseName());
        }

        return courseList;
    }

    public void registerCourse(int profID, String courseName, int semID){
        professorDAO.registerCourseForProfessor(profID, courseName, semID);
    }

    public void deregisterCourse(int profID, String courseName, int semID){
        professorDAO.deregisterCourseForProfessor(profID, courseName);
    }

    public void viewEnrolledStudents(int semID , String courseName){
        List<Student> students = professorDAO.viewEnrolledStudents(courseName);

        for(Student student : students){
            System.out.println(student.getUserID() + " - " + student.getName());
        }

    }

    public void addGrade(int profID , int courseID , int studentID , int score){
//      Scanner sc = new Scanner(System.in);



        for(int semID : Data.semCourseList.keySet()){
            for(Course c: Data.semCourseList.get(semID) ){
                if( c.getCourseID() == courseID && c.getProfID() != profID ){
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

        if( Data.gradeList.get(studentID) == null ){
            Data.gradeList.put(studentID, new ArrayList<Grade>() );
        }
        Data.gradeList.get(studentID).add(grade);

    }
}
