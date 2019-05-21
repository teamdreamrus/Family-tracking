package com.example.familyTracking.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.familyTracking.location.Location;

import java.util.Date;
import java.util.List;

public interface LocationRepository extends CrudRepository<Location,Integer>{

    List<Location> findByUsername(String title);


    @Query(value = "SELECT * FROM location WHERE date = (SELECT MAX(date) FROM location WHERE (username=?1));",
            nativeQuery = true)
    Location LastUsername(String username);

    @Query(value = "SELECT * FROM location WHERE ((date <?1) and (date > ?2) and (username=?3));",
            nativeQuery = true)
    List<Location> LocalDateBeetwin(Date now, Date hourAgo,String username);




}