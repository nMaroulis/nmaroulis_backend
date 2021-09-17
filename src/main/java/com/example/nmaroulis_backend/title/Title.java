package com.example.nmaroulis_backend.title;

import com.example.nmaroulis_backend.models.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Title {

    private @Id @GeneratedValue Long id;
    private String name;

    @OneToMany(mappedBy = "title")
    @JsonIgnoreProperties("title")
    private List<User> users;

    public Title(String name) {
        this.name = name;
    }

}
