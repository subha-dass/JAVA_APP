package com.example.sprngsecurityproject.dto;

import com.example.sprngsecurityproject.model.SecuredUser;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SecuredUserDTO {
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String authorities;

    public SecuredUser to(){
        System.out.println("inside Secureduser");
        return SecuredUser.builder().username(this.username)
                .password(this.password)
                .authorities("ADMIN::ADMIN1")
                .build();
    }
}
