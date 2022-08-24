package com.example.touroperators.dto;

import com.example.touroperators.dto.Abstract.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDTO implements BaseDTO {

    Long id;

    String userName;

    String lastName;
}
