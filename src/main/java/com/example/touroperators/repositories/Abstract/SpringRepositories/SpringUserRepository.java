package com.example.touroperators.repositories.Abstract.SpringRepositories;

import com.example.touroperators.models.User;
import org.springframework.data.repository.Repository;

public interface SpringUserRepository extends Repository<User, Long> {

    User save(User user);

    User getUserById(Long id);

    User getUserByUsername(String username);

    User deleteUserById(Long id);

}
