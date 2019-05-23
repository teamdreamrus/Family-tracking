package com.example.familyTracking.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import javax.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@Entity
public class Location{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;

    private String username;
    private double latitude;
    private double longitude;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date;

    public Location(){
        date = new Date();
    }
}