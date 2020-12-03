package com.vkei.service;

import com.vkei.model.UserInfo;

import java.util.List;

public interface FriendService {

    void addFriendToUser(Long friendId, Long userId);

    List<UserInfo> getFriendsByUserId(Long userId);
}
