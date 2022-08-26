package com.example.touroperators.validators;

import com.example.touroperators.dto.Abstract.UserDTO;
import com.example.touroperators.dto.CreateUserDTO;
import com.example.touroperators.dto.UpdateUserDTO;
import com.example.touroperators.enums.UserRole;
import com.example.touroperators.exceptions.ServiceValidationError;
import com.example.touroperators.models.User;
import com.example.touroperators.repositories.TourOperatorReposirory;
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
    private final TourOperatorReposirory tourOperatorReposirory;

    public void validateAlreadyExists(CreateUserDTO createUserDTO) throws ServiceValidationError {

        boolean alreadyExists = userRepository
                .getUserByUserName(createUserDTO.getUserName())!=null;

        if(alreadyExists){
            throw new ServiceValidationError("userName", "userName already exists");
        }
    }

    public void validateMoreThan18Years(UserDTO userDTO) throws ServiceValidationError {

        if(Period.between(userDTO.getBirthday(), LocalDate.now()).getYears()<18){
            throw new ServiceValidationError("birthday", "Age is less than 18 years");
        }
    }

    public void validateTourOperatorExists(CreateUserDTO createUserDTO) throws ServiceValidationError {

        Long tourOperatorId = createUserDTO.getTourOperatorId();

        if(tourOperatorId == null)
        {
            return;
        }

        boolean tourOperatorNotExists = tourOperatorReposirory
                .getTourOperatorById(tourOperatorId) == null;

        if(tourOperatorNotExists){
            throw new ServiceValidationError("tourOperatorId", "Tour operator not exists");
        }
    }

    public void validateCorrectRoleIfTourOperatorNotNull(CreateUserDTO createUserDTO) throws ServiceValidationError {

        if(createUserDTO.getUserRole().equals(UserRole.ROLE_Customer) && createUserDTO.getTourOperatorId()!=null){
            throw new ServiceValidationError("tourOperatorId", "Customer can not have tour operator");
        }

        if(!createUserDTO.getUserRole().equals(UserRole.ROLE_Customer) && createUserDTO.getTourOperatorId()==null){
            throw new ServiceValidationError("tourOperatorId", "Role must have tour operator");
        }

    }

    public CreateUserDTO validateCreate(CreateUserDTO createUserDTO) throws ServiceValidationError {

        validateAlreadyExists(createUserDTO);

        validateMoreThan18Years(createUserDTO);

        validateTourOperatorExists(createUserDTO);

        validateCorrectRoleIfTourOperatorNotNull(createUserDTO);

        return createUserDTO;
    }

    public UpdateUserDTO validateUpdate(UpdateUserDTO updateUserDTO) throws ServiceValidationError {

        validateMoreThan18Years(updateUserDTO);

        return updateUserDTO;
    }
}
