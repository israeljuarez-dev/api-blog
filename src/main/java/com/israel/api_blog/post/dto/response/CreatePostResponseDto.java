package com.israel.api_blog.post.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CreatePostResponseDto(
        Long postId,
        String title,
        String content,
        LocalDateTime createdAt,
        Long authorId
) {
}
