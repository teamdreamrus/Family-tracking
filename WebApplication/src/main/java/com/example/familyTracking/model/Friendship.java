package com.example.familyTracking.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class Friendship{
    public Friendship(Integer id1, Integer id2, Integer initiatorId, boolean accepted){
        this.id1 = id1;
        this.id2 = id2;
        this.initiatorId = initiatorId;
        this.accepted = accepted;
    }
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id;
    @SerializedName("id1")
    public Integer id1;
    @SerializedName("id2")
    public Integer id2;
    @SerializedName("initiatorId")
    public Integer initiatorId;
    @SerializedName("accepted")
    public boolean accepted;

}