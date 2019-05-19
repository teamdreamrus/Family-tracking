package com.example.familyTracking.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.familyTracking.location.Location;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location,Integer>{

}
