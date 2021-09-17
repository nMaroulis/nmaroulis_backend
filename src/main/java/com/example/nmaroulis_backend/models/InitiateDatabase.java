package com.example.nmaroulis_backend.models;

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
            log.info("Preloading " + repository.save(new User("Bslbo", "Bagdfsds", "bssdob", new Role("burglar"))));
            log.info("Preloading " + repository.save(new User("Frssodo", "Baggfns", "frfsb", new Role("thief"))));
        };
    }
}
