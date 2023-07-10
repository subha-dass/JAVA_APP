package com.example.sprngsecurityproject.dao;

import com.example.sprngsecurityproject.model.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDAO extends JpaRepository<AdminModel,Integer> {
}
