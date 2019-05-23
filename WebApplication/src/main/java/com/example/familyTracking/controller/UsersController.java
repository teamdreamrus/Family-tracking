package com.example.familyTracking.controller;


import com.example.familyTracking.model.UserPublic;
import com.example.familyTracking.security.User;
import com.example.familyTracking.security.UserDataAccessObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    private Logger logger;

    @Autowired
    UserDataAccessObject userDataAccessObject;

    @GetMapping
    public String getAllUsers(){
        String allUsersJson = "";
        List<UserPublic> allUsersList = new LinkedList<>();
        for(User x : userDataAccessObject.getAllUsers()){
            allUsersList.add(new UserPublic(x.getId(),x.getUsername()));
        }
        allUsersJson = gson.toJson(allUsersList);

        User user = User.getCurrentUser();
        if (user != null) {
            logger.debug("Sending list of all users in JSON format to user " + user.getUsername());
        }
        return allUsersJson;
    }

}
