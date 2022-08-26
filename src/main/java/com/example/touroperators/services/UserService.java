package com.example.touroperators.services;

import com.example.touroperators.dto.CreateUserDTO;
import com.example.touroperators.dto.UpdateUserDTO;
import com.example.touroperators.exceptions.ServiceValidationError;
import com.example.touroperators.models.Tour;
import com.example.touroperators.models.User;
import com.example.touroperators.repositories.TourRepository;
import com.example.touroperators.repositories.UserRepository;
import com.example.touroperators.services.Abstract.BaseService;
import com.example.touroperators.validators.UserValidator;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService {

    private final UserRepository userRepository;

    private final UserValidator userValidator;

    private final PasswordEncoder passwordEncoder;

    public UserService(ModelMapper modelMapper,
                       UserRepository userRepository,
                       UserValidator userValidator,
                       PasswordEncoder passwordEncoder) {
        super(modelMapper);
        this.userRepository = userRepository;
        this.userValidator = userValidator;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateUserDTO createUserDTO) throws ServiceValidationError {

        createUserDTO.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));

        CreateUserDTO validatedCreateUserDTO = userValidator.validateCreate(createUserDTO);

        User user = modelMapper.map(validatedCreateUserDTO, User.class);

        return userRepository.save(user);
    }

    public User getUserById(Long id){

        return userRepository.getUserById(id);
    }

    public User getUserByUserName(String userName){

        return userRepository.getUserByUserName(userName);
    }

    public User updateUser(User userInDb, UpdateUserDTO updateUserDTO) throws ServiceValidationError {

        if (updateUserDTO.getPassword() == null) {
            updateUserDTO.setPassword(userInDb.getPassword());
        } else {
            updateUserDTO.setPassword(passwordEncoder.encode(updateUserDTO.getPassword()));
        }

        UpdateUserDTO validatedUpdateUserDTO = userValidator.validateUpdate(updateUserDTO);

        modelMapper.map(validatedUpdateUserDTO, userInDb);

        return userRepository.save(userInDb);
    }


    public List<User> deleteUserById(Long id){

        return userRepository.deleteUserById(id);
    }

}
