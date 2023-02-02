package com.flipkart.bean;

public class Student extends User{

    private String branch;
    private boolean isApproved;

    private int semID;

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
}
