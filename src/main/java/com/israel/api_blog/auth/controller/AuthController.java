package com.israel.api_blog.auth.controller;

import com.israel.api_blog.user.dto.request.RegisterUserRequestDto;
import com.israel.api_blog.user.dto.response.UserResponseDto;
import com.israel.api_blog.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody @Valid RegisterUserRequestDto registerUserRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(registerUserRequestDto));
    }
}
