package com.flipkart.exception;

import com.flipkart.constant.ColourConstant;

/**
 * Exception course is deleted from catalog
 * @author JEDI-2.0 Team A
 *
 */
public class CourseNotDeletedException extends Exception{
    private Integer courseCode;

    public CourseNotDeletedException(Integer courseCode)
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
     * Message thrown by exception
     */
    @Override
    public String getMessage()
    {
        return ColourConstant.ANSI_YELLOW+"Course with courseCode: " + courseCode + " can not be deleted."+ ColourConstant.ANSI_RESET;
    }
}