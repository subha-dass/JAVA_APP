package org.example.dto;

import lombok.*;
import org.example.model.ImageProd;
import org.example.model.ProductModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageProdDTO {
    public String name;


    public ImageProd to(ProductModel productModel){
        return ImageProd.builder().name(this.name).productModel(productModel).build();
    }
}
