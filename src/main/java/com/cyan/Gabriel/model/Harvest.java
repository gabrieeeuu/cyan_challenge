package com.cyan.Gabriel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
public class Harvest {

    @Id
    private String code;

    private String start;
    private String end;

    @OneToMany
    @JoinColumn
    private List<Farm> farms;

    private Harvest(){

    }

    public Harvest(@JsonProperty("code") String code,
                   @JsonProperty("start") String start,
                   @JsonProperty("end") String end,
                   @JsonProperty("farms") List<Farm> farms){
        this.code = code;
        this.start = start;
        this.end = end;

        this.farms = farms;
    }

    public Harvest addFarm(Farm farm){

        if(!this.farms.contains(farm)) {
            this.farms.add(farm);
        }

        return this;
    }

    public String getCode() {
        return code;
    }

    public String getEnd() {
        return end;
    }

    public String getStart() {
        return start;
    }

    public List<Farm> getFarms() {
        return farms;
    }
}
