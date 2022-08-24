package com.example.touroperators.repositories;

import com.example.touroperators.models.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

    User save(User user);

    User getUserById(Long id);

    User getUserByUsername(String username);

    User deleteUserById(Long id);

}
