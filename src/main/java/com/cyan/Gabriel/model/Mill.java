package com.cyan.Gabriel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
public class Mill {

    @Id
    @Column
    private String name;

    @OneToMany(targetEntity = Harvest.class)
    @JoinColumn
    @Column
    private List<Harvest> harvests;

    public Mill(){

    }

    public Mill(@JsonProperty ("name") String name,
                @JsonProperty ("harvests") List<Harvest> harvests) {
        this.name = name;
        this.harvests = harvests;
    }

    public Mill addHarvest(Harvest harvest){
        if (!this.harvests.contains(harvest)){
            this.harvests.add(harvest);
        }
        return this;
    }

    public String getName() {
        return name;
    }

    public List<Harvest> getHarvests() {
        return harvests;
    }
}
