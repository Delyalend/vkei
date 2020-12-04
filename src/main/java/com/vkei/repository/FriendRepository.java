package com.vkei.repository;

import com.vkei.dto.FriendDto;

import java.util.List;

public interface FriendRepository {

    void addFriendToUser(Long friendId, Long userId);

    List<FriendDto> findFriendsByUserId(Long userId);
}
