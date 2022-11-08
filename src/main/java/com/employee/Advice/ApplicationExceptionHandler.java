package com.employee.Advice;

import com.employee.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException exception){

        Map<String ,String> errorMap = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach(error->{
                errorMap.put(exception.getObjectName(), exception.getMessage());
        });
        return errorMap;
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EmployeeNotFoundException.class)
    public Map<String,String> userNotFound(EmployeeNotFoundException ex){
        Map<String,String> errMap = new HashMap<>();
        errMap.put("Error",ex.getMessage());
        return errMap;
    }

}
