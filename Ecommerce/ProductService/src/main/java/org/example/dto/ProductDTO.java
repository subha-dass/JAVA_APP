package org.example.dto;

import lombok.*;
import org.example.model.ImageProd;
import org.example.model.ProductModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    @NotBlank
    public String productno;
    @NotNull
    public double price;
    @NotBlank
    public String productName;
    public double discount;
    public String productcatagory;
    @NotNull
    public Integer productserialnum;
    @NotNull
    public Integer totalstock;
    public List<ImageProdDTO> image;

    public ProductModel to(){
        ProductModel productModel=ProductModel.builder().productNo(this.productno)
                .productCatagory(this.productcatagory)
                .productName(this.productName)
                .productserialnum(this.productserialnum)
                .price(this.price).total_stock(this.totalstock).discount(this.discount)
                .build();
        List<ImageProd> p=new ArrayList<>();
        for (ImageProdDTO imageProd:image){
            ImageProd imageProd1=imageProd.to(productModel);
            p.add(imageProd1);

        }
        productModel.setImage(p);
        return productModel;
    }
}
