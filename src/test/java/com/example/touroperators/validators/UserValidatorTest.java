package com.example.touroperators.validators;

import com.example.touroperators.dto.CreateUserDTO;
import com.example.touroperators.enums.UserRole;
import com.example.touroperators.exceptions.Abstract.BaseCustomException;
import com.example.touroperators.exceptions.ServiceValidationError;
import com.example.touroperators.models.User;
import com.example.touroperators.repositories.UserRepository;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserValidatorTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserValidator userValidator;

    @Test
    void validateCreateAlreadyExists() {

        CreateUserDTO createUserDTO = new CreateUserDTO();

        createUserDTO.setBirthday(LocalDate.of(2002, 12, 1));
        createUserDTO.setUserName("username");
        createUserDTO.setLastName("lastname");
        createUserDTO.setPassword("password");
        createUserDTO.setUserRole(UserRole.ROLE_Customer);

        when(userRepository.getUserByUserName("username")).thenReturn(new User());
        //method should return not null, that's enough

        BaseCustomException exception = assertThrows(ServiceValidationError.class,
                () -> userValidator.validateCreate(createUserDTO));

        assertEquals(Map.of("userName", "userName already exists"), exception.getExceptionMap());
    }
}