package com.vkei.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegistrationDto {

    @Size(min = 5, max = 100)
    @NotNull
    private String mail;

    @Size(min = 6, max = 20)
    @NotNull
    private String password;

    @Size(min = 5, max = 20)
    @NotNull
    private String login;

    @JsonCreator
    public UserRegistrationDto(@JsonProperty("mail") String mail,
                               @JsonProperty("password") String password,
                               @JsonProperty("login") String login) {
        this.login = login;
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }
}
