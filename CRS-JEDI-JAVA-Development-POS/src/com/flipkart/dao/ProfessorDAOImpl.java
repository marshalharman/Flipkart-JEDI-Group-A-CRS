package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

import java.util.ArrayList;
import java.util.List;

public class ProfessorDAOImpl implements ProferssorDAO{

    @Override
    public List<Course> getCoursesByProfessor(String userId) {
        List<Course> courseList=new ArrayList<Course>();
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
