package org.example.service;


import org.example.model.ItrmDetail;
import org.example.model.ProductCart;
import org.example.repo.ItemRepo;
import org.example.repo.ProductCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    ItemRepo itemRepo2;

    @Autowired
    ProductCartRepo productCart;
    public ItrmDetail add1(ItrmDetail itrmDetail){
        System.out.println("Saving the details...");
        return itemRepo2.save(itrmDetail);
    }
    public ItrmDetail add(ItrmDetail itrmDetail){
        System.out.println("id of check"+itrmDetail.getId());
        System.out.println("before fetching the id....");
        Optional<ItrmDetail> existingItrmDetailOptional = itemRepo2.findById(itrmDetail.getId());
        System.out.println("After fetching the id ..");
        if (existingItrmDetailOptional.isPresent())
        {
            ItrmDetail existingItrmDetail = existingItrmDetailOptional.get();
            List<ProductCart> existingProductCarts = new ArrayList<>();
            for(ProductCart productCart1:itrmDetail.getProductlist())
            {
                        Optional<ProductCart> existingProduct = productCart.findByItemNo(productCart1.getItemNo());
                        if(existingProduct.isPresent()){
                            ProductCart existingProductCart = existingProduct.get();
                            System.out.println("product quantity"+productCart1.getQuantity()+" "+existingProductCart.getQuantity());
                            existingProductCart.setQuantity(Integer.valueOf(existingProductCart.getQuantity())+Integer.valueOf(productCart1.getQuantity()));
    //                        existingProductCart.setItemdetail(existingItrmDetail);
                            existingProductCarts.add(existingProductCart);

                        }
                System.out.println("Before Saving....");
            }
            existingItrmDetail.setProductlist(existingProductCarts);
            return itemRepo2.save(existingItrmDetail);
        }
        System.out.println("Before not  Saving.");
        return itemRepo2.save(itrmDetail);
    }
    public List<ItrmDetail> getAllCartdata(String username){
        return itemRepo2.findUsername(username);
    }
    public Integer getCartCount(Integer username){
        return  productCart.getcartnumber(username);
    }
    public Integer getCartUsername(String username){
        return  itemRepo2.getcartnumber(username);
    }
    public ItrmDetail getUserData(String username){
        return itemRepo2.findUser(username);
    }
    public String deletImem(String slno){
        productCart.deleteByslno(slno);
        return "deleted.........";
    }

    public Integer getProddetail(Integer item_id){
        System.out.println("Product cart value"+productCart.getPdetails(item_id));
        return productCart.getPdetails(item_id);

    }
}
