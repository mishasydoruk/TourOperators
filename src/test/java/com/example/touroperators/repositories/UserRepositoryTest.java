package com.example.touroperators.repositories;

import static org.mockito.Mockito.when;

import com.example.touroperators.dto.CreateUserDTO;
import com.example.touroperators.models.User;
import com.example.touroperators.repositories.Abstract.SpringRepositories.SpringUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith({MockitoExtension.class})
class UserRepositoryTest {

    @Mock
    SpringUserRepository springUserRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    UserRepository userRepository;

    @Test
    void createUser() {
        CreateUserDTO createUserDTO = new CreateUserDTO();

        createUserDTO.setUserName("Username");
        createUserDTO.setPassword("password");
        createUserDTO.setLastName("LastName");

        User user = new User();
        user.setUserName("Username");
        user.setPassword("password");
        user.setLastName("LastName");

        when(userRepository.createUser(createUserDTO)).thenReturn(user);

        User createdUser = userRepository.createUser(createUserDTO);

        Assertions.assertEquals("Username", createdUser.getUserName());
        Assertions.assertEquals("password", createdUser.getPassword());
        Assertions.assertEquals("LastName", createdUser.getLastName());

    }

    @Test
    void getUserById() {
    }

    @Test
    void getUserByUsername() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUserById() {
    }
}