package com.example.nmaroulis_backend.models.post;


import com.example.nmaroulis_backend.models.user.User;
import com.example.nmaroulis_backend.models.image.Image;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Post {

    private @Id @GeneratedValue Long id;
    private String title;
    private String body;
    private Boolean accesibility;  // public or private

//    @ManyToOne(cascade= CascadeType.ALL)
//    @JsonIgnoreProperties("post")
//    @JoinColumn(name="user_id", nullable=false)
//    private User user;


    @ManyToOne(cascade= CascadeType.ALL)
    @JsonIgnoreProperties("post")
    private Image image;

    public Post() {}

    public Post(String title,String body, boolean accesibility, Image image) {
        this.title = title;
        this.body = body;
        this.accesibility = accesibility;
        //this.user = user;
        this.image = image;
    }

    public Post(String title,String body, boolean accesibility) {
        this.title = title;
        this.body = body;
        this.accesibility = accesibility;
        //this.user = user;
    }

}
