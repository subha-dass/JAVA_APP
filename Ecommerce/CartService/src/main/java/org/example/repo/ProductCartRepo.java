package org.example.repo;

import org.example.model.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ProductCartRepo extends JpaRepository<ProductCart,Integer> {

    @Query("SELECT pc FROM ProductCart pc WHERE pc.ItemNo = :item_no")
    Optional<ProductCart> findByItemNo(@Param("item_no") String item_no);


    @Query("SELECT COUNT(p) FROM ProductCart p where p.itemdetail.id=:item_id")
    Integer getcartnumber(@Param("item_id") Integer item_id);

    @Query("Select pc.Id FROM ProductCart pc WHERE pc.itemdetail.id=:item_id")
    Integer getPdetails(@Param("item_id") Integer item_id);

    @Modifying
    @Transactional
    @Query("DELETE FROM ProductCart p where p.ItemNo=:slno")
    void deleteByslno(@Param("slno") String slno);
}
