package com.cyan.Gabriel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import java.util.List;

@Table
@Entity
public class Harvest {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String start;

    @Column
    private String end;

    @OneToMany(targetEntity = Farm.class)
    @JoinColumn
    private List<Farm> farms;

    public Harvest(){

    }

    public Harvest(@JsonProperty("start") String start,
                   @JsonProperty("end") String end,
                   @JsonProperty("farms") List<Farm> farms){
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

    public long getId() {
        return this.id;
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
