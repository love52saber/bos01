package cn.itcast.controller.exception;

import org.springframework.validation.FieldError;

import java.util.List;

public class ParamException extends RuntimeException {

    private List<FieldError> fieldErrors;

    public ParamException() {
    }

    public ParamException(String message) {
        super(message);
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
