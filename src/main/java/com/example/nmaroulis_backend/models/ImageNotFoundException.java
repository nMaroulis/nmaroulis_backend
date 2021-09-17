package com.example.nmaroulis_backend.models;

class ImageNotFoundException extends RuntimeException {

    ImageNotFoundException(Long id) {
        super("Could not find image " + id);
    }
}
