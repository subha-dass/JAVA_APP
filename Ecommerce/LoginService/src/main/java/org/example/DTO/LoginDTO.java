package org.example.DTO;

import lombok.*;
import org.example.LoginConfig.passwordencoder;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTO {

    @NotBlank
    private String username;
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    private String email;
    public User to(){
        return User.builder().
                userName(this.username)
                .name(this.name)
                .password(this.password)
                .authorities("LOGIN:NORMAL")
                .email(this.email).build();
    }
}
