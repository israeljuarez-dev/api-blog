package com.israel.api_blog.post.service;

import com.israel.api_blog.author.model.Author;
import com.israel.api_blog.author.repository.AuthorRepository;
import com.israel.api_blog.post.dto.request.CreatePostRequestDto;
import com.israel.api_blog.post.dto.request.UpdatePostRequestDto;
import com.israel.api_blog.post.dto.response.CreatePostResponseDto;
import com.israel.api_blog.post.dto.response.PostResponseDto;
import com.israel.api_blog.post.dto.response.UpdatePostResponseDto;
import com.israel.api_blog.post.mapper.PostMapper;
import com.israel.api_blog.post.model.Post;
import com.israel.api_blog.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    private final AuthorRepository authorRepository;

    private final PostMapper postMapper;

    @Override
    @Transactional(readOnly = true)
    public PostResponseDto getPostById(Long postId) {
        return postMapper.toPostResponseDto(findPostOrThrow(postId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDto> getPosts() {
        return postRepository.findAll().stream()
                .map(postMapper::toPostResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public CreatePostResponseDto createPost(CreatePostRequestDto createPostRequestDto) {
        Author author = authorRepository.findById(createPostRequestDto.authorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Author not found with id: " + createPostRequestDto.authorId()));

        Post post = Post.builder()
                .title(createPostRequestDto.title())
                .content(createPostRequestDto.content())
                .author(author)
                .build();

        Post postSaved = postRepository.save(post);
        log.info("Posteo registrado exitosamente con id {}", postSaved.getPostId());

        return postMapper.toCreatePostResponseDto(postSaved);
    }

    @Override
    @Transactional
    public UpdatePostResponseDto updatePost(Long postId, UpdatePostRequestDto updatePostRequestDto) {
        Post post = findPostOrThrow(postId);

        post.setTitle(updatePostRequestDto.title());
        post.setContent(updatePostRequestDto.content());

        Post postUpdated = postRepository.save(post);
        log.info("Posteo con id {} actualizado exitosamente", postUpdated.getPostId());

        return postMapper.toUpdatePostResponseDto(postUpdated);
    }

    @Override
    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
        log.info("Posteo con id {} eliminado exitosamente", postId);
    }

    private Post findPostOrThrow(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> {
                    log.debug("No existe posteo con id: {}", postId);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found with id: " + postId);
                });
    }
}
