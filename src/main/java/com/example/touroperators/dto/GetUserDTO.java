package com.example.touroperators.dto;

import com.example.touroperators.dto.Abstract.BaseDTO;
import com.example.touroperators.dto.Abstract.UserDTO;
import com.example.touroperators.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GetUserDTO implements BaseDTO {

    private Long id;

    private String userName;

    UserRole userRole;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;

    private String lastName;
}
