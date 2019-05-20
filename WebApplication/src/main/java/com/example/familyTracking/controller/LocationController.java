package com.example.familyTracking.controller;

import com.example.familyTracking.security.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
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
        System.out.println("User " + userLocation.username + " latitude " + userLocation.latitude + " longitude " + userLocation.longitude);
        //getting new location from user, send it to database
        Location locUser = new Location();
        locUser.setUsername(userLocation.username);
        locUser.setLatitude(userLocation.latitude);
        locUser.setLongitude(userLocation.longitude);
        locationRepository.save(locUser);
        return newLocationJson;
    }

    private User getCurrentUser(){
        User user = null;
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            user = (User) auth.getPrincipal();
        }
        catch(ClassCastException ex){

            System.out.println("Trying to get user credentials for non authorized user");
        }
        return user;
    }

    @DeleteMapping()
    public String deleteAllLocations(){
        User user = getCurrentUser();
        System.out.print("Delete all data for user " + user.getId());
        //delete all locations for user.getUsername();
        //locationRepository.findAllById();
        return "";
    }


    @GetMapping("{id}")
    public String getFriendLast(@PathVariable String id, @RequestParam("period") String period){
        //getting last friend's location
        List<UserLocation> userLocations = new LinkedList<>();
        System.out.print("Getting last " + id + " location  for period " + period + " : ");
        switch(period){
            case "one":
                UserLocation userLocation = new UserLocation();
                switch(id){
                    case "id1":
                        userLocation.longitude = 80;
                        userLocation.latitude = 54;
                        break;
                    case "id2":
                        userLocation.longitude = 80;
                        userLocation.latitude = 53;
                        break;
                    case "id3":
                        userLocation.longitude = 81;
                        userLocation.latitude = 53;
                        break;
                }
                userLocation.username = id;
                userLocations.add(userLocation);
                break;
            case "hour":
                switch(id){
                    case "id1":
                        userLocations.add(new UserLocation(80, 54));
                        userLocations.add(new UserLocation(80, 55));
                        userLocations.add(new UserLocation(80, 56));
                        break;
                    case "id2":
                        userLocations.add(new UserLocation(80, 52));
                        userLocations.add(new UserLocation(81, 54));
                        userLocations.add(new UserLocation(82, 54));
                        break;
                    case "id3":
                        userLocations.add(new UserLocation(80, 51));
                        userLocations.add(new UserLocation(79, 51));
                        userLocations.add(new UserLocation(79, 50));
                        break;
                }
                //get last locations for hour
                break;
            case "day":
                //get last locations for day
                break;
        }


        String userLocationsJson = gson.toJson(userLocations);
        System.out.println(userLocationsJson);
        //send friend's location to user
        return userLocationsJson;
    }




}