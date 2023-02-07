package com.flipkart.client;


import com.flipkart.bean.Admin;
import com.flipkart.bean.Student;
import com.flipkart.data.Data;
import com.flipkart.exception.*;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminServiceOperation;

import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CRSAdminMenu {
    AdminInterface service = new AdminServiceOperation();
    Scanner sc = new Scanner(System.in);
    public void adminMenu(int id) throws CourseAlreadyPresentException, CourseNotDeletedException, CourseNotFoundException, StudentNotFoundForApprovalException, UserIdAlreadyInUseException, ProfessorNotAddedException {

        while(true) {

            System.out.println("****************************************************");
            System.out.println("******************** ADMIN MENU ********************");
            System.out.println("****************************************************");
            System.out.println();
            System.out.println("Choose one of the options");
            System.out.println("1. Approve Student Registration");
            System.out.println("2. Add Admin");
            System.out.println("3. Add Professor");
            System.out.println("4. Add Courses");
            System.out.println("5. Delete Courses");
            System.out.println("6. Generate Grade Card");
            System.out.println("7. Logout");

            Scanner obj = new Scanner(System.in);
            int choice = Integer.parseInt(obj.nextLine());

            switch (choice) {
                case 1:
                    approvedStudentRegistration();
                    break;

                case 2:{
                    System.out.println("Enter Admin ID");
                    int userID = Integer.parseInt(obj.nextLine());

                    System.out.println("Enter Username");
                    String userName =obj.nextLine();

                    System.out.println("Enter Password");
                    String password = obj.nextLine();

                    System.out.println("Enter Name");
                    String name = obj.nextLine();

                    addAdmin(userID, userName, password, "admin", true, name);

                    for(Admin a: Data.admins){
                        System.out.println(a.getName());
                    }
                    break;
                }
                case 3:

                    System.out.println("Enter Prof ID");
                    int userID = Integer.parseInt(obj.nextLine());

                    System.out.println("Enter Username");
                    String userName =obj.nextLine();

                    System.out.println("Enter Password");
                    String password = obj.nextLine();

                    System.out.println("Enter Name");
                    String name = obj.nextLine();

                    System.out.println("Enter Department");
                    String dept = obj.nextLine();

                    System.out.println("Enter Designation");
                    String designation = obj.nextLine();

                    addProfessor(userID, userName, password, "professor", name, dept, designation);
                    break;
                case 4:
                    addCourses();
                    break;
                case 5:
                    deleteCourses();
                    break;
                case 6:
                    generateReportCard();
                    break;
                case 7:
                    System.out.println("Logged out\n");
                    break;
                default:
                    System.out.println("Choose in the given options\n");
            }

            if(choice == 7){break;}
        }
    }
	private void approvedStudentRegistration() throws StudentNotFoundForApprovalException {
        List<Student>  unapprovedStudents = service.viewUnapprovedStudents();
        if(unapprovedStudents.isEmpty())
        {
            System.out.println("No students to approve.");
            return ;
        }
        System.out.println("List of unapproved students: ");
        for(Student student: unapprovedStudents)
        {
            System.out.println("Student ID : "+student.getUserID() + " , " + "Student Name : "+student.getName());
        }
        System.out.println("1. Approve students by ID.");
        System.out.println("2. Approve all students.");

        int choice = sc.nextInt();
        switch (choice)
        {
            case 1:
                System.out.println("Enter the student ID to approve : ");
                service.approveStudentRegistration(sc.nextInt());
                break;
            case 2:
                service.approveAllStudents();
                break;
            default:
                System.out.println("Wrong input");
                break;
        }

    }
    private void addAdmin(int userID, String userName, String password, String role, boolean isApproved, String name){
        service.addAdmin(userID, userName, password, role, isApproved, name);
    }
    private void addProfessor(int userID,String userName,String password,String role,String name,String dept,String designation) throws UserIdAlreadyInUseException, ProfessorNotAddedException {
        service.addProfessor(userID,userName,password,role,name,dept,designation);
    }
    private void addCourses() throws CourseAlreadyPresentException {
        System.out.println("Enter SemId: ");
        int semID=sc.nextInt();
        System.out.println("Enter courseId: ");
        int courseID=sc.nextInt();
        System.out.println("Enter courseName: ");
        String courseName=sc.next();
        service.addCourse(courseID, courseName, semID);
    }
    private void deleteCourses() throws CourseNotDeletedException, CourseNotFoundException {
        System.out.println("Enter SemId: ");
        int semId=sc.nextInt();
        System.out.println("Enter courseId: ");
        int courseId=sc.nextInt();
        service.removeCourse(semId,courseId);
    }
    private void generateReportCard(){
    	service.generateGradeCard();
    }
}
