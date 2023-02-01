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
            System.out.println("3. Drop Course");
            System.out.println("4. View Course");
            System.out.println("5. View grades");
            System.out.println("6. Make Payment");
            System.out.println("7. Logout");

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
                    viewCourses();
                    break;
                case 5:
                    viewGrades();
                    break;
                case 6:
                    payFees();
                    break;
                case 7:
                    System.out.println("Logged out");
                default:
                    System.out.println("Please enter a valid input\n");
            }

            if( choice == 7 ){
                break;
            }
        }
    }


    private void semesterRegister(){
        studentServiceOperation.semesterRegister();
    }

    private void addCourse(){

    }
    private void deleteCourse(){

    }
    private void viewCourses(){

    }
    private void viewGrades(){

    }
    private void payFees() {

    }
}
