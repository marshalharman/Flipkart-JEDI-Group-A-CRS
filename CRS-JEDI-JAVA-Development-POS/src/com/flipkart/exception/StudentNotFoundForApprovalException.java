package com.flipkart.exception;

/**
 * Exception thrown when student is not found for approval
 * @author JEDI-2.0 Team A
 *
 */
public class StudentNotFoundForApprovalException extends Exception {
    private int studentId;

    public StudentNotFoundForApprovalException(int studentId) {
        this.studentId = studentId;
    }

    /**
     * Getter function for studentId
     * @return
     */
    public int getStudentId() {
        return this.studentId;
    }


    /**
     * Message returned when exception is thrown
     */
    @Override
    public String getMessage() {
        return "studentId: " + studentId + " not found for approval!" ;
    }
}
