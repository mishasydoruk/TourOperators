package com.example.touroperators.dto;

import com.example.touroperators.dto.Abstract.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateUserDTO implements BaseDTO {

    @NotNull @NotBlank
    String userName;

    @NotNull @NotBlank
    String lastName;

    @NotNull @NotBlank
    String password;

}
