package com.example.api101withspringboot.controlleradvice;

import com.example.api101withspringboot.controlleradvice.model.ErrorRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(
            MethodArgumentNotValidException ex, WebRequest request) {

        final List<String> errors = new ArrayList<String>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ErrorRes res =  new ErrorRes(
                String.join(", ", errors)
        );

        return ResponseEntity.badRequest().body(
                res
        );

        // alternative
        // return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Object> numberFormatException(
            NumberFormatException ex, WebRequest request) {

        ErrorRes res =  new ErrorRes(
                "Can't parse cause : " + ex.getMessage()
        );

        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

}
