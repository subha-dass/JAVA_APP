package org.example.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageProd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer Id;
    @Column(nullable = false)
    public String name;
    @ManyToOne
    @JoinColumn(name="product_model_id")
    private ProductModel productModel;
}
