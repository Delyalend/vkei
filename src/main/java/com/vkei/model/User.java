package com.vkei.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    @Column(name = "id")
    private Long id;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "login", nullable = false)
    private String login;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_friendship",
            joinColumns = @JoinColumn(name = "first_user_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "second_user_id", referencedColumnName = "id", nullable = false))
    @JsonIgnoreProperties({"friends"})
    private List<User> friends;


}
