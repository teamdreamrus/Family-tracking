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
import org.slf4j.Logger;
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
    Logger logger;

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
        List<Location> locationUser = locationRepository.findByUsername(user.getUsername());
        for(Location a : locationUser){
           locationRepository.deleteById(a.getId());
        }
        return "";
    }


    @GetMapping("{id}")
    public String getFriendLast(@PathVariable String id, @RequestParam("period") String period){


        User user = getCurrentUser();
        User friend = userRepository.findById(Integer.parseInt(id)).orElse(new User());

        logger.debug("User " + user.getUsername() + " getting locations of user with id " + id + " for period " + period);
        List<UserLocation> userLocations = new LinkedList<>();

            List<Integer> friendshipsID = friendshipRepository.getIDbyIdId(user.getId(),friend.getId());

            for(Integer idFriendship: friendshipsID){
                Friendship friendship = friendshipRepository.findById(idFriendship).orElse(new Friendship());

                if(friendship.isAccepted() | user.getUsername().equals(friend.getUsername()) )

                 {

			
                    switch(period){
                        case "one":



                            Location local= locationRepository.LastUsername(friend.getUsername());
                            if(local == null) return "";
                            System.out.println("LOCAL: ");
                            System.out.println(local);
                            UserLocation userlocalVAR = new UserLocation(local.getUsername(),local.getLatitude(),local.getLongitude());

                            userLocations.add(userlocalVAR);

                            break;
                        case "hour":
                            //взять дату сейчас и час назад и в запрос их, дата должан быть между ними
                            Date now = new Date();
                            Date hourAgo = new Date(System.currentTimeMillis() - 3600 * 1000);
                            List<Location> locationPerHour = new LinkedList<>();
                            locationPerHour = locationRepository.LocalDateBeetwin(now,hourAgo,friend.getUsername());
                            for(Location locationHour : locationPerHour){
                                userLocations.add(new UserLocation(locationHour.getUsername(),locationHour.getLatitude(),locationHour.getLongitude()));
                            }

                            break;
                        case "day":
                            //взять дату сейчас и день назад и в запрос их
                            Date nowDay = new Date();
                            Date dayAgo = new Date(System.currentTimeMillis() - 3600 * 1000 * 24);
                            List<Location> locationPerDay = new LinkedList<>();
                            locationPerDay = locationRepository.LocalDateBeetwin(nowDay,dayAgo,friend.getUsername());
                            for(Location locationDay : locationPerDay){
                                userLocations.add(new UserLocation(locationDay.getUsername(),locationDay.getLatitude(),locationDay.getLongitude()));
                            }
                            break;
                    }
                }
                }





        System.out.print("Getting last " + id + " location  for period " + period + " : ");



        String userLocationsJson = gson.toJson(userLocations);
        return userLocationsJson;
    }




}