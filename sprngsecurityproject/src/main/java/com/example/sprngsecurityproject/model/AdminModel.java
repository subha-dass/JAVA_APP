package com.example.sprngsecurityproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @UpdateTimestamp
    private Date updatedon;
    @CreationTimestamp
    private Date Createdon;
    @OneToOne
    @JoinColumn
    @JsonIgnoreProperties("adminModel")
    private SecuredUser securedUser;
    @OneToMany(mappedBy = "admin")
    @JsonIgnoreProperties({"admin"})
    private List<BookModel> booklist;

}
