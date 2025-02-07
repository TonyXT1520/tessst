package com.example.unitTest.service;

import com.example.unitTest.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {

    UserEntity createUser(UserEntity user);

    //UserEntity updateUser(Long id, UserEntity user);

    void deleteUser(Long id);

    Optional<UserEntity> getUserById(Long id);

    List<UserEntity> getAllUsers();


}
