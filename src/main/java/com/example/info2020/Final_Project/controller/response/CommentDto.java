package com.example.info2020.Final_Project.controller.response;

import com.example.info2020.Final_Project.DAO.Comment;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDto {

    private Long id;
    private UserDto author;
    private LocalDate creationDate;
    private String comment;

    public CommentDto(Comment comment) {
        this.id = comment.getId();;
        this.author = new UserDto(comment.getAuthor());
        this.creationDate = comment.getCreationDate();
        this.comment = comment.getComment();

    }
}
