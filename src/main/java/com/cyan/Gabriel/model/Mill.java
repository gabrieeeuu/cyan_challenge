package com.cyan.Gabriel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Mill {

    @Id
    private String name;

    @OneToMany
    @JoinColumn
    private List<Harvest> harvests;

    private Mill(){

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
