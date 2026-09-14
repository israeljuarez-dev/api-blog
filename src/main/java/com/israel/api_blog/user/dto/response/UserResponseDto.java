package com.israel.api_blog.user.dto.response;

import com.israel.api_blog.role.dto.response.RoleResponseDto;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record UserResponseDto(
        Long userId,
        String username,
        String email,
        Boolean enabled,
        LocalDateTime createdAt,
        Set<RoleResponseDto> roles
) {
}
