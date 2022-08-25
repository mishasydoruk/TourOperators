package com.example.touroperators.exceptions.Abstract;

import lombok.Getter;

import java.util.Map;

@Getter
public abstract class BaseCustomException extends Exception {

    private final Map<String, String> exceptionMap;

    public BaseCustomException(Map<String, String> exceptionMap){
        this.exceptionMap = exceptionMap;
    }
}
