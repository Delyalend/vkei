package com.vkei.service;

import com.vkei.dto.UserRegistrationDto;
import com.vkei.exception.UserAlreadyExistsException;
import com.vkei.model.User;
import com.vkei.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private UserRepository userRepository;

    @Autowired
    public RegistrationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Long registerUser(UserRegistrationDto dto) {

        Optional<User> user = userRepository.findByRegistrationDto(dto);

        if (user.isPresent()) {
            throw new UserAlreadyExistsException(dto, user.get());
        }

        return userRepository.save(dto.getLogin(), dto.getMail(), dto.getPassword());
    }

}
