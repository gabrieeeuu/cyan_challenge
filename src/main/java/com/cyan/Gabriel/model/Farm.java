package com.cyan.Gabriel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
public class Farm {

    @Id
    private String code;

    private String name;

    @OneToMany
    @JoinColumn
    private List<Field> fields;

    private Farm(){

    }

    public Farm(@JsonProperty("code") String code,
                @JsonProperty("name") String name,
                @JsonProperty("fields") List<Field> fields) {
        this.code = code;
        this.name = name;
        this.fields = fields;
    }

    public Farm addField(Field field) {

        if(!this.fields.contains(field)){
            this.fields.add(field);
        }

        return this;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public List<Field> getFields() {
        return fields;
    }
}
