package com.example.sprngsecurityproject.dao;

import com.example.sprngsecurityproject.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDAO extends JpaRepository<BookModel,Integer> {

    @Query("select e from BookModel e where e.AuthorName=?1")
    List<BookModel> findByAuthorName(String AuthorName);
}

