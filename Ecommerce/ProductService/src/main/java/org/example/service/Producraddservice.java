package org.example.service;

import org.example.model.ProductModel;
import org.example.repo.productrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class Producraddservice {
    @Autowired
    productrepo productrepo;

    public ProductModel productaddorupdate(ProductModel productModel){
        Optional<ProductModel> pro= productrepo.findbyproductname(productModel.getProductNo());
        if(pro.isPresent()){
            ProductModel productModel1=pro.get();
            int totalstock=productModel1.getStockavl()+ productModel.total_stock;
            productModel1.setStockavl(totalstock);
            productModel1.setTotal_stock(totalstock);
            ProductModel prod= productrepo.save(productModel1);
            System.out.println("available stock"+productModel1.stockavl+" "+"total stock"+productModel1.total_stock);
            return prod;
        }else {
            productModel.setStockavl(productModel.getTotal_stock());
            ProductModel prod=productrepo.save(productModel);
            System.out.println("save product is"+prod);
            return prod;
        }
    }
    public List<ProductModel> productfetchcat(String productcat){
        Optional<List<ProductModel>> pr= productrepo.findbyproductcat(productcat);
        List<ProductModel> pr1=pr.get();
        return pr1;
    }
    public ProductModel getProduct(String slno){
        Optional<ProductModel> p= productrepo.findbyprodByslno(slno);
        ProductModel entity = p.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        return entity;
    }
}
