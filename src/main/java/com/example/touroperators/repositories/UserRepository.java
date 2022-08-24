package com.example.touroperators.repositories;

import com.example.touroperators.dto.CreateUserDTO;
import com.example.touroperators.dto.UpdateUserDTO;
import com.example.touroperators.models.User;
import com.example.touroperators.repositories.Abstract.BaseRepository;
import com.example.touroperators.repositories.Abstract.SpringRepositories.SpringUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends BaseRepository {

   private final SpringUserRepository springUserRepository;

    public UserRepository(ModelMapper modelMapper, SpringUserRepository springUserRepository) {
        super(modelMapper);
        this.springUserRepository = springUserRepository;
    }

    public User createUser(CreateUserDTO createUserDTO){

        User user = modelMapper.map(createUserDTO, User.class);

        return springUserRepository.save(user);
    }

    public User getUserById(Long id){
        return springUserRepository.getUserById(id);
    }

    public User getUserByUsername(String username){
        return springUserRepository.getUserByUsername(username);
    }

    public User updateUser(User userToUpdate, UpdateUserDTO updateUserDTO){

        modelMapper.map(updateUserDTO, userToUpdate);

        return springUserRepository.save(userToUpdate);
    }

    public User deleteUserById(Long id){
        return springUserRepository.deleteUserById(id);
    }

}
