package com.example.nmaroulis_backend.models;

import com.example.nmaroulis_backend.models.post.Post;
import com.example.nmaroulis_backend.models.user.User;
import com.example.nmaroulis_backend.models.user.UserRepository;
import com.example.nmaroulis_backend.title.Title;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class InitiateDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new User("Nikos",
                    "Nikolaou", "nik", "male", new Title("Data Scientist"))));

            log.info("Preloading " + repository.save(new User("Emily",
                    "Pap", "emily1","female", new Title("Andriod Developer"))));

        };
    }
}
