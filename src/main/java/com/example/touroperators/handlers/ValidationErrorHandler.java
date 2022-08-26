package com.example.touroperators.handlers;

import com.example.touroperators.exceptions.ServiceValidationError;
import com.example.touroperators.handlers.Abstract.BaseExceptionHandler;
import com.example.touroperators.dto.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ValidationErrorHandler implements BaseExceptionHandler {

    @ExceptionHandler(ServiceValidationError.class)
    public ResponseEntity<ApiError> catchServiceValidationError(ServiceValidationError ex, HttpServletRequest request){

        ApiError apiError =new ApiError(HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                request.getServletPath(),
                ex.getExceptionMap());

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> catchMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request){

        Map<String, String> exceptionMap = new HashMap<>();

        ex.getFieldErrors()
                .forEach(er -> exceptionMap.put(er.getField(),er.getDefaultMessage()));

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                request.getServletPath(),
                exceptionMap);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

}
