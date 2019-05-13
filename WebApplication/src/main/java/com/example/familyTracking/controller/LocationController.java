package com.example.familyTracking.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/location")
public class LocationController{

    Gson gson = new GsonBuilder().create();

    @Data
    private class UserLocation{
        @SerializedName("username")
        public String username;
        @SerializedName("latitude")
        public String latitude;
        @SerializedName("longitude")
        public String longitude;
    }

    @PostMapping
    public String newLocation(@RequestBody String newLocationJson){
        UserLocation userLocation = gson.fromJson(newLocationJson, UserLocation.class);
        System.out.print("User " + userLocation.username + " latitude " + userLocation.latitude + " longitude " + userLocation.longitude);
        //getting new location цfrom user
        return newLocationJson;
    }


    @GetMapping("{id}")
    public String getFriendLast(@PathVariable String id, @RequestParam("period") String period){
        //getting last friend's location
        System.out.print("Getting last " + id + " location  for period " + period );
        switch(period){
            case "one":
                //get one last location
                break;
            case "hour":
                //get last locations for hour
                break;
            case "day":
                //get last locations for day
                break;
        }
        return "";
    }




}