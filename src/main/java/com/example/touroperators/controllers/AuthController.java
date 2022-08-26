package com.example.touroperators.controllers;

import com.example.touroperators.controllers.Abstract.BaseController;
import com.example.touroperators.dto.CreateUserDTO;
import com.example.touroperators.dto.GetUserDTO;
import com.example.touroperators.dto.LoginDTO;
import com.example.touroperators.dto.SuccessfulLoginResponceDTO;
import com.example.touroperators.exceptions.ServiceValidationError;
import com.example.touroperators.models.User;
import com.example.touroperators.security.Jwt.JwtTokenUtil;
import com.example.touroperators.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController extends BaseController {

    private final UserService userService;

    private final AuthenticationManager authManager;

    private final JwtTokenUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<GetUserDTO> register(@Valid @RequestBody CreateUserDTO createUserDTO) throws ServiceValidationError {

        User createdUser = userService.createUser(createUserDTO);

        GetUserDTO userToSend = modelMapper.map(createdUser, GetUserDTO.class);

        return new ResponseEntity<>(userToSend, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessfulLoginResponceDTO> login(@Valid @RequestBody LoginDTO loginDTO) {

        Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUserName(), loginDTO.getPassword())
            );

        User user = modelMapper.map(authentication.getPrincipal(), User.class);

        String accessToken = jwtUtil.generateAccessToken(user);

        SuccessfulLoginResponceDTO loginResponseDTO = new SuccessfulLoginResponceDTO();

        loginResponseDTO.setId(userService.getUserByUserName(user.getUserName()).getId());
        loginResponseDTO.setToken(accessToken);

        return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
    }
}
