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
<<<<<<< HEAD

        List<Grade> tempGrades=new ArrayList<Grade>();
        Grade g=new Grade();
        g.setStudentID(1);
        g.setCourseID(11);
        g.setScore('A');
        tempGrades.add(g);

        g.setCourseID(12);
        g.setScore('B');
        tempGrades.add(g);

        g.setCourseID(13);
        g.setScore('C');
        tempGrades.add(g);

        g.setCourseID(14);
        g.setScore('A');
        tempGrades.add(g);


=======
        semCourseList.put(2, sem2List);
>>>>>>> 9a34f44107bd182c85fb5eeed1a40385e4ad35af
    }



    public Data(){
//        semCourseList.put(1, List.of(new String[]{}));
    }


    // courses - freq

    // student - courses and grades



}
