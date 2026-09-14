package com.israel.api_blog.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Set;

@Builder
public record UpdateUserRequestDto(
        @Email(message = "Email must be valid")
        String email,

        @NotNull(message = "Enabled flag is required")
        Boolean enabled,

        @NotEmpty(message = "At least one role id is required")
        Set<Long> roleIds
) {
}
