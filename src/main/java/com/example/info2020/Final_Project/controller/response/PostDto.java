package com.example.info2020.Final_Project.controller.response;

import com.example.info2020.Final_Project.DAO.Post;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDto {

    private Long id;
    private String title;
    private String description;
    private String content;
    private LocalDate creationDate;
    private UserDto author;
    private List<CommentDto> comments;
    private boolean published;

    public PostDto (Post post ){
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.content = post.getContent();
        this.creationDate = post.getCreationDate();
        this.author = new UserDto(post.getAuthor());
        this.comments = post.getComments().stream().map(comment -> new CommentDto(comment)).collect(Collectors.toList());
        this.published = post.isPublished();

    }


}
