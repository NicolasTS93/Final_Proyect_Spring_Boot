package com.example.info2020.Final_Project.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdatePost {

    private String title;
    private String description;
    private String content;
    private boolean published;
}
