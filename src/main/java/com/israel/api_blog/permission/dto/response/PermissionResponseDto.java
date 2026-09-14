package com.israel.api_blog.permission.dto.response;

import lombok.Builder;

@Builder
public record PermissionResponseDto(
        Long permissionId,
        String name
) {
}
