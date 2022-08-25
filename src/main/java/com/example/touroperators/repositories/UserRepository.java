package com.example.touroperators.repositories;

import com.example.touroperators.models.User;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends Repository<User, Long> {

    User save(User user);

    User getUserById(Long id);

    User getUserByUserName(String username);

    @Transactional
    List<User> deleteUserById(Long id);



}
