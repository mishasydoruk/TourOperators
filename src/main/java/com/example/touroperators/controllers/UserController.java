package com.example.touroperators.controllers;

import com.example.touroperators.controllers.Abstract.BaseController;
import com.example.touroperators.dto.GetUserDTO;
import com.example.touroperators.dto.UpdateUserDTO;
import com.example.touroperators.exceptions.NotFoundException;
import com.example.touroperators.exceptions.ServiceValidationError;
import com.example.touroperators.models.User;
import com.example.touroperators.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController extends BaseController {

    private final UserService userService;

    @GetMapping("user/{userId}")
    public ResponseEntity<GetUserDTO> getUser(@PathVariable Long userId) throws NotFoundException {

        User user = userService.getUserById(userId);

        if (user == null) {
            throw new NotFoundException("userId", "Not found");
        }

        GetUserDTO getUserDTO = modelMapper.map(user, GetUserDTO.class);

        return new ResponseEntity<>(getUserDTO, HttpStatus.OK);
    }

    @PutMapping("user/{userId}")
    public ResponseEntity<GetUserDTO> updateUser(@PathVariable Long userId, @Valid @RequestBody UpdateUserDTO updateUserDTO) throws NotFoundException, ServiceValidationError {

        User user = userService.getUserById(userId);

        if (user == null) {
            throw new NotFoundException("userId", "Not found");
        }

        User updatedUser = userService.updateUser(user, updateUserDTO);

        GetUserDTO getUserDTO = modelMapper.map(updatedUser, GetUserDTO.class);

        return new ResponseEntity<>(getUserDTO, HttpStatus.OK);
    }

    @DeleteMapping("user/{userId}")
    public ResponseEntity<GetUserDTO> deleteUser(@PathVariable Long userId) throws NotFoundException {

        List<User> users = userService.deleteUserById(userId);

        if (users.size() == 0) {
            throw new NotFoundException("userId", "Not found");
        }

        GetUserDTO getUserDTO = modelMapper.map(users.get(0), GetUserDTO.class);

        return new ResponseEntity<>(getUserDTO, HttpStatus.OK);
    }

}
