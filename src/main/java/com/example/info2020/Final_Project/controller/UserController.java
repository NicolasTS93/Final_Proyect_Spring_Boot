package com.example.info2020.Final_Project.controller;

import com.example.info2020.Final_Project.controller.request.CreateUser;
import com.example.info2020.Final_Project.controller.request.UpdateUser;
import com.example.info2020.Final_Project.controller.response.UserDto;
import com.example.info2020.Final_Project.services.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDto>> getUsers(
            @RequestParam(required = false, value = "createdAfter")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate localDate,
            @RequestParam(required = false, value = "fromCity") String city) {
        if (localDate != null && StringUtils.hasText(city)) {
            return ResponseEntity.ok(userService.getUsersFilteredBy(localDate, city));
        }
        if (localDate != null) {
            return ResponseEntity.ok(userService.getUsersCreatedAfterOf(localDate));
        }
        if (StringUtils.hasText(city)) {
            return ResponseEntity.ok(userService.getUsersFromCity(city));
        }

        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping(value = "/users")
    public ResponseEntity<String> createUser(@RequestBody CreateUser body) {
        return ResponseEntity.ok(userService.createUser(body));
    }

    @PutMapping(value = "/users/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable(value = "userId") Long userId, @RequestBody UpdateUser body) {
        return ResponseEntity.ok(userService.updateUser(userId, body));
    }

    @DeleteMapping(value = "/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "userId") Long userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));

    }

}
