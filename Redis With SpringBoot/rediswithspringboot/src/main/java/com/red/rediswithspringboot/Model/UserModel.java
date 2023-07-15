package com.red.rediswithspringboot.Model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    private String location;

}
