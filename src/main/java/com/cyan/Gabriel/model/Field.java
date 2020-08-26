package com.cyan.Gabriel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;


@Table
@Entity
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    public Field(){

    }

    public Field(@JsonProperty("latitude") Double latitude,
                 @JsonProperty("longitude") Double longitude){
        if(latitude>90 || latitude<-90){
            throw new InternalError("Invalid Latitude.");
        }
        this.latitude = latitude;
        if(longitude>180 || longitude<-180){
            throw new InternalError("Invalid Longitude.");
        }
        this.longitude=longitude;
    }

    public long getId() {
        return id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
