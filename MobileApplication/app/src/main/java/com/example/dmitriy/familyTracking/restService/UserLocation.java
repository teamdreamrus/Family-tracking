package com.example.dmitriy.familyTracking.restService;

import com.google.gson.annotations.SerializedName;

public class UserLocation {
    @SerializedName("username")
    public String username;
    @SerializedName("latitude")
    public String latitude;
    @SerializedName("longitude")
    public String longitude;
}
