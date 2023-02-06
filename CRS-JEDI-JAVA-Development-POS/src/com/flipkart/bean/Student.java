package com.flipkart.bean;

import java.util.*;

public class Student extends User{

    private String branch;
    private String degree;

    public String getDegree() {
        return degree;
    }

    private boolean isApproved;
    private int semID;

//    private List<Course> primaryCourses = new ArrayList<Course>();
//    private List<Course> alternateCourses = new ArrayList<Course>();

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public int getSemID() {
        return semID;
    }

    public void setSemID(int semID) {
        this.semID = semID;
    }

//    public List<Course> getPrimaryCourses() {
//        return primaryCourses;
//    }
//
//    public List<Course> getAlternateCourses() {
//        return alternateCourses;
//    }
//
//    public boolean addPrimaryCourse(Course course){
//        if( primaryCourses.size() == 4 ){ return false;}
//        primaryCourses.add(course);
//        return true;
//    }
//
//    public boolean addAlternateCourse(Course course){
//        if( alternateCourses.size() == 2){ return false;}
//        alternateCourses.add(course);
//        return true;
//    }

//    public void removePrimaryCourse(Course course){
//        primaryCourses.remove(course);
//    }
//
//    public void removeAlternateCourse(Course course){
//        alternateCourses.remove(course);
//    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

}
