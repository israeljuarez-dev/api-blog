package com.israel.api_blog.post.service;

import com.israel.api_blog.post.dto.request.CreatePostRequestDto;
import com.israel.api_blog.post.dto.request.UpdatePostRequestDto;
import com.israel.api_blog.post.dto.response.CreatePostResponseDto;
import com.israel.api_blog.post.dto.response.PostResponseDto;
import com.israel.api_blog.post.dto.response.UpdatePostResponseDto;

import java.util.List;

public interface PostService {
    PostResponseDto getPostById(Long postId);

    List<PostResponseDto> getPosts();

    CreatePostResponseDto createPost(CreatePostRequestDto createPostRequestDto);

    UpdatePostResponseDto updatePost(Long postId, UpdatePostRequestDto updatePostRequestDto);

    void deletePost(Long postId);
}
