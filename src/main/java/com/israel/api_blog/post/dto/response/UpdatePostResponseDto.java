package com.israel.api_blog.post.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UpdatePostResponseDto(
        Long postId,
        String title,
        String content,
        LocalDateTime updatedAt
) {
}
