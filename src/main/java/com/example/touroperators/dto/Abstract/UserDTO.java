package com.example.touroperators.dto.Abstract;

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
public abstract class UserDTO implements BaseDTO{

    protected static final int minPasswordSize = 8;

    @NotNull @NotBlank
    protected String lastName;

    @NotNull
    @Past
    @JsonFormat(pattern="yyyy-MM-dd")
    protected LocalDate birthday;

}
