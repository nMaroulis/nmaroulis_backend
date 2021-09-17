package com.example.nmaroulis_backend.models.post;

public class PostNotFoundException extends RuntimeException {

    PostNotFoundException(Long id) {
        super("Could not find post " + id);
    }

}
