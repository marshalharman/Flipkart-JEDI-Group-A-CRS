package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
//import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.SQLException;

public class ProfessorDAOImpl implements ProferssorDAO{

    @Override
    public List<Course> getCoursesByProfessor(String userId) {
        List<Course> courseList=new ArrayList<Course>();
        //PreparedStatement statement=Connection.prepareStatement("select * from course where professorId=?");
        return courseList;
    }

    @Override
    public Boolean addGrade(int studentId, String courseCode, String grade) {
        return null;
    }

    @Override
    public String getProfessorById(String profId) {
        return null;
    }
}
