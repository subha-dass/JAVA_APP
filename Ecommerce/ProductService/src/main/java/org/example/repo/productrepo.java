package org.example.repo;

import org.example.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface productrepo extends JpaRepository<ProductModel,Integer> {

    @Query("select p from ProductModel p where p.productNo=:productno")
    Optional<ProductModel> findbyproductname(@Param("productno") String productno);
    @Query("select p from ProductModel p where p.productCatagory=:productcat")
    Optional<List<ProductModel>> findbyproductcat(@Param("productcat") String productcat);

    @Query("select p from ProductModel p where p.productNo=:slno")
    Optional<ProductModel> findbyprodByslno(@Param("slno") String slno);
}
