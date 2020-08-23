package com.cyan.Gabriel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Field {

    @Id
    private String code;

    private Double latitude;
    private Double longitude;

    public Field(){

    }

    public Field(@JsonProperty("code") String code,
                 @JsonProperty("latitude") Double latitude,
                 @JsonProperty("longitude") Double longitude){
        this.code = code;
        if(latitude>100 || latitude<-100){
            throw new InternalError("Invalid Latitude.");
        }
        this.latitude = latitude;
        if(longitude>100 || longitude<-100){
            throw new InternalError("Invalid Longitude.");
        }
        this.longitude = longitude;
    }

    public String getCode() {
        return code;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }
}
