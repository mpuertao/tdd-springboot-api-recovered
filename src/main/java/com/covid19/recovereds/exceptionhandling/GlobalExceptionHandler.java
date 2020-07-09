package com.covid19.recovereds.exceptionhandling;

import com.covid19.recovereds.exceptionhandling.exceptions.DataBaseEmptyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleGeneralException(NoSuchElementException e, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        List<ErrorItem> errorItems = Stream.of(new ErrorItem(e.getMessage(), "400",
                "User no exist in application")).collect(Collectors.toList());
        return handleExceptionInternal(e,errorItems,headers, HttpStatus.BAD_REQUEST,request);
    }

    @ExceptionHandler(DataBaseEmptyException.class)
    public ResponseEntity<Object> handleGeneralException(DataBaseEmptyException e, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        List<ErrorItem> errorItems = Stream.of(new ErrorItem("DB is empty", "GL001",
                e.getMessage())).collect(Collectors.toList());
        return handleExceptionInternal(e,errorItems,headers, HttpStatus.BAD_REQUEST,request);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleGeneralException(RuntimeException e, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        List<ErrorItem> errorItems = Stream.of(new ErrorItem("Runtime Exception", "GL000",
                e.getMessage())).collect(Collectors.toList());
        return handleExceptionInternal(e,errorItems,headers, HttpStatus.BAD_REQUEST,request);
    }

}
