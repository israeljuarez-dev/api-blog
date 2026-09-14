package com.israel.api_blog.role.dto.response;

import com.israel.api_blog.permission.dto.response.PermissionResponseDto;
import lombok.Builder;

import java.util.Set;

@Builder
public record RoleResponseDto(
        Long roleId,
        String name,
        Set<PermissionResponseDto> permissions
) {
}
