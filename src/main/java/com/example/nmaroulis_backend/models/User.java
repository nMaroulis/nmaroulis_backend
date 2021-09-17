package com.example.nmaroulis_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {

    private @Id @GeneratedValue Long id;
    private String fname;
    private String lname;
    private String username;
    private String email;
    private String phone;
    private String title;
    private String education;
    private String work;
    private String gender;

    @ManyToOne (cascade= CascadeType.ALL)
    @JsonIgnoreProperties("users")
    private Role role;

    public User() {}

    public User(String fname,String lname, String username,String email,
                String phone,String title,String education,String work,String gender, Role role) {
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.title = title;
        this.education = education;
        this.work = work;
        this.gender = gender;
        this.role = role;
    }

    public User(String fname,String lname, String username, Role role) {
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.role = role;
    }


}