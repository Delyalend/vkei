package com.vkei.exception;

import lombok.Data;

@Data
public class SubjectIsAlreadyExistsInCollectionException extends Exception {
    private String titleSubject;

    public SubjectIsAlreadyExistsInCollectionException(String titleSubject) {
        this.titleSubject = titleSubject;
    }

}
