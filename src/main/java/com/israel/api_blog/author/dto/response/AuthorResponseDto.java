package com.israel.api_blog.author.dto.response;

public record AuthorResponseDto(
        Long authorId,
        String firstName,
        String lastName,
        String email,
        String bio
) {
}
