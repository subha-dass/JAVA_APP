package com.user.dto;

import com.user.model.Userdetails;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserReq {

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    private String email;

    @Min(18)
    private int age;

    public Userdetails convertUserCreateRequest(){
        return Userdetails.builder()
                .name(this.name)
                .email(this.email)
                .phone(this.phone)
                .age(this.age)
                .build();
    }
}
