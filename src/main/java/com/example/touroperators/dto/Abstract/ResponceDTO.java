package com.example.touroperators.dto.Abstract;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Setter
public abstract class ResponceDTO implements BaseDTO{

    protected final Integer status;
    protected final LocalDateTime timestamp;
    protected final String path;
}
