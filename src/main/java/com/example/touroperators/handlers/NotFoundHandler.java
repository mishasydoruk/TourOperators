package com.example.touroperators.handlers;

import com.example.touroperators.dto.ApiError;
import com.example.touroperators.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class NotFoundHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex, HttpServletRequest request){

        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                request.getServletPath(),
                ex.getExceptionMap());

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
