package com.example.crudjwt.dto;

import com.example.crudjwt.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class PostResponseDto {
    private String title;
    private String username;
    private String content;
    private LocalDateTime createdAt;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.username = post.getUsername();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
    }
}
