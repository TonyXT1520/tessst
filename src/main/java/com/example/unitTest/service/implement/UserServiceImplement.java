package com.example.unitTest.service.implement;

import com.example.unitTest.entity.UserEntity;
import com.example.unitTest.repository.UserRepository;
import com.example.unitTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

//    @Override
//    public UserEntity updateUser(Long id, UserEntity user) {
//        return userRepository.findById(id).map(existingUser -> {
//            existingUser.setName(user.getName());
//            existingUser.setEmail(user.getEmail());
//            existingUser.setPassword(user.getPassword());
//            return userRepository.save(existingUser);
//        }).orElse(null);
//    }


    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
