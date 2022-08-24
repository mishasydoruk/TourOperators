package com.example.touroperators.dto;

import com.example.touroperators.dto.Abstract.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateUserDTO implements BaseDTO {

    private static final int minPasswordSize = 8;

    @NotNull @NotBlank
    String userName;

    @NotNull @NotBlank
    String lastName;

    @NotNull @NotBlank @Size(min=minPasswordSize)
    String password;

}
