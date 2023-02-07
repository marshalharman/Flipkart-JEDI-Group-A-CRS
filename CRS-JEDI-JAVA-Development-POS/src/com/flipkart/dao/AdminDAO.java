package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
public interface AdminDAO {

    public void addAdmin(int userID, String name);
    public void deleteCourse(int courseID);// throws CourseNotFoundException, CourseNotDeletedException;
    public void addCourse(Course course, int semID);// throws CourseFoundException;
    public void approveStudent(int studentID);//throws StudentNotFoundForApprovalException;
    public void addProfessor(Professor professor);// throws ProfessorNotAddedException, UserIdAlreadyInUseException;
    public void generateGradeCard();
}
