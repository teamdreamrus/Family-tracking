package com.example.familyTracking.controller;

import com.example.familyTracking.model.UserPublic;
import com.example.familyTracking.repositories.FriendshipRepository;
import com.example.familyTracking.security.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.familyTracking.security.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    Gson gson = new GsonBuilder().create();

    @Autowired
    private Logger logger;

    @Autowired
    private UserService userService;

    @Autowired
    private FriendshipRepository friendshipRepository;


    @GetMapping
    public String getAccountData(){
        String userDataJson = "";
        User user = User.getCurrentUser();
        logger.debug("User " + user.getUsername() + " getting his account data");
        UserPublic userPublic = new UserPublic(user.getId(), user.getUsername());
        userDataJson = gson.toJson(userPublic);
        return userDataJson;
    }

    @DeleteMapping
    public String deleteAccount(){
        User user = User.getCurrentUser();
        List<Integer> friendships = friendshipRepository.findByIds(user.getId());
        for(Integer friendshipId : friendships){
            friendshipRepository.deleteById(friendshipId);
        }
        userService.deleteUser(user);
        logger.info("User " + user.getUsername() + " has deleted his account");
        return "";
    }

}
