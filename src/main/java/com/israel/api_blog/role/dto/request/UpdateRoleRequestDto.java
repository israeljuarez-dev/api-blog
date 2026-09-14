package com.israel.api_blog.role.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.Set;

@Builder
public record UpdateRoleRequestDto(
        @NotBlank(message = "Name is required")
        @Size(max = 30, message = "Name must not exceed 30 characters")
        String name,

        @NotEmpty(message = "At least one permission id is required")
        Set<Long> permissionIds
) {
}
