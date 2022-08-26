package com.example.touroperators.dto;

import com.example.touroperators.dto.Abstract.BaseDTO;
import com.example.touroperators.dto.Abstract.UserDTO;
import com.example.touroperators.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class CreateUserDTO extends UserDTO {

    @NotNull @NotBlank
    String userName;

    @NotNull
    UserRole userRole;

    @NotNull @NotBlank @Size(min=minPasswordSize)
    String password;

    Long tourOperatorId;
}
