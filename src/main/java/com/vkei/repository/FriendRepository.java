package com.vkei.repository;

import com.vkei.model.UserInfo;

import java.util.List;

public interface FriendRepository {

    void addFriendToUser(Long friendId, Long userId);

    List<UserInfo> findFriendsByUserId(Long userId);
}
