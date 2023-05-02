package com.microservices.services.impl;

import com.microservices.entities.User;
import com.microservices.repositories.UserRepo;
import com.microservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class UserServiceImpl implements UserService {
   @Autowired
   public UserRepo userRepo;
    @Override
    public User saveUser(User user) {

        return userRepo.save(user);
    }


    @Override
    public User getUserById(Integer userId) {

       User user = this.userRepo.findById(userId).orElseThrow(RuntimeException::new);
        return user;
    }



    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

}
