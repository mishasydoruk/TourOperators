package com.example.touroperators.controllers;

import com.example.touroperators.controllers.Abstract.BaseController;
import com.example.touroperators.dto.GetUserDTO;
import com.example.touroperators.dto.UpdateUserDTO;
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
    public ResponseEntity<GetUserDTO> getUser(@PathVariable Long userId) {

        User user = userService.getUserById(userId);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        GetUserDTO getUserDTO = modelMapper.map(user, GetUserDTO.class);

        return new ResponseEntity<>(getUserDTO, HttpStatus.OK);
    }

    @PutMapping("user/{userId}")
    public ResponseEntity<GetUserDTO> updateUser(@PathVariable Long userId, @Valid @RequestBody UpdateUserDTO updateUserDTO){

        User user = userService.getUserById(userId);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User updatedUser = userService.updateUser(user, updateUserDTO);

        GetUserDTO getUserDTO = modelMapper.map(updatedUser, GetUserDTO.class);

        return new ResponseEntity<>(getUserDTO, HttpStatus.OK);
    }

    @DeleteMapping("user/{userId}")
    public ResponseEntity<GetUserDTO> deleteUser(@PathVariable Long userId){

        List<User> users = userService.deleteUserById(userId);

        if (users.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        GetUserDTO getUserDTO = modelMapper.map(users.get(0), GetUserDTO.class);

        return new ResponseEntity<>(getUserDTO, HttpStatus.OK);
    }

}
