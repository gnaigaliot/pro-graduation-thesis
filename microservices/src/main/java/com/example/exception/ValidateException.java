package com.example.exception;

public class ValidateException extends SysException {

    private String code;
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * ValidateException
     * 
     * @param code
     */
    public ValidateException(String code) {
        this.code = code;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }
}