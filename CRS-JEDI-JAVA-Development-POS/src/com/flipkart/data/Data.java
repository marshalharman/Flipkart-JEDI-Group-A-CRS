package com.flipkart.data;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import java.util.*;

public class Data {

    // list of Students
    public static List <Student> students= new ArrayList<Student>();

    public static Student s1,s2, s3,s4 = new Student();

    public static HashMap<Integer,List<Grade> > gradeList=new HashMap<Integer,List<Grade>>();
    // semID - courses
    public static TreeMap<Integer,List<Course>> semCourseList=new TreeMap<Integer,List<Course>>();

    public static HashMap<Integer, List<Course>> registeredCourses = new HashMap<>();

    static {
        Student st1 = new Student();
        st1.setName("Harman");
        st1.setPassword("abcd");
        st1.setUserID(1);

        Student st2 = new Student();
        st2.setName("Marshal");
        st2.setPassword("xyz");

        students.add(st1);
        students.add(st2);


        Course c1 = new Course();
        c1.setCourseName("Bio 111");
        Course c2 = new Course();
        c2.setCourseName("Chem 111");
        Course c3 = new Course();
        c3.setCourseName("Phy 111");

        List<Course> sem1List = new ArrayList<>();
        sem1List.add(c1);
        sem1List.add(c2);
        sem1List.add(c3);

        Course c4 = new Course();
        c4.setCourseName("Bio 211");
        Course c5 = new Course();
        c5.setCourseName("Chem 211");
        Course c6 = new Course();
        c6.setCourseName("Phy 211");

        List<Course> sem2List = new ArrayList<>();
        sem2List.add(c1);
        sem2List.add(c2);
        sem2List.add(c3);

        semCourseList.put(1, sem1List);

        List<Grade> tempGrades=new ArrayList<Grade>();
        Grade g1=new Grade();
        Grade g2=new Grade();
        Grade g3=new Grade();
        Grade g4=new Grade();
        g1.setStudentID(1);
        g1.setCourseID(11);
        g1.setScore('A');
        tempGrades.add(g1);

        g2.setStudentID(1);
        g2.setCourseID(12);
        g2.setScore('B');
        tempGrades.add(g2);

        g3.setStudentID(1);
        g3.setCourseID(13);
        g3.setScore('C');
        tempGrades.add(g3);

        g4.setStudentID(1);
        g4.setCourseID(14);
        g4.setScore('A');
        tempGrades.add(g4);

        gradeList.put(1,tempGrades);

        //semCourseList.put(2, sem2List);
    }



    public Data(){
//        semCourseList.put(1, List.of(new String[]{}));
    }


    // courses - freq

    // student - courses and grades



}
