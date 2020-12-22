package com.vkei.service;

import com.vkei.dto.FriendDto;

import java.util.List;

public interface FriendService {

    void addFriendToUser(Long friendId, Long userId);

    List<FriendDto> getFriendDtos(Long userId, int firstResult, int maxResult);
}
