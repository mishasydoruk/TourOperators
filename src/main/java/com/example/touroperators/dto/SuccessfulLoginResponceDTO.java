package com.example.touroperators.dto;

import com.example.touroperators.dto.Abstract.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessfulLoginResponceDTO implements BaseDTO {

    Long id;

    String token;
}
