package com.cyan.Gabriel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class User {

    @Id
    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String password;

    public User(){
    }

    public User(@JsonProperty("name") String name,
                @JsonProperty("email") String email,
                @JsonProperty("password") String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
