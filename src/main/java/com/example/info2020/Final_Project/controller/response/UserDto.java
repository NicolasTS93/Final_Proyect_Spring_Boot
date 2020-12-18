package com.example.info2020.Final_Project.controller.response;

import com.example.info2020.Final_Project.DAO.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;
    private String name;
    private String lastname;
    private String email;
    private LocalDate creationDate;
    private String city;
    private String state;
    private String country;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.creationDate = user.getCreationDate();
        this.city = user.getCity();
        this.state = user.getState();
        this.country = user.getCountry();


    }
}
