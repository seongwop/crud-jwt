package com.example.crudjwt.service;


import com.example.crudjwt.dto.PostRequestDto;
import com.example.crudjwt.dto.PostResponseDto;
import com.example.crudjwt.message.Message;
import com.example.crudjwt.entity.Post;
import com.example.crudjwt.entity.User;
import com.example.crudjwt.jwt.JwtUtil;
import com.example.crudjwt.repository.PostRepository;
import com.example.crudjwt.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional(readOnly = true)
    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, HttpServletRequest request) {

        String token = jwtUtil.resolveToken(request);

        if (token != null) {
            User user = validateUser(token);

            Post post = new Post(requestDto, user);
            postRepository.saveAndFlush(post);

            return new PostResponseDto(post);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto, HttpServletRequest request) {

        String token = jwtUtil.resolveToken(request);

        if (token != null) {
            User user = validateUser(token);

            Post post = postRepository.findByIdAndUserId(id, user).orElseThrow(
                    () -> new NullPointerException("해당 사용자의 게시글은 존재하지 않습니다.")
            );

            post.update(requestDto);

            return new PostResponseDto(post);
        } else {
            return null;
        }

    }

    @Transactional
    public ResponseEntity<Message> deletePost(Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);

        if (token != null) {
            User user = validateUser(token);

            Post post = postRepository.findByIdAndUserId(id, user).orElseThrow(
                    () -> new NullPointerException("해당 사용자의 게시글은 존재하지 않습니다.")
            );

            postRepository.deleteById(post.getId());

            Message message = new Message();
            message.setMsg("삭제 성공");
            message.setStatusCode(HttpStatus.OK.value());

            return new ResponseEntity<>(message, HttpStatus.OK);

        } else {
            return null;
        }
    }

    private User validateUser(String token) {
        Claims claims;
        if (jwtUtil.validateToken(token)) {

            claims = jwtUtil.getUserInfoFromToken(token);
        } else {
            throw new IllegalArgumentException("Token Error");
        }
        return userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다")
        );
    }
}
