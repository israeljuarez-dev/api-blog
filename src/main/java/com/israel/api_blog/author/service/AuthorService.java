package com.israel.api_blog.author.service;

import com.israel.api_blog.author.dto.request.CreateAuthorRequestDto;
import com.israel.api_blog.author.dto.request.UpdateAuthorRequestDto;
import com.israel.api_blog.author.dto.response.AuthorResponseDto;
import com.israel.api_blog.author.dto.response.CreateAuthorResponseDto;
import com.israel.api_blog.author.dto.response.UpdateAuthorResponseDto;

import java.util.List;

public interface AuthorService {
    AuthorResponseDto getAuthorById(Long authorId);

    List<AuthorResponseDto> getAuthors();

    CreateAuthorResponseDto createAuthor(CreateAuthorRequestDto createAuthorRequestDto);

    UpdateAuthorResponseDto updateAuthor(Long authorId, UpdateAuthorRequestDto updateAuthorRequestDto);

    void deleteAuthor(Long authorId);
}
