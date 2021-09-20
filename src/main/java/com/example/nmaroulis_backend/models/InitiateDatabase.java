package com.example.nmaroulis_backend.models;

import com.example.nmaroulis_backend.models.post.Post;
import com.example.nmaroulis_backend.models.post.PostRepository;

import com.example.nmaroulis_backend.models.user.User;
import com.example.nmaroulis_backend.models.user.UserRepository;
import com.example.nmaroulis_backend.title.Title;
import javafx.geometry.Pos;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
@Slf4j
class InitiateDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
//            log.info("Preloading " + repository.save(new User("Nikos",
//                    "Nikolaou", "nik", "nik@gmail.com", "+30 69 823732821", "Athens, Greece","MS.c. in Computer Science", "Data Scientinst at Google", "male", new Title("Data Scientist")  )));
//
//            log.info("Preloading " + repository.save(new User("Emily",
//                    "Tzani", "emily", "emily@gmail.com", "+30 69 827732821", "LA California, USA", "BS.c. in Computer Science", "Software Developer at Google", "female", new Title("Android Developer")  )));
//
//            log.info("Preloading " + repository.save(new User("Stathis",
//                    "Papadopoulos", "stathis", "stathis@gmail.com", "+30 69 823700821", "Athens, Greece","MS.c. in Computer Science", "GO Developer at Google", "male", new Title("Software Developer")  )));

        };
    }

}
