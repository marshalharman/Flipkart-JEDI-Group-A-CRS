package com.flipkart.exception;

import com.flipkart.constant.ColourConstant;

/**
 * Exception to check if the professor is not added successfully by admin
 * @author JEDI-2.0 Team A
 *
 */
public class ProfessorNotAddedException extends Exception{
    private Integer professorId;

    public ProfessorNotAddedException(Integer professorId) {
        this.professorId = professorId;
    }

    /**
     * Getter function for professorId
     * @return
     */
    public Integer getUserId() {
        return this.professorId;
    }


    /**
     * Message returned when exception is thrown
     */
    @Override
    public String getMessage() {
        return ColourConstant.ANSI_YELLOW+"professorId: " + professorId + " not added!"+ ColourConstant.ANSI_RESET;
    }
}
