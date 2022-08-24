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

    public CreateUserDTO validateCreate(CreateUserDTO createUserDTO) throws ServiceValidationError {

        validateAlreadyExists(createUserDTO);

        return createUserDTO;
    }
}
