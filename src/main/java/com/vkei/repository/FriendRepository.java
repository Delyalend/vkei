package com.vkei.repository;

import com.vkei.model.User;

import java.util.List;

public interface FriendRepository {

    void addFriendToUser(Long friendId, Long userId);

    List<User> findFriendsByUserId(Long userId);
}
