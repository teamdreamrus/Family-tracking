package com.example.familyTracking.security;

import org.slf4j.Logger;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.familyTracking.security.User;
import com.example.familyTracking.repositories.UserRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDataAccessObject {

    @Autowired
    Logger logger;

    @Autowired
    public UserRepository userRepository;
    public Optional<User> findByUsername(@NonNull String username){
        User user = null;
        if (userRepository.findByUsername(username).size()>0) {
            user = userRepository.findByUsername(username).get(0);
            user.parseAuthority();
        }
        //find user in database here

        return Optional.ofNullable(user);
    }

    public List<User> getAllUsers(){
        List<User> userList = new LinkedList<User>();
        //List<User> userList = userRepository.findAll();
       userRepository.findAll().forEach(userList::add);

        //get all users from database here
        return userList;
    }

    public boolean saveUser(User newUser){
        //call database user saving here
        userRepository.save(newUser);
        return true;
    }

    public boolean deleteUserByUsername(@NonNull String username){
        //call database user deleting here
      //  userRepository.delete(User username);
        User user = userRepository.findByUsername(username).get(0);
        Integer id = user.getId();
        userRepository.deleteById(id);
        return true;
    }

    public boolean deleteUserById(@NonNull Integer id){
        userRepository.deleteById(id);
        return true;
    }

}