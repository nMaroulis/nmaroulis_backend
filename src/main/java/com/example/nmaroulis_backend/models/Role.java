package com.example.nmaroulis_backend.models;

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
public class Role {

    private @Id @GeneratedValue Long id;
    private String name;

    @OneToMany(mappedBy = "role")
    @JsonIgnoreProperties("role")
    private List<User> users;

    public Role(String name) {
        this.name = name;
    }

}
