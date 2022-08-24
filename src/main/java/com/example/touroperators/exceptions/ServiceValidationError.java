package com.example.touroperators.exceptions;

import com.example.touroperators.exceptions.Abstract.BaseCustomException;

import java.util.Map;

public class ServiceValidationError extends BaseCustomException {

    public ServiceValidationError(Map<String, String> exceptionMap) {
        super(exceptionMap);
    }

    public ServiceValidationError(String field, String errorMessage){
        super(Map.of(field, errorMessage));
    }
}
