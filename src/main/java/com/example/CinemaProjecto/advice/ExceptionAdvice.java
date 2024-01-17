package com.example.CinemaProjecto.advice;

import com.example.CinemaProjecto.exceptions.NoSeatsException;
import com.example.CinemaProjecto.exceptions.NotFoundException;
import com.example.CinemaProjecto.exceptions.TicketNotCancellable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionAdvice {

    @Data
    @AllArgsConstructor
    public static class ExceptionFormat {
        String message, details;
    }
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<String> operationException(NotFoundException e) {
//        Map<String, String> response = new HashMap<>();
//        response.put("message", e.getMessage());
        var response = e.getMessage();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NoSeatsException.class})
    public ResponseEntity<String> ticketException(NoSeatsException e) {
        var response = e.getMessage();
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({TicketNotCancellable.class})
    public ResponseEntity<String> ticketNotCancellable(TicketNotCancellable e) {
        var response = e.getMessage();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<String> operationException(MethodArgumentNotValidException e) {
        String fieldName = e.getBindingResult().getFieldError().getField();
        var fieldValue = e.getBindingResult().getFieldError().getRejectedValue();
        String fieldMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        var response = fieldName + ' ' + fieldMessage;

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
