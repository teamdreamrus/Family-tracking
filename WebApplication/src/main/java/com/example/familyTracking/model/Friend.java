package com.example.familyTracking.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Friend extends UserPublic {
    public Friend(Integer id, String username, boolean accepted){
        super(id, username);
        this.accepted = accepted;
    }
    @SerializedName("accepted")
    public boolean accepted;
}