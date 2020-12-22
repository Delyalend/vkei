package com.vkei.controller;

import com.vkei.dto.UserRegistrationDto;
import com.vkei.model.User;
import com.vkei.service.RegistrationService;
import com.vkei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private UserService UserService;

    private RegistrationService registrationService;

    @Autowired
    public UserController(UserService UserService, RegistrationService registrationService) {
        this.UserService = UserService;
        this.registrationService = registrationService;
    }


    @PostMapping(path = "users/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@Valid @RequestBody UserRegistrationDto dto) {
        registrationService.registerUser(dto);
    }


    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return UserService.findAll();
    }



}
