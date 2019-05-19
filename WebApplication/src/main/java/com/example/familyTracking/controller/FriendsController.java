package com.example.familyTracking.controller;

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

    @Data
    class UserPublic{
        public UserPublic(Integer id, String username){
            this.username = username;
            this.id = id;
        }
        @SerializedName("id")
        public Integer id;
        @SerializedName("username")
        public String username;
    }

    @Data
    class Friend extends UserPublic{
        public Friend(Integer id, String username, boolean accepted){
            super(id, username);
            this.accepted = accepted;
        }
        @SerializedName("accepted")
        public boolean accepted;
    }

    @Data
    class Friendship{
        public Friendship(Integer id1, Integer id2, Integer initiatorId, boolean accepted){
            this.id1 = id1;
            this.id2 = id2;
            this.initiatorId = initiatorId;
            this.accepted = accepted;
        }
        @SerializedName("id1")
        public Integer id1;
        @SerializedName("id2")
        public Integer id2;
        @SerializedName("initiatorId")
        public Integer initiatorId;
        @SerializedName("accepted")
        public boolean accepted;
        @SerializedName("rejected")
        public boolean rejected;
    }

    Gson gson = new GsonBuilder().create();

    @GetMapping("{id}")
    public String getFriendsForId(@PathVariable Integer id){
        String friendsJson = "";
        List<Friend> userFriendsList = new LinkedList<>();
        System.out.println("Sending friends:" + friendsJson);
        // find friends for user with id
        // put it to userFriendsList

        userFriendsList.add(new Friend(0,"JohlGalt", true));
        userFriendsList.add(new Friend(1,"SamGrakham", true));
        userFriendsList.add(new Friend(2,"SomeoneElse", false));
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
        return id;
    }

    @DeleteMapping("{id}")
    public Integer deleteFriendship(@PathVariable Integer id){
        User user = getCurrentUser();
        //delete friendship from db for users with id and user.getId()
        return id;
    }

    @PutMapping("{id}")
    public Integer acceptFriendship(@PathVariable Integer id){
        User user = getCurrentUser();
        //accept friendship in db for users with id and user.getId()
        return id;
    }
}
