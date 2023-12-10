package org.example.LoginConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class passwordencoder {
    @Bean
    PasswordEncoder getPE(){
        return new BCryptPasswordEncoder();
    }
}
