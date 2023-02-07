package com.flipkart.exception;


/**
 * Exception to check if course is available in catalog
 * @author JEDI-2.0 Team A
 *
 */
public class CourseNotFoundException extends Exception{
    private Integer courseCode;

    public CourseNotFoundException(Integer courseCode)
    {
        this.courseCode = courseCode;
    }

    /**
     * Getter function for course code
     * @return
     */
    public Integer getCourseCode()
    {
        return courseCode;
    }


    /**
     * Message returned when exception is thrown
     */
    @Override
    public String getMessage()
    {
        return "Course with courseCode: " + courseCode + " not found.";
    }
}