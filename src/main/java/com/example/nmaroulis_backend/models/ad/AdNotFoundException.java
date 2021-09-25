package com.example.nmaroulis_backend.models.ad;

public class AdNotFoundException extends RuntimeException {

    AdNotFoundException(Long id) {
        super("Could not find ad " + id);
    }

}
