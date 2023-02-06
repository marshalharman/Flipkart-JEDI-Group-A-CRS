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

public class StudentServiceOperation implements StudentInterface {

    StudentDAOImpl studentDAO = new StudentDAOImpl();
    Scanner sc = new Scanner(System.in);
    public void register(int studentId, String name, String address, String username, String password, String branch){

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
    public List<Integer>  getSemesterList(int studentID){
        StudentDAO studentDAO = new StudentDAOImpl();
        return studentDAO.getSemesterList();
    }

    public List<Course> getCourses(int semID){
        StudentDAO studentDAO = new StudentDAOImpl();
        return studentDAO.getCourses(semID);
    }

    public void addCourse(Student student, int choice, int semID, String courseName){

        for( Course course: Data.semCourseList.get(student.getSemID())){
            if( course.getCourseName().equalsIgnoreCase(courseName) ){
                if( choice == 1){ student.addPrimaryCourse(course); }
                else if( choice == 2 ){ student.addAlternateCourse(course); }
            }
        }

    }

    public void  removeCourse(Student student, int choice){
        Scanner sc = new Scanner(System.in);

        if( choice == 1 ){
            System.out.println("Please select course : ");
            for(Course course: student.getPrimaryCourses()) {
                System.out.println(course.getCourseName());
            }
            String courseName = sc.nextLine();
            for(Course course: student.getPrimaryCourses()){
                if( course.getCourseName().equalsIgnoreCase(courseName) ){
                    student.removePrimaryCourse(course);
                    break;
                }
            }
        }
        else if( choice == 2 ){
            System.out.println("Please select course : ");
            for(Course course: student.getAlternateCourses()) {
                System.out.println(course.getCourseName());
            }
            String courseName = sc.nextLine();
            for(Course course: student.getAlternateCourses()){
                if( course.getCourseName().equalsIgnoreCase(courseName) ){
                    student.removeAlternateCourse(course);
                    break;
                }
            }
        }
    }
    public void dropCourse(int studentID, int courseID){
        StudentDAOImpl studentDAO = new StudentDAOImpl();
        studentDAO.dropCourse(studentID, courseID);

    }

    public void submitPreferences(Student student){

        int registeredCourseCount = 0;
        Data.registeredCourses.put(student.getUserID(), new ArrayList<>());

        for(Course course: student.getPrimaryCourses()){
            if(!Data.courseEnrollmentCount.containsKey(course.getCourseID())){
                Data.courseEnrollmentCount.put(course.getCourseID(), 0);
            }
            int currentCount = Data.courseEnrollmentCount.get(course.getCourseID());
            if(currentCount < 10){
                Data.registeredCourses.get(student.getUserID()).add(course);
                currentCount++;
                Data.courseEnrollmentCount.put(course.getCourseID(), currentCount);
                registeredCourseCount++;
            }
        }

        for(Course course: student.getAlternateCourses()){
            if(!Data.courseEnrollmentCount.containsKey(course.getCourseID())){
                Data.courseEnrollmentCount.put(course.getCourseID(), 0);
            }
            int currentCount = Data.courseEnrollmentCount.get(course.getCourseID());
            if(registeredCourseCount < 4 && currentCount < 10){
                Data.registeredCourses.get(student.getUserID()).add(course);
                currentCount++;
                Data.courseEnrollmentCount.put(course.getCourseID(), currentCount);
                registeredCourseCount++;
            }
        }

        System.out.println("REGISTERED COURSES:");
        for(int i=0;i<registeredCourseCount;i++){
            System.out.println(Data.registeredCourses.get(student.getUserID()).get(i).getCourseName());
        }
    }

    public void getRegisteredCourses(Student student, int userId){

        for(Course course: Data.registeredCourses.get(userId)){
            System.out.println(course.getCourseName());
        }
    }



    public HashMap<Course,String> viewGrades(int studentId){
        return studentDAO.viewGrades(studentId);
    }
}
