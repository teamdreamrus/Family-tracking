package com.example.familyTracking.controller;

import com.example.familyTracking.model.UserPublic;
import com.example.familyTracking.security.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.familyTracking.security.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    Gson gson = new GsonBuilder().create();

    @Autowired
    UserService userService;

    @GetMapping
    public String getAccountData(){
        String userDataJson = "";
        User user = User.getCurrentUser();
        System.out.print("Get account data of user " + user.getId());
        UserPublic userPublic = new UserPublic(user.getId(), user.getUsername());
        userDataJson = gson.toJson(userPublic);
        return userDataJson;
    }

    @DeleteMapping
    public String deleteAccount(){
        User user = User.getCurrentUser();
        //response.sendRedirect("/logout");
        System.out.print("Delete account of user " + user.getId());
        //delete user from user database
        userService.deleteUser(user);
        return "";
    }

}
