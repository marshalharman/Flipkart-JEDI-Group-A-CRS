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
/**
 *
 * Implementations of Student Interface
 *
 */
public class StudentServiceOperation implements StudentInterface {

    StudentDAOImpl studentDAO = new StudentDAOImpl();
    Scanner sc = new Scanner(System.in);
    /**
     * Method to register a student, although student can't login until it's approved by admin
     * @param studentId
     * @param name
     * @param address
     * @param username
     * @param password
     * @param branch
     * @param degree
     */
    public void register(int studentId, String name, String address, String username, String password, String branch, String degree){

        studentDAO.register(studentId,name,address,username,password,branch,degree);

        for(Student s:Data.students)
        {
            if(s.getUserID()==studentId)
            {
                System.out.println("Student already exists, please Login\n");
                return;
            }
        }

        Student student = new Student();
        student.setUserID(studentId);
        student.setName(name);
        student.setAddress(address);
        student.setUsername(username);
        student.setPassword(password);
        student.setBranch(branch);
        student.setRole("Student");
        Data.unapprovedStudents.add(student);
        System.out.println("Registration request sent.");
    }
    /**
     * Method to get list of all semesters
     * @param studentID
     * @return List of all semesters
     */
    public List<Integer>  getSemesterList(int studentID){
        StudentDAO studentDAO = new StudentDAOImpl();
        return studentDAO.getSemesterList();
    }
    /**
     * Method to get all courses in a given semester
     * @param studentID
     * @return List of all courses in a given semester
     */
    public List<Course> getCourses(int studentID){
        StudentDAO studentDAO = new StudentDAOImpl();
        int semID = studentDAO.getStudentByID(studentID).getSemID();
        return studentDAO.getCourses(semID);
    }
    /**
     * Method to assign respective semID to the student
     * @param studentID
     * @param semID
     */
    public void setSemID(int studentID, int semID){
        studentDAO.setSemIDforStudent(studentID, semID);
    }
    /**
     * Method for a student to drop a course they are enrolled in
     * @param studentID
     * @param courseID
     */
    public void dropCourse(int studentID, int courseID) {

        studentDAO.dropCourse(studentID, courseID);
    }
    /**
     * Method for a student to submit course preference
     * @param studentID
     * @param primaryCourses
     * @param alternateCourses
     */
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
    /**
     * Method for a student to get all the courses they are registered in
     * @param studentID
     */
    public void getRegisteredCourses(int studentID){
        List<Course> registeredCourses = studentDAO.getRegisteredCourses(studentID);

        for(Course course: registeredCourses){
            System.out.println(course.getCourseName());
        }
    }
    /**
     * Method to view grades assigned to a student in respective courses
     * @param studentId
     * @return Map of courses and respective grades assigned
     */
    public HashMap<Course,String> viewGrades(int studentId){
        return studentDAO.viewGrades(studentId);
    }
}
