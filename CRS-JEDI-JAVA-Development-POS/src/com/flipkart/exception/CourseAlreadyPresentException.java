package com.flipkart.exception;
import com.flipkart.constant.Colours;

/**
 * Exception to check if course is available in catalog
 * @author JEDI-2.0 Team A
 *
 */
public class CourseAlreadyPresentException extends Exception{
    private Integer courseCode;

    public CourseAlreadyPresentException(Integer courseCode)
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
        return Colours.ANSI_YELLOW+"Course with courseID: " + courseCode + " already present"+Colours.ANSI_RESET;
    }
}