package com.user.repo;

import com.user.model.Userdetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Userrepo extends JpaRepository<Userdetails,Integer> {
}
