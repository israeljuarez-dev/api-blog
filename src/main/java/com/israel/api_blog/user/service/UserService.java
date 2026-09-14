package com.israel.api_blog.user.service;

import com.israel.api_blog.user.dto.request.RegisterUserRequestDto;
import com.israel.api_blog.user.dto.request.UpdateUserRequestDto;
import com.israel.api_blog.user.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto getUserById(Long userId);

    List<UserResponseDto> getUsers();

    UserResponseDto register(RegisterUserRequestDto registerUserRequestDto);

    UserResponseDto updateUser(Long userId, UpdateUserRequestDto updateUserRequestDto);

    void deleteUser(Long userId);
}
