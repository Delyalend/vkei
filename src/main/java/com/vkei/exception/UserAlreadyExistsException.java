package com.vkei.exception;

import com.vkei.dto.UserRegistrationDto;
import com.vkei.model.User;
import lombok.Data;

@Data
public class UserAlreadyExistsException extends RuntimeException {

    private UserRegistrationDto dto;
    private User user;

    public UserAlreadyExistsException(UserRegistrationDto dto, User user) {
        this.dto = dto;
        this.user = user;
    }

}
