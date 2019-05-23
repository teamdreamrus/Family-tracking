package com.example.familyTracking.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class UserPublic {
    public UserPublic(Integer id, String username){
        this.username = username;
        this.id = id;
    }
    @SerializedName("id")
    public Integer id;
    @SerializedName("username")
    public String username;
}
