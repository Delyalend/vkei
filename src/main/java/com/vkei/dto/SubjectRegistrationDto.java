package com.vkei.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SubjectRegistrationDto {


    @Size(min = 4, max = 100)
    @NotNull
    private final String title;

    @JsonCreator
    public SubjectRegistrationDto(@JsonProperty("title") String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
