package com.example.familyTracking.security;

import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDataAccessObject {
    public Optional<User> findByUsername(@NonNull String username){
        User user = null;
        //find user in database here

        user = new User();
        user.setPassword(new BCryptPasswordEncoder().encode("password"));
        user.setUsername("dmitriy");
        user.addAuthority(Role.USER);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        return Optional.ofNullable(user);
    }

    public Optional<List<User>> getAllUsers(){
        List<User> userList = new LinkedList<User>();
        //get all users from database here
        return Optional.ofNullable(userList);
    }

    public boolean saveUser(User newUser){
        //call database user saving here

        return false;
    }

    public boolean deleteUserByUsername(@NonNull String username){
        //call database user deleting here
        return false;
    }
}