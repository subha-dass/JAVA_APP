package org.example.dto;


import lombok.*;
import org.example.model.ImageProd;
import org.example.model.ProductModel;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseSlNO {
    private ProductModel productModel;
    private List<byte[]> images;
}
