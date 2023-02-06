package com.flipkart.dao;
import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
public interface AdminDAO {
    public void deleteCourse(int courseID) throws SQLException, ClassNotFoundException;// throws CourseNotFoundException, CourseNotDeletedException;

    public void addCourse(Course course, int semID) throws SQLException, ClassNotFoundException;// throws CourseFoundException;

//    public List<Student> viewPendingAdmissions();

    public void approveStudent(int studentID) throws SQLException, ClassNotFoundException;//throws StudentNotFoundForApprovalException;

    public void addUser(User user) throws SQLException, ClassNotFoundException;// throws UserNotAddedException, UserIdAlreadyInUseException;

    public void addProfessor(Professor professor) throws SQLException, ClassNotFoundException;// throws ProfessorNotAddedException, UserIdAlreadyInUseException;


    public void removeProfessor(Integer profID) throws SQLException, ClassNotFoundException;// throws ProfessorNotAddedException, UserIdAlreadyInUseException;
//
    public void generateReportCard() throws SQLException,ClassNotFoundException;
//    public void assignCourse(String courseCode, String professorId);// throws CourseNotFoundException, UserNotFoundException;
//
//    public List<Course> viewCourses(int catalogId);
//
//    public List<Professor> viewProfessors();
}
