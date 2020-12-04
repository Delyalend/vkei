package com.vkei.service;

import com.vkei.model.User;
import com.vkei.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return userRepo.findAll();
    }
}
