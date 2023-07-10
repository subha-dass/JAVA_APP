package com.example.sprngsecurityproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String BookName;
    private String AuthorName;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"booklist"})
    private AdminModel admin;
}
