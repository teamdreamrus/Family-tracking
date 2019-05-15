package com.example.familyTracking.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.familyTracking.security.User;

public interface UserRepository extends CrudRepository<User, Integer>{

}
