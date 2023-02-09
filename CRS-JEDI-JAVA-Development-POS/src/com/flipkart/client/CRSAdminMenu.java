package com.flipkart.client;


import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constant.ColourConstant;
import com.flipkart.constant.Role;
import com.flipkart.dao.UserDAO;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.exception.*;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminServiceOperation;

import java.io.PrintStream;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CRSAdminMenu {
    AdminInterface service = new AdminServiceOperation();
    UserDAO userDAO = new UserDAOImpl();
    Scanner sc = new Scanner(System.in);
    public void adminMenu(int id) throws CourseAlreadyPresentException, CourseNotDeletedException, CourseNotFoundException, StudentNotFoundForApprovalException, UserIdAlreadyInUseException, ProfessorNotAddedException, SemNotFoundException {

        while(true) {

            System.out.println("****************************************************");
            System.out.println("******************** ADMIN MENU ********************");
            System.out.println("****************************************************");
            System.out.println();
            System.out.println("Welcome "+ userDAO.getUserByID(id).getUsername()+" !");
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
            int choice = 0;
            try {
                choice = Integer.parseInt(obj.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input should be numerical!");
            }

            switch (choice) {
                case 1:
                    approvedStudentRegistration();
                    break;

                case 2:{
                    System.out.println("Enter Admin ID");

                    int userID = 0;
                    try {
                        userID = Integer.parseInt(obj.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println( ColourConstant.ANSI_YELLOW+"Input should be numerical!" + ColourConstant.ANSI_RESET);
                        break;
                    }

                    System.out.println("Enter Username");
                    String userName =obj.nextLine();

                    System.out.println("Enter Password");
                    String password = obj.nextLine();

                    System.out.println("Enter Name");
                    String name = obj.nextLine();

                    addAdmin(userID, userName, password, Role.ADMIN, true, name);
                    break;
                }
                case 3:

                    System.out.println("Enter Prof ID");
                    int userID = 0;
                    try {
                        userID = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println( ColourConstant.ANSI_YELLOW+"Input should be numerical!" + ColourConstant.ANSI_RESET);
                        break;
                    }

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

                    addProfessor(userID, userName, password, Role.PROFESSOR, name, dept, designation);
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
                    System.out.println("Enter valid input!\n");
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
        Formatter fmt = new Formatter();
        fmt.format("%15s %15s\n", "StudentID", "StudentName");
        for(Student student: unapprovedStudents)
        {
            fmt.format("%14s %14s\n",student.getUserID(),student.getName());
        }
        System.out.println(fmt);

        System.out.println("1. Approve students by ID.");
        System.out.println("2. Approve all students.");

        int choice = 0;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println( ColourConstant.ANSI_YELLOW+"Input should be numerical!" + ColourConstant.ANSI_RESET);
            return;
        }
        switch (choice)
        {
            case 1:
                System.out.println("Enter the student ID to approve : ");
                int userID = 0;
                try {
                    userID = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println( ColourConstant.ANSI_YELLOW+"Input should be numerical!" + ColourConstant.ANSI_RESET);
                    break;
                }
                service.approveStudentRegistration(userID);
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
    private void addCourses() throws CourseAlreadyPresentException, SemNotFoundException {
        System.out.println("Enter SemId: ");
        int semID = 0;
        try {
            semID = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println( ColourConstant.ANSI_YELLOW+"Input should be numerical!" + ColourConstant.ANSI_RESET);
            return;
        }

        System.out.println("Enter courseId: ");
        int courseID= 0;
        try {
            courseID = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println( ColourConstant.ANSI_YELLOW+"Input should be numerical!" + ColourConstant.ANSI_RESET);
            return;
        }
        System.out.println("Enter courseName: ");
        String courseName= sc.nextLine();
        service.addCourse(courseID, courseName, semID);
    }
    private void deleteCourses() throws CourseNotDeletedException, CourseNotFoundException {
        System.out.println("Enter SemId: ");
        int semId = 0;
        try {
            semId = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println( ColourConstant.ANSI_YELLOW+"Input should be numerical!" + ColourConstant.ANSI_RESET);
        }

        List<Course> courseList = service.getCourses(semId);
        if(courseList.size()==0)
        {
            System.out.println("No Courses to view\n");
        }
        Formatter fmt = new Formatter();
        fmt.format("%15s %15s\n", "CourseID", "CourseName");
        for(int i=0;i<courseList.size();i++){
            fmt.format("%14s %14s\n",courseList.get(i).getCourseID() , courseList.get(i).getCourseName());
        }
        System.out.println(fmt);

        System.out.println("Enter courseId: ");
        int courseId=0;
        try {
            courseId = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println( ColourConstant.ANSI_YELLOW+"Input should be numerical!" + ColourConstant.ANSI_RESET);
        }
        service.removeCourse(semId,courseId);
    }
    private void generateReportCard(){
    	service.generateGradeCard();
        System.out.println("Grades Released!");
    }
}
