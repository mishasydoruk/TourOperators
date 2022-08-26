package com.example.touroperators.exceptions;

import com.example.touroperators.exceptions.Abstract.BaseCustomException;

import java.util.Map;

public class NotFoundException extends BaseCustomException {

    public NotFoundException(Map<String, String> exceptionMap) {
        super(exceptionMap);
    }

    public NotFoundException(String field, String errorMessage){
        super(Map.of(field, errorMessage));
    }
}
