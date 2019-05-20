package com.example.familyTracking.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.familyTracking.location.Location;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location,Integer>{

    List<Location> findByUsername(String title);


    @Query(value = "SELECT * FROM location WHERE date = (SELECT MAX(date) FROM location) and username=?1;",
            nativeQuery = true)
    Location FindLastByUsername(String username);
}