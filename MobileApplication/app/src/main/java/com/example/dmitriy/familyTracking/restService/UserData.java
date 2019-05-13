package com.example.dmitriy.familyTracking.restService;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;

public class UserData {
    @SerializedName("username")
    public String username;
    @SerializedName("id")
    public String id;
    public List<UserLocation> userLocations;

    public UserData(){
        userLocations = new LinkedList<UserLocation>();
    }

}
