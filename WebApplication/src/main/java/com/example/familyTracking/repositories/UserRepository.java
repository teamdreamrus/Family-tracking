package com.example.familyTracking.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.familyTracking.security.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer>{
    List<User> findByUsername(String title);
    
}
