package com.flipkart.bean;

import java.util.List;

public class Catalog {
    private List<Integer> courseIDs;
    private List<String> courseNames;
    private int semID;

    public List<String> getCourseNames() {
        return courseNames;
    }

    public void setCourseNames(List<String> courseNames) {
        this.courseNames = courseNames;
    }

    public List<Integer> getCourseIDs() {
        return courseIDs;
    }
    public void setCourseIDs(List<Integer> courseIDs) {
        this.courseIDs = courseIDs;
    }

    public int getSemID() {
        return semID;
    }

    public void setSemID(int semID) {
        this.semID = semID;
    }

}
