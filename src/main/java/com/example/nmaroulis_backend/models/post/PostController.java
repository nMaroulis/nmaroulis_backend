package com.example.nmaroulis_backend.models.post;

import java.util.List;

import com.example.nmaroulis_backend.models.ResourceNotFoundException;
import com.example.nmaroulis_backend.models.user.User;
import com.example.nmaroulis_backend.models.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;


//    PostController(PostRepository repository) {
//        this.postRepository = repository;
//    }

    // Aggregate root

    @GetMapping("/users/{userId}/posts")
    public Page<Post> getAllPostsByUserId(@PathVariable (value = "userId") Long userId,
                                          Pageable pageable) {
        return postRepository.findByUserId(userId, pageable);
    }

    @PostMapping("/users/{userId}/posts")
    public Post createPost(@PathVariable (value = "userId") Long userId,
                                  Post post) {

        return userRepository.findById(userId).map(user -> {
            post.setUser(user);
            return postRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

    @PutMapping("/users/{userId}/post/{postId}")
    public Post updatePost(@PathVariable (value = "userId") Long userId,
                                 @PathVariable (value = "postId") Long postId,
                                 @Valid @RequestBody Post postRequest) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("userId " + userId + " not found");
        }

        return postRepository.findById(postId).map(post -> {
            //post.setText(postRequest.getText());
            return postRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + "not found"));
    }

    @DeleteMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable (value = "userId") Long userId,
                                           @PathVariable (value = "postId") Long postId) {
        return postRepository.findByIdAndUserId(postId, userId).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + postId + " and userId " + userId));
    }


//    @GetMapping("/posts")
//    List<Post> all() {
//        return repository.findAll();
//    }


}
