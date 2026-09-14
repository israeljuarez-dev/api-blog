package com.israel.api_blog.author.dto.response;

public record UpdateAuthorResponseDto(
        Long authorId,
        String firstName,
        String lastName,
        String email,
        String bio
) {
}
