package com.example.touroperators.controllers;

import com.example.touroperators.controllers.Abstract.BaseController;
import com.example.touroperators.dto.CreateUserDTO;
import com.example.touroperators.dto.RegisterUserDTO;
import com.example.touroperators.exceptions.ServiceValidationError;
import com.example.touroperators.models.User;
import com.example.touroperators.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class AuthController extends BaseController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserDTO> register(@Valid @RequestBody CreateUserDTO createUserDTO) throws ServiceValidationError {

        User createdUser = userService.createUser(createUserDTO);

        RegisterUserDTO userToSend = modelMapper.map(createdUser, RegisterUserDTO.class);

        return new ResponseEntity<>(userToSend, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody CreateUserDTO createUserDTO) throws ServiceValidationError {

        User createdUser = userService.createUser(createUserDTO);

        RegisterUserDTO userToSend = modelMapper.map(createdUser, RegisterUserDTO.class);

        return new ResponseEntity<>(userToSend, HttpStatus.CREATED);
    }



}
