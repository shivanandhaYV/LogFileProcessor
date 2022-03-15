package com.tejora.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ErrorDto error = new ErrorDto("failure", processFieldErrors(ex.getBindingResult().getFieldErrors()));
        System.out.println("error = " + error);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception ex) {
        ErrorDto errorDto = new ErrorDto("failure",
                ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.CREATED).body(errorDto);
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<ErrorDto> handleQuestionNotException(QuestionNotFoundException ex){
        ErrorDto errorDto = new ErrorDto("failure",ex.getLocalizedMessage());
        System.out.println("exception in question not found " +errorDto.toString());
        return ResponseEntity.badRequest().body(errorDto);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorDto> handleDataNotException(DataNotFoundException ex){
        ErrorDto errorDto = new ErrorDto("failure",ex.getLocalizedMessage());
        System.out.println("exception in question not found " +errorDto.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }


    private String processFieldErrors(List<FieldError> fieldErrors) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors.toString();
    }
}
