package com.vkei.controller;

import com.vkei.dto.UserRegistrationDto;
import com.vkei.exception.UserAlreadyExistsException;
import com.vkei.model.User;
import com.vkei.repository.UserRepository;
import com.vkei.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class RegistrationController {


    private RegistrationService registrationService;

    private UserRepository userRepository;

    @Autowired
    public RegistrationController(RegistrationService registrationService, UserRepository userRepository) {
        this.registrationService = registrationService;
        this.userRepository = userRepository;
    }

    @PostMapping(path = "users/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@Valid @RequestBody UserRegistrationDto dto) {


        Optional<User> user = userRepository.findByRegistrationDto(dto);

        if (user.isPresent()) {
            throw new UserAlreadyExistsException(dto, user.get());
        }


        registrationService.registerUser(
                dto.getLogin(),
                dto.getMail(),
                dto.getPassword());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
