package com.red.rediswithspringboot.DTO;

import com.red.rediswithspringboot.Model.UserModel;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    @NotBlank
    private String name;
    private String email;
    private String location;

    public UserModel to(){
        return UserModel.builder()
                .name(this.name)
                .email(this.email)
                .location(this.location)
                .build();
    }
}
