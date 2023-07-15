package com.red.rediswithspringboot.controller;

import com.red.rediswithspringboot.DTO.UserDTO;
import com.red.rediswithspringboot.Model.UserModel;
import com.red.rediswithspringboot.repo.UserRepo;
import com.red.rediswithspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class Usercontroller {
    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @PostMapping("/user")
    public String createuser(@RequestBody UserDTO userDTO){
        userService.create(userDTO.to());
        return "User Created";
    }
    @GetMapping("/get")
    public List<UserModel> get(){
        Set<String> keys = userRepo.getAllkeys();
        return keys.stream()
                .map(k -> userRepo.getByKey(k))
                .collect(Collectors.toList());
    }
    @DeleteMapping("delUser/{id}")
    public String delUser(@PathVariable int id){
        userRepo.deleteKeys(id);
        return "user deleted";
    }
}
