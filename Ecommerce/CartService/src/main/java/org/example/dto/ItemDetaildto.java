package org.example.dto;

import lombok.*;
import org.example.model.ItrmDetail;
import org.example.model.ProductCart;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDetaildto {


    private List<productCartDto> productdetailadd;


    public ItrmDetail to(String name,int id){

        ItrmDetail itrmDetail=ItrmDetail.builder().uId(id)
                .username(name).build();
        List<ProductCart> p=new ArrayList<>();
        for(productCartDto product:this.productdetailadd){
            ProductCart productto=product.to(itrmDetail);
            p.add(productto);
        }

        itrmDetail.setProductlist(p);
        return itrmDetail;
    }
    public ItrmDetail to1(String name,int idm,ItrmDetail itrmDetail){

//        ItrmDetail itrmDetail=ItrmDetail.builder().uId(id)
//                .username(name).build();
        List<ProductCart> p=new ArrayList<>();
        for(productCartDto product:this.productdetailadd){
            ProductCart productto=product.to(itrmDetail);
            p.add(productto);
        }

        itrmDetail.setProductlist(p);
        return itrmDetail;
    }
}
