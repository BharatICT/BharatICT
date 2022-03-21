package com.tatvasoft.exception;

import java.util.Map;

import com.tatvasoft.commonentity.ErrorResponse;


public class ValidationErrorResponse extends ErrorResponse {

    private Map<String, String> fieldErrors;

    public ValidationErrorResponse() {
    }

    public ValidationErrorResponse(Map<String, String> fieldErrors, int status, String message) {
        super(message, status);
        this.fieldErrors = fieldErrors;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

	@Override
	public String toString() {
		return "ValidationErrorResponse [fieldErrors=" + fieldErrors
				+ ", getFieldErrors()=" + getFieldErrors() + ", getMessage()="
				+ getMessage() + ", getStatus()=" + getStatus()
				+ ", getTimeStamp()=" + getTimeStamp() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}