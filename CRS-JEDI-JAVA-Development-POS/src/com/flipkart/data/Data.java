package com.flipkart.data;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;

import java.util.*;

public class Data {

    // list of Students
    public static List <Student> students= new ArrayList<Student>();

    public static Student s1,s2, s3,s4 = new Student();

    // semID - courses
    public static TreeMap<Integer,List<Course>> semCourseList=new TreeMap<Integer,List<Course>>();


    // studentID - Courses
    public static HashMap<Integer, List<Course>> registeredCourses = new HashMap<>();

    // courseId - count
    public static HashMap<Integer, Integer> courseEnrollmentCount = new HashMap<>();

    static {
        Student st1 = new Student();
        st1.setUserID(101);
        st1.setName("Harman");
        st1.setPassword("abcd");

        Student st2 = new Student();
        st1.setUserID(102);
        st2.setName("Marshal");
        st2.setPassword("xyz");

        students.add(st1);
        students.add(st2);


        Course c1 = new Course();
        c1.setCourseName("Bio 111");
        c1.setCourseID(101);

        Course c2 = new Course();
        c2.setCourseName("Chem 111");
        c2.setCourseID(102);

        Course c3 = new Course();
        c3.setCourseName("Phy 111");
        c3.setCourseID(103);

        List<Course> sem1List = new ArrayList<>();
        sem1List.add(c1);
        sem1List.add(c2);
        sem1List.add(c3);

        Course c4 = new Course();
        c4.setCourseName("Bio 211");
        c4.setCourseID(201);

        Course c5 = new Course();
        c5.setCourseName("Chem 211");
        c5.setCourseID(202);

        Course c6 = new Course();
        c6.setCourseName("Phy 211");
        c6.setCourseID(203);

        List<Course> sem2List = new ArrayList<>();
        sem2List.add(c4);
        sem2List.add(c5);
        sem2List.add(c6);

        semCourseList.put(1, sem1List);
        semCourseList.put(2, sem2List);
    }



    public Data(){
//        semCourseList.put(1, List.of(new String[]{}));
    }


    // courses - freq

    // student - courses and grades



}
