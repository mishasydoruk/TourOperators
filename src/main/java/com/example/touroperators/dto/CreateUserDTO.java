package com.example.touroperators.dto;

import com.example.touroperators.dto.Abstract.BaseDTO;
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
public class CreateUserDTO implements BaseDTO {

    private static final int minPasswordSize = 8;

    @NotNull @NotBlank
    String userName;

    @NotNull @NotBlank
    String lastName;

    @NotNull
    UserRole userRole;

    @NotNull
    @Past
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;

    @NotNull @NotBlank @Size(min=minPasswordSize)
    String password;

}
