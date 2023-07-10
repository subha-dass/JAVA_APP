package com.example.sprngsecurityproject.controller;

import com.example.sprngsecurityproject.dto.BookDTO;
import com.example.sprngsecurityproject.dto.adminDTO;
import com.example.sprngsecurityproject.model.AdminModel;
import com.example.sprngsecurityproject.model.BookModel;
import com.example.sprngsecurityproject.model.SecuredUser;
import com.example.sprngsecurityproject.service.AdminService;
import com.example.sprngsecurityproject.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    BookService bookService;

    @PostMapping("/createadmin")
    public String createAdmin(@RequestBody adminDTO adminDTO1){
        adminService.create(adminDTO1.to());
        return "Admin Created Succssfully...";
    }
//    @PostMapping("/book/add")
//    public String addbook(@RequestBody )
    @PostMapping("/Admin/book")
    public String addBook(@RequestBody BookDTO bookDTO){
        System.out.println("hiiiii"+bookDTO.getAuthorName());
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        SecuredUser user=(SecuredUser)authentication.getPrincipal();
        System.out.println("Admin is"+user);
        int adminId=user.getAdminModel().getId();
        AdminModel admin=adminService.find(adminId);
        bookService.addbookservice(bookDTO.to(admin));
        return "Book added Successfully..";
    }
    @GetMapping("/Admin/getbook/{authorName}")
    public List<BookDTO> getBoook(@PathVariable("authorName") String authorName )throws Exception{
        System.out.println("authorName is"+authorName);
        List<BookModel> bookdetails= bookService.getBookname(authorName);
        ModelMapper modelMapper=new ModelMapper();
        List<BookDTO> bookDTOS=bookdetails.stream().map(book->modelMapper.map(book, BookDTO.class)).
                collect(Collectors.toList());
        return bookDTOS;
    }
}
