package com.example.nmaroulis_backend.models.image;

class ImageNotFoundException extends RuntimeException {

    ImageNotFoundException(Long id) {
        super("Could not find image " + id);
    }
}
