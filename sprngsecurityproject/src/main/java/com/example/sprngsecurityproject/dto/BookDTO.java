package com.example.sprngsecurityproject.dto;

import com.example.sprngsecurityproject.model.AdminModel;
import com.example.sprngsecurityproject.model.BookModel;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {

    private String BookName;
    private String AuthorName;


    public BookModel to(AdminModel adminModel){

        return BookModel.builder() .BookName(this.BookName)
                .AuthorName(this.AuthorName)
                .admin(adminModel)
                .build();
    }

}
