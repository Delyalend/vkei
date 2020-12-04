package com.vkei.controller;

import com.vkei.dto.FriendDto;
import com.vkei.service.FriendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FriendController {

    private FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @GetMapping("users/{id}/friends")
    public List<FriendDto> getFriendsByUserId(@PathVariable Long id) {
        return friendService.getFriendDtos(id);
    }

    @PostMapping(
            path = "users/{id}/friends/{friendId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addFriendToUser(@PathVariable Long id, @PathVariable Long friendId) {
        friendService.addFriendToUser(friendId, id);
    }


}
