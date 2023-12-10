package org.example.dto;


import lombok.*;
import org.example.model.ItrmDetail;
import org.example.model.ProductCart;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class productCartDto {
    @NotBlank
    private String itermName;

    @NotBlank
    private String itemNo;

    @Max(20)
    private Integer quantity;

    @Min(0)
    private Long price;

    private String imageFileName;



    public ProductCart to(ItrmDetail itrmDetail){
        return ProductCart.builder().ItemName(this.itermName)
                .ItemNo(this.itemNo).quantity(this.quantity).imageFileName(this.imageFileName).price(this.price).itemdetail(itrmDetail)
                .build();
    }
}
