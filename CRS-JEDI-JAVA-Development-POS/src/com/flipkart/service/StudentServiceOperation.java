package com.flipkart.service;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Payment;
import com.flipkart.bean.User;
import com.flipkart.bean.Course;
import com.flipkart.data.Data;
import java.util.*;
import com.flipkart.bean.Student;

public class StudentServiceOperation implements StudentInterface {

    Data studentsdata= new Data();

    Scanner sc = new Scanner(System.in);
    public void register(){

        System.out.println("Enter your student ID: ");
        int studentId = sc.nextInt();

        System.out.println("Enter your name: ");
        String name = sc.next();

        System.out.println("Enter your address: ");
        String address = sc.next();

        System.out.println("Enter your username: ");
        String username = sc.next();

        System.out.println("Enter your password: ");
        String password = sc.next();

        System.out.println("Enter your branch: ");
        String branch = sc.next();

        Student student = new Student();
        student.setUserID(studentId);
        student.setName(name);
        student.setAddress(address);
        student.setUsername(username);
        student.setPassword(password);
        student.setBranch(branch);
        student.setRole("Student");

        System.out.println("Student registered successfully.");
    }

    public int login(String studentName, String password){
        List<Student> studentsList= studentsdata.students;
        int userId = -1;
        for(Student s:studentsList)
        {
            if(s.getName().equals(studentName) && s.getPassword().equals(password)) {
                userId = s.getUserID();
                break;
            }
        }
        return userId;
    }

    public void semesterRegister(Student student){

        System.out.println("Select Semester to register:");
        for(Map.Entry m: Data.semCourseList.entrySet()){
            System.out.println(m.getKey());
        }

        Scanner sc = new Scanner(System.in);
        int semID = Integer.parseInt(sc.nextLine());

        student.setSemID(semID);
    }

    public List<Course> getCourses(int semID){
        List<Course> c=Data.semCourseList.get(semID);
        System.out.println("List of courses");
        for(int i=0;i<c.size();i++)
        {
            System.out.println(c.get(i)+"\n");
        }
        return c;
    }

    public void addCourse(Student student){
        System.out.println("1. Add Primary Course\n2. Add Alternate Course");
        int choice = Integer.parseInt(sc.nextLine());

        System.out.println("Select Course to Add:");
        getCourses(student.getSemID());
        String courseName = sc.nextLine();

        for( Course course: studentsdata.semCourseList.get(student.getSemID())){
            if( course.getCourseName().equalsIgnoreCase(courseName) ){
                if( choice == 1){ student.addPrimaryCourse(course); }
                else if( choice == 2 ){ student.addAlternateCourse(course); }
            }
        }

    }

    public void  removeCourse(Student student){

        System.out.println("1. Remove Primary Course\n2. Remove Alternate Course");
        int choice = Integer.parseInt(sc.nextLine());

        System.out.println("Please select course : ");

        if( choice == 1 ){
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
    public void dropCourse(){
//        for(Course course: student.getPrimaryCourses()){
//            if( course.getCourseName().equalsIgnoreCase(courseName) ){
//                student.removePrimaryCourse(course);
//            }
//        }
    }

    public void submitPreferences(Student student){

        int registeredCourseCount = 0;
        Data.registeredCourses.put(student.getUserID(), new ArrayList<>());

        for(Course course: student.getPrimaryCourses()){
            if(!Data.courseEnrollmentCount.containsKey(course.getCourseID())){
                Data.courseEnrollmentCount.put(course.getCourseID(), 0);
            }
            if(Data.courseEnrollmentCount.get(course.getCourseID()) < 10){
                Data.registeredCourses.get(student.getUserID()).add(course);
                registeredCourseCount++;
            }
        }

        for(Course course: student.getAlternateCourses()){
            if(!Data.courseEnrollmentCount.containsKey(course.getCourseID())){
                Data.courseEnrollmentCount.put(course.getCourseID(), 0);
            }
            if(registeredCourseCount < 4 && Data.courseEnrollmentCount.get(course.getCourseID()) < 10){
                Data.registeredCourses.get(student.getUserID()).add(course);
                registeredCourseCount++;
            }
        }

        System.out.println("REGISTERED COURSES:");
        for(int i=0;i<registeredCourseCount;i++){
            System.out.println(Data.registeredCourses.get(student.getUserID()).get(i).getCourseName());
        }
    }

    public void getRegisteredCourses(Student student){

        int userId = student.getUserID();
        System.out.println("REGISTERED COURSES");
        for(Course course: Data.registeredCourses.get(userId)){
            System.out.println(course.getCourseName());
        }
    }


    public void viewGrades(int studentId){
        System.out.println();
        Data d = new Data();
        HashMap<Integer,List<Grade> > hp= d.gradeList;
        List<Grade> studentGrades=hp.get(studentId);
        System.out.println("Grades :");
        for(Grade grade:studentGrades){
            System.out.println(grade.getStudentID() + " " +grade.getCourseID() + " " +grade.getScore());
        }

    }


}
