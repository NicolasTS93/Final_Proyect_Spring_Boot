package com.example.info2020.Final_Project.services;


import com.example.info2020.Final_Project.controller.request.CreateUser;
import com.example.info2020.Final_Project.controller.request.UpdateUser;
import com.example.info2020.Final_Project.controller.response.UserDto;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    String createUser(CreateUser body);

    List<UserDto> getUsers();

    List<UserDto> getUsersCreatedAfterOf(LocalDate localDate);

    List<UserDto> getUsersFilteredBy(LocalDate creationDate, String city);

    List<UserDto> getUsersFromCity(String city);

    String updateUser(Long userId, UpdateUser body);

    String deleteUser(Long userId);
}
