package com.example.familyTracking.controller;

import com.example.familyTracking.security.User;
import com.example.familyTracking.security.UserService;
import com.example.familyTracking.security.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.Map;


@Controller
public class RegistrationController{

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @Autowired
    UserService userService;

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        boolean userAlreadyExists = false;
        System.out.println("Registration!!!");
        String redirectUrl;
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);
      //  user.addAuthority(Role.USER);
        user.addAuthority(Role.USER);
        System.out.println(new Date());


        try{
            userService.loadUserByUsername(user.getUsername());
        }
        catch(UsernameNotFoundException ex){
            userAlreadyExists = false;
        }
        if (userAlreadyExists){
            redirectUrl = "/login";
        }
        else {
            redirectUrl = "/login";
            userService.addUser(user);
        }
        return redirectUrl;
    }
}