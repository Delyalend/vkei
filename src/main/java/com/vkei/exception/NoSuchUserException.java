package com.vkei.exception;

import lombok.Data;

@Data
public class NoSuchUserException extends RuntimeException {
    private Long userId;

    public NoSuchUserException(Long userId) {
        this.userId = userId;
    }

}
