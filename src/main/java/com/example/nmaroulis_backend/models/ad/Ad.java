package com.example.nmaroulis_backend.models.ad;

import com.example.nmaroulis_backend.models.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


@Data
@Entity
public class Ad {

    private @Id
    @GeneratedValue
    Long id;
    private String title;
    private String body;
    private String position;  // public or private
    private String created_by;  // public or private
    private String time_posted;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;


    public Ad() {}

    public Ad(String title,String body, String position, String time_posted,String created_by, User user) {
        this.title = title;
        this.body = body;
        this.position = position;
        this.time_posted = time_posted;
        this.created_by = created_by;
        this.user = user;
    }


}
