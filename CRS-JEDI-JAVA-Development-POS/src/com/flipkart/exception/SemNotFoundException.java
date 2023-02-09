package com.flipkart.exception;

import com.flipkart.constant.ColourConstant;

/**
 * Exception to check if sem exists
 * @author JEDI-2.0 Team A
 *
 */

public class SemNotFoundException extends Exception{

    private Integer semCode;

    public SemNotFoundException(Integer semCode)
    {
        this.semCode = semCode;
    }

    /**
     * Getter function for course code
     * @return integer with semCode
     */
    public Integer getSemCode()
    {
        return semCode;
    }


    /**
     * Message returned when exception is thrown
     */
    @Override
    public String getMessage()
    {
        return ColourConstant.ANSI_YELLOW+"Sem with semID: " + semCode + " not found."+ ColourConstant.ANSI_RESET;
    }
}
