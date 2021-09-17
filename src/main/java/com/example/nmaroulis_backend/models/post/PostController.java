package com.example.nmaroulis_backend.models.post;

import java.util.List;

import com.example.nmaroulis_backend.models.user.User;
import com.example.nmaroulis_backend.models.user.UserRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    private final PostRepository repository;

    PostController(PostRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/posts")
    List<Post> all() {
        return repository.findAll();
    }


}
