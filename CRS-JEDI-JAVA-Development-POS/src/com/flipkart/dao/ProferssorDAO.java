package com.flipkart.dao;
import com.flipkart.bean.Course;
import java.util.*;
public interface ProferssorDAO {
    public List<Course> getCoursesByProfessor(String userId);
    public Boolean addGrade(int studentId,String courseCode,String grade);
    public String getProfessorById(String profId);
}
