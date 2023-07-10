package com.example.sprngsecurityproject.service;

import com.example.sprngsecurityproject.dao.AdminDAO;
import com.example.sprngsecurityproject.dao.SecuredUserDAO;
import com.example.sprngsecurityproject.model.AdminModel;
import com.example.sprngsecurityproject.model.SecuredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class AdminService {
    @Autowired
    AdminDAO adminDAO;

    @Autowired
    SecuredUserDAO securedUserDAO;

    @Autowired
    PasswordEncoder passwordEncoder;
    public void create(AdminModel adminModel){
        SecuredUser securedUser=adminModel.getSecuredUser();
        System.out.println(" secured user"+securedUser+" "+adminModel);
        securedUser.setPassword(passwordEncoder.encode(securedUser.getPassword()));
        System.out.println("before saving secured user"+securedUser);
        securedUser=securedUserDAO.save(securedUser);
        adminModel.setSecuredUser(securedUser);
        System.out.println("After Secured user"+adminModel);
        adminDAO.save(adminModel);

    }
    public AdminModel find(int adminId){
        AdminModel admin=adminDAO.findById(adminId).orElse(null);
        System.out.println("admin details are"+admin);
        return admin;
    }
}
