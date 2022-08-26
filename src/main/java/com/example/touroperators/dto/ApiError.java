package com.example.touroperators.dto;

import com.example.touroperators.dto.Abstract.BaseDTO;
import com.example.touroperators.dto.Abstract.ResponceDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ApiError extends ResponceDTO {

    private final Map<String, String> errors;

    public ApiError(Integer status, LocalDateTime timestamp, String path, Map<String, String> errors ) {
        super(status, timestamp, path);
        this.errors = errors;
    }
}
