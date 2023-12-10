package org.example.DTO;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
