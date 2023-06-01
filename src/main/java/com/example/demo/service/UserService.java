package com.example.demo.service;

import com.example.demo.entity.UserEntity;

public interface UserService {
    UserEntity findByUsername(String username);
    UserEntity findById(String id);
    public UserEntity updateUser(UserEntity userEntity);
}
