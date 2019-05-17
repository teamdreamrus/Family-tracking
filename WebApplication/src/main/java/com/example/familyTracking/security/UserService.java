package com.example.familyTracking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserDataAccessObject userDao;

    public static UserService instance;

    @PostConstruct
    public void init(){
        instance = this;
    }

    public void addUser(User newUser){
        newUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
        userDao.saveUser(newUser);

    }

    List<Role> simpleRoleList = new LinkedList<Role>();
    {
        simpleRoleList.add(Role.USER);
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull  String username) throws UsernameNotFoundException {
        System.out.println("Find user with username: " + username);
        return userDao.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User "+username+" wasn't found"));
    }
}
