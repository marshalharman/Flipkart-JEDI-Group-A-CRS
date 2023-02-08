package com.flipkart.exception;


import com.flipkart.constant.Colours;

/**
 * Exception to check if course is available in catalog
 * @author JEDI-2.0 Team A
 *
 */
public class CourseNotFoundByNameException extends Exception{
    private String courseName;

    public CourseNotFoundByNameException(String courseName)
    {
        this.courseName = courseName;
    }

    /**
     * Getter function for course code
     * @return
     */
    public String getCourseName()
    {
        return courseName;
    }


    /**
     * Message returned when exception is thrown
     */
    @Override
    public String getMessage()
    {
        return Colours.ANSI_YELLOW+"Course with Name: " + courseName + " not found."+Colours.ANSI_RESET;
    }
}