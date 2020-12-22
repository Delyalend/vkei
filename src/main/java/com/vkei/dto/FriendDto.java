package com.vkei.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FriendDto {
    private final Long id;
    private String mail;
    private String login;

    @JsonCreator
    public FriendDto(@JsonProperty("id") Long id,
                     @JsonProperty("mail") String mail,
                     @JsonProperty("login") String login) {
        this.id = id;
        this.login = login;
        this.mail = mail;
    }

    public Long getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getLogin() {
        return login;
    }

}
