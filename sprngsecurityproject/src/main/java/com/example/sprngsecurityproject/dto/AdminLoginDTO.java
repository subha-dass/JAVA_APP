package com.example.sprngsecurityproject.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminLoginDTO {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
