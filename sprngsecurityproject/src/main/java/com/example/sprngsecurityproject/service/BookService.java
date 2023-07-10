package com.example.sprngsecurityproject.service;

import com.example.sprngsecurityproject.dao.BookDAO;
import com.example.sprngsecurityproject.model.BookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookDAO bookDAO;

    public void addbookservice(BookModel bookModel){
        System.out.println("added books are"+bookModel);
        bookDAO.save(bookModel);
    }
    public List<BookModel> getBookname(String authorName){
        return bookDAO.findByAuthorName(authorName);
    }
}
