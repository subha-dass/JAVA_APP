package com.example.sprngsecurityproject.dto;

import com.example.sprngsecurityproject.model.AdminModel;
import com.example.sprngsecurityproject.model.SecuredUser;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class adminDTO {
    @Autowired
    SecuredUserDTO securedUserDTO;

    private String name;
    private SecuredUserDTO securedUser;

    public AdminModel to(){
        securedUserDTO=securedUser;
        return AdminModel.builder().name(this.name)
                .securedUser(securedUserDTO.to())
                .build();
    }
}
