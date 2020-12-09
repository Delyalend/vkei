package com.vkei.exception;

import lombok.Data;

@Data
public class SubjectAlreadyExistsException extends RuntimeException{

    private String titleSubject;

    public SubjectAlreadyExistsException(String titleSubject) {
        this.titleSubject = titleSubject;
    }

}
