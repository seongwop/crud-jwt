package com.example.jwtpost.repository;

import com.example.jwtpost.entity.Post;
import com.example.jwtpost.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();
    Optional<Post> findByIdAndUserId(Long id, User userId);
}
