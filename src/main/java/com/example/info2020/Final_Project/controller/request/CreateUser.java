package com.example.info2020.Final_Project.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateUser {

    private String name;
    private String lastname;
    private String email;
    private String password;
    private String city;
    private String state;
    private String country;


}
