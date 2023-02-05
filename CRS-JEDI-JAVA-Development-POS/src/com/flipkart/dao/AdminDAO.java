package com.flipkart.dao;
import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
public interface AdminDAO {
    public void deleteCourse(String courseCode) throws SQLException;// throws CourseNotFoundException, CourseNotDeletedException;

    public void addCourse(Course course) throws SQLException;// throws CourseFoundException;

//    public List<Student> viewPendingAdmissions();

    public void approveStudent(int studentId) throws SQLException;//throws StudentNotFoundForApprovalException;


    public void addProfessor(Professor professor) throws SQLException;// throws ProfessorNotAddedException, UserIdAlreadyInUseException;

    public void addUser(User user) throws SQLException;// throws UserNotAddedException, UserIdAlreadyInUseException;


//    public void assignCourse(String courseCode, String professorId);// throws CourseNotFoundException, UserNotFoundException;
//
//    public List<Course> viewCourses(int catalogId);
//
//    public List<Professor> viewProfessors();
}
