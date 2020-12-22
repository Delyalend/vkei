package com.vkei.exception;

import lombok.Data;

@Data
public class SubjectExistsInOppositeCategoryException extends RuntimeException{
    private String titleSubject;

    public SubjectExistsInOppositeCategoryException(String titleSubject) {
        this.titleSubject = titleSubject;
    }
}
