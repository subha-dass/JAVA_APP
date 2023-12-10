package org.example.DAO;

import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Integer> {
    User findByuserName(String Username);
}
