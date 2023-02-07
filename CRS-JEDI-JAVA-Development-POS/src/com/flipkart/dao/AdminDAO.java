package com.flipkart.dao;
import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.*;

public interface AdminDAO {

    public void addAdmin(int userID, String name);
    public void deleteCourse(int courseID) throws CourseNotFoundException, CourseNotDeletedException;
    public void addCourse(Course course, int semID) throws CourseAlreadyPresentException;
    public void approveStudent(int studentID) throws StudentNotFoundForApprovalException;
    public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserIdAlreadyInUseException;
    public void generateGradeCard();
    public List<Student> viewUnapprovedStudents();
    public void approveAllStudents();
}
