package com.example.familyTracking.controller;


import com.example.familyTracking.model.UserPublic;
import com.example.familyTracking.security.User;
import com.example.familyTracking.security.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    UserService userService;

    @GetMapping
    public String getAllUsers(){
        String allUsersJson = "";
        List<UserPublic> allUsersList = new LinkedList<>();
        for(User x : userService.getAllUsers()){
            allUsersList.add(new UserPublic(x.getId(),x.getUsername()));
        }
        allUsersJson = gson.toJson(allUsersList);
        return allUsersJson;
    }

}
