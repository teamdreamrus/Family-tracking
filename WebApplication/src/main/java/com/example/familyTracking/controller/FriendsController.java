package com.example.familyTracking.controller;

import com.example.familyTracking.model.Friend;
import com.example.familyTracking.model.Friendship;
import com.example.familyTracking.model.UserPublic;
import com.example.familyTracking.security.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendsController {

    Gson gson = new GsonBuilder().create();

    @GetMapping("{id}")
    public String getFriendsForId(@PathVariable Integer id){
        String friendsJson = "";
        List<Friend> userFriendsList = new LinkedList<>();
        System.out.println("Sending friends:" + friendsJson);
        // find friends for user with id
        // put it to userFriendsList
        friendsJson = gson.toJson(userFriendsList);
        return friendsJson;
    }

    private User getCurrentUser(){
        User user = null;
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            user = (User) auth.getPrincipal();
        }
        catch(ClassCastException ex){

            System.out.println("Trying to get user credentials for non authorized user");
        }
        return user;
    }

    @GetMapping
    public String getFriendsForCurrentUser(){
        String friendsJson = "";
        User user = getCurrentUser();
        if (user != null) {
            friendsJson = getFriendsForId(user.getId());
        }
        return friendsJson;
    }

    @PostMapping("{id}")
    public Integer addNewFriendshipRequest(@PathVariable Integer id){
        User user = getCurrentUser();
        Friendship friendship = new Friendship(user.getId(), id, user.getId(), false);
        //add new friendship to db if not exists
        System.out.println("New frienship request from user " + user.getId() + " to user " + id);
        return id;
    }

    @DeleteMapping("{id}")
    public Integer deleteFriendship(@PathVariable Integer id){
        User user = getCurrentUser();
        //delete friendship from db for users with id and user.getId()
        System.out.println("Deleting frienship of users " + user.getId() + " and " + id);
        return id;
    }

    @PutMapping("{id}")
    public Integer acceptFriendship(@PathVariable Integer id){
        User user = getCurrentUser();
        //accept friendship in db for users with id and user.getId()
        System.out.println("Accepting frienship request from user " + user.getId() + " to user " + id);
        return id;
    }
}
