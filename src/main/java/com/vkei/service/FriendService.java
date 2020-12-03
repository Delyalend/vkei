package com.vkei.service;

import com.vkei.model.User;

import java.util.List;

public interface FriendService {

    void addFriendToUser(Long friendId, Long userId);

    List<User> getFriendsByUserId(Long userId);
}
