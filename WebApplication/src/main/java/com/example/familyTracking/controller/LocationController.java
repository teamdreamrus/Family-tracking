package com.example.familyTracking.controller;

import com.example.familyTracking.security.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.slf4j.Logger;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

import com.example.familyTracking.repositories.LocationRepository;
import com.example.familyTracking.location.Location;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/location")
public class LocationController{

    @Autowired
    Logger logger;

    @Autowired
    public LocationRepository locationRepository;

    Gson gson = new GsonBuilder().create();

    @Data
    private class UserLocation{
        public UserLocation(double latitude, double longitude){
            this.latitude = latitude;
            this.longitude = longitude;
        }
        public UserLocation(){
        }
        @SerializedName("username")
        public String username;
        @SerializedName("latitude")
        public double latitude;
        @SerializedName("longitude")
        public double longitude;
    }

    @PostMapping
    public String newLocation(@RequestBody String newLocationJson){
        UserLocation userLocation = gson.fromJson(newLocationJson, UserLocation.class);
        logger.debug("User " + userLocation.username + " send new location: ( "
                + userLocation.latitude + " ; " + userLocation.longitude + " )");
        Location locUser = new Location();
        locUser.setUsername(userLocation.username);
        locUser.setLatitude(userLocation.latitude);
        locUser.setLongitude(userLocation.longitude);
        locationRepository.save(locUser);
        return newLocationJson;
    }


    @DeleteMapping()
    public String deleteAllLocations(){
        User user = User.getCurrentUser();
        logger.info("User " + user.getUsername() + " has deleted all his locations");
        //delete all locations for user.getUsername();
        //locationRepository.findAllById();
        return "";
    }


    @GetMapping("{id}")
    public String getFriendLast(@PathVariable String id, @RequestParam("period") String period){
        //getting last friend's location
        List<UserLocation> userLocations = new LinkedList<>();
        User user = User.getCurrentUser();
        logger.debug("User " + user.getUsername() + " getting locations of user with id " + id + " for period " + period);
        switch(period) {
            case "one":
                //get last locations
                break;
            case "hour":
                //get last locations for hour
                break;
            case "day":
                //get last locations for day
                break;
        }


        String userLocationsJson = gson.toJson(userLocations);
        return userLocationsJson;
    }




}