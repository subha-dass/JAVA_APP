package com.example.sprngsecurityproject.dao;

import com.example.sprngsecurityproject.model.SecuredUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecuredUserDAO extends JpaRepository<SecuredUser,Integer> {
    SecuredUser findByusername(String username);
}
