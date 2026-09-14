package com.israel.api_blog.post.controller;

import com.israel.api_blog.post.dto.request.CreatePostRequestDto;
import com.israel.api_blog.post.dto.request.UpdatePostRequestDto;
import com.israel.api_blog.post.dto.response.CreatePostResponseDto;
import com.israel.api_blog.post.dto.response.PostResponseDto;
import com.israel.api_blog.post.dto.response.UpdatePostResponseDto;
import com.israel.api_blog.post.service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPostById(@Min(1) @PathVariable("postId") Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getPosts() {
        return ResponseEntity.ok(postService.getPosts());
    }

    @PostMapping
    public ResponseEntity<CreatePostResponseDto> createPost(@RequestBody @Valid CreatePostRequestDto createPostRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(createPostRequestDto));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<UpdatePostResponseDto> updatePost(
            @Min(1) @PathVariable("postId") Long postId,
            @RequestBody @Valid UpdatePostRequestDto updatePostRequestDto
    ) {
        return ResponseEntity.ok(postService.updatePost(postId, updatePostRequestDto));
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Void> deletePost(@Min(1) @PathVariable("postId") Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}
