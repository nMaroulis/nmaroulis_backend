package com.example.nmaroulis_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class }) //remove after the parentheses to secure
public class RestApplication {

    //    @Bean
    //    public BCryptPasswordEncoder bCryptPasswordEncoder() {
    //        return new BCryptPasswordEncoder();
    //    }
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

}
