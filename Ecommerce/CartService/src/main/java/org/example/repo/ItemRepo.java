package org.example.repo;

import org.example.model.ItrmDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepo extends JpaRepository<ItrmDetail,Integer > {

    @Query("SELECT p.Id FROM ItrmDetail p where p.username=:username")
    Integer getcartnumber(@Param("username") String username);

    @Query("SELECT u FROM ItrmDetail u where u.username=:username")
    List<ItrmDetail> findUsername(@Param("username") String username);

    @Query("SELECT u FROM ItrmDetail u where u.username=:username")
    ItrmDetail findUser(@Param("username") String username);
}
