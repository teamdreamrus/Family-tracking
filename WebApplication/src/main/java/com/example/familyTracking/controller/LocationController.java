package com.example.familyTracking.controller;

import com.example.familyTracking.model.Friendship;
import com.example.familyTracking.repositories.FriendshipRepository;
import com.example.familyTracking.repositories.UserRepository;
import com.example.familyTracking.security.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public FriendshipRepository friendshipRepository;

    Gson gson = new GsonBuilder().create();
    @AllArgsConstructor
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
        List<Location> locationUser = locationRepository.findByUsername(user.getUsername());
        for(Location a : locationUser){
           locationRepository.deleteById(a.getId());
        }
        return "";
    }


    @GetMapping("{id}")
    public String getFriendLast(@PathVariable String id, @RequestParam("period") String period){
        //getting last friend's location
        Location l = new Location();
        l.setUsername("c");
        l.setLongitude(80.5);
        l.setLatitude(90.5);
        locationRepository.save(l);

        User user = getCurrentUser();
        User friend = userRepository.findById(Integer.parseInt(id)).orElse(new User());
        Integer flagTrueFriendship = 0;
        if(user.getId() == friend.getId()) flagTrueFriendship++;
        else{
            List<Integer> friendshipsID = friendshipRepository.getIDbyIdId(user.getId(),friend.getId());

            for(Integer idFriendship: friendshipsID){
                Friendship friendship = friendshipRepository.findById(idFriendship).orElse(new Friendship());
                System.out.println(friendship.isAccepted());
                if(friendship.isAccepted())
                    flagTrueFriendship++;
            }
        }
        List<UserLocation> userLocations = new LinkedList<>();
        if(flagTrueFriendship>0){

            switch(period){
                case "one":
                    UserLocation userlocal = new UserLocation();


                    Location local= locationRepository.LastUsername(friend.getUsername());
                    userlocal.setUsername(local.getUsername());
                    userlocal.setLatitude(local.getLatitude());
                    userlocal.setLongitude(local.getLongitude());
                    userLocations.add(userlocal);

                    break;
                case "hour":
                        //взять дату сейчас и час назад и в запрос их, дата должан быть между ними
                    Date now = new Date();
                    Date hourAgo = new Date(System.currentTimeMillis() - 3600 * 1000);
                    List<Location> locationPerHour = new LinkedList<>();
                    locationPerHour = locationRepository.LocalByDateBeetwin(now,hourAgo);
                    for(Location locationHour : locationPerHour){
                        userLocations.add(new UserLocation(locationHour.getUsername(),locationHour.getLatitude(),locationHour.getLongitude()));
                    }

                    break;
                case "day":
                    //взять дату сейчас и день назад и в запрос их
                    Date nowDay = new Date();
                    Date dayAgo = new Date(System.currentTimeMillis() - 3600 * 1000 * 24);
                    List<Location> locationPerDay = new LinkedList<>();
                    locationPerDay = locationRepository.LocalByDateBeetwin(nowDay,dayAgo);
                    for(Location locationDay : locationPerDay){
                        userLocations.add(new UserLocation(locationDay.getUsername(),locationDay.getLatitude(),locationDay.getLongitude()));
                    }
                    break;
            }

        }
        else return "";



        System.out.print("Getting last " + id + " location  for period " + period + " : ");



        String userLocationsJson = gson.toJson(userLocations);
        System.out.println(userLocationsJson);
        //send friend's location to user
        return userLocationsJson;
    }




}