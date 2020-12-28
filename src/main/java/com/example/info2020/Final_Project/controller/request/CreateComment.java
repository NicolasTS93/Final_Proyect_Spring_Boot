package com.example.info2020.Final_Project.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateComment {

    private Long authorID;
    private Long postId;
    private String comment;

}
