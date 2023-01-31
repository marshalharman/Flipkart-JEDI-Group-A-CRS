package com.flipkart.bean;
import java.util.*;

public class Catalog {
    private List<Integer> courseIDs;

    public List<Integer> getCourseIDs() {
        return courseIDs;
    }
    private int semID;
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
