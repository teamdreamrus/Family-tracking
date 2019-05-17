package com.example.familyTracking.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendsController {
    @Data
    class Friend{
        public Friend(String username, String id){
            this.username = username;
            this.id = id;
        }
        @SerializedName("username")
        public String username;
        @SerializedName("id")
        public String id;
    }

    public List<Friend> userFriends = new LinkedList<>();
    {
        userFriends.add(new Friend("JohlGalt", "id1"));
        userFriends.add(new Friend("SamGrakham", "id2"));
        userFriends.add(new Friend("SomeoneElse", "id3"));
    }

    Gson gson = new GsonBuilder().create();

    @GetMapping("{id}")
    public String getFriends(@PathVariable String id){
        String friendsJson = gson.toJson(userFriends);
        System.out.println("Sending friends:" + friendsJson);
        return friendsJson;
    }
}
