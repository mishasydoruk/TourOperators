package com.example.touroperators.validators;

import com.example.touroperators.dto.CreateUserDTO;
import com.example.touroperators.exceptions.ServiceValidationError;
import com.example.touroperators.models.User;
import com.example.touroperators.repositories.UserRepository;
import com.example.touroperators.validators.Abstract.BaseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;

@Component
@RequiredArgsConstructor
public class UserValidator implements BaseValidator {

    private final UserRepository userRepository;

    public void validateAlreadyExists(CreateUserDTO createUserDTO) throws ServiceValidationError {

        boolean alreadyExists = userRepository
                .getUserByUserName(createUserDTO.getUserName())!=null;

        if(alreadyExists){
            throw new ServiceValidationError("userName", "userName already exists");
        }
    }

    public void validateMoreThan18Years(CreateUserDTO createUserDTO) throws ServiceValidationError {

        if(Period.between(createUserDTO.getBirthday(), LocalDate.now()).getYears()<18){
            throw new ServiceValidationError("bithday", "Age is less than 18 years");
        }
    }

    public CreateUserDTO validateCreate(CreateUserDTO createUserDTO) throws ServiceValidationError {

        validateAlreadyExists(createUserDTO);

        validateMoreThan18Years(createUserDTO);

        return createUserDTO;
    }
}
