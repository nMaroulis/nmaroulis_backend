package com.example.nmaroulis_backend.models.user;

import com.example.nmaroulis_backend.models.post.Post;
import com.example.nmaroulis_backend.title.Title;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {

    private @Id @GeneratedValue Long id;
    private String fname;
    private String lname;
    private String username;
    private String email;
    private String phone;
    private String education;
    private String work;
    private String gender;

    @ManyToOne (cascade= CascadeType.ALL)
    @JsonIgnoreProperties("users")
    private Title title;

    @OneToMany (cascade= CascadeType.ALL)
    @JsonIgnoreProperties("users")
    private List<Post> posts;

    public User() {}

    public User(String fname,String lname, String username,String email,
                String phone,String education,String work,String gender, Title title) {
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.education = education;
        this.work = work;
        this.gender = gender;
        this.title = title;
    }

    public User(String fname,String lname, String username, String gender, Title title) {
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.title = title;
    }

}