package com.vkei.service;

import com.vkei.model.UserInfo;
import com.vkei.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    private FriendRepository friendRepository;

    @Autowired
    public FriendServiceImpl(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }


    //TODO:плохо работает
    @Override
    public void addFriendToUser(Long friendId, Long userId) {
        friendRepository.addFriendToUser(friendId, userId);
    }

    @Override
    public List<UserInfo> getFriendsByUserId(Long userId) {
        List<UserInfo> friendsByUserId = friendRepository.findFriendsByUserId(userId);
        return friendsByUserId;
    }
}
