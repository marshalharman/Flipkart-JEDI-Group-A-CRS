package com.flipkart.service;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Payment;
import com.flipkart.bean.User;
import com.flipkart.bean.Course;
import com.flipkart.dao.StudentDAO;
import com.flipkart.dao.StudentDAOImpl;
import com.flipkart.data.Data;
import java.util.*;
import com.flipkart.bean.Student;
import com.flipkart.exception.DuplicateUserException;

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
        StudentDAO studentDAO = new StudentDAOImpl();
        return studentDAO.getSemesterList();
    }

    public List<Course> getCourses(int studentID){
        StudentDAO studentDAO = new StudentDAOImpl();
        int semID = studentDAO.getStudentByID(studentID).getSemID();
        return studentDAO.getCourses(semID);
    }

    public void setSemID(int studentID, int semID){
        studentDAO.setSemIDforStudent(studentID, semID);
    }
    public void dropCourse(int studentID, int courseID) {

        studentDAO.dropCourse(studentID, courseID);
    }

    public void submitPreferences(int studentID, List<Course> primaryCourses, List<Course> alternateCourses){

        int registeredCourseCount = 0;

        StudentDAO studentDAO = new StudentDAOImpl();

        Student student = studentDAO.getStudentByID(studentID);
        int semID = student.getSemID();

        HashMap<Integer,Integer> courseEnrollmentCount =  studentDAO.getCourseEnrollmentCount(semID);
        List<Integer> registeredCoursesID = new ArrayList<>();

        for(Course course: primaryCourses){
            if(!courseEnrollmentCount.containsKey(course.getCourseID())){
                courseEnrollmentCount.put(course.getCourseID(), 0);
            }
            int currentCount = courseEnrollmentCount.get(course.getCourseID());
            if(currentCount < 10){
                registeredCoursesID.add(course.getCourseID());
//                Data.registeredCourses.get(student.getUserID()).add(course);
                currentCount++;
                courseEnrollmentCount.put(course.getCourseID(), currentCount);
                registeredCourseCount++;
            }
        }

        for(Course course: alternateCourses){
            if(!courseEnrollmentCount.containsKey(course.getCourseID())){
                courseEnrollmentCount.put(course.getCourseID(), 0);
            }
            int currentCount = courseEnrollmentCount.get(course.getCourseID());
            if(registeredCourseCount < 4 && currentCount < 10){
                registeredCoursesID.add(course.getCourseID());
//                Data.registeredCourses.get(student.getUserID()).add(course);
                currentCount++;
                courseEnrollmentCount.put(course.getCourseID(), currentCount);
                registeredCourseCount++;
            }
        }

        studentDAO.registerCourses(studentID, registeredCoursesID, semID);

        System.out.println("REGISTERED COURSES:");
        for(int i=0;i<registeredCourseCount;i++){
            System.out.println(registeredCoursesID.get(i));
        }
    }

    public void getRegisteredCourses(int studentID){
        List<Course> registeredCourses = studentDAO.getRegisteredCourses(studentID);

        for(Course course: registeredCourses){
            System.out.println(course.getCourseID() + " - " + course.getCourseName());
        }
    }



    public HashMap<Course,String> viewGrades(int studentId){
        return studentDAO.viewGrades(studentId);
    }
}
