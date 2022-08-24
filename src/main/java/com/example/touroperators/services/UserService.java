package com.example.touroperators.services;

import com.example.touroperators.dto.CreateUserDTO;
import com.example.touroperators.dto.UpdateUserDTO;
import com.example.touroperators.exceptions.ServiceValidationError;
import com.example.touroperators.models.User;
import com.example.touroperators.repositories.UserRepository;
import com.example.touroperators.services.Abstract.BaseService;
import com.example.touroperators.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService extends BaseService {

    private final UserRepository userRepository;

    private final UserValidator userValidator;

    public User createUser(CreateUserDTO createUserDTO) throws ServiceValidationError {

        CreateUserDTO validatedCreateUserDTO = userValidator.validateCreate(createUserDTO);

        User user = modelMapper.map(validatedCreateUserDTO, User.class);

        return userRepository.save(user);
    }

    public User getUserById(Long id){

        return userRepository.getUserById(id);
    }

    public User updateUser(User userInDb, UpdateUserDTO updateUserDTO){

        modelMapper.map(updateUserDTO, userInDb);

        return userRepository.save(userInDb);
    }

    public User deleteUserById(Long id){

        return userRepository.deleteUserById(id);
    }

}
