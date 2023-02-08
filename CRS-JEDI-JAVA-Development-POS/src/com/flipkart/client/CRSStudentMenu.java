package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentServiceOperation;
import com.flipkart.service.PaymentInterface;
import com.flipkart.service.PaymentServiceOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.spi.AbstractResourceBundleProvider;
import java.util.*;

import static java.util.Objects.isNull;


public class CRSStudentMenu {

    StudentInterface studentServiceOperation = new StudentServiceOperation();
    PaymentInterface paymentServiceOperation = new PaymentServiceOperation();

    List<Course> primaryCourses = new ArrayList<Course>();
    List<Course> alternateCourses = new ArrayList<Course>();

    public void studentMenu(int studentID) {
        while(true) {
            System.out.println("****************************************************");
            System.out.println("******************* STUDENT MENU *******************");
            System.out.println("****************************************************");
            System.out.println();
            System.out.println("Choose one of the options");
            System.out.println("1. Semester Registration");
            System.out.println("2. Add Course");
            System.out.println("3. Remove Course");
            System.out.println("4. Submit Preferences");
            System.out.println("5. Drop Course");
            System.out.println("6. View Courses");
            System.out.println("7. View grades");
            System.out.println("8. Make Payment");
            System.out.println("9. View Registered Courses");
            System.out.println("10. Logout");

            Scanner obj = new Scanner(System.in);

            int choice = 0;
            try {
                choice = Integer.parseInt(obj.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input should be numerical!");
            }

            switch (choice) {
                case 1:
                    semesterRegister(studentID);
                    break;
                case 2:
                    addCourse(studentID);
                    break;
                case 3:
                    deleteCourse(studentID);
                    break;
                case 4:
                    submit(studentID);
                    break;
                case 5:
                    dropCourse(studentID);
                    break;
                case 6:
                    //System.out.println(student.getSemID());
                    viewCourses(studentID);
                    break;
                case 7:
                    //System.out.println(id + "hello ");
                    viewGrades(studentID);
                    break;
                case 8:
                    payFees(studentID);
                    break;
                case 9:
                    viewRegisteredCourses(studentID);
                    break;
                case 10:
                    System.out.println("Logged out");
                    break;
                default:
                    System.out.println("Please enter a valid input\n");
            }

            if( choice == 10 ){
                break;
            }
        }
    }

    private void viewRegisteredCourses(int studentID) {
        studentServiceOperation.getRegisteredCourses(studentID);
    }


    private void semesterRegister(int studentID){
        List<Integer> semList = studentServiceOperation.getSemesterList(studentID);
        System.out.println("Select Semester to Register:");
        for(int semID: semList){
            System.out.println("SEM " + semID);
        }
        Scanner sc = new Scanner(System.in);

        int semID = 0;
        try {
            semID = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input should be numerical!");
            return;
        }


        studentServiceOperation.setSemID(studentID, semID);
    }

    private void addCourse(int studentID){
        System.out.println("1. Add Primary Course\n2. Add Alternate Course");
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input should be numerical!");
            return;
        }

        List<Course> courseList = viewCourses(studentID);
        System.out.println("Enter name of the Course to add:");
        String courseName = sc.nextLine();

        System.out.println("Selected course: " + courseName);

        for( Course course: courseList){
            if( course.getCourseName().equalsIgnoreCase(courseName) ){
                if( choice == 1){
                    primaryCourses.add(course);
                }
                else if( choice == 2 ){
                    alternateCourses.add(course);
                }
            }
        }
    }

    private void deleteCourse(int studentID) {
        System.out.println("1. Delete Primary Course\n2. Delete Alternate Course");
        Scanner sc = new Scanner(System.in);

        int choice = 0;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input should be numerical!");
            return;
        }
        if (choice == 1) {
            System.out.println("Please select course : ");
            for (Course course : primaryCourses) {
                System.out.println(course.getCourseName());
            }
            String courseName = sc.nextLine();
            for (Course course : primaryCourses) {
                if (course.getCourseName().equalsIgnoreCase(courseName)) {
                    primaryCourses.remove(course);
                    break;
                }
            }
        } else if (choice == 2) {
            System.out.println("Please select course : ");
            for (Course course : alternateCourses) {
                System.out.println(course.getCourseName());
            }
            String courseName = sc.nextLine();
            for (Course course : alternateCourses) {
                if (course.getCourseName().equalsIgnoreCase(courseName)) {
                    alternateCourses.remove(course);
                    break;
                }
            }
        }
    }

    public void submit(int studentID){
        studentServiceOperation.submitPreferences(studentID, primaryCourses, alternateCourses);
    }

    public void dropCourse(int studentID){
        viewRegisteredCourses(studentID);
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the ID of the course to be dropped : ");
        int courseID = 0;
        try {
            courseID = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input should be numerical!");
            return;
        }

        studentServiceOperation.dropCourse(studentID, courseID);
    }
    private List<Course> viewCourses(int studentID){
        List<Course> courseList = studentServiceOperation.getCourses(studentID);
        Formatter fmt = new Formatter();
        fmt.format("%15s %15s\n", "CourseID", "CourseName");
        for(int i=0;i<courseList.size();i++){
            fmt.format("%14s %14s\n",courseList.get(i).getCourseID() , courseList.get(i).getCourseName());
        }
        System.out.println(fmt);
        return courseList;
    }
    private void viewGrades(int studentID){
        HashMap<Course, String> GradesInCourses = studentServiceOperation.viewGrades(studentID);
        if(GradesInCourses==null) {
            System.out.println("Grades not published yet");
            return;
        }
        Formatter fmt = new Formatter();
        fmt.format("%15s %15s %15s\n", "CourseID", "CourseName", "grade");
        for(Map.Entry e: GradesInCourses.entrySet()) {
            Course course = new Course();
            course = (Course) e.getKey();
            fmt.format("%14s %14s %14s\n",course.getCourseID() , course.getCourseName(), e.getValue());
        }
        System.out.println(fmt);
    }
    private void payFees(int studentID) {
        paymentServiceOperation.pay(studentID);
    }
}
