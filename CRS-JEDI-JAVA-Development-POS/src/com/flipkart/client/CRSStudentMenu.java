package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.data.Data;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentServiceOperation;
import com.flipkart.service.PaymentInterface;
import com.flipkart.service.PaymentServiceOperation;

import java.util.List;
import java.util.Scanner;
import java.util.spi.AbstractResourceBundleProvider;


public class CRSStudentMenu {

    StudentInterface studentServiceOperation = new StudentServiceOperation();
    PaymentInterface paymentServiceOperation = new PaymentServiceOperation();

    public void studentMenu(int studentID) {

        while(true) {
            System.out.println("\nStudent Menu!");
            System.out.println("Choose one of the options");
            System.out.println("1. Semester Registration");
            System.out.println("2. Add Course");
            System.out.println("3. Remove Course");
            System.out.println("4. Submit");
            System.out.println("5. Drop Course");
            System.out.println("6. View Course");
            System.out.println("7. View grades");
            System.out.println("8. Make Payment");
            System.out.println("9. View Registered Courses");
            System.out.println("10. Logout");

            Scanner obj = new Scanner(System.in);

            int choice;
            choice = Integer.parseInt(obj.nextLine());

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
        Scanner sc = new Scanner(System.in);
        int semID = Integer.parseInt(sc.nextLine());

        student.setSemID(semID);
    }

    private void addCourse(int studentID){
        System.out.println("1. Add Primary Course\n2. Add Alternate Course");
        Scanner sc = new Scanner(System.in);
        int choice = Integer.parseInt(sc.nextLine());

        System.out.println("Select Course to Add:");
        viewCourses(student.getSemID());
        String courseName = sc.nextLine();


        for( Course course: Data.semCourseList.get(student.getSemID())){
            if( course.getCourseName().equalsIgnoreCase(courseName) ){
                if( choice == 1){ student.addPrimaryCourse(course); }
                else if( choice == 2 ){ student.addAlternateCourse(course); }
            }
        }
    }

    private void deleteCourse(int studentID){
        studentServiceOperation.removeCourse(student);
    }

    public void submit(int studentID){
        studentServiceOperation.submitPreferences(student);
    }

    public void dropCourse(int studentID){
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the ID of the course to be dropped : ");
        int courseID = Integer.parseInt(sc.nextLine());

        studentServiceOperation.dropCourse(studentID, courseID);
    }
    private void viewCourses(int studentID){
        List<Course> courseList = studentServiceOperation.getCourses(semId);

        for(int i=0;i<courseList.size();i++){
            System.out.println(courseList.get(i).getCourseName() + "\n");
        }
    }
    private void viewGrades(int studentID){
        studentServiceOperation.viewGrades(studentID);
    }
    private void payFees(int studentIDt) {

        paymentServiceOperation.pay(student);
    }
}
