package com.flipkart.service;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.*;

import java.sql.SQLException;
import java.util.List;

public interface AdminInterface {

    public void approveStudentRegistration(int studentID) throws StudentNotFoundForApprovalException;

    public void addAdmin(int userID, String userName, String password, String role, boolean isApproved, String name);

    public void addProfessor(int userID, String userName, String password, String role, String name, String dept, String designation) throws UserIdAlreadyInUseException, ProfessorNotAddedException;

    public void addCourse(int courseID, String courseName, int semID) throws CourseAlreadyPresentException;

    public void removeCourse(int semId, int courseId) throws CourseNotDeletedException, CourseNotFoundException;

    public void generateGradeCard();

    public List<Student> viewUnapprovedStudents();

    public void approveAllStudents();
}
