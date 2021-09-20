package com.example.nmaroulis_backend.models.post;

import com.example.nmaroulis_backend.models.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByUserId(Long postId, Pageable pageable);
    Optional<Post> findByIdAndUserId(Long id, Long postId);

}
