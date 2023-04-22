package com.example.crudjwt.service;

import com.example.crudjwt.dto.LoginRequestDto;
import com.example.crudjwt.dto.SignupRequestDto;
import com.example.crudjwt.entity.User;
import com.example.crudjwt.jwt.JwtUtil;
import com.example.crudjwt.message.Message;
import com.example.crudjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public ResponseEntity<Message> signUp(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
        User user = new User(username, password);

        userRepository.save(user);

        Message message = new Message();
        message.setMsg("회원가입 성공");
        message.setStatusCode(HttpStatus.OK.value());

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Message> login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if(!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));

        Message message = new Message();
        message.setMsg("로그인 성공");
        message.setStatusCode(HttpStatus.OK.value());

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
