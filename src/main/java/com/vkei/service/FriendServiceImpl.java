package com.vkei.service;

import com.vkei.dto.FriendDto;
import com.vkei.exception.NoSuchUserException;
import com.vkei.model.User;
import com.vkei.repository.FriendRepository;
import com.vkei.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FriendServiceImpl implements FriendService {

    private FriendRepository friendRepo;
    private UserRepository userRepo;

    @Autowired
    public FriendServiceImpl(FriendRepository friendRepo, UserRepository userRepo) {
        this.friendRepo = friendRepo;
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public void addFriendToUser(Long friendId, Long userId) {
        Optional<User> user = userRepo.findById(userId);
        Optional<User> friend = userRepo.findById(friendId);

        if (user.isPresent()) {
            if (friend.isPresent()) {
                friendRepo.addFriendToUser(friendId, userId);
            } else {
                throw new NoSuchUserException(friendId);
            }
        } else {
            throw new NoSuchUserException(userId);
        }
    }

    @Override
    @Transactional
    public List<FriendDto> getFriendDtos(Long userId, int firstResult, int maxResult) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isPresent()) {
            return friendRepo.findFriendsByUserId(userId, firstResult, maxResult);
        } else {
            throw new NoSuchUserException(userId);
        }
    }
}
