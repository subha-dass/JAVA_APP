package com.red.rediswithspringboot.service;

import com.red.rediswithspringboot.Model.UserModel;
import com.red.rediswithspringboot.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public void create(UserModel userModel){
        userRepo.set(userModel);
    }
}
