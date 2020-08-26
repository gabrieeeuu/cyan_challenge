package com.cyan.Gabriel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String name;

    @OneToMany(targetEntity = Field.class)
    @JoinColumn
    @Column
    private List<Field> fields;

    public Farm(){

    }

    public Farm(@JsonProperty("name") String name,
                @JsonProperty("fields") List<Field> fields) {
        this.name = name;
        this.fields = fields;
    }

    public Farm addField(Field field) {

        if(!this.fields.contains(field)){
            this.fields.add(field);
        }

        return this;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Field> getFields() {
        return fields;
    }
}
