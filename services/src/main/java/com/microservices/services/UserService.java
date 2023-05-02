package com.microservices.services;

import com.microservices.entities.User;

import java.util.List;


public interface UserService {
    public User saveUser(User user);
    public User getUserById(Integer userId);

    public List<User> getAllUsers();
}
