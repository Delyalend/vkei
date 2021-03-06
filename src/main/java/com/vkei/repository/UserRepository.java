package com.vkei.repository;

import com.vkei.dto.UserRegistrationDto;
import com.vkei.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();

    Optional<User> findByLogin(String login);

    Optional<User> findByRegistrationDto(UserRegistrationDto dto);

    Optional<User> findById(Long id);

    //Long save(String login, String mail, String password);
    Long save(User user);

}
