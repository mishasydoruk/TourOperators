package com.example.touroperators.dto;

import com.example.touroperators.dto.Abstract.BaseDTO;
import com.example.touroperators.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class RegisterUserDTO implements BaseDTO {

    private Long id;

    private String userName;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;

    @NotNull
    UserRole userRole;

    private String lastName;
}
