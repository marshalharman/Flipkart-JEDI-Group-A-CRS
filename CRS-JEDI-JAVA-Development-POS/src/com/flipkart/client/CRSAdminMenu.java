package com.flipkart.client;


import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.data.Data;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminServiceOperation;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CRSAdminMenu {
    AdminInterface service = new AdminServiceOperation();
    Scanner sc = new Scanner(System.in);
    public void adminMenu(int id)
    {

        while(true) {

            System.out.println("\nAdmin Menu!");
            System.out.println("Choose one of the options");
            System.out.println("1. Approve Student Registration");
            System.out.println("2. Add Admin");
            System.out.println("3. Add Professor");
            System.out.println("4. Remove Professor");
            System.out.println("5. Add Courses");
            System.out.println("6. Delete Courses");
            System.out.println("7. Generate Grade Card");
            System.out.println("8. Logout");

            Scanner obj = new Scanner(System.in);
            int choice = Integer.parseInt(obj.nextLine());

            switch (choice) {
                case 1:
                    approvedStudentRegistration();
                    break;

                case 2:{
                    Admin admin = new Admin();

                    System.out.println("Enter Admin ID\n");
                    int userID = Integer.parseInt(obj.nextLine());
                    admin.setUserID(userID);

                    System.out.println("Enter Admin name\n");
                    String userName =obj.nextLine();
                    admin.setName(userName);

                    System.out.println("Enter Password\n");
                    String password = obj.nextLine();
                    admin.setPassword(password);

                    addAdmin(admin);

                    for(Admin a: Data.admins){
                        System.out.println(a.getName());
                    }
                    break;
                }
                case 3:
                    Professor p=new Professor();

                    System.out.println("Enter Prof ID\n");
                    int userID = Integer.parseInt(obj.nextLine());
                    p.setUserID(userID);

                    System.out.println("Enter Prof name\n");
                    String userName =obj.nextLine();
                    p.setName(userName);

                    System.out.println("Enter Password\n");
                    String password = obj.nextLine();
                    p.setPassword(password);

                    addProfessor(p);
                    break;
                case 4:
                    System.out.println("Give Prof name: \n");
                    String profName = obj.nextLine();
                    removeProfessor(profName);
                    break;
                case 5:
                    addCourses();
                    break;
                case 6:
                    deleteCourses();
                    break;
                case 7:
                    generateReportCard();
                    break;
                case 8:
                    System.out.println("Logged out\n");
                    break;
                default:
                    System.out.println("Choose in the given options\n");
            }

            if(choice == 8){break;}
        }
    }
	private void approvedStudentRegistration(){
        List<Student> unapprovedStudents =Data.unapprovedStudents;
        if(unapprovedStudents.isEmpty())
        {
            System.out.println("No unapproved student.");
            return;

        }
        System.out.println("List of unapproved students : ");
        for(Student s: unapprovedStudents){
            System.out.println(s.getUserID() + " - " + s.getUsername());
        }

        System.out.println("Enter the student ID to approve : ");
        int studentID = Integer.parseInt(sc.nextLine());
        service.approveStudentRegistration(studentID);
    }
    private void addAdmin(Admin a){ service.addAdmin(a);}
    private void addProfessor(Professor p){
        service.addProfessor(p);
    }
    private void removeProfessor(String profName){
        service.removeProfessor(profName);
    }
    private void addCourses() throws SQLException, ClassNotFoundException {
        System.out.println("Enter SemId: ");
        int semID=sc.nextInt();
        System.out.println("Enter courseId: ");
        int courseID=sc.nextInt();
        System.out.println("Enter courseName: ");
        String courseName=sc.next();
        service.addCourse(courseID, courseName, semID);
    }
    private void deleteCourses(){
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
