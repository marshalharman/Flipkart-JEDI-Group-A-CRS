package com.flipkart.service;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Payment;
import com.flipkart.bean.User;
import com.flipkart.bean.Course;
import com.flipkart.data.Data;
import java.util.*;
import com.flipkart.bean.Student;

public class StudentServiceOperation implements StudentInterface {

    Scanner sc = new Scanner(System.in);
    public void register(int studentId, String name, String address, String username, String password, String branch){

        for(Student s:Data.students)
        {
            if(s.getUserID()==studentId)
            {
                System.out.println("Student already exists, please Login\n");
                return;
            }
        }

        Student student = new Student();
        student.setUserID(studentId);
        student.setName(name);
        student.setAddress(address);
        student.setUsername(username);
        student.setPassword(password);
        student.setBranch(branch);
        student.setRole("Student");
        Data.unapprovedStudents.add(student);
        System.out.println("Student registered successfully.");
    }

    public int login(String studentName, String password){
        List<Student> studentsList= Data.students;
        int userId = -1;
        for(Student s:studentsList)
        {
            if(s.getName().equals(studentName) && s.getPassword().equals(password)) {
                userId = s.getUserID();
                break;
            }
        }
        return userId;
    }

    public void semesterRegister(Student student){

        System.out.println("Select course to register:");

        Scanner sc = new Scanner(System.in);
        System.out.println("1. BCA\n2.MCA");
        sc.nextLine();

        for(Map.Entry m: Data.semCourseList.entrySet()){
            System.out.println(m.getKey());
        }

        int semID = Integer.parseInt(sc.nextLine());

        student.setSemID(semID);
    }

    public List<Course> getCourses(int semID){
        List<Course> c=Data.semCourseList.get(semID);
        System.out.println("List of courses");
        for(int i=0;i<c.size();i++)
        {
            System.out.println(c.get(i).getCourseName()+"\n");
        }
        return c;
    }

    public void addCourse(Student student, int choice, int semID, String courseName){

        for( Course course: Data.semCourseList.get(student.getSemID())){
            if( course.getCourseName().equalsIgnoreCase(courseName) ){
                if( choice == 1){ student.addPrimaryCourse(course); }
                else if( choice == 2 ){ student.addAlternateCourse(course); }
            }
        }

    }

    public void  removeCourse(Student student, int choice){
        Scanner sc = new Scanner(System.in);

        if( choice == 1 ){
            System.out.println("Please select course : ");
            for(Course course: student.getPrimaryCourses()) {
                System.out.println(course.getCourseName());
            }
            String courseName = sc.nextLine();
            for(Course course: student.getPrimaryCourses()){
                if( course.getCourseName().equalsIgnoreCase(courseName) ){
                    student.removePrimaryCourse(course);
                    break;
                }
            }
        }
        else if( choice == 2 ){
            System.out.println("Please select course : ");
            for(Course course: student.getAlternateCourses()) {
                System.out.println(course.getCourseName());
            }
            String courseName = sc.nextLine();
            for(Course course: student.getAlternateCourses()){
                if( course.getCourseName().equalsIgnoreCase(courseName) ){
                    student.removeAlternateCourse(course);
                    break;
                }
            }
        }
    }
    public void dropCourse(Student student, String courseName){

        for(Course course: Data.semCourseList.get(student.getSemID()) ){
            if( course.getCourseName().equalsIgnoreCase(courseName) ){
                Data.registeredCourses.get(student.getUserID()).remove(course);
                Data.courseEnrollmentCount.put(course.getCourseID(), Data.courseEnrollmentCount.get(course.getCourseID())-1 );
                break;
            }
        }

    }

    public void submitPreferences(Student student){

        int registeredCourseCount = 0;
        Data.registeredCourses.put(student.getUserID(), new ArrayList<>());

        for(Course course: student.getPrimaryCourses()){
            if(!Data.courseEnrollmentCount.containsKey(course.getCourseID())){
                Data.courseEnrollmentCount.put(course.getCourseID(), 0);
            }
            int currentCount = Data.courseEnrollmentCount.get(course.getCourseID());
            if(currentCount < 10){
                Data.registeredCourses.get(student.getUserID()).add(course);
                currentCount++;
                Data.courseEnrollmentCount.put(course.getCourseID(), currentCount);
                registeredCourseCount++;
            }
        }

        for(Course course: student.getAlternateCourses()){
            if(!Data.courseEnrollmentCount.containsKey(course.getCourseID())){
                Data.courseEnrollmentCount.put(course.getCourseID(), 0);
            }
            int currentCount = Data.courseEnrollmentCount.get(course.getCourseID());
            if(registeredCourseCount < 4 && currentCount < 10){
                Data.registeredCourses.get(student.getUserID()).add(course);
                currentCount++;
                Data.courseEnrollmentCount.put(course.getCourseID(), currentCount);
                registeredCourseCount++;
            }
        }

        System.out.println("REGISTERED COURSES:");
        for(int i=0;i<registeredCourseCount;i++){
            System.out.println(Data.registeredCourses.get(student.getUserID()).get(i).getCourseName());
        }
    }

    public void getRegisteredCourses(Student student, int userId){

        for(Course course: Data.registeredCourses.get(userId)){
            System.out.println(course.getCourseName());
        }
    }



    public void viewGrades(int studentId){
        System.out.println();
        if(!Data.viewGradesEnabled){
            System.out.println("Grades are not available. Please check later");
            return;
        }
        HashMap<Integer,List<Grade> > hp= Data.gradeList;
        List<Grade> studentGrades=hp.get(studentId);
        System.out.println("Grades :");

        for(Grade grade:studentGrades){
            for( int semID : Data.semCourseList.keySet() ){
                for( Course c: Data.semCourseList.get(semID) ){
                    if( grade.getCourseID() == c.getCourseID() ){
                        System.out.println(c.getCourseName() + " : " + grade.getScore() );
                    }
                }
            }
        }


    }
}
