package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer Id;
    @Column(nullable = false)
    public String productName;
    @Column(nullable = false)
    public String productNo;
    @Column(nullable = false)
    public double price;
    public double discount;
    public String productCatagory;
    @Column(nullable = false)
    public Integer productserialnum;
    public Integer stockavl;
    @Column(nullable = false)
    public Integer total_stock;
    @OneToMany(mappedBy = "productModel",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("productModel")
    private List<ImageProd> image;
}
