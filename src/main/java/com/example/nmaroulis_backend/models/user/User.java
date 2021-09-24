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

    private @Column(unique=true) String username;
    private String email;

    private @Column(nullable = false) String password;

    private String phone;
    private String residence;

    private String education;
    private String work;
    private String gender;
    private String member_since;
    @ManyToOne (cascade= CascadeType.ALL)
    @JsonIgnoreProperties("users")
    private Title title;

//    @OneToMany (cascade= CascadeType.ALL)
//    @JsonIgnoreProperties("users")
//    private List<Post> posts;

    @ManyToMany (cascade= CascadeType.ALL)
    @JsonIgnoreProperties({"request_connections", "response_connections", "connections", "password"})
    private List<User> connections;

    @ManyToMany (cascade= CascadeType.ALL)
    @JsonIgnoreProperties({"request_connections", "response_connections", "connections", "password"})
    private List<User> request_connections;

    @ManyToMany (cascade= CascadeType.ALL)
    @JsonIgnoreProperties({"request_connections", "response_connections", "connections", "password"})
    private List<User> response_connections;


    public User() {}

    public User(String username, String password, String fname,String lname , String email,
                String phone, String residence, String education,String work,String gender, Title title, String member_since) {
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.residence = residence;
        this.education = education;
        this.work = work;
        this.gender = gender;
        this.title = title;
        this.member_since = member_since;
    }

    public User(String username, String password, String fname,String lname, String email, String gender, Title title,String residence, String member_since) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.title = title;
        this.residence = residence;
        this.member_since = member_since;
    }

    public void setUser(String username) {
        this.username = username;
    }

    public void setToken(String token) {
        this.password = token;
    }
}