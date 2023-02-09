package com.flipkart.constant;

public class SQLConstants {

    // AdminDao queries
    public static final String ADD_ADMIN = "INSERT INTO Admin VALUES (?, ?)";
    public static final String FIND_COURSE = "Select * from courses where courseId = ?";
    public static final String DELETE_FROM_CATALOG = "DELETE FROM Catalog WHERE CourseId = ?";
    public static final String DELETE_FROM_SEM_REGISTRATION = "DELETE FROM SemRegistration WHERE CourseID = ?";
    public static final String DELETE_FROM_COURSES = "DELETE FROM Courses WHERE CourseID = ?";
    public static final String ADD_COURSE = "INSERT INTO Courses(CourseID, Name) VALUES (?, ?)";
    public static final String INSERT_INTO_CATALOG = "INSERT INTO Catalog(CourseId, SemID) VALUES (?, ?)";
    public static final String APPROVE_STUDENT_BY_ID = "UPDATE User SET isApproved = 1 WHERE UserId = ?";
    public static final String ADD_PROFESSOR = "INSERT INTO Professor(ProfId, Name, Department, Designation) values (?, ?, ?, ?)";
    public static final String GENERATE_GRADE_CARD = "UPDATE Student SET GradesEnabled = ? WHERE GradesEnabled = ?";
    public static final String VIEW_UNAPPROVED_STUDENTS = "SELECT UserID, UserName from crs_database.User where isApproved = 0";
    public static final String APPROVE_ALL_STUDENTS = "UPDATE User SET isApproved = 1";



    // PaymentDao queries
    public static final String NUMBER_OF_REGISTERED_COURSES = "select count(*) from SemRegistration where StudentID = ?";
    public static final String ADD_UPI = "insert into UPI values(?,?)";
    public static final String ADD_CARD = "insert into CardDetails values(?,?,?,?)";
    public static final String SAVE_PAYMENT = "insert into Payment values(?,?,?,?,?)";
    public static final String SAVE_NOTIFICATION = "insert into Notification values(?,?,?)";


    // ProfessorDao queries
    public static final String VIEW_COURSES_BY_SEM_ID = "SELECT Courses.CourseID, Courses.Name, Courses.ProfID FROM Courses INNER JOIN Catalog ON Courses.CourseID = Catalog.CourseId WHERE Catalog.SemID = (?)";
    public static final String REGISTER_COURSE_FOR_PROFESSOR = "UPDATE Courses SET ProfID = (?) WHERE CourseID = (?)";
    public static final String DEREGISTER_COURSE_FOR_PROFESSOR = "UPDATE Courses SET ProfID = (?) WHERE CourseID = (?)";
    public static final String VIEW_ENROLLED_STUDENTS = "SELECT * FROM SemRegistration INNER JOIN Student ON SemRegistration.StudentID = Student.StudentID WHERE SemRegistration.CourseID=?";
    public static final String GET_STUDENT_BY_ID = "Select StudentID, Name, Address, Branch, Degree, SemID FROM Student WHERE StudentID = (?)";
    public static final String GET_COURSES_BY_PROFESSOR = "Select CourseID, Name, ProfID FROM Courses WHERE ProfID = (?)";
    public static final String ADD_GRADE = "UPDATE SemRegistration SET Grade = (?) WHERE StudentID = (?) AND CourseID = (?)";
    public static final String GET_SEM_ID_BY_COURSE_ID = "SELECT SemID FROM Catalog WHERE CourseId = (?)";
    public static final String GET_COURSE_BY_NAME = "SELECT CourseID, Name, ProfID FROM Courses WHERE Name=?";
    public static final String GET_COURSE_BY_ID = "SELECT CourseID, Name, ProfID FROM Courses WHERE CourseID=?";
    public static final String GET_PROFESSOR_BY_ID = "SELECT ProfID, Name, Department, Designation FROM Professor WHERE ProfID = (?)";


    // StudentDao queries

    public static final String REGISTER_USER = "INSERT INTO User VALUES (?,?,?,?,?)";
    public static final String REGISTER_STUDENT = "INSERT INTO Student (StudentID, Name, Address, Branch, Degree) VALUES (?,?,?,?,?);";
    public static final String GET_SEMESTER_LIST = "SELECT DISTINCT semID FROM Catalog";
    public static final String SET_SEM_ID = "UPDATE Student SET SemID = ? WHERE StudentID = ?";
    public static final String GET_COURSES = "SELECT Courses.CourseID, Courses.Name, Courses.ProfID FROM crs_database.Catalog INNER JOIN crs_database.Courses ON Catalog.CourseId = Courses.CourseID WHERE semID = ? AND ProfID IS NOT NULL AND ProfID != -1";
    public static final String GET_COURSE_ENROLLMENT_COUNT = "SELECT CourseID, COUNT(studentID) AS Frequency FROM SemRegistration GROUP BY CourseID";
    public static final String REGISTER_COURSES = "INSERT INTO SemRegistration (`StudentID`, `CourseID`, `SemID`) VALUES  (?, ?, ?)";
    public static final String DROP_COURSE = "DELETE FROM SemRegistration WHERE CourseID = (?) AND StudentID = (?)";
    public static final String GET_REGISTERED_COURSE = "SELECT Courses.CourseID, Courses.Name, Courses.ProfID FROM SemRegistration INNER JOIN Courses ON SemRegistration.CourseID = Courses.CourseID WHERE StudentID=?";
    public static final String CHECK_GRADES_ENABLED = "select GradesEnabled from Student where StudentID=(?)";
    public static final String VIEW_GRADES = "SELECT Courses.CourseID AS CourseID, Grade, Name AS CourseName FROM SemRegistration INNER JOIN Courses ON SemRegistration.CourseID = Courses.CourseID WHERE StudentID = ?";

    // UserDao queries
    public static final String LOGIN = "SELECT * FROM User WHERE UserID=? AND Password=?";
    public static final String UPDATE_PASSWORD = "UPDATE USER SET Password = ? where UserID = ?";
    public static final String GET_USER_BY_ID = "SELECT * FROM User WHERE UserID=?";







}
