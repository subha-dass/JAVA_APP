package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ItrmDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer Id;

    @CreationTimestamp
    private Date addDate;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Integer uId;

    @OneToMany(mappedBy = "itemdetail",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("itemdetail")
    private List<ProductCart> productlist;


}
