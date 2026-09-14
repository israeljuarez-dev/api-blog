package com.israel.api_blog.user.controller;

import com.israel.api_blog.user.dto.request.UpdateUserRequestDto;
import com.israel.api_blog.user.dto.response.UserResponseDto;
import com.israel.api_blog.user.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@Min(1) @PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(
            @Min(1) @PathVariable("userId") Long userId,
            @RequestBody @Valid UpdateUserRequestDto updateUserRequestDto
    ) {
        return ResponseEntity.ok(userService.updateUser(userId, updateUserRequestDto));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@Min(1) @PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}