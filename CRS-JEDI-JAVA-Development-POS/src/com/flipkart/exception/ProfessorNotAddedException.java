package com.flipkart.exception;

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
        return "professorId: " + professorId + " not added!";
    }
}
