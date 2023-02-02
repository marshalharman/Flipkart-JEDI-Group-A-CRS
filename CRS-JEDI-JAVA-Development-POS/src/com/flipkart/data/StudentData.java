package com.flipkart.data;

import com.flipkart.bean.Student;

import java.util.*;

public class StudentData {

    // list of Students


    // semID - courses
    public HashMap<Integer,List<String>> semCourseList=new HashMap<Integer,List<String>>();
    public List<Student> studentsList = new ArrayList<Student>();
    public StudentData(){
        semCourseList.put(1, List.of(new String[]{"Chem-111", "Phy-111", "Bio-111", "Math-111"}));
        semCourseList.put(2, List.of(new String[]{"Chem-211", "Phy-211", "Bio-211", "Math-211"}));
        semCourseList.put(3, List.of(new String[]{"Chem-311", "Phy-311", "Bio-311", "Math-311"}));
        Student s= new Student();
        s.setUserID(1);
        s.setName("1");
        s.setAddress("1");
        s.setBranch("1");
        studentsList.add(s);

    }




    // courses - freq

    // student - courses and grades



}
