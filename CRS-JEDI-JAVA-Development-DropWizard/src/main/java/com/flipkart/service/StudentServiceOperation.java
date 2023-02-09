package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.dao.StudentDAO;
import com.flipkart.dao.StudentDAOImpl;
import com.flipkart.exception.DuplicateUserException;
import org.glassfish.jersey.process.internal.RequestScoped;

import java.util.*;

public class StudentServiceOperation implements StudentInterface {

    StudentDAOImpl studentDAO = new StudentDAOImpl();
    Scanner sc = new Scanner(System.in);
    public void register(int studentId, String name, String address, String username, String password, String branch, String degree){
        try {
            studentDAO.register(studentId, name, address, username, password, branch, degree);
        }
        catch(DuplicateUserException e){
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Registration request sent.");
    }
    public List<Integer>  getSemesterList(int studentID){
        return studentDAO.getSemesterList();
    }

    public List<Course> getCourses(int studentID){
        int semID = studentDAO.getStudentByID(studentID).getSemID();
        return studentDAO.getCourses(semID);
    }

    public void setSemID(int studentID, int semID){
        studentDAO.setSemIDforStudent(studentID, semID);
    }
    public void dropCourse(int studentID, int courseID) {

        studentDAO.dropCourse(studentID, courseID);
    }
    public void addCourse(int studentID, int courseID){


        Student student = studentDAO.getStudentByID(studentID);
        int semID = student.getSemID();

        HashMap<Integer,Integer> courseEnrollmentCount =  studentDAO.getCourseEnrollmentCount(semID);
        List<Integer> registeredCoursesID = new ArrayList<>();

        if(!courseEnrollmentCount.containsKey(courseID)){
            courseEnrollmentCount.put(courseID, 0);
        }

        int currentCount = courseEnrollmentCount.get(courseID);

        if(currentCount < 10){
            currentCount++;
            courseEnrollmentCount.put(courseID, currentCount);
            studentDAO.registerCourse(studentID, courseID, semID);
        }
        else{
            System.out.println("Student cannot register. 0 seats left");
        }

//        Formatter fmt = new Formatter();
//        fmt.format("%15s\n", "CourseID");
//        for(int i=0;i<registeredCourseCount;i++){
//            fmt.format("%14s\n",registeredCoursesID.get(i));
//        }
//        System.out.println(fmt);

    }

    public List<Course> getRegisteredCourses(int studentID){
        List<Course> registeredCourses = studentDAO.getRegisteredCourses(studentID);
        Formatter fmt = new Formatter();
        fmt.format("%15s %15s\n", "CourseID", "CourseName");
        for(Course course: registeredCourses){
            fmt.format("%14s %14s\n",course.getCourseID() , course.getCourseName());
        }
        System.out.println(fmt);
        return registeredCourses;
    }


    public HashMap<Course,String> viewGrades(int studentId){
        return studentDAO.viewGrades(studentId);
    }
}
