package com.example.familyTracking.controller;


import com.example.familyTracking.security.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    Gson gson = new GsonBuilder().create();

    @GetMapping
    public String getAllUsers(){
        String allUsersJson = "";
        List<FriendsController.UserPublic> allUsersList = new LinkedList<>();
        //get all users from DB to allUsersList
        allUsersJson = gson.toJson(allUsersList);
        return allUsersJson;
    }
}
