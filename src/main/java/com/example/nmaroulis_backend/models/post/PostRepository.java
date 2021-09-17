package com.example.nmaroulis_backend.models.post;

import com.example.nmaroulis_backend.models.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
