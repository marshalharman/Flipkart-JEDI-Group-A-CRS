package com.flipkart.exception;

import com.flipkart.constant.Colours;

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
        return Colours.ANSI_YELLOW+"studentId: " + studentId + " not found for approval!"+Colours.ANSI_RESET ;
    }
}
