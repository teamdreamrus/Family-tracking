package com.example.familyTracking.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Friendship{
    public Friendship(Integer id1, Integer id2, Integer initiatorId, boolean accepted){
        this.id1 = id1;
        this.id2 = id2;
        this.initiatorId = initiatorId;
        this.accepted = accepted;
    }
    @SerializedName("id1")
    public Integer id1;
    @SerializedName("id2")
    public Integer id2;
    @SerializedName("initiatorId")
    public Integer initiatorId;
    @SerializedName("accepted")
    public boolean accepted;
    @SerializedName("rejected")
    public boolean rejected;
}