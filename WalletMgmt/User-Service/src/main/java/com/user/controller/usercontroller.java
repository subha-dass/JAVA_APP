package com.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.user.Userservice;
import com.user.dto.CreateUserReq;
import com.user.service.UserdetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.CacheRequest;

@RestController
public class usercontroller {
    @Autowired
    UserdetailService userdetailService;

    @PostMapping("/useradd")
    public String create(@RequestBody CreateUserReq createUserReq) throws JsonProcessingException {
        String result=userdetailService.create(createUserReq.convertUserCreateRequest());

        return result;
    }
}
