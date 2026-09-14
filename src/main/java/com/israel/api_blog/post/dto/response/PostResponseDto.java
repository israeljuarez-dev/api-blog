package com.israel.api_blog.post.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostResponseDto (
        Long postId,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long authorId,
        String authorFullName
){
}
