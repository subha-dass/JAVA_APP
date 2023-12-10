package org.example.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRespDTO {
    private Integer id;
    private String username;
    private String email;
}
