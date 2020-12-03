package com.vkei.controller;

import com.vkei.exception.NoSuchUserException;
import com.vkei.model.User;
import com.vkei.model.UserInfo;
import com.vkei.repository.UserRepository;
import com.vkei.service.FriendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
public class FriendController {

    private UserRepository userRepository;
    private FriendService friendService;
    private EntityManager em;

    public FriendController(UserRepository userRepository, FriendService friendService, EntityManager em) {
        this.userRepository = userRepository;
        this.friendService = friendService;
        this.em = em;
    }


    @GetMapping("users/{id}/friends")
    @Transactional
    public List<UserInfo> getFriendsByUserId(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            List<UserInfo> friendsByUserId = friendService.getFriendsByUserId(id);
            friendsByUserId.forEach(friend ->
                    System.out.println(friend));
            return friendService.getFriendsByUserId(id);
        } else {
            throw new NoSuchUserException(id);
        }


    }

    @PostMapping(path = "users/{id}/friends/{friendId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void addFriendToUser(@PathVariable Long id, @PathVariable Long friendId) {

        friendService.addFriendToUser(friendId, id);
//
//        Optional<User> user = userRepository.findById(id);
//        Optional<User> friend = userRepository.findById(friendId);
//
//        if (user.isPresent() && friend.isPresent()) {
//            friendService.addFriendToUser(user.get(),friend.get());
//        }
//        throw new NoSuchUserException(id);

    }


}
