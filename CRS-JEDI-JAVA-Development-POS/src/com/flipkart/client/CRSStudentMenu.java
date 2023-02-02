package com.flipkart.client;

import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentServiceOperation;

import java.util.Scanner;
import java.util.spi.AbstractResourceBundleProvider;


public class CRSStudentMenu {

    StudentInterface studentServiceOperation = new StudentServiceOperation();

    public void studentMenu(int id) {

        while(true) {
            System.out.println("Student Menu!");
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
                    semesterRegister();
                    break;
                case 2:
                    addCourse();
                    break;
                case 3:
                    deleteCourse();
                    break;
                case 4:
                    submit();
                    break;
                case 5:
                    dropCourse();
                    break;
                case 6:
                    viewCourses();
                    break;
                case 7:
                    viewGrades(id);
                    break;
                case 8:
                    payFees();
                    break;
                case 9:
                    viewRegisteredCourses();
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

    private void viewRegisteredCourses() {
    }


    private void semesterRegister(){
        studentServiceOperation.semesterRegister();
    }

    private void addCourse(){

    }
    private void deleteCourse(){

    }

    public void submit(){

    }

    public void dropCourse(){

    }
    private void viewCourses(){

    }
    private void viewGrades(int studentId){

    }
    private void payFees() {

    }
}
