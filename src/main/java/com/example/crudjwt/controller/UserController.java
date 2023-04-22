package com.example.crudjwt.controller;

import com.example.crudjwt.dto.LoginRequestDto;
import com.example.crudjwt.dto.SignupRequestDto;
import com.example.crudjwt.message.Message;
import com.example.crudjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Message> signup(@RequestBody SignupRequestDto signupRequestDto) {

        return userService.signUp(signupRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Message> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {

        return userService.login(loginRequestDto, response);
    }
}
