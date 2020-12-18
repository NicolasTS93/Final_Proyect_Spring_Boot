package com.example.info2020.Final_Project.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatePost {

    private String title;
    private String description;
    private String content;
    private Long authorId;
    private boolean published;

}
