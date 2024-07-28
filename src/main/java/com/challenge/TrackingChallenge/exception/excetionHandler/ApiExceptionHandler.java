package com.challenge.TrackingChallenge.exception.excetionHandler;

import com.challenge.TrackingChallenge.exception.CustomException;
import com.challenge.TrackingChallenge.exception.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.HibernateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<Problem.Field> fields = new ArrayList<>();
        for (var error : ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            fields.add(new Problem.Field(name, message));
        }

        Problem problem = new Problem(LocalDateTime.now());
        problem.setStatus(status.value());
        problem.setTitle("Um ou mais campos inválidos, preencha corretamente!");
        problem.setFields(fields);


        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        List<Problem.Field> fields = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String name = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            fields.add(new Problem.Field(name, message));
        }

        Problem problem = new Problem(LocalDateTime.now());
        problem.setStatus(HttpStatus.BAD_REQUEST.value());
        problem.setTitle("Um ou mais campos inválidos, preencha corretamente!");
        problem.setFields(fields);

        return new ResponseEntity<>(problem, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> customException(CustomException ex, WebRequest request) {
        HttpStatusCode status = HttpStatus.BAD_REQUEST;

        Problem problem = new Problem(LocalDateTime.now());

        problem.setTitle(ex.getMessage());
        problem.setStatus(status.value());

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        Problem problem = new Problem(LocalDateTime.now());
        problem.setStatus(status.value());
        problem.setTitle(ex.getMessage());


        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(HibernateException.class)
    public ResponseEntity<Object> handleHibernateException(HibernateException he) {
        HttpStatusCode status = HttpStatus.INTERNAL_SERVER_ERROR;

        Problem problem = new Problem(LocalDateTime.now());

        problem.setTitle(he.getMessage());
        problem.setStatus(status.value());

        return new ResponseEntity<>(problem, status);
    }
}
