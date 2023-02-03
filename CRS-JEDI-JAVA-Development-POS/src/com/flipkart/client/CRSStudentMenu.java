package com.flipkart.client;

import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.data.Data;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentServiceOperation;
import com.flipkart.service.PaymentInterface;
import com.flipkart.service.PaymentServiceOperation;

import java.util.Scanner;
import java.util.spi.AbstractResourceBundleProvider;


public class CRSStudentMenu {

    StudentInterface studentServiceOperation = new StudentServiceOperation();
    PaymentInterface paymentServiceOperation = new PaymentServiceOperation();

    public void studentMenu(int id) {

        Student student = null;

        for(Student st: Data.students){
            if(st.getUserID() == id){
                student = st;
                break;
            }
        }

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
            if(choice!=1 && choice!=10)
            {
                if(student.getSemID()==0)
                {
                    System.out.println("Please register first!");
                    continue;
                }
            }
            switch (choice) {
                case 1:
                    semesterRegister(student);
                    break;
                case 2:
                    addCourse(student);
                    break;
                case 3:
                    deleteCourse(student);
                    break;
                case 4:
                    submit(student);
                    break;
                case 5:
                    dropCourse(student);
                    break;
                case 6:
                    //System.out.println(student.getSemID());
                    viewCourses(student.getSemID());
                    break;
                case 7:
                    //System.out.println(id + "hello ");
                    viewGrades(id);
                    break;
                case 8:
                    payFees(student);
                    break;
                case 9:
                    viewRegisteredCourses(student);
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

    private void viewRegisteredCourses(Student student) {

        int userId = student.getUserID();
        System.out.println("REGISTERED COURSES");

        studentServiceOperation.getRegisteredCourses(student, userId);
    }


    private void semesterRegister(Student student){
        studentServiceOperation.semesterRegister(student);
    }

    private void addCourse(Student student){
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Add Primary Course\n2. Add Alternate Course");
        int choice = Integer.parseInt(sc.nextLine());

        System.out.println("Enter Sem ID : ");
        int semID = Integer.parseInt(sc.nextLine());
        viewCourses(semID);

        System.out.println("Select Course to Add:");
        String courseName = sc.nextLine();

        studentServiceOperation.addCourse(student, choice, semID, courseName);
    }

    private void deleteCourse(Student student){
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Remove Primary Course\n2. Remove Alternate Course");
        int choice = Integer.parseInt(sc.nextLine());

        studentServiceOperation.removeCourse(student, choice);
    }

    public void submit(Student student){
        studentServiceOperation.submitPreferences(student);
    }

    public void dropCourse(Student student){
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the name of the course to be dropped : ");
        String courseName = sc.nextLine();

        studentServiceOperation.dropCourse(student, courseName);
    }
    private void viewCourses(int semId){
        studentServiceOperation.getCourses(semId);
    }
    private void viewGrades(int studentId){
        studentServiceOperation.viewGrades(studentId);
    }
    private void payFees(Student student) {
        paymentServiceOperation.pay(student);
    }
}
