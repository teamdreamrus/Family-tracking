package com.example.familyTracking.controller;

import com.example.familyTracking.model.Friend;
import com.example.familyTracking.model.Friendship;
import com.example.familyTracking.model.UserPublic;
import com.example.familyTracking.repositories.FriendshipRepository;
import com.example.familyTracking.repositories.UserRepository;
import com.example.familyTracking.security.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public UserRepository userRepository;
    @Autowired
    public FriendshipRepository friendshipRepository;

    @GetMapping("{id}")
    public String getFriendsForId(@PathVariable Integer id,  @RequestParam("select") String select){
        String response = "";
        switch(select){
            case "all":
                response = getAllFriends(id);
                break;
            case "accepted":
                response = getAcceptedFriends(id);
                break;
        }
        return response;
    }

    private String getAllFriends(Integer id){
        String friendsJson = "";
        List<Friend> userFriendsList = new LinkedList<>();
        List<Integer> idsFriendship;

        System.out.println("Sending friends:" + friendsJson);
        idsFriendship = friendshipRepository.findByIds(id);
        for(Integer a :idsFriendship) {
            Friendship friendship = friendshipRepository.findById(a).orElse(new Friendship());
            if (friendship.initiatorId != User.getCurrentUser().getId() || friendship.accepted){
                User user;
                if (friendship.id1 == id)
                    user = userRepository.findById(friendship.id2).orElse(new User());
                else user = userRepository.findById(friendship.id1).orElse(new User());
                userFriendsList.add(new Friend(user.getId(), user.getUsername(), friendship.accepted));
            }
        }

        friendsJson = gson.toJson(userFriendsList);
        return friendsJson;
    }

    private String getAcceptedFriends(Integer id){
        String friendsJson = "";
        List<Friend> userFriendsList = new LinkedList<>();
        List<Integer> idsFriendship;

        System.out.println("Sending friends:" + friendsJson);
        idsFriendship = friendshipRepository.findByIds(id);
        for(Integer a :idsFriendship) {
            Friendship friendship = friendshipRepository.findById(a).orElse(new Friendship());
            if (friendship.accepted){
                User user;
                if (friendship.id1 == id)
                    user = userRepository.findById(friendship.id2).orElse(new User());
                else user = userRepository.findById(friendship.id1).orElse(new User());
                userFriendsList.add(new Friend(user.getId(), user.getUsername(), friendship.accepted));
            }
        }

        friendsJson = gson.toJson(userFriendsList);
        return friendsJson;
    }


    @GetMapping
    public String getFriendsForCurrentUser(@RequestParam("select") String select){
        String friendsJson = "";
        User user = User.getCurrentUser();
        if (user != null) {
            friendsJson = getFriendsForId(user.getId(), select);
        }
        return friendsJson;
    }

    @PostMapping("{id}")
    public Integer addNewFriendshipRequest(@PathVariable Integer id){
        User user = User.getCurrentUser();
        Friendship friendship = new Friendship(user.getId(), id, user.getId(), false);

        System.out.println("New frienship request from user " + user.getId() + " to user " + id);
        if(user.getId()!=id)
            if(friendshipRepository.getIDbyIdId(user.getId(),id).size()==0)
                friendshipRepository.save(friendship);
        return id;
    }

    @DeleteMapping("{id}")
    public Integer deleteFriendship(@PathVariable Integer id){
        User user = User.getCurrentUser();
        Integer idFriendship;

        if(friendshipRepository.getIDbyIdId(user.getId(),id).size()>0){
            idFriendship = friendshipRepository.getIDbyIdId(user.getId(),id).get(0);
            friendshipRepository.deleteById(idFriendship);
        }
        System.out.println("Deleting frienship of users " + user.getId() + " and " + id);
        return id;
    }

    @PutMapping("{id}")
    public Integer acceptFriendship(@PathVariable Integer id){
        User user = User.getCurrentUser();
        Integer idFriendship;
        if(friendshipRepository.getIDbyIdId(user.getId(),id).size()>0){
            idFriendship = friendshipRepository.getIDbyIdId(user.getId(),id).get(0);
            System.out.println("Friendship id " + idFriendship);
            Friendship friendship = friendshipRepository.findById(idFriendship).orElse(new Friendship());
            friendship.setAccepted(true);
            System.out.print("Friednship: ");
            System.out.println(friendship);
            friendshipRepository.save(friendship);
        }
        System.out.println("Accepting frienship request from user " + user.getId() + " to user " + id);
        return id;
    }
}
